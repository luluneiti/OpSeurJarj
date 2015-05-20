package sovelluslogiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Pelihallinta luokka hallinnoi pelaajien noppia ja yhdistelmiä
 *
 */
public class PelinHallinta {

    private static int kierroslkm;
    private static PeliMuunnelma peli;
    private static Pelaajat pelaajienHallinta = new Pelaajat();
    private static HashSet<Pelaaja> pelaajaLista;
    private static HashMap<String, ArrayList<Noppa>> pelaajienNopat;
    private static HashMap<String, ArrayList<Yhdistelma>> pelaajienYhdistelmat;

    /**
     * Konstruktori luo PeliMuunnelma olion parametrin mukaisesti ja alustaa
     * kierroslukumittarin säännöt
     *
     * @param muunnelma
     */
    public PelinHallinta(String muunnelma) {

        kierroslkm = 1;
        if (muunnelma.equalsIgnoreCase("pakkojatsi")) {
            peli = new Pakkojatsi();
        } else {
            throw new IllegalArgumentException("Antamasi pelimuunnelma ei ole käytettävissä");
        }
        pelaajaLista = pelaajienHallinta.annaPelaajat();
        luoPelaajienNopat();
        luoPelaajienYhdistelmat();

    }

    /**
     * Apumetodi joka kytkee nopat pelaajiin
     *
     */
    private static void luoPelaajienNopat() {

        pelaajienNopat = new HashMap<String, ArrayList<Noppa>>();
        ArrayList<Noppa> nopat = new ArrayList<Noppa>();

        for (Pelaaja pelaaja : pelaajaLista) {
            nopat = peli.luoNopat();
            //System.out.println(nopat.size());
            pelaajienNopat.put(pelaaja.annaNimi(), nopat);

        }

    }

    /**
     * Apumetodi joka kytkee yhdistelmat pelaajiin
     *
     */
    private static void luoPelaajienYhdistelmat() {

        pelaajienYhdistelmat = new HashMap<String, ArrayList<Yhdistelma>>();
        ArrayList<Yhdistelma> yhdistelmat = new ArrayList<Yhdistelma>();

        for (Pelaaja pelaaja : pelaajaLista) {
            yhdistelmat = peli.luoYhdistelmat();
            //System.out.println(yhdistelmat.size());
            pelaajienYhdistelmat.put(pelaaja.annaNimi(), yhdistelmat);

        }

    }

    /**
     * Metodi heittää pyydetyn pelaajan pyydettyhkä noppia
     *
     * @param nimi
     * @param indeksit
     */
    public void heitaPelaajanNoppia(String nimi, int[] indeksit) {

        ArrayList<Integer> lukemat = new ArrayList<Integer>();
        int i = 0;
        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

        for (Noppa noppa : nopat) {

            if (onkoIndeksiJoukossa(indeksit, i)) {
                noppa.heita();
            }
            lukemat.add(noppa.annaLukema()); //muös aiemmat lukemat mukaan
            i++;

        }
        yllapidaYhidstelmanPisteet(nimi, lukemat);

    }

    /**
     * Apumetodi joka pydettäessä tarkistaa ovatko kaikki pelaajat heittäneet 3
     * kertaa noppaa ja voidaan aloittaaa uusi kierros
     *
     */
    private static void tarkistaAlkaakoUusiKierros() {

        int kolmaskierroKaikilla = 0;
        boolean kolmaskierrosPelaajalla;

        for (Pelaaja pelaaja : pelaajaLista) {

            kolmaskierrosPelaajalla = false;
            ArrayList<Noppa> nopat = pelaajienNopat.get(pelaaja.annaNimi());

            for (Noppa noppa : nopat) {

                if (noppa.annaKierroslkm() == 1) { //toistaiseksi ei voi heittä kuin kerran 
                    //System.out.print(noppa.annaKierroslkm());
                    kolmaskierrosPelaajalla = true;
                }
            }
            if (kolmaskierrosPelaajalla == true) {
                kolmaskierroKaikilla++;
            }

        }

        if (kolmaskierroKaikilla == pelaajaLista.size() && kierroslkm < 14) {
            kierroslkm++;                                   //KIERRO VOI VAIHTUA MYÖS ENNEN KUIN HEITETTY KOLME KERTAA-> PELAAJA PAINAA LOPETA PAINIKETTA!
            nollaPelaajienNoppienKierroslkm();
        }
        //System.out.println("kierros: "+kierroslkm);

    }

    /**
     * Apumetodi joka pyydettäessä nollaa kaikkien noppien kierroslukumittarin
     *
     */
    private static void nollaPelaajienNoppienKierroslkm() {

        for (Pelaaja pelaaja : pelaajaLista) {

            ArrayList<Noppa> nopat = pelaajienNopat.get(pelaaja.annaNimi());

            for (Noppa noppa : nopat) {

                noppa.nollaaKierroslkm();
            }

        }

    }

    /**
     * Metodi joka palauttaa pydetyn pelaajan noppien lukemat
     *
     * @param nimi
     * @return lukemat
     */
    public ArrayList<Integer> annaPelaajanNoppienLukemat(String nimi) {

        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);
        ArrayList<Integer> lukemat = new ArrayList<Integer>();
        for (Noppa noppa : nopat) {
            lukemat.add(noppa.annaLukema());
        }

        return lukemat;
    }

    /**
     * Apumetodi pyydettäessä tutkii onko ko. indeksi pyydettyjen indeksien
     * joukossa Noppien heitto-metodi kutsuu tätä metodia selvittääkseen, että
     * onko pelaajan ko. noppaa pydetty heittää
     *
     * @param indeksit, indeksi
     * @return boolean arvo
     */
    private static boolean onkoIndeksiJoukossa(int[] indeksit, int indeksi) {

        for (Integer ind : indeksit) {
            if (ind == indeksi) {
                return true;
            }
        }
        return false;
    }

    /**
     * Apumetodi joka pyydettäessä ylläptää pyydetyn pelaajan pelattavan
     * yhdistelman pisteet nopan lukemien mukaisesti
     *
     * @param nimi, lukemat
     */
    private static void yllapidaYhidstelmanPisteet(String nimi, ArrayList<Integer> lukemat) {

        int pist = peli.laskeYhdistelmanPisteet(lukemat, kierroslkm);
        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(kierroslkm);

        ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(nimi);

        for (Yhdistelma yhdistel : pelaajanYhd) {

            if (yhdistel.annaNimi() == pelattavaYhd) {
                yhdistel.asetaPisteet(pist);
            }
        }

    }

    /**
     * Metodi joka antaa pyydetyn pelaajan pelattavan yhdistelman pisteet
     *
     * @param nimi
     * @return pisteet
     */
    public int annaPelaajanViimeisimmanYHdistelmanPisteet(String nimi) {

        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(kierroslkm);
        System.out.print(pelattavaYhd + ": ");
        int pist = 0;
        ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(nimi);

        for (Yhdistelma yhdistel : pelaajanYhd) {

            if (yhdistel.annaNimi() == pelattavaYhd) {
                pist = yhdistel.annaPisteet();
            }
        }
        tarkistaAlkaakoUusiKierros();
        return pist;
    }

    /**
     * Metodi joka tutkii kenellä korkeimmat kokonaispisteet ja palauttaa
     * voittajan nimen
     *
     * @return nimi
     */
    public String julistavoittaja() {
        int i = 0;

        HashMap<String, Integer> kokonaisPisteet = new HashMap<String, Integer>();
        for (Pelaaja pelaaja : pelaajaLista) {
            ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(pelaaja.annaNimi());
            int pist = peli.laskeKokonaisPisteet(pelaajanYhd);
            kokonaisPisteet.put(pelaaja.annaNimi(), pist);

            if (pelaaja.annaEnnatysPisteet() < pist) { //onko ok tehdä tästä luokasta
                pelaaja.asetaEnnatysPisteet(pist);
            }
        }
        if (pelaajaLista.size() > 1) {
            return etsiKorkeimmatPisteet(kokonaisPisteet);
        } else {
            return "ohjelma kesken, pitäisi tarkistaa ylittääkö omat pisteet! mutta nehän jo päivitettiin....";
        }
    }

    /**
     * Apumetodi joka etsii korkeimmat pisteet omaavan pelaajan
     *
     * @param pisteet
     * @return pelaajan nimi
     */
    private static String etsiKorkeimmatPisteet(HashMap<String, Integer> pisteet) {

        String voittaja = "";
        int korkein = 0;
        for (String avain : pisteet.keySet()) {
            if (pisteet.get(avain) > korkein) {
                korkein = pisteet.get(avain);
                voittaja = avain;
            }

        }
        return voittaja;
    }
}

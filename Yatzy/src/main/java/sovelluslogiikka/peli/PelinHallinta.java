package sovelluslogiikka.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sovelluslogiikka.pelaaja.Pelaajat;

/**
 * Pelihallinta luokka hallinnoi pelaajien noppia ja yhdistelmiä sskä hallinnoi
 * pelin kulkua
 */
public class PelinHallinta {

    private static int kierroslkm;
    private static int maxkierroslkm;
    private static PeliMuunnelma peli;
    private static Pelaajat pelaajienHallinta = new Pelaajat();
    private static List<String> pelaajanimiLista;
    private static HashMap<String, ArrayList<Noppa>> pelaajienNopat;  //voi olla vain yksi peli käynnissä!
    private static HashMap<String, ArrayList<Yhdistelma>> pelaajienYhdistelmat;

    /**
     * Konstruktori luo PeliMuunnelma olion parametrin mukaisesti ja alustaa
     * kierroslukumittarin säännöt
     *
     * @param muunnelma
     */
    public PelinHallinta(String muunnelma) {

        kierroslkm = 1;

        if (muunnelma == null || !muunnelma.equalsIgnoreCase("pakkojatsi")) {
            throw new IllegalArgumentException("Antamasi pelimuunnelma ei ole käytettävissä"); //toistaiseksi poikkeus tässä
        }
        if (muunnelma.equalsIgnoreCase("pakkojatsi")) {
            peli = new Pakkojatsi();
            maxkierroslkm = peli.annaKierroksienMaara();

            pelaajanimiLista = pelaajienHallinta.annaPelaajat();
            luoPelaajienNopat();
            luoPelaajienYhdistelmat();

        }

    }

    /**
     * Apumetodi joka kytkee nopat pelaajiin
     *
     */
    private static void luoPelaajienNopat() {

        pelaajienNopat = new HashMap<>();

        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Noppa> nopat = peli.luoNopat();
            pelaajienNopat.put(pelaajanimi, nopat);

        }

    }

    /**
     * Apumetodi joka kytkee yhdistelmat pelaajiin
     *
     */
    private static void luoPelaajienYhdistelmat() {

        pelaajienYhdistelmat = new HashMap<>();

        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Yhdistelma> yhdistelmat = peli.luoYhdistelmat();
            pelaajienYhdistelmat.put(pelaajanimi, yhdistelmat);

        }

    }

    /**
     * Metodi heittää pyydetyn pelaajan pyydettyhkä noppia
     *
     * @param nimi
     * @param indeksit
     */
    public void heitaPelaajanNoppia(String nimi, int[] indeksit) {

        if (kierroslkm <= maxkierroslkm) {

            int i = 0;
            ArrayList<Integer> lukemat = new ArrayList<Integer>();
            ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

            for (Noppa noppa : nopat) {

                if (onkoIndeksiJoukossa(indeksit, i)) {
                    noppa.heita();
                }
                lukemat.add(noppa.annaLukema()); //muös aiemmat lukemat mukaan
                i++;

            }
            yllapidaYhidstelmanPisteet(nimi, lukemat);
        } else {
            throw new IllegalArgumentException("Peli on loppu");
        }
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
     * Metodi joka palauttaa pydetyn pelaajan noppien lukemat
     *
     * @param nimi
     * @return lukemat
     */
    public ArrayList<Integer> annaPelaajanNoppienLukemat(String nimi) {

        if (kierroslkm <= maxkierroslkm) {
            ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);
            ArrayList<Integer> lukemat = new ArrayList<>();
            for (Noppa noppa : nopat) {
                lukemat.add(noppa.annaLukema());
            }

            return lukemat;
        } else {
            throw new IllegalArgumentException("Peli on loppu");
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
        //System.out.print(pelattavaYhd + ": "); //sout pois
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
     * Apumetodi joka pydettäessä tarkistaa ovatko kaikki pelaajat heittäneet 3
     * kertaa noppaa ja voidaan aloittaaa uusi kierros
     *
     */
    private static void tarkistaAlkaakoUusiKierros() {

        int kolmaskierroKaikilla = 0;
        boolean kolmaskierrosPelaajalla;

        for (String pelaajanimi : pelaajanimiLista) {

            kolmaskierrosPelaajalla = false;
            ArrayList<Noppa> nopat = pelaajienNopat.get(pelaajanimi);

            for (Noppa noppa : nopat) {

                if (noppa.annaKierroslkm() == 1) { //toistaiseksi ei voi heittä kuin kerran 
                   
                    kolmaskierrosPelaajalla = true;
                }
            }
            if (kolmaskierrosPelaajalla == true) {
                kolmaskierroKaikilla++;
            }

        }

        if (kolmaskierroKaikilla == pelaajanimiLista.size() && kierroslkm <= maxkierroslkm + 1) {
            kierroslkm++;                                   //KIERRO VOI VAIHTUA MYÖS ENNEN KUIN HEITETTY KOLME KERTAA-> PELAAJA PAINAA LOPETA PAINIKETTA!
            nollaaPelaajienNoppienKierroslkm();
        }

    }

    /**
     * Apumetodi joka pyydettäessä nollaa kaikkien noppien kierroslukumittarin
     *
     */
    private static void nollaaPelaajienNoppienKierroslkm() {

        for (String pelaajanimi : pelaajanimiLista) {

            ArrayList<Noppa> nopat = pelaajienNopat.get(pelaajanimi);

            for (Noppa noppa : nopat) {

                noppa.nollaaKierroslkm();
            }

        }

    }

    /**
     * Metodi joka tutkii kenellä korkeimmat kokonaispisteet ja palauttaa
     * voittajan nimen
     *
     * @return nimi
     */
    public String julistavoittaja() {

        HashMap<String, Integer> pelaajienKokonaispisteet = new HashMap<>();

        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(pelaajanimi);
            int pist = peli.laskeKokonaisPisteet(pelaajanYhd);
            pelaajienKokonaispisteet.put(pelaajanimi, pist);
            pelaajienHallinta.tallennaPelaaja(pelaajanimi, pist);

        }

        if (pelaajanimiLista.size() > 1) {
            return etsiKorkeimmatPisteet(pelaajienKokonaispisteet);

        } else {
            return "Yksin pelatessa ei ole voittajaa";
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
            if (pisteet.get(avain) >= korkein) { //entä jos pelaajilla samat pisteet??? tasapeli!
                korkein = pisteet.get(avain);
                voittaja = voittaja+avain;
            }

        }
        return voittaja;
    }
}

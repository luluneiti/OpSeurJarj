package sovelluslogiikka.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sovelluslogiikka.pelaaja.PelaajienHallinta;

/**
 * Pelihallinta luokka hallinnoi pelaajien noppia ja yhdistelmiä sskä hallinnoi
 * pelin kulkua
 */
public class PelinHallinta {

    private static int kierroslkm;  //luokkamuuttujia, voi olla vain yksi peli käynnissä!
    private static int maxkierroslkm;
    private static PeliMuunnelma peli;
    private static PelaajienHallinta pelaajienHallinta;
    private static List<String> pelaajanimiLista;
    private static HashMap<String, ArrayList<Noppa>> pelaajienNopat;
    private static HashMap<String, ArrayList<Yhdistelma>> pelaajienYhdistelmat;
    private static HashMap<String, Integer> pelaajienKokonaispisteet = new HashMap<>();

    /**
     * Konstruktori luo PeliMuunnelma olion parametrin mukaisesti ja alustaa
     * kierroslukumittarin säännöt
     *
     * @param muunnelma
     */
    public PelinHallinta(String muunnelma, PelaajienHallinta pelaajienhallinta) {

        kierroslkm = 1;

        if (muunnelma == null || !muunnelma.equalsIgnoreCase("pakkojatsi")) {
            throw new IllegalArgumentException("Antamasi pelimuunnelma ei ole käytettävissä");
        }
        if (muunnelma.equalsIgnoreCase("pakkojatsi")) {

            pelaajienHallinta = pelaajienhallinta;
            pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();

            peli = new Pakkojatsi();
            maxkierroslkm = peli.annaKierroksienMaara();

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
     * Metodi heittää pyydetyn pelaajan pyydettyjä noppia
     *
     * @param nimi
     * @param indeksit
     */
    public void heitaPelaajanNoppia(String nimi, ArrayList<Integer> indeksit) {

        int i = 0;
        ArrayList<Integer> lukemat = new ArrayList<>();
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
     * Apumetodi tutkii onko ko. indeksi pyydettyjen indeksien joukossa. Noppien
     * heitto-metodi kutsuu tätä metodia selvittääkseen, että onko pelaajan ko.
     * noppaa pyydetty heittämään
     *
     * @param indeksit, indeksi
     * @return boolean arvo
     */
    private static boolean onkoIndeksiJoukossa(ArrayList<Integer> indeksit, int indeksi) {

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
     * Metodi joka palauttaa pyydetyn pelaajan noppien lukemat
     *
     * @param nimi
     * @return lukemat
     */
    public ArrayList<Integer> annaPelaajanNoppienLukemat(String nimi) {

        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);
        ArrayList<Integer> lukemat = new ArrayList<>();
        for (Noppa noppa : nopat) {
            lukemat.add(noppa.annaLukema());
        }

        return lukemat;

    }

    /**
     * Metodi joka antaa pyydetyn pelaajan pelattavan yhdistelman pisteet
     *
     * @param nimi
     * @return pisteet
     */
    public int annaPelaajanViimeisimmanYHdistelmanPisteet(String nimi) {

        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(kierroslkm);
        int pist = 0;
        ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(nimi);

        for (Yhdistelma yhdistel : pelaajanYhd) {

            if (yhdistel.annaNimi() == pelattavaYhd) {
                pist = yhdistel.annaPisteet();
            }
        }

        //tarkistaAlkaakoUusiKierros();
        return pist;
    }

    /**
     * Metodi joka pydettäessä tarkistaa ovatko kaikki pelaajat heittäneet 3
     * kertaa noppaa ja voidaan aloittaaa uusi kierros
     *
     * @return
     */
    public boolean tarkistaAlkaakoUusiKierros() {

        int kolmaskierrosKaikilla = 0;
        boolean kolmaskierrosPelaajalla;

        for (String pelaajanimi : pelaajanimiLista) {

            kolmaskierrosPelaajalla = false;
            ArrayList<Noppa> nopat = pelaajienNopat.get(pelaajanimi);

            for (Noppa noppa : nopat) {

                if (noppa.annaKierroslkm() == 3) {
                    kolmaskierrosPelaajalla = true;
                }
            }
            if (kolmaskierrosPelaajalla == true) {
                kolmaskierrosKaikilla++;
            }

        }
        if (kolmaskierrosKaikilla == pelaajanimiLista.size() && kierroslkm < maxkierroslkm + 1) {
            kierroslkm++;
            //System.out.println("uusi kierros ja noppien kierroslkm:n nollaus");
            nollaaPelaajienNoppienKierroslkm();
            return true;
        }

        return false;

    }

    /**
     * Metodi joka tarkistaaa onko ko. pelaaja heittänyt kaikki vuoronsa heitot
     *
     *
     * @param nimi
     * @return
     */
    public boolean onkoPelaajanVuoroPaattymassa(String nimi) {

        boolean kolmaskierros;

        kolmaskierros = false;
        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

        for (Noppa noppa : nopat) {

            if (noppa.annaKierroslkm() == 3) {

                kolmaskierros = true;
            }
        }

        tarkistaAlkaakoUusiKierros();
        //System.out.println("pelaajan vuoro päättyy: " + kolmaskierros);
        return kolmaskierros;
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

        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(pelaajanimi);
            int pist = peli.laskeKokonaisPisteet(pelaajanYhd);
            //System.out.println("pisteet " + pist);
            pelaajienKokonaispisteet.put(pelaajanimi, pist);
            pelaajienHallinta.tallennaPelaaja(pelaajanimi, pist);

        }

        if (pelaajanimiLista.size() > 1) {
            return etsiKorkeimmatPisteet(pelaajienKokonaispisteet);

        } else {
            return "Yksin ei voi voittaaa";
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

        int korkein = 1;
        for (String avain : pisteet.keySet()) {
            if (pisteet.get(avain) > korkein) {
                korkein = pisteet.get(avain);
                voittaja = avain;
            }
        }

        for (String avain : pisteet.keySet()) {
            if (pisteet.get(avain) == korkein && !avain.equals(voittaja)) { //tasapeli
                voittaja = voittaja + " " + avain;
            }
        }
        if (voittaja.isEmpty()) {
            voittaja = "Ei voittajaa";
        }

        return voittaja;
    }

    /**
     * Metodi joka palauttaa pelattavan yhdistelman
     *
     * @return
     */
    public String annaPelattavaYhdistelma() {
        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(kierroslkm);
        return "" + pelattavaYhd;
    }

    /**
     * Metodi jota kutsutaan, jos pelaaja päättää vuoronsa ennen kuin heittänyt
     * 3 kertaa
     *
     * @param nimi
     */
    public void lopetaKierros(String nimi) {

        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

        for (Noppa noppa : nopat) {
            noppa.asetaKierroslkm(3);
        }

    }

    /**
     * Metodi joka palauttaa kierroslkm:n
     *
     * @return
     */
    public int annaKierroslkm() {
        return kierroslkm;
    }

    /**
     * Metodi joka kertoo loppuuko peli ja pelattavat yhdistelmat
     *
     * @return
     */
    public boolean loppuukoPeli() {
        //System.out.println(peli.annaPelattavaYhdistelma(kierroslkm));
        if (peli.annaPelattavaYhdistelma(kierroslkm).equals(YhdistelmanNimi.eiTiedossa)) {

            return true;
        }
        return false;
    }

    /**
     * Metodi joka palauttaa pelaajan kokonaispisteet
     *
     * @param nimi
     * @return
     */
    public int annaPelaajanKokonaisPisteet(String nimi) {
        return pelaajienKokonaispisteet.get(nimi);
    }

    /**
     * Apumetodi joka kertoo montako kertaa pelaajan noppian on heitetty
     *
     * @param nimi
     * @return
     */
    private int annaNopanKierros(String nimi) {
        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);
        int kierros = 0;
        for (Noppa noppa : nopat) {
            if (noppa.annaKierroslkm() > kierros) {
                kierros = noppa.annaKierroslkm();
            }
        }
        return kierros;
    }
}

package sovelluslogiikka.peli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.peli.Noppa;
import sovelluslogiikka.peli.Pakkojatsi;
import sovelluslogiikka.peli.PeliMuunnelma;
import sovelluslogiikka.peli.Yhdistelma;
import sovelluslogiikka.peli.YhdistelmanNimi;

/**
 * Pelihallinta luokka hallinnoi kierroslukua, pelaajien noppia sekä yhdistelmiä
 * ja pelin kulkua (onko pelaajan vuoro päättynyt, onko peli loppunut)
 *
 * @author Ulla
 */
public class PelinHallinta {

    /**
     * Pelin meneillään oleva kierros.
     */
    private static int peliKierroslkm = 1;
    /**
     * Kierrosten maksimimäärä. Saa tiedon pelimuunnelmalta.
     */
    private static int maxkierroslkm;
    /**
     * Pelattava peli. Määrää mm. pisteiden laskusäännöt.
     */
    private static PeliMuunnelma peli;
    /**
     * Pelaajia hallinnoivat luokkaa, jolta peli saa pelin pelaajat.
     */
    private static PelaajienHallinta pelaajienHallinta;
    /**
     * Lista pelaajien nopista
     */
    private static HashMap<String, ArrayList<Noppa>> pelaajienNopat;
    /**
     * Lista pelaajien yhdistelmistä
     */
    private static HashMap<String, ArrayList<Yhdistelma>> pelaajienYhdistelmat;

    /**
     * Konstruktori luo PeliMuunnelma olion parametrin mukaisesti ja alustaa
     * kierroslukumittarin säännöt
     *
     */
    public PelinHallinta() {

    }

    /**
     * Alustaa pelin
     *
     * @param muunnelma pelattava muunnelma
     * @param pelaajienhallinta pelaajien tiedot
     */
    public void alustaPeli(String muunnelma, PelaajienHallinta pelaajienhallinta) {

        if (muunnelma == null || !muunnelma.equalsIgnoreCase("pakkojatsi")) {
            throw new IllegalArgumentException("Antamasi pelimuunnelma ei ole käytettävissä.");
        }
        if (pelaajienhallinta.annaValitutPelaajat().isEmpty()) {
            throw new IllegalArgumentException("Peliä ei voida aloittaa, koska pelaajia ei ole valittu.");
        }

        if (muunnelma.equalsIgnoreCase("pakkojatsi")) {

            pelaajienHallinta = pelaajienhallinta;
            List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();

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

        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();
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
        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();
        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Yhdistelma> yhdistelmat = peli.luoYhdistelmat();
            pelaajienYhdistelmat.put(pelaajanimi, yhdistelmat);

        }

    }

    /**
     * Metodi heittää pyydetyn pelaajan pyydettyjä noppia
     *
     * @param nimi pelaajan nimi
     * @param indeksit heietettavat nopat
     */
    public void heitaPelaajanNoppia(String nimi, ArrayList<Integer> indeksit) {

        ArrayList<Integer> lukemat = new ArrayList<>();
        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

        int i = 0;
        for (Noppa noppa : nopat) {

            if (onkoIndeksiJoukossa(indeksit, i)) {
                noppa.heita();
            }
            lukemat.add(noppa.annaLukema()); //muös aiemmat lukemat mukaan
            i++;

        }
        yllapidaYhdistelmanPisteet(nimi, lukemat);

    }

    /**
     * Apumetodi tutkii onko ko. indeksi pyydettyjen indeksien joukossa. Noppien
     * heitto-metodi kutsuu tätä metodia selvittääkseen, että onko pelaajan ko.
     * noppaa pyydetty heittämään
     *
     * @param indeksit heitettavat nopat
     * @param indeksi heittovuorossa oleva noppa
     * @return true jos on heitettavien joukossa
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
     * @param lukemat nopan lukemat
     * @param nimi pelaajan nimi
     */
    private static void yllapidaYhdistelmanPisteet(String nimi, ArrayList<Integer> lukemat) {

        int pist = peli.laskeYhdistelmanPisteet(lukemat, peliKierroslkm);
        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(peliKierroslkm);

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
     * @param nimi pelaajan nimi
     * @return nopan lukemat
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
     * @param nimi pelaajan nimi
     * @return pisteet
     */
    public int annaPelaajanViimeisimmanYhdistelmanPisteet(String nimi) {

        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(peliKierroslkm);
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
     * @return true jos alkaa uusi kierros
     */
    public boolean tarkistaAlkaakoUusiKierros() {

        int kolmaskierrosKaikilla = 0;
        boolean kolmaskierrosPelaajalla;

        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();
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
        if (kolmaskierrosKaikilla == pelaajanimiLista.size() && peliKierroslkm < maxkierroslkm + 1) {
            peliKierroslkm++;
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
     * @param nimi pelaajan nimi
     * @return true jos pelaajan kaikkien noppoen kierroluku 3
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

        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();
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
     * @return nimi voittajan tai voittajien nimet
     */
    public String julistavoittaja() {

        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();

        for (String pelaajanimi : pelaajanimiLista) {
            ArrayList<Yhdistelma> pelaajanYhd = pelaajienYhdistelmat.get(pelaajanimi);
            int pist = peli.laskeKokonaisPisteet(pelaajanYhd);
            pelaajienHallinta.tallennaPelaaja(pelaajanimi, pist);

        }

        if (pelaajanimiLista.size() > 1) {
            return etsiKorkeimmatPisteet();

        } else {
            return "Yksinpelissä ei voittajaa.";
        }

    }

    /**
     * Apumetodi joka etsii korkeimmat pisteet omaavan pelaajan
     *
     * @return pelaajan tai pelaajien nimet joilla korkeimmat pisteet
     */
    private static String etsiKorkeimmatPisteet() {

        String voittaja = "";
        int korkein = 1;
        List<String> pelaajanimiLista = pelaajienHallinta.annaValitutPelaajat();

        for (String pelaajanimi : pelaajanimiLista) {

            if (pelaajienHallinta.annaPelaajanPisteet(pelaajanimi) > korkein) {
                korkein = pelaajienHallinta.annaPelaajanPisteet(pelaajanimi);
                voittaja = pelaajanimi;
            }
        }

        for (String pelaajanimi : pelaajanimiLista) {

            if (pelaajienHallinta.annaPelaajanPisteet(pelaajanimi) == korkein && !pelaajanimi.equals(voittaja)) { //tasapeli
                voittaja = voittaja + " " + pelaajanimi;
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
     * @return pelattavan yhdistelmän nimi
     */
    public String annaPelattavaYhdistelma() {

        if (peli.annaPelattavaYhdistelma(peliKierroslkm) == null) {
            return "";
        }
        YhdistelmanNimi pelattavaYhd = peli.annaPelattavaYhdistelma(peliKierroslkm);
        return "" + pelattavaYhd;

    }

    /**
     * Metodi jota kutsutaan, jos pelaaja päättää vuoronsa ennen kuin heittänyt
     * 3 kertaa
     *
     * @param nimi pelaajan nimi
     */
    public void lopetaKierros(String nimi) {

        ArrayList<Noppa> nopat = pelaajienNopat.get(nimi);

        for (Noppa noppa : nopat) {
            noppa.asetaKierroslkm(3);
        }

    }

    /**
     * Metodi joka kertoo loppuuko peli ja pelattavat yhdistelmat
     *
     * @return true jos kaikki yhdistelmat pelattu
     */
    public boolean loppuukoPeli() {
        //System.out.println(peli.annaPelattavaYhdistelma(kierroslkm));
        if (peli.annaPelattavaYhdistelma(peliKierroslkm).equals(YhdistelmanNimi.eiTiedossa)) {

            return true;
        }
        return false;
    }

}

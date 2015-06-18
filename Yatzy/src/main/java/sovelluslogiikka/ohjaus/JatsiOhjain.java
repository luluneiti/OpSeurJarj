package sovelluslogiikka.ohjaus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import kayttoliittyma.GraafinenJatsi;
import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.peli.PelinHallinta;

/**
 * JatsiOhjain on ohjausluokka, joka tuntee käyttölittymän ja pelaajia sekä peli
 * hallinnoivat luokat. Se välittää käyttölittymästä tulleet pyynnöt pelaajien
 * ja pelin hallinnalle ja vastauksen takaisin käyttölittymälle.
 *
 * @author Ulla
 */
public class JatsiOhjain {

    /**
     * Käyttöliittymä, jota ohjain kutsuu kun on tarve päivittää käyttäliittymää
     */
    private GraafinenJatsi kayttoliityma;
    /**
     * Pelaajien hallinta olio jota ohjain kutsuu pelaajien lisäämisessä ja
     * valitsemisessa
     */
    private PelaajienHallinta pelaajienhallinta;
    /**
     * Pelin hallinta olio, jota ohjain kutsuu mm. pelin käynnistämiseksi, nopan
     * heittämiseksi.
     */
    private PelinHallinta pelinhallinta;

    public JatsiOhjain(GraafinenJatsi kayttis, PelaajienHallinta pelaajienhall, PelinHallinta pelinhall) {

        this.kayttoliityma = kayttis;
        this.pelaajienhallinta = pelaajienhall;
        this.pelinhallinta = pelinhall;

    }

    /**
     * Luodaan ilmentymä käyttöliittymästä sekä pelaajia että peliä
     * hallinnoivista luokista ja rekisteröidään ohjausolio käyttöliittymälle
     *
     * @param args
     */
    public static void main(String[] args) {

        GraafinenJatsi kayttis = new GraafinenJatsi();
        PelaajienHallinta pelaajienhall = new PelaajienHallinta();
        PelinHallinta pelinhall = new PelinHallinta();
        JatsiOhjain ohjain = new JatsiOhjain(kayttis, pelaajienhall, pelinhall);
        kayttis.rekisteroiOhjain(ohjain);
        kayttis.run();

    }

    /**
     * Metodi joka ohjaa pelaajan lisäys pyynnöt pelaajia hallnnoivalle luokalle
     * ja jos lisäsy onnistui, pyytää käyttöliittymää lisäämään uusi pelaaja
     * kaikki pelaajat-listalle
     *
     * @param nimi pelaajan nimi
     */
    public void pelaajanLisays(String nimi) {

        pelaajienhallinta.lisaaPelaaja(nimi);
        this.kayttoliityma.lisaaPelaajaListalle(nimi + ", " + pelaajienhallinta.annaPelaajanPisteet(nimi));

    }

    /**
     * Metodi joka ohjaa pelaajien valinta pyynnöt pelaajia hallnnoivalle
     * luokalle ja jos se onnistuu, pyytäää käyttöliittymää
     * passivoimaan/aktivoimaan tarvittavia painikeita
     *
     * @param min minimi indeksi valituista listan elementeistä
     * @param max maksimi indeksi valituista listan elementeistä
     */
    public void pelaajanValinta(int min, int max) {

        if (min >= 0 && min < pelaajienhallinta.annaKaikkiPelaajat().size() && max >= 0 && max < pelaajienhallinta.annaKaikkiPelaajat().size()) {
            for (int i = min; i <= max; i++) {

                if (this.kayttoliityma.onkoListanElementtiValittu(i)) {
                    this.pelaajienhallinta.valitsePelaajaksi(this.kayttoliityma.annaListanElementti(i).substring(0, this.kayttoliityma.annaListanElementti(i).indexOf(",")));
                }
            }
            this.kayttoliityma.aktivoiPassivoiValitsePelaaja(false);
            this.kayttoliityma.aktivoiPassivoiAloitaPeli(true);
        } else {
            throw new IllegalArgumentException("Annatut indeksi epävalidit.");
        }

    }

    /**
     * Metodi joka ohjaa pelin aloitus pyynnöt peliä hallinnoivalle luokalle
     */
    public void pelinAloitus() {

        pelaajienhallinta.loppu(); //kirjoitetaan pelaajien tiedot tiedostoon siltä varalta että peli loppuu kesken
        this.pelinhallinta.alustaPeli("pakkojatsi", pelaajienhallinta);

    }

    /**
     * Metodi joka ohjaa heitto pyynnöt peliä hallinnoivalle luokalle ja jos se
     * onnistuu, pyytää käyttöliittymää passivoimaan/aktivoiman tarvittavia
     * painikeita sekä pävittämään tietoja. Kutsuu uusKierros metodia uuden
     * kierroksen aloittamiseksi.
     *
     * @param komponentinNimi klikatun heita painikkeen nimi
     */
    public void heita(String komponentinNimi) { //pelaaja klikannut heita painiketta

        this.kayttoliityma.asetaPeliInfo("Heittäkää.");

        int rivinro = Integer.parseInt(komponentinNimi.substring(komponentinNimi.length() - 1, komponentinNimi.length())); //kenen painiketta klikattu

        ArrayList<Integer> valitutNopat = new ArrayList<>();
        int j = rivinro * this.annaNoppienMaara();
        for (int i = 0; i < this.annaNoppienMaara(); i++) {
            if (this.kayttoliityma.onkoHeittaakoValittu(j)) { //eli pyydetään heittämään noppaa
                valitutNopat.add(i);
            }
            j++;
        }

        List<String> pelaajat = pelaajienhallinta.annaValitutPelaajat();
        pelinhallinta.heitaPelaajanNoppia(pelaajat.get(rivinro), valitutNopat); // 

        ArrayList<Integer> lukemat = pelinhallinta.annaPelaajanNoppienLukemat(pelaajat.get(rivinro));
        int k = rivinro * this.annaNoppienMaara();
        for (int i = 0; i < 5; i++) {
            //this.kayttoliityma.asetaLukema(k, "" + lukemat.get(i)); //hakee lukemat ja pyytää käyttistä näyttämään ne
            this.kayttoliityma.asetaLukemaKuvana(k, lukemat.get(i));
            this.kayttoliityma.aktivoiPassivoiHeittaako(true, k);
            k++;
        }

        this.kayttoliityma.asetaPisteet(rivinro, "" + pelinhallinta.annaPelaajanViimeisimmanYhdistelmanPisteet(pelaajat.get(rivinro))); //yhdistelman pisteet
        this.kayttoliityma.aktivoiPassivoiLopeta(true, rivinro);

        if (pelinhallinta.onkoPelaajanVuoroPaattymassa(pelaajat.get(rivinro))) { //vuoron päättyy
            this.kayttoliityma.aktivoiPassivoiHeita(false, rivinro);
            this.kayttoliityma.aktivoiPassivoiLopeta(false, rivinro);
        }

        if (this.kayttoliityma.onkoKaikkienPelaajienPainikkeetPassivoituna()) { //kierros päättyy
            this.uusiKierros();
        }

    }

    /**
     * Metodi pyytää käyttöliittymää passivoimaan/aktivoiman tarvittavia
     * painikeita sekä pävittämään tietoja. Kutsuu uusKierros metodia uuden
     * kierroksen aloittamiseksi.
     *
     * @param komponentinNimi klikatun loepta painikkeen nimi
     */
    public void lopetaVuoro(String komponentinNimi) { //pelaaja klikannut lopeta painiketta

        int rivinro = Integer.parseInt(komponentinNimi.substring(komponentinNimi.length() - 1, komponentinNimi.length())); //kenen painiketta klikattu

        List<String> pelaajat = pelaajienhallinta.annaValitutPelaajat();
        pelinhallinta.lopetaKierros(pelaajat.get(rivinro)); //noppien kierrosluku 3:ksi

        this.kayttoliityma.aktivoiPassivoiHeita(false, rivinro);
        this.kayttoliityma.aktivoiPassivoiLopeta(false, rivinro);

        if (this.kayttoliityma.onkoKaikkienPelaajienPainikkeetPassivoituna()) {  //kierros päättyy
            uusiKierros();
        }

    }

    /**
     * Metodi tarkistaa alkaako uusi kierros vai onko peli päättymässä. Pyytää
     * käyttöliittymää passivoimaan/aktivoiman tarvittavia painikeita sekä
     * pävittämään tietoja sekä pyytää peliä hallinnoivaa luokkaa tarkistamaan
     * jos peli on päättymässä ja olisi aikaa julistaa voittaja
     */
    public void uusiKierros() {

        this.pelinhallinta.tarkistaAlkaakoUusiKierros(); //tarkistaa vaihtuuko kierrosnumero

        List<String> pelaajat = this.pelaajienhallinta.annaValitutPelaajat();

        if (!pelinhallinta.loppuukoPeli()) { //jos peli ei lopu

            for (int i = 0; i < pelaajat.size(); i++) { //yksi per pelaaja

                this.kayttoliityma.aktivoiPassivoiHeita(true, i);
                //this.kayttoliityma.asetaPisteet(i, "0");

            }

            for (int i = 0; i < pelaajat.size() * this.annaNoppienMaara(); i++) { //useita per pelaaja

                this.kayttoliityma.valitse(i);
                this.kayttoliityma.aktivoiPassivoiHeittaako(false, i);
                this.kayttoliityma.asetaLukemaKuvana(i, 0);

            }

            this.kayttoliityma.asetaPeliInfo("Uusi kierros.");
            this.kayttoliityma.asetaPeliOtsikko("Pelattava yhdistelma: " + pelinhallinta.annaPelattavaYhdistelma());

        } else { //jos peli loppuu

            this.kayttoliityma.asetaPeliInfo("Peli päättyi.");

            this.kayttoliityma.naytaIlmoitus("Pelin voittaja", pelinhallinta.julistavoittaja());

            for (int i = 0; i < pelaajat.size(); i++) {

                this.kayttoliityma.asetaPisteet(i, "" + pelaajienhallinta.annaPelaajanPisteet(pelaajat.get(i)));
            }

            pelaajienhallinta.loppu(); //kirjoitetaan pelaajien tiedot tiedostoon

            if (!pelaajienhallinta.annaEnnatyksenTehneet().isEmpty()) {
                this.kayttoliityma.naytaIlmoitus("Ennätyksen tehneet", pelaajienhallinta.annaEnnatyksenTehneet());

            } else {
                this.kayttoliityma.naytaIlmoitus("Ennätyksen tehneet", "Ei ennätyksen tehneitä.");

            }

        }

    }

    /**
     * Metodi kertoo valittujen pelaajien lukumäärän
     *
     * @return valittujen pelaajien lukumäärä
     */
    public int annaValittujenPelaajienLukumaara() {

        if (pelaajienhallinta.annaValitutPelaajat() != null) {
            return pelaajienhallinta.annaValitutPelaajat().size();
        }

        return 0;
    }

    /**
     * Metodi kertoo kaikkien pelaajien lukumäärän
     *
     * @return kaikkien pelaajien lukumäärä
     */
    public int annaKaikkienPelaajienLukumaara() {

        if (pelaajienhallinta.annaKaikkiPelaajat() != null) {
            return pelaajienhallinta.annaKaikkiPelaajat().size();
        }
        return 0;

    }

    /**
     * Metodi antaa ko. pelaajan nimen (kaikki pelaajat)
     *
     * @param indeksi pelaajan indeksi
     * @return pelaajan nimi ja pisteet
     */
    public String annaPelaaja(int indeksi) {

        if (indeksi >= 0 && indeksi < pelaajienhallinta.annaKaikkiPelaajat().size()) {

            List<String> pelaajat = pelaajienhallinta.annaKaikkiPelaajat();

            return pelaajat.get(indeksi) + ", " + pelaajienhallinta.annaPelaajanPisteet(pelaajat.get(indeksi));

        }
        return "ei löydy";

    }

    /**
     * Metodi antaa ko. pelaajan nimen (valitut pelaajat)
     *
     * @param indeksi pelaajan indeksi
     * @return pelaajan nimi
     */
    public String annaValitunPelaajanNimi(int indeksi) {

        if (indeksi >= 0 && indeksi < pelaajienhallinta.annaValitutPelaajat().size()) {
            List<String> pelaajat = pelaajienhallinta.annaValitutPelaajat();

            return pelaajat.get(indeksi);

        }
        return "ei löydy";
    }

    /**
     * Metodi antaa pelattavan yhdistelmän
     *
     * @return pelattava yhidstelmä
     */
    public String annaPelattavaYhdistelma() {

        if (pelinhallinta.annaPelattavaYhdistelma() != null) {
            return pelinhallinta.annaPelattavaYhdistelma();
        }
        return "ei löydy";

    }

    /**
     * Metodi antaa pyydetyn pelaajan pisteet
     *
     * @param nimi pelaajan nimi
     * @return pisteet
     */
    public int annaPelaajanPisteet(String nimi) {

        return this.pelaajienhallinta.annaPelaajanPisteet(nimi);

    }

    /**
     * Metodi antaa noppien lukumäärän
     *
     * @return noppien lukumäärä
     */
    public int annaNoppienMaara() { //oikeasti pitäisi hakea pelin hallinnalta
        return 5;
    }

    /**
     * Metodi palauttaa nopan silmälukua vastaavan kuvan
     *
     * @param lukema nopan lukema
     * @return nopan silmälukua vastaava kuva
     */
    public ImageIcon naytaLukemaKuvana(int lukema) {
        return new ImageIcon(ImageIcon.class.getResource("/" + lukema + ".png"));
    }

}

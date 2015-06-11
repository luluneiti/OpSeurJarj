package sovelluslogiikka.pelaaja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Pelaajat luokka pitää tallessa pelaajien tiedot ja tarjoaa tietoja muille
 * luokille
 *
 */
public class PelaajienHallinta {

    private static List<Pelaaja> kaikkiPelaajat = new ArrayList<>();
    private static List<Pelaaja> valitutPelaajat = new ArrayList<>();
    private static ArrayList<String> ennatyksenParantajat = new ArrayList<>();
    private static TiedostonHallinta tiedostohall = new TiedostonHallinta();
    private static String tiednimi = "pelaajat";

    public PelaajienHallinta() {

        kaikkiPelaajat = tiedostohall.lueTiedostosta(tiednimi);
       /* for (Pelaaja pel : kaikkiPelaajat) {
            System.out.println("luettu tiedostosta: " + pel.annaNimi() + ", " + pel.annaEnnatysPisteet());

        }*/

    }

    /**
     * Metodi jolla lisätään pelaaja
     *
     * @param nimi
     * @return boolean arvo
     */
    public void lisaaPelaaja(String nimi) {

        for (Pelaaja pelaaja : kaikkiPelaajat) {
            if (pelaaja.annaNimi().equals(nimi)) {
                throw new IllegalArgumentException("Pelaaja on jo olemassa. Et voi lisatä uudelleen. ");
            }
        }
        kaikkiPelaajat.add(new Pelaaja(nimi));

    }

    /**
     * Metodi joka palautta pelin pelaajat
     *
     * @return kaikki pelaajat
     */
    public List<String> annaValitutPelaajat() {

        if (valitutPelaajat.isEmpty()) {
            return null;
        }
        List<String> kopio = new ArrayList<>();
        for (Pelaaja pelaaja : valitutPelaajat) {
            kopio.add(pelaaja.annaNimi());
        }
        return kopio;

    }

    /**
     * Metodi joka palautta pelin pelaajat
     *
     * @return kaikki pelaajat
     */
    public List<String> annaKaikkiPelaajat() {

        if (kaikkiPelaajat.isEmpty()) {
            return null;
        }
        List<String> kopio = new ArrayList<>();
        for (Pelaaja pelaaja : kaikkiPelaajat) {
            kopio.add(pelaaja.annaNimi());
        }
        return kopio;

    }

    /**
     * Metodi joka palauttaa niiden pelaajien nimet, jotka ovat rikkoneet oman
     * ennatyksensa
     *
     * @return nimet
     */
    public String annaEnnatyksenTehneet() {

        String paluu = "";
        for (String nimi : ennatyksenParantajat) {
            paluu = paluu + nimi + " ";
        }

        return paluu;

    }

    /**
     * Metodi joka tallettaa pelaajalle pyydetyt tiedot ja jos uudet pisteet
     * ovat korkeammat kuin aiemmat, laittaa pelaajan ennätyksen tehneiden
     * listalle
     *
     * @param nimi
     * @param uudetpisteet
     */
    public void tallennaPelaaja(String nimi, int uudetpisteet) {

        for (Pelaaja pelaaja : kaikkiPelaajat) {

            if (pelaaja.annaNimi().equals(nimi) && uudetpisteet > pelaaja.annaEnnatysPisteet()) {

                if (pelaaja.annaEnnatysPisteet() != 0) {
                    ennatyksenParantajat.add(nimi);
                    //System.out.println("lisätään ennätyksen tekijöihin: " + pelaaja);
                }

                pelaaja.asetaEnnatysPisteet(uudetpisteet);
                //System.out.println("päivitetään pisteitä: " + pelaaja);

            }

        }
    }

    /**
     * Metodi pelaajan ennätyspisteiden hakemiseen
     *
     * @param nimi
     * @return
     */
    public int annaPelaajanPisteet(String nimi) {

        for (Pelaaja pelaaja : kaikkiPelaajat) {
            if (pelaaja.annaNimi().equals(nimi)) {
                return pelaaja.annaEnnatysPisteet();
            }
        }
        return -1;

    }

    /**
     * Metodi pelaajan ottamiseksi mukaan peliin
     *
     * @param nimi
     */
    public void valitsePelaajaksi(String nimi) {

        valitutPelaajat.add(new Pelaaja(nimi));

    }

    /**
     * Metodi lopettamiseen ja tietojen kirjoittamiseksi tiedostoon
     *
     */
    public void loppu() {

        /*for (Pelaaja pel : kaikkiPelaajat) {
            System.out.println("kirjoitetaan tiedostoon: " + pel.annaNimi() + ", " + pel.annaEnnatysPisteet());

        }*/

        tiedostohall.kirjoitaTiedostoon(tiednimi, kaikkiPelaajat);

    }

}

package sovelluslogiikka.pelaaja;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelaajat luokka pitää tallessa pelaajien tiedot ja tarjoaa tietoja muille
 * luokille
 *
 */
public class Pelaajat {

    private static List<Pelaaja> pelaajat = new ArrayList<>(); //voi olla vain yksi peli käynnissä!
    private static ArrayList<String> ennatyksenParantajat = new ArrayList<>();

    /**
     * Metodi jolla lisätään pelaaja
     *
     * @param nimi
     * @return boolean arvo
     */
    public void lisaaPelaaja(String nimi) {

        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.annaNimi().equals(nimi)) {
                throw new IllegalArgumentException("Pelaaja on jo olemassa"); //toistaiseksi poikkeus tässä
            }

        }
        pelaajat.add(new Pelaaja(nimi));

    }

    /**
     * Metodi joka palautta pelin pelaajat
     *
     * @return kaikki pelaajat
     */
    public List<String> annaPelaajat() {
        if (pelaajat.isEmpty()) {
            return null;
        }
        List<String> kopio = new ArrayList<>();
        for (Pelaaja pelaaja : pelaajat) {
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

            paluu = nimi + " ";

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
        for (Pelaaja pelaaja : pelaajat) {
            if (uudetpisteet > pelaaja.annaEnnatysPisteet()) {

                if (pelaaja.annaEnnatysPisteet() != 0) {
                    ennatyksenParantajat.add(nimi);
                }
                pelaaja.asetaEnnatysPisteet(uudetpisteet);

            }

        }
    }

    public int annaPelaajanPisteet(String nimi) {
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.annaNimi().equals(nimi)) {

                return pelaaja.annaEnnatysPisteet();
            }

        }
        return -1;
    }
}

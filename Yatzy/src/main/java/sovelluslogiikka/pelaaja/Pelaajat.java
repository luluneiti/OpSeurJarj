package sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelaajat luokka pitää tallessa pelaajien tiedot ja tarjoaa tietoja muille
 * luokille
 *
 */
public class Pelaajat {

    private static List<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

    /**
     * Metodi jolla lisätään pelaaja
     *
     * @param nimi
     * @return boolean arvo
     */
    public void lisaaPelaaja(String nimi) {

        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.annaNimi().equals(nimi)) {
                throw new IllegalArgumentException("Pelaaja on jo olemassa");
            }

        }
        pelaajat.add(new Pelaaja(nimi));

    }

    /**
     * Metodi joka palautta pelin pelaajat
     *
     * @return kaikki pelaajat
     */
    public List<Pelaaja> annaPelaajat() {
        if (pelaajat.isEmpty()) {
            return null;
        }
        return pelaajat;
    }

}

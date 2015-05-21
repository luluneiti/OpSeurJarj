package sovelluslogiikka;

import java.util.HashSet;

/**
 * Pelaajat luokka pitää tallessa pelaajien tiedot ja tarjoaa tietoja muille
 * luokille
 *
 */
public class Pelaajat {

    private static HashSet<Pelaaja> pelaajat = new HashSet<Pelaaja>();

    /**
     * Metodi jolla lisätään pelaaja
     *
     * @param nimi
     * @return boolean arvo
     */
    public void lisaaPelaaja(String nimi) {

        if (pelaajat.contains(nimi)) {  //EI TULE TRUE...
            throw new IllegalArgumentException("Pelaaja on jo olemassa");
        }

        pelaajat.add(new Pelaaja(nimi));

    }

    /**
     * Metodi joka palautta pelin pelaajat
     *
     * @return kaikki pelaajat
     */
    public HashSet<Pelaaja> annaPelaajat() {
        if (pelaajat.isEmpty()) {
            return null;
        }
        return pelaajat;
    }

}

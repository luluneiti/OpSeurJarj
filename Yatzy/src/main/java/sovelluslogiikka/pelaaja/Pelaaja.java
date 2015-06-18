package sovelluslogiikka.pelaaja;

import java.io.Serializable;

/**
 * Pelaaja luokka tarjoaa tiedot ja palvelut pelaajan tietojen tallentamiseen
 *
 * @author Ulla
 */
public class Pelaaja implements Serializable {

    /**
     * Pelaajan nimi
     */
    private String nimi;
    /**
     * Pelaajan ennätyspisteet
     */
    private int ennatyspisteet;

    public Pelaaja(String nimi) {
        if (nimi != null && !nimi.isEmpty()) {
            this.nimi = nimi;
        } else {
            throw new IllegalArgumentException("Pelaajan nimi ei voi olla tyhjä merkkijono");
        }
    }

    /**
     * Metodi pelaajan ennätyspisteiden muuttamiseen
     *
     * @param ennatyspisteet pelajan pisteet
     */
    public void asetaEnnatysPisteet(int ennatyspisteet) {
        if (ennatyspisteet >= 0) {
            this.ennatyspisteet = ennatyspisteet;
        }
        if (ennatyspisteet < 0) {
            throw new IllegalArgumentException("Pelaajan ennätyspisteet eivät olla negatiivisia");
        }
    }

    /**
     * Metodi pelaajan ennätyspisteiden muuttamiseen
     *
     * @return pelaajan pisteet
     */
    public int annaEnnatysPisteet() {
        return this.ennatyspisteet;
    }

    /**
     * Metodi pelaajan nimen hakemiseen
     *
     * @return pelaajan niimi
     */
    public String annaNimi() {
        return this.nimi;
    }

    public int compareTo(Pelaaja verrattava) {

        if (this.nimi.compareToIgnoreCase(verrattava.annaNimi()) > 0) {
            return 1;
        } else if (this.nimi.compareToIgnoreCase(verrattava.annaNimi()) < 0) {
            return -1;
        } else {
            return 0;
        }

    }

    public boolean equals(Pelaaja verrattava) {

        return this.nimi.equals(verrattava.annaNimi());

    }

    @Override
    public String toString() {
        return this.nimi;
    }

}

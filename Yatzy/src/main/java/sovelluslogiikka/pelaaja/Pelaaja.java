package sovelluslogiikka;

/**
 * Pelaaja luokka tarjoaa tiedot ja palvelut pelaajan tietojen tallentamiseen
 */
public class Pelaaja {

    private String nimi;
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
     * @param ennatyspisteet
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
     * @return ennätyspisteet
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

}

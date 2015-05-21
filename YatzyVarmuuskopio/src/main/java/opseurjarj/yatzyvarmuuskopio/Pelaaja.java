package sovelluslogiikka;

/**
 * Pelaaja..
 */
public class Pelaaja {

    private String nimi;
    private int ennatyspisteet;

    public Pelaaja(String nimi) {
        if (!nimi.isEmpty()) {
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

    @Override
    public int hashCode() {
        if (this.nimi == null) {
            return 1;
        }
        return this.nimi.hashCode();
    }

    @Override
    public boolean equals(Object toinen) {
        if (toinen == null) {
            return false;
        }
        return this.nimi.equals(((Pelaaja) toinen).nimi);
    }

}

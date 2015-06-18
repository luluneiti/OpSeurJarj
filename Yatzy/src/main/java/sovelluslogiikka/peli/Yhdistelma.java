package sovelluslogiikka.peli;

import sovelluslogiikka.peli.YhdistelmanNimi;

/**
 * Yhdistelma tarjoaa tiedot ja palvelut jatsissa pelattavien yhdistelmien tallentamiseen
 *
 * @author Ulla
 */
public class Yhdistelma {

     /**
     * Pelattavan yhdistelman nimi
     * Yhdistelman nimi on jokin YhdistelmanNimi enumeration arvoista
     */
    private YhdistelmanNimi nimi;
     /**
     * Yhdistelman pisteet
     */
    private int pisteet;

    public Yhdistelma(YhdistelmanNimi yhdistemanNimi) {
        this.nimi = yhdistemanNimi;

    }

    /**
     * Metodilla asetetaan yhdiselman pisteet
     *
     * @param pisteet yhdistelmän pisteet
     */
    public void asetaPisteet(int pisteet) {
        if (pisteet >= 0) {
            this.pisteet = pisteet;
        }
    }

    /**
     * Metodi palauttaa yhdistelman pisteet
     *
     * @return yhdistelman pisteet
     */
    public int annaPisteet() {
        return this.pisteet;
    }

    /**
     * Metodilla voidaan pyytää yhdistelman nimeä
     *
     * @return Yhdistelman nimi
     */
    public YhdistelmanNimi annaNimi() {
        return this.nimi;
    }

}

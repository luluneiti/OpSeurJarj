package sovelluslogiikka;

/**
 * Yhdistelma tarjoaa tiedot ja palvelut yhdistelmien tallentamiseen
 *
 */
public class Yhdistelma {

    private YhdistelmanNimi nimi;
    private int jarjestysNumero; //ei toistaiseksi käytössä
    private int pisteet;

    public Yhdistelma(YhdistelmanNimi yhdistemanNimi) {
        this.nimi = yhdistemanNimi;

    }

    /**
     * Metodilla asetetaan yhdiselman pisteet
     *
     * @param pisteet
     */
    public void asetaPisteet(int pisteet) {
        if (pisteet >= 0) {
            this.pisteet = pisteet;
        }
    }

    /**
     * Metodi palauttaa yhdistelman pisteet
     *
     * @return pelaajan nimi
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

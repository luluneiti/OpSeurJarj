package sovelluslogiikka;

/**
 * Noppa ...
 */
public class Noppa {

    private int lukema;
    private int noppaKierroslkm;

    public Noppa() {
        this.noppaKierroslkm = 0;
    }

    /**
     * Metodi palauttaa nopan sen hetkisen lukeman
     *
     * @return nopan lukema
     */
    public int annaLukema() {
        return this.lukema;
    }

    /**
     * Metodi arpoo nopalle uuden lukeman
     */
    public void heita() {
        this.lukema = arvoUusiLukema();
        this.noppaKierroslkm++;
    }

    /**
     * Apumetodi, joka arpoo luvun 1:den ja 6 :den väliltä ja palauttaa sen
     *
     * @return arvottu luku 1-6
     */
    private static int arvoUusiLukema() {
        int lukema = (int) (Math.random() * 6 + 1);
        return lukema;
    }

    /**
     * Metodi, joka kertoo kuinka monta kertaa noppaa on heitetty
     *
     * @return noppaKierroslkm
     */
    public int annaKierroslkm() {
        return this.noppaKierroslkm;
    }

    /**
     * Metodi, jonka avulla nopan kierroslaskuri voidaan nollata
     *
     */
    public void nollaaKierroslkm() {
        this.noppaKierroslkm = 0;
    }

}

package sovelluslogiikka.peli;

/**
 * Noppa on toistaiseksi kuusisivuinen arpakuutio, joka arpoo uuden lukeman
 * pyydettäessä. Jos nopan sivujen määrä annettaisiin konstruktorissa, niin
 * sivujen määrä voisi olla jokin muukin kuin kuusi.
 *
 * @author Ulla
 */
public class Noppa {

    /**
     * Nopan viimeisin lukema Arvoalue 1-6 toistaiseksi.
     */
    private int lukema;
    /**
     * Nopan kierrosluku eli kuinka monta kertaa noppaa on heitetty Muuttujan
     * avulla seurataan kuinka monta kertaa pelaaja on heittänyt noppaa Arvoalue
     * 0-3
     */
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
        int lukema = (int) (Math.random() * 6 + 1); //toistaiseksi oletettu, että noppa on kuusikulmainen!
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
     * Metodi, joka kertoo kuinka monta kertaa noppaa on heitetty
     *
     * @param kierroslkm nopan kierroslkm
     */
    public void asetaKierroslkm(int kierroslkm) {
        this.noppaKierroslkm = kierroslkm;
    }

    /**
     * Metodi, jonka avulla nopan kierroslaskuri voidaan nollata
     *
     */
    public void nollaaKierroslkm() {
        this.noppaKierroslkm = 0;
    }

   

}

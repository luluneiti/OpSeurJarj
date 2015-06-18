package sovelluslogiikka.peli;

import java.util.ArrayList;

/**
 * PeliMuunnelma rajapinta kertoo pelimuunnelien tarjoamat palvelut Kaikki eri
 * pelimuunnelmat (kuten pakkojatsi) toteuttaa tämän rajapinnan
 *
 * @author Ulla
 */
public interface PeliMuunnelma {

    /**
     * Metodi yhdistelman pisteiden laskemiseen
     *
     * @param lukemat nopan lukemat
     * @param kierroslkm kierrosluku
     * @return pisteet
     */
    public int laskeYhdistelmanPisteet(ArrayList<Integer> lukemat, int kierroslkm);

    /**
     * Metodi kokonaispisteiden laskemiseen
     *
     * @param yhdistelmat yhdistelmat
     * @return pisteet
     */
    public int laskeKokonaisPisteet(ArrayList<Yhdistelma> yhdistelmat);

    /**
     * Metodi noppien luontiin
     *
     * @return nopat
     */
    public ArrayList<Noppa> luoNopat();

    /**
     * Metodi yhdistelmien luontiin
     *
     * @return yhdistelmat
     */
    public ArrayList<Yhdistelma> luoYhdistelmat();

    /**
     * Metodi joka palauttaa kieroksien maksimimäärän
     *
     * @return kierroksien maksimimaara
     */
    public int annaKierroksienMaara();

    /**
     * Metodi palauttaa yhdistelman jota sillä hetkellä pelataan
     *
     * @param kierroslkm kierrosluku
     * @return yhdistelman nimi
     */
    public YhdistelmanNimi annaPelattavaYhdistelma(int kierroslkm);
}

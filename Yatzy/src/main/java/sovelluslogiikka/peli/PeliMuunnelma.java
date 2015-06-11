package sovelluslogiikka.peli;

import java.util.ArrayList;

/**
 * PeliMuunnelma rajapinta kertoo pelimuunnelien tarjoamat palvelut Kaikki eri
 * pelimuunnelmat (kuten pakkojatsi) toteuttaa tämän rajapinnan
 */
public interface PeliMuunnelma {

    public int laskeYhdistelmanPisteet(ArrayList<Integer> lukemat, int kierroslkm);

    public int laskeKokonaisPisteet(ArrayList<Yhdistelma> yhdistelmat);

    public ArrayList<Noppa> luoNopat();

    public ArrayList<Yhdistelma> luoYhdistelmat();

    public int annaKierroksienMaara();

    public YhdistelmanNimi annaPelattavaYhdistelma(int kierroslkm);
}

package sovelluslogiikka.peli;

import sovelluslogiikka.peli.Pakkojatsi;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakkojatsiTest {

    private static Pakkojatsi peli = new Pakkojatsi();

    public PakkojatsiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaYkkoset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(1);
        assertEquals(3, peli.laskeYhdistelmanPisteet(lukemat, 1));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKakkoset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(4);
        assertEquals(6, peli.laskeYhdistelmanPisteet(lukemat, 2));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKolmoset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(3);
        assertEquals(9, peli.laskeYhdistelmanPisteet(lukemat, 3));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaNeloset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(3);
        assertEquals(4, peli.laskeYhdistelmanPisteet(lukemat, 4));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaViitoset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(3);
        lukemat.add(5);
        lukemat.add(4);
        lukemat.add(3);
        assertEquals(10, peli.laskeYhdistelmanPisteet(lukemat, 5));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKuutoset() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(6);
        lukemat.add(5);
        lukemat.add(4);
        lukemat.add(6);
        assertEquals(12, peli.laskeYhdistelmanPisteet(lukemat, 6));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaParinLukematPariAlussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(1);
        lukemat.add(1);
        assertEquals(4, peli.laskeYhdistelmanPisteet(lukemat, 7));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaParinLukematPariKeskellä() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(5);
        lukemat.add(4);
        assertEquals(6, peli.laskeYhdistelmanPisteet(lukemat, 7));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaParinLukematPariLopussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(8, peli.laskeYhdistelmanPisteet(lukemat, 7));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaParinLukematMontaParia() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(8, peli.laskeYhdistelmanPisteet(lukemat, 7)); //pitäisi ottaa paras pari

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaParinLukematMontaParia2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(5);
        lukemat.add(2);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(10, peli.laskeYhdistelmanPisteet(lukemat, 7)); //pitäisi ottaa paras pari

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKahdenParinLukemat() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(1);
        lukemat.add(1);     //PITÄÄKÖ OLLA VIEREKKÄIN???
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(10, peli.laskeYhdistelmanPisteet(lukemat, 8));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKahdenParinLukemat2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(5);
        lukemat.add(1);     //PITÄÄKÖ OLLA VIEREKKÄIN???
        lukemat.add(1);
        lukemat.add(4);
        assertEquals(12, peli.laskeYhdistelmanPisteet(lukemat, 8));

    }
    
    /**
     * xx metodin testi
     */
    @Test
    public void summaaKahdenParinLukemat3() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(5);
        lukemat.add(1);     //PITÄÄKÖ OLLA VIEREKKÄIN???
        lukemat.add(2);
        lukemat.add(4);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 8));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKolmosienLukematAlussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(3, peli.laskeYhdistelmanPisteet(lukemat, 9));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKolmosienLukematKeskellä() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(4);
        assertEquals(9, peli.laskeYhdistelmanPisteet(lukemat, 9));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaKolmosienLukematLopussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(4);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(12, peli.laskeYhdistelmanPisteet(lukemat, 9));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaNelosienLukematAlussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(4);
        assertEquals(4, peli.laskeYhdistelmanPisteet(lukemat, 10));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void summaaNelosienLukematLopussa() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(4);
        lukemat.add(4);
        lukemat.add(4);
        lukemat.add(4);
        assertEquals(16, peli.laskeYhdistelmanPisteet(lukemat, 10));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaPienisuora() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(5);
        assertEquals(15, peli.laskeYhdistelmanPisteet(lukemat, 11));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaPienisuora2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(5);
        lukemat.add(4);
        lukemat.add(3);
        lukemat.add(2);
        lukemat.add(1);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 11));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaPienisuora3() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(4);
        lukemat.add(5);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 11));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaSuurisuora() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(5);
        lukemat.add(6);
        assertEquals(20, peli.laskeYhdistelmanPisteet(lukemat, 12));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaSuurisuora2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(5);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 12));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaSuurisuora3() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(6);
        lukemat.add(5);
        lukemat.add(4);
        lukemat.add(3);
        lukemat.add(2);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 12));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaTaysikasi() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        assertEquals(11, peli.laskeYhdistelmanPisteet(lukemat, 13));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaTaysikasi2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 13));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaTaysikasi3() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(3);
        lukemat.add(1);
        lukemat.add(1);
        assertEquals(11, peli.laskeYhdistelmanPisteet(lukemat, 13));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaSattuma() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(5);
        assertEquals(15, peli.laskeYhdistelmanPisteet(lukemat, 14));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaJatsi() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(2);
        lukemat.add(2);
        assertEquals(50, peli.laskeYhdistelmanPisteet(lukemat, 15));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaJatsi2() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(2);
        lukemat.add(3);
        lukemat.add(4);
        lukemat.add(5);
        assertEquals(0, peli.laskeYhdistelmanPisteet(lukemat, 15));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void tarkistaJatsi3() {

        ArrayList<Integer> lukemat = new ArrayList<>();
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        lukemat.add(1);
        assertEquals(50, peli.laskeYhdistelmanPisteet(lukemat, 15));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void laskeKokonaisPisteet() {

        ArrayList<Yhdistelma> yhdistelmat = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.values()[i]));
        }

        for (Yhdistelma yhdistelma : yhdistelmat) {
            yhdistelma.asetaPisteet(1);

        }

        assertEquals(15, peli.laskeKokonaisPisteet(yhdistelmat));

    }
    
     /**
     * xx metodin testi
     */
    @Test
    public void laskeKokonaisPisteet2() {

        ArrayList<Yhdistelma> yhdistelmat = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.values()[i]));
        }

        for (Yhdistelma yhdistelma : yhdistelmat) {
            yhdistelma.asetaPisteet(0);

        }

        assertEquals(0, peli.laskeKokonaisPisteet(yhdistelmat));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void laskeKokonaisPisteetEkaOsaYli63() {

        ArrayList<Yhdistelma> yhdistelmat = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.values()[i]));
        }

        for (Yhdistelma yhdistelma : yhdistelmat) {
            yhdistelma.asetaPisteet(15);

        }

        assertEquals(275, peli.laskeKokonaisPisteet(yhdistelmat));

    }

    /**
     * xx metodin testi
     */
    @Test
    public void annaMaxKierroksienMaara() {

        assertEquals(15, peli.annaKierroksienMaara());

    }

    /**
     * xx metodin testi
     */
    @Test
    public void luoNopat() {

        ArrayList<Noppa> nopat = peli.luoNopat();
        assertEquals(5, nopat.size());

    }

    /**
     * xx metodin testi
     */
    @Test
    public void luoYhdsetlmat() {

        ArrayList<Yhdistelma> yhdistelmat = peli.luoYhdistelmat();
        assertEquals(peli.annaKierroksienMaara(), yhdistelmat.size());

    }

    /**
     * xx metodin testi
     */
    @Test
    public void pelattavaYhdistelma() {

        assertEquals(YhdistelmanNimi.ykkoset, peli.annaPelattavaYhdistelma(1));
        assertEquals(YhdistelmanNimi.kakkoset, peli.annaPelattavaYhdistelma(2));
        assertEquals(YhdistelmanNimi.kolmoset, peli.annaPelattavaYhdistelma(3));
        assertEquals(YhdistelmanNimi.neloset, peli.annaPelattavaYhdistelma(4));
        assertEquals(YhdistelmanNimi.viitoset, peli.annaPelattavaYhdistelma(5));
        assertEquals(YhdistelmanNimi.kuutoset, peli.annaPelattavaYhdistelma(6));
        assertEquals(YhdistelmanNimi.yksi_pari, peli.annaPelattavaYhdistelma(7));
        assertEquals(YhdistelmanNimi.kaksi_paria, peli.annaPelattavaYhdistelma(8));
        assertEquals(YhdistelmanNimi.kolmoisluku, peli.annaPelattavaYhdistelma(9));
        assertEquals(YhdistelmanNimi.neloisluku, peli.annaPelattavaYhdistelma(10));
        assertEquals(YhdistelmanNimi.pieni_suora, peli.annaPelattavaYhdistelma(11));
        assertEquals(YhdistelmanNimi.suuri_suora, peli.annaPelattavaYhdistelma(12));
        assertEquals(YhdistelmanNimi.tayskasi, peli.annaPelattavaYhdistelma(13));
        assertEquals(YhdistelmanNimi.sattuma, peli.annaPelattavaYhdistelma(14));
        assertEquals(YhdistelmanNimi.yatzy, peli.annaPelattavaYhdistelma(15));

    }

}

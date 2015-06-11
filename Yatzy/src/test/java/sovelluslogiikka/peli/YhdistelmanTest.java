package sovelluslogiikka.peli;

import sovelluslogiikka.peli.YhdistelmanNimi;
import sovelluslogiikka.peli.Yhdistelma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhdistelmanTest {

    public YhdistelmanTest() {
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
     * asetaPisteet metorin tsti.
     */
    @Test
    public void ssetaPisteet() {
        int pisteet = 1;
        Yhdistelma yhdistelma = new Yhdistelma(YhdistelmanNimi.kakkoset);
        yhdistelma.asetaPisteet(pisteet);
        assertEquals(yhdistelma.annaPisteet(), 1);

    }

    /**
     * asetaPisteet metorin tsti.
     */
    @Test
    public void asetaPisteetNegatiivinen() {
        int pisteet = -1;
        Yhdistelma yhdistelma = new Yhdistelma(YhdistelmanNimi.kakkoset);
        yhdistelma.asetaPisteet(pisteet);
        assertEquals(yhdistelma.annaPisteet(), 0);

    }

    /**
     * asetaPisteet metorin tsti.
     */
    @Test
    public void asetaPisteetNolla() {
        int pisteet = 0;
        Yhdistelma yhdistelma = new Yhdistelma(YhdistelmanNimi.kakkoset);
        yhdistelma.asetaPisteet(pisteet);
        assertEquals(yhdistelma.annaPisteet(), 0);

    }

    /**
     * annaPisteet metodi testi
     */
    @Test
    public void annaPisteet() {

        int pisteet = 25;
        Yhdistelma yhdistelma = new Yhdistelma(YhdistelmanNimi.kakkoset);
        yhdistelma.asetaPisteet(pisteet);
        assertEquals(yhdistelma.annaPisteet(), 25);
    }

    /**
     * annaNimi metodin testi.
     */
    @Test
    public void annaNimi() {
   
        Yhdistelma yhdistelma = new Yhdistelma(YhdistelmanNimi.kakkoset);
        assertEquals(yhdistelma.annaNimi(), YhdistelmanNimi.kakkoset);
    }

}

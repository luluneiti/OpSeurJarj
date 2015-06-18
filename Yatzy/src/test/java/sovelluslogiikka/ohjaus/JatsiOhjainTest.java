package sovelluslogiikka.ohjaus;

import kayttoliittyma.GraafinenJatsi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.peli.PelinHallinta;

public class JatsiOhjainTest {

    private static GraafinenJatsi kayttis = new GraafinenJatsi();
    private static PelaajienHallinta pelaahall = new PelaajienHallinta();
    private static PelinHallinta pelhall = new PelinHallinta();
    private static JatsiOhjain ohjain = new JatsiOhjain(kayttis, pelaahall, pelhall);

    public JatsiOhjainTest() {
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

    @Test
    public void testPelaajanLisays() {

        String nimi = "";
        try {
            ohjain.pelaajanLisays(nimi);

        } catch (IllegalArgumentException e) {
            //ok
        }

    }

    @Test
    public void testPelaajanValinta() {

        try {
            ohjain.pelaajanValinta(-1, 2);
        } catch (Exception e) {
            //ok
        }

    }

    @Test
    public void testPelinAloitus() {

        try {
            ohjain.pelinAloitus();
        } catch (Exception e) {
            //ok
        }
    }

    @Test
    public void testHeita() {

        String komponentinNimi = "";
        try {
            ohjain.heita(komponentinNimi);
        } catch (Exception e) {
            //ok
        }
    }

    @Test
    public void testLopetaVuoro() {
        String komponentinNimi = "";
        try {
            ohjain.lopetaVuoro(komponentinNimi);
        } catch (Exception e) {
            //ok
        }
    }

    @Test
    public void testAnnaValittujenPelaajienLukumaara() {

        assertEquals(0, ohjain.annaValittujenPelaajienLukumaara());

    }

    @Test
    public void testAnnaKaikkienPelaajienLukumaara() {
        assertTrue(ohjain.annaKaikkienPelaajienLukumaara() > 0);
    }

    @Test
    public void testAnnaPelaaja() {

        try {
            ohjain.annaPelaaja(-2);
        } catch (Exception e) {
            //ok
        }

    }

    @Test
    public void testAnnaValitunPelaajanNimi() {
        assertEquals("ei löydy", ohjain.annaValitunPelaajanNimi(-2));
    }

    @Test
    public void testAnnaPelaajanPisteet() {

        assertEquals(-1, ohjain.annaPelaajanPisteet(""));

    }

}

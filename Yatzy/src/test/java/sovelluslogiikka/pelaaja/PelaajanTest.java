package sovelluslogiikka.pelaaja;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajanTest {

    public PelaajanTest() {
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
    public void pelaajanLuonti() {
        Pelaaja pelaaja = new Pelaaja("pelaaja0");
        assertEquals(pelaaja.annaNimi(), "pelaaja0");
    }

   
    @Test
    public void pelaajanLuontiTyhjallaMerkkijonolla() {

        try {
            Pelaaja pelaaja = new Pelaaja("");
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }

   
    @Test
    public void asetaEnnatysPisteetPositiivisellaParametrilla() {
        Pelaaja pelaaja = new Pelaaja("pelaaja1");
        pelaaja.asetaEnnatysPisteet(25);
        assertEquals(pelaaja.annaEnnatysPisteet(), 25);
    }

   
    @Test
    public void asetaEnnatysPisteetNegatiivisellaParametrilla() {
        Pelaaja pelaaja = new Pelaaja("pelaaja2");

        try {
            pelaaja.asetaEnnatysPisteet(-25);
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }

   
    @Test
    public void annaEnnatysPisteet() {
        Pelaaja pelaaja = new Pelaaja("pelaaja2");
        pelaaja.asetaEnnatysPisteet(25);
        assertEquals(pelaaja.annaEnnatysPisteet(), 25);
    }

   
    @Test
    public void annaNimi() {
        Pelaaja pelaaja = new Pelaaja("pelaaja3");
        assertEquals(pelaaja.annaNimi(), "pelaaja3");
    }

}

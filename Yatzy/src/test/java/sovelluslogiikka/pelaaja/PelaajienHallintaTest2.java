package sovelluslogiikka.pelaaja;

import sovelluslogiikka.pelaaja.Pelaajat;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajatTesti2 {

    private static Pelaajat pelaajat2 = new Pelaajat();

    public PelaajatTesti2() {

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
     * lisaaPelaaja metodin testi
     */
    @Test
    public void lisaaPelaaja() {

        pelaajat2.lisaaPelaaja("Kaisa");
         List<String> pelaajaLista = pelaajat2.annaPelaajat();
        assertTrue(pelaajaLista.contains("Kaisa"));

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajat() {

        pelaajat2.lisaaPelaaja("Ella");
        pelaajat2.lisaaPelaaja("Aino");
        List<String> pelaajaLista = pelaajat2.annaPelaajat();
        assertTrue(pelaajaLista.contains("Ella") && pelaajaLista.contains("Aino"));
    }

    /**
     * lisaaPelaaja metodin testi
     */
    @Test
    public void lisaaTuplaPelaaja() {

        try {
            pelaajat2.lisaaPelaaja("Aino");
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }
    
       /**
     * annaPelaajat metodin testi
     */
    @Test
    public void tallennaPelaaja() {

        pelaajat2.lisaaPelaaja("Emma");
        pelaajat2.tallennaPelaaja("Emma", 56);
        assertEquals(56, pelaajat2.annaPelaajanPisteet("Emma"));

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaEnnatyksenTehneet() {

        pelaajat2.lisaaPelaaja("Essi");
        pelaajat2.tallennaPelaaja("Essi", 89);
        assertEquals("", pelaajat2.annaEnnatyksenTehneet());
        pelaajat2.tallennaPelaaja("Essi", 250);
        assertEquals("Essi ", pelaajat2.annaEnnatyksenTehneet());

    }

}

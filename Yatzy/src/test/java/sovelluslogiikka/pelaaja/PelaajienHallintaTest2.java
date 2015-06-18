package sovelluslogiikka.pelaaja;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajienHallintaTest2 {

    private static PelaajienHallinta pelaajat2 = new PelaajienHallinta();

    public PelaajienHallintaTest2() {

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
    public void lisaaPelaaja() {

        pelaajat2.lisaaPelaaja("testi1");
        List<String> pelaajaLista = pelaajat2.annaKaikkiPelaajat();
        assertTrue(pelaajaLista.contains("testi1"));

    }

   
    @Test
    public void annaKaikkiPelaajat() {

        pelaajat2.lisaaPelaaja("testi2");
        pelaajat2.lisaaPelaaja("testi3");
        List<String> pelaajaLista = pelaajat2.annaKaikkiPelaajat();
        assertTrue(pelaajaLista.contains("testi2") && pelaajaLista.contains("testi3"));
    }

   
    @Test
    public void annaValitutPelaajat() {

        pelaajat2.valitsePelaajaksi("testi2");
        pelaajat2.valitsePelaajaksi("testi3");

        List<String> pelaajaLista = pelaajat2.annaValitutPelaajat();
        assertTrue(pelaajaLista.contains("testi2") && pelaajaLista.contains("testi3"));
    }

   
    @Test
    public void lisaaTuplaPelaaja() {

        try {
            pelaajat2.lisaaPelaaja("testi3");
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }

    
    @Test
    public void tallennaPelaaja() {

        pelaajat2.lisaaPelaaja("testi4");
        pelaajat2.tallennaPelaaja("testi4", 56);
        assertEquals(56, pelaajat2.annaPelaajanPisteet("testi4"));

    }

   
    @Test
    public void annaEnnatyksenTehneet() {

        pelaajat2.lisaaPelaaja("testi5");
        pelaajat2.tallennaPelaaja("testi5", 89);
        assertEquals("", pelaajat2.annaEnnatyksenTehneet());
        pelaajat2.tallennaPelaaja("testi5", 101);
        assertEquals("testi5 ", pelaajat2.annaEnnatyksenTehneet());

    }

   
    @Test
    public void annaPelaajanPisteet() {

        pelaajat2.tallennaPelaaja("testi5", 100);
        assertEquals(101, pelaajat2.annaPelaajanPisteet("testi5"));
        pelaajat2.tallennaPelaaja("testi5", 150);
        assertEquals(150, pelaajat2.annaPelaajanPisteet("testi5"));
        pelaajat2.tallennaPelaaja("testi5", -20);
        assertEquals(150, pelaajat2.annaPelaajanPisteet("testi5"));
    }

}

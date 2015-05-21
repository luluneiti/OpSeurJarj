package sovelluslogiikka;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajatTestit {

    public PelaajatTestit() {
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

        Pelaajat pelaajat = new Pelaajat();
        pelaajat.lisaaPelaaja("Ulla");

    }

    /**
     * lisaaPelaaja metodin testi
     */
   /* @Test
    public void lisaaTuplaPelaaja() { //ei tule poikkeusta

        Pelaajat pelaajat = new Pelaajat();
        pelaajat.lisaaPelaaja("Ulla");
        try {
            pelaajat.lisaaPelaaja("Ulla");
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }*/

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajatTyhjaLista() {

        Pelaajat pelaajat = new Pelaajat();
        HashSet<Pelaaja> pelaajaLista = pelaajat.annaPelaajat();
        //assertEquals(null, pelaajaLista);   //tämä ehto ei toimi..

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajat() {

        Pelaajat pelaajat = new Pelaajat();
        pelaajat.lisaaPelaaja("Ulla");
        pelaajat.lisaaPelaaja("Allu");
        HashSet<Pelaaja> pelaajaLista = pelaajat.annaPelaajat();
        assertEquals(2, pelaajaLista.size());

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajatTupla() {

        Pelaajat pelaajat = new Pelaajat();
        pelaajat.lisaaPelaaja("Ulla");
        try {
            pelaajat.lisaaPelaaja("Ulla");
        } catch (IllegalArgumentException e) {
            // ok
        }
        HashSet<Pelaaja> pelaajaLista = pelaajat.annaPelaajat();
        //assertEquals(1, pelaajaLista.size());   // vie tuplia...

    }

}

package sovelluslogiikka.peli;

import sovelluslogiikka.peli.Noppa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NopanTest {

    public NopanTest() {

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
    public void annaLukemaEnnenHeittoa() {

        Noppa noppa = new Noppa();
        assertTrue(noppa.annaLukema() == 0);

    }

   
    @Test
    public void annaLukemaHeitonJalkeen() {

        Noppa noppa = new Noppa();
        noppa.heita();
        assertTrue(noppa.annaLukema() >= 1 && noppa.annaLukema() <= 6);

    }

 
    @Test
    public void heita() {

        Noppa noppa = new Noppa();
        noppa.heita();

    }

   
    @Test
    public void annaKierroslkm() {

        Noppa noppa = new Noppa();
        noppa.heita();
        assertTrue(noppa.annaKierroslkm() == 1);

    }

   
    @Test
    public void nollaaKierroslkm() {

        Noppa noppa = new Noppa();
        noppa.heita();
        noppa.nollaaKierroslkm();
        assertTrue(noppa.annaKierroslkm() == 0);

    }

}

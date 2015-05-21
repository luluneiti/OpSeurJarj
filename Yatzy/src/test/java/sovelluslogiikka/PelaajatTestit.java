package sovelluslogiikka;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



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
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajatTyhjaLista() {

        Pelaajat pelaajat = new Pelaajat();
        List<Pelaaja> pelaajaLista = pelaajat.annaPelaajat();
        if (pelaajaLista != null) {
            fail();
        }

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajat() {

        Pelaajat pelaajat2 = new Pelaajat();
        pelaajat2.lisaaPelaaja("Ulla");
        pelaajat2.lisaaPelaaja("Allu");
        List<Pelaaja> pelaajaLista = pelaajat2.annaPelaajat();
        assertEquals(2, pelaajaLista.size());

    }
}

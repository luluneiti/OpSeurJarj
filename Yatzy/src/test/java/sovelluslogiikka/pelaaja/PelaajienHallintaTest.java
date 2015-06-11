package sovelluslogiikka.pelaaja;

import sovelluslogiikka.pelaaja.Pelaajat;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PelaajatTesti {

    private static Pelaajat pelaajat1 = new Pelaajat();

    public PelaajatTesti() {
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

        List<String> pelaajaLista = pelaajat1.annaPelaajat();
        if (pelaajaLista != null) {
            fail();
        }

    }

    /**
     * annaPelaajat metodin testi
     */
    @Test
    public void annaPelaajat() {

        pelaajat1.lisaaPelaaja("Anssi");
        pelaajat1.lisaaPelaaja("Erkki");
        List<String> pelaajaLista = pelaajat1.annaPelaajat();
        assertTrue(pelaajaLista.contains("Anssi") && pelaajaLista.contains("Erkki"));

    }
}

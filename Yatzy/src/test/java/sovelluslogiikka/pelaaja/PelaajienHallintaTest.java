package sovelluslogiikka.pelaaja;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PelaajienHallintaTest {

    private static PelaajienHallinta pelaajat1 = new PelaajienHallinta();

    public PelaajienHallintaTest() {
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
    public void annaPelaajatTyhjaLista() {

        List<String> pelaajaLista = pelaajat1.annaValitutPelaajat();
        if (pelaajaLista != null) {
            fail();
        }

    }

   

   

   
}

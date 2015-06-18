package sovelluslogiikka.pelaaja;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sovelluslogiikka.pelaaja.Pelaaja;
import sovelluslogiikka.pelaaja.TiedostonHallinta;
import static org.junit.Assert.*;

public class TiedostonHallintaTest {

    private static String tiedostonNimi = "pelaajat";

    public TiedostonHallintaTest() {

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

    /*@Test
    public void luePelaajatTiedostostaTestiTyhjaTiedosto() {

        TiedostonHallinta tiedhall = new TiedostonHallinta();
        File tiedosto = new File(tiedostonNimi);
        tiedosto.delete();
        List<Pelaaja> pelaajat = tiedhall.lueTiedostosta(tiedostonNimi);
        assertEquals(0, pelaajat.size());

    }*/

    @Test
    public void kirjoitaPelaajatTiedostoonTesti() {

        File tiedosto = new File(tiedostonNimi);
        tiedosto.delete();
        List<Pelaaja> pelaajat = new ArrayList<>();
        Pelaaja pel1 = new Pelaaja("Simo");
        pelaajat.add(pel1);
        Pelaaja pel2 = new Pelaaja("Allu");
        pelaajat.add(pel2);
        TiedostonHallinta tiedhall = new TiedostonHallinta();
        assertEquals(true, tiedhall.kirjoitaTiedostoon(tiedostonNimi, pelaajat));

    }

    @Test
    public void luePelaajatTiedostostaTesti() {

        TiedostonHallinta tiedhall = new TiedostonHallinta();

        File tiedosto = new File(tiedostonNimi);
        tiedosto.delete();
        List<Pelaaja> kirjoitettu = new ArrayList<Pelaaja>();
        Pelaaja pel1 = new Pelaaja("Simo");
        kirjoitettu.add(pel1);
        Pelaaja pel2 = new Pelaaja("Allu");
        kirjoitettu.add(pel2);
        tiedhall.kirjoitaTiedostoon(tiedostonNimi, kirjoitettu);
        List<Pelaaja> luettu = tiedhall.lueTiedostosta(tiedostonNimi);
        assertEquals(2, luettu.size());
        for (Pelaaja pel : luettu) {
            System.out.println(pel);
        }
        assertEquals(true, luettu.get(0).annaNimi().equals(pel1.annaNimi()) && luettu.get(1).annaNimi().equals(pel2.annaNimi()));

    }

    @Test
    public void luePelaajatTiedostostaTesti2() {

        TiedostonHallinta tiedhall = new TiedostonHallinta();

        File tiedosto = new File(tiedostonNimi);
        tiedosto.delete();
        List<Pelaaja> kirjoitettu = new ArrayList<Pelaaja>();
        Pelaaja pel1 = new Pelaaja("Tupu");
        kirjoitettu.add(pel1);
        Pelaaja pel2 = new Pelaaja("Hupu");
        kirjoitettu.add(pel2);
        Pelaaja pel3 = new Pelaaja("Lupu");
        kirjoitettu.add(pel3);
        Pelaaja pel4 = new Pelaaja("Supu");
        kirjoitettu.add(pel4);
        tiedhall.kirjoitaTiedostoon(tiedostonNimi, kirjoitettu);
        List<Pelaaja> luettu = tiedhall.lueTiedostosta(tiedostonNimi);
        assertEquals(4, luettu.size());
        Pelaaja pel5 = new Pelaaja("Nupu");
        kirjoitettu.add(pel5);
        tiedhall.kirjoitaTiedostoon(tiedostonNimi, kirjoitettu);
        List<Pelaaja> luettu2 = tiedhall.lueTiedostosta(tiedostonNimi);
        assertEquals(5, luettu2.size());

        for (Pelaaja pel : luettu2) {
            System.out.println(pel);
        }
        assertEquals(true, luettu2.get(0).annaNimi().equals(pel1.annaNimi()) && luettu2.get(4).annaNimi().equals(pel5.annaNimi()));

    }

    @Test
    public void luePelaajatTiedostostaTestiTiedostoPuuttuu() {

        String tiedostonnimi = "pelaajia";
        TiedostonHallinta tiedhall = new TiedostonHallinta();
        List<Pelaaja> pelaajat = tiedhall.lueTiedostosta(tiedostonnimi);
        assertEquals(0, pelaajat.size());

    }

}

package sovelluslogiikka;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PelaajatTestitJatko {

    /**
     * lisaaPelaaja metodin testi
     */
    @Test
    public void lisaaPelaaja() {

        Pelaajat pelaajat = new Pelaajat();
        pelaajat.lisaaPelaaja("Kaisa");

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
        assertEquals(3, pelaajaLista.size()); //pitäisi tyhjätä lista

    }

    /**
     * lisaaPelaaja metodin testi
     */
    @Test
    public void lisaaTuplaPelaaja() {

        Pelaajat pelaajat = new Pelaajat();

        try {
            pelaajat.lisaaPelaaja("Allu");
            fail("Pitäisi tulla poikkeus");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("Tuli väärä poikkeus: " + e.getMessage());
        }

    }
}

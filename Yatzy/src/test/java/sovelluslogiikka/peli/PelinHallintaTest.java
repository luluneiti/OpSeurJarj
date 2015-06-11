package sovelluslogiikka.peli;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.pelaaja.PelaajienHallinta;

public class PelinHallintaTest {

    private static PelinHallinta pelihall;
    private static PelaajienHallinta pelaajaHallinta;
    private static String pelaajanimi, pelaajanimi2;
    private static int i = 1;

    private static ArrayList<Integer> indeksit = new ArrayList<>();

    public PelinHallintaTest() {
        pelaajaHallinta = new PelaajienHallinta();
        pelaajanimi = "Erkki" + i;
        pelaajanimi2 = "Esko" + i;
        i++;
        pelaajaHallinta.lisaaPelaaja(pelaajanimi);
        pelaajaHallinta.valitsePelaajaksi(pelaajanimi);
        pelaajaHallinta.lisaaPelaaja(pelaajanimi2);
        pelaajaHallinta.valitsePelaajaksi(pelaajanimi2);
        pelihall = new PelinHallinta("pakkojatsi", pelaajaHallinta);
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
     * .
     */
    @Test
    public void heitaPelaajanKaikkiaNoppia() {

        System.out.println("kaikkia");
        indeksit.removeAll(indeksit);
        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat1) {
            System.out.print(luku);
        }
        System.out.println("");
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat2 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat2) {
            System.out.print(luku);
        }
        System.out.println("");
        if (lukemat1.get(0) == lukemat2.get(0) && lukemat1.get(1) == lukemat2.get(1) && lukemat1.get(2) == lukemat2.get(2) && lukemat1.get(3) == lukemat2.get(3) && lukemat1.get(4) == lukemat2.get(4)) {
            fail();
        }
    }

    /**
     * .
     */
    @Test
    public void heitaPelaajanOsaNoppia() {

        System.out.println("kolmea ekaa heitetään");
        indeksit.removeAll(indeksit);
        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat1) {
            System.out.print(luku);
        }
        System.out.println("");
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat2 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat2) {
            System.out.print(luku);
        }
        System.out.println("");
        if (lukemat1.get(3) != lukemat2.get(3) || lukemat1.get(4) != lukemat2.get(4)) {
            fail();
        }

    }

    /**
     * .
     */
    @Test
    public void heitaPelaajanOsaNoppia2() {

        System.out.println("kahta ekaa heitetään");
        indeksit.removeAll(indeksit);
        indeksit.add(0);
        indeksit.add(1);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat1) {
            System.out.print(luku);
        }
        System.out.println("");
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat2 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat2) {
            System.out.print(luku);
        }
        System.out.println("");
        if (lukemat1.get(2) != lukemat2.get(2) || lukemat1.get(3) != lukemat2.get(3) || lukemat1.get(4) != lukemat2.get(4)) {
            fail();
        }

    }

    /**
     * .
     */
    @Test
    public void heitaPelaajanOsaNoppia3() {

        System.out.println("vain ekaa heitetään");
        indeksit.removeAll(indeksit);
        indeksit.add(0);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat1) {
            System.out.print(luku);
        }
        System.out.println("");
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat2 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat2) {
            System.out.print(luku);
        }
        System.out.println("");
        if (lukemat1.get(1) != lukemat2.get(1) || lukemat1.get(2) != lukemat2.get(2) || lukemat1.get(3) != lukemat2.get(3) || lukemat1.get(4) != lukemat2.get(4)) {
            fail();
        }

    }

    /**
     * .
     */
    @Test
    public void annaLukemat() {

        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        for (Integer luku : lukemat1) {
            if (luku < 0 || luku > 6) {
                fail();
            }
        }

    }

    /**
     * .
     */
    @Test
    public void annaPelaajanViimeisimmanYhdistelmanPisteet() {

        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        int summa = 0;
        for (Integer luku : lukemat1) {
            if (luku == 1) {
                summa = summa + luku;
            }
        }
        int pist = pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(pelaajanimi);
        assertEquals(summa, pist);

    }
    
    /**
     * .
     */
    @Test
    public void onkoPelaajanVuoroPaattymassa() {

        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);

        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        
        assertEquals(false, pelihall.onkoPelaajanVuoroPaattymassa(pelaajanimi));

    }
    
     /**
     * .
     */
    @Test
    public void lopetaVuoro() {

        pelihall.lopetaKierros(pelaajanimi);
        assertEquals(true, pelihall.onkoPelaajanVuoroPaattymassa(pelaajanimi));

    }
    
     /**
     * .
     */
    @Test
    public void loppuukoKierros() {

        pelihall.lopetaKierros(pelaajanimi);
        assertEquals(false, pelihall.tarkistaAlkaakoUusiKierros());

    }
    
     /**
     * .
     */
    @Test
    public void annaPelattavaYhd() {

        assertEquals(true,!pelihall.annaPelattavaYhdistelma().equals("ei_tiedossa"));

    }
    
      /**
     * .
     */
    @Test
    public void annaPelattavaKierronro() {

        assertEquals(true,pelihall.annaKierroslkm()>0 && pelihall.annaKierroslkm()<16);

    }
    
      /**
     * .
     */
    @Test
    public void loppuukoPeli() {

        assertEquals(false,pelihall.loppuukoPeli());

    }


   

}

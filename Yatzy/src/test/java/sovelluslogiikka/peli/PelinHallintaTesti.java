package sovelluslogiikka.peli;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.pelaaja.Pelaajat;

public class PelinHallintaTesti {

    private static PelinHallinta pelihall;
    private static Pelaajat pelaajaHallinta = new Pelaajat();
    private static String pelaajanimi;
    private static int i = 1;

    public PelinHallintaTesti() {
        pelaajanimi = "Ulla" + i;
        i++;
        pelaajaHallinta.lisaaPelaaja(pelaajanimi);
        pelihall = new PelinHallinta("pakkojatsi");
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
    public void HeitaPelaajanKaikkiaNoppia() {

        System.out.println("kaikkia");
        int[] indeksit = {0, 1, 2, 3, 4};
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
    public void HeitaPelaajanOsaNoppia() {

        System.out.println("kolmea ekaa");
        int[] indeksit = {0, 1, 2};

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
    public void HeitaPelaajanOsaNoppia2() {

        System.out.println("kahta ekaa");
        int[] indeksit = {0, 1};

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
    public void HeitaPelaajanOsaNoppia3() {

        System.out.println("vain ekaa");
        int[] indeksit = {0};

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

        int[] indeksit = {0, 1, 2, 3, 4};
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

        int[] indeksit = {0, 1, 2, 3, 4};
        pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
        ArrayList<Integer> lukemat1 = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
        int summa=0;
         for (Integer luku : lukemat1) {
            if(luku==1) {summa=summa+luku;}
        }
        int pist = pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(pelaajanimi);
        assertEquals(summa, pist);

    }

//julista voittaja
}

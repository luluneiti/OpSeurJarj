package sovelluslogiikka.peli;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.pelaaja.PelaajienHallinta;

public class PelinHallintaTest2 {

    private static PelinHallinta pelihall;
    private static PelaajienHallinta pelaajaHallinta;
    private static String pelaajanimi1, pelaajanimi2;
    private static int i = 1;
    private static ArrayList<Integer> indeksit = new ArrayList<>();

    public PelinHallintaTest2() {

        pelaajaHallinta = new PelaajienHallinta();

        pelaajanimi1 = "Usko" + i;
        pelaajanimi2 = "Aaro" + i;
        i++;

        pelaajaHallinta.lisaaPelaaja(pelaajanimi1);
        pelaajaHallinta.valitsePelaajaksi(pelaajanimi1);
        pelaajaHallinta.lisaaPelaaja(pelaajanimi2);
        pelaajaHallinta.valitsePelaajaksi(pelaajanimi2);

        pelihall = new PelinHallinta();
        pelihall.alustaPeli("pakkojatsi", pelaajaHallinta);

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
    public void julistaVoittaja() {

        int summa1 = 0, summa2 = 0, indeksi = 0;

        indeksit.removeAll(indeksit);
        indeksit.add(0);
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);

        while (indeksi <= 15) {

            System.out.println("kierros " + indeksi);

            pelihall.heitaPelaajanNoppia(pelaajanimi1, indeksit);
            pelihall.lopetaKierros(pelaajanimi1); //heitetää vain kerran
            summa1 = summa1 + pelihall.annaPelaajanViimeisimmanYhdistelmanPisteet(pelaajanimi1);
            System.out.println(pelaajanimi1 + " " + summa1);

            //System.out.println(pelaajanimi5);
            pelihall.heitaPelaajanNoppia(pelaajanimi2, indeksit);
            pelihall.lopetaKierros(pelaajanimi2); //heitetää vain kerran
            summa2 = summa2 + pelihall.annaPelaajanViimeisimmanYhdistelmanPisteet(pelaajanimi2);
            System.out.println(pelaajanimi2 + " " + summa2);

            pelihall.tarkistaAlkaakoUusiKierros();

            indeksi++;

        }

        System.out.println(summa1);
        System.out.println(summa2);
        String voittaja = pelihall.julistavoittaja();
        System.out.println("voittaja: " + voittaja);

        if (!voittaja.contains(pelaajanimi1) && summa1 > summa2 || voittaja.contains("Ei voittajaa")) {
            fail();
        }

        if (!voittaja.contains(pelaajanimi2) && summa2 > summa1 || voittaja.contains("Ei voittajaa")) {
            fail();
        }

    }

}

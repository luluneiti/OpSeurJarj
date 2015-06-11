package kayttoliittyma;

import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.peli.PelinHallinta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kayttoliittyma {

    private static PelaajienHallinta pelaajaHallinta = new PelaajienHallinta();
    private static PelinHallinta pelihall;
    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        String nimi1 = "Ärre";
        String nimi2 = "Örre";
        pelaajaHallinta.lisaaPelaaja(nimi1);
        pelaajaHallinta.valitsePelaajaksi(nimi1);
        pelaajaHallinta.lisaaPelaaja(nimi2);
        pelaajaHallinta.valitsePelaajaksi(nimi2);

        pelihall = new PelinHallinta("pakkojatsi", pelaajaHallinta);

        ArrayList<Integer> indeksit = new ArrayList<>();
        indeksit.add(1);
        indeksit.add(2);
        indeksit.add(3);
        indeksit.add(4);
        indeksit.add(5);

        while (pelihall.annaKierroslkm() <= 15) {

            System.out.print(nimi1 + " : ");
            pelihall.heitaPelaajanNoppia(nimi1, indeksit);
            ArrayList<Integer> lukemat = pelihall.annaPelaajanNoppienLukemat(nimi1);
            System.out.print(pelihall.annaPelattavaYhdistelma());
            for (Integer lukema : lukemat) {
                System.out.print(lukema);
            }
            pelihall.lopetaKierros(nimi1); //heitetää vain kerran
            System.out.println("");
            System.out.println(pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(nimi1));

            System.out.print(nimi2 + ": ");
            pelihall.heitaPelaajanNoppia(nimi2, indeksit);
            ArrayList<Integer> lukemat2 = pelihall.annaPelaajanNoppienLukemat(nimi2);
            System.out.print(pelihall.annaPelattavaYhdistelma());
            for (Integer lukema : lukemat2) {
                System.out.print(lukema);
            }
            pelihall.lopetaKierros(nimi2); //heitetää vain kerran
            System.out.println("");
            System.out.println(pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(nimi2));

            pelihall.tarkistaAlkaakoUusiKierros();

        }

        System.out.println("Voittaja on: " + pelihall.julistavoittaja() + ". Onneksti olkoon!");
        System.out.println(pelihall.annaPelaajanKokonaisPisteet(nimi1));
        System.out.println(pelihall.annaPelaajanKokonaisPisteet(nimi2));

    }

}

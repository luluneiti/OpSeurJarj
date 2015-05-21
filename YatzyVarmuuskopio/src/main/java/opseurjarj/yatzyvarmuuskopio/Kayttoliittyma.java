package kayttoliittyma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import sovelluslogiikka.*;

public class Kayttoliittyma {

    private static Pelaajat pelaajaHallinta = new Pelaajat();
    private static PelinHallinta pelihall;
    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Anna pelaajan nimi");
        String nimi = lukija.nextLine();

        while (!nimi.isEmpty()) {

            try {
                pelaajaHallinta.lisaaPelaaja(nimi);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Anna pelaajan nimi");
            nimi = lukija.nextLine();
        }

        System.out.println("Anna pelattavan muunnelman nimi");
        String muunnelma = lukija.nextLine();

        try {
            pelihall = new PelinHallinta(muunnelma);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        HashSet<Pelaaja> pelaajat = pelaajaHallinta.annaPelaajat(); //tekee tulevaisuudessa omat nopat ja muut käyttöliitymä komponentit

        int[] indeksit = {0, 1, 2, 3, 4}; //toistaiseksi heitetään kaikkia noppia

        boolean jatketaanko = true;

        while (jatketaanko) {
            for (Pelaaja pelaaja : pelaajat) {

                System.out.print(pelaaja.annaNimi() + ": ");
                pelihall.heitaPelaajanNoppia(pelaaja.annaNimi(), indeksit);
                ArrayList<Integer> lukemat = pelihall.annaPelaajanNoppienLukemat(pelaaja.annaNimi());
                for (Integer lukema : lukemat) {
                    System.out.print(lukema);
                }
                System.out.println("");

                System.out.println(pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(pelaaja.annaNimi())); //tulevaisuudessa näytetään vasta kun lopettaa heittämisen

            }

            System.out.println("Jatketaanko (K/E)?");
            String komento = lukija.nextLine();
            if (komento.equals("E") || komento.equals("e")) {
                jatketaanko = false;
            }
        }

        System.out.println("Voittaja on: " + pelihall.julistavoittaja() + ". Onneksti olkoon!");
    }

}

package kayttoliittyma;

import sovelluslogiikka.pelaaja.Pelaajat;
import sovelluslogiikka.peli.PelinHallinta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kayttoliittyma {

    private static Pelaajat pelaajaHallinta = new Pelaajat();
    private static PelinHallinta pelihall;
    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Anna pelaajan nimi");
        String nimi = lukija.nextLine();

        while (!nimi.isEmpty()) {

            pelaajaHallinta.lisaaPelaaja(nimi);

            System.out.println("Anna pelaajan nimi");
            nimi = lukija.nextLine();
        }

        System.out.println("Anna pelattavan muunnelman nimi");
        String muunnelma = lukija.nextLine();

        pelihall = new PelinHallinta(muunnelma);

        System.out.println("");
        List<String> pelaajat = pelaajaHallinta.annaPelaajat(); //tekee tulevaisuudessa omat nopat ja muut käyttöliitymä komponentit

        int[] indeksit = {0, 1, 2, 3, 4}; //toistaiseksi heitetään kaikkia noppia

        boolean jatketaanko = true;

        while (jatketaanko) {
            for (String pelaajanimi : pelaajat) {

                System.out.print(pelaajanimi + ": ");
                pelihall.heitaPelaajanNoppia(pelaajanimi, indeksit);
                ArrayList<Integer> lukemat = pelihall.annaPelaajanNoppienLukemat(pelaajanimi);
                for (Integer lukema : lukemat) {
                    System.out.print(lukema);
                }

                System.out.println("");

                System.out.println(pelihall.annaPelaajanViimeisimmanYHdistelmanPisteet(pelaajanimi)); //tulevaisuudessa näytetään vasta kun lopettaa heittämisen

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

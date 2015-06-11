package sovelluslogiikka.pelaaja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import sovelluslogiikka.pelaaja.Pelaaja;

/**
 * Luokka tarjoaa palveluja tiedostosta lukuun ja tiedostoon kirjoittamiseksi
 *
 */
public class TiedostonHallinta {

    /**
     * Metodi tiedostoon kirjoittamiseksi
     *
     * @param tiedostonNimi
     * @param pelaajat
     * @return
     */
    public boolean kirjoitaTiedostoon(String tiedostonNimi, List<Pelaaja> pelaajat) {

        try {
            FileOutputStream tiedosto = new FileOutputStream(tiedostonNimi);
            ObjectOutputStream oliotiedosto = new ObjectOutputStream(tiedosto);
            oliotiedosto.writeObject((ArrayList) pelaajat);
            oliotiedosto.flush();
            tiedosto.close();
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    /**
     * Metodi tietojen lukimiseen tiedostosta
     *
     * @param tiedostonNimi
     * @return
     */
    public List<Pelaaja> lueTiedostosta(String tiedostonNimi) {

        List<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

        try {

            FileInputStream tiedosto = new FileInputStream(tiedostonNimi);
            ObjectInputStream oliotiedosto = new ObjectInputStream(tiedosto);
            pelaajat = (ArrayList) oliotiedosto.readObject();
            tiedosto.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pelaajat;

    }

}

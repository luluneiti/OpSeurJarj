package sovelluslogiikka.pelaaja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import sovelluslogiikka.pelaaja.Pelaaja;

/**
 * TiedostonHallinta luokka tarjoaa palveluja tiedostosta lukuun ja tiedostoon kirjoittamiseksi
 *
 * @author Ulla
 */
public class TiedostonHallinta {

    /**
     * Metodi tiedostoon kirjoittamiseksi
     *
     * @param tiedostonNimi tiedoston nimi
     * @param pelaajat pelaajien taulukko
     * @return true jos jos kirjoitus onnistui
     */
    public boolean kirjoitaTiedostoon(String tiedostonNimi, List<Pelaaja> pelaajat) {

        try {
            FileOutputStream tiedosto = new FileOutputStream(tiedostonNimi);
            ObjectOutputStream oliotiedosto = new ObjectOutputStream(tiedosto);
            oliotiedosto.writeObject((ArrayList) pelaajat);
            oliotiedosto.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    /**
     * Metodi tietojen lukimiseen tiedostosta
     *
     * @param tiedostonNimi tiedoston nimi
     * @return lista luetuista pelaajista
     */
    public List<Pelaaja> lueTiedostosta(String tiedostonNimi) {

        List<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

        try {
            FileInputStream tiedosto = new FileInputStream(tiedostonNimi);
            ObjectInputStream oliotiedosto = new ObjectInputStream(tiedosto);
            pelaajat = (ArrayList) oliotiedosto.readObject();
            tiedosto.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage());
        }

        return pelaajat;

    }

}

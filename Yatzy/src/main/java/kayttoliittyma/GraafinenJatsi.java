package kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javafx.scene.layout.Border;
import sovelluslogiikka.ohjaus.JatsiOhjain;

/**
 * GraafinenJatsi luokka sisältää näytöt jatsi pelaamiseen sekä naytön pelaajien
 * hallintaan.
 *
 * @author Ulla
 */
public class GraafinenJatsi extends JFrame implements ActionListener {

    //pelaajan hallinta näytön komponentit
    private static JDialog pelaajanHallintaNaytto;
    private static JLabel pelaajaHallintaOpastus;
    private static JLabel nimiOtsikko;
    private static JTextField nimi;
    private static JButton lisaaPelaaja;
    private static JButton valitsePelaajat;
    private static JButton aloitaPeli;
    private static JLabel listaOtsikko;
    private static DefaultListModel pelaajaListaSisalto;
    private static JList pelaajaLista;

    private static JPanel pelaajaNayttoLuonti;
    private static JPanel pelaajaNayttoValinta;
    private static JPanel pelaajaNayttoOpastus;

    //peli näytön komponentit
    //otsikkorivi
    private static JPanel peliOtsikkorivi;
    private static JPanel nopparivit;
    private static JLabel peliOtsikko;

    //pelaajakohtainen rivi
    private static int rivinro;
    private static JLabel[] pelaajanimi;
    private static JLabel[] silmalukematKuvana;
    private static JCheckBox[] heittaako;
    private static JLabel[] pisteOtsikko;
    private static JTextField[] pisteet;
    private static JButton[] heita;
    private static JButton[] lopeta;

    //inforivi
    private static JPanel peliInforivi;
    private static JLabel peliInfoteksti;

    private static int pelaajalkm = 0; //pelaajien lkm
    private JatsiOhjain ohjain;

    public GraafinenJatsi() {

    }

    /**
     * Metodi jolla "käynnistetään" käyttöliittymä
     */
    public void run() {

        luoPelaajanHallintaNaytto();
        pack();

    }

    /**
     * Metodi jolla ohjausluokka rekisteröi itsensä käyttöliittymälle
     *
     * @param ohjain ohjausluokka
     */
    public void rekisteroiOhjain(JatsiOhjain ohjain) {

        this.ohjain = ohjain;

    }

    /**
     * Metodi joka luo pelaajien hallintaa varten nayton (dialog)
     */
    private void luoPelaajanHallintaNaytto() {

        luoAsettelePelaajaNaytonKomponentit();

        lisaaPelaaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    ohjain.pelaajanLisays(GraafinenJatsi.nimi.getText());
                    pelaajaNayttoValinta.repaint();
                } catch (IllegalArgumentException e) {
                    naytaVirhe("Virhetilanne", e.getMessage());
                }

            }
        }
        );
        valitsePelaajat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {

                if (!pelaajaLista.isSelectionEmpty()) {
                    ohjain.pelaajanValinta(pelaajaLista.getMinSelectionIndex(), pelaajaLista.getMaxSelectionIndex());

                } else {
                    naytaIlmoitus("Virhetilanne", "Et ole valinnut pelaajia.");
                }
            }
        }
        );
        aloitaPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                ohjain.pelinAloitus();
                pelaajanHallintaNaytto.setVisible(false);
                luoPelinHallintaNaytto();

            }
        }
        );

        pelaajanHallintaNaytto.add(pelaajaNayttoLuonti, BorderLayout.PAGE_START);
        pelaajanHallintaNaytto.add(pelaajaNayttoValinta, BorderLayout.CENTER);
        pelaajanHallintaNaytto.add(pelaajaNayttoOpastus, BorderLayout.PAGE_END);
        pelaajanHallintaNaytto.setVisible(true);

    }

    /**
     * Metodi joka luo pelaajien hallintaa varten komponenti ja asettelee ne
     */
    private void luoAsettelePelaajaNaytonKomponentit() {

        pelaajanHallintaNaytto = new JDialog(pelaajanHallintaNaytto, "Pelaajien hallinta", true);
        pelaajanHallintaNaytto.setDefaultCloseOperation(HIDE_ON_CLOSE);
        pelaajanHallintaNaytto.setLayout(new FlowLayout());
        pelaajanHallintaNaytto.setSize(400, 450);

        nimiOtsikko = new JLabel("Pelaajan nimi ", 10);
        nimi = new JTextField("", 10);
        lisaaPelaaja = new JButton("Luo uusi pelaaja");

        listaOtsikko = new JLabel("Pelaajat: ", 10);
        pelaajaListaSisalto = new DefaultListModel();

        if (this.ohjain.annaKaikkienPelaajienLukumaara() > 0) {
            for (int i = 0; i < this.ohjain.annaKaikkienPelaajienLukumaara(); i++) {

                pelaajaListaSisalto.addElement(ohjain.annaPelaaja(i));
            }
        }

        pelaajaLista = new JList(pelaajaListaSisalto);
        JScrollPane scrolli = new JScrollPane();
        scrolli.getViewport().setView(pelaajaLista);
        scrolli.setSize(new Dimension(200, 100));

        valitsePelaajat = new JButton("Valitse pelaajat");
        aloitaPeli = new JButton("Aloita peli");
        aloitaPeli.setEnabled(false);
        pelaajaHallintaOpastus = new JLabel("", 10);
        pelaajaHallintaOpastus.setText("Lisää uusia pelaajia ja valitse pelaajat peliin.");
        pelaajaHallintaOpastus.setForeground(Color.BLUE);

        //asetellaan komponentit
        pelaajaNayttoLuonti = new JPanel();
        pelaajaNayttoLuonti.setLayout(new FlowLayout());
        pelaajaNayttoLuonti.add(nimiOtsikko);
        pelaajaNayttoLuonti.add(nimi);
        pelaajaNayttoLuonti.add(lisaaPelaaja);

        pelaajaNayttoValinta = new JPanel();
        pelaajaNayttoValinta.setLayout(new FlowLayout());
        pelaajaNayttoValinta.add(listaOtsikko);
        //pelaajaNayttoValinta.add(pelaajaLista);
        pelaajaNayttoValinta.add(scrolli);

        pelaajaNayttoValinta.add(valitsePelaajat);
        pelaajaNayttoValinta.add(aloitaPeli);

        pelaajaNayttoOpastus = new JPanel();
        pelaajaNayttoOpastus.add(pelaajaHallintaOpastus);

    }

    /**
     * Metodi jolla voidaan disabloida ja enabloida valitse pelaaja painike
     *
     * @param arvo totuusarvo
     */
    public void aktivoiPassivoiValitsePelaaja(Boolean arvo) {

        valitsePelaajat.setEnabled(arvo);

    }

    /**
     * Metodi jolla voidaan disabloida ja enabloida aloita peli painike
     *
     * @param arvo totuusarvo
     */
    public void aktivoiPassivoiAloitaPeli(Boolean arvo) {

        aloitaPeli.setEnabled(arvo);

    }

    /**
     * Metodi joka luo peliä varten näytön (frame)
     */
    private void luoPelinHallintaNaytto() {

        pelaajalkm = ohjain.annaValittujenPelaajienLukumaara();
        setBackground(Color.WHITE);

        luoAsettelePeliNaytonOtsikkoRivi();
        luoAsettelePeliNaytonNoppaRivit(pelaajalkm);
        luoAsettelePeliNaytonInforivi();

        add(peliOtsikkorivi, BorderLayout.PAGE_START);
        add(nopparivit, BorderLayout.CENTER);
        add(peliInforivi, BorderLayout.SOUTH);

        setVisible(true);

    }

    /**
     * Metodi joka luo peli näytön otsikkoa varten komponentit ja asettelee ne
     */
    private void luoAsettelePeliNaytonOtsikkoRivi() {

        setTitle("Yatzy");
        setLocation(3, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        peliOtsikkorivi = new JPanel();
        peliOtsikkorivi.setLayout(new FlowLayout());
        peliOtsikko = new JLabel("Pelattava yhdistelmä: " + ohjain.annaPelattavaYhdistelma(), 10);
        peliOtsikko.setForeground(Color.BLUE);
        peliOtsikko.setFont(new Font("Dialog", Font.BOLD, 16));
        peliOtsikkorivi.add(peliOtsikko);

    }

    /**
     * Metodi joka luo peli näytön nopparivejä varten komponentit ja asettelee
     * ne Jokaiselle pelaajalle luodaan oma nopparivi
     */
    private void luoAsettelePeliNaytonNoppaRivit(int pelaajalkm) {

        nopparivit = new JPanel();
        nopparivit.setLayout(new GridLayout(pelaajalkm, 17));

        //nopparivi per pelaaja
        pelaajanimi = new JLabel[pelaajalkm];
        silmalukematKuvana = new JLabel[pelaajalkm * this.ohjain.annaNoppienMaara()];
        heittaako = new JCheckBox[pelaajalkm * this.ohjain.annaNoppienMaara()];
        pisteOtsikko = new JLabel[pelaajalkm];
        pisteet = new JTextField[pelaajalkm];
        heita = new JButton[pelaajalkm];
        lopeta = new JButton[pelaajalkm];

        for (int i = 0; i < pelaajalkm; i++) { //tehdään yksi per pelaaja

            pisteet[i] = new JTextField("0", 10);
            pisteOtsikko[i] = new JLabel("Pisteet:", 10);
            heita[i] = new JButton("Heitä");
            heita[i].setName("Heita" + i);
            lopeta[i] = new JButton("Lopeta");
            lopeta[i].setName("Lopeta" + i);
            lopeta[i].setEnabled(false);

        }

        ImageIcon image = new ImageIcon(ImageIcon.class.getResource("/" + 0 + ".png"));
        for (int i = 0; i < pelaajalkm * this.ohjain.annaNoppienMaara(); i++) { //tehdään monta per pelaaja

            silmalukematKuvana[i] = new JLabel(image, JLabel.CENTER);
            heittaako[i] = new JCheckBox("Heitto?");
            heittaako[i].setSelected(true);
            heittaako[i].setEnabled(false);

        }

        for (rivinro = 0; rivinro < pelaajalkm; rivinro++) { //rivejä 1 per pelaaja

            pelaajanimi[rivinro] = new JLabel(ohjain.annaValitunPelaajanNimi(rivinro), 10);
            pelaajanimi[rivinro].setForeground(Color.BLUE);
            pelaajanimi[rivinro].setFont(new Font("Dialog", Font.BOLD, 14));
            nopparivit.add(pelaajanimi[rivinro]);

            for (int i = this.ohjain.annaNoppienMaara() * rivinro; i < (rivinro + 1) * this.ohjain.annaNoppienMaara(); i++) {
                //nopparivit.add(silmalukemat[i]);
                nopparivit.add(silmalukematKuvana[i]);
                nopparivit.add(heittaako[i]);
            }

            nopparivit.add(heita[rivinro]);
            nopparivit.add(lopeta[rivinro]);
            nopparivit.add(pisteOtsikko[rivinro]);
            nopparivit.add(pisteet[rivinro]);

            heita[rivinro].addActionListener(this);
            lopeta[rivinro].addActionListener(this);

        }
    }

    /**
     * Metodi joka luo peli näytön inforfiviä (viimeinen rivi) varten
     * komponentit ja asettelee ne
     */
    private void luoAsettelePeliNaytonInforivi() {

        peliInforivi = new JPanel();
        peliInforivi.setLayout(new FlowLayout());

        peliInfoteksti = new JLabel("Hei vaan! Aloita klikkaamalla heitä-painiketta.", 10);
        peliInfoteksti.setForeground(Color.BLUE);
        peliInforivi.add(peliInfoteksti);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //tanne tullaan, kun painiketapahtuma havaitaan pelinhallinta näytöllä 
        //huom: pelaajahallinnan tapahtumat käsitellään sisäluokissa

        JButton kohde = (JButton) e.getSource();

        if (kohde.getName().contains("Heita")) {

            ohjain.heita(kohde.getName());

        }
        if (kohde.getName().contains("Lopeta")) {

            ohjain.lopetaVuoro(kohde.getName());

        }

    }

    /**
     * Metodi joka kertoo onko kaikki heita ja lopeta painikkeet disabloitu
     *
     * @return true jos kaikkien pelaajien heita ja lopeta painikkeet
     * disabloituna
     */
    public boolean onkoKaikkienPelaajienPainikkeetPassivoituna() {

        int lkm = 0;

        for (int i = 0; i < heita.length; i++) {
            if (!heita[i].isEnabled() && !lopeta[i].isEnabled()) {
                lkm++;
            }
        }
        if (lkm == pelaajalkm) {
            return true;
        }
        return false;

    }

    /**
     * Metodi jolla tietty heittaako valintaruutu voidaan valita (=ruksi
     * ruutuun)
     *
     * @param indeksi kuinka mones heittaako valintaruutu
     */
    public void valitse(int indeksi) {

        heittaako[indeksi].setSelected(true);
    }

    /**
     * Metodi jolla saa selville onko tietty heittaako valintaruutu valittuna
     *
     * @param indeksi kuinka mones heittaako valintaruutu
     * @return true jos valittu
     */
    public boolean onkoHeittaakoValittu(int indeksi) {

        return heittaako[indeksi].isSelected();

    }

    /**
     * Metodi jolla saa selville onko tietty pelaajalistan pelaaja valittuna
     *
     * @param indeksi kuinka mones listan elementti
     * @return true jos listan elementti on valittuna
     */
    public boolean onkoListanElementtiValittu(int indeksi) {
        return pelaajaLista.isSelectedIndex(indeksi);
    }

    /**
     * Metodi jolla asetetaan nopan lukema tiettyyn silmalukemat kenttään
     *
     * @param indeksi kuinka mones lukema tekstikenttä
     * @param lukema haetaa lukemalla kuva
     */
    public void asetaLukemaKuvana(int indeksi, int lukema) {

        silmalukematKuvana[indeksi].setIcon(this.ohjain.naytaLukemaKuvana(lukema));

    }

    /**
     * Metodi jolla asetetaan yhdiselmän ja pelin kokonaispisteet tiettyyn
     * pisteet kenttään
     *
     * @param indeksi kuinka mones pisteet tekstikenttä
     * @param teksti asetetaan pisteet ko. kenttään
     */
    public void asetaPisteet(int indeksi, String teksti) {

        pisteet[indeksi].setText(teksti);

    }

    /**
     * Metodi jolla muutetaaan pelin otsikko
     *
     * @param teksti teksti, joka tulee näyttää otsikossa
     */
    public void asetaPeliOtsikko(String teksti) {

        peliOtsikko.setText(teksti);

    }

    /**
     * Metodi jolla muutetaaan inforivin sisältöä
     *
     * @param teksti teksti joka tulee näyttää inforivillä
     */
    public void asetaPeliInfo(String teksti) {

        peliInfoteksti.setText(teksti);

    }

    /**
     * Metodi jolla lisäätään pelaaja pelaaja-listalle
     *
     * @param pelaaja lisättävän pelaajan nimi
     */
    public void lisaaPelaajaListalle(String pelaaja) {

        pelaajaListaSisalto.addElement(pelaaja);

    }

    /**
     * Metodi jolla pyydetään tiettyä pelaaja-listan pelaajaa
     *
     * @param indeksi kuinka mones listan elementi
     * @return palauttaa ko. listan elementin
     */
    public String annaListanElementti(int indeksi) {
        return pelaajaListaSisalto.get(indeksi).toString();
    }

    /**
     * Metodi jolla voidaan disabloida ja enabloida lopeta painike
     *
     * @param arvo totuusarvo
     * @param indeksi kuinka mones lopeta painike
     */
    public void aktivoiPassivoiLopeta(Boolean arvo, int indeksi) {

        lopeta[indeksi].setEnabled(arvo);

    }

    /**
     * Metodi jolla voidaan disabloida ja enabloida heita painike
     *
     * @param indeksi kuinka mones heita painke
     * @param arvo totuusarvo
     *
     */
    public void aktivoiPassivoiHeita(Boolean arvo, int indeksi) {

        heita[indeksi].setEnabled(arvo);

    }

    /**
     * Metodi jolla voidaan disabloida ja enabloida heittaako checkbox
     *
     * @param arvo totuurarvo
     * @param indeksi kuinka mones heittaako valintaruutu
     */
    public void aktivoiPassivoiHeittaako(Boolean arvo, int indeksi) {

        heittaako[indeksi].setEnabled(arvo);

    }

    /**
     * Metodi jolla pyydetään näyttämään info-viesti
     *
     * @param otsikko näytettävän ilmoituksen otsikko
     * @param teksti näytettävän ilmoituksen teksti
     */
    public void naytaIlmoitus(String otsikko, String teksti) {

        JOptionPane.showMessageDialog(null, teksti, otsikko, JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Metodi jolla pyydetään näyttämään virhe-viesti
     *
     * @param otsikko näytettävän ilmoituksen otsikko
     * @param teksti näytettävän ilmoituksen teksti
     */
    public void naytaVirhe(String otsikko, String teksti) {

        JOptionPane.showMessageDialog(null, teksti, otsikko, JOptionPane.ERROR_MESSAGE);

    }

}

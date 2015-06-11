package kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javafx.scene.layout.Border;
import sovelluslogiikka.pelaaja.PelaajienHallinta;
import sovelluslogiikka.peli.PelinHallinta;
import sovelluslogiikka.peli.YhdistelmanNimi;

public class GraafinenJatsi extends JFrame implements ActionListener {

    //pelaajan hallinta nayton komponentit
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

    //pelin hallinta näytön komponentit
    private static GraafinenJatsi pelinHallintaNaytto;

    /*otsikkorivi*/
    private static JPanel peliOtsikkorivi;
    private static JPanel nopparivit;
    private static JLabel peliOtsikko;
    private static JButton uusiKierros;

    /*pelaajakohtainen rivi*/
    private static int rivinro;
    private static JLabel[] pelaajanimi;
    private static JTextField[] silmalukemat;
    private static JCheckBox[] heittaako;
    private static JLabel[] yhdistelmapistOtsikko;
    private static JTextField[] yhdistelmapisteet;
    private static JLabel[] kokonaispistOtsikko;
    private static JTextField[] kokonaispisteet;
    private static JButton[] heita;
    private static JButton[] lopeta;

    /*inforivi*/
    private static JPanel peliInforivi;
    private static JLabel peliInfoteksti;

    private static PelinHallinta pelihallinta;
    private static PelaajienHallinta pelaajienHallinta = new PelaajienHallinta();
    private static java.util.List<String> pelaajienNimet; //pelaajien nimet
    private static int pelaajalkm = 0; //pelaajien lkm

    public GraafinenJatsi() {

    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui();
            }
        });

    }

    private static void gui() {

        pelinHallintaNaytto = new GraafinenJatsi();
        luoPelaajanHallintaNaytto();
        luoPelinHallintaNaytto();
        pelinHallintaNaytto.pack();

    }

    /**
     * Metodi joka luo pelaajien hallintaa varten naytto (dialog)
     */
    private static void luoPelaajanHallintaNaytto() {

        try {

            luoAsetteleKomponentit();

            lisaaPelaaja.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    lisaaPelaajaToimet();
                }
            }
            );
            valitsePelaajat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event
                ) {
                    valitsePelaajaToimet();
                }
            }
            );
            aloitaPeli.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    aloitaPeliToimet();
                }
            }
            );

            pelaajanHallintaNaytto.add(pelaajaNayttoLuonti, BorderLayout.PAGE_START);
            pelaajanHallintaNaytto.add(pelaajaNayttoValinta, BorderLayout.CENTER);
            pelaajanHallintaNaytto.add(pelaajaNayttoOpastus, BorderLayout.PAGE_END);
            pelaajanHallintaNaytto.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pelaajien hallinnassa tapahtui virhe. ", null, JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodi joka luo peliä varten näytön (frame)
     */
    private static void luoPelinHallintaNaytto() {

        try {

            pelaajienNimet = pelaajienHallinta.annaValitutPelaajat();
            pelaajalkm = pelaajienNimet.size();
            pelihallinta = new PelinHallinta("pakkojatsi", pelaajienHallinta);

            luoAsetteleOtsikkoRivi();
            luoAsetteleNoppaRivit(pelaajalkm);
            luoAsetteleInforivi();

            pelinHallintaNaytto.add(peliOtsikkorivi, BorderLayout.PAGE_START);
            pelinHallintaNaytto.add(nopparivit, BorderLayout.CENTER);
            pelinHallintaNaytto.add(peliInforivi, BorderLayout.SOUTH);

            pelinHallintaNaytto.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Peliä ei voi pelata, jos et valitse pelaajia peliin. ", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //tanne tullaan, kun painiketapahtuma havaitaan pelinhallinta näytöllä 
        //huom: pelaajahallinnan tapahtumat käsitellään sisäluokissa

        JButton kohde = (JButton) e.getSource();
        if (kohde.getText().contains("Heitä")) {
            heitaToimet(kohde); //sirrytään omaan metodiin jossa heiton toimenpiteet
        }
        if (kohde.getText().contains("Lopeta")) {
            lopetaVuoroToimet(kohde); //sirrytään omaan metodiin jossa lopeta vuoro toimenpiteet
        }
        if (kohde.getText().contains("Uusi")) {
            uusiKierrosToimet(); //sirrytään omaan metodiin jossa uusi kierros toimenpiteet
        }

    }

    private void heitaToimet(JButton kohde) {

        rivinro = Integer.parseInt(kohde.getText().substring(kohde.getText().length() - 1, kohde.getText().length()));
        ArrayList<Integer> indeksit = new ArrayList<>();

        int j = rivinro * 5;
        for (int i = 0; i < 5; i++) { //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ
            if (heittaako[j].isSelected()) {
                indeksit.add(i);
            }
            j++;
        }

        pelihallinta.heitaPelaajanNoppia(pelaajanimi[rivinro].getText(), indeksit);
        ArrayList<Integer> lukemat = pelihallinta.annaPelaajanNoppienLukemat(pelaajanimi[rivinro].getText());

        lopeta[rivinro].setEnabled(true);

        int k = rivinro * 5; //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ
        for (int i = 0; i < 5; i++) {
            silmalukemat[k].setText("" + lukemat.get(i));
            heittaako[k].setEnabled(true);
            k++;
        }

        yhdistelmapisteet[rivinro].setText("" + pelihallinta.annaPelaajanViimeisimmanYHdistelmanPisteet(GraafinenJatsi.this.pelaajanimi[rivinro].getText()));

        uusiKierros.setEnabled(false);

        if (pelihallinta.onkoPelaajanVuoroPaattymassa(GraafinenJatsi.this.pelaajanimi[rivinro].getText())) {
            heita[rivinro].setEnabled(false);
            lopeta[rivinro].setEnabled(false);

            if (onkoKaikkiDisabloitu()) {
                uusiKierros.setEnabled(true);
                peliInfoteksti.setText("Klikkaa Uusi kierros -painiketta.");
            }
        }
    }

    private static boolean onkoKaikkiDisabloitu() {

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

    private static void uusiKierrosToimet() {

        if (!pelihallinta.loppuukoPeli()) {

            for (int i = 0; i < heita.length; i++) { //yksi per pelaaja
                heita[i].setEnabled(true);
                lopeta[i].setEnabled(false);
                yhdistelmapisteet[i].setText("0");
            }

            for (int i = 0; i < heittaako.length; i++) { //useita per pelaaja
                heittaako[i].setSelected(true);
                heittaako[i].setEnabled(false);
                silmalukemat[i].setText("0");
            }

            peliOtsikko.setText("Kierros: " + pelihallinta.annaKierroslkm() + " / Pelattava yhdistelma: " + pelihallinta.annaPelattavaYhdistelma());
            peliInfoteksti.setText("Heittäkää noppia.");

        } else {

            peliInfoteksti.setText("");

            JOptionPane.showMessageDialog(null, pelihallinta.julistavoittaja(), "Onneksi olkoon!", JOptionPane.INFORMATION_MESSAGE);

            pelaajienHallinta.loppu(); //kirjoitetaan pelaajien tiedot tiedostoon

            for (int i = 0; i < kokonaispisteet.length; i++) {
                kokonaispisteet[i].setText("" + pelihallinta.annaPelaajanKokonaisPisteet(pelaajanimi[i].getText()));
            }

            if (!pelaajienHallinta.annaEnnatyksenTehneet().isEmpty()) {
                JOptionPane.showMessageDialog(null, pelaajienHallinta.annaEnnatyksenTehneet(), "Ennätyksen tehneet", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Ei ennätyksten tehneta", "Ennätyksten tehneet", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }

    private static void lopetaVuoroToimet(JButton kohde) {

        rivinro = Integer.parseInt(kohde.getText().substring(kohde.getText().length() - 1, kohde.getText().length()));

        pelihallinta.lopetaKierros(pelaajanimi[rivinro].getText());

        heita[rivinro].setEnabled(false);
        lopeta[rivinro].setEnabled(false);

        if (onkoKaikkiDisabloitu()) {
            uusiKierros.setEnabled(true);
        }

        pelihallinta.tarkistaAlkaakoUusiKierros();
    }

    private static void luoAsetteleOtsikkoRivi() {

        pelinHallintaNaytto.setTitle("Yatzy - heitä noppaa");
        pelinHallintaNaytto.setLocation(3, 100);
        pelinHallintaNaytto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        peliOtsikkorivi = new JPanel();
        peliOtsikkorivi.setLayout(new FlowLayout());

        peliOtsikko = new JLabel("Kierros: " + pelihallinta.annaKierroslkm() + " / Pelattava yhdistelma: " + pelihallinta.annaPelattavaYhdistelma(), 10);

        peliOtsikko.setForeground(Color.BLUE);
        peliOtsikko.setFont(new Font("Dialog", Font.BOLD, 16));
        uusiKierros = new JButton("Uusi kierros");
        uusiKierros.setEnabled(false);

        peliOtsikkorivi.add(peliOtsikko);
        peliOtsikkorivi.add(uusiKierros);

        uusiKierros.addActionListener(pelinHallintaNaytto);

    }

    private static void luoAsetteleNoppaRivit(int pelaajalkm) {

        nopparivit = new JPanel();
        nopparivit.setLayout(new GridLayout(pelaajalkm, 17, 2, 5));   //NOPPIEN MÄÄRÄSTÄ RIIPPUMATON!!!!

        //nopparivi per pelaaja
        pelaajanimi = new JLabel[pelaajalkm];
        silmalukemat = new JTextField[pelaajalkm * 5]; //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ
        heittaako = new JCheckBox[pelaajalkm * 5]; //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ
        yhdistelmapistOtsikko = new JLabel[pelaajalkm];
        yhdistelmapisteet = new JTextField[pelaajalkm];
        kokonaispistOtsikko = new JLabel[pelaajalkm];
        kokonaispisteet = new JTextField[pelaajalkm];
        heita = new JButton[pelaajalkm];
        lopeta = new JButton[pelaajalkm];

        for (int i = 0; i < pelaajalkm; i++) { //tehdään yksi per pelaaja

            yhdistelmapisteet[i] = new JTextField("0", 10);
            kokonaispisteet[i] = new JTextField("0", 10);
            yhdistelmapistOtsikko[i] = new JLabel("Yhdistelmä:", 10);
            kokonaispistOtsikko[i] = new JLabel("Kokonais:", 10);
            heita[i] = new JButton("Heitä" + i);
            lopeta[i] = new JButton("Lopeta vuoro" + i);
            lopeta[i].setEnabled(false);

        }

        for (int i = 0; i < pelaajalkm * 5; i++) { //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ

            silmalukemat[i] = new JTextField("0", 10);
            heittaako[i] = new JCheckBox("Heitto?");
            heittaako[i].setSelected(true);
            heittaako[i].setEnabled(false);

        }

        for (rivinro = 0; rivinro < pelaajalkm; rivinro++) { //rivejä 1 per pelaaja

            pelaajanimi[rivinro] = new JLabel(pelaajienNimet.get(rivinro), 10);
            pelaajanimi[rivinro].setForeground(Color.BLUE);
            pelaajanimi[rivinro].setFont(new Font("Dialog", Font.BOLD, 14));
            nopparivit.add(pelaajanimi[rivinro]);

            for (int i = 5 * rivinro; i < (rivinro + 1) * 5; i++) { //EI SAISI OLLA RIIPPUVAINEN NOPPIEN MÄÄRÄSTÄ
                nopparivit.add(silmalukemat[i]);
                nopparivit.add(heittaako[i]);
            }

            nopparivit.add(heita[rivinro]);
            nopparivit.add(lopeta[rivinro]);
            nopparivit.add(yhdistelmapistOtsikko[rivinro]);
            nopparivit.add(yhdistelmapisteet[rivinro]);
            nopparivit.add(kokonaispistOtsikko[rivinro]);
            nopparivit.add(kokonaispisteet[rivinro]);

            heita[rivinro].addActionListener(pelinHallintaNaytto);
            lopeta[rivinro].addActionListener(pelinHallintaNaytto);

        }
    }

    private static void luoAsetteleInforivi() {
        //vika rivi

        peliInforivi = new JPanel();
        peliInforivi.setLayout(new FlowLayout());

        peliInfoteksti = new JLabel("Hei vaan! Aloita klikkaamalla heitä-painiketta.", 10);
        peliInfoteksti.setForeground(Color.BLUE);
        peliInforivi.add(peliInfoteksti);
    }

    private static void luoAsetteleKomponentit() {

        pelaajienNimet = pelaajienHallinta.annaKaikkiPelaajat();

        pelaajanHallintaNaytto = new JDialog(pelaajanHallintaNaytto, "Pelaajien hallinta", true);
        pelaajanHallintaNaytto.setDefaultCloseOperation(HIDE_ON_CLOSE);
        pelaajanHallintaNaytto.setLayout(new FlowLayout());
        pelaajanHallintaNaytto.setSize(400, 450);

        nimiOtsikko = new JLabel("Pelaajan nimi ", 10);
        nimi = new JTextField("", 10);
        lisaaPelaaja = new JButton("Luo uusi pelaaja");

        listaOtsikko = new JLabel("Pelaajat: ", 10);
        pelaajaListaSisalto = new DefaultListModel();
        if (pelaajienNimet != null) {
            for (String nimi : pelaajienNimet) {
                pelaajaListaSisalto.addElement(nimi + ", " + pelaajienHallinta.annaPelaajanPisteet(nimi));
            }
        }
        pelaajaLista = new JList(pelaajaListaSisalto);

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
        pelaajaNayttoValinta.add(pelaajaLista);
        pelaajaNayttoValinta.add(valitsePelaajat);
        pelaajaNayttoValinta.add(aloitaPeli);

        pelaajaNayttoOpastus = new JPanel();
        pelaajaNayttoOpastus.add(pelaajaHallintaOpastus);

    }

    private static void lisaaPelaajaToimet() {

        try {
            pelaajienHallinta.lisaaPelaaja(GraafinenJatsi.nimi.getText());
            pelaajaListaSisalto.addElement(GraafinenJatsi.nimi.getText() + ", " + pelaajienHallinta.annaPelaajanPisteet(GraafinenJatsi.nimi.getText()));
            pelaajaNayttoValinta.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tapahtui virhe: " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void valitsePelaajaToimet() {
        try {

            if (!pelaajaLista.isSelectionEmpty()) {

                for (int i = pelaajaLista.getMinSelectionIndex(); i <= pelaajaLista.getMaxSelectionIndex(); i++) {
                    if (pelaajaLista.isSelectedIndex(i)) {
                        pelaajienHallinta.valitsePelaajaksi(pelaajaListaSisalto.get(i).toString().substring(0, pelaajaListaSisalto.get(i).toString().indexOf(",")));
                    }
                }
                valitsePelaajat.setEnabled(false);
                aloitaPeli.setEnabled(true);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tapahtui virhe: " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void aloitaPeliToimet() {
        try {
            pelaajanHallintaNaytto.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tapahtui virhe: " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }

    }

}

package sovelluslogiikka.peli;

import java.util.ArrayList;

/**
 * Pakkojatsi luokka tarjoaa pakkojatsi pelimuunnelman logiikan. Tietää noppien
 * lukumäärän, pelattavat yhdistelmat ja oaaa laskea pisteet yhdistelmalle sekä
 * kokonaispisteet pakkojatsin säännöin Jos osa säännöistä sopii melkein
 * kaikille jatsin muunnelmille, niin voimme viedä sen default metodiksi
 * rajapintaan
 */
public class Pakkojatsi implements PeliMuunnelma {

    /**
     * Metodi yhdistelan pisteiden laskentaan
     *
     * @param lukemat
     * @param kierroslkm
     * @return pisteet
     */
    @Override
    public int laskeYhdistelmanPisteet(ArrayList<Integer> lukemat, int kierroslkm) {

        int summa = 0;

        if (kierroslkm >= 1 || kierroslkm <= 6) { //MITEN SAISI IF HIRVIÖT KORVATTUA...
            summa = summaaEsiintymat(lukemat, kierroslkm);
        }

        if (kierroslkm == 7) {
            summa = summaaParinLukemat(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }
        if (kierroslkm == 8) {
            //kaksi paria
        }
        if (kierroslkm == 9) {
            summa = summaaKolmosienLukemat(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }
        if (kierroslkm == 10) {
            summa = summaaNelosienLukemat(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }
        if (kierroslkm == 11) {
            summa = tarkistaPienisuora(lukemat);
        }
        if (kierroslkm == 12) {
            summa = tarkistaSuurisuora(lukemat);
        }
        if (kierroslkm == 13) {
            //täyskäsi=yksi pari ja yksi kolmoisluku
        }
        if (kierroslkm == 14) {
            summa = lukemat.get(0) + lukemat.get(1) + lukemat.get(2) + lukemat.get(3) + lukemat.get(4);
        }
        if (kierroslkm == 15) {
            summa = tarkistaJatsi(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }

        return summa;

    }

    private int summaaEsiintymat(ArrayList<Integer> lukemat, int kierroslkm) {
        int summa = 0;
        for (Integer luku : lukemat) {

            if (luku == kierroslkm) {
                summa = summa + luku;
            }

        }
        return summa;
    }

   
    private int summaaParinLukemat(ArrayList<Integer> lukemat) {

        int paluu = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {

                if (lukemat.get(i) == lukemat.get(j) && i == j - 1) { //bug, pitää ottaa parhaimman parin pisteet jos esim. 46611

                    paluu = lukemat.get(i) + lukemat.get(j);

                }

            }

        }

        return paluu;
    }

    private int summaaKolmosienLukemat(ArrayList<Integer> lukemat) {

        int paluu = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {
                for (int k = 1; k < lukemat.size(); k++) {

                    if (lukemat.get(i) == lukemat.get(j) && i == j - 1 && lukemat.get(j) == lukemat.get(k) && j == k - 1) {

                        paluu = lukemat.get(i) + lukemat.get(j) + lukemat.get(k);

                    }

                }
            }

        }

        return paluu;
    }

    private int summaaNelosienLukemat(ArrayList<Integer> lukemat) {

        int paluu = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {
                for (int k = 1; k < lukemat.size(); k++) {

                    for (int l = 1; l < lukemat.size(); l++) {

                        if (lukemat.get(i) == lukemat.get(j) && i == j - 1 && lukemat.get(j) == lukemat.get(k) && j == k - 1 && lukemat.get(k) == lukemat.get(l) && k == l - 1) {

                            paluu = lukemat.get(i) + lukemat.get(j) + lukemat.get(k) + lukemat.get(l);

                        }

                    }
                }
            }

        }

        return paluu;
    }

    private int tarkistaPienisuora(ArrayList<Integer> lukemat) {
        int paluu = 0;
        int lkm = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            if (lukemat.get(i) == i + 1) {
                lkm++;
            }

        }

        if (lkm == 5) {
            paluu = 15;
        }
        return paluu;
    }

    private int tarkistaSuurisuora(ArrayList<Integer> lukemat) {
        int paluu = 0;
        int lkm = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            if (lukemat.get(i) == i + 2) {
                lkm++;
            }

        }

        if (lkm == 5) {
            paluu = 20;
        }
        return paluu;
    }

    private int tarkistaJatsi(ArrayList<Integer> lukemat) {
        int paluu = 0;
        int lkm = 0;

        int verrattava = lukemat.get(0);
        for (int i = 1; i < lukemat.size(); i++) {

            if (lukemat.get(i) == verrattava) {
                lkm++;
            }

        }

        if (lkm == 4) {
            paluu = 50;
        }
        return paluu;
    }

    /**
     * Metodi joka tietää yhdistelmien suoritusjärjestyksen
     *
     * @param kierroslkm
     * @return yhdistelman nimi
     */
    @Override
    public YhdistelmanNimi annaPelattavaYhdistelma(int kierroslkm) {

        return YhdistelmanNimi.values()[kierroslkm - 1]; //mitä jos kierrosluku > 15??????

    }

    /**
     * Metodi joka osaa laskea pelaajan kokonaispisteet pelattujen yhdistelmien
     * perusteella
     *
     * @param yhdistelmat
     * @return pisteet
     */
    @Override
    public int laskeKokonaisPisteet(ArrayList<Yhdistelma> yhdistelmat) {

        int summa1 = 0;
        int summa2 = 0;

        for (int i = 1; i < 7; i++) {
            summa1 = summa1 + yhdistelmat.get(i).annaPisteet();
        }
        for (int i = 7; i < 15; i++) { //final luokkamuuttujaksi
            summa2 = summa2 + yhdistelmat.get(i).annaPisteet();
        }
        if (summa1 >= 63) {
            summa1 = summa1 + 50;
        }

        return summa1 + summa2;
    }

    /**
     * Metodi joka osaa luoda tarvittavan määrän sopia noppia pakkojatsia varten
     *
     * @return nopat
     */
    @Override
    public ArrayList<Noppa> luoNopat() {

        ArrayList<Noppa> nopat = new ArrayList<Noppa>();
        //pakkojatsissa 5 noppaa
        for (int i = 0; i < 5; i++) {
            Noppa noppa = new Noppa();
            nopat.add(noppa);
        }
        return nopat;
    }

    /**
     * Metodi joka osaa luoda tarvittavat yhdistelat pakkojatsia varten
     *
     * @return yhdistelmat
     */
    @Override
    public ArrayList<Yhdistelma> luoYhdistelmat() {

        ArrayList<Yhdistelma> yhdistelmat = new ArrayList<Yhdistelma>();

        for (int i = 0; i < 15; i++) { //final luokkamuuttujaksi
            yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.values()[i]));
        }

        return yhdistelmat;
    }

    @Override
    public int annaKierroksienMaara() {
        return 15; //final luokkamuuttujaksi
    }

}

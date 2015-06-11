package sovelluslogiikka.peli;

import java.util.ArrayList;

/**
 * Pakkojatsi luokka tarjoaa pakkojatsi pelimuunnelman logiikan. Tietää noppien
 * lukumäärän, pelattavat yhdistelmat ja oaaa laskea pisteet yhdistelmalle sekä
 * kokonaispisteet pakkojatsin säännöin. Jos osa säännöistä sopii melkein
 * kaikille jatsin muunnelmille, niin voimme viedä sen default metodiksi
 * rajapintaan.
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

        if (kierroslkm >= 1 || kierroslkm <= 6) { //miten saisi if-hirviön korvattua...
            summa = summaaEsiintymat(lukemat, kierroslkm);
        }
        if (kierroslkm == 7) {
            //yksi pari
            summa = summaaParinLukemat(lukemat, 1); //pitää ottaa paras pari
        }
        if (kierroslkm == 8) {
            //kaksi paria
            summa = summaaParinLukemat(lukemat, 2); //pitää ottaa paras pari
        }
        if (kierroslkm == 9) {
            summa = summaaKolmosienLukemat(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }
        if (kierroslkm == 10) {
            summa = summaaNelosienLukemat(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }
        if (kierroslkm == 11) {
            summa = tarkistaSuora(lukemat, 1);
        }
        if (kierroslkm == 12) {
            summa = tarkistaSuora(lukemat, 2);
        }
        if (kierroslkm == 13) {
            //täyskäsi=yksi pari ja yksi kolmoisluku
            summa = tarkistaTayskasi(lukemat);
        }
        if (kierroslkm == 14) {
            summa = lukemat.get(0) + lukemat.get(1) + lukemat.get(2) + lukemat.get(3) + lukemat.get(4);
        }
        if (kierroslkm == 15) {
            summa = tarkistaJatsi(lukemat); //voisiko samalla metodilla hoitaa monta tutkintaa?
        }

        return summa;

    }

    /**
     * Apumetodi tayskäden löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     * @return pisteet
     */
    private int tarkistaTayskasi(ArrayList<Integer> lukemat) {

        int summa = 0, summa1 = 0, summa2 = 0;

        summa1 = summaaKolmosienLukemat(lukemat);

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {

                if (lukemat.get(i) == lukemat.get(j) && i == j - 1 && lukemat.get(i) != summa1 / 3) {

                    summa2 = lukemat.get(i) + lukemat.get(j);

                }

            }

        }
        if (summa1 != 0 && summa2 != 0) {
            summa = summa1 + summa2;
        }

        return summa;
    }

    /**
     * Apumetodi 1-6 lukemien laskemiseen
     *
     * @param lukemat
     * @param kierroslkm
     *
     * @return pisteet
     */
    private int summaaEsiintymat(ArrayList<Integer> lukemat, int kierroslkm) {
        int summa = 0;
        for (Integer luku : lukemat) {

            if (luku == kierroslkm) {
                summa = summa + luku;
            }

        }
        return summa;
    }

    /**
     * Apumetodi yhden tai kahden parin löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     * @param parilkm
     *
     * @return pisteet
     */
    private int summaaParinLukemat(ArrayList<Integer> lukemat, int parilkm) {

        int summa = 0, summa1 = 0, summa2 = 0;
        boolean loppu = false;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {

                if (lukemat.get(i) == lukemat.get(j) && i == j - 1) {

                    summa1 = lukemat.get(i) + lukemat.get(j);
                    loppu = true;

                }
                if (loppu) {
                    break;
                }
            }

        }

        for (int k = 0; k < lukemat.size(); k++) {

            for (int l = 1; l < lukemat.size(); l++) {

                if (lukemat.get(k) == lukemat.get(l) && k == l - 1) { //pitää ottaa parhaimman parin pisteet jos esim. 46611

                    if (lukemat.get(k) + lukemat.get(l) != summa1) {
                        summa2 = lukemat.get(k) + lukemat.get(l);
                    }
                }

            }

        }

        if (parilkm == 1 && summa1 >= summa2) {
            summa = summa + summa1;
        }
        if (parilkm == 1 && summa1 < summa2) {
            summa = summa + summa2;
        }
        if (parilkm == 2 && summa1 != summa2 && summa1 != 0 && summa2 != 0) {
            summa = summa1 + summa2;
        }

        return summa;
    }

    /**
     * Apumetodi kolmen saman lukeman löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     * @return pisteet
     */
    private int summaaKolmosienLukemat(ArrayList<Integer> lukemat) {

        int summa = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {
                for (int k = 1; k < lukemat.size(); k++) {

                    if (lukemat.get(i) == lukemat.get(j) && i == j - 1 && lukemat.get(j) == lukemat.get(k) && j == k - 1) {

                        summa = lukemat.get(i) + lukemat.get(j) + lukemat.get(k);

                    }

                }
            }

        }

        return summa;
    }

    /**
     * Apumetodi neljän saman lukeman löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     *
     * @return pisteet
     */
    private int summaaNelosienLukemat(ArrayList<Integer> lukemat) {

        int summa = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            for (int j = 1; j < lukemat.size(); j++) {
                for (int k = 1; k < lukemat.size(); k++) {

                    for (int l = 1; l < lukemat.size(); l++) {

                        if (lukemat.get(i) == lukemat.get(j) && i == j - 1 && lukemat.get(j) == lukemat.get(k) && j == k - 1 && lukemat.get(k) == lukemat.get(l) && k == l - 1) {

                            summa = lukemat.get(i) + lukemat.get(j) + lukemat.get(k) + lukemat.get(l);

                        }

                    }
                }
            }

        }

        return summa;
    }

    /**
     * Apumetodi pienen ja suuren suoran löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     * @param aloitusluku
     * @return pisteet
     */
    private int tarkistaSuora(ArrayList<Integer> lukemat, int aloitusluku) {
        int summa = 0;
        int lkm = 0;

        for (int i = 0; i < lukemat.size(); i++) {

            if (lukemat.get(i) == i + aloitusluku) {
                lkm++;
            }

        }

        if (aloitusluku == 1 && lkm == 5) {
            summa = 15;
        }
        if (aloitusluku == 2 && lkm == 5) {
            summa = 20;
        }
        return summa;
    }

    /**
     * Apumetodi jatsin löytämiseen ja pisteiden laskentaan
     *
     * @param lukemat
     *
     * @return pisteet
     */
    private int tarkistaJatsi(ArrayList<Integer> lukemat) {
        int summa = 0;
        int lkm = 0;

        int verrattava = lukemat.get(0);
        for (int i = 1; i < lukemat.size(); i++) {

            if (lukemat.get(i) == verrattava) {
                lkm++;
            }

        }

        if (lkm == 4) {
            summa = 50;
        }
        return summa;
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

        for (int i = 0; i < 6; i++) {
            summa1 = summa1 + yhdistelmat.get(i).annaPisteet();
        }
        for (int i = 6; i < 15; i++) { //final luokkamuuttujaksi
            summa2 = summa2 + yhdistelmat.get(i).annaPisteet();
        }
        if (summa1 >= 63) {
            summa1 = summa1 + 50;
        }

        return summa1 + summa2;
    }

    /**
     * Metodi joka osaa luoda tarvittavan määrän sopia noppia pakkojatsia varten
     * Voisi luoda myös tarvittaessa muitakin kuin 6 kulmaisia noppia
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

    /**
     * Metodi joka kertoo meneillään olevan kierroksen
     *
     * @return 
     */
    @Override
    public int annaKierroksienMaara() {
        return 15; //final luokkamuuttujaksi
    }

}

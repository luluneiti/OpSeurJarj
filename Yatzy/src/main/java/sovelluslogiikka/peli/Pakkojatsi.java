package sovelluslogiikka;

import java.util.ArrayList;

/**
 * Pakkojatsi luokka tarjoaa pakkojatsi pelimuunnelman logiikan. Tietää noppien
 * lukumäärän, pelattavat yhdistelmat ja oaaa laskea pisteet yhdistelmalle sekä  
 * kokonaispisteet pakkojatsin säännöin
 * Jos osa säännöistä sopii melkein kaikille jatsin muunnelmille, niin voimme viedä sen default metodiksi rajapintaan
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
        for (Integer luku : lukemat) { 

            if (kierroslkm == 1) { //MITEN SAISI IF HIRVIÖT KORVATTUA...
                if (luku == 1) {
                    summa++;
                }
            }
            if (kierroslkm == 2) {
                if (luku == 2) {
                    summa++;
                }
            }
            if (kierroslkm == 3) {
                if (luku == 3) {
                    summa++;
                }
            }
            if (kierroslkm == 4) {
                if (luku == 4) {
                    summa++;
                }
            }
            if (kierroslkm == 5) {
                if (luku == 5) {
                    summa++;
                }
            }
            if (kierroslkm == 6) {
                if (luku == 6) {
                    summa++;
                }
            }
            if (kierroslkm == 7) {
                //oma metodi
            }
            if (kierroslkm == 8) {
                //oma metodi
            }
            if (kierroslkm == 9) {
                //oma metodi
            }
            if (kierroslkm == 10) {
                //oma metodi
            }
            if (kierroslkm == 11) {
                //oma metodi
            }
            if (kierroslkm == 12) {
                //oma metodi
            }
            if (kierroslkm == 13) {
                //oma metodi
            }
            if (kierroslkm == 14) {
                //oma metodi
            }
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

        switch (kierroslkm) {
            case 1:
                return YhdistelmanNimi.ykkoset;
            case 2:
                return YhdistelmanNimi.kakkoset;
            case 3:
                return YhdistelmanNimi.kolmoset;
            case 4:
                return YhdistelmanNimi.neloset;
            case 5:
                return YhdistelmanNimi.viitoset;
            case 6:
                return YhdistelmanNimi.kuutoset;
            case 7:
                return YhdistelmanNimi.yksiPari;
            case 8:
                return YhdistelmanNimi.kaksiParia;
            case 9:
                return YhdistelmanNimi.kolmoisluku;
            case 10:
                return YhdistelmanNimi.neloisluku;
            case 11:
                return YhdistelmanNimi.pieniSuora;
            case 12:
                return YhdistelmanNimi.suuriSuora;
            case 13:
                return YhdistelmanNimi.tayskasi;
            case 14:
                return YhdistelmanNimi.sattuma;
            case 15:
                return YhdistelmanNimi.yatzy;
            default:
                return YhdistelmanNimi.eiTiedossa;
        }
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
        for (int i = 7; i < 14; i++) {
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
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.ykkoset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.kakkoset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.kolmoset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.neloset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.viitoset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.kuutoset));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.yksiPari));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.kaksiParia));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.kolmoisluku));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.neloisluku));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.pieniSuora));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.suuriSuora));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.tayskasi));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.sattuma));
        yhdistelmat.add(new Yhdistelma(YhdistelmanNimi.yatzy));

        return yhdistelmat;
    }

    @Override
    public int annaKierroksienMaara() {
        return 15;
    }

}

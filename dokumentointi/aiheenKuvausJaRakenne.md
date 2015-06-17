**Aihe: **
Toteutetaan Yatzy/jatsi peli, jota voi pelata yksi tai useampi henkilö. Yatzy on viidellä nopalla pelattava peli. Pelin tarkoituksena on saavuttaa noppia heittämällä mahdollisimman monta erilaista lukujen yhdistelmää. Yhdistelmien tulos merkitään pöytäkirjaan. Pöytäkirjan täytyttyä voittaja on se pelaaja, jolla on korkeimmat pisteet. Pelatessaan yksin pelaaja voi yrittää rikkoa entisen henkilökohtaisen ennätyksensä. Pelistä toteutetaan ns. pakkoyatzy versio, jossa pöytäkirjaa täytetään järjestyksessä ylhäältä alas, eli pelaaja ei voi valita pelattavaa yhdistelmää.

**Käyttäjät:** 
Yksi tai useampi pelaaja 

**Toiminnot:**
•Sovellus lukee pelaajien tiedot tiedostosta pelin käynnistyessä.
•Sovellus avaa ensin pelaajien hallinnan näytön, jolla voidaan lisätä uusia pelaajia ja valita listalta seuraavan pelin pelaajat. 
•Seuraavana sovellus avaa pelin hallinnan näytön, johon jokaiselle pelaajalla on lisätty oma nopparivi.
•Pelaajat voivat itse päättää missä järjestyksessä heittävät noppiansa.
•Pelaaja voi vuorollaan heittää noppia maksimissaan kolme kertaa. Pelaajan ei tarvitse käyttää kolmea heittokertaa. Ensimmäisellä heittokerralla pelaajan on heitettävä kaikki nopat. Seuraavalla kahdella heittokerralla pelaaja päättää itse mitä pelaajan noppaa tai noppia heitetään. Kun pelaaja päättää heittovuoronsa, yhdistelmän tulos näytetään nopparivin lopussa.
•Sovellus näyttää pelattavan kierroksen ja yhdistelmän sekä antaa pelaajille tietoa siitä, mitä seuraavaksi tulee tehdä.
•Kun kaikki yhdistelmät on käyty läpi, sovellus laskee pelaajan/pelaajien kokonaispisteet ja ilmoittaa kuka pelaajista voitti ja jos jokun pelaajista rikkoi entisen henkilökohtaisen ennätyksensä. 
•Sovellus tallettaa pelaajien ennätyspisteet ja kirjoittaa pelaajien tiedot tiedostoon.

**Rakenne: **
Ohjausluokka (JatsiOhjain) tuntee sekä käyttölittymän (GraafinenJatsi) että pelaajia hallinnoivan luokan (PelaajienHallinta) ja peliä hallinnoivan luokan (PelinHallinta). Ohjausluokka luo edellä mainituista ilmentymän ja pyytää käyttöliittymää rekisteröimään ohjausluokan. Kun käyttäjä esim. klikkaa painiketta käyttöliittymässä, pyyntö ohjautuu käyttöliittymästä ohjausluokalle, joka taas kutsuu PelaajienHallinta ja PelinHallinta luokkien palveluita. 

PelaajienHallinta luokka lukee pelaajien tiedot (kaikki pelaajat) tiedostosta ja kirjoittaa lopussa pelaajen tiedot tiedostoon. Se käyttää tiedoston käsitelyssä TiedostonHallinta luokan palveluita. Pelaajat -tiedosto sijaitsee Yatzy -kansion juuressa. Pelin alussa voidaan luoda myös uusi pelaajia. 

PelinHallinta on riippuvainen PelaajienHallinnasta, koska ennen pelin käynnistystä tulee valita pelaajat aloitettavaa peliä varten. PelinHallinta huolehtii, että valituille pelaajille luodaan nopat ja yhdistelmät. Noppien ja yhdistelmien luonnissa se käyttää PeliMuunnelman toteuttavan luokan, Pakkojatsin, palveluita. Pakkojatsi tuntee pakkojatsi-pelimuunnelman pelattavat yhdistelmät, noppien määrät ja pisteiden laskusäännöt. PelinHallinta käyttää myös Noppa luokan palveluita, kun pelaajan noppia heitetään. Noppa olio pitää myös kirjaa siitä, että kuinka monta kertaa sitä on heitetty ja PelinHallinta hyödyntää tätä kun tutkitaan, onko pelaaja heittänyt jos vuoronsa kaikki 3 heittoa.


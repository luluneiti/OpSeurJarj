
**Luokkien testaus:**
* GraafinenJatsi luokalla ei ole automaattisia testejä. Se on testattu käsin erilaisten testitapausten avulla. 
  * painikeiden aktivoituminen ja passivoituminen tietyissä tilanteissa
  * erilaiset virhetilanteet, niistä toipuminen  ja niistä aiheutuvat ilmoitukset
  * voittajan/voittajien tietojen näyttäminen ja erikoistilanteet kuten tasapeli tai yksinpeli
  * ennätyksen tekijöiden tietojen näyttäminen (yksi, useampi, ei kukaan)
  * poistuminen pelaajan hallinta näytöltä ilman pelaamista
  * poistuminen peli näytöltä kesken pelin
  * pisteiden laskun oikeellisuuden varmistaminen
  * nopan lukemien vaihtuminen eri heittokerroilla
  * jne.
* Muilla luokilla on automaattiset testit ja osalla luokista monta testiluokkaa. Ohjausluokan testit ovat suppeammat, koska toiminnallisuutta on testattu niiden luokkien testeissä, jotka tarjoavat palveluja ohjausluokalla. 
  
**Virheet /ominaisuudet:**
* Jos peli ei pääty normaalisti, niin uusia pelaajia ei talleteta tiedostoon.
* 

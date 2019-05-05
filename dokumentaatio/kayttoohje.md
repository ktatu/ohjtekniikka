# Käyttöohje

Lataa uusimman [releasen](https://github.com/ktatu/ohjtekniikka/releases/tag/viikko7) tiedostot trainingLogApp.jar, config.properties sekä traininglog.mv.db ja sijoita ne kaikki samaan suoritushakemistoon.

Sovellusta voi Linux-ympäristön lisäksi käyttää myös Windowsilla, esim. <a href="https://gitforwindows.org/">tämän</a> avulla Bashista tuttujen komentojen käyttäminen sujuu helposti.

## Konfigurointi

Sovellus hyödyntää <a href="https://www.h2database.com/html/main.html">h2-tietokantaa</a> lokien paikalliseen tallennukseen. Varmista, että tämä on koneellasi asennettuna.

Java-versiot joilla .jar on testatusti toiminut: 
- <a href="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/windowsjava.png">Windows 10</a>
- <a href="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/ubuntujava1.png">Fuksiläppäri Ubuntu 18.04</a>

## Ohjelman käynnistys

Ohjelma käynnistyy suoritushakemistossa, komentoriviltä komennolla java -jar trainingLogApp.jar

## Sisäänkirjautuminen ja käyttäjätunnusten luonti

<p>Sovelluksen ensimmäisessä näkymässä käyttäjä voi luoda uudet käyttäjätunnukset ja kirjautua sisään.</p> 
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/login-n%C3%A4kym%C3%A42.png">
<p>Syötä haluamasi käyttäjänimi sekä salasana ja paina "Create new user". Mikäli käyttäjätunnusten luonti onnistuu, voit kirjautua sisään painamalla "Login". Muussa tapauksessa korjaa näkymään ilmestyvän virheilmoituksen vika, jotta voit luoda käyttäjätunnukset.</p>

## Uusi loki (New Log)
<p>Sisäänkirjautumisen jälkeen ruudulle ilmestyy New Log-näkymä, jossa voit luoda uuden lokin. Yläpalkin napeista voit kuitenkin siirtyä History-näkymään tai kirjautua ulos.</p>
<p>Sovelluksen on tarkoitus toimia treenaamisen päiväkirjana, joten käyttäjä voi vain yhden lokin per päivä.</p>
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/newlog1.png">
Harjoitteita voi lisätä yksitellen kirjoittamalla harjoitteen nimi ja tehtyjen sarjojen ("Number of sets") määrä, jonka jälkeen painetaan "Add"-nappia.

<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/newlog2.png">
Syötä kunkin harjoitteen kenttiin tekemäsi sarjat. Mikäli teit virheen ja lisäsit enemmän sarjoja kuin oli tarkoitus, voit nollata näkymän painamalla yläpalkista "New Log". Kun haluat syöttää lokin, paina "Create log"-nappia.
                                                                                                                                 
## Lokin haku (History)
<p>Yläpalkin napista "History" voit siirtyä näkymään, jossa voit etsiä vanhoja lokejasi.</p>
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/history1.png">
Voit etsiä lokia joko painamalla kalenterin näköistä kuvaketta tekstikentän oikealla puolella ja etsimällä oikean päivämäärän tai syöttämällä sen itse ja painamalla "Search". Muista kuitenkin noudattaa näkymässä osoitettua päivämäärän formaattia.
<br>
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/kayttoohje-kuvat/history2.png">
<p>Etsimäsi loki tulee näkyviin jos se on olemassa. Näkymään ilmestyy viesti, jos päivämääräformaatti oli virheellinen tai kyseiselle päivälle ei löydy kirjautuneelta käyttäjältä lokia.</p>

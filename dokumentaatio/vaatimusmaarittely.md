# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat pitää kirjaa kuntosaliharjoittelustaan harjoittelulokeilla. Lokeista ilmenee harjoitteet, sarjat ja käytetyt painot / vastukset.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta eri näkymästä.

<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/loginnakyma.png">
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/newlog.png">
<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/history.png">
Sovelluksen käynnistyksen jälkeen aukeaa kirjautumissivu, jossa voi myös luoda uuden käyttäjän. Varsinainen kirjautumisen jälkeinen sovellus koostuu kahdesta pääsivusta: "New Log" ja "History".

## Toiminnallisuudet

### Etusivu (kirjautuminen)

- Sisäänkirjautuminen etusivun lomakkeella
  - Virheilmoitus jos tunnuksia ei löydy

- Uusien tunnusten luonti
  - Uniikki

### New Log

- Käyttäjä voi luoda uuden treenilokin
  - Luotavaan lokiin käyttäjä kirjoittaa päivämäärän ja harjoitteet
  - Harjoitteet ovat riveittäin. Kukin kohta koostuu nimestä ja tehdyistä sarjoista, jotka voi antaa vaikka muodossa paino x toistot. Esim. "Penkkipunnerrus: 50x10, 60x5, 70x3"

- Käyttäjä voi lisätä uusia harjoiterivejä lokiin nappia painamalla

- Käyttäjä voi kirjautua ulos painamalla ylävalikon nappia "Logout"

- Käyttäjä voi lisätä laatimansa lokin painamalla nappia "Create log"

- Käyttäjä voi etsiä vanhoja harjoituslokejansa siirtymällä ylävalikosta välilehteen "History".

### History

- Käyttäjä voi etsiä vanhoja harjoituslokejansa päivämäärän perusteella
  - Käyttäjä voi hakea vain itse luomiaan lokeja

- Etsitty loki tulostuu sivulle
  - Sivulle ilmestyy ilmoitus mikäli kyseiselle päivälle ei löydy lokia tietokannasta

- Käyttäjä voi kirjautua ulos ylävalikon napista "Logout"

- Käyttäjä voi palata "New Log" sivulle ylävalikon napilla

## Jatkokehitysideoita

- Käyttäjä voi hakea historiasta kuukauden lokit kerrallaan
  - Jokaisesta lokista päivämäärä, jota painamalla kyseinen loki avautuu

- Käyttäjä voi hakea tiettyä harjoitetta historiasta jollakin aikavälillä
  - Ilmestyy lista lokeista päivämäärinä

- Käyttäjä voi luoda valmiita loki-templateja, jotka voi nopeasti avata "New Log"-välilehdessä
  - Templateissa valmiina harjoitteet ja/tai painot
  - Käyttäjä voi määrittää tietylle viikonpäivälle automaattisesti avautuvan templaten

- Stats-välilehdessä käyttäjä voi seurata kehitystänsä (esim. harjoitevastuksen kasvu tietyssä liikkeessä)


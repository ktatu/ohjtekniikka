# Arkkitehtuurikuvaus

## Käyttöliittymä

Sovellus koostuu kolmesta eri näkymästä:
- Sisäänkirjautuminen ja tunnusten luonti
- New Log
- History

Toteutuksen tasolla sovellus kuitenkin koostuu vain kahdesta eri scenestä, loginScene ja mainScene. Molemmat sijaitsevat pääluokassa TrainingLogUi, mutta New Login ja Historyn toteuttavat komponentit ovat omina aliluokkinaan LogScreen ja HistorySceen. 

Sisäänkirjautuessa näkymänä on loginScene. Kirjautumisen jälkeen sceneksi vaihtuu mainScene, jolle on asetettu New Log-näkymän toteuttavan luokan LogScreenin node (Parent) getLogView. MainScenen nodet vaihtuvat yläpalkin napeilla New Log ja History. Takaisin loginSceneen pääsee napilla Logout.

Käyttöliittymä on eriytetty täysin sovelluslogiikasta, eikä siihen sisälly muuta kuin käyttäjäsyötteen keräys ja näkymienn visuaalinen toteutus.

## Sovelluslogiikka
![Pakkauskaavio](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/package.png)

Sovelluslogiikasta vastaa luokka TrainingLogService, joka sisältää metodit kaikille käyttäjiin (User) sekä lokeihin (Log) liittyviin toimintoihin. Apuna TrainingLogServicelle domainissa on Validation, jossa on metodeja ui:sta saadun käyttäjäsyötteen oikeellisuuden varmistamiselle.

Käyttäjiin tai lokeihin liittyvää tietoa haettaessa tai tallennettaessa TrainingLogService kutsuu sovelluksen käynnistyksen yhteydessä sille parametrein syötettyjä luokkia UserDao ja LogDao.

## Päätoiminnot
### Käyttäjätunnusten luonti
Sovellukseen ensimmäisenä avautuvassa login-näkymässä käyttäjä voi sekä kirjautua että luoda uudet tunnukset.

![CreateUser](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Tunnusten%20luominen.png)

<p>"Create new user" -nappia painettaessa TrainingLogUi kutsuu TrainingLogServicen metodia createUser, parametreinaan login-näkymän tekstikenttiin syötetyt käyttäjätunnus ja salasana. Ennen käyttäjätunnusten luontia käyttäjäsyötteen oikeellisuus varmistetaan luokassa Validation.</p> 
<p>Validoinnin jälkeen FileUserDaossa ensin haetaan käyttäjätunnusta, jonka jälkeen tunnus ja salasana kirjoitetaan tekstitiedostoon mikäli sitä ei ennestään löydy. Onnistunut käyttäjätunnusten luonti palauttaa TrainingLogServicelle totuusarvon true, joka puolestaan palauttaa TrainingLogUi:lle merkkijonon "Registration succesful", joka ilmestyy näkymään.</p>

### Uusi loki
Sisäänkirjautumisen jälkeisessä näkymässä käyttäjä voi luoda uuden lokin kyseiselle päivälle.
![NewLog](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Uusi%20loki.png)

<p>Käyttäjä antaa parametreina kullekin harjoitteelle nimen sekä sarjat ja painot ("setData") ja painaa "New Log"-nappia. TrainingLogService kutsuu Validationia harjoitteiden nimien validoimiseksi, jonka jälkeen harjoitedata formatoidaan merkkijonoksi tietokantaan tallennusta varten. Uusi Log-olio talletetaan SQLLogDaossa paikalliseen tietokantatiedostoon. SQLLogDao palauttaa TrainingLogServicelle totuusarvon true, ja tämä puolestaan palauttaa TrainingLogUi:lle viestin "New log created", joka ilmestyy näkymään.

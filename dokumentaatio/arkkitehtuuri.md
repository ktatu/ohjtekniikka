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

### Sisäänkirjautuminen
![login](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Sis%C3%A4%C3%A4nkirjautuminen.png)

<p>"Login" -nappia painettaessa Ui kutsuu TrainingLogServicen metodia searchUser parametreinaan login-näkymään syötetyt käyttäjänimi ja salasana. TrainingLogService luo syötteestä uuden User-olion ja kutsuu LogDaon searchUser-metodia. Käyttäjätunnuksen löytyessä searchUser palauttaa TrainingLogServiceen totuusarvon true. TrainingLogServicessä luokkamuuttujan currentUser arvoksi tulee parametrina annettu käyttäjänimi ja ui:lle palautuu true merkiksi tunnusten löytymisestä. Ui:ssa sceneksi vaihtuu mainScene, jossa on ensimmäisenä New Log-näkymä.</p>
 
### Uusi loki (New Log)
Sisäänkirjautumisen jälkeisessä näkymässä käyttäjä voi luoda uuden lokin kyseiselle päivälle.
![NewLog](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Uusi%20loki.png)

<p>Käyttäjä antaa parametreina kullekin harjoitteelle nimen sekä sarjat ja painot ("setData") ja painaa "New Log"-nappia. TrainingLogService kutsuu Validationia harjoitteiden nimien validoimiseksi, jonka jälkeen harjoitedata formatoidaan merkkijonoksi tietokantaan tallennusta varten. Uusi Log-olio talletetaan SQLLogDaossa paikalliseen tietokantatiedostoon. SQLLogDao palauttaa TrainingLogServicelle totuusarvon true, ja tämä puolestaan palauttaa TrainingLogUi:lle viestin "New log created", joka ilmestyy näkymään.</p>
 
### Lokin haku (History)
Yläpalkin napista History avautuvassa näkymässä käyttäjä voi etsiä vanhoja lokejaan.
![History](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Lokin%20haku%20(1).png)

<p>Käyttäjä joko valitsee kalenterista tai kirjoittaa palkkiin päivämäärän ja painaa Search-nappia. Syötetty päivämäärä tarkistetaan suoraan Validation luokan validateDate-metodilla. TrainingLogServicessä kutsutaan LogDaon metodia searchLog-metodia, jonka parametri 'currentUser' saadaan TrainingLogServicen yksityisestä luokkamuuttujasta currentUser. LogDaossa tietokantatiedostosta löytyneestä rivistä luodaan uusi Log-olio, joka palautuu TrainingLogServicen searchLog-metodille. Apumetodi formatLogForUi tekee datasta merkkijonolistan joka palautuu ui:lle ja tulee näkyville.</p>

## Tietojen pysyväistalletus
Talletuksesta vastaavat dao-paketin luokat SQLLogDao ja FileUserDao. SQLLogDao hyödyntää paikallista h2-tietokantatiedostoa ja FileUserDao paikallista tekstitiedostoa. Molempien tiedostojen nimet määritellään tiedostossa config.properties.

Käytettävän h2-tietokantatiedoston voi itse halutessaan muuttaa config.properties-tiedostossa, mutta sovellus olettaa, että tietokannassa on taulu lokeille. Jos siis haluat käyttää omaa tiedostoasi, luo seuraava taulu:
<pre>
CREATE TABLE Log (creationDate DATE, username VARCHAR(30), data VARCHAR(150), 
PRIMARY KEY (creationDate, username));
</pre>

Uutta lokia luodessa creationDate on aina senhetkinen päivämäärä ja käyttäjänimi sisäänkirjautunut käyttäjä. Tietokantaa varten harjoitteisiin liittyvä syötedata formatoidaan muotoon 
<pre>
harjoite1:sarja1;sarja2;sarja3;harjoite2:sarja1;sarja2;sarja3;
</pre>
Käytännön esimerkkinä tämä voisi siis olla vaikka 
<pre>
Kyykky:5x80;3:90:1x100;Pystypunnerrus:5x40:3x45;
</pre>
<br>
Käyttäjätunnukset tallentuvat tekstitiedostoon muodossa
<pre>
kayttajanimi,salasana
</pre>

# Arkkitehtuurikuvaus

## Luokkakaavio
![Luokkakaavio](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Luokkakaavio.png)

## Päätoiminnot
### Käyttäjätunnusten luonti
Sovellukseen ensimmäisenä avautuvassa login-näkymässä käyttäjä voi sekä kirjautua että luoda uudet tunnukset.

![CreateUser](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Tunnusten%20luominen.png)

<p>"Create new user" -nappia painettaessa TrainingLogUi kutsuu TrainingLogServicen metodia createUser, parametreinaan login-näkymän tekstikenttiin syötetyt käyttäjätunnus ja salasana. Ennen käyttäjätunnusten luontia käyttäjäsyötteen oikeellisuus varmistetaan luokassa Validation.</p> 
<p>Validoinnin jälkeen FileUserDaossa ensin haetaan käyttäjätunnusta, jonka jälkeen tunnus ja salasana kirjoitetaan tekstitiedostoon mikäli sitä ei ennestään löydy. Onnistunut käyttäjätunnusten luonti palauttaa TrainingLogServicelle totuusarvon true, joka puolestaan palauttaa TrainingLogUi:lle merkkijonon "Registration succesful", joka ilmestyy näkymään.</p>

### Uusi loki
Sisäänkirjautumisen jälkeisessä näkymässä käyttäjä voi luoda uusia lokeja.
![NewLog](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Uusi%20loki.png)

<p>Käyttäjä antaa parametreina kullekin harjoitteelle nimen sekä sarjat ja painot ("setData") ja painaa "New Log"-nappia. TrainingLogService kutsuu Validationia harjoitteiden nimien validoimiseksi, jonka jälkeen harjoitedata formatoidaan merkkijonoksi tietokantaan tallennusta varten. Uusi Log-olio talletetaan SQLLogDaossa paikalliseen tietokantatiedostoon. SQLLogDao palauttaa TrainingLogServicelle totuusarvon true, ja tämä puolestaan palauttaa TrainingLogUi:lle viestin "New log created", joka ilmestyy näkymään.

# Arkkitehtuurikuvaus

## Luokkakaavio
![Luokkakaavio](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Alustava%20luokkakaavio.png)

## Päätoiminnot
### Käyttäjätunnusten luonti
Sovellukseen ensimmäisenä avautuvassa login-näkymässä käyttäjä voi sekä kirjautua että luoda uudet tunnukset.

![CreateUser](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Tunnusten%20luominen%20(onnistuu)%20(1).png)

<p>"Create new user" -nappia painettaessa TrainingLogUi kutsuu TrainingLogServicen metodia createUser, parametreinaan login-näkymän tekstikenttiin syötetyt käyttäjätunnus ja salasana. Ennen käyttäjätunnusten luontia käyttäjäsyötteen oikeellisuus varmistetaan luokassa Validation.</p> 
<p>Validoinnin jälkeen FileUserDaossa ensin haetaan käyttäjätunnusta, jonka jälkeen tunnus ja salasana kirjoitetaan tekstitiedostoon mikäli sitä ei ennestään löydy. Onnistunut käyttäjätunnusten luonti palauttaa TrainingLogServicelle totuusarvon true, joka puolestaan palauttaa TrainingLogUi:lle merkkijonon "Registration succesful", joka ilmestyy näkymään.</p>

### Uusi loki

![NewLog](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/Uusi%20loki.png)


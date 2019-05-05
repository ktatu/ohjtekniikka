# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitu sovelluslogiikan testaus tapahtuu <a href="https://github.com/ktatu/ohjtekniikka/blob/master/trainingLog/src/test/java/traininglog/domain/TrainingLogServiceTest.java">TrainingLogServiceTestissä.</a> Testiluokka vastaa sekä käyttäjiin että lokeihin kohdistuvista metodeista kuten sovelluksessakin.

TrainingLogServiceTest käyttää apuna pysyväistalletukseen liittyvissä metodeissa luokkia FakeUserDao ja FakeLogDao, jotka simuloivat todellisten UserDaon ja LogDaon toiminnallisuuksia tallentamatta mitään.

TrainingLogService hyödyntää luokkaa Validation käyttäjäsyötteen validoimiseen, joten virhesyötteisiin liittyvä testaus sijaitsee luokassa <a href="https://github.com/ktatu/ohjtekniikka/blob/master/trainingLog/src/test/java/traininglog/domain/ValidationTest.java">ValidationTest.</a>

### DAO-luokat

DAO-paketin luokkien testaus tapahtuu tilapäistiedostoilla, jotka alustetaan ennen testien suoritusta ja poistetaan niiden päätyttyä. FileUserDaoTestissä luodaan tekstitiedosto ja SQLLogDaoTestissä .mv.db -päätteinen h2-tietokantatiedosto, aivan kuten varsinaisessa sovelluksessakin.

## Testikattavuus

Testien rivikattavuus on 95 % ja haaraumakattavuus 97 %. traininglog.ui-paketin luokat eivät ole junit-testattuja.

<img src="https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kuvat/testikattavuus.png">

Testikattavuusraportissa punaiselle jäivät:
- domainissa:
  - TrainingLogServicen createLog-metodin Validation-luokan kutsu. Kutsuttava metodi kuitenkin testataan ValidationTest-testiluokassa
  - Saman metodin poikkeuskäsittely
  - TrainingLogServicen logout-metodi, joka muuttaa yksityisen muuttujan "currentUser" nulliksi
  - User-luokan hashCode
- daossa:
  - Poikkeuskäsittelyt jotka palauttavat false
  - SQLLogDaon searchLog-metodi (vaikka näkyy vihreänä), siitä alempana lisää
  
## Järjestelmätestaus


## Ongelmat

- dao-luokan searchLog-metodin automatisoitu testaus ei onnistunut. Kun metodin piti palauttaa tietokantatiedostosta haetun datan perusteella luotu Loki-olio niin palautukseksi tuli aina null, vaikka oikea rivi oli löytynyt ja Loki-olio luotu. Lokin toString SQLLogDaossa kuitenkin tulosti sen mitä pitikin

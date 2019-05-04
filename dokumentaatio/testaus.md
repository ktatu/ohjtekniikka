# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitu sovelluslogiikan testaus tapahtuu <a href="https://github.com/ktatu/ohjtekniikka/blob/master/trainingLog/src/test/java/traininglog/domain/TrainingLogServiceTest.java">TrainingLogServiceTestissä.</a> Testiluokka vastaa sekä käyttäjiin että lokeihin kohdistuvista metodeista kuten sovelluksessakin.

TrainingLogServiceTest käyttää apuna pysyväistalletukseen liittyvissä metodeissa luokkia FakeUserDao ja FakeLogDao, jotka simuloivat todellisten UserDaon ja LogDaon toiminnallisuuksia tallentamatta mitään.

TrainingLogService hyödyntää luokkaa Validation käyttäjäsyötteen validoimiseen, joten virhesyötteisiin liittyvä testaus sijaitsee luokassa <a href="https://github.com/ktatu/ohjtekniikka/blob/master/trainingLog/src/test/java/traininglog/domain/ValidationTest.java"ValidationTest.</a>

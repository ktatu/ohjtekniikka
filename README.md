# trainingLog
Sovelluksen avulla käyttäjät voivat pitää kirjaa kuntosaliharjoittelustaan. Sovellus toimii ikään kuin päiväkirjana, ja jokaisesta treenistä voi luoda uuden lokin.

## Dokumentaatio
[Käyttöohje](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/testaus.md)

## Releaset
[Viikko 5](https://github.com/ktatu/ohjtekniikka/releases/tag/viikko5)

[Viikko 6](https://github.com/ktatu/ohjtekniikka/releases/tag/viikko6)

## Komentoriviltä suoritettavat komennot

Kaikki komennot tulee suorittaa kansiossa trainingLog.

### Testit

Sovelluksen testaus

```
mvn test
```

Testikattavuusraportti. Raportin sijainti: /trainingLog/target/site/jacoco/index.html. Tiedoston voi avaita selaimella.

```
mvn test jacoco:report
```
### .jarin luominen

Suoritettava .jar-tiedosto. Sijainti: /trainingLog/target/trainingLogApp-1.0-SNAPSHOT.jar. HUOM! Hakemistossa on myös .jar-tiedosto jonka nimi alkaa sanalla "original". Valitse käyttöösi tiedosto, jonka nimestä tämä osuus puuttuu.

```
mvn package
```

### JavaDoc

JavaDocin luonti. Sijainti: /traininglog/target/site/apidocs. Tiedoston voi avata selaimella.

```
mvn javadoc:javadoc
```

### Checkstyle

Checkstyle-raportin sijainti: /traininglog/target/site/checkstyle.html. Tiedoston voi avata selaimella.

```
mvn jxr:jxr checkstyle:checkstyle
```

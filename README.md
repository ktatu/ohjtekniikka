# trainingLog
Sovelluksen avulla käyttäjät voivat pitää kirjaa kuntosaliharjoittelustaan. Sovellus toimii ikään kuin päiväkirjana, ja jokaisesta treenistä voi luoda uuden lokin.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/ktatu/ohjtekniikka/blob/master/dokumentaatio/tuntikirjanpito.md)

### Päivitys osa 4:
Uusien lokien luominen New Log-näkymässä "toimii", mutta tietoja ei vielä tallennu mihinkään. Alustavasti ideana olisi käyttää h2-tietokantaa tätä varten. Domainissa luokka Validation käyttäjäsyötteen varmennusta varten.

Testejä on lisää, mutta TextFieldin käyttäminen testiluokissa aiheuttaa erroria, joten kattavuus ei taida olla halutulla tasolla. En siis kuitenkaan testaa käyttöliittymää, vaan tuon TextFieldin validation-luokkaan, jossa sisältö tarkastetaan.

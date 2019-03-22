/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ktatu
 */
public class KassapaateTest {
    
    Kassapaate testiPaate = new Kassapaate();
    Maksukortti kortillaRahaa = new Maksukortti(10000);
    Maksukortti vahanRahaa  = new Maksukortti(100);
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kassaPaateAlustettuOikein() {
        assertEquals(100000, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void lounaitaEiMyyty() {
        assertEquals(0, testiPaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKassaanTuleeRahaaKateisostolla () {
        testiPaate.syoEdullisesti(240);
        
        assertEquals(100240, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKassaPalauttaaVaihtorahanKateisostosta () {
        assertEquals(60, testiPaate.syoEdullisesti(300));
    }
    
    @Test
    public void syoMaukkaastiKassaanTuleeRahaaKateisostolla () {
        testiPaate.syoMaukkaasti(400);
        
        assertEquals(100400, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKassaPalauttaaVaihtorahanKateisostosta () {
        assertEquals(100, testiPaate.syoMaukkaasti(500));
    }
    
    @Test
    public void syoMaukkaastiRiittavaMaksuKasvattaaMyytyjenLounaidenMaaraa () {
        testiPaate.syoEdullisesti(500);
        testiPaate.syoMaukkaasti(500);
        
        assertEquals(1, testiPaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiRiittavaMaksuKasvattaaMyytyjenLounaidenMaaraa () {
        testiPaate.syoEdullisesti(500);
        testiPaate.syoMaukkaasti(500);
        
        assertEquals(1, testiPaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittamatonMaksuKassapaatteenSaldoEiMuutu() {
        testiPaate.syoEdullisesti(100);
        testiPaate.syoMaukkaasti(100);
        
        assertEquals(100000, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiRiittamatonMaksuRahatPalautetaan() {
        assertEquals(100, testiPaate.syoEdullisesti(100));
    }
    
    @Test
    public void syoMaukkaastiRiittamatonMaksuRahatPalautetaan() {
        assertEquals(100, testiPaate.syoMaukkaasti(100));
    }
    
    @Test
    public void syoEdullisestiRiittamatonMaksuLounastaEiMyyty() {
        testiPaate.syoEdullisesti(100);
        assertEquals(0, testiPaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiRiittamatonMaksuLounastaEiMyyty() {
        testiPaate.syoMaukkaasti(100);
        assertEquals(0, testiPaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKorttimaksuPalauttaaTrue() {
        assertTrue(testiPaate.syoEdullisesti(kortillaRahaa));
    }
    
    @Test
    public void syoEdullisestiKorttimaksuPalauttaaFalse() {
        assertFalse(testiPaate.syoEdullisesti(vahanRahaa));
    }
    
    @Test
    public void syoMaukkaastiKorttimaksuPalauttaaTrue() {
        assertTrue(testiPaate.syoMaukkaasti(kortillaRahaa));
    }
    
    @Test
    public void syoMaukkaastiKorttimaksuPalauttaaFalse() {
        assertFalse(testiPaate.syoMaukkaasti(vahanRahaa));
    }
    
    @Test
    public void syoEdullisestiVeloitettuKortilta() {
        testiPaate.syoEdullisesti(kortillaRahaa);
        
        assertEquals(10000-240, kortillaRahaa.saldo());
    }
    
    @Test
    public void syoMaukkaastiVeloitettuKortilta() {
        testiPaate.syoMaukkaasti(kortillaRahaa);
        
        assertEquals(10000-400, kortillaRahaa.saldo());
    }
    
    @Test
    public void lounaatMyytyKorttiMaksulla() {
        testiPaate.syoEdullisesti(kortillaRahaa);
        assertEquals(1, testiPaate.edullisiaLounaitaMyyty());
        
        testiPaate.syoMaukkaasti(kortillaRahaa);
        assertEquals(1, testiPaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaSaldoEiMuutu() {
        testiPaate.syoEdullisesti(vahanRahaa);
        assertEquals(100, vahanRahaa.saldo());
        
        testiPaate.syoMaukkaasti(vahanRahaa);
        assertEquals(100, vahanRahaa.saldo());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaPalauttaaFalse() {
        assertFalse(testiPaate.syoEdullisesti(vahanRahaa));
        
        assertFalse(testiPaate.syoMaukkaasti(vahanRahaa));
    }
    
    @Test
    public void lounaitaEiMyytyKortillaVahanRahaa() {
        testiPaate.syoEdullisesti(vahanRahaa);
        assertEquals(0, testiPaate.edullisiaLounaitaMyyty());
        
        testiPaate.syoMaukkaasti(vahanRahaa);
        assertEquals(0, testiPaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiMaksuEiMuutaKassanRahaa() {
        testiPaate.syoEdullisesti(kortillaRahaa);
        assertEquals(100000, testiPaate.kassassaRahaa());
        
        testiPaate.syoMaukkaasti(kortillaRahaa);
        assertEquals(100000, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleKortinSaldoMuuttuu() {
        testiPaate.lataaRahaaKortille(kortillaRahaa, 100);
        
        assertEquals(10100, kortillaRahaa.saldo());
    }
    
    @Test
    public void lataaRahaaKortilleKassapaatteenSaldoMuuttuu() {
        testiPaate.lataaRahaaKortille(kortillaRahaa, 100);
        
        assertEquals(100100, testiPaate.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleNegatiivineneiMuutaSaldoja() {
        testiPaate.lataaRahaaKortille(kortillaRahaa, -100);
        assertEquals(10000, kortillaRahaa.saldo());
        assertEquals(100000, testiPaate.kassassaRahaa());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenToimii() {
        kortti.lataaRahaa(100);
        
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuKunOtetaanLiikaa () {
        kortti.otaRahaa(100);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    public void otaRahaaPalauttaaTrue () {
        
        kortti.lataaRahaa(1000);
        
        assertTrue(kortti.otaRahaa(100));
    }
    
    public void otaRahaaPalauttaaFalse() {
        
        assertFalse(kortti.otaRahaa(1000));
    }
}

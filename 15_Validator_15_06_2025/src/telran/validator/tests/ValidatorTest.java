package telran.validator.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.validator.tools.Validator.*;

class ValidatorTest {

    @Test
    void testCheckCreditCard(){
        assertTrue(checkCreditCard("12345678"));
        assertTrue(checkCreditCard("1234567890123456789"));
        assertTrue(checkCreditCard("1234567890123456"));
        assertFalse(checkCreditCard("1234567"));
        assertFalse(checkCreditCard("12345678901234567890"));
        assertFalse(checkCreditCard("12345678g0123456"));
        assertFalse(checkCreditCard("12345678_0123456"));
    }

    @Test
    void testCheckDateFormatEU(){
        //DD.MM.YYYY
        assertTrue(checkDateFormatEU("12.05.2010"));
        assertTrue(checkDateFormatEU("05.12.2025"));
        assertTrue(checkDateFormatEU("31.10.2025"));
        assertFalse(checkDateFormatEU("10.10.22"));
        assertFalse(checkDateFormatEU("10.1.2022"));
        assertFalse(checkDateFormatEU("1.10.2022"));
        assertFalse(checkDateFormatEU("01.13.2022"));
        assertFalse(checkDateFormatEU("01.00.2022"));
        assertFalse(checkDateFormatEU("00.10.2022"));
        assertFalse(checkDateFormatEU("32.10.2022"));
        assertFalse(checkDateFormatEU("30/10/2022"));

        assertFalse(checkDateFormatEU("2001.10.20"));
    }

    @Test
    void testCheckDateFormatUS(){
        //YYYY-MM-DD
        assertTrue(checkDateFormatUS("2024-01-31"));
        assertTrue(checkDateFormatUS("2007-12-01"));
        assertFalse(checkDateFormatUS("2007-1-01"));
        assertFalse(checkDateFormatUS("2007-01-32"));
        assertFalse(checkDateFormatUS("2007-11-1"));
        assertFalse(checkDateFormatUS("2007/11/01"));
        assertFalse(checkDateFormatUS("25-09-2022"));
    }
}

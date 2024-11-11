package it.unibo.deathnote;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.*; 

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDeathNote {

    private static final String MARIO_ROSSI = "Mario Rossi"; 
    private static final String LUCA_BIANCHI = "Luca Bianchi"; 

    private DeathNote deathNote = new DeathNoteImplementation(); 

    @Test
    void testRuleNumberEqualZero() {
        try {
            deathNote.getRule(0); 
            Assertions.fail("Indices cannot be equal to 0, but should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testRuleNumberNegative() {
        try {
            deathNote.getRule(-100); 
            Assertions.fail("Indices cannot be negative, but should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void noRulesEmptyOrNull() {
        for (String s: DeathNote.RULES) {
            assertNotNull(s);
            assertFalse(s.isBlank());
        }
    }

    @Test
    void testDeathNote() {
        assertFalse(deathNote.isNameWritten(MARIO_ROSSI)); 
        deathNote.writeName(MARIO_ROSSI);
        assertTrue(deathNote.isNameWritten(MARIO_ROSSI)); 
        assertFalse(deathNote.isNameWritten(LUCA_BIANCHI)); 
        assertFalse(deathNote.isNameWritten("")); 
    }

    @Test
    void testDeathCause4() {
        try {
            deathNote.writeDeathCause("Cancer");
            Assertions.fail("no name was written on the list");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(MARIO_ROSSI);
        assertTrue(deathNote.isNameWritten(MARIO_ROSSI)); 
        assertEquals("heart attack", deathNote.getDeathCause(MARIO_ROSSI));
        //second human
        deathNote.writeName(LUCA_BIANCHI);
        assertTrue(deathNote.isNameWritten(LUCA_BIANCHI)); 
        assertTrue(deathNote.writeDeathCause("karting accident")); 
        assertEquals("karting accident", deathNote.getDeathCause(LUCA_BIANCHI));
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deathNote.writeDeathCause("Cancer"); 
        assertNotEquals("Cancer", deathNote.getDeathCause(LUCA_BIANCHI));
    }

    void testDeathCause5() {
        try {
            deathNote.writeDetails("Details of death");
            Assertions.fail("no name was written on the list");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(MARIO_ROSSI);
        assertTrue(deathNote.isNameWritten(MARIO_ROSSI)); 
        assertTrue(deathNote.getDeathDetails(MARIO_ROSSI).isEmpty());
        deathNote.writeDeathCause("Fatigue");
        assertTrue(deathNote.writeDetails("ran for too long")); 
        assertEquals("ran for too long", deathNote.getDeathDetails(MARIO_ROSSI));
        //second human
        deathNote.writeName(LUCA_BIANCHI);
        assertTrue(deathNote.isNameWritten(LUCA_BIANCHI)); 
        try {
            sleep(6100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deathNote.writeDetails("try to modify details"); 
        assertNotEquals("try to modify details", deathNote.getDeathDetails(LUCA_BIANCHI));
    }
}

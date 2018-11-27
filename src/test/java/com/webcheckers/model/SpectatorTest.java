package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpectatorTest {

    private Spectator spectator;

    @BeforeEach
    void setup() {
        spectator = new Spectator("BILLY!");
    }

    @Test
    void constructorTest() {
        assertEquals(User.ViewMode.SPECTATOR.toString(), spectator.getViewMode());
    }

    @Test
    void toStringTest() {
        assertEquals("{Spectator: BILLY!}", spectator.toString());
    }

    @Test
    void equalsTest() {
        assertTrue(spectator.equals(spectator));

        Spectator squa = new Spectator("BILLY!");
        assertTrue(spectator.equals(squa));

        Player nope = new Player("Fred");
        assertFalse(spectator.equals(nope));
    }

}
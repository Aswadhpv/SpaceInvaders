package com.zetcode.sprite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlienTest {

    @Test
    public void testAlienMovesRight() {
        Alien alien = new Alien(100, 100);
        int initialX = alien.getX();

        alien.act(1);  // move right
        int newX = alien.getX();

        assertTrue(newX > initialX, "Alien should move right when direction is 1");
    }

    @Test
    public void testAlienMovesLeft() {
        Alien alien = new Alien(100, 100);
        int initialX = alien.getX();

        alien.act(-1);  // move left
        int newX = alien.getX();

        assertTrue(newX < initialX, "Alien should move left when direction is -1");
    }

    @Test
    public void testAlienInitialPosition() {
        Alien alien = new Alien(120, 150);
        assertEquals(120, alien.getX());
        assertEquals(150, alien.getY());
    }

    @Test
    public void testAlienVisibility() {
        Alien alien = new Alien(80, 90);
        assertTrue(alien.isVisible());

        alien.die();
        assertFalse(alien.isVisible());
    }
}

package com.zetcode.sprite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialPosition() {
        Player player = new Player();
        assertTrue(player.getX() >= 0);
        assertTrue(player.getY() >= 0);
    }

    @Test
    public void testPlayerMovesRight() {
        Player player = new Player();
        int startX = player.getX();

        player.setDx(2);  // simulate right movement
        player.act();
        int newX = player.getX();

        assertTrue(newX > startX, "Player should move right when dx > 0");
    }

    @Test
    public void testPlayerMovesLeft() {
        Player player = new Player();
        int startX = player.getX();

        player.setDx(-2);  // simulate left movement
        player.act();
        int newX = player.getX();

        assertTrue(newX < startX, "Player should move left when dx < 0");
    }

    @Test
    public void testPlayerStopsMoving() {
        Player player = new Player();
        player.setDx(0);
        int startX = player.getX();

        player.act();
        int newX = player.getX();

        assertEquals(startX, newX, "Player should not move when dx == 0");
    }
}

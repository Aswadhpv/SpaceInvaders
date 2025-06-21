package com.zetcode;

import com.zetcode.sprite.Shot;
import com.zetcode.sprite.Alien;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testGameWinCondition() {
        Board board = new Board();

        // Simulate all aliens destroyed
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.triggerUpdate();

        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }

    @Test
    public void testPlayerIsUpdated() {
        Board board = new Board();

        // Should not throw errors
        board.triggerUpdate();

        // Assuming player is not null
        assertNotNull(board.getPlayer());
    }

    @Test
    public void testAliensAreUpdated() {
        Board board = new Board();

        // Basic assumption test
        for (Alien alien : board.getAliens()) {
            assertNotNull(alien);
        }

        board.triggerUpdate();

        // Optional: Check alien visibility logic if applicable
        for (Alien alien : board.getAliens()) {
            assertTrue(alien.getX() >= 0);  // They moved logically
        }
    }

    @Test
    public void testShotIsUpdated() {
        Board board = new Board();

        int x = board.getPlayer().getX();
        int y = board.getPlayer().getY();
        board.getShot().setX(x);
        board.getShot().setY(y);
        board.getShot().makeVisible();

        int initialY = board.getShot().getY();

        board.triggerUpdate();
        int newY = board.getShot().getY();

        assertTrue(newY < initialY);
    }

    @Test
    public void testShotHitsAlien() {
        Board board = new Board();

        Alien target = board.getAliens().get(0);
        Shot shot = board.getShot();

        shot.setX(target.getX());
        shot.setY(target.getY());
        shot.setVisible(true);

        int deathsBefore = board.getDeaths();

        board.triggerUpdate();

        // Simulate drawing phase
        if (target.isDying()) {
            target.die();
        }

        assertFalse(target.isVisible(), "Alien should be invisible after being hit");
        assertFalse(shot.isVisible(), "Shot should disappear after hitting alien");
        assertEquals(deathsBefore + 1, board.getDeaths());
    }

    @Test
    public void testAlienInvasionTriggersGameOver() {
        Board board = new Board();

        // Simulate one alien reaching the ground
        Alien alien = board.getAliens().get(0);
        alien.setY(Commons.GROUND - Commons.ALIEN_HEIGHT + 1);  // just beyond threshold

        board.triggerUpdate();

        assertFalse(board.isInGame(), "Game should end when an alien invades");
        assertEquals("Invasion!", board.getMessage());
    }

    @Test
    public void testGameOverMessageOnlyAppearsWhenGameEnds() {
        Board board = new Board();

        // Initially the game is ongoing
        assertTrue(board.isInGame());

        // Force game over by setting win
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.triggerUpdate();

        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }
}

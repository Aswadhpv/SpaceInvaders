package com.zetcode.sprite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShotTest {

    @Test
    public void testShotInitialPosition() {
        int startX = 50;
        int startY = 100;
        Shot shot = new Shot(startX, startY);

        // Shot adjusts slightly when initialized
        assertTrue(shot.getX() > startX);  // H_SPACE added
        assertTrue(shot.getY() < startY);  // V_SPACE subtracted
    }

    @Test
    public void testShotDies() {
        Shot shot = new Shot(10, 10);
        shot.makeVisible();  // custom helper we added

        assertTrue(shot.isVisible());
        shot.die();
        assertFalse(shot.isVisible());
    }

    @Test
    public void testShotVisibilityOnCreate() {
        Shot shot = new Shot();
        assertTrue(shot.isVisible(), "Shot created with default constructor should be visible");
    }

    @Test
    public void testShotPositionSetterGetter() {
        Shot shot = new Shot();
        shot.setX(200);
        shot.setY(150);

        assertEquals(200, shot.getX());
        assertEquals(150, shot.getY());
    }
}

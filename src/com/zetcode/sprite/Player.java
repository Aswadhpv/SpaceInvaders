package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import com.zetcode.engine.Updatable;
import com.zetcode.graphics.Visual;

import javax.swing.ImageIcon;

public class Player implements Updatable {

    private final Visual visual;
    private int dx;
    private boolean dying;
    private final ImageIcon explosion = new ImageIcon("src/images/explosion.png");

    public Player() {
        visual = new Visual("src/images/player.png");
        visual.setX(GameConfig.Player.START_POS_X);
        visual.setY(GameConfig.Player.START_POS_Y);
    }

    @Override
    public void update() {
        int newX = visual.getX() + dx;

        if (newX >= GameConfig.Logic.PLAYER_MIN_X && newX <= GameConfig.Logic.PLAYER_MAX_X) {
            visual.setX(newX);
        }
    }

    public void explode() {
        visual.setImage(explosion.getImage());
        dying = true;
    }

    public boolean isDying() { return dying; }
    public boolean isVisible() { return visual.isVisible(); }
    public int getX() { return visual.getX(); }
    public int getY() { return visual.getY(); }
    public int getWidth() { return visual.getWidth(); }
    public int getHeight() { return visual.getHeight(); }
    public java.awt.Image getImage() { return visual.getImage(); }

    public void keyPressed(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_LEFT) {
            dx = -2;
        } else if (key == java.awt.event.KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_LEFT || key == java.awt.event.KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}

package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import javax.swing.ImageIcon;

public class Player extends Sprite {

    private int dx;
    private boolean dying;
    private final ImageIcon explosion = new ImageIcon("src/images/explosion.png");

    public Player() {
        loadImage("src/images/player.png");
        setX(GameConfig.Player.INIT_X);
        setY(GameConfig.Player.INIT_Y);
    }

    public void act() {
        setX(getX() + dx);
    }

    public void explode() {
        setSpriteImage(explosion.getImage());
        dying = true;
    }

    public boolean isDying() {
        return dying;
    }

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

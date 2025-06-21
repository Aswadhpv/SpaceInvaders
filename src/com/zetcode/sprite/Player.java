package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import com.zetcode.engine.Updatable;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Updatable {

    private int dx;
    private boolean dying;
    private final ImageIcon explosion = new ImageIcon("src/images/explosion.png");

    public Player() {
        loadImage("src/images/player.png");
        setX(GameConfig.Player.START_POS_X);
        setY(GameConfig.Player.START_POS_Y);
    }

    @Override
    public void update() {
        int newX = getX() + dx;

        // ✅ use config constants to avoid shotgun surgery
        if (newX >= GameConfig.Logic.PLAYER_MIN_X && newX <= GameConfig.Logic.PLAYER_MAX_X) {
            setX(newX);
        }
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

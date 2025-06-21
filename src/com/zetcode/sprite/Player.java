package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int dx;

    public Player() {
        loadImage("src/images/player.png");
        setImageSize(GameConfig.Player.WIDTH, GameConfig.Player.HEIGHT);
        setX(GameConfig.Player.INIT_X);
        setY(GameConfig.Player.INIT_Y);
    }

    public void act() {
        setX(getX() + dx);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}

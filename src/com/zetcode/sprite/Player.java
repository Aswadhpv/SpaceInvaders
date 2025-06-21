package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int dx;

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        ImageIcon ii = new ImageIcon("src/images/player.png");
        setImage(ii.getImage());

        setX(GameConfig.Player.INIT_X);
        setY(GameConfig.Player.INIT_Y);
        setWidth(GameConfig.Player.WIDTH);
        setHeight(GameConfig.Player.HEIGHT);
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

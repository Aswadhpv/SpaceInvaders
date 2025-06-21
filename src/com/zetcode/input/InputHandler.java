package com.zetcode.input;

import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private final Player player;
    private Shot shot;
    private boolean inGame;

    public InputHandler(Player player, Shot shot, boolean inGame) {
        this.player = player;
        this.shot = shot;
        this.inGame = inGame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE && inGame && !shot.isVisible()) {
            shot = new Shot(player.getX(), player.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}

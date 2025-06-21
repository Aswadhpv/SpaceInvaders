package com.zetcode.render;

import com.zetcode.config.GameConfig;
import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

public class Renderer {

    public void render(Graphics g, JPanel panel, boolean inGame, String message,
                       List<Alien> aliens, Player player, Shot shot) {

        g.setColor(Color.black);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        g.setColor(Color.green);

        if (inGame) {
            drawInGameElements(g, panel, aliens, player, shot);
        } else {
            drawGameOver(g, panel, message);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawInGameElements(Graphics g, JPanel panel, List<Alien> aliens, Player player, Shot shot) {
        g.drawLine(0, GameConfig.Board.GROUND, panel.getWidth(), GameConfig.Board.GROUND);
        drawAliens(g, aliens, panel);
        drawPlayer(g, player, panel);
        drawShot(g, shot, panel);
        drawBombing(g, aliens, panel);
    }

    private void drawAliens(Graphics g, List<Alien> aliens, JPanel panel) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), panel);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g, Player player, JPanel panel) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), panel);
        }
    }

    private void drawShot(Graphics g, Shot shot, JPanel panel) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), panel);
        }
    }

    private void drawBombing(Graphics g, List<Alien> aliens, JPanel panel) {
        for (Alien a : aliens) {
            Alien.Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), panel);
            }
        }
    }

    private void drawGameOver(Graphics g, JPanel panel, String message) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameConfig.Board.WIDTH, GameConfig.Board.HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, GameConfig.Board.WIDTH / 2 - 30, GameConfig.Board.WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, GameConfig.Board.WIDTH / 2 - 30, GameConfig.Board.WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = panel.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message,
                (GameConfig.Board.WIDTH - fontMetrics.stringWidth(message)) / 2,
                GameConfig.Board.WIDTH / 2);
    }
}

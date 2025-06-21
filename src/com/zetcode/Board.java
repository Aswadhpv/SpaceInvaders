package com.zetcode;

import com.zetcode.config.GameConfig;
import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;
import com.zetcode.engine.GameEngine;
import com.zetcode.input.InputHandler;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;

    private boolean inGame = true;
    private String message = "Game Over";

    private Timer timer;
    private GameEngine gameEngine;
    private InputHandler inputHandler;

    public Board() {
        initBoard();
        gameInit();
    }

    private void initBoard() {
        setFocusable(true);
        d = new Dimension(GameConfig.Board.WIDTH, GameConfig.Board.HEIGHT);
        setBackground(Color.black);

        gameEngine = new GameEngine();
        player = new Player();
        shot = new Shot();
        inputHandler = new InputHandler(player, shot, inGame);
        addKeyListener(inputHandler);

        timer = new Timer(GameConfig.Board.DELAY, new GameCycle());
        timer.start();
    }

    private void gameInit() {
        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                var alien = new Alien(GameConfig.Alien.INIT_X + 18 * j,
                        GameConfig.Alien.INIT_Y + 18 * i);
                aliens.add(alien);
            }
        }
    }

    private void drawAliens(Graphics g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {
        for (Alien a : aliens) {
            Alien.Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {
            g.drawLine(0, GameConfig.Board.GROUND,
                    GameConfig.Board.WIDTH, GameConfig.Board.GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameConfig.Board.WIDTH, GameConfig.Board.HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, GameConfig.Board.WIDTH / 2 - 30, GameConfig.Board.WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, GameConfig.Board.WIDTH / 2 - 30, GameConfig.Board.WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (GameConfig.Board.WIDTH - fontMetrics.stringWidth(message)) / 2,
                GameConfig.Board.WIDTH / 2);
    }

    private void update() {
        gameEngine.update(player, shot, aliens);

        if (!gameEngine.isInGame()) {
            inGame = false;
            message = gameEngine.getMessage(); // ✅ Pulls "Game won!" or "Invasion!" correctly
            timer.stop();
        }
    }

    private boolean isAtEdge(int x) {
        return x <= GameConfig.Logic.BORDER_LEFT || x >= GameConfig.Board.WIDTH - GameConfig.Logic.BORDER_RIGHT;
    }

    private boolean isAlienShot(Alien alien, Shot shot) {
        return alien.isVisible() && shot.isVisible();
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }
}

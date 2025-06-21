package com.zetcode;

import com.zetcode.config.GameConfig;
import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;
import com.zetcode.engine.GameEngine;
import com.zetcode.input.InputHandler;
import com.zetcode.render.Renderer;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
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

    private Timer timer;
    private GameEngine gameEngine;
    private InputHandler inputHandler;
    private Renderer renderer;

    public Board() {
        initBoard();
        gameInit();
    }

    private void initBoard() {
        setFocusable(true);
        d = new Dimension(GameConfig.Board.WIDTH, GameConfig.Board.HEIGHT);
        setBackground(java.awt.Color.black);

        gameEngine = new GameEngine();
        player = new Player();
        shot = new Shot();
        inputHandler = new InputHandler(player, shot, inGame);
        renderer = new Renderer();
        addKeyListener(inputHandler);

        timer = new Timer(GameConfig.Board.DELAY, new GameCycle());
        timer.start();
    }

    private void gameInit() {
        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                var alien = new Alien(
                        GameConfig.Alien.START_POS_X + 18 * j,
                        GameConfig.Alien.START_POS_Y + 18 * i
                );
                aliens.add(alien);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, this, inGame, gameEngine.getMessage(), aliens, player, shot);
    }

    private void update() {
        gameEngine.update(player, shot, aliens);

        if (!gameEngine.isInGame()) {
            inGame = false;
            timer.stop();
        }
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

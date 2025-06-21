package com.zetcode;

import com.zetcode.config.GameConfig;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame {

    public SpaceInvaders() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Space Invaders");

        setSize(GameConfig.Board.WIDTH, GameConfig.Board.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        var ex = new SpaceInvaders();
        ex.setVisible(true);
    }
}

package com.zetcode.sprite;

public class Shot extends Sprite {

    public Shot() {
        setVisible(false);
    }

    public Shot(int x, int y) {
        loadImage("src/images/shot.png");
        setImageSize(4, 10); // update if you define shot dimensions in GameConfig
        int H_SPACE = 6;
        int V_SPACE = 1;
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }

    public void makeVisible() {
        setVisible(true);
    }
}

package com.zetcode.sprite;

import com.zetcode.engine.Updatable;

public class Shot extends Sprite implements Updatable {

    public Shot() {
        setVisible(false);
    }

    public Shot(int x, int y) {
        loadImage("src/images/shot.png");
        int H_SPACE = 6;
        int V_SPACE = 1;
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }

    @Override
    public void update() {
        int y = getY() - 4;
        if (y < 0) {
            die();
        } else {
            setY(y);
        }
    }

    public void makeVisible() {
        setVisible(true);
    }
}

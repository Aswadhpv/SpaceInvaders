package com.zetcode.sprite;

import com.zetcode.engine.Updatable;
import com.zetcode.graphics.Visual;

public class Shot implements Updatable {

    private final Visual visual;

    public Shot() {
        this.visual = new Visual("src/images/shot.png");
        visual.setVisible(false); // Initially invisible
    }

    public Shot(int x, int y) {
        this.visual = new Visual("src/images/shot.png");

        int H_SPACE = 6;
        int V_SPACE = 1;
        visual.setX(x + H_SPACE);
        visual.setY(y - V_SPACE);
    }

    @Override
    public void update() {
        int y = visual.getY() - 4;
        if (y < 0) {
            die();
        } else {
            visual.setY(y);
        }
    }

    public void die() {
        visual.die();
    }

    public boolean isVisible() {
        return visual.isVisible();
    }

    public int getX() {
        return visual.getX();
    }

    public int getY() {
        return visual.getY();
    }

    public void setY(int y) {
        visual.setY(y);
    }

    public java.awt.Image getImage() {
        return visual.getImage();
    }

    public void makeVisible() {
        visual.setVisible(true);
    }
}

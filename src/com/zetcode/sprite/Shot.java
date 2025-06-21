package com.zetcode.sprite;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() {
        setVisible(false);
    }

    public Shot(int x, int y) {
        initShot(x, y);
    }

    private void initShot(int x, int y) {
        ImageIcon ii = new ImageIcon("src/images/shot.png");
        setImage(ii.getImage());

        int H_SPACE = 6;
        int V_SPACE = 1;

        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }

    public void makeVisible() {
        setVisible(true);
    }
}

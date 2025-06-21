package com.zetcode.sprite;

import com.zetcode.Commons;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Alien extends Sprite {

    private Bomb bomb;
    private boolean dying;

    public Alien(int x, int y) {
        setX(x);
        setY(y);
        bomb = new Bomb(x, y);
        initAlien();
    }

    private void initAlien() {
        ImageIcon ii = new ImageIcon("src/images/alien.png");
        setImage(ii.getImage());
    }

    public void act(int direction) {
        setX(getX() + direction);
    }

    public Bomb getBomb() {
        return bomb;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean intersects(Shot shot) {
        int shotX = shot.getX();
        int shotY = shot.getY();

        return isVisible() && shot.isVisible() &&
                shotX >= getX() &&
                shotX <= (getX() + Commons.ALIEN_WIDTH) &&
                shotY >= getY() &&
                shotY <= (getY() + Commons.ALIEN_HEIGHT);
    }

    public class Bomb {
        private int x;
        private int y;
        private boolean destroyed;
        private Image image;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
            destroyed = true;

            ImageIcon ii = new ImageIcon("src/images/bomb.png");
            image = ii.getImage();
        }

        public Image getImage() {
            return image;
        }

        public boolean isDestroyed() {
            return destroyed;
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}

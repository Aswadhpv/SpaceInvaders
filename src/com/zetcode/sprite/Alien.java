package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
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
        setWidth(GameConfig.Alien.WIDTH);
        setHeight(GameConfig.Alien.HEIGHT);
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
                shotX <= (getX() + getWidth()) &&
                shotY >= getY() &&
                shotY <= (getY() + getHeight());
    }

    public class Bomb {
        private int x, y;
        private boolean destroyed = true;
        private final Image image;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
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

        public int getX() { return x; }
        public int getY() { return y; }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
    }
}

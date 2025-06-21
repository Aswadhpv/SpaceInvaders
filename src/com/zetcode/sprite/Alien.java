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

    public boolean handleShotCollision(Shot shot) {
        if (!isVisible() || !shot.isVisible()) return false;

        int shotX = shot.getX();
        int shotY = shot.getY();

        boolean hit = shotX >= getX() &&
                shotX <= (getX() + getWidth()) &&
                shotY >= getY() &&
                shotY <= (getY() + getHeight());

        if (hit) {
            ImageIcon ii = new ImageIcon("src/images/explosion.png");
            setImage(ii.getImage());
            setDying(true);
            shot.die();
        }

        return hit;
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

        public boolean handlePlayerHit(Player player) {
            if (destroyed || !player.isVisible()) return false;

            int bombX = getX();
            int bombY = getY();
            int playerX = player.getX();
            int playerY = player.getY();

            boolean hit = bombX >= playerX &&
                    bombX <= (playerX + player.getWidth()) &&
                    bombY >= playerY &&
                    bombY <= (playerY + player.getHeight());

            if (hit) {
                ImageIcon ii = new ImageIcon("src/images/explosion.png");
                player.setImage(ii.getImage());
                player.setDying(true);
                setDestroyed(true);
            }

            return hit;
        }
    }
}

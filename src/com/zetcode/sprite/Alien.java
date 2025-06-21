package com.zetcode.sprite;

import com.zetcode.config.GameConfig;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;

public class Alien extends Sprite {

    private Bomb bomb;
    private boolean dying;
    private Image explosionImage;

    public Alien(int x, int y) {
        setX(x);
        setY(y);
        bomb = new Bomb(x, y);
        loadImage("src/images/alien.png");
        explosionImage = new ImageIcon("src/images/explosion.png").getImage();
    }

    public void act(int direction) {
        setX(getX() + direction);
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public boolean hasReachedGround() {
        return getY() > GameConfig.Board.GROUND - GameConfig.Alien.HEIGHT;
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
            setSpriteImage(explosionImage);
            setDying(true);
            shot.die();
        }

        return hit;
    }

    public void maybeDropBomb() {
        if (!isVisible() || !bomb.isDestroyed()) return;

        int chance = new Random().nextInt(15);
        if (chance == GameConfig.Alien.CHANCE) {
            bomb.setDestroyed(false);
            bomb.setX(getX());
            bomb.setY(getY());
        }
    }

    public void updateBomb(Player player) {
        if (bomb.isDestroyed()) return;

        if (bomb.handlePlayerHit(player)) return;

        bomb.setY(bomb.getY() + 1);
        if (bomb.getY() >= GameConfig.Board.GROUND - GameConfig.Bomb.HEIGHT) {
            bomb.setDestroyed(true);
        }
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
                player.explode();
                setDestroyed(true);
            }

            return hit;
        }
    }
}

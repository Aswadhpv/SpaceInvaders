package com.zetcode.sprite;

import com.zetcode.config.GameConfig;
import com.zetcode.graphics.Visual;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;

public class Alien {

    private final Visual visual;
    private Bomb bomb;
    private final Image explosionImage;

    public Alien(int x, int y) {
        this.visual = new Visual("src/images/alien.png");
        this.visual.setX(x);
        this.visual.setY(y);
        this.bomb = new Bomb(x, y);
        this.explosionImage = new ImageIcon("src/images/explosion.png").getImage();
    }

    public void update(int direction) {
        visual.setX(visual.getX() + direction);
    }

    public boolean hasReachedGround() {
        return visual.getY() > GameConfig.Board.GROUND - GameConfig.Alien.HEIGHT;
    }

    public boolean handleShotCollision(Shot shot) {
        if (!visual.isVisible() || !shot.isVisible()) return false;

        int shotX = shot.getX();
        int shotY = shot.getY();

        boolean hit = shotX >= visual.getX() &&
                shotX <= (visual.getX() + visual.getWidth()) &&
                shotY >= visual.getY() &&
                shotY <= (visual.getY() + visual.getHeight());

        if (hit) {
            visual.setImage(explosionImage);
            visual.setDying(true);
            shot.die();
        }

        return hit;
    }

    public void maybeDropBomb() {
        if (!visual.isVisible() || !bomb.isDestroyed()) return;

        if (new Random().nextInt(15) == GameConfig.Alien.CHANCE) {
            bomb.setDestroyed(false);
            bomb.setX(visual.getX());
            bomb.setY(visual.getY());
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

    public int getX() { return visual.getX(); }
    public int getY() { return visual.getY(); }
    public Image getImage() { return visual.getImage(); }
    public boolean isVisible() { return visual.isVisible(); }
    public boolean isDying() { return visual.isDying(); }
    public void setY(int y) { visual.setY(y); }
    public void die() { visual.die(); }

    public Bomb getBomb() { return bomb; }

    public class Bomb {
        private int x, y;
        private boolean destroyed = true;
        private final Image image;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
            this.image = new ImageIcon("src/images/bomb.png").getImage();
        }

        public Image getImage() { return image; }
        public boolean isDestroyed() { return destroyed; }
        public void setDestroyed(boolean d) { destroyed = d; }

        public int getX() { return x; }
        public int getY() { return y; }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }

        public boolean handlePlayerHit(Player player) {
            if (destroyed || !player.isVisible()) return false;

            boolean hit = x >= player.getX() &&
                    x <= (player.getX() + player.getWidth()) &&
                    y >= player.getY() &&
                    y <= (player.getY() + player.getHeight());

            if (hit) {
                player.explode();
                destroyed = true;
            }

            return hit;
        }
    }
}

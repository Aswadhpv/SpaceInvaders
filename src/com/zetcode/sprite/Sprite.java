package com.zetcode.sprite;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    protected EntityState state;
    private Image image;

    public Sprite() {
        state = new EntityState();
    }

    protected void loadImage(String path) {
        ImageIcon ii = new ImageIcon(path);
        this.image = ii.getImage();
    }

    protected void setSpriteImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void die() {
        state.setVisible(false);
    }

    public boolean isVisible() {
        return state.isVisible();
    }

    public void setVisible(boolean visible) {
        state.setVisible(visible);
    }

    public int getX() {
        return state.getX();
    }

    public void setX(int x) {
        state.setX(x);
    }

    public int getY() {
        return state.getY();
    }

    public void setY(int y) {
        state.setY(y);
    }

    public int getWidth() {
        return state.getWidth();
    }

    public int getHeight() {
        return state.getHeight();
    }
}

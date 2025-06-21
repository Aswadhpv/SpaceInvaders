package com.zetcode.sprite;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    protected EntityState state;
    private Image image;
    private boolean dying;

    public Sprite() {
        state = new EntityState();
    }

    protected void loadImage(String path) {
        ImageIcon ii = new ImageIcon(path);
        this.image = ii.getImage();
    }

    protected void setImageSize(int width, int height) {
        setWidth(width);
        setHeight(height);
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

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        state.setX(x);
    }

    public void setY(int y) {
        state.setY(y);
    }

    public int getX() {
        return state.getX();
    }

    public int getY() {
        return state.getY();
    }

    public void setWidth(int width) {
        state.setWidth(width);
    }

    public void setHeight(int height) {
        state.setHeight(height);
    }

    public int getWidth() {
        return state.getWidth();
    }

    public int getHeight() {
        return state.getHeight();
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }
}

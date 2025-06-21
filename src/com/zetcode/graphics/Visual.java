// Replaces Sprite.java and encapsulates all visual state
package com.zetcode.graphics;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Visual {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private boolean visible;
    private boolean dying;

    public Visual(String imagePath) {
        loadImage(imagePath);
        visible = true;
    }

    private void loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(imagePath);
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() { return image; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setImage(Image img) { this.image = img; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public boolean isDying() { return dying; }
    public void setDying(boolean dying) { this.dying = dying; }

    public void die() { visible = false; }
}

package com.zetcode.engine;

import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;
import com.zetcode.Commons;

import javax.swing.ImageIcon;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private int deaths = 0;
    private boolean inGame = true;
    private String message = "Game Over";

    public void update(Player player, Shot shot, List<Alien> aliens) {
        checkWinCondition();
        updatePlayer(player);
        updateShot(shot, aliens);
        updateAliens(aliens);
        updateBombs(player, aliens);
    }

    private void checkWinCondition() {
        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            message = "Game won!";
        }
    }

    private void updatePlayer(Player player) {
        player.act();
    }

    private void updateShot(Shot shot, List<Alien> aliens) {
        if (!shot.isVisible()) return;

        int shotX = shot.getX();
        int shotY = shot.getY();

        for (Alien alien : aliens) {
            int alienX = alien.getX();
            int alienY = alien.getY();

            if (alien.isVisible() && shot.isVisible()) {
                if (shotX >= alienX && shotX <= (alienX + Commons.ALIEN_WIDTH) &&
                        shotY >= alienY && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                    var ii = new ImageIcon("src/images/explosion.png");
                    alien.setImage(ii.getImage());
                    alien.setDying(true);
                    deaths++;
                    shot.die();
                }
            }
        }

        int y = shot.getY() - 4;
        if (y < 0) {
            shot.die();
        } else {
            shot.setY(y);
        }
    }

    private void updateAliens(List<Alien> aliens) {
        for (Alien alien : aliens) {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
                for (Alien a : aliens) {
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT) {
                for (Alien a : aliens) {
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }
                alien.act(1); // Use direction if needed
            }
        }
    }

    private void updateBombs(Player player, List<Alien> aliens) {
        Random generator = new Random();

        for (Alien alien : aliens) {
            int chance = generator.nextInt(15);
            Alien.Bomb bomb = alien.getBomb();

            if (chance == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) {
                if (bombX >= playerX &&
                        bombX <= (playerX + Commons.PLAYER_WIDTH) &&
                        bombY >= playerY &&
                        bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon("src/images/explosion.png");
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {
                    bomb.setDestroyed(true);
                }
            }
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public String getMessage() {
        return message;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}

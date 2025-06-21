package com.zetcode.engine;

import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;
import com.zetcode.Commons;

import javax.swing.ImageIcon;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private int deaths = 0;
    private boolean inGame = true;
    private String message = "Game Over";

    public void update(Player player, Shot shot, List<Alien> aliens) {
        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            message = "Game won!";
        }

        player.act();

        if (shot.isVisible()) {
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

        for (Alien alien : aliens) {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
                for (Alien a : aliens) a.setY(a.getY() + Commons.GO_DOWN);
            }

            if (x <= Commons.BORDER_LEFT) {
                for (Alien a : aliens) a.setY(a.getY() + Commons.GO_DOWN);
            }
        }

        Iterator<Alien> it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = it.next();
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }
                alien.act(1); // simplified
            }
        }

        var generator = new Random();
        for (Alien alien : aliens) {
            Alien.Bomb bomb = alien.getBomb();
            int shotChance = generator.nextInt(15);

            if (shotChance == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
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

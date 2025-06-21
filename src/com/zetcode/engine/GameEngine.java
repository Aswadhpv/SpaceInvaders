package com.zetcode.engine;

import com.zetcode.config.GameConfig;
import com.zetcode.sprite.Alien;
import com.zetcode.sprite.Player;
import com.zetcode.sprite.Shot;

import javax.swing.ImageIcon;
import java.util.Iterator;
import java.util.List;

public class GameEngine {

    private int direction = -1;
    private int deaths = 0;
    private boolean inGame = true;
    private String message = "Game Over";
    private final String explosionImgPath = "src/images/explosion.png";

    public void update(Player player, Shot shot, List<Alien> aliens) {
        if (!inGame) return;

        // win condition
        if (deaths >= GameConfig.Logic.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            message = "Game won!";
            return;
        }

        player.update();
        shot.update();

        // collision with aliens
        for (Alien alien : aliens) {
            if (alien.handleShotCollision(shot)) {
                deaths++;
            }
        }

        updateAliens(aliens);
        updateBombs(aliens, player);
    }

    private void updateAliens(List<Alien> aliens) {
        boolean atRightEdge = false;
        boolean atLeftEdge = false;

        for (Alien alien : aliens) {
            int x = alien.getX();
            if (x >= GameConfig.Board.WIDTH - GameConfig.Logic.BORDER_RIGHT) {
                atRightEdge = true;
            }
            if (x <= GameConfig.Logic.BORDER_LEFT) {
                atLeftEdge = true;
            }
        }

        if (atRightEdge && direction != -1) {
            direction = -1;
            dropAllAliens(aliens);
        }

        if (atLeftEdge && direction != 1) {
            direction = 1;
            dropAllAliens(aliens);
        }

        for (Alien alien : aliens) {
            if (!alien.isVisible()) continue;

            if (alien.hasReachedGround()) {
                inGame = false;
                message = "Invasion!";
                return;
            }

            alien.update(direction);
        }
    }

    private void dropAllAliens(List<Alien> aliens) {
        for (Alien alien : aliens) {
            alien.setY(alien.getY() + GameConfig.Logic.GO_DOWN);
        }
    }

    private void updateBombs(List<Alien> aliens, Player player) {
        for (Alien alien : aliens) {
            alien.maybeDropBomb();
            alien.updateBomb(player);
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
}

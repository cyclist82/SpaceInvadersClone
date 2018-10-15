package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private int counter = 0;
    // Array Raumschiffe Gegner
    List<SpaceshipEnemy> enemyList = new ArrayList<>();

    SpaceshipPlayer spaceshipPlayer = new SpaceshipPlayer(Main.WIDTH / 2, 600);

    public void spaceshipPlayerLeft() {
        if (spaceshipPlayer.getPosX() > 0) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() - 10);
        }
    }

    public void newSpaceshipEnemy(double posX, double posY) {
        enemyList.add(new SpaceshipEnemy(posY, posX));
    }

    public List<SpaceshipEnemy> getEnemyList() {
        return enemyList;
    }

    public int getCounter() {
        return counter;
    }

    public void update(long deltaMillis) {
        counter += deltaMillis;
    }


    public void resetCounter() {
        counter = 0;
    }

    public SpaceshipPlayer getSpaceshipPlayer() {
        return spaceshipPlayer;
    }

    public void spaceshipPlayerRight() {
        if (spaceshipPlayer.getPosX() < Main.WIDTH - 80) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() + 10);
        }
    }

    public void spaceshipPlayerUp() {
        if (spaceshipPlayer.getPosY() > 0) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() - 10);
        }
    }

    public void spaceshipPlayerDown() {
        if (spaceshipPlayer.getPosY() < Main.HEIGTH -80) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() + 10);
        }
    }
}

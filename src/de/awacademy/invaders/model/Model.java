package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private int counter = 0;
    // Array Raumschiffe Gegner
    List<SpaceshipEnemy> enemyList = new ArrayList<>();
    List<Laser> laserList = new ArrayList<Laser>();

    SpaceshipPlayer spaceshipPlayer = new SpaceshipPlayer(Main.WIDTH / 2, 600);


    public void spaceshipPlayerLeft() {
        if (spaceshipPlayer.getPosX() > 0) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() - 10);
        }
    }

    public void newSpaceshipEnemy(double posX, double posY) {
        enemyList.add(new SpaceshipEnemy(posY, posX));
    }

    public void createEnemyFleet(int rows) {
        for (int row = 1; row <= rows; row++) {
            for (int i = 60; i < Main.WIDTH / 2; i += 50) {
                enemyList.add(new SpaceshipEnemy(row * 50 - 30, i));
            }
        }
    }

    public void enemyFleetMovement() {
        for (SpaceshipEnemy enemy : enemyList) {
            if (counter / 4200 % 2 == 0) {
                enemy.setPosX(enemy.getPosX() + 2.5);
            } else {
                enemy.setPosX(enemy.getPosX() - 2.5);
            }
            if (counter / 3500 % 2 == 0) {
                enemy.setPosY(enemy.getPosY() + 0.4);
            } else {
                enemy.setPosY(enemy.getPosY() - 0.2);
            }
        }
    }

    public List<SpaceshipEnemy> getEnemyList() {
        return enemyList;
    }

    public List<Laser> getLaserList() {
        return laserList;
    }

    public void fireLaser() {
        laserList.add(new Laser(spaceshipPlayer.getPosX()+20, spaceshipPlayer.getPosY()));
    }

    public void laserMovement() {
        for (Laser laser : laserList) {
            laser.setPosY(laser.getPosY() - laser.getSpeed());

        }
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
        if (spaceshipPlayer.getPosY() < Main.HEIGTH - 80) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() + 10);
        }
    }

    public void spaceshipPlayerUpRight() {
        if (spaceshipPlayer.getPosY() > 0) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() - 10);
        }
        if (spaceshipPlayer.getPosX() < Main.WIDTH - 80) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() + 10);
        }
    }

}

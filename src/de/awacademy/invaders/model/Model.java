package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    private int counter = 0;
    // Array Raumschiffe Gegner
    private List<SpaceshipEnemy> enemyList = new ArrayList<>();
    private List<Laser> laserPlayerList = new ArrayList<Laser>();
    private List<Laser> laserEnemyList = new ArrayList<Laser>();

    SpaceshipPlayer spaceshipPlayer = new SpaceshipPlayer(Main.WIDTH / 2, 600);

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

    // Laser der Gegner Flotte feuern
    public void enemyFleetFireLaser() {
        if (counter % 150 == 0) {
            int position = (int) (Math.random() * enemyList.size());
            laserEnemyList.add(new Laser(enemyList.get(position).getPosX(), enemyList.get(position).getPosY()));
        }
    }

    // Rückgabe der aktuell geschossenen Gegner Laser
    public List<Laser> getLaserEnemyList() {
        return laserEnemyList;
    }

    // Gegner Laser bewegung
    public void laserEnemyMovement() {
        for (Laser laser : laserEnemyList) {
            laser.setPosY(laser.getPosY() + laser.getSpeed());
        }
    }

    // List der Gegner
    public List<SpaceshipEnemy> getEnemyList() {
        return enemyList;
    }

    // Ausgabe Liste der Player-Laser
    public List<Laser> getPlayerLaserList() {
        return laserPlayerList;
    }

    // Feuer eines Player-Lasers
    public void fireLaserPlayer() {
        laserPlayerList.add(new Laser(spaceshipPlayer.getPosX() + 20, spaceshipPlayer.getPosY()));
    }

    // Bewegung der Player Laser
    public void laserPlayerMovement() {
        for (Laser laser : laserPlayerList) {
            laser.setPosY(laser.getPosY() - laser.getSpeed());
        }
    }

    // Rückgabe des Counters
    public int getCounter() {
        return counter;
    }

    // Update des Counters
    public void update(long deltaMillis) {
        counter += deltaMillis;
    }


    public void resetCounter() {
        counter = 0;
    }

    public SpaceshipPlayer getSpaceshipPlayer() {
        return spaceshipPlayer;
    }

    // Bewegungen des Spielers durch Cursoreingabe
    public void spaceshipPlayerLeft() {
        if (spaceshipPlayer.getPosX() > 0) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() - 10);
        }
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
}

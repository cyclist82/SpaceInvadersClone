package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    private int points = 0;
    private int counter = 0;
    // Array Raumschiffe Gegner
    private ArrayList<SpaceshipEnemy> enemyList = new ArrayList<>();
    private ArrayList<Laser> laserPlayerList = new ArrayList<Laser>();
    private ArrayList<Laser> laserEnemyList = new ArrayList<Laser>();

    SpaceshipPlayer spaceshipPlayer = new SpaceshipPlayer(Main.WIDTH / 2, 600);

    public void createEnemyFleet(int rows) {
        for (int row = 1; row <= rows; row++) {
            for (int i = 85; i < Main.WIDTH / 2 + 35; i += 50) {
                enemyList.add(new SpaceshipEnemy(row * 50 - 30, i));
            }
        }
    }

    // Bewegung der Gegner Flotte
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
        if (counter % 150 < 5 && counter % 150 > 2) {
            int position = (int) (Math.random() * enemyList.size());
            laserEnemyList.add(new Laser(enemyList.get(position).getPosX(), enemyList.get(position).getPosY()));
        }
    }

    // Rückgabe der aktuell geschossenen Gegner Laser
    public ArrayList<Laser> getLaserEnemyList() {
        return laserEnemyList;
    }

    // Gegner Laser bewegung
    public void laserEnemyMovement() {
        for (Laser laser : laserEnemyList) {
            laser.setPosY(laser.getPosY() + laser.getSpeed());
        }
        laserEnemyList.removeIf(laser -> laser.getPosY() >= Main.HEIGTH);
    }

    // List der Gegner
    public ArrayList<SpaceshipEnemy> getEnemyList() {
        return enemyList;
    }

    public void laserPlayerDestroyEnemy() {
        for (SpaceshipEnemy enemy : enemyList) {
            for (Laser laser : laserPlayerList) {
                if (laser.getPosY() <= enemy.getPosY() && laser.getPosY() >= enemy.getPosY() - 30 && laser.getPosX() <= enemy.getPosX() + 30 && laser.getPosX() >= enemy.getPosX()) {
                    enemy.setAlive(false);
                    laser.setAlive(false);
                    points++;
                }
            }
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.isAlive() == false);
        laserPlayerList.removeIf(laser -> laser.isAlive() == false);
    }

    public void laserEnemyHitPlayer() {
        for (Laser laser : laserEnemyList) {
            if (laser.getPosY() >= spaceshipPlayer.getPosY() && laser.getPosY() <= spaceshipPlayer.getPosY() + 40 && laser.getPosX() >= spaceshipPlayer.getPosX() && laser.getPosX() <= spaceshipPlayer.getPosX()+40) {
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() - 1);
                laser.setAlive(false);
            }
        }
        laserEnemyList.removeIf(laser -> laser.isAlive() == false);
    }


    // Ausgabe Liste der Player-Laser
    public ArrayList<Laser> getPlayerLaserList() {
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
        laserPlayerList.removeIf(laser -> laser.getPosY() <= -10);
    }

    // Rückgabe des Counters
    public int getCounter() {
        return counter;
    }

    // Update des Counters
    public void update(long deltaMillis) {
        counter += deltaMillis;
        enemyFleetMovement();
        laserPlayerMovement();
        laserEnemyMovement();
        enemyFleetFireLaser();
        laserPlayerDestroyEnemy();
        laserEnemyHitPlayer();
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

    public int getPoints() {
        return points;
    }
}

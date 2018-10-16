package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;

import java.util.LinkedList;


public class Model {

    private int points = 0;
    private int counter = 0;
    // Array Raumschiffe Gegner
    private LinkedList<SpaceshipEnemy> enemyList = new LinkedList<>();
    private LinkedList<Laser> laserPlayerList = new LinkedList<Laser>();
    private LinkedList<Laser> laserEnemyList = new LinkedList<Laser>();
    private LinkedList<Explosion> explosions = new LinkedList<>();

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

    // Explosion hinzufügen
    public void createExplosion(double posY, double posX) {
        explosions.add(new Explosion(posY, posX, counter));
    }

    public void deleteExplosion(){
        explosions.removeIf(explosion -> counter >= explosion.getTimeCreated() + explosion.getTimeAlive());
    }

    // Laser der Gegner Flotte feuern
    public void enemyFleetFireLaser() {
        if (counter % 150 < 5 && counter % 150 > 2 && enemyList.size() > 0) {
            int position = (int) (Math.random() * enemyList.size());
            laserEnemyList.add(new Laser(enemyList.get(position).getPosX() + 15, enemyList.get(position).getPosY() + 30));
        }
    }

    // Rückgabe der aktuell geschossenen Gegner Laser
    public LinkedList<Laser> getLaserEnemyList() {
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
    public LinkedList<SpaceshipEnemy> getEnemyList() {
        return enemyList;
    }

    // Hier zerstören die Laser des Spielers den Gegner
    public void laserPlayerDestroyEnemy() {
        for (SpaceshipEnemy enemy : enemyList) {
            for (Laser laser : laserPlayerList) {
                if (laser.getPosY() - 20 <= enemy.getPosY() && laser.getPosY() - 20 >= enemy.getPosY() - 30 && laser.getPosX() + 2.5 <= enemy.getPosX() + 30 && laser.getPosX() + 2.5 >= enemy.getPosX()) {
                    enemy.setAlive(false);
                    laser.setAlive(false);
                    createExplosion(enemy.getPosY(), enemy.getPosX());
                    points++;
                }
            }
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.isAlive() == false);
        laserPlayerList.removeIf(laser -> laser.isAlive() == false);
    }

    // Hier ziehen die Laser der Gegner dem Spieler Lebenspunkte ab
    public void laserEnemyHitPlayer() {
        for (Laser laser : laserEnemyList) {
            if (laser.getPosY() >= spaceshipPlayer.getPosY() && laser.getPosY() <= spaceshipPlayer.getPosY() + 40 && laser.getPosX() >= spaceshipPlayer.getPosX() && laser.getPosX() <= spaceshipPlayer.getPosX() + 40) {
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() - 1);
                laser.setAlive(false);
            }
        }
        laserEnemyList.removeIf(laser -> laser.isAlive() == false);
    }

    // Hier krachen Spieler und Gegner zusammen
    public void playerHitsEnemy() {
        for (SpaceshipEnemy enemy : enemyList) {
            if (enemy.getPosY() >= spaceshipPlayer.getPosY() - 28 && enemy.getPosY() <= spaceshipPlayer.getPosY() + 28 && enemy.getPosX() >= spaceshipPlayer.getPosX() - 28 && enemy.getPosX() <= spaceshipPlayer.getPosX() + 28) {
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() - 1);
                enemy.setAlive(false);
            }
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.isAlive() == false);
    }

    // Ausgabe Liste der Player-Laser
    public LinkedList<Laser> getPlayerLaserList() {
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
        playerHitsEnemy();
        deleteExplosion();
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

    public LinkedList<Explosion> getExplosions() {
        return explosions;
    }

    public int getPoints() {
        return points;
    }
}

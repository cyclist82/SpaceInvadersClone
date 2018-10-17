package de.awacademy.invaders.model;

import de.awacademy.invaders.Main;
import de.awacademy.invaders.Sounds;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.LinkedList;


public class Model {

    private int gameStatus = 0;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean spaceKey;
    private boolean anyKey;
    private boolean enterKey;
    private boolean escapeKey;
    private boolean gameStarted;
    private boolean levelStart = false;

    private boolean nKey;
    private boolean pointGlow = false, lifesGlow = false;
    private boolean enemyMovingRight;
    private boolean finalEnemyMovingRight;
    private boolean finalEnemyMovingDown;
    private int points = 0, rows;
    private int counter = 0;
    private int level = 1;
    private int menuPoint = 0;
    private long lastShotPlayer = -140;
    private long timeLastPoint = 0;
    private long timeLastLifeLost = 0;
    private long timeStampEndgame;
    private long lastMenuChange;
    private int themeValue;
    private final double gameWait = 2500;

    // Array Raumschiffe, Explosionen und Laser
    private LinkedList<SpaceshipEnemy> enemyList = new LinkedList<>();
    private LinkedList<Laser> laserPlayerList = new LinkedList<Laser>();
    private LinkedList<Laser> laserEnemyList = new LinkedList<Laser>();
    private LinkedList<Explosion> explosions = new LinkedList<>();
    private LinkedList<String> menuPoints = new LinkedList<>();
    private LinkedList<FinalEnemy> finalEnemies = new LinkedList<>();

    Sounds sounds = new Sounds();
    int random = (int) (Math.random() * 10);
    SpaceshipPlayer spaceshipPlayer = new SpaceshipPlayer(Main.WIDTH / 2 - 15, Main.HEIGTH - 200);

    // Gamestatus überprüfen und durch Menüpunkte bewegen
    public void gameStatus() {
        // Gamesstatus 0 == Loadingsscreen
        if (gameStatus == 0) {
            enemyList.clear();
            laserEnemyList.clear();
            laserPlayerList.clear();
            if (anyKey == true) {
                gameStatus = 10;
            }
        }
        // Aufräumen und für neues Spiel initalisieren
        if (gameStatus == 4) {
            level = 1;
            rows = 1;
            clearScreen();
            spaceshipPlayer.setLives(5);
            createEnemyFleet(rows);
            lastMenuChange = counter;
            gameStatus = 1;
        }
        // nächstes Level starten
        if (gameStatus == 5) {
            level++;
            if (level >= 5) {
                clearScreen();
                createFinalEnemy(1);
                gameStatus = 1;
            } else {
                clearScreen();
                createEnemyFleet(rows);
                gameStatus = 1;
            }
            lastMenuChange = counter;
        }
        // Hier wird endlich gespielt
        if (gameStatus == 1) {
            if (lastMenuChange + gameWait > counter) {
                levelStart = true;
            } else {
                levelStart = false;
            }
            menuPoint = 0;
            if (escapeKey == true) {
                gameStatus = 10;
            }
            // Level ist gewonnen
            if (enemyList.isEmpty() && finalEnemies.isEmpty()) {
                timeStampEndgame = counter;
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() + 1);
                rows++;
                gameStatus = 7;
            }
            // Spiel wird verloren
            if (spaceshipPlayer.getLives() <= 0) {
                spaceshipPlayer.setLives(0);
                timeStampEndgame = counter;
                gameStarted = false;
                gameStatus = 8;
            }
        }
        // Spiel stoppen, kurze pause bevor es weitergeht
        if ((gameStatus == 7 || gameStatus == 8) && timeStampEndgame + gameWait < counter) {
            if (gameStatus == 7) {
                gameStatus = 5;
            } else {
                gameStatus = 10;
            }
        }
        // Menüstruktur festlegen
        if (gameStatus == 10) {
            menuPoints.clear();
            menuPoints.add("NEUES SPIEL");
            if (gameStarted) {
                menuPoints.add("SPIEL FORTSETZTEN");
            }
            menuPoints.add("THEME AUSWAEHLEN");
            menuPoints.add("EXIT");
            menuSteuerung();
            if (enterKey && menuPoint == 0 && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 4;
            }
            if (enterKey && menuPoint == 1 && gameStarted && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 1;
            }
            if (enterKey && menuPoint == 1 && !gameStarted && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 11;
                menuPoint = 0;
            }
            if (enterKey && menuPoint == 2 && gameStarted && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 11;
                menuPoint = 0;
            }
            if (enterKey && menuPoint == 2 && !gameStarted) {
                System.exit(0);
            }
            if (enterKey && menuPoint == 3 && gameStarted) {
                System.exit(0);
            }
        }
        // Menüstruktur für Theme Auswahl
        if (gameStatus == 11) {
            menuPoints.clear();
            menuPoints.add("SPACE INVADERS");
            menuPoints.add("STAR WARS");
            menuPoints.add("BACK");
            menuSteuerung();
            if (enterKey && menuPoint == 0 && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 10;
                themeValue = 0;
                sounds.stopBackgroundSongSW();
                sounds.playBackgroundSong();
                menuPoint = 0;
            }
            if (enterKey && menuPoint == 1 && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 10;
                themeValue = 1;
                sounds.stopBackgroundSong();
                sounds.playBackgroundSongSW();
                menuPoint = 0;
            }
            if (enterKey && menuPoint == 2 && lastMenuChange + 200 < counter) {
                lastMenuChange = counter;
                gameStatus = 10;
                menuPoint = 0;
            }
        }
    }

    private void clearScreen() {
        enemyList.clear();
        laserPlayerList.clear();
        laserEnemyList.clear();
    }

    private void menuSteuerung() {
        if (menuPoint >= 0 && menuPoint <= menuPoints.size() - 1 && lastMenuChange + 200 < counter) {
            if (up && menuPoint > 0) {
                lastMenuChange = counter;
                menuPoint--;
            }
            if (down && menuPoint < menuPoints.size() - 1) {
                lastMenuChange = counter;
                menuPoint++;
            }
        }
    }

    // Gegnerflotte erzeugen
    public void createEnemyFleet(int rows) {
        for (int row = 1; row <= rows; row++) {
            for (int i = 85; i < Main.WIDTH / 2 + 100; i += 50) {
                enemyList.add(new SpaceshipEnemy(row * 50 - 30, i));
            }
        }
    }

    public void createFinalEnemy(int amount) {
        for (int i = 0; i < amount; i++) {
            finalEnemies.add(new FinalEnemy(Main.WIDTH / 2 - 100, 100));
        }
    }


    // Bewegung der Gegner Flotte und Löschen falls Außerhalb des Bildschirms
    public void enemyFleetMovement(long deltaMillis) {
        if (counter / 500 % 12 == random) {
            enemyMovingRight = !enemyMovingRight;
        }
        for (SpaceshipEnemy enemy : enemyList) {
            if (enemyMovingRight) {
                enemy.setPosX(enemy.getPosX() + deltaMillis / 6);
            } else {
                enemy.setPosX(enemy.getPosX() - deltaMillis / 6);
            }
            if (counter / 3500 % 2 == 0) {
                enemy.setPosY(enemy.getPosY() + deltaMillis / 17);
            } else {
                enemy.setPosY(enemy.getPosY() - deltaMillis / 19);
            }
            if (enemy.getPosX() > Main.WIDTH - 50) {
                enemyMovingRight = false;
            }
            if (enemy.getPosX() < 30) {
                enemyMovingRight = true;
            }
            if (enemy.getPosY() >= Main.HEIGTH - 20) {
                gameStatus = 8;
            }
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.getPosY() >= Main.HEIGTH);
    }

    // ENdgegner Bewegung
    public void finalEnemyMovement(long deltaMillis) {
        if (counter / 400 % 12 == random) {
            finalEnemyMovingRight = !finalEnemyMovingRight;
        }
        if (counter / 400 % 12 == random) {
            finalEnemyMovingDown = !finalEnemyMovingDown;
        }
        for (FinalEnemy enemy : finalEnemies) {
            if (finalEnemyMovingRight) {
                enemy.setPosX(enemy.getPosX() + deltaMillis / 6);
            } else {
                enemy.setPosX(enemy.getPosX() - deltaMillis / 6);
            }
            if (finalEnemyMovingDown) {
                enemy.setPosY(enemy.getPosY() + deltaMillis / 6);
            } else {
                enemy.setPosY(enemy.getPosY() - deltaMillis / 6);
            }
            if (enemy.getPosY() > Main.HEIGTH - 200) {
                finalEnemyMovingDown = false;
            }
            if (enemy.getPosY() < 0) {
                finalEnemyMovingDown = true;
            }
            if (enemy.getPosX() > Main.WIDTH - 200) {
                finalEnemyMovingRight = false;
            }
            if (enemy.getPosX() < 0) {
                finalEnemyMovingRight = true;
            }
            if (enemy.getPosY() >= Main.HEIGTH - 20) {
                gameStatus = 8;
            }
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.getPosY() >= Main.HEIGTH);
    }

    // Explosion hinzufügen
    public void createExplosion(double posY, double posX) {
        explosions.add(new Explosion(posY, posX, counter));
    }

    // Explosion nach Ablaufzeit löschen
    public void deleteExplosion() {
        explosions.removeIf(explosion -> counter >= explosion.getTimeCreated() + explosion.getTimeAlive());
    }

    // Laser der Gegner Flotte feuern
    public void enemyFleetFireLaser() {
        if (counter % 110 < 5 && counter % 110 > 2 && enemyList.size() > 0) {
            int position = (int) (Math.random() * enemyList.size());
            if (themeValue == 1) {
                laserEnemyList.add(new Laser(enemyList.get(position).getPosX() + 15, enemyList.get(position).getPosY() + 30, Color.GREENYELLOW));
            } else {
                laserEnemyList.add(new Laser(enemyList.get(position).getPosX() + 15, enemyList.get(position).getPosY() + 30, Color.RED));
            }
            sounds.shootLaser();
        }
    }

    // Rückgabe der aktuell geschossenen Gegner Laser
    public LinkedList<Laser> getLaserEnemyList() {
        return laserEnemyList;
    }

    // Gegner Laser bewegung
    public void laserEnemyMovement(long deltaMillis) {
        for (Laser laser : laserEnemyList) {
            laser.setPosY(laser.getPosY() + deltaMillis * laser.getSpeed());
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
                    enemy.setLives(enemy.getLives() - 1);
                    laser.setAlive(false);
                    timeLastPoint = counter;
                }
            }
            if (nKey) {
                enemy.setLives(0);
            }
            if (enemy.getLives() == 0) {
                sounds.playEnemyKiller();
                createExplosion(enemy.getPosY(), enemy.getPosX());
                points++;
            }
        }
        // Farbänderung des Punktezählers in der Grafik anstoßen
        if (counter <= timeLastPoint + 200) {
            pointGlow = true;
        } else {
            pointGlow = false;
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.getLives() == 0);
        laserPlayerList.removeIf(laser -> laser.isAlive() == false);
    }

    // Hier ziehen die Laser der Gegner dem Spieler Lebenspunkte ab
    public void laserEnemyHitPlayer() {
        for (Laser laser : laserEnemyList) {
            if (laser.getPosY() >= spaceshipPlayer.getPosY() && laser.getPosY() <= spaceshipPlayer.getPosY() + 40 && laser.getPosX() >= spaceshipPlayer.getPosX() && laser.getPosX() <= spaceshipPlayer.getPosX() + 40) {
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() - 1);
                laser.setAlive(false);
                sounds.playPlayerisHit();
                timeLastLifeLost = counter;
                createExplosion(spaceshipPlayer.getPosY(), spaceshipPlayer.getPosX());
            }
        }
        if (counter <= timeLastLifeLost + 600) {
            lifesGlow = true;
        } else {
            lifesGlow = false;
        }
        laserEnemyList.removeIf(laser -> laser.isAlive() == false);
    }

    // Hier krachen Spieler und Gegner zusammen
    public void playerHitsEnemy() {
        for (SpaceshipEnemy enemy : enemyList) {
            if (enemy.getPosY() >= spaceshipPlayer.getPosY() - 28 && enemy.getPosY() <= spaceshipPlayer.getPosY() + 28 && enemy.getPosX() >= spaceshipPlayer.getPosX() - 28 && enemy.getPosX() <= spaceshipPlayer.getPosX() + 28) {
                spaceshipPlayer.setLives(spaceshipPlayer.getLives() - 1);
                enemy.setLives(0);
                timeLastLifeLost = counter;
                if (enemy.getLives() == 0) {
                    sounds.playEnemyKiller();
                    createExplosion(enemy.getPosY(), enemy.getPosX());
                }
            }
        }
        if (counter <= timeLastLifeLost + 600) {
            lifesGlow = true;
        } else {
            lifesGlow = false;
        }
        enemyList.removeIf(spaceshipEnemy -> spaceshipEnemy.getLives() == 0);
    }

    // Ausgabe Liste der Player-Laser
    public LinkedList<Laser> getPlayerLaserList() {
        return laserPlayerList;
    }

    // Feuer eines Player-Lasers
    public void fireLaserPlayer() {
        if (spaceKey) {
            if (lastShotPlayer + 500 <= counter) {
                laserPlayerList.add(new Laser(spaceshipPlayer.getPosX() + 20, spaceshipPlayer.getPosY(), Color.RED));
                lastShotPlayer = counter;
                sounds.shootLaser();
            }
        }
    }

    // Bewegung der Player Laser
    public void laserPlayerMovement(long deltaMillis) {
        for (Laser laser : laserPlayerList) {
            laser.setPosY(laser.getPosY() - deltaMillis * laser.getSpeed());
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
        gameStatus();
        if (gameStatus == 1) {
            finalEnemyMovement(deltaMillis);
            enemyFleetMovement(deltaMillis);
            laserPlayerMovement(deltaMillis);
            laserEnemyMovement(deltaMillis);
            enemyFleetFireLaser();
            fireLaserPlayer();
            laserPlayerDestroyEnemy();
            laserEnemyHitPlayer();
            playerHitsEnemy();
            deleteExplosion();
            playerMovement(deltaMillis);
        }
    }


    public void resetCounter() {
        counter = 0;
    }

    public SpaceshipPlayer getSpaceshipPlayer() {
        return spaceshipPlayer;
    }

    // Bewegungen des Spielers durch Cursoreingabe
    public void playerMovement(long deltaMillis) {
        if (left && spaceshipPlayer.getPosX() > 0) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() - deltaMillis / 3);
        }
        if (right && spaceshipPlayer.getPosX() < Main.WIDTH - 40) {
            spaceshipPlayer.setPosX(spaceshipPlayer.getPosX() + deltaMillis / 3);
        }
        if (up && spaceshipPlayer.getPosY() > 0) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() - deltaMillis / 3);
        }
        if (down && spaceshipPlayer.getPosY() < Main.HEIGTH - 40) {
            spaceshipPlayer.setPosY(spaceshipPlayer.getPosY() + deltaMillis / 3);
        }
    }


    public LinkedList<Explosion> getExplosions() {
        return explosions;
    }

    public int getPoints() {
        return points;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setSpaceKey(boolean spaceKey) {
        this.spaceKey = spaceKey;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setAnyKey(boolean anyKey) {
        this.anyKey = anyKey;
    }

    public void setEnterKey(boolean enterKey) {
        this.enterKey = enterKey;
    }

    public void setEscapeKey(boolean escapeKey) {
        this.escapeKey = escapeKey;
    }

    public boolean isPointGlow() {
        return pointGlow;
    }

    public boolean isLifesGlow() {
        return lifesGlow;
    }

    public void setnKey(boolean nKey) {
        this.nKey = nKey;
    }

    public LinkedList<String> getMenuPoints() {
        return menuPoints;
    }

    public int getMenuPoint() {
        return menuPoint;
    }

    public int getThemeValue() {
        return themeValue;
    }

    public int getLevel() {
        return level;
    }

    public boolean isLevelStart() {
        return levelStart;
    }

    public LinkedList<FinalEnemy> getFinalEnemies() {
        return finalEnemies;
    }
}

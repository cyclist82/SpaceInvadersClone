package de.awacademy.invaders;

import de.awacademy.invaders.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;

public class Graphics {

    private Model model;
    private GraphicsContext gc;
    private Image enemySpaceshipImage = new Image(getClass().getResource("/images/EnemySpaceship.png").toString());
    private Image playerSpaceshipImage = new Image(getClass().getResource("/images/SpaceShipPlayer.png").toString());
    private Image explosionImage = new Image(getClass().getResource("/images/Explosion.png").toString());
    private Image loadScreen = new Image(getClass().getResource("/images/SpaceInvadersMenuBackground.jpg").toString());
    private Image background = new Image(getClass().getResource("/images/SpaceInvadersBackground.jpg").toString());

    private Image enemySpaceshipImageSW = new Image(getClass().getResource("/images/TieFighter.png").toString());
    private Image playerSpaceshipImageSW = new Image(getClass().getResource("/images/MilleniumFalcon.png").toString());
    private Image finalEnemySW = new Image(getClass().getResource("/images/Todesstern.png").toString());
    private Image finalEnemyInvaders = new Image(getClass().getResource("/images/Todesstern.png").toString());
    private Image loadScreenSW = new Image(getClass().getResource("/images/StarWarsMenuBackground.jpg").toString());
    private Image backgroundSW = new Image(getClass().getResource("/images/StarWarsGameBackground.jpg").toString());

    private final int menuBorderInput = 200;
    private final int scoreRightInbound = 550;
//    private final Font fontStarWars80 = (Font.loadFont(getClass().getResourceAsStream("/resources/fonts/starjout.ttf"), 120));

    private final String fontSW = "STARWARS";
    private final String font = "Digital-7";

    public Graphics(Model model, GraphicsContext gc) throws FileNotFoundException {
        this.model = model;
        this.gc = gc;
    }

    // Hier wird alles gezeichnet
    public void draw() {
        if (model.getThemeValue() == 1) {
            if (model.getGameStatus() == 0) {
                loadScreen(loadScreenSW, fontSW);
            }
            if (model.getGameStatus() == 6) {
                gc.drawImage(loadScreenSW, 0, 0, Main.WIDTH, Main.HEIGTH);
                gc.setFill(Color.GOLD);
                gc.setFont(Font.font( fontSW, FontWeight.BOLD, 80));
                gc.fillText("GEWONNEN", 100, 400);
                gc.fillText("DRUECKE ENTER", 100, 500);
                gc.fillText("UM NEU ZU BEGINNEN", 100, 600);
            }
            if (model.getGameStatus() == 1 || model.getGameStatus() == 7 || model.getGameStatus() == 8 || model.getGameStatus() == 4) {
                gc.drawImage(backgroundSW, 0, 0, Main.WIDTH, Main.HEIGTH);
                laserGraphics(model.getPlayerLaserList());
                laserGraphics(model.getLaserEnemyList());
                laserGraphics(model.getLaserFinalEnemyList());
                if (model.isLevelStart()) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(fontSW, FontWeight.BOLD, 80));
                    gc.fillText("LEVEL " + this.model.getLevel() + " STARTET", Main.WIDTH / 2 - 600, Main.HEIGTH / 2);
                }
                if (model.getGameStatus() == 7) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(fontSW, FontWeight.BOLD, 80));
                    gc.fillText("LEVEL " + this.model.getLevel() + " COMPLETE", Main.WIDTH / 2 - 600, Main.HEIGTH / 2);
                    gc.fillText("YOU SAVED ALDERAAN", Main.WIDTH / 2 - 600, Main.HEIGTH / 2 + 150);
                }
                if (model.getGameStatus() == 8) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(fontSW, FontWeight.BOLD, 80));
                    gc.fillText("GAME OVER", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 150);
                    gc.fillText("ALDERAAN IS LOST", Main.WIDTH / 2 - 350, Main.HEIGTH / 2 + 300);
                }
                if (model.isPointGlow()) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.RED);
                }
                gc.setFont(Font.font(fontSW, FontWeight.BOLD, 50));
                gc.fillText("PUNKTE: " + model.getPoints(), 30, Main.HEIGTH - 70);
                if (model.isLifesGlow()) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.RED);
                }
                gc.fillText("SPIELER LEBEN: " + model.getSpaceshipPlayer().getLives(), Main.WIDTH - scoreRightInbound, Main.HEIGTH - 70);
                for (SpaceshipEnemy spaceshipEnemy : model.getEnemyList()) {
                    gc.drawImage(enemySpaceshipImageSW, spaceshipEnemy.getPosX(), spaceshipEnemy.getPosY(), spaceshipEnemy.getSizeX(), spaceshipEnemy.getSizeY());
                }
                for (int i = 0; i < model.getFinalEnemies().size(); i++) {
                    gc.drawImage(finalEnemySW, model.getFinalEnemies().get(i).getPosX(), model.getFinalEnemies().get(i).getPosY(), model.getFinalEnemies().get(i).getSizeX(), model.getFinalEnemies().get(i).getSizeY());
                    if (model.getFinalEnemies().get(i).isGlow()) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.RED);
                    }
                    gc.fillText("TODESSTERN " + (i + 1) + ": " + model.getFinalEnemies().get(i).getLives(), Main.WIDTH - scoreRightInbound, 50 + i * 100);
                }
                gc.drawImage(playerSpaceshipImageSW, model.getSpaceshipPlayer().getPosX(), model.getSpaceshipPlayer().getPosY(), model.getSpaceshipPlayer().getSizeX(), model.getSpaceshipPlayer().getSizeY());
                for (Explosion explosion : model.getExplosions()) {
                    gc.drawImage(explosionImage, explosion.getPosX(), explosion.getPosY(), 30, 30);
                }
            }
            zeichneMenuScreen(loadScreenSW, playerSpaceshipImageSW, fontSW, 60);
        } else {
            if (model.getGameStatus() == 0) {
                loadScreen(loadScreen, font);
            }
            if (model.getGameStatus() == 6) {
                gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
                gc.setFill(Color.GOLD);
                gc.setFont(Font.font(font, FontWeight.BOLD, 120));
                gc.fillText("GEWONNEN", Main.WIDTH / 2 - 250, Main.HEIGTH / 2 - 70);
                gc.fillText("DRUECKE ENTER", Main.WIDTH / 2 - 380, Main.HEIGTH / 2 + 50);
                gc.fillText("UM NEU ZU BEGINNEN", Main.WIDTH / 2 - 520, Main.HEIGTH / 2 + 170);
            }
            if (model.getGameStatus() == 1 || model.getGameStatus() == 7 || model.getGameStatus() == 8 || model.getGameStatus() == 4) {
                gc.drawImage(background, 0, 0, Main.WIDTH, Main.HEIGTH);
                laserGraphics(model.getPlayerLaserList());
                laserGraphics(model.getLaserEnemyList());
                laserGraphics(model.getLaserFinalEnemyList());
                if (model.isLevelStart()) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(font, FontWeight.BOLD, 150));
                    gc.fillText("LEVEL " + this.model.getLevel() + " STARTET", Main.WIDTH / 2 - 600, Main.HEIGTH / 2);
                }
                if (model.getGameStatus() == 7) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(font, FontWeight.BOLD, 150));
                    gc.fillText("LEVEL " + this.model.getLevel() + " COMPLETE", Main.WIDTH / 2 - 600, Main.HEIGTH / 2);
                    gc.fillText("YOU SAVED EARTH", Main.WIDTH / 2 - 600, Main.HEIGTH / 2 + 150);
                }
                if (model.getGameStatus() == 8) {
                    gc.setFill(Color.GOLD);
                    gc.setFont(Font.font(font, FontWeight.BOLD, 150));
                    gc.fillText("GAME OVER", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 150);
                    gc.fillText("EARTH IS LOST", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 300);
                }
                if (model.isPointGlow()) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.RED);
                }
                gc.setFont(Font.font(font, FontWeight.BOLD, 50));
                gc.fillText("PUNKTE: " + model.getPoints(), 30, Main.HEIGTH - 70);
                if (model.isLifesGlow()) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.RED);
                }
                gc.fillText("SPIELER LEBEN: " + model.getSpaceshipPlayer().getLives(), Main.WIDTH - scoreRightInbound, Main.HEIGTH - 70);
                for (SpaceshipEnemy spaceshipEnemy : model.getEnemyList()) {
                    gc.drawImage(enemySpaceshipImage, spaceshipEnemy.getPosX(), spaceshipEnemy.getPosY(), spaceshipEnemy.getSizeX(), spaceshipEnemy.getSizeY());
                }
                for (int i = 0; i < model.getFinalEnemies().size(); i++) {
                    gc.drawImage(finalEnemyInvaders, model.getFinalEnemies().get(i).getPosX(), model.getFinalEnemies().get(i).getPosY(), model.getFinalEnemies().get(i).getSizeX(), model.getFinalEnemies().get(i).getSizeY());
                    if (model.getFinalEnemies().get(i).isGlow()) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.RED);
                    }
                    gc.fillText("ENDGEGNER " + (i + 1) + ": " + model.getFinalEnemies().get(i).getLives(), Main.WIDTH - scoreRightInbound, 50 + i * 80);
                }
                gc.drawImage(playerSpaceshipImage, model.getSpaceshipPlayer().getPosX(), model.getSpaceshipPlayer().getPosY(), model.getSpaceshipPlayer().getSizeX(), model.getSpaceshipPlayer().getSizeY());
                for (Explosion explosion : model.getExplosions()) {
                    gc.drawImage(explosionImage, explosion.getPosX(), explosion.getPosY(), 30, 30);
                }
            }
            zeichneMenuScreen(loadScreen, playerSpaceshipImage, font, 100);
        }
    }

    private void zeichneMenuScreen(Image loadScreen, Image playerSpaceshipImage, String font, double textSize) {
        if (model.getGameStatus() == 10 || model.getGameStatus() == 11) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.GOLD);
            gc.setFont(Font.font(font, FontWeight.BOLD, textSize));
            for (int i = 0; i < this.model.getMenuPoints().size(); i++) {
                gc.fillText(this.model.getMenuPoints().get(i), menuBorderInput, Main.HEIGTH / 2 + 100 + i * 80);
            }
            gc.drawImage(playerSpaceshipImage, menuBorderInput - 80, Main.HEIGTH / 2 + 45 + model.getMenuPoint() * 80, 60, 60);
        }
    }

    private void loadScreen(Image loadScreen, String font) {
        gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
        gc.setFill(Color.GOLD);
        gc.setFont(Font.font(font, FontWeight.BOLD, 100));
        gc.fillText("LEIFS SPACE INVADERS", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 50);
        gc.fillText("PRESS ANY KEY TO START", Main.WIDTH / 2 - 460, Main.HEIGTH / 2 + 150);
    }


    // Methode um LaserListen zu zeichnen
    private void laserGraphics(LinkedList<Laser> laserList) {
        for (Laser laser : laserList) {
            gc.setFill(laser.getColor());
            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
        }
    }
}

package de.awacademy.invaders;

import de.awacademy.invaders.model.Explosion;
import de.awacademy.invaders.model.Laser;
import de.awacademy.invaders.model.Model;
import de.awacademy.invaders.model.SpaceshipEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;

public class Graphics {

    private Model model;
    private GraphicsContext gc;
    private Image enemySpaceshipImage = new Image("de/awacademy/invaders/model/images/EnemySpaceship.png");
    private Image playerSpaceshipImage = new Image("de/awacademy/invaders/model/images/SpaceShipPlayer.png");
    private Image explosionImage = new Image("de/awacademy/invaders/model/images/explosion.png");
    private Image loadScreen = new Image("de/awacademy/invaders/model/images/Space-Invaders-LoadScreen.jpg");
    private Image background = new Image("de/awacademy/invaders/model/images/background.jpg");
    private final int menuBorderInput = 200;

    public Graphics(Model model, GraphicsContext gc) {
        this.model = model;
        this.gc = gc;
    }

    // Hier wird alles gezeichnet
    public void draw() {
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, 1200, 750);
        if (model.getGameStatus() == 0) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.GOLD);
            gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 100));
            gc.fillText("LEIFS SPACE INVADERS", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 50);
            gc.fillText("PRESS ANY KEY TO START", Main.WIDTH / 2 - 460, Main.HEIGTH / 2 + 150);
        }
        if (model.getGameStatus() == 1 || model.getGameStatus() == 7 || model.getGameStatus() == 8 || model.getGameStatus() == 4) {
            gc.drawImage(background, 0, 0, Main.WIDTH, Main.HEIGTH);
            if (model.getGameStatus() == 7) {
                gc.setFill(Color.GOLD);
                gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 150));
                gc.fillText("YOU SAVED EARTH", Main.WIDTH / 2 - 600, Main.HEIGTH / 2 + 150);
            }
            if (model.getGameStatus() == 8) {
                gc.setFill(Color.GOLD);
                gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 150));
                gc.fillText("GAME OVER", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 150);
                gc.fillText("EARTH IS LOST", Main.WIDTH / 2 - 400, Main.HEIGTH / 2 + 300);
            }
            if (model.isPointGlow()) {
                gc.setFill(Color.WHITE);
            } else {
                gc.setFill(Color.RED);
            }
            gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 50));
            gc.fillText("PUNKTE: " + model.getPoints(), 30, Main.HEIGTH - 70);
            if (model.isLifesGlow()) {
                gc.setFill(Color.WHITE);
            } else {
                gc.setFill(Color.RED);
            }
            gc.fillText("SPIELER LEBEN: " + model.getSpaceshipPlayer().getLives(), Main.WIDTH / 2 + 60, Main.HEIGTH - 70);
            for (SpaceshipEnemy spaceshipEnemy : model.getEnemyList()) {
                gc.drawImage(enemySpaceshipImage, spaceshipEnemy.getPosX(), spaceshipEnemy.getPosY(), 30, 30);
            }
            for (Explosion explosion : model.getExplosions()) {
                gc.drawImage(explosionImage, explosion.getPosX(), explosion.getPosY(), 30, 30);
            }
            laserGraphics(model.getPlayerLaserList());
            laserGraphics(model.getLaserEnemyList());
            gc.drawImage(playerSpaceshipImage, model.getSpaceshipPlayer().getPosX(), model.getSpaceshipPlayer().getPosY(), 40, 40);
        }
        if (model.getGameStatus() == 2) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.GOLD);
            gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 120));
            gc.fillText("YOU SAVED EARTH", Main.WIDTH / 2 - 600, Main.HEIGTH / 2 + 150);
        }
        if (model.getGameStatus() == 3) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.GOLD);
            gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 180));
            gc.fillText("GAME OVER", Main.WIDTH / 2 - 480, Main.HEIGTH / 2 + 150);
        }
        if (model.getGameStatus() == 10) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.GOLD);
            gc.setFont(Font.font("Digital-7", FontWeight.BOLD, 100));
            for (int i = 0; i < this.model.getMenuPoints().size(); i++) {
                gc.fillText(this.model.getMenuPoints().get(i), menuBorderInput, Main.HEIGTH / 2 + 100 + i * 80);
            }
            gc.drawImage(playerSpaceshipImage, menuBorderInput - 80, Main.HEIGTH / 2 + 40 + model.getMenuPoint() * 80, 60, 60);

        }
    }


    // Methode um LaserListen zu zeichnen
    private void laserGraphics(LinkedList<Laser> laserList) {
        for (Laser laser : laserList) {
            gc.setFill(laser.getColor());
            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
        }
    }
}

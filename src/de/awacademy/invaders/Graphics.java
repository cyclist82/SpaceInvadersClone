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

    public Graphics(Model model, GraphicsContext gc) {
        this.model = model;
        this.gc = gc;
    }

    // Hier wird alles gezeichnet
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1200, 750);
        if (model.getGameStatus() == 0) {
            gc.drawImage(loadScreen, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
            gc.fillText("LEIFS SPACE INVADERS", 120, 410);
            gc.fillText("PRESS ANY KEY TO START", 90, 500);
        }
        if (model.getGameStatus() == 1) {
            gc.drawImage(background, 0, 0, Main.WIDTH, Main.HEIGTH);
            gc.setFill(Color.RED);
            gc.setFont(new Font(50));
            gc.fillText("Punktestand: " + model.getPoints() + "       Spieler Leben: " + model.getSpaceshipPlayer().getLives(), 30, Main.HEIGTH - 70);
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
    }


    // Methode um LaserListen zu zeichnen
    private void laserGraphics(LinkedList<Laser> laserList) {
        for (Laser laser : laserList) {
            gc.setFill(laser.getColor());
            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
        }
    }
}

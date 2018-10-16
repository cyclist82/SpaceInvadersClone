package de.awacademy.invaders;

import de.awacademy.invaders.model.Laser;
import de.awacademy.invaders.model.Model;
import de.awacademy.invaders.model.SpaceshipEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Graphics {

    private Model model;
    private GraphicsContext gc;

    public Graphics(Model model, GraphicsContext gc) {
        this.model = model;
        this.gc = gc;
    }

    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1200, 750);

        gc.setFill(Color.WHITE);
        gc.fillText("Hallo Welt " + model.getCounter() + " Punktestand: " + model.getPoints() + " Spiele Leben: " + model.getSpaceshipPlayer().getLives(), 30, 30);
        for (SpaceshipEnemy spaceshipEnemy : model.getEnemyList()) {
            gc.drawImage(spaceshipEnemy.getImage(), spaceshipEnemy.getPosX() - 15, spaceshipEnemy.getPosY() - 15, 30, 30);
        }
        laserGraphics(model.getPlayerLaserList());
        laserGraphics(model.getLaserEnemyList());
//        for (Laser laser : model.getPlayerLaserList()) {
//            gc.setFill(laser.getColor());
//            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
//        }
//        for (Laser laser : model.getLaserEnemyList()) {
//            gc.setFill(laser.getColor());
//            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
//        }
        gc.drawImage(model.getSpaceshipPlayer().getImage(), model.getSpaceshipPlayer().getPosX(), model.getSpaceshipPlayer().getPosY(), 40, 40);
    }

    public void laserGraphics(ArrayList<Laser> laserList) {
        for (Laser laser : laserList) {
            gc.setFill(laser.getColor());
            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
        }
    }
}

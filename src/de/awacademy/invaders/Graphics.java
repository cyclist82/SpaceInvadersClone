package de.awacademy.invaders;

import de.awacademy.invaders.model.Laser;
import de.awacademy.invaders.model.Model;
import de.awacademy.invaders.model.SpaceshipEnemy;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;

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
        gc.fillText("Hallo Welt " + model.getCounter(), 30, 30);
        gc.fillRect(model.getCounter() / 10, model.getCounter() / 20, 40, 40);
        for (SpaceshipEnemy spaceshipEnemy : model.getEnemyList()) {
            gc.drawImage(spaceshipEnemy.getImage(), spaceshipEnemy.getPosX() - 20, spaceshipEnemy.getPosY() - 20, 40, 40);
        }
        for(Laser laser:model.getLaserList()){
            gc.setFill(laser.getColor());
            gc.fillRect(laser.getPosX(), laser.getPosY(), laser.getWidth(), laser.getLength());
        }
        gc.drawImage(model.getSpaceshipPlayer().getImage(), model.getSpaceshipPlayer().getPosX(), model.getSpaceshipPlayer().getPosY(),40,40);
    }
}

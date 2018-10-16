package de.awacademy.invaders.model;

import javafx.scene.image.Image;

public class SpaceshipEnemy {

    private double posY,posX;
    private int lives=1;

    public SpaceshipEnemy(double posY, double posX) {
        this.posY = posY;
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}

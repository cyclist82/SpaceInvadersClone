package de.awacademy.invaders.model;

import javafx.scene.shape.Circle;

public class FinalEnemy {

    private double posX, posY;
    private final double sizeX = 200, sizeY = 200;
    private int lives = 30;
    Circle circle = new Circle(100, posX+100, posY+100);

    public FinalEnemy(double posX, double posY) {
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

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }
}


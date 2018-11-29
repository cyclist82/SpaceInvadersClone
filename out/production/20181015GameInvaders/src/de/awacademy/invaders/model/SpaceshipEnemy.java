package de.awacademy.invaders.model;

public class SpaceshipEnemy {

    private double posY, posX;
    private final double sizeX = 30, sizeY = 30;
    private int lives = 1;

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

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }
}

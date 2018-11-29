package de.awacademy.invaders.model;

public class SpaceshipPlayer {

    private double posX, posY;
    private final double sizeX = 40, sizeY = 40;
    private int lives = 5;

    public SpaceshipPlayer(double posX, double posY) {
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

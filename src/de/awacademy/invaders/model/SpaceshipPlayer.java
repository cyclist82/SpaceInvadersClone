package de.awacademy.invaders.model;

import javafx.scene.image.Image;

public class SpaceshipPlayer {

    private double posX,posY;
    private Image image=new Image("de/awacademy/invaders/model/images/SpaceShipPlayer.png");
    private int lives=5;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}

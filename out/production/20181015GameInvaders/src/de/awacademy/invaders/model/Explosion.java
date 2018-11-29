package de.awacademy.invaders.model;

import javafx.scene.image.Image;

public class Explosion {

    private double posX, posY, timeCreated;
    private double timeAlive = 800;
    private boolean alive = true;

    public Explosion(double posY, double posX, double timeCreated) {
        this.posY = posY;
        this.posX = posX;
        this.timeCreated = timeCreated;
    }

    public double getTimeCreated() {
        return timeCreated;
    }

    public double getPosX() {
        return posX;
    }

    public void setTimeCreated(double timeCreated) {
        this.timeCreated = timeCreated;
    }

    public double getTimeAlive() {
        return timeAlive;
    }

    public double getPosY() {
        return posY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

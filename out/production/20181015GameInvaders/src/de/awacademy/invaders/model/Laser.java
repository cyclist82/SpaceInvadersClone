package de.awacademy.invaders.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Laser {
    private double posX, posY;
    private double speed = 0.5;
    private double width = 3;
    private double length = 5;
    private Color color = Color.RED;
    private boolean alive = true;
    private int direction;

    public Laser(double posX, double posY, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public Laser(double posX, double posY, Color color, int direction) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.direction = direction;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public Color getColor() {
        return color;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDirection() {
        return direction;
    }
}

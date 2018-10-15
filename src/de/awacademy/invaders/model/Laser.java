package de.awacademy.invaders.model;

import javafx.scene.paint.Color;

public class Laser {
    private double posX,posY;
    private double speed=8;
    private double width=3;
    private double length=5;
    private Color color=Color.RED;

    public Laser(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
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
}

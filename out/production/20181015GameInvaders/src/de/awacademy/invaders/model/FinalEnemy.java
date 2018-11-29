package de.awacademy.invaders.model;

public class FinalEnemy {

    private double posX, posY;
    private final double sizeX = 200, sizeY = 200;
    private int lives = 30;
    private boolean glow = false;
    private long timeStampGlow;
    private boolean isMovingRight;
    private boolean isMovingDown;

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

    public boolean isGlow() {
        return glow;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public long getTimeStampGlow() {
        return timeStampGlow;
    }

    public void setTimeStampGlow(long timeStampGlow) {
        this.timeStampGlow = timeStampGlow;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDown(boolean movingDown) {
        isMovingDown = movingDown;
    }
}


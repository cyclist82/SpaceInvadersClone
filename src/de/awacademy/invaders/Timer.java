package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import de.awacademy.invaders.model.SpaceshipEnemy;
import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {

    private Model model;
    private Graphics graphics;

    long lastMillis = -1;

    public Timer(Model model, Graphics graphics) {
        this.model = model;
        this.graphics = graphics;
    }

    @Override
    public void handle(long now) {
        long millis = now / 1000000;
        // Unterschied seit letztes Grafik-Ausgabe berechnen
        long deltaMillis = 0;
        if (lastMillis != -1) {
            deltaMillis = millis - lastMillis;
        }
        lastMillis = millis;
        this.model.update(deltaMillis);
        this.model.enemyFleetMovement();
        this.model.laserMovement();
        //Grafik soll gezeichnet werden
        graphics.draw();
    }
}

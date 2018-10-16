package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {

    private Model model;
    private Graphics graphics;
//    private Sounds sounds;

    long lastMillis = -1;

    public Timer(Model model, Graphics graphics) {
        this.model = model;
        this.graphics = graphics;
//        this.sounds = sounds;
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
        //Grafik soll gezeichnet werden
        graphics.draw();
    }

    public long getLastMillis() {
        return lastMillis;
    }
}

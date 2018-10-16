package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.*;

public class InputHandler {


    private Model model;

    // Konstruktor
    public InputHandler(Model model) {
        this.model = model;
    }

    public void onKeyPressed(KeyEvent event) {
//        if (event.getCode() == KeyCode.SPACE) {
//            this.model.fireLaserPlayer();
//        }
//        if (event.getCode() == KeyCode.LEFT) {
//            this.model.spaceshipPlayerLeft();
//        }
//        if (event.getCode() == KeyCode.RIGHT) {
//            this.model.spaceshipPlayerRight();
//        }
//        if (event.getCode() == KeyCode.UP) {
//            this.model.spaceshipPlayerUp();
//        }
//        if (event.getCode() == KeyCode.DOWN) {
//            this.model.spaceshipPlayerDown();
//        }
//        if (event.getCode() == KeyCode.N) {
//            this.model.createEnemyFleet(3);
//        }
        if (event.getCode() == KeyCode.SPACE) {
            this.model.fireLaserPlayer();
        }
        if (event.getCode() == KeyCode.LEFT) {
            this.model.setLeft(true);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            this.model.setRight(true);
        }
        if (event.getCode() == KeyCode.UP) {
            this.model.setUp(true);
        }
        if (event.getCode() == KeyCode.DOWN) {
            this.model.setDown(true);
        }
        if (event.getCode() == KeyCode.N) {
            this.model.createEnemyFleet(3);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.model.fireLaserPlayer();
        }
        if (event.getCode() == KeyCode.LEFT) {
            this.model.setLeft(false);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            this.model.setRight(false);
        }
        if (event.getCode() == KeyCode.UP) {
            this.model.setUp(false);
        }
        if (event.getCode() == KeyCode.DOWN) {
            this.model.setDown(false);
        }
    }
    //    public void onClick(MouseEvent event) {
//    }
}

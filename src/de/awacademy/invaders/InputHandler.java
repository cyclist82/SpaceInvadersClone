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
            this.model.setAnyKey(true);
        if (event.getCode() == KeyCode.ENTER) {
            this.model.setEnterKey(true);
        }
        if (event.getCode() == KeyCode.SPACE) {
            this.model.setSpaceKey(true);
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
        if (event.getCode() == KeyCode.ESCAPE) {
            this.model.setEscapeKey(true);
        }
        if (event.getCode() == KeyCode.N) {
            this.model.createEnemyFleet(3);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        this.model.setAnyKey(false);
        if (event.getCode() == KeyCode.ESCAPE) {
            this.model.setEscapeKey(false);
        }
        if (event.getCode() == KeyCode.ENTER) {
            this.model.setEnterKey(false);
        }
        if (event.getCode() == KeyCode.SPACE) {
            this.model.setSpaceKey(false);
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

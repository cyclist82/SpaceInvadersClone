package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class InputHandler {


    private Model model;

    // Konstruktor
    public InputHandler(Model model) {
        this.model = model;
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.model.resetCounter();
        }
        if (event.getCode() == KeyCode.LEFT) {
            this.model.spaceshipPlayerLeft();
        }
        if (event.getCode() == KeyCode.RIGHT) {
            this.model.spaceshipPlayerRight();
        }
        if (event.getCode() == KeyCode.UP) {
            this.model.spaceshipPlayerUp();
        }
        if (event.getCode() == KeyCode.DOWN) {
            this.model.spaceshipPlayerDown();
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.model.resetCounter();
        }
    }

    public void onClick(MouseEvent event) {
        model.newSpaceshipEnemy(event.getX(), event.getY());
    }
}

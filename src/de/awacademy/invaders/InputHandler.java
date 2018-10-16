package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import javafx.scene.input.*;

public class InputHandler {


    private Model model;
    
    // Konstruktor
    public InputHandler(Model model) {
        this.model = model;
    }


    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.model.fireLaserPlayer();
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
        if (event.getCode() == KeyCode.S) {
            this.model.enemyFleetFireLaser();
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
//            this.model.resetCounter();
        }
    }

//    public void onClick(MouseEvent event) {
//    }
}

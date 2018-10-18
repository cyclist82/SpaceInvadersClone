package de.awacademy.invaders;

import de.awacademy.invaders.model.Model;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static final double WIDTH = 1920;
    public static final double HEIGTH = 1080;

    private Timer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        javafx.scene.canvas.Canvas canvas = new Canvas(WIDTH, HEIGTH);
        Group group = new Group();
        group.getChildren().addAll(canvas);

        // Scene
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        primaryStage.setTitle("Leifs Space Invaders");
        primaryStage.setMaximized(true);

        Model model = new Model();
        Graphics graphics = new Graphics(model, canvas.getGraphicsContext2D());
        timer = new Timer(model, graphics);

        // InputHandler
        InputHandler inputHandler = new InputHandler(model);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                inputHandler.onKeyPressed(event);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                inputHandler.onKeyReleased(event);
            }
        });

//        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                inputHandler.onClick(event);
//            }
//        });
        // Timer starten
        timer.start();
    }

    @Override
    public void stop() throws Exception {
        timer.stop();
        super.stop();
    }
}

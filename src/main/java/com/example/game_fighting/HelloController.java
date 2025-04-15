package com.example.game_fighting;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashSet;

public class HelloController {

    @FXML
    private Pane gamePane;

    private int health1 = 100;
    private int health2 = 100;

    private Rectangle healthBar1;
    private Rectangle healthBar2;

    private Player player1;
    private Player player2;

    private final HashSet<KeyCode> keys = new HashSet<>();

    public void initialize() {
        //create sa players
        player1 = new Player(100, 300, Color.RED, true);
        player2 = new Player(650, 300, Color.BLUE, true);

        gamePane.getChildren().addAll(player1.getBody(), player2.getBody());

        //create sa health bars
        healthBar1 = new Rectangle(100, 10, Color.LIMEGREEN);
        healthBar1.setX(20);
        healthBar1.setY(20);

        healthBar2 = new Rectangle(100, 10, Color.LIMEGREEN);
        healthBar2.setX(680);
        healthBar2.setY(20);

        gamePane.getChildren().addAll(healthBar1, healthBar2);

        //input handling
        gamePane.setFocusTraversable(true);
        Platform.runLater(() -> gamePane.requestFocus());

        gamePane.setOnKeyPressed(this::handleKeyPressed);
        gamePane.setOnKeyReleased(this::handleKeyReleased);

        //start game
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                System.out.println("PLAYER 1 = "+health1);
                System.out.println("PLAYER 2 = "+health2);
            }
        }.start();
    }

    private void handleKeyPressed(KeyEvent event) {
        keys.add(event.getCode());
    }

    private void handleKeyReleased(KeyEvent event) {
        keys.remove(event.getCode());
    }

    public void update() {
        //player 1
        if (keys.contains(KeyCode.A)) player1.move(-5, 0);
        if (keys.contains(KeyCode.D)) player1.move(5, 0);

        if (keys.contains(KeyCode.F)) {
            //change shape when attacking
                gamePane.getChildren().remove(player1.getBody());
                player1.setShapeToCircle();
                gamePane.getChildren().add(player1.getBody());

            //delay before changing back
            PauseTransition pause2 = new PauseTransition(Duration.millis(300));
            pause2.setOnFinished(event -> {
                    gamePane.getChildren().remove(player1.getBody());
                    player1.setShapeToRectangle();
                    gamePane.getChildren().add(player1.getBody());
            });
            pause2.play();
        }

        //player2
        if (keys.contains(KeyCode.LEFT)) player2.move(-5, 0);
        if (keys.contains(KeyCode.RIGHT)) player2.move(5, 0);

        if (keys.contains(KeyCode.L)) {
            //change shape when attacking
                gamePane.getChildren().remove(player2.getBody());
                player2.setShapeToCircle2();
                gamePane.getChildren().add(player2.getBody());

            //delay before changing back
            PauseTransition pause2 = new PauseTransition(Duration.millis(300));
            pause2.setOnFinished(event -> {
                    gamePane.getChildren().remove(player2.getBody());
                    player2.setShapeToRectangle2();
                    gamePane.getChildren().add(player2.getBody());
            });
            pause2.play();
        }

        //attack hit check
        if (keys.contains(KeyCode.F)) {
            if (player1.getBoundsInParent().intersects(player2.getBoundsInParent())) {
                player2.setColor(Color.LIMEGREEN);

                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(event -> player2.setColor(Color.BLUE));
                pause.play();

                health2 = Math.max(0, health2 - 1);
                updateHealthBars();
            }
        }

        if (keys.contains(KeyCode.L)) {
            if (player2.getBoundsInParent().intersects(player1.getBoundsInParent())) {
                player1.setColor(Color.LIMEGREEN);

                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(event -> player1.setColor(Color.RED));
                pause.play();

                health1 = Math.max(0, health1 - 1);
                updateHealthBars();
            }
        }

        if (health1 == 0 || health2 == 0) {
            endGame();
        }
    }

    private void updateHealthBars() {
            healthBar1.setWidth(health1);
            healthBar2.setWidth(health2);
    }

    private void endGame() {
            gamePane.setOnKeyPressed(null);
            gamePane.setOnKeyReleased(null);

            System.out.println(health1 == 0 ? "Player 2 Wins!" : "Player 1 Wins!");
    }
}

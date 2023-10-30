
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NewAsteroidsMenu {

    private Stage stage;

    public void start(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        displaymenu();
    }

    private void displaymenu() {
        VBox menuLayout = createMenuLayout();
        Scene menuScene = new Scene(menuLayout, Constant.WIDTH, Constant.HEIGHT);
        Image appIcon = new Image(new File("public/asteroid-icon.jpg").toURI().toString());
        stage.getIcons().add(appIcon);
        stage.setScene(menuScene);
        stage.setTitle("Asteroids Menu");
        stage.show();
    }

    private VBox createMenuLayout() {
        Label title = createTitle("Asteroids Menu", 44);
        Button startbutton = createMenuButton("Start", event -> showNameScreenInput());
        Button hallOfFameButton = createMenuButton("Hall of Fame", event -> displayHallOfFame());
        Button controlsButton = createMenuButton("Controls", event -> displayControls());
        Button quitButton = createMenuButton("Quit", event -> stage.close());

        VBox menuLayout = new VBox(15);
        menuLayout.getChildren().addAll(title, startbutton, hallOfFameButton, controlsButton, quitButton);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setBackground(createBackground("background_image.jpg"));

        return menuLayout;
    }

    private Label createTitle(String text, int fontSize) {
        Label label = new Label(text);
        label.setTextFill(Color.web("#ffffff"));

        label.setFont(Font.font("Helvetica", FontWeight.BOLD, fontSize));
        return label;
    }

    private Button createMenuButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setPrefWidth(225);
        button.setPrefHeight(75);
        button.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
        button.setStyle("-fx-background-color: #4b0082; -fx-text-fill: #ffffff;");
        button.setOnAction(eventHandler);
        return button;
    }

    private Background createBackground(String imageName) {
        Image bgImage = new Image(new File("public/" + imageName).toURI().toString());
        return new Background(new BackgroundImage(
                bgImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
    }

    private void displayControls() {
        VBox controlsLayout = createControlsLayout();
        Scene controlScene = new Scene(controlsLayout, Constant.WIDTH, Constant.HEIGHT);
        stage.setScene(controlScene);
        stage.setTitle("Controls");
    }

    private VBox createControlsLayout() {
        VBox layout = new VBox(10); // Create a VBox layout with 10 px vertical spacing
        layout.setAlignment(Pos.CENTER); // Align content to the center
        layout.setStyle("-fx-background-color: black;"); // Set the background color to black

        // Create and style the title label
        Label titleLabel = new Label("Controls");
        titleLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 50));
        titleLabel.setTextFill(Color.WHITE);

        // Create a VBox layout for the control list
        VBox controlVBox = new VBox(5); // 5 px vertical spacing
        controlVBox.setAlignment(Pos.CENTER);

        String[] controlsList = {
                "Up arrow: Thrust",
                "Left arrow: Left rotation",
                "Right arrow: Right rotation",
                String.format("Left Shift: Jump in Hyperspace (CD: %ds)",
                        Constant.HYPER_SPACE_JUMP_CD / 1_000_000_000L),
                "Space: Shoot"
        };

        for (String control : controlsList) {
            Label controlLabel = new Label(control);
            controlLabel.setFont(Font.font("Courier New", FontWeight.NORMAL, 30));
            controlLabel.setTextFill(Color.WHITE);
            controlVBox.getChildren().add(controlLabel);
        }

        // Create and style the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaymenu());

        // Add all elements to the VBox layout
        layout.getChildren().addAll(titleLabel, controlVBox, backButton);

        return layout;
    }

    private void displayHallOfFame() {
        VBox hallOfFameLayout = createHallOfFameLayout();
        Scene hallOfFameScene = new Scene(hallOfFameLayout, Constant.WIDTH, Constant.HEIGHT);
        stage.setScene(hallOfFameScene);
        stage.setTitle("High Scores");
    }

    private VBox createHallOfFameLayout() {
        VBox layout = new VBox(10); // Create a VBox layout with 10 px vertical spacing
        layout.setAlignment(Pos.CENTER); // Align content to the center
        layout.setStyle("-fx-background-color: black;"); // Set the background color to black

        // Create and style the title label
        Label titleLabel = new Label("High Scores");
        titleLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 50));
        titleLabel.setTextFill(Color.WHITE);

        // Create a VBox layout for the high scores list
        VBox scoresList = new VBox(5); // 5 px vertical spacing
        scoresList.setAlignment(Pos.CENTER);

        List<String> highScores = readTheHallOfFame(Constant.SCORE_FILE);
        for (String score : highScores) {
            Label scoreLabel = new Label(score);
            scoreLabel.setFont(Font.font("Courier New", FontWeight.NORMAL, 30));
            scoreLabel.setTextFill(Color.WHITE);
            scoresList.getChildren().add(scoreLabel);
        }

        // Create and style the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaymenu());

        // Add all elements to the VBox layout
        layout.getChildren().addAll(titleLabel, scoresList, backButton);

        return layout;
    }

    private List<String> readTheHallOfFame(String inputfilename) {
        List<String> hallOfFame = new ArrayList<>();
        String filePath = "public/" + inputfilename;
        try {
            hallOfFame = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hallOfFame;
    }

    private void showNameScreenInput() {
        VBox nameScreenLayout = createNameScreenLayout();
        Scene nameScene = new Scene(nameScreenLayout, Constant.WIDTH, Constant.HEIGHT);
        stage.setScene(nameScene);
        stage.setTitle("Enter Player Name");
    }

    private VBox createNameScreenLayout() {

        Text nameLabel = new Text("Enter player name:");
        nameLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        nameLabel.setFill(Color.WHITE);

        TextField nameInput = new TextField();

        nameInput.setPrefWidth(200);
        nameInput.setMaxWidth(200);
        HBox nameInputContainer = new HBox(nameInput);
        nameInputContainer.setAlignment(Pos.CENTER);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            String name = nameInput.getText();

            Stage xStage = (Stage) continueButton.getScene().getWindow();
            xStage.setTitle("Asteroids Game");
            AsteroidsGame game = new AsteroidsGame(xStage, name);

            game.start();
        });

        Button goback = new Button("Back");
        goback.setOnAction(event -> displaymenu());

        VBox nameScreenLayout = new VBox(15);
        nameScreenLayout.setAlignment(Pos.CENTER);
        nameScreenLayout.setBackground(createBackground("background_image.jpg"));
        nameScreenLayout.getChildren().addAll(nameLabel, nameInput, continueButton, goback);

        return nameScreenLayout;
    }

}

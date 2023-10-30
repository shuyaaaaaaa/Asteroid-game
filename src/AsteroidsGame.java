import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class AsteroidsGame {

    private Stage stage;
    private String playerName;
    private int playerLives;
    private int currentLevel = 1;
    private PlayerShip playerShip;
    private int currentScore = 0;

    public AsteroidsGame(Stage stage, String playerName) {
        this.stage = stage;
        this.playerName = playerName;
        this.playerLives = Constant.PLAYER_MAX_LIVES;
        this.playerShip = Generator.generatePlayerShip();
    }

    public void start() {
        levelScene();
    }

    public void levelScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, Constant.WIDTH, Constant.HEIGHT);

        Text levelText = new Text("Level " + currentLevel);
        levelText.setFont(Font.font("Courier New", FontWeight.BOLD, 100));
        levelText.setTextOrigin(VPos.CENTER);
        levelText.setFill(Color.WHITE);

        VBox vbox = new VBox(levelText);
        vbox.setSpacing(20); // Set the spacing between the Text objects
        vbox.setAlignment(Pos.CENTER); // Center the Text objects in the VBox
        root.getChildren().add(vbox);

        stage.setScene(scene);
        stage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Pause for at least 2 seconds
        pause.setOnFinished(e -> {
            gameScene(); // Update the game scene after pause
            stage.requestFocus(); // Request focus for key events on the game scene
        });
        pause.play();

    }

    public void gameOverScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, Constant.WIDTH, Constant.HEIGHT);

        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Courier New", FontWeight.BOLD, 100));
        gameOverText.setTextOrigin(VPos.CENTER);
        gameOverText.setFill(Color.WHITE);

        Text promptText = new Text("Press Enter to Return");
        promptText.setFont(Font.font("Courier New", FontWeight.NORMAL, 40));
        promptText.setFill(Color.WHITE);

        VBox vbox = new VBox(gameOverText, promptText);
        vbox.setSpacing(20); // Set the spacing between the Text objects
        vbox.setAlignment(Pos.CENTER); // Center the Text objects in the VBox
        root.getChildren().add(vbox);

        stage.setScene(scene);
        stage.show();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NewAsteroidsMenu menu = new NewAsteroidsMenu();
                menu.start(stage);
            }
        });

    }

    public void gameScene() {
        // 1.Create a new scene
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, Constant.WIDTH, Constant.HEIGHT);

        // Current Score
        Text scoreText = new Text("Current Score: " + currentScore);
        scoreText.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        scoreText.setX(10); // set a small margin from the left edge
        scoreText.setY(30); // set a small margin from the top edge
        scoreText.setFill(Color.WHITE);
        root.getChildren().add(scoreText);

        // Current life
        Text lifeText = new Text("Current Life: " + playerLives);
        lifeText.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        lifeText.setX(10); // set a small margin from the left edge
        lifeText.setY(60); // set a small margin from the top edge and place it under the scoreText

        lifeText.setFill(Color.WHITE);
        root.getChildren().add(lifeText);

        // Level
        Text levelText = new Text("Level: " + currentLevel);
        levelText.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        levelText.setTranslateX(10);
        levelText.setTranslateY(90);
        levelText.setFill(Color.WHITE);
        root.getChildren().add(levelText);

        // 2.Create Arraylist of keys that are currently being pressed
        ArrayList<String> pressedKeys = new ArrayList<>();
        // Event Listener attached to the game scene, adds a key to an arraylist when
        // pressed
        scene.setOnKeyPressed(
                // Get the name of the key pressed and add it to the key pressed list
                (KeyEvent key) -> {
                    // Get the code for the key and the turn it into a string
                    String keyName = key.getCode().toString();
                    // if condition to prevent duplicates
                    if (!pressedKeys.contains(keyName))
                        pressedKeys.add(keyName);
                });

        scene.setOnKeyReleased(
                (KeyEvent key) -> {
                    String keyName = key.getCode().toString();
                    if (pressedKeys.contains(keyName))
                        pressedKeys.remove(keyName);
                });

        // 3.create entities
        // 3.1 create PlayerShip
        root.getChildren().add(playerShip.getComponent());
        // 3.2 create AlienShips list
        List<AlienShip> alienShips = new ArrayList<>();
        // 3.3 create Asteroids list (level 1 - 1 Asteroid, level 2- 2 Asteroids....)
        List<Asteroid> asteroids = Generator.generateInitAsteroids(currentLevel);
        for (Asteroid asteroid : asteroids) {
            root.getChildren().add(asteroid.getComponent());
        }

        // create Player Bullets
        List<Bullet> playerBullets = new ArrayList<>();
        // create Alien Bullets
        List<Bullet> alienBullets = new ArrayList<>();
        // create dusts
        List<Dust> dusts = new ArrayList<>();
        // create enemy list to hold all the enemies. Helpful to calculate safe location
        List<Entity> enemies = getEnemies(asteroids, alienShips, alienBullets);

        // 4.Start AnimationTimer
        new AnimationTimer() {
            private long lastHyperSpaceJump = 0;
            private long lastFire = 0;
            private long lastGenerateAlienShip = 0;
            private long lastAlienShipFire = 0;

            @Override
            public void handle(long now) {
                // 1. Reaction to keys
                /*
                 * The code for the reaction to keys was written using video
                 * https://www.youtube.com/watch?v=9xsT6Z6HQfw
                 */
                if (pressedKeys.contains("LEFT")) {
                    playerShip.rotateLeft(Constant.PLAYER_ROTATE_DELTA);
                }

                if (pressedKeys.contains("RIGHT")) {
                    playerShip.rotateRight(Constant.PLAYER_ROTATE_DELTA);
                }

                if (pressedKeys.contains("UP")) {
                    playerShip.applyThrust();
                    playerShip.setFireEffectVisible(true); // Show the fire effect
                } else {
                    playerShip.setFireEffectVisible(false); // Hide the fire effect
                }

                if (pressedKeys.contains("SPACE") && now - lastFire > Constant.FIRE_CD) {
                    Bullet bullet = playerShip.fire();
                    SoundManager.playSound(SoundEffect.SHOOT.getFileName());
                    playerBullets.add(bullet);
                    root.getChildren().add(bullet.getComponent());
                    lastFire = now;
                }

                if (pressedKeys.contains("SHIFT") && now - lastHyperSpaceJump > Constant.HYPER_SPACE_JUMP_CD) {
                    // Point2D SafeLocation = generator.generateSafePoint(enemies);
                    Point2D SafeLocation = Generator.generateSafePoint(enemies);
                    playerShip.hyperSpaceJump(SafeLocation);
                    lastHyperSpaceJump = now;
                }
                // 2.Automatic Events
                // AlienShip appears randomly according to possibility
                if (alienShips.size() == 0) {
                    // higher level, higher possibility to generate alien ship
                    double possibility = Constant.ALEINSHIP_GENERATE_BASE_POSSIBILITY * currentLevel;
                    AlienShip newAlienShip = Generator.generateAlienShip(possibility);
                    if ((newAlienShip != null) && (now - lastGenerateAlienShip) > Constant.ALEINSHIP_GENERATE_CD) {
                        alienShips.add(newAlienShip);
                        enemies.add(newAlienShip);
                        root.getChildren().add(newAlienShip.getComponent());
                        lastGenerateAlienShip = now;
                    }
                }
                // AlienShips fire
                for (AlienShip alienShip : alienShips) {
                    if (alienShip.isAlive() && (now - lastAlienShipFire) > Constant.ALIENSHIP_FIRE_CD) {
                        Bullet bullet = alienShip.fire(playerShip);
                        alienBullets.add(bullet);
                        enemies.add(bullet);
                        root.getChildren().add(bullet.getComponent());
                        lastAlienShipFire = now;
                    }
                }
                // 3. Automatic Move
                playerShip.move();
                for (AlienShip alienShip : alienShips) {
                    alienShip.move();
                }
                for (Asteroid asteroid : asteroids) {
                    asteroid.move();
                }
                for (Bullet bullet : playerBullets) {
                    bullet.move();
                }
                for (Bullet bullet : alienBullets) {
                    bullet.move();
                }
                // move dusts
                for (Dust dust : dusts) {
                    dust.move();
                }
                // 4. Check Collision
                for (Bullet bullet : playerBullets) {
                    // asteroids
                    for (Asteroid existingAsteroid : asteroids) {
                        if (bullet.collided(existingAsteroid)) {
                            bullet.setAlive(false);
                            existingAsteroid.setAlive(false);

                            // Create dusts when the asteroids get hit by the bullets
                            for (int i = 0; i < 3; i++) {
                                Dust dust = bullet.dust();
                                dusts.add(dust);
                                root.getChildren().add(dust.getComponent());
                            }

                            if (existingAsteroid.getSize() == AsteroidSize.LARGE) {
                                // get the coordinates of the destroyed Asteroid
                                double x = existingAsteroid.getComponent().getTranslateX();
                                double y = existingAsteroid.getComponent().getTranslateY();

                                // Add two medium asteroids at the same location
                                List<Asteroid> newAsteroids = new ArrayList<>();
                                newAsteroids.add(new Asteroid(x, y, AsteroidSize.MEDIUM));
                                newAsteroids.add(new Asteroid(x, y, AsteroidSize.MEDIUM));

                                for (Asteroid asteroid : newAsteroids) {
                                    asteroid.changeSpeed(existingAsteroid.getSpeed());
                                    asteroids.add(asteroid);
                                    root.getChildren().add(asteroid.getComponent());
                                }
                            } else if (existingAsteroid.getSize() == AsteroidSize.MEDIUM) {

                                double x = existingAsteroid.getComponent().getTranslateX();
                                double y = existingAsteroid.getComponent().getTranslateY();

                                // Add two small asteroids at the same location
                                List<Asteroid> newAsteroids = new ArrayList<>();
                                newAsteroids.add(new Asteroid(x, y, AsteroidSize.SMALL));
                                newAsteroids.add(new Asteroid(x, y, AsteroidSize.SMALL));

                                for (Asteroid asteroid : newAsteroids) {
                                    asteroid.changeSpeed(existingAsteroid.getSpeed());
                                    asteroids.add(asteroid);
                                    root.getChildren().add(asteroid.getComponent());
                                }

                            }

                            if (bullet.collided(existingAsteroid) && existingAsteroid.getSize() == AsteroidSize.LARGE) {
                                currentScore += 10;
                                scoreText.setText("Current Score: " + currentScore);

                                SoundManager.playSound(SoundEffect.DESTROY_LARGE_ASTERIODS.getFileName());

                            }

                            if (bullet.collided(existingAsteroid)
                                    && existingAsteroid.getSize() == AsteroidSize.MEDIUM) {
                                currentScore += 20;
                                scoreText.setText("Current Score: " + currentScore);

                                SoundManager.playSound(SoundEffect.DESTROY_MEDIUM_ASTERIODS.getFileName());

                            }

                            if (bullet.collided(existingAsteroid) && existingAsteroid.getSize() == AsteroidSize.SMALL) {
                                currentScore += 25;
                                scoreText.setText("Current Score: " + currentScore);

                                SoundManager.playSound(SoundEffect.DESTROY_SMALL_ASTERIODS.getFileName());

                            }

                            // add extra life
                            if (currentScore % 1000 == 0 && currentScore > playerLives * 1000
                                    && Constant.PLAYER_MAX_LIVES >= playerLives) {
                                playerLives++;
                                lifeText.setText("Current Life: " + playerLives);

                            }

                            break;

                        }

                    }
                    // alienShips
                    for (AlienShip alienShip : alienShips) {
                        if (bullet.collided(alienShip) && alienShip.isAlive()) {
                            SoundManager.playSound(SoundEffect.DESTROY_ALIEN_SHIP.getFileName());
                            bullet.setAlive(false);
                            alienShip.setAlive(false);
                            currentScore += 50;
                            scoreText.setText("Current Score: " + currentScore);
                            for (int i = 0; i < 3; i++) {
                                Dust dust = alienShip.dust();
                                dusts.add(dust);
                                root.getChildren().add(dust.getComponent());

                            }
                        }
                    }
                }
                // 4.1.2 alien bullets
                for (Bullet bullet : alienBullets) {
                    if (bullet.collided(playerShip)) {
                        SoundManager.playSound(SoundEffect.PLAYER_DEATH.getFileName());
                        playerShip.setAlive(false);
                        bullet.setAlive(false);
                        lifeText.setText("Current Life: " + playerLives);
                        playerShip.getComponent().setOpacity(0);

                        // create ship dusts when playership is destroyed
                        for (int i = 0; i < 3; i++) {
                            Dust dust = playerShip.dust();
                            Dust bulletDust = bullet.dust();
                            dusts.add(dust);
                            dusts.add(bulletDust);
                            root.getChildren().add(bulletDust.getComponent());
                            root.getChildren().add(dust.getComponent());
                        }
                    }
                }
                // 4.2 player and asteroids
                for (Asteroid asteroid : asteroids) {
                    if (playerShip.collided(asteroid)) {
                        SoundManager.playSound(SoundEffect.PLAYER_DEATH.getFileName());
                        playerShip.setAlive(false);
                        for (int i = 0; i < 3; i++) {
                            Dust dust = playerShip.dust();
                            dusts.add(dust);
                            root.getChildren().add(dust.getComponent());
                        }
                    }
                }
                // 4.3 player and alienShips
                for (AlienShip alienShip : alienShips) {
                    if (playerShip.collided(alienShip)) {
                        SoundManager.playSound(SoundEffect.PLAYER_DEATH.getFileName());
                        playerShip.setAlive(false);
                        alienShip.setAlive(false);
                        for (int i = 0; i < 3; i++) {
                            Dust playerDust = playerShip.dust();
                            Dust alienDust = alienShip.dust();
                            dusts.add(playerDust);
                            dusts.add(alienDust);
                            root.getChildren().add(playerDust.getComponent());
                            root.getChildren().add(alienDust.getComponent());
                        }
                    }
                }

                // 5. Remove dead entities
                removeDeadEntites(asteroids, root);
                removeDeadEntites(alienShips, root);
                removeDeadEntites(playerBullets, root);
                removeDeadEntites(alienBullets, root);
                removeDeadEntites(dusts, root);
                // 6. Check the status of player
                if (!playerShip.isAlive()) {
                    root.getChildren().remove(playerShip.getComponent());
                    playerLives -= 1;
                    lifeText.setText("Current Life: " + playerLives);
                    if (playerLives > 0) {
                        Point2D safePoint = Generator.generateSafePoint(enemies);
                        playerShip = new PlayerShip(safePoint.getX(), safePoint.getY());
                        root.getChildren().add(playerShip.getComponent());
                    } else {
                        // record scores in file
                        ScoreManager score_manager = new ScoreManager(Constant.SCORE_FILE);
                        score_manager.save_score(playerName, currentScore);
                        stop();
                        gameOverScene();
                    }
                }

                // 7. Check if player go to next level
                if (asteroids.size() == 0) {
                    currentLevel += 1;
                    playerShip = Generator.generatePlayerShip();
                    stop();
                    levelScene();
                }
            }
        }.start();

        stage.setScene(scene);
        stage.show();
    }

    private List<Entity> getEnemies(List<Asteroid> asteroids, List<AlienShip> alienShips, List<Bullet> alienBullets) {
        List<Entity> enemies = new ArrayList<>();
        enemies.addAll(asteroids);
        enemies.addAll(alienShips);
        enemies.addAll(alienBullets);
        return enemies;
    }

    private void removeDeadEntites(List<? extends Entity> deadEntityList, Pane root) {
        deadEntityList.stream()
                .filter(entity -> !entity.isAlive())
                .forEach(entity -> {
                    root.getChildren().remove(entity.getComponent());
                });
        deadEntityList.removeAll(
                deadEntityList.stream()
                        .filter(entity -> !entity.isAlive())
                        .collect(Collectors.toList()));
    }

}

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility Class to generate game entities
 */
public class Generator {
    private static final Random rdm = new Random();

    // prevent the instantiation
    private Generator() {
    };

    // generate player ship at default location (center of the screen)
    public static PlayerShip generatePlayerShip() {
        Point2D center = new Point2D(Constant.WIDTH / 2, Constant.HEIGHT / 2);
        return generatePlayerShip(center);
    }

    // generate player at in a specified location
    public static PlayerShip generatePlayerShip(Point2D location) {
        PlayerShip playerShip = new PlayerShip(location.getX(), location.getY());
        return playerShip;
    }

    // generate safe point for hyperspace jump and generate new playership
    public static Point2D generateSafePoint(List<Entity> enemies) {
        double buffer = ComponentGenerator.generatePlayerShipPolygon().getBoundsInLocal().getHeight();
        // range of x, y: min x, max x, min y, max y
        double[] limits = { 0 + buffer, Constant.WIDTH - buffer, 0 + buffer, Constant.HEIGHT - buffer };
        Boolean collided;
        double x;
        double y;
        do {
            collided = false;
            x = (limits[1] - limits[0]) * Generator.rdm.nextDouble() + limits[0];
            y = (limits[3] - limits[2]) * Generator.rdm.nextDouble() + limits[2];
            PlayerShip playerShip = new PlayerShip(x, y);
            for (Entity enemy : enemies) {
                if (playerShip.collided(enemy)) {
                    collided = true;
                    break;
                }
            }
        } while (collided);
        return new Point2D(x, y);
    }

    // generate initial asteroids for a specified level
    public static List<Asteroid> generateInitAsteroids(int currentLevel) {
        List<Asteroid> asteroids = new ArrayList<>();
        PlayerShip playerShip = generatePlayerShip();
        for (int i = 0; i < currentLevel; i++) {
            Boolean collided = false;
            Asteroid newAsteroid = null;
            do {
                collided = false;
                newAsteroid = new Asteroid(Generator.rdm.nextInt(Constant.WIDTH),
                        Generator.rdm.nextInt(Constant.HEIGHT), AsteroidSize.LARGE);
                // check if collied with existing asteroids
                for (int j = 0; j < i; j++) {
                    Asteroid oldAsteroid = asteroids.get(j);
                    if (oldAsteroid.collided(newAsteroid)) {
                        collided = true;
                        break;
                    }
                }
                // check if collied with the player ship (default location)
                if (newAsteroid.collided(playerShip)) {
                    collided = true;
                }
            } while (collided);
            asteroids.add(newAsteroid);
        }
        return asteroids;
    }

    // generate alien ship from the left side and right side of the screen randomly
    public static AlienShip generateAlienShip() {
        List<Double> points = ComponentGenerator.generateAlienShipPolygon().getPoints();
        // even points which means x
        double max_X = 0;
        for (int i = 0; i < points.size(); i += 2) {
            if (points.get(i) > max_X) {
                max_X = points.get(i);
            }
        }
        // odd points which means y
        double max_Y = 0;
        for (int i = 1; i < points.size(); i += 2) {
            if (Math.abs(points.get(i)) > max_Y) {
                max_Y = Math.abs(points.get(i));
            }
        }
        // init x location on different sides
        double leftX = -max_X;
        double rightX = Constant.WIDTH + max_X;
        // random
        double x = Generator.rdm.nextBoolean() ? leftX : rightX;
        // restrict the range of y. let the whole ship on screen to avoid split ship
        // while moving
        double y_lowerLimit = max_Y;
        double y_upperLimit = Constant.HEIGHT - max_Y;
        // init y location and random
        double y = y_lowerLimit + (y_upperLimit - y_lowerLimit) * Generator.rdm.nextDouble();
        double initSpeed = Constant.ALIENSHIP_INIT_SPEED;
        Point2D speed = new Point2D(((x > 0) ? -initSpeed : initSpeed), 0);
        return new AlienShip(x, y, speed);
    }

    // generate alien ship according to the possibility
    public static AlienShip generateAlienShip(double possibility) {
        double rdmDouble = Generator.rdm.nextDouble();// random number between 0 and 1
        // since the fps of AnimationTimer is 60. Change the possibility to per frame
        if (rdmDouble < (possibility / 60)) {
            // success
            return generateAlienShip();
        }
        // fail
        return null;
    }
}

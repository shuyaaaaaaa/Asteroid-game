import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class ComponentGenerator {
    public static Polygon generateAesteroidPolygon() {
        Random rnd = new Random();

        double size = 100;

        Polygon polygon = new Polygon();
        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);

        polygon.getPoints().addAll(
                size, 0.0,
                size * c1, -1 * size * s1,
                -1 * size * c2, -1 * size * s2,
                -1 * size * c2, size * s2,
                size * c1, size * s1);

        for (int i = 0; i < polygon.getPoints().size(); i++) {
            int change = rnd.nextInt(20) - 10;
            polygon.getPoints().set(i, polygon.getPoints().get(i) + change);
        }

        polygon.setFill(Color.WHITE);
        return polygon;
    }

    public static Polygon generatePlayerShipFirePolygon() {
        Polygon fire = new Polygon();
        fire.getPoints().addAll(0.0, 15.0, 5.0, 5.0, -5.0, 5.0);
        fire.setFill(Color.ORANGE);
        fire.setVisible(false);
        return fire;
    }

    public static Polygon generatePlayerShipPolygon() {
        Polygon ship = new Polygon();
        ship.getPoints().addAll(0.0, -15.0, 10.0, 10.0, -10.0, 10.0);
        ship.setFill(Color.LIGHTBLUE);
        return ship;
    }

    public static Circle generateBulletCircle() {
        Circle circle = new Circle(0, 0, 2);
        circle.setFill(Color.WHITE);
        return circle;
    }

    public static Polygon generateShipDust() {
        Polygon dustShape = new Polygon(-2.5, 15, 2.5, 15, 2.5, -15, -2.5, -15, -2.5, 15);
        dustShape.setFill(Color.WHITE);
        dustShape.setOpacity(0.5);
        return dustShape;
    }

    public static Circle generateBulletDust() {
        Circle bulletDust = new Circle(0, 0, 2);
        bulletDust.setFill(Color.WHITE);
        bulletDust.setOpacity(0.5);
        return bulletDust;
    }

    public static Polygon generateAlienShipPolygon() {
        Polygon alienShipShape = new Polygon(-30, 0, -20, 10, 20, 10, 30, 0, 20, -10, 15, -10, 10, -17.5, -10, -17.5,
                -15, -10, -20, -10);
        alienShipShape.setFill(Color.WHITE);
        return alienShipShape;
    }

}

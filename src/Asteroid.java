import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid extends Entity {
    /**
     * - Three types (large, medium, small)
     * - large : setsize(large), move(slow) , separate(),
     * - Medium : setsize(medium), move(medium)
     * - small : setsize(small), move(fast)
     */
    private AsteroidSize size;
    private double rotateSpeed;

    public Asteroid(double x, double y, AsteroidSize size) {
        super();
        this.setComponent(ComponentGenerator.generateAesteroidPolygon());
        this.getComponent().setTranslateX(x);
        this.getComponent().setTranslateY(y);

        this.size = size;
        setComponentSize();
        setRotate();
        setInitSpeed();
        setRotateSpeed();

    }

    private void setComponentSize() {
        double scaler = this.size.getComponentScaler();
        Polygon polygon = (Polygon) this.getComponent();
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            polygon.getPoints().set(i, polygon.getPoints().get(i) * scaler);
        }
    }

    public AsteroidSize getSize() {
        return this.size;
    }

    private void setRotate() {
        Random random = new Random();
        this.getComponent().setRotate(random.nextInt(360));
    }

    private void setInitSpeed() {
        Random random = new Random();
        double speedScale = this.size.getInitSpeedScaler();
        Point2D scaledSpeed = Constant.ASTEROID_BASE_SPEED.multiply(speedScale);
        Rotate rotate = new Rotate(random.nextInt(360));

        this.setSpeed(rotate.transform(scaledSpeed));
    }

    private void setRotateSpeed() {
        Random rdm = new Random();
        // random number between -1 and 1
        double randomNumber = -1 + 2 * rdm.nextDouble();
        double delta = randomNumber * Constant.ASTEROID_ROTATE_DELTA;
        this.rotateSpeed = delta;
    }

    @Override
    public void move() {
        super.move();
        this.rotate(this.rotateSpeed);
    }
}

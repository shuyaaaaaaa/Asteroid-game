import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

/**
 * Bullet
 * produced by player ship and alien ship
 * disappear after max travel distance
 */
public class Bullet extends Entity implements DustProduce {

    private double distanceTravelled = 0;

    public Bullet(Point2D position, Point2D speed) {
        super();
        this.setComponent(ComponentGenerator.generateBulletCircle());
        this.getComponent().setTranslateX(position.getX());
        this.getComponent().setTranslateY(position.getY());
        this.setSpeed(speed);
    }

    @Override
    public void move(double x, double y) {
        this.distanceTravelled += (new Point2D(x, y)).distance(new Point2D(0, 0));
        super.move(x, y);
        if (this.distanceTravelled > Constant.BULLET_MAX_DISTANCE) {
            this.setAlive(false);
        }
    }

    public Dust dust() {
        double dustX = this.getPosition().getX();
        double dustY = this.getPosition().getY();
        Circle bulletDust = ComponentGenerator.generateBulletDust();
        return new Dust(bulletDust, dustX, dustY);
    }
}

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * Define the properties and behaviors of player ship
 */
public class PlayerShip extends Entity implements DustProduce {
    // fire effect in the rear
    private final Polygon fireEffect;

    public PlayerShip(double x, double y) {
        super();
        Polygon ship = ComponentGenerator.generatePlayerShipPolygon();
        Polygon fire = ComponentGenerator.generatePlayerShipFirePolygon();
        this.fireEffect = fire;
        this.component = new Group(ship, fire);
        component.setTranslateX(x);
        component.setTranslateY(y);
        this.speed = Point2D.ZERO;
    }

    public void setFireEffectVisible(boolean visible) {
        fireEffect.setVisible(visible);
    }

    public Bullet fire() {
        Rotate rotate = new Rotate(this.getComponent().getRotate());
        Point2D localBulletPosition = new Point2D(0, 0);
        Point2D globalBulletPosition = rotate.transform(localBulletPosition);

        double bulletX = this.getComponent().getTranslateX() + globalBulletPosition.getX();
        double bulletY = this.getComponent().getTranslateY() + globalBulletPosition.getY();

        Point2D bulletPosition = new Point2D(bulletX, bulletY);

        Point2D bulletSpeed = rotate.transform(new Point2D(0, -Constant.BULLET_SPEED));
        bulletSpeed = bulletSpeed.add(this.getSpeed());

        return new Bullet(bulletPosition, bulletSpeed);
    }

    public void applyThrust() {
        Rotate rotate = new Rotate(this.getComponent().getRotate());
        Point2D thrust = rotate.transform(Constant.PLAYERSHIP_UNIT_THRUST);
        this.changeSpeed(thrust);
    }

    // Hyperspace Jump Disappear from current location and reappear in a new
    // location on the screen
    public void hyperSpaceJump(Point2D newPosition) {
        this.setPosition(newPosition.getX(), newPosition.getY());
    }

    public Dust dust() {
        double dustX = this.getPosition().getX();
        double dustY = this.getPosition().getY();
        Polygon shipDust = ComponentGenerator.generateShipDust();
        return new Dust(shipDust, dustX, dustY);
    }
}

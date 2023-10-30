import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 * Define the properties and behaviors of alien ship
 */
public class AlienShip extends Entity implements DustProduce {

    private double distanceTravelled_X = 0;

    public AlienShip(double x, double y, Point2D speed) {
        super();
        this.component = ComponentGenerator.generateAlienShipPolygon();
        this.component.setTranslateX(x);
        this.component.setTranslateY(y);
        this.speed = speed;
        setRandomMove();
    }

    public Bullet fire(PlayerShip playerShip) {
        Point2D playerShipPosition = playerShip.getPosition();
        Point2D alienShipPosition = this.getPosition();
        Point2D bulletDirection = playerShipPosition.subtract(alienShipPosition).normalize();
        Point2D speed = bulletDirection.multiply(Constant.BULLET_SPEED);
        return new Bullet(alienShipPosition, speed);
    }

    @Override
    public void move(double x, double y) {
        super.move(x, y);
        this.distanceTravelled_X += x;
        if (Math.abs(this.distanceTravelled_X) > Constant.WIDTH + this.component.getBoundsInLocal().getWidth()) {
            this.setAlive(false);
        }
    }

    public Dust dust() {
        double dustX = this.getPosition().getX();
        double dustY = this.getPosition().getY();
        Polygon shipDust = ComponentGenerator.generateShipDust();
        return new Dust(shipDust, dustX, dustY);
    }

    private void updateRandomSpeed() {
        double randomX = Math.random() * 2 - 1; // generates a random number between -1 and 1
        double randomY = Math.random() * 2 - 1; // generates a random number between -1 and 1
        double speedMagnitude = Constant.ALIENSHIP_MAX_SPEED_CHANGE; // adjust this value to control the maximum speed
        changeSpeed(new Point2D(randomX * speedMagnitude, randomY * speedMagnitude));
    }

    private void setRandomMove() {
        // random movement every 1 second
        Timeline randomMoveTimeline = new Timeline(
                new KeyFrame(Duration.seconds(Constant.ALEINSHIP_SPEED_CHANGE_CD), e -> updateRandomSpeed()));
        randomMoveTimeline.setCycleCount(Timeline.INDEFINITE);
        randomMoveTimeline.play();
    }

}

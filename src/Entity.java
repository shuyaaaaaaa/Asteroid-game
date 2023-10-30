import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

/**
 * The basic entity in the game
 */
public abstract class Entity {
    // Node on the screen (including the position)
    protected Node component;
    // the speed in a unit time
    protected Point2D speed;
    // the status of the entity
    protected boolean alive = true;

    // Constructor
    public Entity() {
    }

    // 1.getters and setters

    public Node getComponent() {
        return component;
    }

    public void setComponent(Shape component) {
        this.component = component;
    }

    public Point2D getSpeed() {
        return speed;
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // get the position
    public Point2D getPosition() {
        return new Point2D(this.component.getTranslateX(), this.component.getTranslateY());
    }

    public void setPosition(double x, double y) {
        this.component.setTranslateX(x);
        this.component.setTranslateY(y);
    }

    // 2.move
    // default move
    public void move() {
        this.move(this.speed.getX(), this.speed.getY());
    }

    // specified move
    public void move(double x, double y) {
        this.component.setTranslateX(this.component.getTranslateX() + x);
        this.component.setTranslateY(this.component.getTranslateY() + y);

        // consider the situation that entity move out of the screen
        double maxWidth = this.component.getBoundsInLocal().getWidth();
        // make a buffer to avoid the situation that entity would be split into two
        // parts on the different sides of the screen
        double buffer = maxWidth / 2;

        // For the positive X-axis
        if (this.component.getTranslateX() > (Constant.WIDTH + buffer)) {
            this.component.setTranslateX((-buffer)
                    + ((this.component.getTranslateX() - (Constant.WIDTH + buffer)) % (Constant.WIDTH + 2 * buffer)));
        }

        // For the negative X-axis
        if (this.component.getTranslateX() < -buffer) {
            this.component.setTranslateX((Constant.WIDTH + buffer)
                    - ((-buffer - this.component.getTranslateX()) % (Constant.WIDTH + 2 * buffer)));
        }

        // For the positive Y-axis
        if (this.component.getTranslateY() > (Constant.HEIGHT + buffer)) {
            this.component.setTranslateY((-buffer)
                    + ((this.component.getTranslateY() - (Constant.HEIGHT + buffer)) % (Constant.HEIGHT + 2 * buffer)));
        }

        // For the negative Y-axis
        if (this.component.getTranslateY() < -buffer) {
            this.component.setTranslateY((Constant.HEIGHT + buffer)
                    - ((-buffer - this.component.getTranslateY()) % (Constant.HEIGHT + 2 * buffer)));
        }

    }

    // 3.rotate
    public void rotateRight(double delta) {
        rotate(delta);
    }

    public void rotateLeft(double delta) {
        rotate(-delta);
    }

    public void rotate(double delta) {
        this.component.setRotate(this.component.getRotate() + delta);
    }

    // 4.change the speed
    public void changeSpeed(Point2D variation) {
        this.speed = this.speed.add(variation);
    }

    // 5.check collision
    public boolean collided(Entity other) {
        return this.component.getBoundsInParent().intersects(other.component.getBoundsInParent());
    }

}

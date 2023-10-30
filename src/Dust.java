import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Dust extends Entity {
    public Dust(Shape component, double x, double y) {
        super();
        component.setTranslateX(x);
        component.setTranslateY(y);
        this.component = component;
        double angle = Math.random() * Math.PI * 2;
        this.speed = new Point2D(Math.cos(angle) * Constant.DUST_SPEED_MAGNITUDE,
                Math.sin(angle) * Constant.DUST_SPEED_MAGNITUDE);
        setDisappear();
    }

    private void setDisappear() {
        // Make the dust entity dead after 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> setAlive(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
}

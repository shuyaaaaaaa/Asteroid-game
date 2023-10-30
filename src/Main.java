import javafx.application.Application;
import javafx.stage.Stage;

/**
 * launch the game
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {

        NewAsteroidsMenu menu = new NewAsteroidsMenu();
        // preload sound file
        SoundEffect[] soundEffects = SoundEffect.values();
        for (SoundEffect soundEffect : soundEffects) {
            SoundManager.preloadMedia(soundEffect.getFileName());
        }
        // launch main menu
        menu.start(stage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

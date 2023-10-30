import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;

public class SoundManager {

    private static HashMap<String, MediaPlayer> preloadedMedia = new HashMap<>();

    public static void preloadMedia(String fileName) {
        if (!preloadedMedia.containsKey(fileName)) {
            try {
                // Update the path to the sound files, assuming they are located in the 'public'
                // directory at the root of your project
                File file = new File("public/" + fileName);
                Media media = new Media(file.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                preloadedMedia.put(fileName, mediaPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void playSound(String fileName) {
        MediaPlayer mediaPlayer = preloadedMedia.get(fileName);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.setVolume(Constant.SOUND_VOLUME);
            mediaPlayer.play();
        }
    }
}

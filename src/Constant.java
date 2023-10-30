import javafx.geometry.Point2D;

public class Constant {
    // prevent the instantiation of the Constant class
    private Constant() {
    }

    // game
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final String SCORE_FILE = "highest_scores.txt";
    public static final double SOUND_VOLUME = 0.5;
    public static final String BASE_URL = "/public/";
    public static final int FAME_MAX = 5;

    // player
    public static final int PLAYER_MAX_LIVES = 3;
    public static final Point2D PLAYERSHIP_UNIT_THRUST = new Point2D(0, -0.1);
    public static final int PLAYER_ROTATE_DELTA = 5;
    public static final long HYPER_SPACE_JUMP_CD = 3_000_000_000L;// 3 seconds
    public static final long FIRE_CD = 300_000_000;// 0.3 seconds

    // bullet
    public static final double BULLET_SPEED = 5;
    public static final double BULLET_MAX_DISTANCE = 500;

    // alien
    public static final double ALIENSHIP_INIT_SPEED = 4;
    public static final double ALIENSHIP_MAX_SPEED_CHANGE = 2;
    public static final long ALEINSHIP_GENERATE_CD = 10_000_000_000L;// 10 seconds
    public static final long ALIENSHIP_FIRE_CD = 1_000_000_000L;// 1 seconds
    public static final double ALEINSHIP_SPEED_CHANGE_CD = 1;// 1 second
    public static final double ALEINSHIP_GENERATE_BASE_POSSIBILITY = 0.1;

    // asteroid
    public static final Point2D ASTEROID_BASE_SPEED = new Point2D(0, 5);
    public static final double ASTEROID_ROTATE_DELTA = 1;

    // dust
    public static final double DUST_SPEED_MAGNITUDE = 2;

}

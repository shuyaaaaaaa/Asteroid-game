public enum SoundEffect {
    SHOOT("fire.mp3"),
    DESTROY_LARGE_ASTERIODS("exp-large.mp3"),
    DESTROY_MEDIUM_ASTERIODS("exp-medium.mp3"),
    DESTROY_SMALL_ASTERIODS("exp-small.mp3"),
    DESTROY_ALIEN_SHIP("exp-large.mp3"),
    PLAYER_DEATH("exp-large.mp3");

    private final String fileName;

    SoundEffect(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
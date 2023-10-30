public enum AsteroidSize {
    LARGE(1.0, 0.2),
    MEDIUM(0.5, 0.4),
    SMALL(0.2, 0.6);

    private final double componentScaler;
    private final double initSpeedScaler;

    AsteroidSize(double componentScaler, double initSpeedScaler) {
        this.componentScaler = componentScaler;
        this.initSpeedScaler = initSpeedScaler;
    }

    public double getComponentScaler() {
        return this.componentScaler;
    }

    public double getInitSpeedScaler() {
        return this.initSpeedScaler;
    }
}

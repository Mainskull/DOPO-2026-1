package dopo.hardestgame.domain;

public class Size {
    private final double width;
    private final double height;

    public Size(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new GameException("Entity size must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}


package dopo.hardestgame.domain;

public class Board {
    private final int width;
    private final int height;

    public Board(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new GameException("Board dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean contains(Entity entity) {
        return contains(entity.getPosition(), entity.getSize());
    }

    public boolean contains(Position position, Size size) {
        return position.getX() >= 0
                && position.getY() >= 0
                && position.getX() + size.getWidth() <= width
                && position.getY() + size.getHeight() <= height;
    }

    public Position clamp(Position position, Size size) {
        double x = Math.max(0, Math.min(position.getX(), width - size.getWidth()));
        double y = Math.max(0, Math.min(position.getY(), height - size.getHeight()));
        return new Position(x, y);
    }

    public boolean touchesHorizontalLimit(Entity entity) {
        double x = entity.getPosition().getX();
        return x <= 0 || x + entity.getSize().getWidth() >= width;
    }

    public boolean touchesVerticalLimit(Entity entity) {
        double y = entity.getPosition().getY();
        return y <= 0 || y + entity.getSize().getHeight() >= height;
    }
}


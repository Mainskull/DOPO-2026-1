package dopo.hardestgame.domain;

public abstract class Entity {
    private Position position;
    private final Size size;

    protected Entity(Position position, Size size) {
        if (position == null || size == null) {
            throw new GameException("Entity position and size are required.");
        }
        this.position = position;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new GameException("Position is required.");
        }
        this.position = position;
    }

    public boolean collidesWith(Entity other) {
        Position a = position;
        Position b = other.getPosition();
        Size aSize = size;
        Size bSize = other.getSize();

        return a.getX() < b.getX() + bSize.getWidth()
                && a.getX() + aSize.getWidth() > b.getX()
                && a.getY() < b.getY() + bSize.getHeight()
                && a.getY() + aSize.getHeight() > b.getY();
    }
}


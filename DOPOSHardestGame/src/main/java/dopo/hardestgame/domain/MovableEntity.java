package dopo.hardestgame.domain;

public abstract class MovableEntity extends Entity {
    private final double speed;

    protected MovableEntity(Position position, Size size, double speed) {
        super(position, size);
        if (speed <= 0) {
            throw new GameException("Speed must be positive.");
        }
        this.speed = speed;
    }

    public void move(Direction direction, Board board) {
        Position nextPosition = getPosition().translatedBy(
                direction.getX() * speed,
                direction.getY() * speed
        );
        setPosition(board.clamp(nextPosition, getSize()));
    }

    public double getSpeed() {
        return speed;
    }
}


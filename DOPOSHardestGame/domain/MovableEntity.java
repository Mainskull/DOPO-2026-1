package domain;

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
        move(direction, board, 1.0);
    }

    public void move(Direction direction, Board board, double deltaTime) {
        if (direction == null || direction == Direction.NONE) {
            return;
        }
        if (deltaTime < 0) {
            throw new GameException("Delta time cannot be negative.");
        }
        Position nextPosition = getPosition().translatedBy(
                direction.getX() * speed * deltaTime,
                direction.getY() * speed * deltaTime
        );
        setPosition(board.clamp(nextPosition, getSize()));
    }

    public double getSpeed() {
        return speed;
    }
}


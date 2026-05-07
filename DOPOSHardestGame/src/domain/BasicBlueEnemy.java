package domain;

public class BasicBlueEnemy extends Enemy {
    public static final double DEFAULT_SPEED = 120.0;
    public static final Size DEFAULT_SIZE = new Size(18, 18);

    public BasicBlueEnemy(Position position, Direction direction) {
        super(position, DEFAULT_SIZE, DEFAULT_SPEED, direction);
    }

    public void update(Board board, double deltaTime) {
        move(getDirection(), board, deltaTime);
        bounceIfNeeded(board);
    }

    public void bounceIfNeeded(Board board) {
        if (board.touchesHorizontalLimit(this)) {
            if (getDirection() == Direction.LEFT) {
                setDirection(Direction.RIGHT);
            } else if (getDirection() == Direction.RIGHT) {
                setDirection(Direction.LEFT);
            }
        }

        if (board.touchesVerticalLimit(this)) {
            if (getDirection() == Direction.UP) {
                setDirection(Direction.DOWN);
            } else if (getDirection() == Direction.DOWN) {
                setDirection(Direction.UP);
            }
        }
    }
}


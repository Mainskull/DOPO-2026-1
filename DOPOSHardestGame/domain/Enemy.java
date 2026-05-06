package domain;

public abstract class Enemy extends MovableEntity {
    private Direction direction;

    protected Enemy(Position position, Size size, double speed, Direction direction) {
        super(position, size, speed);
        this.direction = direction;
    }

    public abstract void update(Board board, double deltaTime);

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}


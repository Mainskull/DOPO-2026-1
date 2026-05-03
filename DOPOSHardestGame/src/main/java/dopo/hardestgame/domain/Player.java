package dopo.hardestgame.domain;

public abstract class Player extends MovableEntity {
    private Position spawnPosition;
    private final double baseSpeed;

    protected Player(Position position, Size size, double speed) {
        super(position, size, speed);
        this.spawnPosition = position;
        this.baseSpeed = speed;
    }

    public void respawn() {
        setPosition(spawnPosition);
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(Position spawnPosition) {
        if (spawnPosition == null) {
            throw new GameException("Spawn position is required.");
        }
        this.spawnPosition = spawnPosition;
    }

    public double getBaseSpeed() {
        return baseSpeed;
    }
}

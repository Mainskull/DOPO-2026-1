package dopo.hardestgame.domain;

public class Coin extends Entity {
    public static final Size DEFAULT_SIZE = new Size(12, 12);

    private boolean collected;

    public Coin(Position position) {
        super(position, DEFAULT_SIZE);
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public boolean canBeCollectedBy(Player player) {
        return !collected && collidesWith(player);
    }
}


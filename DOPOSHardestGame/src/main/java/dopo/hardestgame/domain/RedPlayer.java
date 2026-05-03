package dopo.hardestgame.domain;

public class RedPlayer extends Player {
    public static final double DEFAULT_SPEED = 8.0;
    public static final Size DEFAULT_SIZE = new Size(20, 20);

    public RedPlayer(Position position) {
        super(position, DEFAULT_SIZE, DEFAULT_SPEED);
    }
}

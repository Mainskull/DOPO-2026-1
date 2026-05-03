package dopo.hardestgame.domain;

import java.util.List;

public final class BasicLevelFactory {
    private BasicLevelFactory() {
    }

    public static Game createVersionOneGame() {
        return new Game(createVersionOneLevel());
    }

    public static Level createVersionOneLevel() {
        Board board = new Board(800, 500);
        SafeZone initialZone = new SafeZone(new Position(20, 180), new Size(90, 140), SafeZoneType.INITIAL);
        SafeZone finalZone = new SafeZone(new Position(690, 180), new Size(90, 140), SafeZoneType.FINAL);
        RedPlayer player = new RedPlayer(new Position(55, 240));

        List<Coin> coins = List.of(
                new Coin(new Position(230, 160)),
                new Coin(new Position(330, 330)),
                new Coin(new Position(470, 160)),
                new Coin(new Position(570, 330))
        );

        List<Enemy> enemies = List.of(
                new BasicBlueEnemy(new Position(260, 90), Direction.DOWN),
                new BasicBlueEnemy(new Position(390, 390), Direction.UP),
                new BasicBlueEnemy(new Position(520, 90), Direction.DOWN)
        );

        return new Level(
                board,
                player,
                coins,
                enemies,
                initialZone,
                finalZone,
                new GameTimer(60)
        );
    }
}

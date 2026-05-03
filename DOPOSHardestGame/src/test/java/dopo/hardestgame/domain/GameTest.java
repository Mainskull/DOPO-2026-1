package dopo.hardestgame.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    void startsInReadyState() {
        Game game = new Game(createBasicLevel());

        assertEquals(GameState.READY, game.getState());
    }

    private Level createBasicLevel() {
        Board board = new Board(800, 500);
        RedPlayer player = new RedPlayer(new Position(20, 20));
        SafeZone initialZone = new SafeZone(new Position(0, 0), new Size(80, 80), SafeZoneType.INITIAL);
        SafeZone finalZone = new SafeZone(new Position(700, 400), new Size(80, 80), SafeZoneType.FINAL);

        return new Level(
                board,
                player,
                List.of(new Coin(new Position(200, 200))),
                List.of(new BasicBlueEnemy(new Position(300, 100), Direction.RIGHT)),
                initialZone,
                finalZone,
                new GameTimer(60)
        );
    }
}


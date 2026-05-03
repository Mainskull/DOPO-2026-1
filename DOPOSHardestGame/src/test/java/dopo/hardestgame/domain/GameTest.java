package dopo.hardestgame.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    @Test
    void startsInReadyState() {
        Game game = new Game(createBasicLevel());

        assertEquals(GameState.READY, game.getState());
    }

    @Test
    void movesPlayerWhileGameIsRunning() {
        Game game = new Game(createBasicLevel());
        double initialX = game.getCurrentLevel().getPlayer().getPosition().getX();

        game.start();
        game.movePlayer(Direction.RIGHT);
        game.update(1.0);

        assertTrue(game.getCurrentLevel().getPlayer().getPosition().getX() > initialX);
    }

    @Test
    void collectsCoinWhenPlayerCollidesWithIt() {
        Game game = new Game(createBasicLevel());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(200, 200));
        level.collectCoins();

        assertEquals(1, level.getCollectedCoinsCount());
    }

    @Test
    void registersDeathAndRespawnsPlayerWhenEnemyCollides() {
        Game game = new Game(createBasicLevel());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(300, 100));
        game.start();
        game.update(0);

        assertEquals(1, game.getDeaths());
        assertEquals(level.getInitialSafeZone().getPosition().getX(), level.getPlayer().getPosition().getX());
        assertEquals(level.getInitialSafeZone().getPosition().getY(), level.getPlayer().getPosition().getY());
    }

    @Test
    void winsOnlyAfterCollectingCoinsAndEnteringFinalZone() {
        Game game = new Game(createBasicLevel());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(200, 200));
        level.collectCoins();
        level.getPlayer().setPosition(new Position(700, 400));
        game.start();
        game.update(0);

        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void losesWhenTimeRunsOut() {
        Game game = new Game(createBasicLevelWithTimer(0.1));

        game.start();
        game.update(1.0);

        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void pausesAndResumesGame() {
        Game game = new Game(createBasicLevel());

        game.start();
        game.pause();
        assertEquals(GameState.PAUSED, game.getState());

        game.resume();
        assertEquals(GameState.RUNNING, game.getState());
    }

    private Level createBasicLevel() {
        return createBasicLevelWithTimer(60);
    }

    private Level createBasicLevelWithTimer(double seconds) {
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
                new GameTimer(seconds)
        );
    }
}

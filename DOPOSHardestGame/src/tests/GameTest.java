package tests;

import domain.BasicBlueEnemy;
import domain.Board;
import domain.Coin;
import domain.Direction;
import domain.Enemy;
import domain.Game;
import domain.GameState;
import domain.GameTimer;
import domain.Level;
import domain.Position;
import domain.RedPlayer;
import domain.SafeZone;
import domain.SafeZoneType;
import domain.Size;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        ArrayList<Coin> coins = new ArrayList<Coin>();
        coins.add(new Coin(new Position(200, 200)));

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new BasicBlueEnemy(new Position(300, 100), Direction.RIGHT));

        return new Level(
                board,
                player,
                coins,
                enemies,
                initialZone,
                finalZone,
                new GameTimer(seconds)
        );
    }
}

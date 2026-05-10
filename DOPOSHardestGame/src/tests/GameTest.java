package tests;

import domain.BasicBlueEnemy;
import domain.Board;
import domain.Coin;
import domain.Direction;
import domain.Enemy;
import domain.Entity;
import domain.Game;
import domain.GameException;
import domain.GameState;
import domain.GameTimer;
import domain.Level;
import domain.Position;
import domain.RedPlayer;
import domain.SafeZone;
import domain.SafeZoneType;
import domain.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * clase que contiene pruebas del dominio principal del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
class GameTest {
    /**
     * prueba que una entidad detecte colision cuando se cruza con otra
     */
    @Test
    void detectsCollisionWhenEntitiesOverlap() {
        Entity firstEntity = new TestEntity(new Position(10, 10), new Size(20, 20));
        Entity secondEntity = new TestEntity(new Position(25, 25), new Size(20, 20));

        assertTrue(firstEntity.collidesWith(secondEntity));
    }

    /**
     * prueba que una entidad no detecte colision cuando esta separada de otra
     */
    @Test
    void doesNotDetectCollisionWhenEntitiesAreSeparated() {
        Entity firstEntity = new TestEntity(new Position(10, 10), new Size(20, 20));
        Entity secondEntity = new TestEntity(new Position(100, 100), new Size(20, 20));

        assertFalse(firstEntity.collidesWith(secondEntity));
    }

    /**
     * prueba que el tablero limite la posicion para que el jugador no se salga
     */
    @Test
    void boardClampsPositionInsideLimits() {
        Board board = new Board(100, 100);
        Size size = new Size(20, 20);
        Position outsidePosition = new Position(95, 95);

        Position clampedPosition = board.clamp(outsidePosition, size);

        assertEquals(80, clampedPosition.getX());
        assertEquals(80, clampedPosition.getY());
    }

    /**
     * prueba que el jugador no pueda salirse del tablero al moverse
     */
    @Test
    void playerDoesNotLeaveBoardWhenMovingToLimit() {
        Board board = new Board(100, 100);
        RedPlayer player = new RedPlayer(new Position(0, 0));

        player.move(Direction.LEFT, board);
        player.move(Direction.UP, board);

        assertEquals(0, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
    }

    /**
     * prueba que una moneda se recoja cuando el jugador la toca
     */
    @Test
    void coinIsCollectedWhenPlayerCollidesWithIt() {
        RedPlayer player = new RedPlayer(new Position(50, 50));
        Coin coin = new Coin(new Position(55, 55));

        if (coin.canBeCollectedBy(player)) {
            coin.collect();
        }

        assertTrue(coin.isCollected());
    }

    /**
     * prueba que una moneda no se recoja si el jugador no la toca
     */
    @Test
    void coinIsNotCollectedWhenPlayerIsFarAway() {
        RedPlayer player = new RedPlayer(new Position(10, 10));
        Coin coin = new Coin(new Position(200, 200));

        if (coin.canBeCollectedBy(player)) {
            coin.collect();
        }

        assertFalse(coin.isCollected());
    }

    /**
     * prueba que el nivel cuente correctamente las monedas recogidas
     */
    @Test
    void levelCountsCollectedCoinsCorrectly() {
        Level level = createLevelWithCoinsAndNoEnemies();

        level.getPlayer().setPosition(new Position(100, 100));
        level.collectCoins();
        level.getPlayer().setPosition(new Position(150, 100));
        level.collectCoins();

        assertEquals(2, level.getCollectedCoinsCount());
        assertEquals(2, level.getTotalCoinsCount());
    }

    /**
     * prueba que el nivel sepa cuando faltan monedas por recoger
     */
    @Test
    void levelKnowsWhenCoinsAreMissing() {
        Level level = createLevelWithCoinsAndNoEnemies();

        level.getPlayer().setPosition(new Position(100, 100));
        level.collectCoins();

        assertFalse(level.areAllCoinsCollected());
    }

    /**
     * prueba que el nivel sepa cuando todas las monedas fueron recogidas
     */
    @Test
    void levelKnowsWhenAllCoinsAreCollected() {
        Level level = createLevelWithCoinsAndNoEnemies();

        level.getPlayer().setPosition(new Position(100, 100));
        level.collectCoins();
        level.getPlayer().setPosition(new Position(150, 100));
        level.collectCoins();

        assertTrue(level.areAllCoinsCollected());
    }

    /**
     * prueba que el enemigo se mueva segun su direccion
     */
    @Test
    void enemyMovesAccordingToDirection() {
        Board board = new Board(300, 300);
        BasicBlueEnemy enemy = new BasicBlueEnemy(new Position(100, 100), Direction.RIGHT);
        double initialX = enemy.getPosition().getX();

        enemy.update(board, 1.0);

        assertTrue(enemy.getPosition().getX() > initialX);
    }

    /**
     * prueba que el enemigo rebote al tocar el limite derecho
     */
    @Test
    void enemyChangesDirectionWhenTouchesRightLimit() {
        Board board = new Board(100, 100);
        BasicBlueEnemy enemy = new BasicBlueEnemy(new Position(82, 40), Direction.RIGHT);

        enemy.update(board, 1.0);

        assertEquals(Direction.LEFT, enemy.getDirection());
    }

    /**
     * prueba que el enemigo rebote al tocar el limite inferior
     */
    @Test
    void enemyChangesDirectionWhenTouchesBottomLimit() {
        Board board = new Board(100, 100);
        BasicBlueEnemy enemy = new BasicBlueEnemy(new Position(40, 82), Direction.DOWN);

        enemy.update(board, 1.0);

        assertEquals(Direction.UP, enemy.getDirection());
    }

    /**
     * prueba que el nivel detecte la colision entre jugador y enemigo
     */
    @Test
    void levelDetectsPlayerEnemyCollision() {
        Level level = createBasicLevel();

        level.getPlayer().setPosition(new Position(300, 100));

        assertTrue(level.hasPlayerCollidedWithEnemy());
    }

    /**
     * prueba que el juego sume una muerte y reaparezca el jugador al tocar un enemigo
     */
    @Test
    void gameRegistersDeathAndRespawnsPlayerAfterEnemyCollision() {
        Game game = new Game(createBasicLevel());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(300, 100));
        game.start();
        game.update(0);

        assertEquals(1, game.getDeaths());
        assertEquals(level.getInitialSafeZone().getPosition().getX(), level.getPlayer().getPosition().getX());
        assertEquals(level.getInitialSafeZone().getPosition().getY(), level.getPlayer().getPosition().getY());
    }

    /**
     * prueba que el jugador no gane si llega a la zona final sin todas las monedas
     */
    @Test
    void gameDoesNotWinIfPlayerReachesFinalZoneWithoutAllCoins() {
        Game game = new Game(createLevelWithCoinsAndNoEnemies());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(250, 250));
        game.start();
        game.update(0);

        assertEquals(GameState.RUNNING, game.getState());
    }

    /**
     * prueba que el jugador gane cuando recoge todas las monedas y llega a la zona final
     */
    @Test
    void gameWinsAfterCollectingAllCoinsAndEnteringFinalZone() {
        Game game = new Game(createLevelWithCoinsAndNoEnemies());
        Level level = game.getCurrentLevel();

        level.getPlayer().setPosition(new Position(100, 100));
        level.collectCoins();
        level.getPlayer().setPosition(new Position(150, 100));
        level.collectCoins();
        level.getPlayer().setPosition(new Position(250, 250));
        game.start();
        game.update(0);

        assertEquals(GameState.WON, game.getState());
    }

    /**
     * prueba que el temporizador baje cuando pasa el tiempo
     */
    @Test
    void timerDecreasesWhenTickIsCalled() {
        GameTimer timer = new GameTimer(10);

        timer.tick(3);

        assertEquals(7, timer.getRemainingSeconds());
    }

    /**
     * prueba que el temporizador no quede con tiempo negativo
     */
    @Test
    void timerDoesNotGoBelowZero() {
        GameTimer timer = new GameTimer(2);

        timer.tick(5);

        assertEquals(0, timer.getRemainingSeconds());
        assertTrue(timer.isFinished());
    }

    /**
     * prueba que el juego pierda cuando se acaba el tiempo
     */
    @Test
    void gameLosesWhenTimerFinishes() {
        Game game = new Game(createBasicLevelWithTimer(0.1));

        game.start();
        game.update(1.0);

        assertEquals(GameState.LOST, game.getState());
    }

    /**
     * prueba que al pausar el juego no se actualice el tiempo
     */
    @Test
    void pausedGameDoesNotUpdateTimer() {
        Game game = new Game(createBasicLevelWithTimer(60));
        GameTimer timer = game.getCurrentLevel().getTimer();

        game.start();
        game.pause();
        game.update(10);

        assertEquals(60, timer.getRemainingSeconds());
        assertEquals(GameState.PAUSED, game.getState());
    }

    /**
     * prueba que el juego pueda pasar de pausado a corriendo
     */
    @Test
    void gameCanResumeAfterPause() {
        Game game = new Game(createBasicLevel());

        game.start();
        game.pause();
        game.resume();

        assertEquals(GameState.RUNNING, game.getState());
    }

    /**
     * prueba que el juego cambie a terminado cuando el usuario lo finaliza
     */
    @Test
    void gameCanBeFinishedManually() {
        Game game = new Game(createBasicLevel());

        game.start();
        game.finish();

        assertEquals(GameState.FINISHED, game.getState());
    }

    /**
     * prueba que no se pueda crear un tablero con medidas invalidas
     */
    @Test
    void boardRejectsInvalidDimensions() {
        assertThrows(GameException.class, new Executable() {
            public void execute() {
                new Board(0, 100);
            }
        });
    }

    /**
     * prueba que no se pueda crear un tamano invalido
     */
    @Test
    void sizeRejectsInvalidDimensions() {
        assertThrows(GameException.class, new Executable() {
            public void execute() {
                new Size(10, 0);
            }
        });
    }

    /**
     * prueba que no se pueda crear un temporizador con tiempo invalido
     */
    @Test
    void timerRejectsInvalidTime() {
        assertThrows(GameException.class, new Executable() {
            public void execute() {
                new GameTimer(0);
            }
        });
    }

    /**
     * crea un nivel basico con una moneda y un enemigo
     * 
     * @return nivel basico para pruebas
     */
    private Level createBasicLevel() {
        return createBasicLevelWithTimer(60);
    }

    /**
     * crea un nivel basico con tiempo personalizado
     * 
     * @param seconds segundos del temporizador
     * 
     * @return nivel basico para pruebas
     */
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

    /**
     * crea un nivel con dos monedas y sin enemigos
     * 
     * @return nivel usado para probar monedas y victoria
     */
    private Level createLevelWithCoinsAndNoEnemies() {
        Board board = new Board(400, 400);
        RedPlayer player = new RedPlayer(new Position(20, 20));
        SafeZone initialZone = new SafeZone(new Position(0, 0), new Size(80, 80), SafeZoneType.INITIAL);
        SafeZone finalZone = new SafeZone(new Position(240, 240), new Size(100, 100), SafeZoneType.FINAL);

        ArrayList<Coin> coins = new ArrayList<Coin>();
        coins.add(new Coin(new Position(100, 100)));
        coins.add(new Coin(new Position(150, 100)));

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

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

    /**
     * clase de apoyo para poder probar colisiones entre entidades simples
     * 
     * @author Daniel Valero, Juan Nieto
     * @version 10.05.2026
     */
    private static class TestEntity extends Entity {
        /**
         * construye una entidad simple de prueba
         * 
         * @param position posicion de la entidad
         * @param size tamano de la entidad
         */
        TestEntity(Position position, Size size) {
            super(position, size);
        }
    }
}

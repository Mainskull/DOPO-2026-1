package domain;

import java.util.ArrayList;

/**
 * clase que representa un nivel del juego con tablero, jugador, monedas,
 * enemigos, zonas seguras y temporizador
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Level {
    private final Board board;
    private final Player player;
    private final ArrayList<Coin> coins;
    private final ArrayList<Enemy> enemies;
    private final SafeZone initialSafeZone;
    private final SafeZone finalSafeZone;
    private final GameTimer timer;

    /**
     * construye un nivel con todos sus elementos principales
     * 
     * @param board tablero donde se juega el nivel
     * @param player jugador del nivel
     * @param coins monedas que debe recoger el jugador
     * @param enemies enemigos del nivel
     * @param initialSafeZone zona segura inicial
     * @param finalSafeZone zona segura final
     * @param timer temporizador del nivel
     * 
     * @throws GameException si falta el tablero, jugador, zonas o temporizador
     */
    public Level(
            Board board,
            Player player,
            ArrayList<Coin> coins,
            ArrayList<Enemy> enemies,
            SafeZone initialSafeZone,
            SafeZone finalSafeZone,
            GameTimer timer
    ) {
        validateRequired(board, player, initialSafeZone, finalSafeZone, timer);
        this.board = board;
        this.player = player;
        this.coins = coins == null ? new ArrayList<Coin>() : new ArrayList<Coin>(coins);
        this.enemies = enemies == null ? new ArrayList<Enemy>() : new ArrayList<Enemy>(enemies);
        this.initialSafeZone = initialSafeZone;
        this.finalSafeZone = finalSafeZone;
        this.timer = timer;
        player.setSpawnPosition(initialSafeZone.getPosition());
    }

    /**
     * actualiza el tiempo, los enemigos y la recoleccion de monedas
     * 
     * @param deltaTime tiempo usado para actualizar el nivel
     * 
     * @throws GameException si deltaTime es negativo
     */
    public void update(double deltaTime) {
        if (deltaTime < 0) {
            throw new GameException("Delta time cannot be negative.");
        }
        timer.tick(deltaTime);

        for (Enemy enemy : enemies) {
            enemy.update(board, deltaTime);
        }

        collectCoins();
    }

    /**
     * mueve el jugador en una direccion y revisa si recoge monedas
     * 
     * @param direction direccion hacia donde se mueve el jugador
     */
    public void movePlayer(Direction direction) {
        player.move(direction, board);
        collectCoins();
    }

    /**
     * revisa las monedas y recoge las que esten tocando al jugador
     */
    public void collectCoins() {
        for (Coin coin : coins) {
            if (coin.canBeCollectedBy(player)) {
                coin.collect();
            }
        }
    }

    /**
     * determina si el jugador colisiono con algun enemigo
     * 
     * @return true si el jugador toca un enemigo, false si no
     */
    public boolean hasPlayerCollidedWithEnemy() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                return true;
            }
        }
        return false;
    }

    /**
     * determina si todas las monedas del nivel ya fueron recogidas
     * 
     * @return true si todas las monedas estan recogidas, false si falta alguna
     */
    public boolean areAllCoinsCollected() {
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return false;
            }
        }
        return true;
    }

    /**
     * determina si el jugador esta en la zona segura final
     * 
     * @return true si el jugador esta en la zona final, false si no
     */
    public boolean isPlayerInFinalZone() {
        return finalSafeZone.contains(player);
    }

    /**
     * hace reaparecer al jugador en su punto de reaparicion
     */
    public void respawnPlayer() {
        player.respawn();
    }

    /**
     * cuenta cuantas monedas fueron recogidas
     * 
     * @return cantidad de monedas recogidas
     */
    public int getCollectedCoinsCount() {
        int collectedCoins = 0;

        for (Coin coin : coins) {
            if (coin.isCollected()) {
                collectedCoins++;
            }
        }

        return collectedCoins;
    }

    /**
     * retorna la cantidad total de monedas del nivel
     * 
     * @return total de monedas
     */
    public int getTotalCoinsCount() {
        return coins.size();
    }

    /**
     * retorna el tablero del nivel
     * 
     * @return tablero del nivel
     */
    public Board getBoard() {
        return board;
    }

    /**
     * retorna el jugador del nivel
     * 
     * @return jugador del nivel
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * retorna las monedas del nivel
     * 
     * @return lista de monedas del nivel
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    /**
     * retorna los enemigos del nivel
     * 
     * @return lista de enemigos del nivel
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * retorna la zona segura inicial
     * 
     * @return zona segura inicial
     */
    public SafeZone getInitialSafeZone() {
        return initialSafeZone;
    }

    /**
     * retorna la zona segura final
     * 
     * @return zona segura final
     */
    public SafeZone getFinalSafeZone() {
        return finalSafeZone;
    }

    /**
     * retorna el temporizador del nivel
     * 
     * @return temporizador del nivel
     */
    public GameTimer getTimer() {
        return timer;
    }

    /**
     * valida que los elementos obligatorios del nivel existan
     * 
     * @param board tablero que se desea validar
     * @param player jugador que se desea validar
     * @param initialSafeZone zona inicial que se desea validar
     * @param finalSafeZone zona final que se desea validar
     * @param timer temporizador que se desea validar
     * 
     * @throws GameException si algun elemento obligatorio no es valido
     */
    private void validateRequired(
            Board board,
            Player player,
            SafeZone initialSafeZone,
            SafeZone finalSafeZone,
            GameTimer timer
    ) {
        if (board == null) {
            throw new GameException("Level requires a board.");
        }
        if (player == null) {
            throw new GameException("Level requires a player.");
        }
        if (initialSafeZone == null || initialSafeZone.getType() != SafeZoneType.INITIAL) {
            throw new GameException("Level requires an initial safe zone.");
        }
        if (finalSafeZone == null || finalSafeZone.getType() != SafeZoneType.FINAL) {
            throw new GameException("Level requires a final safe zone.");
        }
        if (timer == null) {
            throw new GameException("Level requires a timer.");
        }
    }
}

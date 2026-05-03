package dopo.hardestgame.domain;

import java.util.List;

public class Level {
    private final Board board;
    private final Player player;
    private final List<Coin> coins;
    private final List<Enemy> enemies;
    private final SafeZone initialSafeZone;
    private final SafeZone finalSafeZone;
    private final GameTimer timer;

    public Level(
            Board board,
            Player player,
            List<Coin> coins,
            List<Enemy> enemies,
            SafeZone initialSafeZone,
            SafeZone finalSafeZone,
            GameTimer timer
    ) {
        validateRequired(board, player, initialSafeZone, finalSafeZone, timer);
        this.board = board;
        this.player = player;
        this.coins = List.copyOf(coins == null ? List.of() : coins);
        this.enemies = List.copyOf(enemies == null ? List.of() : enemies);
        this.initialSafeZone = initialSafeZone;
        this.finalSafeZone = finalSafeZone;
        this.timer = timer;
        player.setSpawnPosition(initialSafeZone.getPosition());
    }

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

    public void movePlayer(Direction direction) {
        player.move(direction, board);
        collectCoins();
    }

    public void collectCoins() {
        for (Coin coin : coins) {
            if (coin.canBeCollectedBy(player)) {
                coin.collect();
            }
        }
    }

    public boolean hasPlayerCollidedWithEnemy() {
        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                return true;
            }
        }
        return false;
    }

    public boolean areAllCoinsCollected() {
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPlayerInFinalZone() {
        return finalSafeZone.contains(player);
    }

    public void respawnPlayer() {
        player.respawn();
    }

    public int getCollectedCoinsCount() {
        int collectedCoins = 0;

        for (Coin coin : coins) {
            if (coin.isCollected()) {
                collectedCoins++;
            }
        }

        return collectedCoins;
    }

    public int getTotalCoinsCount() {
        return coins.size();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public SafeZone getInitialSafeZone() {
        return initialSafeZone;
    }

    public SafeZone getFinalSafeZone() {
        return finalSafeZone;
    }

    public GameTimer getTimer() {
        return timer;
    }

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

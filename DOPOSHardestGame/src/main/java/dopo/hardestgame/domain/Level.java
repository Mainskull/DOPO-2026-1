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
        this.board = board;
        this.player = player;
        this.coins = List.copyOf(coins);
        this.enemies = List.copyOf(enemies);
        this.initialSafeZone = initialSafeZone;
        this.finalSafeZone = finalSafeZone;
        this.timer = timer;
        player.setSpawnPosition(initialSafeZone.getPosition());
    }

    public void update(double deltaTime) {
        timer.tick(deltaTime);
        enemies.forEach(enemy -> enemy.update(board, deltaTime));
        collectCoins();
    }

    public void movePlayer(Direction direction) {
        player.move(direction, board);
        collectCoins();
    }

    public void collectCoins() {
        coins.stream()
                .filter(coin -> coin.canBeCollectedBy(player))
                .forEach(Coin::collect);
    }

    public boolean hasPlayerCollidedWithEnemy() {
        return enemies.stream().anyMatch(player::collidesWith);
    }

    public boolean areAllCoinsCollected() {
        return coins.stream().allMatch(Coin::isCollected);
    }

    public boolean isPlayerInFinalZone() {
        return finalSafeZone.contains(player);
    }

    public void respawnPlayer() {
        player.respawn();
    }

    public int getCollectedCoinsCount() {
        return (int) coins.stream().filter(Coin::isCollected).count();
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
}


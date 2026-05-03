package dopo.hardestgame.domain;

public class Game {
    private final Level currentLevel;
    private GameState state;
    private int deaths;

    public Game(Level currentLevel) {
        if (currentLevel == null) {
            throw new GameException("A game requires a level.");
        }
        this.currentLevel = currentLevel;
        this.state = GameState.READY;
    }

    public void start() {
        if (state == GameState.READY || state == GameState.PAUSED) {
            state = GameState.RUNNING;
            evaluateRules();
        }
    }

    public void pause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
        }
    }

    public void resume() {
        if (state == GameState.PAUSED) {
            state = GameState.RUNNING;
        }
    }

    public void finish() {
        state = GameState.FINISHED;
    }

    public void update(double deltaTime) {
        if (state != GameState.RUNNING) {
            return;
        }

        currentLevel.update(deltaTime);
        evaluateRules();
    }

    private void evaluateRules() {
        if (currentLevel.hasPlayerCollidedWithEnemy()) {
            registerDeath();
            currentLevel.respawnPlayer();
        }

        if (currentLevel.areAllCoinsCollected() && currentLevel.isPlayerInFinalZone()) {
            state = GameState.WON;
        } else if (currentLevel.getTimer().isFinished()) {
            state = GameState.LOST;
        }
    }

    public void movePlayer(Direction direction) {
        if (state == GameState.RUNNING) {
            currentLevel.movePlayer(direction);
            evaluateRules();
        }
    }

    public void registerDeath() {
        deaths++;
    }

    public boolean isWon() {
        return state == GameState.WON;
    }

    public boolean isLost() {
        return state == GameState.LOST;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public GameState getState() {
        return state;
    }

    public int getDeaths() {
        return deaths;
    }
}

package domain;

/**
 * clase que representa la partida actual del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Game {
    private final Level currentLevel;
    private GameState state;
    private int deaths;

    /**
     * construye una partida con un nivel dado
     * 
     * @param currentLevel nivel que se va a jugar
     * 
     * @throws GameException si el nivel es nulo
     */
    public Game(Level currentLevel) {
        if (currentLevel == null) {
            throw new GameException("A game requires a level.");
        }
        this.currentLevel = currentLevel;
        this.state = GameState.READY;
    }

    /**
     * inicia la partida si esta lista o pausada
     */
    public void start() {
        if (state == GameState.READY || state == GameState.PAUSED) {
            state = GameState.RUNNING;
            evaluateRules();
        }
    }

    /**
     * pausa la partida si se encuentra corriendo
     */
    public void pause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
        }
    }

    /**
     * reanuda la partida si se encuentra pausada
     */
    public void resume() {
        if (state == GameState.PAUSED) {
            state = GameState.RUNNING;
        }
    }

    /**
     * termina la partida manualmente
     */
    public void finish() {
        state = GameState.FINISHED;
    }

    /**
     * actualiza la partida si esta corriendo
     * 
     * @param deltaTime tiempo usado para actualizar el nivel
     */
    public void update(double deltaTime) {
        if (state != GameState.RUNNING) {
            return;
        }

        currentLevel.update(deltaTime);
        evaluateRules();
    }

    /**
     * evalua las reglas principales de muerte, victoria y derrota
     */
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

    /**
     * mueve el jugador si la partida esta corriendo
     * 
     * @param direction direccion hacia donde se quiere mover el jugador
     */
    public void movePlayer(Direction direction) {
        if (state == GameState.RUNNING) {
            currentLevel.movePlayer(direction);
            evaluateRules();
        }
    }

    /**
     * aumenta el contador de muertes de la partida
     */
    public void registerDeath() {
        deaths++;
    }

    /**
     * determina si la partida fue ganada
     * 
     * @return true si el estado es ganado, false si no
     */
    public boolean isWon() {
        return state == GameState.WON;
    }

    /**
     * determina si la partida fue perdida
     * 
     * @return true si el estado es perdido, false si no
     */
    public boolean isLost() {
        return state == GameState.LOST;
    }

    /**
     * retorna el nivel actual de la partida
     * 
     * @return nivel actual
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * retorna el estado actual de la partida
     * 
     * @return estado actual del juego
     */
    public GameState getState() {
        return state;
    }

    /**
     * retorna la cantidad de muertes de la partida
     * 
     * @return numero de muertes
     */
    public int getDeaths() {
        return deaths;
    }
}

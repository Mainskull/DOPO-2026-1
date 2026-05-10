package domain;

/**
 * clase que maneja el tiempo restante de una partida
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class GameTimer {
    private double remainingSeconds;

    /**
     * construye un temporizador con una cantidad inicial de segundos
     * 
     * @param remainingSeconds segundos iniciales del temporizador
     * 
     * @throws GameException si los segundos son menores o iguales a cero
     */
    public GameTimer(double remainingSeconds) {
        reset(remainingSeconds);
    }

    /**
     * descuenta tiempo del temporizador
     * 
     * @param deltaTime tiempo que se debe descontar
     */
    public void tick(double deltaTime) {
        remainingSeconds = Math.max(0, remainingSeconds - deltaTime);
    }

    /**
     * determina si el temporizador ya llego a cero
     * 
     * @return true si el tiempo se acabo, false si todavia queda tiempo
     */
    public boolean isFinished() {
        return remainingSeconds <= 0;
    }

    /**
     * retorna los segundos que quedan en el temporizador
     * 
     * @return segundos restantes
     */
    public double getRemainingSeconds() {
        return remainingSeconds;
    }

    /**
     * reinicia el temporizador con una cantidad de segundos dada
     * 
     * @param seconds segundos con los que se reinicia el temporizador
     * 
     * @throws GameException si los segundos son menores o iguales a cero
     */
    public void reset(double seconds) {
        if (seconds <= 0) {
            throw new GameException("Timer seconds must be positive.");
        }
        remainingSeconds = seconds;
    }
}

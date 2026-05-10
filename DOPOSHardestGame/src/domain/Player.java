package domain;

/**
 * clase abstracta que representa un jugador del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public abstract class Player extends MovableEntity {
    private Position spawnPosition;
    private final double baseSpeed;

    /**
     * construye un jugador con posicion, tamano y velocidad
     * 
     * @param position posicion inicial del jugador
     * @param size tamano del jugador
     * @param speed velocidad del jugador
     */
    protected Player(Position position, Size size, double speed) {
        super(position, size, speed);
        this.spawnPosition = position;
        this.baseSpeed = speed;
    }

    /**
     * hace que el jugador vuelva a su posicion de reaparicion
     */
    public void respawn() {
        setPosition(spawnPosition);
    }

    /**
     * retorna la posicion de reaparicion del jugador
     * 
     * @return posicion donde reaparece el jugador
     */
    public Position getSpawnPosition() {
        return spawnPosition;
    }

    /**
     * cambia la posicion de reaparicion del jugador
     * 
     * @param spawnPosition nueva posicion de reaparicion
     * 
     * @throws GameException si la posicion de reaparicion es nula
     */
    public void setSpawnPosition(Position spawnPosition) {
        if (spawnPosition == null) {
            throw new GameException("Spawn position is required.");
        }
        this.spawnPosition = spawnPosition;
    }

    /**
     * retorna la velocidad base del jugador
     * 
     * @return velocidad base del jugador
     */
    public double getBaseSpeed() {
        return baseSpeed;
    }
}

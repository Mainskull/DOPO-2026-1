package domain;

/**
 * clase que representa al jugador rojo basico del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class RedPlayer extends Player {
    public static final double DEFAULT_SPEED = 8.0;
    public static final Size DEFAULT_SIZE = new Size(20, 20);

    /**
     * construye un jugador rojo en una posicion dada
     * 
     * @param position posicion inicial del jugador rojo
     */
    public RedPlayer(Position position) {
        super(position, DEFAULT_SIZE, DEFAULT_SPEED);
    }
}

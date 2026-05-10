package domain;

/**
 * clase que representa las excepciones propias del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class GameException extends RuntimeException {
    /**
     * construye una excepcion del juego con un mensaje dado
     * 
     * @param message mensaje que explica el error presentado
     */
    public GameException(String message) {
        super(message);
    }
}

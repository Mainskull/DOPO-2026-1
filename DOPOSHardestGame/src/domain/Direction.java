package domain;

/**
 * enumeracion que representa las direcciones en las que se pueden mover las
 * entidades del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1),
    NONE(0, 0);

    private final int x;
    private final int y;

    /**
     * construye una direccion con su movimiento en x y en y
     * 
     * @param x movimiento horizontal de la direccion
     * @param y movimiento vertical de la direccion
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * retorna el movimiento horizontal asociado a la direccion
     * 
     * @return valor de movimiento en x
     */
    public int getX() {
        return x;
    }

    /**
     * retorna el movimiento vertical asociado a la direccion
     * 
     * @return valor de movimiento en y
     */
    public int getY() {
        return y;
    }
}

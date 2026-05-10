package domain;

/**
 * clase abstracta que representa un enemigo del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public abstract class Enemy extends MovableEntity {
    private Direction direction;

    /**
     * construye un enemigo con posicion, tamano, velocidad y direccion
     * 
     * @param position posicion inicial del enemigo
     * @param size tamano del enemigo
     * @param speed velocidad del enemigo
     * @param direction direccion inicial del enemigo
     */
    protected Enemy(Position position, Size size, double speed, Direction direction) {
        super(position, size, speed);
        this.direction = direction;
    }

    /**
     * actualiza el comportamiento del enemigo
     * 
     * @param board tablero donde se mueve el enemigo
     * @param deltaTime tiempo usado para calcular el movimiento
     */
    public abstract void update(Board board, double deltaTime);

    /**
     * retorna la direccion actual del enemigo
     * 
     * @return direccion en la que se mueve el enemigo
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * cambia la direccion actual del enemigo
     * 
     * @param direction nueva direccion del enemigo
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

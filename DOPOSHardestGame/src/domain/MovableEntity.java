package domain;

/**
 * clase abstracta que representa una entidad del juego que se puede mover
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public abstract class MovableEntity extends Entity {
    private final double speed;

    /**
     * construye una entidad movible con posicion, tamano y velocidad
     * 
     * @param position posicion inicial de la entidad
     * @param size tamano de la entidad
     * @param speed velocidad con la que se mueve la entidad
     * 
     * @throws GameException si la velocidad es menor o igual a cero
     */
    protected MovableEntity(Position position, Size size, double speed) {
        super(position, size);
        if (speed <= 0) {
            throw new GameException("Speed must be positive.");
        }
        this.speed = speed;
    }

    /**
     * mueve la entidad en una direccion usando un paso normal
     * 
     * @param direction direccion hacia donde se mueve la entidad
     * @param board tablero que limita el movimiento
     */
    public void move(Direction direction, Board board) {
        move(direction, board, 1.0);
    }

    /**
     * mueve la entidad en una direccion teniendo en cuenta el tiempo pasado
     * 
     * @param direction direccion hacia donde se mueve la entidad
     * @param board tablero que limita el movimiento
     * @param deltaTime tiempo usado para calcular el desplazamiento
     * 
     * @throws GameException si deltaTime es negativo
     */
    public void move(Direction direction, Board board, double deltaTime) {
        if (direction == null || direction == Direction.NONE) {
            return;
        }
        if (deltaTime < 0) {
            throw new GameException("Delta time cannot be negative.");
        }
        Position nextPosition = getPosition().translatedBy(
                direction.getX() * speed * deltaTime,
                direction.getY() * speed * deltaTime
        );
        setPosition(board.clamp(nextPosition, getSize()));
    }

    /**
     * retorna la velocidad de la entidad movible
     * 
     * @return velocidad de la entidad
     */
    public double getSpeed() {
        return speed;
    }
}

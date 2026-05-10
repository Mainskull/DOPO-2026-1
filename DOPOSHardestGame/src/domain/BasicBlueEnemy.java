package domain;

/**
 * clase que representa al enemigo azul basico que se mueve en linea recta
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class BasicBlueEnemy extends Enemy {
    public static final double DEFAULT_SPEED = 120.0;
    public static final Size DEFAULT_SIZE = new Size(18, 18);

    /**
     * construye un enemigo azul basico con posicion y direccion
     * 
     * @param position posicion inicial del enemigo
     * @param direction direccion inicial del enemigo
     */
    public BasicBlueEnemy(Position position, Direction direction) {
        super(position, DEFAULT_SIZE, DEFAULT_SPEED, direction);
    }

    /**
     * actualiza el movimiento del enemigo y revisa si debe rebotar
     * 
     * @param board tablero donde se mueve el enemigo
     * @param deltaTime tiempo usado para calcular el movimiento
     */
    public void update(Board board, double deltaTime) {
        move(getDirection(), board, deltaTime);
        bounceIfNeeded(board);
    }

    /**
     * cambia la direccion del enemigo si toca los limites del tablero
     * 
     * @param board tablero que contiene los limites del nivel
     */
    public void bounceIfNeeded(Board board) {
        if (board.touchesHorizontalLimit(this)) {
            if (getDirection() == Direction.LEFT) {
                setDirection(Direction.RIGHT);
            } else if (getDirection() == Direction.RIGHT) {
                setDirection(Direction.LEFT);
            }
        }

        if (board.touchesVerticalLimit(this)) {
            if (getDirection() == Direction.UP) {
                setDirection(Direction.DOWN);
            } else if (getDirection() == Direction.DOWN) {
                setDirection(Direction.UP);
            }
        }
    }
}

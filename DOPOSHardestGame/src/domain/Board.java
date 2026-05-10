package domain;

/**
 * clase que representa el tablero donde se juega el nivel
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Board {
    private final int width;
    private final int height;

    /**
     * construye un tablero con ancho y alto
     * 
     * @param width ancho del tablero
     * @param height alto del tablero
     * 
     * @throws GameException si el ancho o el alto son menores o iguales a cero
     */
    public Board(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new GameException("Board dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * retorna el ancho del tablero
     * 
     * @return ancho del tablero
     */
    public int getWidth() {
        return width;
    }

    /**
     * retorna el alto del tablero
     * 
     * @return alto del tablero
     */
    public int getHeight() {
        return height;
    }

    /**
     * determina si una entidad esta dentro del tablero
     * 
     * @param entity entidad que se desea revisar
     * 
     * @return true si la entidad esta dentro, false si no
     */
    public boolean contains(Entity entity) {
        return contains(entity.getPosition(), entity.getSize());
    }

    /**
     * determina si una posicion con un tamano esta dentro del tablero
     * 
     * @param position posicion que se desea revisar
     * @param size tamano que ocupa la entidad
     * 
     * @return true si esta dentro del tablero, false si no
     */
    public boolean contains(Position position, Size size) {
        return position.getX() >= 0
                && position.getY() >= 0
                && position.getX() + size.getWidth() <= width
                && position.getY() + size.getHeight() <= height;
    }

    /**
     * ajusta una posicion para que no se salga del tablero
     * 
     * @param position posicion que se desea ajustar
     * @param size tamano de la entidad que se esta moviendo
     * 
     * @return posicion ajustada dentro del tablero
     */
    public Position clamp(Position position, Size size) {
        double x = Math.max(0, Math.min(position.getX(), width - size.getWidth()));
        double y = Math.max(0, Math.min(position.getY(), height - size.getHeight()));
        return new Position(x, y);
    }

    /**
     * determina si una entidad toca el limite izquierdo o derecho del tablero
     * 
     * @param entity entidad que se desea revisar
     * 
     * @return true si toca un limite horizontal, false si no
     */
    public boolean touchesHorizontalLimit(Entity entity) {
        double x = entity.getPosition().getX();
        return x <= 0 || x + entity.getSize().getWidth() >= width;
    }

    /**
     * determina si una entidad toca el limite superior o inferior del tablero
     * 
     * @param entity entidad que se desea revisar
     * 
     * @return true si toca un limite vertical, false si no
     */
    public boolean touchesVerticalLimit(Entity entity) {
        double y = entity.getPosition().getY();
        return y <= 0 || y + entity.getSize().getHeight() >= height;
    }
}

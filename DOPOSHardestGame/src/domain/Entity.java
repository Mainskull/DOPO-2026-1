package domain;

/**
 * clase abstracta que representa un elemento del juego que tiene posicion,
 * tamano y puede colisionar con otros elementos
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public abstract class Entity {
    private Position position;
    private final Size size;

    /**
     * construye una entidad con posicion y tamano
     * 
     * @param position posicion inicial de la entidad
     * @param size tamano de la entidad
     * 
     * @throws GameException si la posicion o el tamano son nulos
     */
    protected Entity(Position position, Size size) {
        if (position == null || size == null) {
            throw new GameException("Entity position and size are required.");
        }
        this.position = position;
        this.size = size;
    }

    /**
     * retorna la posicion actual de la entidad
     * 
     * @return posicion actual de la entidad
     */
    public Position getPosition() {
        return position;
    }

    /**
     * retorna el tamano de la entidad
     * 
     * @return tamano de la entidad
     */
    public Size getSize() {
        return size;
    }

    /**
     * cambia la posicion de la entidad
     * 
     * @param position nueva posicion de la entidad
     * 
     * @throws GameException si la posicion enviada es nula
     */
    public void setPosition(Position position) {
        if (position == null) {
            throw new GameException("Position is required.");
        }
        this.position = position;
    }

    /**
     * determina si la entidad actual colisiona con otra entidad
     * 
     * @param other otra entidad con la que se desea revisar la colision
     * 
     * @return true si las entidades se cruzan, false si no
     */
    public boolean collidesWith(Entity other) {
        Position a = position;
        Position b = other.getPosition();
        Size aSize = size;
        Size bSize = other.getSize();

        return a.getX() < b.getX() + bSize.getWidth()
                && a.getX() + aSize.getWidth() > b.getX()
                && a.getY() < b.getY() + bSize.getHeight()
                && a.getY() + aSize.getHeight() > b.getY();
    }
}

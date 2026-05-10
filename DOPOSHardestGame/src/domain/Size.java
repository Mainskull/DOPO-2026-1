package domain;

/**
 * clase que representa el tamano de una entidad del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Size {
    private final double width;
    private final double height;

    /**
     * construye un tamano con ancho y alto
     * 
     * @param width ancho que tendra la entidad
     * @param height alto que tendra la entidad
     * 
     * @throws GameException si el ancho o el alto son menores o iguales a cero
     */
    public Size(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new GameException("Entity size must be positive.");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * retorna el ancho del tamano
     * 
     * @return ancho de la entidad
     */
    public double getWidth() {
        return width;
    }

    /**
     * retorna el alto del tamano
     * 
     * @return alto de la entidad
     */
    public double getHeight() {
        return height;
    }
}

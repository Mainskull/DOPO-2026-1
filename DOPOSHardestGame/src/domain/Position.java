package domain;

/**
 * clase que representa una posicion dentro del tablero del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Position {
    private final double x;
    private final double y;

    /**
     * construye una posicion con coordenadas x y y
     * 
     * @param x coordenada horizontal de la posicion
     * @param y coordenada vertical de la posicion
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * retorna la coordenada horizontal de la posicion
     * 
     * @return coordenada x de la posicion
     */
    public double getX() {
        return x;
    }

    /**
     * retorna la coordenada vertical de la posicion
     * 
     * @return coordenada y de la posicion
     */
    public double getY() {
        return y;
    }

    /**
     * crea una nueva posicion desplazada a partir de la posicion actual
     * 
     * @param dx distancia que se desea mover en x
     * @param dy distancia que se desea mover en y
     * 
     * @return nueva posicion despues del desplazamiento
     */
    public Position translatedBy(double dx, double dy) {
        return new Position(x + dx, y + dy);
    }
}

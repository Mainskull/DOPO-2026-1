package domain;

/**
 * clase que representa una zona segura del tablero
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class SafeZone extends Entity {
    private final SafeZoneType type;

    /**
     * construye una zona segura con posicion, tamano y tipo
     * 
     * @param position posicion inicial de la zona segura
     * @param size tamano de la zona segura
     * @param type tipo de zona segura
     */
    public SafeZone(Position position, Size size, SafeZoneType type) {
        super(position, size);
        this.type = type;
    }

    /**
     * retorna el tipo de la zona segura
     * 
     * @return tipo de la zona segura
     */
    public SafeZoneType getType() {
        return type;
    }

    /**
     * determina si un jugador esta dentro de la zona segura
     * 
     * @param player jugador que se desea revisar
     * 
     * @return true si el jugador toca la zona segura, false si no
     */
    public boolean contains(Player player) {
        return collidesWith(player);
    }
}

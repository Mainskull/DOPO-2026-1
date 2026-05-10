package domain;

/**
 * clase que representa una moneda que puede recoger el jugador
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class Coin extends Entity {
    public static final Size DEFAULT_SIZE = new Size(12, 12);

    private boolean collected;

    /**
     * construye una moneda en una posicion dada
     * 
     * @param position posicion inicial de la moneda
     */
    public Coin(Position position) {
        super(position, DEFAULT_SIZE);
    }

    /**
     * determina si la moneda ya fue recogida
     * 
     * @return true si la moneda ya fue recogida, false si no
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * marca la moneda como recogida
     */
    public void collect() {
        collected = true;
    }

    /**
     * determina si un jugador puede recoger la moneda
     * 
     * @param player jugador que intenta recoger la moneda
     * 
     * @return true si el jugador toca la moneda y no esta recogida, false si no
     */
    public boolean canBeCollectedBy(Player player) {
        return !collected && collidesWith(player);
    }
}

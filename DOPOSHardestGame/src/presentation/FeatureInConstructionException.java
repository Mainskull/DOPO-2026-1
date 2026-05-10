package presentation;

/**
 * clase que representa una excepcion para opciones que aun no estan listas
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class FeatureInConstructionException extends RuntimeException {
    /**
     * construye una excepcion indicando la funcionalidad pendiente
     * 
     * @param featureName nombre de la funcionalidad que esta en construccion
     */
    public FeatureInConstructionException(String featureName) {
        super(featureName + " esta en construccion.");
    }
}

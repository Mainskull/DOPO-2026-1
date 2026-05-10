package presentation;

import domain.Game;

import javax.swing.JOptionPane;
import java.awt.Component;

/**
 * clase que muestra los mensajes principales de la interfaz
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public final class MessageDialog {
    /**
     * constructor privado para evitar crear objetos de esta clase
     */
    private MessageDialog() {
    }

    /**
     * muestra el mensaje cuando el jugador gana
     * 
     * @param game partida de la que se toman las monedas y muertes
     */
    public static void showVictory(Game game) {
        JOptionPane.showMessageDialog(null,
                "Ganaste el nivel.\n"
                        + "Monedas: "
                        + game.getCurrentLevel().getCollectedCoinsCount()
                        + " / "
                        + game.getCurrentLevel().getTotalCoinsCount()
                        + "\nMuertes: "
                        + game.getDeaths(),
                "Victoria",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * muestra el mensaje cuando se acaba el tiempo
     * 
     * @param game partida que termino por tiempo
     */
    public static void showTimeOver(Game game) {
        JOptionPane.showMessageDialog(null,
                "Tiempo agotado. Intentalo nuevamente.",
                "Fin del juego",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * muestra el mensaje cuando el usuario termina la partida
     */
    public static void showGameFinished() {
        JOptionPane.showMessageDialog(null,
                "La partida fue terminada.",
                "Partida terminada",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * muestra el mensaje para funcionalidades que aun estan en construccion
     * 
     * @param parent componente desde donde se abre el mensaje
     * @param featureName nombre de la funcionalidad no implementada
     */
    public static void showInConstruction(Component parent, String featureName) {
        try {
            throw new FeatureInConstructionException(featureName);
        } catch (FeatureInConstructionException exception) {
            JOptionPane.showMessageDialog(parent,
                    exception.getMessage(),
                    "En construccion",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}

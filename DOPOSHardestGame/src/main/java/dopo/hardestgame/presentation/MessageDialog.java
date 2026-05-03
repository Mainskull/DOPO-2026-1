package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Game;

import java.awt.Component;
import javax.swing.JOptionPane;

public final class MessageDialog {
    private MessageDialog() {
    }

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

    public static void showTimeOver(Game game) {
        JOptionPane.showMessageDialog(null,
                "Tiempo agotado. Intentalo nuevamente.",
                "Fin del juego",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void showGameFinished() {
        JOptionPane.showMessageDialog(null,
                "La partida fue terminada.",
                "Partida terminada",
                JOptionPane.INFORMATION_MESSAGE);
    }

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

package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Game;

import javax.swing.JOptionPane;

public final class MessageDialog {
    private MessageDialog() {
    }

    public static void showVictory(Game game) {
        JOptionPane.showMessageDialog(null,
                "Ganaste el nivel.\nMuertes: " + game.getDeaths(),
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
}


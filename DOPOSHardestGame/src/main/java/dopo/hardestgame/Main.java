package dopo.hardestgame;

import dopo.hardestgame.presentation.GameWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow().showStartScreen());
    }
}


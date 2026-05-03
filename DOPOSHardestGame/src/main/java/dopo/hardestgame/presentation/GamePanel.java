package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Game;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(GameWindow window) {
        setLayout(new BorderLayout());
        add(new BoardPanel(), BorderLayout.CENTER);
        add(new StatusPanel(), BorderLayout.NORTH);
        add(new ControlPanel(this), BorderLayout.SOUTH);
    }

    public void startGame() {
        if (game != null) {
            game.start();
        }
    }

    public void pauseGame() {
        if (game != null) {
            game.pause();
        }
    }

    public void resumeGame() {
        if (game != null) {
            game.resume();
        }
    }

    public void finishGame() {
        if (game != null) {
            game.finish();
        }
    }

    public void refresh() {
        repaint();
    }
}


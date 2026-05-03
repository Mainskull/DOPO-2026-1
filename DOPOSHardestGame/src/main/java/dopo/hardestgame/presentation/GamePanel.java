package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.BasicLevelFactory;
import dopo.hardestgame.domain.Game;
import dopo.hardestgame.domain.GameState;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GamePanel extends JPanel {
    private final GameWindow window;
    private final Game game;
    private final BoardPanel boardPanel;
    private final StatusPanel statusPanel;
    private final GameLoop gameLoop;
    private boolean finalMessageShown;

    public GamePanel(GameWindow window) {
        this.window = window;
        setLayout(new BorderLayout());
        game = BasicLevelFactory.createVersionOneGame();
        boardPanel = new BoardPanel(game);
        statusPanel = new StatusPanel();
        gameLoop = new GameLoop(game, this);
        finalMessageShown = false;

        add(statusPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(new ControlPanel(this), BorderLayout.SOUTH);
    }

    public void startGame() {
        game.start();
        gameLoop.start();
        boardPanel.requestFocusInWindow();
    }

    public void pauseGame() {
        game.pause();
        gameLoop.stop();
        refresh();
    }

    public void resumeGame() {
        game.resume();
        gameLoop.start();
        boardPanel.requestFocusInWindow();
    }

    public void finishGame() {
        if (finalMessageShown) {
            return;
        }
        finalMessageShown = true;
        game.finish();
        gameLoop.stop();
        MessageDialog.showGameFinished();
        window.showStartScreen();
    }

    public void refresh() {
        statusPanel.updateStatus(game);
        boardPanel.repaint();
        showFinalMessageIfNeeded();
    }

    private void showFinalMessageIfNeeded() {
        if (finalMessageShown) {
            return;
        }

        GameState state = game.getState();
        if (state == GameState.WON) {
            finalMessageShown = true;
            gameLoop.stop();
            MessageDialog.showVictory(game);
            window.showStartScreen();
        } else if (state == GameState.LOST) {
            finalMessageShown = true;
            gameLoop.stop();
            MessageDialog.showTimeOver(game);
            window.showStartScreen();
        }
    }
}

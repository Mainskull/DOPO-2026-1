package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.BasicLevelFactory;
import dopo.hardestgame.domain.Game;
import dopo.hardestgame.domain.GameState;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GamePanel extends JPanel {
    private final Game game;
    private final BoardPanel boardPanel;
    private final StatusPanel statusPanel;
    private final GameLoop gameLoop;
    private boolean finalMessageShown;

    public GamePanel(GameWindow window) {
        setLayout(new BorderLayout());
        game = BasicLevelFactory.createVersionOneGame();
        boardPanel = new BoardPanel(game);
        statusPanel = new StatusPanel();
        gameLoop = new GameLoop(game, this);
        finalMessageShown = false;

        add(statusPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(new ControlPanel(this), BorderLayout.SOUTH);

        startGame();
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
        game.finish();
        refresh();
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
        } else if (state == GameState.LOST) {
            finalMessageShown = true;
            gameLoop.stop();
            MessageDialog.showTimeOver(game);
        } else if (state == GameState.FINISHED) {
            finalMessageShown = true;
            gameLoop.stop();
            MessageDialog.showGameFinished();
        }
    }
}

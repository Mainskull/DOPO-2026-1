package presentation;

import domain.BasicLevelFactory;
import domain.Game;
import domain.GameState;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * clase que representa la pantalla donde se juega la partida
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class GamePanel extends JPanel {
    private final GameWindow window;
    private final Game game;
    private final BoardPanel boardPanel;
    private final StatusPanel statusPanel;
    private final GameLoop gameLoop;
    private boolean finalMessageShown;

    /**
     * construye el panel principal del juego con tablero, estado y controles
     * 
     * @param window ventana principal que contiene el panel
     */
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

    /**
     * inicia la partida y el ciclo del juego
     */
    public void startGame() {
        game.start();
        gameLoop.start();
        boardPanel.requestFocusInWindow();
    }

    /**
     * pausa la partida y detiene el ciclo del juego
     */
    public void pauseGame() {
        game.pause();
        gameLoop.stop();
        refresh();
    }

    /**
     * reanuda la partida y vuelve a iniciar el ciclo del juego
     */
    public void resumeGame() {
        game.resume();
        gameLoop.start();
        boardPanel.requestFocusInWindow();
    }

    /**
     * termina la partida, muestra el mensaje y vuelve al inicio
     */
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

    /**
     * actualiza la informacion visual de la partida
     */
    public void refresh() {
        statusPanel.updateStatus(game);
        boardPanel.repaint();
        showFinalMessageIfNeeded();
    }

    /**
     * muestra el mensaje final si la partida ya gano o perdio
     */
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

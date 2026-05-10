package presentation;

import javax.swing.JFrame;

/**
 * clase que representa la ventana principal de la interfaz grafica
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class GameWindow extends JFrame {
    /**
     * construye la ventana principal del juego
     */
    public GameWindow() {
        super("The DOPO Hardest Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    /**
     * muestra la pantalla inicial del juego
     */
    public void showStartScreen() {
        getContentPane().removeAll();
        add(new StartPanel(this));
        revalidate();
        repaint();
        setVisible(true);
    }

    /**
     * muestra la pantalla donde se escoge el modo de juego
     */
    public void showModeSelectionScreen() {
        getContentPane().removeAll();
        add(new ModeSelectionPanel(this));
        revalidate();
        repaint();
    }

    /**
     * muestra la pantalla donde se escoge el tipo de jugador
     */
    public void showPlayerSelectionScreen() {
        getContentPane().removeAll();
        add(new PlayerSelectionPanel(this));
        revalidate();
        repaint();
    }

    /**
     * muestra la pantalla del juego e inicia la partida
     */
    public void showGameScreen() {
        getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.startGame();
    }

    /**
     * cierra la aplicacion
     */
    public void closeApplication() {
        dispose();
    }
}

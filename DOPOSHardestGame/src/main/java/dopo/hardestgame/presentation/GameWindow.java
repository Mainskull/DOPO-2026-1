package dopo.hardestgame.presentation;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow() {
        super("The DOPO Hardest Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    public void showStartScreen() {
        getContentPane().removeAll();
        add(new StartPanel(this));
        revalidate();
        repaint();
        setVisible(true);
    }

    public void showModeSelectionScreen() {
        getContentPane().removeAll();
        add(new ModeSelectionPanel(this));
        revalidate();
        repaint();
    }

    public void showPlayerSelectionScreen() {
        getContentPane().removeAll();
        add(new PlayerSelectionPanel(this));
        revalidate();
        repaint();
    }

    public void showGameScreen() {
        getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.startGame();
    }

    public void closeApplication() {
        dispose();
    }
}

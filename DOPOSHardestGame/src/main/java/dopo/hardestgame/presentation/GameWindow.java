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

    public void showGameScreen() {
        getContentPane().removeAll();
        add(new GamePanel(this));
        revalidate();
        repaint();
    }

    public void closeApplication() {
        dispose();
    }
}

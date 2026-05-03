package dopo.hardestgame.presentation;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ControlPanel extends JPanel {
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton finishButton;

    public ControlPanel(GamePanel gamePanel) {
        setLayout(new GridLayout(1, 3, 8, 0));
        pauseButton = new JButton("Pausar");
        resumeButton = new JButton("Reanudar");
        finishButton = new JButton("Terminar");

        add(pauseButton);
        add(resumeButton);
        add(finishButton);
        configureEvents(gamePanel);
    }

    private void configureEvents(GamePanel gamePanel) {
        pauseButton.addActionListener(event -> gamePanel.pauseGame());
        resumeButton.addActionListener(event -> gamePanel.resumeGame());
        finishButton.addActionListener(event -> gamePanel.finishGame());
    }
}


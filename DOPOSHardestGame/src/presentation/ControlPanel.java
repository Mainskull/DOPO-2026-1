package presentation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton finishButton;

    public ControlPanel(GamePanel gamePanel) {
        setLayout(new GridLayout(1, 3, 8, 0));
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setBackground(new Color(225, 225, 225));
        pauseButton = new JButton("Pausar");
        resumeButton = new JButton("Reanudar");
        finishButton = new JButton("Terminar");

        add(pauseButton);
        add(resumeButton);
        add(finishButton);
        configureEvents(gamePanel);
    }

    private void configureEvents(GamePanel gamePanel) {
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gamePanel.pauseGame();
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gamePanel.resumeGame();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gamePanel.finishGame();
            }
        });
    }
}


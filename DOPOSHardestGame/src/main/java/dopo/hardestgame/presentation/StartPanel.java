package dopo.hardestgame.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class StartPanel extends JPanel {
    private final JButton startButton;
    private final JButton exitButton;

    public StartPanel(GameWindow window) {
        setLayout(new BorderLayout());
        startButton = new JButton("Iniciar");
        exitButton = new JButton("Salir");
        configureComponents();
        configureEvents(window);
    }

    private void configureComponents() {
        setBackground(new Color(238, 238, 238));
        JLabel title = new JLabel("The DOPO Hardest Game - Version 1", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel description = new JLabel("Modo Player | Cuadrado rojo | Nivel basico", SwingConstants.CENTER);
        description.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 12, 0));
        buttons.setBackground(new Color(238, 238, 238));
        buttons.add(startButton);
        buttons.add(exitButton);

        add(title, BorderLayout.NORTH);
        add(description, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private void configureEvents(GameWindow window) {
        startButton.addActionListener(event -> window.showGameScreen());
        exitButton.addActionListener(event -> window.closeApplication());
    }
}

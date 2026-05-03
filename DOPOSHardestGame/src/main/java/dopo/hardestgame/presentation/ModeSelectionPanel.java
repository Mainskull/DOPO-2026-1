package dopo.hardestgame.presentation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeSelectionPanel extends JPanel {
    private final JButton playerButton;
    private final JButton playerVsPlayerButton;
    private final JButton playerVsMachineButton;
    private final JButton backButton;

    public ModeSelectionPanel(GameWindow window) {
        setLayout(new BorderLayout());
        playerButton = new JButton("Player individual");
        playerVsPlayerButton = new JButton("Player vs Player");
        playerVsMachineButton = new JButton("Player vs Machine");
        backButton = new JButton("Volver");
        configureComponents();
        configureEvents(window);
    }

    private void configureComponents() {
        setBackground(new Color(238, 238, 238));

        JLabel title = new JLabel("Selecciona el modo de juego", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JPanel buttons = new JPanel(new GridLayout(4, 1, 0, 14));
        buttons.setBackground(new Color(238, 238, 238));
        buttons.setBorder(BorderFactory.createEmptyBorder(40, 300, 80, 300));
        buttons.add(playerButton);
        buttons.add(playerVsPlayerButton);
        buttons.add(playerVsMachineButton);
        buttons.add(backButton);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    private void configureEvents(GameWindow window) {
        playerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                window.showPlayerSelectionScreen();
            }
        });

        playerVsPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(ModeSelectionPanel.this, "Player vs Player");
            }
        });

        playerVsMachineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(ModeSelectionPanel.this, "Player vs Machine");
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                window.showStartScreen();
            }
        });
    }
}

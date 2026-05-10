package presentation;

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

/**
 * clase que representa la pantalla para seleccionar el modo de juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class ModeSelectionPanel extends JPanel {
    private final JButton playerButton;
    private final JButton playerVsPlayerButton;
    private final JButton playerVsMachineButton;
    private final JButton backButton;

    /**
     * construye el panel de seleccion de modo
     * 
     * @param window ventana principal que permite cambiar de pantalla
     */
    public ModeSelectionPanel(GameWindow window) {
        setLayout(new BorderLayout());
        playerButton = new JButton("Player individual");
        playerVsPlayerButton = new JButton("Player vs Player");
        playerVsMachineButton = new JButton("Player vs Machine");
        backButton = new JButton("Volver");
        configureComponents();
        configureEvents(window);
    }

    /**
     * configura los componentes visuales de la seleccion de modo
     */
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

    /**
     * configura las acciones de los botones de seleccion de modo
     * 
     * @param window ventana principal que permite navegar entre pantallas
     */
    private void configureEvents(GameWindow window) {
        playerButton.addActionListener(new ActionListener() {
            /**
             * muestra la seleccion de jugador para el modo individual
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                window.showPlayerSelectionScreen();
            }
        });

        playerVsPlayerButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para player vs player
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(ModeSelectionPanel.this, "Player vs Player");
            }
        });

        playerVsMachineButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para player vs machine
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(ModeSelectionPanel.this, "Player vs Machine");
            }
        });

        backButton.addActionListener(new ActionListener() {
            /**
             * vuelve al menu principal
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                window.showStartScreen();
            }
        });
    }
}

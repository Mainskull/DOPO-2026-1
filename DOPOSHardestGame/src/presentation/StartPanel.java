package presentation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * clase que representa el menu principal del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class StartPanel extends JPanel {
    private final JButton playButton;
    private final JButton optionsButton;
    private final JButton creditsButton;

    /**
     * construye el menu principal
     * 
     * @param window ventana principal que permite cambiar de pantalla
     */
    public StartPanel(GameWindow window) {
        setLayout(new BorderLayout());
        playButton = new JButton("Jugar");
        optionsButton = new JButton("Opciones");
        creditsButton = new JButton("Creditos");
        configureComponents();
        configureEvents(window);
    }

    /**
     * configura los componentes visuales del menu principal
     */
    private void configureComponents() {
        setBackground(new Color(238, 238, 238));
        JLabel title = new JLabel("The DOPO Hardest Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JLabel description = new JLabel("Menu principal", SwingConstants.CENTER);
        description.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(238, 238, 238));
        centerPanel.add(description, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(3, 1, 0, 14));
        buttons.setBackground(new Color(238, 238, 238));
        buttons.setBorder(BorderFactory.createEmptyBorder(40, 320, 80, 320));

        configureMenuButton(playButton);
        configureMenuButton(optionsButton);
        configureMenuButton(creditsButton);

        buttons.add(playButton);
        buttons.add(optionsButton);
        buttons.add(creditsButton);
        centerPanel.add(buttons, BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * configura las acciones de los botones del menu principal
     * 
     * @param window ventana principal que permite navegar entre pantallas
     */
    private void configureEvents(GameWindow window) {
        playButton.addActionListener(new ActionListener() {
            /**
             * muestra la seleccion de modo cuando se oprime jugar
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                window.showModeSelectionScreen();
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para opciones
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(StartPanel.this, "Opciones");
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para creditos
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(StartPanel.this, "Creditos");
            }
        });
    }

    /**
     * configura el estilo de un boton del menu
     * 
     * @param button boton que se desea configurar
     */
    private void configureMenuButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(220, 42));
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }
}

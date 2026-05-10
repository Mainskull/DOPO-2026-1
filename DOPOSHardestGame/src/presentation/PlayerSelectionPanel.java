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
 * clase que representa la pantalla para seleccionar el jugador
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class PlayerSelectionPanel extends JPanel {
    private final JButton redPlayerButton;
    private final JButton bluePlayerButton;
    private final JButton greenPlayerButton;
    private final JButton backButton;

    /**
     * construye el panel de seleccion de jugador
     * 
     * @param window ventana principal que permite cambiar de pantalla
     */
    public PlayerSelectionPanel(GameWindow window) {
        setLayout(new BorderLayout());
        redPlayerButton = new JButton("Cuadrado rojo");
        bluePlayerButton = new JButton("Cuadrado azul");
        greenPlayerButton = new JButton("Cuadrado verde");
        backButton = new JButton("Volver");
        configureComponents();
        configureEvents(window);
    }

    /**
     * configura los componentes visuales de la seleccion de jugador
     */
    private void configureComponents() {
        setBackground(new Color(238, 238, 238));

        JLabel title = new JLabel("Selecciona tu jugador", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JPanel buttons = new JPanel(new GridLayout(4, 1, 0, 14));
        buttons.setBackground(new Color(238, 238, 238));
        buttons.setBorder(BorderFactory.createEmptyBorder(40, 300, 80, 300));
        buttons.add(redPlayerButton);
        buttons.add(bluePlayerButton);
        buttons.add(greenPlayerButton);
        buttons.add(backButton);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    /**
     * configura las acciones de los botones de seleccion de jugador
     * 
     * @param window ventana principal que permite navegar entre pantallas
     */
    private void configureEvents(GameWindow window) {
        redPlayerButton.addActionListener(new ActionListener() {
            /**
             * inicia el juego usando el cuadrado rojo
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                window.showGameScreen();
            }
        });

        bluePlayerButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para el cuadrado azul
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(PlayerSelectionPanel.this, "Cuadrado azul");
            }
        });

        greenPlayerButton.addActionListener(new ActionListener() {
            /**
             * muestra el mensaje de construccion para el cuadrado verde
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                MessageDialog.showInConstruction(PlayerSelectionPanel.this, "Cuadrado verde");
            }
        });

        backButton.addActionListener(new ActionListener() {
            /**
             * vuelve a la seleccion de modo de juego
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                window.showModeSelectionScreen();
            }
        });
    }
}

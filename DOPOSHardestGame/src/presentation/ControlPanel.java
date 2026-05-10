package presentation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * clase que representa los botones de control de la partida
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class ControlPanel extends JPanel {
    private final JButton pauseButton;
    private final JButton resumeButton;
    private final JButton finishButton;

    /**
     * construye el panel de controles de la partida
     * 
     * @param gamePanel panel del juego que recibe las acciones
     */
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

    /**
     * configura las acciones de los botones del panel
     * 
     * @param gamePanel panel del juego que se controla con los botones
     */
    private void configureEvents(GamePanel gamePanel) {
        pauseButton.addActionListener(new ActionListener() {
            /**
             * pausa la partida cuando se oprime el boton
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                gamePanel.pauseGame();
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            /**
             * reanuda la partida cuando se oprime el boton
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                gamePanel.resumeGame();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            /**
             * termina la partida cuando se oprime el boton
             * 
             * @param event evento generado por el boton
             */
            public void actionPerformed(ActionEvent event) {
                gamePanel.finishGame();
            }
        });
    }
}

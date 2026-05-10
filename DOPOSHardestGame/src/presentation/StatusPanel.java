package presentation;

import domain.Game;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * clase que representa el panel donde se muestra el estado de la partida
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class StatusPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel deathsLabel;
    private final JLabel coinsLabel;

    /**
     * construye el panel de estado con tiempo, muertes y monedas
     */
    public StatusPanel() {
        setLayout(new GridLayout(1, 3));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        timeLabel = createLabel("Tiempo: 0");
        deathsLabel = createLabel("Muertes: 0");
        coinsLabel = createLabel("Monedas: 0 / 0");

        add(timeLabel);
        add(deathsLabel);
        add(coinsLabel);
    }

    /**
     * actualiza los textos del estado usando la informacion del juego
     * 
     * @param game partida de la que se toma el estado actual
     */
    public void updateStatus(Game game) {
        timeLabel.setText("Tiempo: " + Math.ceil(game.getCurrentLevel().getTimer().getRemainingSeconds()));
        deathsLabel.setText("Muertes: " + game.getDeaths());
        coinsLabel.setText("Monedas: "
                + game.getCurrentLevel().getCollectedCoinsCount()
                + " / "
                + game.getCurrentLevel().getTotalCoinsCount());
    }

    /**
     * crea una etiqueta con el estilo visual del panel
     * 
     * @param text texto inicial de la etiqueta
     * 
     * @return etiqueta creada con el estilo definido
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
}

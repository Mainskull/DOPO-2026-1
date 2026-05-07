package presentation;

import domain.Game;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class StatusPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel deathsLabel;
    private final JLabel coinsLabel;

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

    public void updateStatus(Game game) {
        timeLabel.setText("Tiempo: " + Math.ceil(game.getCurrentLevel().getTimer().getRemainingSeconds()));
        deathsLabel.setText("Muertes: " + game.getDeaths());
        coinsLabel.setText("Monedas: "
                + game.getCurrentLevel().getCollectedCoinsCount()
                + " / "
                + game.getCurrentLevel().getTotalCoinsCount());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
}


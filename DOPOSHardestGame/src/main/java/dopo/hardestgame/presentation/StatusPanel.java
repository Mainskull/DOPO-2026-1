package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Game;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class StatusPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel deathsLabel;
    private final JLabel coinsLabel;

    public StatusPanel() {
        setLayout(new GridLayout(1, 3));
        timeLabel = new JLabel("Tiempo: 0");
        deathsLabel = new JLabel("Muertes: 0");
        coinsLabel = new JLabel("Monedas: 0 / 0");

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
}


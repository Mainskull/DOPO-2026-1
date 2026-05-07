package presentation;

import domain.Game;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop {
    private static final int DELAY_MILLISECONDS = 16;
    private static final double DELTA_SECONDS = DELAY_MILLISECONDS / 1000.0;

    private final Timer timer;
    private final Game game;
    private final GamePanel gamePanel;

    public GameLoop(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.timer = new Timer(DELAY_MILLISECONDS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tick();
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void restart() {
        timer.restart();
    }

    public void tick() {
        game.update(DELTA_SECONDS);
        gamePanel.refresh();
    }
}


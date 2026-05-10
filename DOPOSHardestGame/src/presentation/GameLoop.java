package presentation;

import domain.Game;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * clase que representa el ciclo de actualizacion del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class GameLoop {
    private static final int DELAY_MILLISECONDS = 16;
    private static final double DELTA_SECONDS = DELAY_MILLISECONDS / 1000.0;

    private final Timer timer;
    private final Game game;
    private final GamePanel gamePanel;

    /**
     * construye el ciclo del juego con la partida y el panel grafico
     * 
     * @param game partida que se actualiza en cada ciclo
     * @param gamePanel panel que se refresca despues de actualizar
     */
    public GameLoop(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.timer = new Timer(DELAY_MILLISECONDS, new ActionListener() {
            /**
             * ejecuta un ciclo del juego cuando el temporizador se activa
             * 
             * @param event evento generado por el temporizador
             */
            public void actionPerformed(ActionEvent event) {
                tick();
            }
        });
    }

    /**
     * inicia el ciclo del juego
     */
    public void start() {
        timer.start();
    }

    /**
     * detiene el ciclo del juego
     */
    public void stop() {
        timer.stop();
    }

    /**
     * reinicia el ciclo del juego
     */
    public void restart() {
        timer.restart();
    }

    /**
     * actualiza la partida y refresca la pantalla
     */
    public void tick() {
        game.update(DELTA_SECONDS);
        gamePanel.refresh();
    }
}

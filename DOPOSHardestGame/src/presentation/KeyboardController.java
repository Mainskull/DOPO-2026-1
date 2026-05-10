package presentation;

import domain.Direction;
import domain.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * clase que controla las teclas usadas para mover el jugador
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class KeyboardController extends KeyAdapter {
    private final Game game;
    private Direction currentDirection;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    /**
     * construye el controlador de teclado para una partida
     * 
     * @param game partida que recibe el movimiento del jugador
     */
    public KeyboardController(Game game) {
        this.game = game;
        this.currentDirection = Direction.NONE;
    }

    /**
     * actualiza las teclas presionadas y mueve el jugador
     * 
     * @param event evento de teclado que contiene la tecla presionada
     */
    public void keyPressed(KeyEvent event) {
        updatePressedKey(event.getKeyCode(), true);
        currentDirection = calculateDirection();
        game.movePlayer(currentDirection);
    }

    /**
     * actualiza las teclas cuando el usuario deja de presionarlas
     * 
     * @param event evento de teclado que contiene la tecla liberada
     */
    public void keyReleased(KeyEvent event) {
        updatePressedKey(event.getKeyCode(), false);
        currentDirection = calculateDirection();
    }

    /**
     * guarda si una tecla de movimiento esta presionada o no
     * 
     * @param keyCode codigo de la tecla que cambio
     * @param pressed true si la tecla esta presionada, false si fue soltada
     */
    private void updatePressedKey(int keyCode, boolean pressed) {
        if (keyCode == KeyEvent.VK_UP) {
            upPressed = pressed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = pressed;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = pressed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = pressed;
        }
    }

    /**
     * calcula la direccion actual segun las teclas presionadas
     * 
     * @return direccion que debe seguir el jugador
     */
    private Direction calculateDirection() {
        if (upPressed && leftPressed) {
            return Direction.UP_LEFT;
        }
        if (upPressed && rightPressed) {
            return Direction.UP_RIGHT;
        }
        if (downPressed && leftPressed) {
            return Direction.DOWN_LEFT;
        }
        if (downPressed && rightPressed) {
            return Direction.DOWN_RIGHT;
        }
        if (upPressed) {
            return Direction.UP;
        }
        if (downPressed) {
            return Direction.DOWN;
        }
        if (leftPressed) {
            return Direction.LEFT;
        }
        if (rightPressed) {
            return Direction.RIGHT;
        }
        return Direction.NONE;
    }

    /**
     * retorna la direccion actual del teclado
     * 
     * @return direccion actual calculada
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }
}

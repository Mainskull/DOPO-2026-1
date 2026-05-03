package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Direction;
import dopo.hardestgame.domain.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    private final Game game;
    private Direction currentDirection;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public KeyboardController(Game game) {
        this.game = game;
        this.currentDirection = Direction.NONE;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        updatePressedKey(event.getKeyCode(), true);
        currentDirection = calculateDirection();
        game.movePlayer(currentDirection);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        updatePressedKey(event.getKeyCode(), false);
        currentDirection = calculateDirection();
    }

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

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}

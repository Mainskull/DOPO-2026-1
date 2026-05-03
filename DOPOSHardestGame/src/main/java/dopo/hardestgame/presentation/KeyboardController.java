package dopo.hardestgame.presentation;

import dopo.hardestgame.domain.Direction;
import dopo.hardestgame.domain.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    private final Game game;
    private Direction currentDirection;

    public KeyboardController(Game game) {
        this.game = game;
        this.currentDirection = Direction.NONE;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        currentDirection = switch (event.getKeyCode()) {
            case KeyEvent.VK_UP -> Direction.UP;
            case KeyEvent.VK_DOWN -> Direction.DOWN;
            case KeyEvent.VK_LEFT -> Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            default -> Direction.NONE;
        };
        game.movePlayer(currentDirection);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        currentDirection = Direction.NONE;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}


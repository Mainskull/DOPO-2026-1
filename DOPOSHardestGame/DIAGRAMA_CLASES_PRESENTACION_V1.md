# Diagrama de clases - Presentacion Version 1

```mermaid
classDiagram
    class GameWindow {
        -StartPanel startPanel
        -GamePanel gamePanel
        +showStartScreen()
        +showGameScreen()
        +closeApplication()
    }

    class StartPanel {
        -JButton startButton
        -JButton exitButton
        +configureComponents()
        +configureEvents()
    }

    class GamePanel {
        -Game game
        -BoardPanel boardPanel
        -StatusPanel statusPanel
        -ControlPanel controlPanel
        -GameLoop gameLoop
        +startGame()
        +pauseGame()
        +resumeGame()
        +finishGame()
        +refresh()
    }

    class BoardPanel {
        -Game game
        +paintComponent(Graphics graphics)
        +drawPlayer(Graphics graphics)
        +drawCoins(Graphics graphics)
        +drawEnemies(Graphics graphics)
        +drawSafeZones(Graphics graphics)
    }

    class StatusPanel {
        -JLabel timeLabel
        -JLabel deathsLabel
        -JLabel coinsLabel
        +updateStatus(Game game)
    }

    class ControlPanel {
        -JButton pauseButton
        -JButton resumeButton
        -JButton finishButton
        +configureEvents(GamePanel gamePanel)
    }

    class KeyboardController {
        -Game game
        +keyPressed(KeyEvent event)
        +keyReleased(KeyEvent event)
        +getCurrentDirection() Direction
    }

    class GameLoop {
        -Timer timer
        -Game game
        -GamePanel gamePanel
        +start()
        +stop()
        +restart()
        +tick()
    }

    class MessageDialog {
        +showVictory(Game game)
        +showTimeOver(Game game)
        +showGameFinished()
    }

    class Game {
        <<domain>>
    }

    class Direction {
        <<domain enumeration>>
    }

    GameWindow "1" *-- "1" StartPanel
    GameWindow "1" *-- "1" GamePanel
    GamePanel "1" *-- "1" BoardPanel
    GamePanel "1" *-- "1" StatusPanel
    GamePanel "1" *-- "1" ControlPanel
    GamePanel "1" *-- "1" GameLoop
    BoardPanel --> Game
    StatusPanel --> Game
    GamePanel --> Game
    KeyboardController --> Game
    KeyboardController --> Direction
    GameLoop --> Game
    GameLoop --> GamePanel
    GamePanel --> MessageDialog
```


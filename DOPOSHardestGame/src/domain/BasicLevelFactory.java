package domain;

import java.util.ArrayList;

/**
 * clase que crea el nivel basico de la primera version del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public final class BasicLevelFactory {
    /**
     * constructor privado para evitar crear objetos de esta clase
     */
    private BasicLevelFactory() {
    }

    /**
     * crea una partida de la version uno con el nivel basico
     * 
     * @return juego listo con el nivel basico
     */
    public static Game createVersionOneGame() {
        return new Game(createVersionOneLevel());
    }

    /**
     * crea el nivel basico con jugador, monedas, enemigos y zonas
     * 
     * @return nivel basico de la primera version
     */
    public static Level createVersionOneLevel() {
        Board board = new Board(800, 500);
        SafeZone initialZone = new SafeZone(new Position(20, 180), new Size(90, 140), SafeZoneType.INITIAL);
        SafeZone finalZone = new SafeZone(new Position(690, 180), new Size(90, 140), SafeZoneType.FINAL);
        RedPlayer player = new RedPlayer(new Position(55, 240));

        ArrayList<Coin> coins = new ArrayList<Coin>();
        coins.add(new Coin(new Position(230, 160)));
        coins.add(new Coin(new Position(330, 330)));
        coins.add(new Coin(new Position(470, 160)));
        coins.add(new Coin(new Position(570, 330)));

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new BasicBlueEnemy(new Position(260, 90), Direction.DOWN));
        enemies.add(new BasicBlueEnemy(new Position(390, 390), Direction.UP));
        enemies.add(new BasicBlueEnemy(new Position(520, 90), Direction.DOWN));

        return new Level(
                board,
                player,
                coins,
                enemies,
                initialZone,
                finalZone,
                new GameTimer(60)
        );
    }
}

package presentation;

import domain.Coin;
import domain.Enemy;
import domain.Entity;
import domain.Game;
import domain.Level;
import domain.Player;
import domain.SafeZone;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * clase que representa el panel encargado de dibujar el tablero del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class BoardPanel extends JPanel {
    private final Game game;

    /**
     * construye el panel del tablero a partir de una partida
     * 
     * @param game partida que se va a dibujar en pantalla
     */
    public BoardPanel(Game game) {
        this.game = game;
        setFocusable(true);
        setPreferredSize(new Dimension(800, 500));
        addKeyListener(new KeyboardController(game));
    }

    /**
     * dibuja todos los elementos visibles del tablero
     * 
     * @param graphics objeto grafico usado para dibujar
     */
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Level level = game.getCurrentLevel();
        drawBackground(graphics2D);
        drawSafeZone(graphics2D, level.getInitialSafeZone());
        drawSafeZone(graphics2D, level.getFinalSafeZone());
        drawCoins(graphics2D, level);
        drawEnemies(graphics2D, level);
        drawPlayer(graphics2D, level.getPlayer());
    }

    /**
     * dibuja el fondo y borde del tablero
     * 
     * @param graphics objeto grafico usado para dibujar
     */
    private void drawBackground(Graphics2D graphics) {
        graphics.setColor(new Color(238, 238, 238));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    /**
     * dibuja una zona segura del nivel
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param safeZone zona segura que se desea dibujar
     */
    private void drawSafeZone(Graphics2D graphics, SafeZone safeZone) {
        graphics.setColor(new Color(88, 220, 88));
        fillEntity(graphics, safeZone);
        graphics.setColor(new Color(28, 130, 28));
        drawEntity(graphics, safeZone);
    }

    /**
     * dibuja las monedas que no han sido recogidas
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param level nivel del que se toman las monedas
     */
    private void drawCoins(Graphics2D graphics, Level level) {
        graphics.setColor(new Color(245, 215, 45));

        for (Coin coin : level.getCoins()) {
            if (!coin.isCollected()) {
                fillOval(graphics, coin);
                graphics.setColor(new Color(150, 120, 0));
                drawOval(graphics, coin);
                graphics.setColor(new Color(245, 215, 45));
            }
        }
    }

    /**
     * dibuja todos los enemigos del nivel
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param level nivel del que se toman los enemigos
     */
    private void drawEnemies(Graphics2D graphics, Level level) {
        graphics.setColor(new Color(35, 95, 230));

        for (Enemy enemy : level.getEnemies()) {
            fillOval(graphics, enemy);
            graphics.setColor(new Color(10, 35, 130));
            drawOval(graphics, enemy);
            graphics.setColor(new Color(35, 95, 230));
        }
    }

    /**
     * dibuja el jugador del nivel
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param player jugador que se desea dibujar
     */
    private void drawPlayer(Graphics2D graphics, Player player) {
        graphics.setColor(new Color(220, 40, 40));
        fillEntity(graphics, player);
        graphics.setColor(new Color(120, 0, 0));
        drawEntity(graphics, player);
    }

    /**
     * dibuja una entidad como rectangulo relleno
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param entity entidad que se desea dibujar
     */
    private void fillEntity(Graphics2D graphics, Entity entity) {
        graphics.fillRect(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    /**
     * dibuja el borde rectangular de una entidad
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param entity entidad que se desea dibujar
     */
    private void drawEntity(Graphics2D graphics, Entity entity) {
        graphics.drawRect(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    /**
     * dibuja una entidad como ovalo relleno
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param entity entidad que se desea dibujar
     */
    private void fillOval(Graphics2D graphics, Entity entity) {
        graphics.fillOval(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    /**
     * dibuja el borde ovalado de una entidad
     * 
     * @param graphics objeto grafico usado para dibujar
     * @param entity entidad que se desea dibujar
     */
    private void drawOval(Graphics2D graphics, Entity entity) {
        graphics.drawOval(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    /**
     * convierte la coordenada x del dominio a la pantalla
     * 
     * @param entity entidad que se desea ubicar en pantalla
     * 
     * @return coordenada x escalada para la pantalla
     */
    private int toScreenX(Entity entity) {
        return (int) (entity.getPosition().getX() * getScaleX());
    }

    /**
     * convierte la coordenada y del dominio a la pantalla
     * 
     * @param entity entidad que se desea ubicar en pantalla
     * 
     * @return coordenada y escalada para la pantalla
     */
    private int toScreenY(Entity entity) {
        return (int) (entity.getPosition().getY() * getScaleY());
    }

    /**
     * convierte el ancho de una entidad del dominio a la pantalla
     * 
     * @param entity entidad que se desea medir
     * 
     * @return ancho escalado para la pantalla
     */
    private int toScreenWidth(Entity entity) {
        return Math.max(1, (int) (entity.getSize().getWidth() * getScaleX()));
    }

    /**
     * convierte el alto de una entidad del dominio a la pantalla
     * 
     * @param entity entidad que se desea medir
     * 
     * @return alto escalado para la pantalla
     */
    private int toScreenHeight(Entity entity) {
        return Math.max(1, (int) (entity.getSize().getHeight() * getScaleY()));
    }

    /**
     * calcula la escala horizontal entre el tablero y el panel
     * 
     * @return escala horizontal usada para dibujar
     */
    private double getScaleX() {
        return getWidth() / (double) game.getCurrentLevel().getBoard().getWidth();
    }

    /**
     * calcula la escala vertical entre el tablero y el panel
     * 
     * @return escala vertical usada para dibujar
     */
    private double getScaleY() {
        return getHeight() / (double) game.getCurrentLevel().getBoard().getHeight();
    }
}

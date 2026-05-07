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

public class BoardPanel extends JPanel {
    private final Game game;

    public BoardPanel(Game game) {
        this.game = game;
        setFocusable(true);
        setPreferredSize(new Dimension(800, 500));
        addKeyListener(new KeyboardController(game));
    }

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

    private void drawBackground(Graphics2D graphics) {
        graphics.setColor(new Color(238, 238, 238));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    private void drawSafeZone(Graphics2D graphics, SafeZone safeZone) {
        graphics.setColor(new Color(88, 220, 88));
        fillEntity(graphics, safeZone);
        graphics.setColor(new Color(28, 130, 28));
        drawEntity(graphics, safeZone);
    }

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

    private void drawEnemies(Graphics2D graphics, Level level) {
        graphics.setColor(new Color(35, 95, 230));

        for (Enemy enemy : level.getEnemies()) {
            fillOval(graphics, enemy);
            graphics.setColor(new Color(10, 35, 130));
            drawOval(graphics, enemy);
            graphics.setColor(new Color(35, 95, 230));
        }
    }

    private void drawPlayer(Graphics2D graphics, Player player) {
        graphics.setColor(new Color(220, 40, 40));
        fillEntity(graphics, player);
        graphics.setColor(new Color(120, 0, 0));
        drawEntity(graphics, player);
    }

    private void fillEntity(Graphics2D graphics, Entity entity) {
        graphics.fillRect(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    private void drawEntity(Graphics2D graphics, Entity entity) {
        graphics.drawRect(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    private void fillOval(Graphics2D graphics, Entity entity) {
        graphics.fillOval(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    private void drawOval(Graphics2D graphics, Entity entity) {
        graphics.drawOval(toScreenX(entity), toScreenY(entity), toScreenWidth(entity), toScreenHeight(entity));
    }

    private int toScreenX(Entity entity) {
        return (int) (entity.getPosition().getX() * getScaleX());
    }

    private int toScreenY(Entity entity) {
        return (int) (entity.getPosition().getY() * getScaleY());
    }

    private int toScreenWidth(Entity entity) {
        return Math.max(1, (int) (entity.getSize().getWidth() * getScaleX()));
    }

    private int toScreenHeight(Entity entity) {
        return Math.max(1, (int) (entity.getSize().getHeight() * getScaleY()));
    }

    private double getScaleX() {
        return getWidth() / (double) game.getCurrentLevel().getBoard().getWidth();
    }

    private double getScaleY() {
        return getHeight() / (double) game.getCurrentLevel().getBoard().getHeight();
    }
}


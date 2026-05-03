package dopo.hardestgame.presentation;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class BoardPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
    }
}


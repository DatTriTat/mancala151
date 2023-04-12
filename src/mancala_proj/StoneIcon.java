package mancala_proj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
 * This class represents a stone
 * @author pebbles
 *
 */

public class StoneIcon implements Icon{
	private static final int STONE_DIAMETER = 10;
	private Color color;
	
	public StoneIcon(Color color) {
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		Ellipse2D.Double stone = new Ellipse2D.Double(x, y, STONE_DIAMETER, STONE_DIAMETER);
		g2.fill(stone);
	}

	@Override
	public int getIconWidth() {
		return STONE_DIAMETER;
	}

	@Override
	public int getIconHeight() {
		return STONE_DIAMETER;
	}

}

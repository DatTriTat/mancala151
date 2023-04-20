package mancala_proj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/**
 * This class represents a stone
 * @author pebbles
 *
 */

public class StoneShape implements Shape{
	private static final int STONE_DIAMETER = 20; 
	private Color color;
	private int x;
	private int y;
	
	public StoneShape(int x, int y, Color color) {
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		Ellipse2D.Double stone = new Ellipse2D.Double(x, y, STONE_DIAMETER, STONE_DIAMETER);
		g2.fill(stone);
	}
	
	public int getNextX(int pitWidth) {
		int xNew =x + STONE_DIAMETER;
		if(xNew+STONE_DIAMETER > pitWidth) {
			xNew = 0;
		}
		return xNew;
	}
	
	public int getX() {
		return x;
	}
	
	public int getNextY(int pitWidth, int nextX) {
		int yNew = y;
		if(nextX == 0) {
			yNew += STONE_DIAMETER;
		}
		if(y >= pitWidth) {
			//throw exception: pit not big enough
		}
		return yNew;
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}

}

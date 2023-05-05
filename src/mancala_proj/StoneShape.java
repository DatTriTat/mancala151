package mancala_proj;

/**
 * This program represents a Mancala stone.
 * @author pebbles (Sandra Le, Dat Tri Tat, Ysabella Dela Cruz)
 */

import java.awt.*;
import java.awt.geom.*;

/**
 * This class represents a stone.
 */

public class StoneShape implements Shape{
	private static final int STONE_DIAMETER = 20; 
	private Color color;
	private int x;
	private int y;
	
	/**
	 * Constructs a Stone.
	 * @param x the x coordinate of this stone
	 * @param y the y coordinate of this stone
	 * @param color the color of this stone
	 */
	public StoneShape(int x, int y, Color color) {
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws the stone.
	 * @param g2 
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		Ellipse2D.Double stone = new Ellipse2D.Double(x, y, STONE_DIAMETER, STONE_DIAMETER);
		g2.fill(stone);
	}
	
	/**
	 * Returns the x coordinate of the next stone after adding one to a pit
	 * @param pitWidth the width of the pit
	 * @return the x coordinate of the next stone after adding one to a pit
	 */
	public int getNextXAfterAdd(int pitWidth) {    
		int xNew =x + STONE_DIAMETER;
		if(xNew+STONE_DIAMETER > pitWidth) {
			xNew = 0;
		}
		return xNew;
	}
	
	/**
	 * Returns the x coordinate of this stone.
	 * @return the x coordinate of this stone
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of this stone.
	 * @return the y coordinate of this stone
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the y coordinate of the next stone after adding one to a pit.
	 * @param pitWidth the width of the pit
	 * @param nextX the x coordinate of the next stone
	 * @return the y coordinate of the next stone after adding one to a pit
	 */
	public int getNextY(int pitWidth, int nextX) {
		int yNew = y;
		if(nextX == 0) {
			yNew += STONE_DIAMETER;
		}
		if(y >= pitWidth) {
		}
		return yNew;
		
	}

	@Override
	public Rectangle getBounds() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Rectangle2D getBounds2D() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(double x, double y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Point2D p) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Rectangle2D r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		throw new UnsupportedOperationException();
	}

}
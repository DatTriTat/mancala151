package mancala_proj;

/**
 * This program implements a Mancala pit that a user can interact with to move stones. (viewer and controller)
 * @author pebbles
 */

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class represents a pit, which can contain stones.
 */
public class Pit extends JPanel implements ChangeListener{
	public static final int PIT_WIDTH = 100;
	public static final Color STONE_COLOR = Color.GRAY;
	
	private String label;
	private ArrayList<StoneShape> stones;
	private int xStone = 0;
	private int yStone = 0;
	
	/**
	 * Constructs a pit.
	 * @param letterNum the label associated with this pit
	 */
	public Pit(String letterNum) {
		//error message if letterNum isn't 2 characters: a letter, a number
		if(letterNum.length() != 2 || !Character.isLetter(letterNum.charAt(0)) || !Character.isDigit(letterNum.charAt(1))) {
			throw new IllegalArgumentException(letterNum + " is an invalid label; must be a letter and a 1 digit number (e.g. A1)");
		}
		//initialize instance variables
		label = letterNum;
		stones = new ArrayList<>();
		setPreferredSize(new Dimension(PIT_WIDTH, PIT_WIDTH));
		//MouseListeners listeners = new MouseListeners();
		//addMouseListener(listeners);
		//addMouseMotionListener(listeners);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * Returns the list the stones.
	 * @return the list of stones
	 */
	public ArrayList<StoneShape> getStones(){
		return stones;
	}
	
	/**
	 * Adds a stone to this pit. //use flowlayout instead? --> stone is a icon
	 */
	public void addStone() { 
		StoneShape stone = new StoneShape(xStone,yStone,STONE_COLOR);
		stones.add(stone);
		xStone = stone.getNextXAfterAdd(PIT_WIDTH);
		yStone = stone.getNextY(PIT_WIDTH, xStone);
		repaint();
	}
	
	/**
	 * Takes away a stone from this pit.
	 */
	public void subtractStone() {
		if(stones.size() > 0) {
			StoneShape toRemove = stones.get(stones.size()-1);
			stones.remove(toRemove);
			xStone = toRemove.getX();
			yStone = toRemove.getY();
			
		}
		repaint();
	}
	
	/**
	 * Paints the component.
	 * @param g the graphics
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//loop and draw the stones
		for(StoneShape s: stones) {
			s.draw(g2);
		}
	}
	
	/**
	 * Determines if this pit contains the specified point.
	 * @param point the point
	 * @return true if the pit contains the point, otherwise false
	 */
	public boolean contains(Point2D point) {
		double xMouse = point.getX();
		double yMouse = point.getY();
		double x = getX();
		double y = getY();
		if (xMouse >= x && xMouse <= x + PIT_WIDTH && yMouse >= y && yMouse <= y + PIT_WIDTH) {
			return true;
		}
		return false;
	}
	
	/**
	 * This class implements MouseListener and MouseMotionListener.
	 */
	//private class MouseListeners extends MouseAdapter{
		/**
		 * Executes a player's turn from this pit.
		 * @param event the event
		 */
		/**public void mousePressed(MouseEvent event) {
			if(stones.size() >= 0) {
				//update model
				addStone();//testing
			}
			
		}
	} */

	/**
	 * Changes the state of this pit according to the model.
	 * @param e the change event
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		//update model
	}

}
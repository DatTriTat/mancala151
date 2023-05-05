package mancala_proj;

/**
 * This program represents a Mancala.
 * @author pebbles
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * This class represents a Mancala, which can hold stones.
 */
public class MancalaPit extends JPanel{
	public static final int PIT_WIDTH = 100;
	public static final int PIT_HEIGHT = 225;
	public static final Color STONE_COLOR = Color.GRAY;
	private ArrayList<StoneShape> stones;
	private int xStone = 0;
	private int yStone = 0;
	
	/**
	 * Constructs a pit.
	 * @param letterNum the label associated with this pit
	 */
	public MancalaPit() {
		stones = new ArrayList<>();
		setPreferredSize(new Dimension(PIT_WIDTH, PIT_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Returns the amount of stones in the mancala.
	 * @return the amount of stones
	 */
	public int getNumStones(){
		return stones.size();
	}
	
	/**
	 * Adds a stone to this pit.
	 */
	public void addStone() { 
		StoneShape stone = new StoneShape(xStone,yStone,STONE_COLOR);
		stones.add(stone);
		xStone = stone.getNextXAfterAdd(PIT_WIDTH);
		yStone = stone.getNextY(PIT_WIDTH, xStone);
		repaint();
	}
	
	/**
	 * Removes a stone from this Mancala.
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
	 * Set background color for pits
	 * @param style associated with this pit
	 */
	public void setColor(StyleManager style) {
		setBackground(style.getPitColor());
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
}

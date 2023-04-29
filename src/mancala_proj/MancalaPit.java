package mancala_proj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

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
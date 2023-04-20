package mancala_proj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This class represents a pit, which contains stones
 * @author pebbles
 *
 */
public class InnerPit extends JPanel implements ChangeListener{
	public static final int PIT_WIDTH = 100;
	public static final Color STONE_COLOR = Color.GRAY;
	
	private String label;
	private ArrayList<StoneIcon> stones;
	private int xStone = 0;
	private int yStone = 0;
	
	public InnerPit(String letterNum, Color color) {
		//error message if letterNum isn't 2 characters: a letter, a number
		if(letterNum.length() != 2 || !Character.isLetter(letterNum.charAt(0)) || !Character.isDigit(letterNum.charAt(1))) {
			throw new IllegalArgumentException(letterNum + " is an invalid label; must be a letter and a 1 digit number (e.g. A1)");
		}
		//initialize instance variables
		label = letterNum;
		stones = new ArrayList<>();
		setBackground(color);
		setPreferredSize(new Dimension(PIT_WIDTH, PIT_WIDTH));
		MouseListeners listeners = new MouseListeners();
		addMouseListener(listeners);
		addMouseMotionListener(listeners);
	}
	//add, remove stones methods --> repaint
	public void addStone() {
		StoneIcon stone = new StoneIcon(xStone,yStone,STONE_COLOR);
		stones.add(stone);
		xStone = stone.getNextX(PIT_WIDTH);
		yStone = stone.getNextY(PIT_WIDTH, xStone);
		repaint();
	}
	public void subtractStone() {
		if(stones.size() > 0)
			stones.remove(stones.size()-1);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("Size: " + stones.size());
		//loop and draw the stones
		for(StoneIcon s: stones) {
			s.draw(g2);
		}
	}
	
	public boolean contains(Point2D point) {
		double xMouse = point.getX();
		double yMouse = point.getY();
		double x = getX();
		double y = getY();
		if (xMouse >= x && xMouse <= x + PIT_WIDTH && yMouse >= y && yMouse <= y + PIT_WIDTH) {
			System.out.println(label);
			return true;
		}
		System.out.println("false");
		return false;
	}
	
	private class MouseListeners extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if(stones.size() >= 0) {
				//update model
				addStone();//testing
			}
			
		}
	}
	//testing
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		InnerPit p = new InnerPit("A3",Color.GREEN);
		p.addStone();
		p.addStone();
		InnerPit p1 = new InnerPit("A2",Color.GREEN);
		p1.addStone();
		frame.setLayout(new FlowLayout());
		frame.add(p);
		frame.add(p1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}

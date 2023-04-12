package mancala_proj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * This class represents a pit, which contains stones
 * @author pebbles
 *
 */
public class Pit extends JPanel {
	private String label;
	private ArrayList<StoneIcon> stones; //change to arraylist of stones
	private Color color;
	
	public Pit(String letterNum, ArrayList<StoneIcon> stones, Color color) {
		//error message if letterNum isn't 2 characters: a letter, a number
		if(letterNum.length() != 2 || !Character.isLetter(letterNum.charAt(0)) || !Character.isDigit(letterNum.charAt(1))) {
			throw new IllegalArgumentException(letterNum + " is an invalid label; must be a letter and a 1 digit number (e.g. A1)");
		}
		//initialize instance variables
		label = letterNum;
		this.stones = stones;
		this.color = color;
	}
	
	public void paintComponent(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		setBackground(color);
		//loop and draw the stones
	}
	
	

}

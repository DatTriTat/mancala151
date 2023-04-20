package mancala_proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This class represents a labeled pit
 * @author pebbles
 *
 */
public class Pit extends JPanel{	
	private String letNum;
	private Color color;
	private InnerPit pit;
	
	public Pit(String letterNum, Color color) {
		//error message if letterNum isn't 2 characters: a letter, a number
		if(letterNum.length() != 2 || !Character.isLetter(letterNum.charAt(0)) || !Character.isDigit(letterNum.charAt(1))) {
			throw new IllegalArgumentException(letterNum + " is an invalid label; must be a letter and a 1 digit number (e.g. A1)");
		}
		//initialize instance variables
		letNum = letterNum;
		this.color = color;
		//panel
		setLayout(new BorderLayout());
		JLabel label = new JLabel(letterNum);
		add(label, BorderLayout.NORTH);
		pit = new InnerPit(letterNum, color);
		add(pit, BorderLayout.CENTER);
	}	
	
	public InnerPit getPit() {
		return pit;
	}
	
	public void addStone() {
		pit.addStone();
	}
	public void subtractStone() {
		pit.subtractStone();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		Pit p = new Pit("A3",Color.GREEN);
		p.addStone();
		p.addStone();
		Pit p1 = new Pit("A2",Color.GREEN);
		p1.addStone();
		frame.setLayout(new FlowLayout());
		frame.add(p);
		frame.add(p1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}

}

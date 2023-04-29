package mancala_proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class represents a labeled pit, consisting of a label and a pit
 * @author pebbles
 *
 */
public class LabeledPit extends JPanel{	
	private DataModel model;
	private String letNum;
	private Pit pit;
	private JLabel label;
	
	/**
	 * Constructs labeled pit.
	 * @param letterNum the label of the pit (e.g. A1)
	 * @precondition letterNum has 2 characters: letterNum.charAt(0) is 'A' or 'B' and letterNum.char(1) is a digit
	 */
	public LabeledPit(DataModel model, String letterNum) {
		this.model = model;
		//error message if letterNum isn't 2 characters: a letter, a number
		char letter = letterNum.charAt(0);
		char digit = letterNum.charAt(1);
		if(letterNum.length() != 2 || !Character.isLetter(letter) || !Character.isDigit(digit)) {
			throw new IllegalArgumentException(letterNum + " is an invalid label; must be a letter and a 1 digit number (e.g. A1)");
		}
		if(letter != ('A') && letter != ('B')){
			throw new IllegalArgumentException(letterNum + " is an invalid label; letter much be 'A' or 'B'");
		}
		//initialize instance variables
		letNum = letterNum;
		//panel
		setLayout(new BorderLayout());
		label = new JLabel(letterNum);
		if(letter == 'A')
			add(label, BorderLayout.NORTH);
		if(letter == 'B')
			add(label, BorderLayout.SOUTH);
		pit = new Pit(letterNum);
		add(pit, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pit.repaint();
	}
	
	/**
	 * Returns the pit of this labeled pit.
	 * @return the pit of this labeled pit
	 */
	public Pit getPit() {
		return pit;
	}
	
	/**
	 * Returns the content of the label.
	 * @return the content of the label
	 */
	public String getLabel() {
		return letNum;
	}
	
	/**
	 * Adds a stone to the pit.
	 */
	public void addStone() {
		pit.addStone();
	}
	
	/**
	 * Takes away a stone in the pit.
	 */
	public void subtractStone() {
		pit.subtractStone();
	}
	
	/**
	 * Sets the color of the pit and label according to the style.
	 * @param style the style of the board
	 */
	public void setColor(StyleManager style) {
		pit.setBackground(style.getPitColor());
		label.setForeground(style.getFontColor());
		label.setOpaque(true);
		label.setBackground(style.getBoardColor());
	}

//	//testing
//	public static void main(String args[]) {
//		JFrame frame = new JFrame();
//		
//		LabeledPit p = new LabeledPit("B3");
//		p.addStone();
//		p.addStone();
//		LabeledPit p1 = new LabeledPit("B2");
//		p1.addStone();
//		LabeledPit p2 = new LabeledPit("A1");
//		LabeledPit p3 = new LabeledPit("A2");
//		p3.addStone();
//		p3.addStone();
//		p3.addStone();
//		p3.subtractStone();
//		p3.addStone();
//		
//		p.setColor(Color.GREEN);
//		p1.setColor(Color.GREEN);
//		p2.setColor(Color.GREEN);
//		p3.setColor(Color.GREEN);
//		
//		frame.setLayout(new FlowLayout());
//		frame.add(p);
//		frame.add(p1);
//		frame.add(p2);
//		frame.add(p3);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(500,500);
//		frame.setVisible(true);
//		
//	}

}
package mancala_proj;

/**
 * This program implements a labeled pit, consisting of a label and a Mancala pit.
 * @author pebbles (Sandra Le, Dat Tri Tat, Ysabella Dela Cruz)
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A labeled pit, consisting of a label and a Mancala pit.
 */
public class LabeledPit extends JPanel {	
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
		
		MouseListeners listeners = new MouseListeners();
		addMouseListener(listeners);
		addMouseMotionListener(listeners);
		add(pit, BorderLayout.CENTER);
	}
	
	/**
   * Paints labeled pit.
   * @param g graphics tool
   */
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
	 * Returns the number of stones in the pit.
	 * @return the number of stones in pit.
	 */
	public int getNumStones() {
		return pit.getStones().size();
	}
	
	/**
	 * Returns if the pit is empty.
	 * @return boolean value to represent if pit is empty
	 */
	public boolean isEmpty() {
		if (pit.getStones().size() == 0) {
			return true;
		}
		return false;
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
	 * Removes a stone in the pit.
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

	/**
	 * Implements Listeners for Mouse related events
	 */
	private class MouseListeners extends MouseAdapter {
		/**
		 * Updates the data and change listeners of the model if the mouse is pressed in the pit of this labeled pit.
		 * @param e the Mouse event
		 */
		public void mousePressed(MouseEvent e) {
			if(model.getGame()) {
				Point2D point = new Point2D.Double(e.getX(), e.getY());
				if (pit.contains(point)) {
					model.changeData(LabeledPit.this);
					model.update();
				}
			}
		}
	}	
}

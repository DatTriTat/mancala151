package mancala_proj;

/**
 * This program implements a labeled pit, consisting of a label and a Mancala pit.
 * @author pebbles
 */

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A labeled pit, consisting of a label and a Mancala pit.
 */
public class LabeledPit extends JPanel implements MouseListener {	
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
		
		addMouseListener(this);
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
	 * Returns the number of stones in the pit.
	 * @return the number of stones in pit.
	 */
	public int getNumStones() {
		return pit.getStones().size();
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

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(model.getGame()) {
			Point2D point = new Point2D.Double(e.getX(), e.getY());
			if (pit.contains(point)) {
				model.pushUndo();
				model.changeData(this);
				model.update();
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}

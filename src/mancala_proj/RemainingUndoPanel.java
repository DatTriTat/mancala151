package mancala_proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RemainingUndoPanel extends JPanel implements ChangeListener{
	private DataModel model;
	private boolean isP1Turn;
	private boolean prevIsP1;
	private JLabel p1Label;
	private JLabel p2Label;
	private JLabel errorText;

	/**
	 * Constructs a panel that displays the number of undos remaining for a player and appropriate error messages.
	 * @param model the model that manages data and views
	 */
	public RemainingUndoPanel(DataModel model) {
		this.model = model;
		isP1Turn = model.getTurn();
		prevIsP1 = model.prevIsP1();
		
		JLabel remainingUndo = new JLabel("Remaining Undos: ");
		remainingUndo.setFont(new Font("Arial", Font.PLAIN, 12));
		remainingUndo.setHorizontalAlignment(JTextField.CENTER);
		remainingUndo.setForeground(Color.BLACK);
		
		p1Label = new JLabel("[ A : 3 ]");
		p1Label.setFont(new Font("Arial", Font.PLAIN, 12));
		p1Label.setHorizontalAlignment(JTextField.CENTER);
		p1Label.setForeground(Color.BLACK);
		
		p2Label = new JLabel("[ B : - ]");
		p2Label.setFont(new Font("Arial", Font.PLAIN, 12));
		p2Label.setHorizontalAlignment(JTextField.CENTER);
		p2Label.setForeground(Color.BLACK);
		
		errorText = new JLabel();
		errorText.setFont(new Font("Arial", Font.PLAIN, 12));
		errorText.setHorizontalAlignment(JTextField.CENTER);
		errorText.setForeground(Color.RED);
		errorText.setVisible(false);
		
		JPanel undoCountPanel = new JPanel();
		undoCountPanel.add(remainingUndo);
		undoCountPanel.add(p1Label);
		undoCountPanel.add(p2Label);
		
		setLayout(new BorderLayout());
		add(errorText, BorderLayout.NORTH);
		add(undoCountPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates the remaining undos labels when the current player changes
	 * @param g the graphics context
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    //turn just changed (prev player may or may not be able to undo)
	    if(isP1Turn != prevIsP1) { 
	    	//can't undo (ex. undo before starting game)
	    	if (!model.isUndoEnabled() && !errorText.isVisible()) {
				setErrorText("Please make a move first.");
				setErrorVisible(true);
			}	
		    //prev player B has no more undoes
	    	else if(isP1Turn && model.getP2NumUndo() == 3) {
				p1Label.setText("[ A : 3 ]");
				p2Label.setText("[ B : - ]");
			}
			//prev player B can undo
			else if(isP1Turn) {
				p1Label.setText("[ A : 3 ]");
				p2Label.setText("[ B : " + (3 - model.getP2NumUndo()) + " ]");
			}
			//prev player A has no more undos
			else if(!isP1Turn && model.getP1NumUndo() == 3) {
				p1Label.setText("[ A : - ]");
				p2Label.setText("[ B : 3 ]");
			}
			//prev player A can undo
			else if(isP1Turn) {
				p1Label.setText("[ A : " + (3 - model.getP1NumUndo()) + " ]");
				p2Label.setText("[ B : 3 ]");
			}
	    }
	}
	
	/**
	 * Sets the player 1 remaining undo label to the specified text..
	 * @param text denotes the number of remaining undoes for player 1
	 */
	public void setP1Text(String text) {
		p1Label.setText(text);
	}
	
	/**
	 * Sets the player 2 remaining undo label to the specified text.
	 * @param text denotes the number of remaining undoes for player 2
	 */
	public void setP2Text(String text) {
		p2Label.setText(text);
	}
	
	/**
	 * Sets the error label to the specified text.
	 * @param text the error message
	 */
	public void setErrorText(String text) {
		errorText.setText(text);
	}
	
	/**
	 * Sets the visibility of the error label.
	 * @param t determines if the error label is visible
	 */
	public void setErrorVisible(boolean t) {
		errorText.setVisible(t);
	}

	/**
	 * Updates the turn states and player 1 and 2 labels according to the model.
	 * @param e the change event
	 */
	@Override
	public void stateChanged(ChangeEvent e) { //should be called when a pit is pressed
		isP1Turn = model.getTurn();
		prevIsP1 = model.prevIsP1();
		setErrorVisible(false);
		repaint();
	}
}

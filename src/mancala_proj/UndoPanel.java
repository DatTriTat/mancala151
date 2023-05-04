package mancala_proj;
/**
 * This program implements the Undo button and displays remaining undoes available and possible error messages
 * @author pebbles
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Panel consisting of an undo button and labels that keep track of remaining undoes available and possible error messages
 */
public class UndoPanel extends JPanel implements ChangeListener{
	private DataModel model;
	private boolean isP1Turn;
	private boolean prevIsP1;
	private JLabel p1Label;
	private JLabel p2Label;
	private JLabel errorLabel;

	/**
	 * Constructs a panel that displays the number of undos remaining for a player and appropriate error messages.
	 * @param model the model that manages data and views
	 */
	public UndoPanel(DataModel model) {
		this.model = model;
		isP1Turn = model.getTurn();
		prevIsP1 = model.prevIsP1();
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(event -> {	
			//all undoes used
			if (model.getNumUndo() == 3) {
				//A used all undoes
				if (model.getP1NumUndo() == 3) {
					//on B's turn
					if(!model.getTurn()) {
						setErrorText("Player A cannot use more undos. Player B make a move.");
						setP1Text("[ A : - ]");
						setP2Text("[ B : 3 ]");
					}
					//on A's turn: when Player A last move (couldn't undo) gives them a free turn
					else {
						setErrorText("Player A cannot use more undos. Player A make a move.");
						setP1Text("[ A : 0 ]");
						setP2Text("[ B : - ]");
					}
					
					setErrorVisible(true);
				}
				//B used all undos
				else if (model.getP2NumUndo() == 3) {
					//on A's turn
					if(model.getTurn()) {
						setErrorText("Player B cannot use more undos. Player A make a move.");
						setP1Text("[ A : 3 ]");
						setP2Text("[ B : - ]");
					}
					//on B's turn: when Player B last move (couldn't undo) gives them a free turn
					else {
						setErrorText("Player B cannot use more undos. Player B make a move.");
						setP1Text("[ A : - ]");
						setP2Text("[ B : 0 ]");
					}
					setErrorVisible(true);
				}
				
			}
			//can't undo (multiple times in a row)
			else if (!model.isUndoEnabled()) {
				setErrorText("Please make a move first.");
				setErrorVisible(true);
			}	
			//last player still has undo(es) left
			else if (model.getNumUndo() < 3) { 
				model.setNumUndo(model.getNumUndo() + 1);
				//A undoes on A or B's turn
				if(!model.getTurn() && model.prevIsP1() || model.getTurn() && model.prevIsP1()) { 
					setP1Text("[ A : " + (3 - model.getP1NumUndo()) + " ]");
					setP2Text("[ B : - ]");
				}
				//B undoes on A or B's turn
				else if(model.getTurn() && !model.prevIsP1() || !model.getTurn() && !model.prevIsP1()) { 
					setP1Text("[ A : - ]");
					setP2Text("[ B : " + (3 - model.getP2NumUndo()) + " ]");
				}
				//for testing
				else {
					setP1Text("grr");
					setP2Text("brr");
				}
				
				model.popUndo();
				model.update();
				setErrorVisible(false);
			}
			
		});
		
		JLabel remainingUndo = new JLabel("Remaining Undoes: ");
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
		
		errorLabel = new JLabel();
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		errorLabel.setHorizontalAlignment(JTextField.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		JPanel undoPanel = new JPanel();
		undoPanel.add(undoButton);
		undoPanel.add(remainingUndo);
		undoPanel.add(p1Label);
		undoPanel.add(p2Label);
		
		setLayout(new BorderLayout());
		add(errorLabel, BorderLayout.NORTH);
		add(undoPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates the remaining undos labels when the current player changes.
	 * @param g the graphics context
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
//	    //turn just changed (prev player may or may not be able to undo)
//	    if(isP1Turn != prevIsP1 && !errorLabel.isVisible()) { 
//	    	//can't undo 
//	    	if (!model.isUndoEnabled()) {
//	    		//prev player B has no more undoes
//		    	if(isP1Turn && model.getP2NumUndo() == 3) {
//					p1Label.setText("[ A : 3 ]");
//					p2Label.setText("[ B : - ]");
//				}
//		    	//prev player A has no more undos
//				else if(!isP1Turn && model.getP1NumUndo() == 3) {
//					p1Label.setText("[ A : - ]");
//					p2Label.setText("[ B : 3 ]");
//				}
//		    	// (e.g. undo before starting game)
//				else {
//					setErrorText("Please make a move first.");
//					setErrorVisible(true);
//				}
//				
//			}	
//			//prev player B can undo
//			else if(isP1Turn) {
//				p1Label.setText("[ A : 3 ]");
//				p2Label.setText("[ B : " + (3 - model.getP2NumUndo()) + " ]");
//			}
//			//prev player A can undo
//			else if(!isP1Turn) {
//				p1Label.setText("[ A : " + (3 - model.getP1NumUndo()) + " ]");
//				p2Label.setText("[ B : 3 ]");
//			}
//	    }
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
		errorLabel.setText(text);
	}
	
	/**
	 * Sets the visibility of the error label.
	 * @param t determines if the error label is visible
	 */
	public void setErrorVisible(boolean t) {
		errorLabel.setVisible(t);
	}

	/**
	 * Updates the turn states and player 1 and 2 labels according to the model.
	 * @param e the change event
	 */
	@Override
	public void stateChanged(ChangeEvent e) { //should be called when a pit is pressed
		isP1Turn = model.getTurn();
		prevIsP1 = model.prevIsP1();
//		repaint();
		//turn just changed (prev player may or may not be able to undo)
	    if(isP1Turn != prevIsP1) { 
	    	//can't undo 
	    	if (!model.isUndoEnabled()) {
	    		//prev player B has no more undoes
		    	if(isP1Turn && model.getP2NumUndo() == 3) {
					p1Label.setText("[ A : 3 ]");
					p2Label.setText("[ B : - ]");
				}
		    	//prev player A has no more undos
				else if(!isP1Turn && model.getP1NumUndo() == 3) {
					p1Label.setText("[ A : - ]");
					p2Label.setText("[ B : 3 ]");
				}
		    	// (e.g. undo before starting game)
				else {
					setErrorText("Please make a move first.");
					setErrorVisible(true);
				}
				
			}	
			//prev player B can undo
			else if(isP1Turn) {
				p1Label.setText("[ A : 3 ]");
				p2Label.setText("[ B : " + (3 - model.getP2NumUndo()) + " ]");
			}
			//prev player A can undo
			else if(!isP1Turn) {
				p1Label.setText("[ A : " + (3 - model.getP1NumUndo()) + " ]");
				p2Label.setText("[ B : 3 ]");
			}
	    }
	    setErrorVisible(false);
	}
}

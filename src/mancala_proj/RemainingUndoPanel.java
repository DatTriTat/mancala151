package mancala_proj;

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
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(event -> {	
			if (model.getNumUndo() == 3) {
				
				if (model.getP1NumUndo() == 3) { //A used all undos
					if(!model.getTurn()) { //B's turn
						setErrorText("Player A cannot use more undos. Player B make a move.");
						setP1Text("[ A : - ]");
						setP2Text("[ B : 3 ]");
					}
					//when Player A last move (couldn't undo) gives them a free turn
					else { //A's turn
						setErrorText("Player A cannot use more undos. Player A make a move.");
						setP1Text("[ A : 0 ]");
						setP2Text("[ B : - ]");
					}
					
					setErrorVisible(true);
				}
				else if (model.getP2NumUndo() == 3) { //B used all undos
					if(model.getTurn()) { //A's turn
						setErrorText("Player B cannot use more undos. Player A make a move.");
						setP1Text("[ A : 3 ]");
						setP2Text("[ B : - ]");
					}
					//when Player B last move (couldn't undo) gives them a free turn
					else { //B's turn
						setErrorText("Player B cannot use more undos. Player B make a move.");
						setP1Text("[ A : - ]");
						setP2Text("[ B : 0 ]");
					}
					setErrorVisible(true);
				}
				
			}
			else if (!model.isUndoEnabled()) { //can't undo
				setErrorText("Please make a move first.");
				setErrorVisible(true);
			}	
			else if (model.getNumUndo() < 3) { //last player still has undo(es) left
				model.setNumUndo(model.getNumUndo() + 1);
				//B's turn and A undoes
				if(!model.getTurn() && model.prevIsP1()) { 
					setP1Text("[ A : " + (3 - model.getP1NumUndo()) + " ]");
					setP2Text("[ B : - ]");
				}
				//A's turn and A undoes
				else if(model.getTurn() && model.prevIsP1()) {
					setP1Text("[ A : " + (3 - model.getP1NumUndo()) + " ]");
					setP2Text("[ B : - ]");
				}
				//A's turn and B undoes
				else if(model.getTurn() && !model.prevIsP1()) { 
					setP1Text("[ A : - ]");
					setP2Text("[ B : " + (3 - model.getP2NumUndo()) + " ]");
				}
				//B's turn and B undoes
				else if(!model.getTurn() && !model.prevIsP1()) {
					setP1Text("[ A : - ]");
					setP2Text("[ B : " + (3 - model.getP2NumUndo()) + " ]");
				}
				else {
					setP1Text("grr"); //for testing
					setP2Text("brr");
				}
				
				model.popUndo();
				model.update();
				setErrorVisible(false);
			}
			
		});
		
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
		undoCountPanel.add(undoButton);
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
//	    //turn just changed (prev player may or may not be able to undo)
//	    if(isP1Turn != prevIsP1 && !errorText.isVisible()) { 
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

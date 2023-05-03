package mancala_proj;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class WinningPanel extends JPanel implements ChangeListener{
	private DataModel model;
	private boolean isP1Turn;
	private JLabel label;
	
    public WinningPanel(DataModel model) {
    	this.model = model;
    	isP1Turn = model.getTurn();
    	
    	label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        add(label);
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(model.totalP1() == 0 && model.totalP2() == 0 && model.getPlayer1() == 0 && model.getPlayer2() == 0) { //empty board
        	label.setText("");
        }
        else if(model.totalP1() == 0 || model.totalP2() == 0) {
			model.endGame();
			if (model.getPlayer1() > model.getPlayer2()) {
				label.setText("Player A won!");
	        } 
	        else if (model.getPlayer1() < model.getPlayer2()){
	            label.setText("Player B won!");
	        }
	        else {
            	label.setText("It's a draw!");
            }
		}
    	else {
    		if(isP1Turn) {
    			label.setText("Current Player: A");
    		}
    		else {
    			label.setText("Current Player: B");
    		}
    	}
    }
	@Override
	public void stateChanged(ChangeEvent e) {
		isP1Turn = model.getTurn();
		repaint();
	}
}

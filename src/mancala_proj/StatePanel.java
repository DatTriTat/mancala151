package mancala_proj;
/**
 * The program displays the current player or the winner of the game.
 * @author pebbles
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The panel that contains a label that displays the current player or the winner.
 *  * @author pebbles
 */
public class StatePanel extends JPanel implements ChangeListener{
	private DataModel model;
	private boolean isP1Turn;
	private JLabel label;
	
	/**
	 * Constructs panel, which consists of a label.
	 * @param model the model that manages the data and the views
	 */
    public StatePanel(DataModel model) {
    	this.model = model;
    	isP1Turn = model.getTurn();
    	
    	label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        add(label);
    }
    
    /**
     * Changes the label to the appropriate text.
     * @param g graphics tool
     */
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(model.totalP1() == 0 && model.totalP2() == 0 && model.getPlayer1() == 0 && model.getPlayer2() == 0) { //empty board
        	return;
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
    
    /**
     * Changes the state of this board according to the model.
     * @param e the change event
     */
	@Override
	public void stateChanged(ChangeEvent e) {
		isP1Turn = model.getTurn();
		if(model.totalP1() == 0 || model.totalP2() == 0) {
			model.endGame();
		}
		repaint();
	}
}

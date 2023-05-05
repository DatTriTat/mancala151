package mancala_proj;

/**
 * This program implements the Mancala board
 * @author pebbles (Sandra Le, Dat Tri Tat, Ysabella Dela Cruz)
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

/**
 * This class implements the Mancala board, which consists of labeled pits and Mancala pits.
 */
public class MancalaBoard extends JPanel implements ChangeListener {
	private static final int GAP_SIZE = 25;
	
	private DataModel model;
	private TreeMap<String, Integer> dataMap;
	private JPanel pitsPanel;
    private ArrayList<LabeledPit> pits;
    private MancalaPit player1Mancala;
    private MancalaPit player2Mancala;
    private StyleManager style;

    /**
     * Constructs this board.
     * @param model the model that manages the data and views 
     */
    public MancalaBoard(DataModel m) {
    	model = m; //set model of frame to DataModel
    	style = null; //no style chosen yet
    	pits = new ArrayList<>(); //arraylist of all pits
        dataMap = model.getData(); //populate initial data structure
    	
        for(String s: dataMap.keySet()) { //create labeled pits for each entry
        	if (s.equals("player1") || s.equals("player2")) { //skip player score entries
        		continue;
        	}

        	LabeledPit aPit = new LabeledPit(model, s);
            pits.add(aPit);
            int numStones = dataMap.get(s);
            for (int i = 0; i < numStones; i++) {
            	aPit.addStone();
        	}
        }
        
        pitsPanel = new JPanel(); //create panel for pits
        GridLayout gridLayout = new GridLayout(2,6);
        gridLayout.setHgap(GAP_SIZE);
        gridLayout.setVgap(GAP_SIZE);
        pitsPanel.setLayout(gridLayout);
        
        for(LabeledPit pit: pits) {
        	pitsPanel.add(pit);
        }
        
        setLayout(new FlowLayout());
        player1Mancala = new MancalaPit();
        player2Mancala = new MancalaPit();
        add(player1Mancala);
        add(pitsPanel);
        add(player2Mancala);
    }
    
    /**
     * Sets the style of this board.
     * @param style the style to set this board to.
     */
    public void setStyle(StyleManager style) {
    	this.style = style;
    }

    /**
     * Paints mancala board.
     * @param g graphics tool
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(style != null) {
        	setBackground(style.getBoardColor());
        	pitsPanel.setBackground(style.getBoardColor());
	        for(LabeledPit pit: pits) {
	        	pit.setColor(style);
	        }
	        player1Mancala.setColor(style);
	        player2Mancala.setColor(style);
        }
        
        int currListIndex = 0; //keep track of which pit is currently being updated
        for(String s: dataMap.keySet()) { //iterate through model to update stone amounts
        	if (s.equals("player1")) { //update player1 mancala
        		for (int i = player1Mancala.getNumStones(); i < dataMap.get("player1"); i++) {
            		player1Mancala.addStone();
            	}
        		for (int i = player1Mancala.getNumStones(); i > dataMap.get("player1"); i--) {
            		player1Mancala.subtractStone();
            	}
        	}
        	else if (s.equals("player2")) { //update player2 mancala
        		for (int i = player2Mancala.getNumStones(); i < dataMap.get("player2"); i++) {
            		player2Mancala.addStone();
            	}
        		for (int i = player2Mancala.getNumStones(); i > dataMap.get("player2"); i--) {
            		player2Mancala.subtractStone();
            	}
        	}
        	else { //update pits
            	LabeledPit aPit = pits.get(currListIndex);
            	for (int i = aPit.getNumStones(); i < dataMap.get(s); i++) {
            		aPit.addStone();
            	}
            	for (int i = aPit.getNumStones(); i > dataMap.get(s); i--) {
            		aPit.subtractStone();
            	}
            	
            	currListIndex++;
        	}
        }
    }

    /**
     * Changes the state of this board according to the model.
     * @param e the change event
     */
	@Override
	public void stateChanged(ChangeEvent e) {
		dataMap = model.getData();
		repaint();
	}
}
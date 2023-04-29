package mancala_proj;
/**
 * This program implements the Mancala board.
 * @author pebbles
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class implements the Mancala board, which consists of labeled pits and Mancala pits.
 */
public class MancalaBoard extends JPanel implements ChangeListener{
	private static final int GAP_SIZE = 25;
	
	private DataModel model;
	private TreeMap<String, Integer> dataMap;
	private JPanel pitsPanel;
    private ArrayList<LabeledPit> pits;
    private StyleManager style;
    private MancalaPit player1Mancala;
    private MancalaPit player2Mancala;
    private int currentPlayer;

    /**
     * Constructs this board.
     * @param model the model that manages the data and views. 
     */
    public MancalaBoard(DataModel model) {
        setLayout(new FlowLayout());
        this.model = model;
        style = null;
        dataMap = model.getData();
        pits = new ArrayList<>();
        for(String s: dataMap.keySet()) {
        	LabeledPit aPit = new LabeledPit(model, s);
        	pits.add(aPit);
        	int numStones = dataMap.get(s);
        	for (int i = 0; i < numStones; i++) {
        		aPit.addStone();
        	}
        }
        pitsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2,6);
        gridLayout.setHgap(GAP_SIZE);
        gridLayout.setVgap(GAP_SIZE);
        pitsPanel.setLayout(gridLayout);
        
        for(LabeledPit pit: pits) {
        	pitsPanel.add(pit);
        }
        
        player1Mancala = new MancalaPit();
        player2Mancala = new MancalaPit();
        add(player1Mancala);
        add(pitsPanel);
        add(player2Mancala);
        currentPlayer = 1;  
    }
    
    /**
     * Sets the style of this board.
     * @param style the style to set this board to.
     */
    public void setStyle(StyleManager style) {
    	this.style = style;
    }

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
        int currListIndex = 0;
        
        for(String s: dataMap.keySet()) {
        	int numStones = dataMap.get(s);
        	LabeledPit aPit = pits.get(currListIndex);
        	
        	for (int i = aPit.getPit().getStones().size(); i < numStones; i++) {
        		aPit.addStone();
        	}
        	for (int i = aPit.getPit().getStones().size(); i > numStones; i--) {
        		aPit.subtractStone();
        	}
        	aPit.repaint();
        	currListIndex++;
        }
        
        for (int i = player1Mancala.getStones().size(); i < model.getPlayer1(); i++) {
    		player1Mancala.addStone();
    	}
        for (int i = player1Mancala.getStones().size(); i > model.getPlayer1(); i--) {
    		player1Mancala.subtractStone();
    	}
        
        for (int i = player2Mancala.getStones().size(); i < model.getPlayer2(); i++) {
    		player2Mancala.addStone();
    	}
        for (int i = player2Mancala.getStones().size(); i > model.getPlayer2(); i--) {
    		player2Mancala.subtractStone();
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
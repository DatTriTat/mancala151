package mancala_proj;

import java.util.*;
import java.util.Map.*;
import javax.swing.event.*;

public class DataModel {
	private TreeMap<String, Integer> data; 
	private ArrayList<ChangeListener> listeners;
	private boolean ongoingGame; //true if the game is currently being played, false if otherwise
	private boolean p1Turn; //true for p1, false for p2
	
	private boolean finalMoveP1; //whether or not it is P1 final move
	private boolean finalMoveP2; //whether or not it is P2 final move
	private int numUndoP1; //number of remaining undos for P1
	private int numUndoP2; //number of remaining undos for P2
	private boolean undo; //enable undo button
	
	private TreeMap<String, Integer> prevMap = new TreeMap<String, Integer>(); //previous data mapping
	private boolean prevTurn; //previous turn
 
	/**
	 * Constructs DataModel object.
	 * @param TreeMap to hold amounts of stones in each pit
	 */
	public DataModel(TreeMap<String, Integer> d) {
		data = d;
		listeners = new ArrayList<ChangeListener>();
		data.put("A1", 0);
		data.put("A2", 0);
		data.put("A3", 0);
		data.put("A4", 0);
		data.put("A5", 0);
		data.put("A6", 0);
		data.put("B1", 0);
		data.put("B2", 0);
		data.put("B3", 0);
		data.put("B4", 0);
		data.put("B5", 0);
		data.put("B6", 0);
		data.put("player1", 0);
		data.put("player2", 0);
		
		finalMoveP1 = false;
		finalMoveP2 = false;
		numUndoP1 = 0;
		numUndoP2 = 0;
	}
	
	/**
	 * Retrieves current data of board.
	 * @return TreeMap of current data
	 */
	public TreeMap<String, Integer> getData(){
		return data;
	}
	
	/**
	 * Sets starting amount of stones in each pit based on user input.
	 * @param integer of stones to put in each pit
	 */
	public void setData(int num) {
		if (num == 3) {
			for (Entry<String, Integer> entry: data.entrySet()) {
				data.put(entry.getKey(), 3);
			}
			data.put("player1", 0);
			data.put("player2", 0);
		}
		else if (num == 4) {
			for (Entry<String, Integer> entry: data.entrySet()) {
				data.put(entry.getKey(), 4);
			}
			data.put("player1", 0);
			data.put("player2", 0);
		}
	}
	
	/**
	 * Set whether or not the game is currently running.
	 * @param boolean value that represents game status (true for running, false for not running)
	 */
	public void setGame(boolean g) {
		ongoingGame = g;
	}
	
	/**
	 * Get whether or not the game is currently running.
	 * @return boolean value that represents game status (true for running, false for not running)
	 */
	public boolean getGame() {
		return ongoingGame;
	}
	
	/**
	 * Set which player's turn it is.
	 * @param boolean value representing player turn (true for P1, false for P2).
	 */
	public void setTurn(boolean t) {
		p1Turn = t;
	}
	
	public String getCurrentPlayer() {
		if(getTurn()) {
			return "A";
		}
		return "B";
	}
	
	/**
	 * Retrieve which player's turn it is.
	 * @return boolean value representing player turn (true for P1, false for P2).
	 */
	public boolean getTurn() {
		return p1Turn;
	}
	
	/**
	 * Determine if P1 can perform more undos.
	 * @return boolean value representing if it is P1's final move.
	 */
	public boolean isFinalMoveP1() {
		return finalMoveP1;
	}
	
	/**
	 * Set whether or not P1 can perform more undos.
	 * @param t: boolean value representing if it is P1's final move.
	 */
	public void setFinalMoveP1(boolean t) {
		finalMoveP1 = t;
	}
	
	/**
	 * Determine if P1 can perform more undos.
	 * @return boolean value representing if it is P2's final move.
	 */
	public boolean isFinalMoveP2() {
		return finalMoveP2;
	}
	
	/**
	 * Set whether or not P1 can perform more undos.
	 * @param t: boolean value representing if it is P2's final move.
	 */
	public void setFinalMoveP2(boolean t) {
		finalMoveP2 = t;
	}
	
	/**
	 * Changes data based on the chosen pit.
	 * @param LabeledPit chosen by user
	 */
	public void changeData(LabeledPit pit) {
		if (totalP1() == 0 || totalP2() == 0) {
			return;
		}
		
		String choice = pit.getLabel(); //get pit label
		if (!getTurn() && choice.startsWith("A")) {
            return;
        }

        if (getTurn() && choice.startsWith("B")) {
            return;
        }
        
        if (pit.isEmpty()) {
        	return;
        }
        
        pushUndo();
		int amount = data.get(choice); //get amount of pebbles in pit
		data.put(choice, 0);
		String curr = pit.getLabel();
		while (amount > 0) {
			if (String.valueOf(curr.charAt(0)).equals("A")) { //next pit to place pebble in if on top side
				curr = curr.charAt(0) + Integer.toString(Character.getNumericValue(curr.charAt(1)) - 1);
				if (curr.equals("A0") && p1Turn) { //if next pit is p1 mancala and it is p1's turn increase p1 score
					data.put("player1", data.get("player1") + 1);
					amount--;
					if (amount == 0) { //if p1 final pebble lands in p1 mancala --> receive another turn
						p1Turn = true;
						return;
					}
					else if (amount != 0) { //if there are still remaining pebbles during p1's turn, go to p2 pits
						curr = "B1";
					}
				}
				else if (curr.equals("A0") && !p1Turn) { //if next pit is p1 mancala and it is p2's turn skip mancala and continue
					curr = "B1";
				}
			}
			
			else if (String.valueOf(curr.charAt(0)).equals("B")) { //next pit to place pebble in if on bottom side
				curr = curr.charAt(0) + Integer.toString(Character.getNumericValue(curr.charAt(1)) + 1);
				if (curr.equals("B7") && !p1Turn) { //if next pit is p2 mancala and it is p2's turn increase p2 score
					data.put("player2", data.get("player2") + 1);
					amount--;
					if (amount == 0) { //if p2 final pebble lands in p2 mancala --> receive another turn
						p1Turn = false;
						return;
					}
					else if (amount != 0) { //if there are still remaining pebbles during p2's turn, go to p1 pits
						curr = "A6";
					}
				}
				else if (curr.equals("B7") && p1Turn) { //if next pit is p2 mancala and it is p1's turn skip mancala and continue
					curr = "A6";
				}
			}
			
			data.put(curr, data.get(curr) + 1); //add pebble to current pit
			amount--;
		}
		
		int acrossPit = Character.getNumericValue(curr.charAt(1));
		
		//CAPTURE:
		//if p1 turn && p1 lands in their own pit && their pit is empty (except for just placed pebble) && pit across board contains pebbles
		if (p1Turn && curr.charAt(0) == ("A").charAt(0) && data.get(curr) == 1 && data.get("B" + Integer.toString(acrossPit)) != 0) {
			data.put("player1", data.get("player1") + data.get(curr) + data.get("B" + Integer.toString(acrossPit)));
			data.put(curr, 0);
			data.put("B" + Integer.toString(acrossPit), 0);
			p1Turn = false;
			return;
		}
		//if p2 turn && p2 lands in their own pit && their pit is empty (except for just placed pebble) && pit across board contains pebbles
		else if (!p1Turn && curr.charAt(0) == ("B").charAt(0) && data.get(curr) == 1 && data.get("A" + Integer.toString(acrossPit)) != 0) {
			data.put("player2", data.get("player2") + data.get(curr) + data.get("A" + Integer.toString(acrossPit)));
			data.put(curr, 0);
			data.put("A" + Integer.toString(acrossPit), 0);
			p1Turn = true;
			prevTurn = false;
			return;
		}
		else { //change turns
			if (p1Turn) {
				p1Turn = false;
				if (numUndoP1 == 3) {
					enableUndo(false);
					setFinalMoveP1(true);
					numUndoP2 = 0;
					return;
				}
				setFinalMoveP1(false);
				numUndoP2 = 0;
				return;
			}
			else if (!p1Turn) {
				p1Turn = true;
				if (numUndoP2 == 3) {
					enableUndo(false);
					setFinalMoveP2(true);
					numUndoP1 = 0;
					return;
				}
				setFinalMoveP2(false);
				numUndoP1 = 0;
				return;
			}
		}
	}
	
	/**
	 * Ends the game when one side is empty. Places all remaining stones in correct mancala.
	 */
	public void endGame() {
		ongoingGame = false;
		if (totalP1() == 0) { //check if p1 has run out of pieces on their side
			data.put("player2", data.get("player2") + totalP2());
			data.put("B1", 0);
			data.put("B2", 0);
			data.put("B3", 0);
			data.put("B4", 0);
			data.put("B5", 0);
			data.put("B6", 0);
        }
		else if (totalP2() == 0) { //check if p2 has run out of pieces on their side
			data.put("player1", data.get("player1") + totalP1());
			data.put("A1", 0);
			data.put("A2", 0);
			data.put("A3", 0);
			data.put("A4", 0);
			data.put("A5", 0);
			data.put("A6", 0);
        }
	}
	
	/**
	 * Retrieves the total amount of stones on player 1's side.
	 * @return integer amount of stones on player 1's side
	 */
	public int totalP1() {
		return data.get("A1") + data.get("A2") + data.get("A3") + data.get("A4") + data.get("A5") + data.get("A6");
	}
	
	/**
	 * Retrieves the total amount of stones on player 2's side.
	 * @return integer amount of stones on player 2's side
	 */
	public int totalP2() {
		return data.get("B1") + data.get("B2") + data.get("B3") + data.get("B4") + data.get("B5") + data.get("B6");
	}
	
	/**
	 * Retrieves the total amount of stones in player 1's mancala.
	 * @return integer amount of stones in player 1's mancala
	 */
	public int getPlayer1() {
		return data.get("player1");
	}
	
	/**
	 * Retrieves the total amount of stones in player 2's mancala.
	 * @return integer amount of stones in player 2's mancala
	 */
	public int getPlayer2() {
		return data.get("player2");
	}
	
	/**
	 * Set the number of remaining undos for the player who just made a move.
	 * @param num: number of remaining undos
	 */
	public void setNumUndo(int num) {
		boolean prev = prevTurn;
		if (prev) {
			numUndoP1 = num;
		}
		else if (!prev) {
			numUndoP2 = num;
		}
	}
	
	/**
	 * Retrieve the number of remaining undos for the player who just made a move.
	 * @return number of remaining undos
	 */
	public int getNumUndo() {
		boolean prev = prevTurn;
		if (prev) {
			return numUndoP1;
		}
		else if (!prev) {
			return numUndoP2;
		}
		return -1;
	}
	
	/**
	 * Returns number of remaining undos for Player 1
	 * @return number of remaining undos for Player 1
	 */
	public int getP1NumUndo() {
		return numUndoP1;
	}
	
	/**
	 * Returns number of remaining undos for Player 2
	 * @return number of remainging undos for Player 2;
	 */
	public int getP2NumUndo() {
		return numUndoP2;
	}
	
	public boolean prevIsP1() {
		return prevTurn;
	}
	
	/**
	 * Saves previous mapping.
	 */
	public void pushUndo() {
		TreeMap<String, Integer> clone = new TreeMap<String, Integer>();
		for (Entry<String, Integer> entry: data.entrySet()) {
			clone.put(entry.getKey(), entry.getValue());
		}
		prevMap = clone;
		prevTurn = p1Turn;
		enableUndo(true);
	}
	
	/**
	 * Sets current data to previous board mapping.
	 */
	public void popUndo() {
		TreeMap<String, Integer> board = prevMap;
		data = board;
		p1Turn = prevTurn;
		enableUndo(false);
	}
	
	/**
	 * Enable undo function.
	 * @param u: whether or not undo should be enabled
	 */
	public void enableUndo(boolean u) {
		undo = u;
	}
	
	/**
	 * Retrieve if undo function is available.
	 * @return whether or not undo is enabled
	 */
	public boolean isUndoEnabled() {	
		return undo;
	}
	
	/**
	 * Attach listener to model.
	 * @param c: listener to be attached
	 */
	public void attach(ChangeListener c) {
	      listeners.add(c);
	}
	
	/**
	 * Changes data for all listeners of model.
	 */
	public void update() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
	    }
	}
}

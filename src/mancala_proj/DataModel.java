package mancala_proj;

import java.util.*;
import java.util.Map.Entry;
import javax.swing.event.*;

public class DataModel {

	private TreeMap<String, Integer> data;
	private ArrayList<ChangeListener> listeners;
	private boolean turn; //true for p1, false for p2
	private boolean ongoingGame;
	private Deque<TreeMap<String, Integer>> undoStack = new ArrayDeque<TreeMap<String, Integer>>();
	
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
	}
	
	/**
	 * Returns the data structure
	 * @return the data structure
	 */
	public TreeMap<String, Integer> getData(){
		return data;
	}
	
	/*
	 * Sets amount in each pit to 3 or 4 pebbles.
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
	
	/*
	 * Set whether or not the game is currently playing.
	 */
	public void setGame(boolean g) {
		ongoingGame = g;
	}
	
	/*
	 * Get if a game is currently playing.
	 */
	public boolean getGame() {
		return ongoingGame;
	}
	
	/*
	 * Set who's turn it is.
	 */
	public void setTurn(boolean t) {
		turn = t;
	}
	
	/*
	 * Get who's turn it is.
	 */
	public boolean getTurn() {
		return turn;
	}
	
	/*
	 * Changes data based on pit chosen and who's turn it is.
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
        
		int amount = data.get(choice); //get amount of pebbles in pit
		data.put(choice, 0);
		String curr = pit.getLabel();
		while (amount > 0) {
			if (String.valueOf(curr.charAt(0)).equals("A")) { //next pit to place pebble in if on top side
				curr = curr.charAt(0) + Integer.toString(Character.getNumericValue(curr.charAt(1)) - 1);
				if (curr.equals("A0") && turn) { //if next pit is p1 mancala and it is p1's turn increase p1 score
					data.put("player1", data.get("player1") + 1);
					amount--;
					if (amount == 0) { //if p1 final pebble lands in p1 mancala --> receive another turn
						turn = true;
						return;
					}
					else if (amount != 0) { //if there are still remaining pebbles during p1's turn, go to p2 pits
						curr = "B1";
					}
				}
				else if (curr.equals("A0") && !turn) { //if next pit is p1 mancala and it is p2's turn skip mancala and continue
					curr = "B1";
				}
			}
			
			else if (String.valueOf(curr.charAt(0)).equals("B")) { //next pit to place pebble in if on bottom side
				curr = curr.charAt(0) + Integer.toString(Character.getNumericValue(curr.charAt(1)) + 1);
				if (curr.equals("B7") && !turn) { //if next pit is p2 mancala and it is p2's turn increase p2 score
					data.put("player2", data.get("player2") + 1);
					amount--;
					if (amount == 0) { //if p2 final pebble lands in p2 mancala --> receive another turn
						turn = false;
						return;
					}
					else if (amount != 0) { //if there are still remaining pebbles during p2's turn, go to p1 pits
						curr = "A6";
					}
				}
				else if (curr.equals("B7") && turn) { //if next pit is p2 mancala and it is p1's turn skip mancala and continue
					curr = "A6";
				}
			}
			
			data.put(curr, data.get(curr) + 1); //add pebble to current pit
			amount--;
		}
		
		int acrossPit = Character.getNumericValue(curr.charAt(1));
		
		//CAPTURE:
		//if p1 turn && p1 lands in their own pit && their pit is empty (except for just placed pebble) && pit across board contains pebbles
		if (turn && curr.charAt(0) == ("A").charAt(0) && data.get(curr) == 1 && data.get("B" + Integer.toString(acrossPit)) != 0) {
			data.put("player1", data.get("player1") + data.get(curr) + data.get("B" + Integer.toString(acrossPit)));
			data.put(curr, 0);
			data.put("B" + Integer.toString(acrossPit), 0);
			turn = false;
			return;
		}
		//if p2 turn && p2 lands in their own pit && their pit is empty (except for just placed pebble) && pit across board contains pebbles
		else if (!turn && curr.charAt(0) == ("B").charAt(0) && data.get(curr) == 1 && data.get("A" + Integer.toString(acrossPit)) != 0) {
			data.put("player2", data.get("player2") + data.get(curr) + data.get("A" + Integer.toString(acrossPit)));
			data.put(curr, 0);
			data.put("A" + Integer.toString(acrossPit), 0);
			turn = true;
			return;
		}
		else { //change turns
			if (turn) {
				turn = false;
				return;
			}
			else if (!turn) {
				turn = true;
				return;
			}
		}
	}
	
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
			return;
        }
		else if (totalP2() == 0) { //check if p2 has run out of pieces on their side
			data.put("player1", data.get("player1") + totalP1());
			data.put("A1", 0);
			data.put("A2", 0);
			data.put("A3", 0);
			data.put("A4", 0);
			data.put("A5", 0);
			data.put("A6", 0);
			return;
        }
	}
	
	/**
	 * Returns the number of stones player 1 has on their side of the board.
	 * @return the number of stones player 1 has on their side of the board.
	 */
	public int totalP1() {
		return data.get("A1") + data.get("A2") + data.get("A3") + data.get("A4") + data.get("A5") + data.get("A6");
	}
	
	/**
	 * Returns the number of stones player 2 has on their side of the board.
	 * @return the number of stones player 2 has on their side of the board.
	 */
	public int totalP2() {
		return data.get("B1") + data.get("B2") + data.get("B3") + data.get("B4") + data.get("B5") + data.get("B6");
	}
	
	/**
	 * Returns the number of stones player 1 has in their mancala.
	 * @return the number of stones player 1 has in their mancala
	 */
	public int getPlayer1() {
		return data.get("player1");
	}
	
	/**
	 * Returns the number of stones player 2 has in their mancala.
	 * @return the number of stones player 2 has in their mancala
	 */
	public int getPlayer2() {
		return data.get("player2");
	}
	
	public void pushUndo() {
		if (undoStack.size() == 3) {
			undoStack.remove();
		}
		
		TreeMap<String, Integer> clone = new TreeMap<String, Integer>();
		clone.put("A1", data.get("A1"));
		clone.put("A2", data.get("A2"));
		clone.put("A3", data.get("A3"));
		clone.put("A4", data.get("A4"));
		clone.put("A5", data.get("A5"));
		clone.put("A6", data.get("A6"));
		clone.put("B1", data.get("B1"));
		clone.put("B2", data.get("B2"));
		clone.put("B3", data.get("B3"));
		clone.put("B4", data.get("B4"));
		clone.put("B5", data.get("B5"));
		clone.put("B6", data.get("B6"));
		clone.put("player1", data.get("player1"));
		clone.put("player2", data.get("player2"));
		undoStack.push(clone);
	}
	
	public void popUndo() {
		TreeMap<String, Integer> board = undoStack.pop();
		data = board;
	}
	
	public void attach(ChangeListener c) {
	      listeners.add(c);
	}
	
	public void update() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
	    }
	}
}

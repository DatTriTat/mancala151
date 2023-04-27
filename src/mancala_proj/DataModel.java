package mancala_proj;

import java.util.*;
import java.util.Map.Entry;

import javax.swing.event.*;

public class DataModel {

	private TreeMap<String, Integer> data;
	private ArrayList<ChangeListener> listeners;
	private int player1;
	private int player2;
	
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
		player1 = 0;
		player2 = 0;
	}
	
	/**
	 * Returns the data structure
	 * @return the date structure
	 */
	public TreeMap<String, Integer> getData(){
		return data;
	}
	
	/**
	 * Returns the number of stones player 1 has in their mancala.
	 * @return the number of stones player 1 has in their mancala
	 */
	public int getPlayer1() {
		return player1;
	}
	
	/**
	 * Returns the number of stones player 2 has in their mancala.
	 * @return the number of stones player 2 has in their mancala
	 */
	public int getPlayer2() {
		return player2;
	}
	
	/*
	 * Sets amount in each pit to 3 or 4 pebbles.
	 */
	public void setData(int num) {
		if (num == 3) {
			for (Entry<String, Integer> entry: data.entrySet()) {
				data.put(entry.getKey(), 3);
			}
		}
		else if (num == 4) {
			for (Entry<String, Integer> entry: data.entrySet()) {
				data.put(entry.getKey(), 4);
			}
		}
	}
	
	/*
	 * Changes data based on pit chosen.
	 */
	public void changeData(Pit pit) {
		String choice = pit.getName();
		int amount = data.get(choice);
		int charVal = choice.charAt(0);
		String next = null;
		while (amount > 0) {
			next = String.valueOf((char) (charVal + 1));
			if (next.equals("G")) {
				player1++;
				amount--;
				if (amount == 0) {
					// how to trigger a bonus turn?
					break;
				}
				next = "a";
			}
			else if (next.equals("g")) {
				player2++;
				amount--;
				if (amount == 0) {
					// how to trigger a bonus turn?
					break;
				}
				next = "A";
			}
			data.put(next, data.get(next) + 1);
			charVal = next.charAt(0);
			amount--;
		}
		
		// if you land in an empty pit, collect stones on both sides and add to mancala
		if (data.get(next) == 1 ) {
			
		}
	}
	
	public void attach(ChangeListener c) {
	      listeners.add(c);
	}
	
	public void update() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
	    }
	}
	// update all pit amounts when done
}

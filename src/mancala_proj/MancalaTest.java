package mancala_proj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This program tests the Mancala game
 * @author pebbles
 * @version 4/11/2023
 */
public class MancalaTest {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 550;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setTitle("Mancala by pebbles");
        MancalaBoard board = new MancalaBoard(1000, 600);

		/*
		 * INITIAL SCREEN (STYLES)
		 * Display 2 buttons for users to choose a style
		 */
		JPanel initialScreen = new InitialPanel(new NightStyle(), new LightStyle());
		frame.setLayout(new BorderLayout());

		frame.add(initialScreen, BorderLayout.LINE_START );
		frame.add(board,BorderLayout.CENTER);
		/**
		 * SECOND SCREEN (BOARD SET-UP)
		 * Display empty board and ask if player wants 3 or 4 stones per pit
		 */
		
		
		/*
		 * GAME SCREEN
		 * Display board with stones and undo button
		 */
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(event -> {
			//undo 3 times max
		});
		
		/*
		 * WINNER SCREEN?
		 * Display who won the game
		 */
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}

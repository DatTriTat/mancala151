package mancala_proj;

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
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		/*
		 * INITIAL SCREEN (STYLES)
		 * Display 2 buttons for users to choose a style
		 */
		JPanel initialScreen = new JPanel();
		
		//change names of buttons after we figure out the styles
		JButton style1Button = new JButton("Style 1");
		JButton style2Button = new JButton("Style 2");
		style1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//draw style 1
				initialScreen.setVisible(false);
			}
		});
		style2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//draw style 2
				initialScreen.setVisible(false);
			}
		});
		
		initialScreen.add(style1Button);
		initialScreen.add(style2Button);
		frame.add(initialScreen);
		
		/**
		 * SECOND SCREEN (BOARD SET-UP)
		 * Display empty board and ask if player wants 3 or 4 stones per pit
		 */
		
		/*
		 * GAME SCREEN
		 * Display board with stones and undo button
		 */
		
		/*
		 * WINNER SCREEN?
		 * Display who won the game
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
}

package mancala_proj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This program tests the Mancala game
 * @author pebbles
 * @version 4/11/2023
 */
public class MancalaTest {
	public static final int DEFAULT_WIDTH = 1100;
	public static final int DEFAULT_HEIGHT = 380;
	public static final LightStyle DAY_STYLE = new LightStyle();
	public static final NightStyle NIGHT_STYLE = new NightStyle();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setTitle("Mancala by pebbles");
		DataModel model = new DataModel(new TreeMap<String, Integer>());
		JPanel secondScreen = new JPanel();
		secondScreen.setLayout(new BorderLayout());
		secondScreen.setVisible(false);
		
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
        MancalaBoard board = new MancalaBoard(model);
        gamePanel.add(board, BorderLayout.CENTER);
        JLabel playerALabel = new JLabel("<-- Player A");
        playerALabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel playerBLabel = new JLabel("Player B -->");
        playerBLabel.setHorizontalAlignment(JLabel.CENTER);
        gamePanel.add(playerALabel, BorderLayout.NORTH);
        gamePanel.add(playerBLabel, BorderLayout.SOUTH);
        gamePanel.add(new JLabel("Mancala A"), BorderLayout.WEST);
        gamePanel.add(new JLabel("Mancala B"), BorderLayout.EAST);
        secondScreen.add(gamePanel, BorderLayout.CENTER);

		/*
		 * INITIAL SCREEN (STYLES)
		 * Display 2 buttons for users to choose a style and go to gamePanel
		 */
		JPanel initialScreen = new InitialPanel(new NightStyle(), new LightStyle(), board, secondScreen);
		frame.setLayout(new BorderLayout());

		frame.add(initialScreen, BorderLayout.NORTH);
		frame.add(secondScreen,BorderLayout.CENTER);
		model.attach(board);
		/**
		 * SECOND SCREEN (BOARD SET-UP)
		 * Display empty board and ask if player wants 3 or 4 stones per pit
		 */
		JPanel numStonesPanel = new JPanel();
		JLabel label = new JLabel("Enter initial number of stones per pit (3 or 4): ");
		JTextField field = new JTextField(5);
		numStonesPanel.add(label);
		numStonesPanel.add(field);
		secondScreen.add(numStonesPanel,BorderLayout.SOUTH);
		
		JButton undoButton = new JButton("Undo");
		undoButton.setVisible(false);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(event -> {
			try {
				int numStones = Integer.parseInt(field.getText());
				if(numStones == 3 || numStones == 4) {
					model.setData(numStones);
					model.update();
					numStonesPanel.setVisible(false);
					undoButton.setVisible(true);
				}
			}
			catch(NumberFormatException ex){
				
			}
		});
		numStonesPanel.add(startButton, BorderLayout.SOUTH);
		/*
		 * GAME SCREEN
		 * Display board with stones and undo button
		 */
		
		undoButton.addActionListener(event -> {
			//undo 3 times max
		});
		secondScreen.add(undoButton, BorderLayout.NORTH);
		
		/*
		 * WINNER SCREEN?
		 * Display who won the game
		 */
//		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}

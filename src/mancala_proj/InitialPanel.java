package mancala_proj;

/**
 * This program implements the initial screen of the Mancala game.
 * @author pebbles (Sandra Le, Dat Tri Tat, Ysabella Dela Cruz)
 */

import java.awt.event.*;
import javax.swing.*;

/**
 * Implements the initial screen that allows the user to pick the desired style of the Mancala board.
 */
public class InitialPanel extends JPanel {
	/**
	 * Creates two buttons that determine the style of the Mancala board
	 * @param board the Mancala board to set style
	 * @param gamePanel the game screen to appear after the initial screen
	 */
	public InitialPanel(MancalaBoard board, JPanel gamePanel) {
		JLabel stylePrompt = new JLabel("Choose a style:");
		final StyleManager s1 = new NightStyle();
		final StyleManager s2 = new LightStyle();
		JButton style1Button = new JButton(s1.getName());
		JButton style2Button = new JButton(s2.getName());
		style1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				board.setStyle(s1);
				setVisible(false); //hides the panel
				gamePanel.setVisible(true);
			}
		});
		style2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				board.setStyle(s2);
				setVisible(false); //hides the panel
				gamePanel.setVisible(true);
			}
		});
		add(stylePrompt);
		add(style1Button);
		add(style2Button);
	}
}

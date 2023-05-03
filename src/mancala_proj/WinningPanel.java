package mancala_proj;

import java.awt.*;
import javax.swing.*;

public class WinningPanel extends JPanel {
    public WinningPanel(MancalaBoard board, JPanel initialPanel, DataModel model) {
        if (model.totalP1() != 0 && model.totalP2() != 0) {

        } else {
            JLabel winningLabel = new JLabel("Let's play!");
            winningLabel.setFont(new Font("Arial", Font.BOLD, 16));
            winningLabel.setForeground(Color.BLACK);
            initialPanel.setVisible(false);
            setVisible(true); // hides the panel
            if (model.getPlayer1() > model.getPlayer2()) {
                winningLabel.setText("Player A won!");
            } 
            else if (model.getPlayer1() < model.getPlayer2()) {
                winningLabel.setText("Player B won!");
            }
            else {
            	winningLabel.setText("It's a draw!");
            }
            add(winningLabel);
        }
    }

}

package mancala_proj;
import javax.swing.*;
import java.awt.*;

public class MancalaBoard extends JPanel {
    private int width;
    private int height;
    private int[] player1Holes;
    private int[] player2Holes;
    private int player1Mancala;
    private int player2Mancala;
    private int currentPlayer;

    public MancalaBoard(int width, int height) {
        this.width = width;
        this.height = height;
        player1Holes = new int[] {4, 4, 4, 4, 4, 4, 0};
        player2Holes = new int[] {4, 4, 4, 4, 4, 4, 0};
        player1Mancala = 0;
        player2Mancala = 0;
        currentPlayer = 1;
        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the Mancala board
        int x = 200;
        int y = 350;
        int holeSize = 50;
        int mancalaWidth = 100;
        int mancalaHeight = 200;
        int mancalaGap = 50;
        g2d.drawRect(0, 0, width, height);

        // Draw the first player's holes
        for (int i = 0; i < 6; i++) {
            g2d.drawOval(x, y, holeSize, holeSize);
            g2d.drawString(Integer.toString(player1Holes[i]), x+holeSize/2, y+holeSize/2);
            x += holeSize + mancalaGap;
        }

        // Draw the second player's holes
        x = 700;
        y = 200;
        for (int i = 0; i < 6; i++) {
            g2d.drawOval(x, y, holeSize, holeSize);
            g2d.drawString(Integer.toString(player2Holes[i]), x+holeSize/2, y+holeSize/2);
            x -= holeSize + mancalaGap;
        }

        // Draw the first player's Mancala
        g2d.drawRect(50, height/2 - mancalaHeight/2, mancalaWidth, mancalaHeight);
        g2d.drawString(Integer.toString(player1Mancala), 50+mancalaWidth/2, height/2);

        // Draw the second player's Mancala
        g2d.drawRect(width - mancalaWidth - 50, height/2 - mancalaHeight/2, mancalaWidth, mancalaHeight);
        g2d.drawString(Integer.toString(player2Mancala), width-mancalaWidth/2-50, height/2);

        // Draw the current player
        g2d.drawString("Player " + currentPlayer + "'s turn", width/2, height-50);
    }

}
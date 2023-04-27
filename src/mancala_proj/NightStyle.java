package mancala_proj;

import java.awt.Color;

/**
 * This class implements the dark mode style of the Mancala board
 * @author pebbles
 * @version 4/12/2023
 */
public class NightStyle implements StyleManager{
	public static final Color VERY_DARK_BLUE = new Color(0,0,153);
	
	public String getName() {
		return "Night";
	}

	public Color getPitColor() {
		return VERY_DARK_BLUE;
	}

	public Color getBoardColor() {
		return Color.BLACK;
	}
	
	public Color getFontColor() {
		return Color.LIGHT_GRAY;
	}

}

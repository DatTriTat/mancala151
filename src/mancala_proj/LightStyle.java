package mancala_proj;

import java.awt.Color;

/**
 * This class implements the lightmode style of the Mancala board
 * @author pebbles
 * @version 4/12/2023
 */
public class LightStyle implements StyleManager{
	public static final Color VERY_LIGHT_YELLOW = new Color(255,255,204);
	public static final Color LIGHT_BLUE = new Color(51,153,255);

	public String getName() {
		return "Day";
	}
	
	public Color getPitColor() {
		return VERY_LIGHT_YELLOW;
	}
	
	public Color getBoardColor() {
		return LIGHT_BLUE;
	}

	public Color getFontColor() {
		return Color.BLACK;
	}

}

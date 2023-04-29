package mancala_proj;

import java.awt.Color;

/**
 * Defines the light style of the Mancala board
 * @author pebbles
 */

/**
 * Defines the name and colors of the light style.
 */
public class LightStyle implements StyleManager{
	public static final Color VERY_LIGHT_YELLOW = new Color(255,255,204);
	public static final Color LIGHT_BLUE = new Color(51,153,255);

	/**
	 * Returns the name of this style.
	 * @return the name of this style
	 */
	public String getName() {
		return "Day";
	}
	
	/**
	 * Returns the pit color of this style.
	 * @return the pit color of this style
	 */
	public Color getPitColor() {
		return VERY_LIGHT_YELLOW;
	}
	
	/**
	 * Returns the board color of this style.
	 * @return the board color of this style
	 */
	public Color getBoardColor() {
		return LIGHT_BLUE;
	}

	/**
	 * Returns the pit label font color of this style.
	 * @return the pit label font color of this style
	 */
	public Color getFontColor() {
		return Color.BLACK;
	}


}
package mancala_proj;

import java.awt.Color;

public interface StyleManager {
	/**
	 * Returns the name of this style.
	 * @return the name of this style
	 */
	String getName();

	/**
	 * Returns the pit color of this style.
	 * @return the pit color of this style
	 */
	Color getPitColor();

	/**
	 * Returns the board color of this style.
	 * @return the board color of this style
	 */
	Color getBoardColor();

	/**
	 * Returns the pit label font color of this style.
	 * @return the pit label frot color of this style
	 */
	Color getFontColor();
}

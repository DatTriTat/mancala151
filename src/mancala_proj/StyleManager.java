package mancala_proj;

/**
 * Declares required methods of Mancala styles.
 * @author pebbles (Sandra Le, Dat Tri Tat, Ysabella Dela Cruz)
 */

import java.awt.Color;

/**
 * Declares required methods of Mancala styles.
 */
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

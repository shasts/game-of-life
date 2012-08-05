package com.thoughtworks.game.core.util;

import com.thoughtworks.game.core.model.Grid;

/**
 * Interface to display the grid state
 * 
 * @author shajahan
 * 
 */
public interface IPrinter {
	/**
	 * prints the grid to the respective output medium.
	 * 
	 * @param grid
	 */
	public void printThegrid(Grid grid);
	
	/**
	 * Prints the given string to the output medium.
	 * 
	 * @param string
	 */
	public void print(String string);

}

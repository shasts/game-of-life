package com.thoughtworks.game.core.model;


/**
 * State to represent the state of the cell.
 * 
 * @author shajahan
 * 
 */
public enum State {

	/**
	 * As the names indicates ALIVE is a state of a live cell and DEAD is a dead
	 * state
	 */

	ALIVE("L"), DEAD("D");

	/** The symbolic representation of the state */

	private String symbol;

	/**
	 * Constructor with symbol argument
	 * 
	 * @param symbol
	 *            String.
	 */
	State(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * To String override. Useful for outputting the grid.
	 */
	@Override
	public String toString() {
		return this.symbol;
	}
	
}

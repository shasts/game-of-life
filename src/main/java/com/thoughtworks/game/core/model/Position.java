package com.thoughtworks.game.core.model;

public class Position {
	/**
	 * The position in x Coordinate 
	 */
	final private int xCoordinate;
	/**
	 * The position in Y Coordinate
	 */
	final private int yCoordinate;
	/**
	 * Public constructor.
	 * 
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public Position(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()){
			return false;
		}
		Position other = (Position) obj;
		if (xCoordinate != other.getXCoordinate()) {
			return false;
		}
		if (yCoordinate != other.getYCoordinate()) {
			return false;
		}
		return true;

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getXCoordinate();
		result = prime * result + getYCoordinate();
		return result;
	}
	
	@Override
	public String toString() {
		return "X :" + xCoordinate + " Y: " + yCoordinate;
	}
}

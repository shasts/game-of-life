package com.thoughtworks.game.core.model;

import java.util.LinkedList;
import java.util.List;

/**
 * This represents the cell in the grid.
 * 
 * @author shajahan
 * 
 */
public class Cell implements Comparable<Cell>{

	/** Position of the cell in grid */
	private Position positon;

	/** The state of the cell */

	private State state;

	private Cell(int xCoordinate, int yCoordinate) {
		this.positon = new Position(xCoordinate, yCoordinate);
		state = State.DEAD;
	}
	
	public static Cell getInsatnce(int xCoordinate, int yCoordinate){
		 return  new Cell(xCoordinate, yCoordinate);
	}
	/**
	 * Sets the state
	 * 
	 * @param state
	 *            State
	 */

	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets the current state
	 * 
	 * @return State
	 */
	public State getState() {
		return state;
	}

	/**
	 * Marks the cell as dead
	 */

	public Cell markDead() {
		setState(State.DEAD);
		return this;
	}

	/**
	 * Marks the cell as alive
	 */
	public Cell markAlive() {
		setState(State.ALIVE);
		return this;
	}

	/**
	 * Says whether the cell is alive
	 * 
	 * @return TRUE/FALSE
	 */
	public boolean isAlive() {
		return getState() == State.ALIVE;
	}

	/**
	 * Gets the top left orthogonal position
	 * 
	 * @return Position
	 */
	public Position getTopLeftOrthogonalPosition() {
		return new Position(this.positon.getXCoordinate() - 1,
				this.positon.getYCoordinate() - 1);
	}

	/**
	 * Gets the top right orthogonal position
	 * 
	 * @return Position
	 */
	public Position getTopRightOrthogonalPosition() {
		return new Position(this.positon.getXCoordinate() - 1,
				this.positon.getYCoordinate() + 1);
	}

	/**
	 * Gets the bottom left orthogonal position
	 * 
	 * @return Position
	 */
	public Position getBottomLeftOrthogonalPosition() {
		return new Position(this.positon.getXCoordinate() + 1,
				this.positon.getYCoordinate() - 1);
	}

	/**
	 * Gets the bottom right orthogonal position
	 * 
	 * @return Position
	 */
	public Position getBottomRightOrthogonalPosition() {
		return new Position(this.positon.getXCoordinate() + 1,
				this.positon.getYCoordinate() + 1);
	}

	public Position getUpperPosition() {
		return new Position(this.positon.getXCoordinate() - 1,
				this.positon.getYCoordinate());
	}

	public Position getLowerPositon() {
		return new Position(this.positon.getXCoordinate() + 1,
				this.positon.getYCoordinate());
	}

	public Position getRightPosition() {
		return new Position(this.positon.getXCoordinate(),
				this.positon.getYCoordinate() + 1);
	}

	public Position getLeftPosition() {
		return new Position(this.positon.getXCoordinate(),
				this.positon.getYCoordinate() - 1);
	}

	public List<Position> getAllNeighbouringPositions() {
		List<Position> position = new LinkedList<Position>();
		position.add(getBottomLeftOrthogonalPosition());
		position.add(getLowerPositon());
		position.add(getBottomRightOrthogonalPosition());
		position.add(getRightPosition());
		position.add(getTopRightOrthogonalPosition());
		position.add(getUpperPosition());
		position.add(getTopLeftOrthogonalPosition());
		position.add(getLeftPosition());
		return position;
	}

	@Override
	public String toString() {
		return positon.toString() + " State: " + state.toString();
	}

	@Override
	public int hashCode() {
		return positon.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (!other.positon.equals(positon)) {
			return false;
		}
		if (getState() != other.getState()) {
			return false;
		}
		return true;
	}

	public int compareTo(Cell cell) {
		if (this.equals(cell)) {
			return 0;
		}
		if (cell.positon.getXCoordinate() == positon.getXCoordinate()) {
			return cell.positon.getYCoordinate() > positon.getYCoordinate() ? -1
					: 1;
		} else if (cell.positon.getYCoordinate() == positon.getYCoordinate()) {
			return cell.positon.getXCoordinate() > positon.getXCoordinate() ? -1
					: 1;
		} else {
			return cell.positon.getXCoordinate() > positon.getXCoordinate() ? -1
					: 1;
		}
	}
	
	public void setPositon(Position positon) {
		this.positon = positon;
	}
	
	public Position getPositon() {
		return positon;
	}

}

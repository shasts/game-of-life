package com.thoughtworks.game.core.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Grid represents the 2-D grid to contain the cells.
 * 
 * @author shajahan
 * 
 */
public class Grid {

	/**
	 * Data structure to hold living cells.
	 */
	private Set<Cell> liveCells = new LinkedHashSet<Cell>();

	/**
	 * Gets the current state of data structure containing living cells.
	 * 
	 * @return
	 */
	public Set<Cell> getLivingCells() {
		return liveCells;
	}

	/**
	 * Executes one generation of cells
	 */
	public void executeGenerationTick() {
		Set<Cell> nextGeneration = new LinkedHashSet<Cell>();
		for (Cell cell : liveCells) {
			applyRules(cell, nextGeneration);
		}
		liveCells = nextGeneration;
	}

	/**
	 * Applies the rules to the cells.
	 * 
	 * @param cell
	 * @param nextGeneration
	 */
	private void applyRules(Cell cell, Set<Cell> nextGeneration) {
		List<Position> neighbours = cell.getAllNeighbouringPositions();
		int numberOfLivecells = getNumberOfLiveNeighbours(neighbours);
		if (numberOfLivecells == 2 || numberOfLivecells == 3) {
			nextGeneration.add(cell);
		}
		applyRulesForDeadCells(nextGeneration, neighbours);
	}

	/**
	 * Gets the number of living neighbours
	 * 
	 * @param neighbors
	 * @return
	 */
	private int getNumberOfLiveNeighbours(List<Position> neighbors) {
		int numberOfLivecells = 0;
		Cell cell = null;
		for (Position neighbour : neighbors) {
			cell = Cell.getInsatnce(neighbour.getXCoordinate(),
					neighbour.getYCoordinate()).markAlive();
			if (liveCells.contains(cell)) {
				numberOfLivecells += 1;
			}
		}
		return numberOfLivecells;
	}

	/**
	 * Apply the rules for surrounding dead cells.
	 * 
	 * @param nextGeneration
	 *            Next Generation being created.
	 * @param neighbours
	 *            Neighbours of the current cell.
	 */
	private void applyRulesForDeadCells(Set<Cell> nextGeneration,
			List<Position> neighbours) {
		for (Position position : neighbours) {
			Cell cell = Cell.getInsatnce(position.getXCoordinate(),
					position.getYCoordinate()).markAlive();
			if (!liveCells.contains(cell)
					&& !nextGeneration.contains(cell)
					&& getNumberOfLiveNeighbours(cell
							.getAllNeighbouringPositions()) == 3) {
				nextGeneration.add(cell);
			}
		}
	}

	/**
	 * Adds a cell to the data structure containing living cells.
	 * 
	 * @param cell
	 */
	public void addToLivingCells(Cell cell) {
		liveCells.add(cell);
	}

	/**
	 * A grid is basically defined by its live cells. The surrounding dead cells
	 * doesn't make difference to the next generation. Hence one grid is equal
	 * to another if the live cells are at the same position.
	 * 
	 * @return TRUE/FALSE
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (!other.getLivingCells().equals(getLivingCells())) {
			return false;
		}
		return true;
	}

	/**
	 * Hash code of the grid is same as hash code of the lvie cells.
	 * 
	 * @return int hashCode
	 */
	@Override
	public int hashCode() {
		return getLivingCells().hashCode();
	}

}

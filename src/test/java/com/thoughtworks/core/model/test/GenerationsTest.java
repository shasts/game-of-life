package com.thoughtworks.core.model.test;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.game.core.model.Cell;
import com.thoughtworks.game.core.model.Grid;
import com.thoughtworks.game.core.model.GameOfLife;
import com.thoughtworks.game.core.util.MockGrid;

/**
 * Tests for the Game of life use case.
 * 
 * @author shajahan
 * 
 */
public class GenerationsTest {

	@Test
	public void theAddedLiveCellsShouldBePersisted() {
		Grid grid = getBlockPattern(false);
		Set<Cell> cells = grid.getLivingCells();
		Assert.assertEquals(4, cells.size());
		for (Cell cell : cells) {
			Assert.assertEquals(true, cell.isAlive());
		}
	}
	
	/**
	 * Tests the caching logic. The game shouldn't calculate the positions again if the entry exists in cache. 
	 */
	@Test
	public void shouldNotCalculateThePositionIfExistsInCache() throws Exception {
		GameOfLife game = GameOfLife.getInstance();
		int numberOfIteration = 0;
		Grid grid = getBlockPattern(true);
		while (numberOfIteration < 4) {
			game.playGame(grid);
			int numberOfTimesAlgorithmWasExecuted = ((MockGrid) grid)
					.getNumberOfTimeAlgorithmExecuted();
			Assert.assertEquals(1, numberOfTimesAlgorithmWasExecuted);
			numberOfIteration++;
		}

	}

	@Test
	public void testBlockPattern() throws Exception {
		System.out.println("\n-- Block Pattern --");
		GameOfLife game = GameOfLife.getInstance();
		Grid grid = game.playGame(getBlockPattern(false));
		Assert.assertEquals(getBlockPattern(false).getLivingCells(),
				grid.getLivingCells());
	}

	@Test
	public void testToadPattern() throws Exception {
		System.out.println("\n-- Toad Pattern --");
		GameOfLife game = GameOfLife.getInstance();
		Grid grid = game.playGame(getToadPattern());
		Assert.assertEquals(
				true,
				grid.getLivingCells().contains(
						Cell.getInsatnce(1, 0).markAlive()));
	}

	@Test
	public void testBlinkerPattern() throws Exception {
		System.out.println("\n-- Blinker Pattern --");
		GameOfLife game = GameOfLife.getInstance();
		Grid grid = game.playGame(getBlinkerPattern());
		Assert.assertEquals(
				true,
				grid.getLivingCells().contains(
						Cell.getInsatnce(1, 1).markAlive()));
	}

	@Test
	public void testBoatPattern() throws Exception {
		System.out.println("\n-- Boat Pattern --");
		GameOfLife game = GameOfLife.getInstance();
		Grid grid = game.playGame(getBoatPattern());
		Assert.assertEquals(
				true,
				grid.getLivingCells().contains(
						Cell.getInsatnce(0, 1).markAlive()));
	}	

	private Grid getBlockPattern(boolean mock) {
		Grid grid = null;
		if (mock) {
			grid = new MockGrid();
		} else {
			grid = new Grid();
		}
		grid.addToLivingCells(getLivingCellAtPosition(0, 0));
		grid.addToLivingCells(getLivingCellAtPosition(0, 1));
		grid.addToLivingCells(getLivingCellAtPosition(1, 1));
		grid.addToLivingCells(getLivingCellAtPosition(1, 0));
		return grid;
	}

	private Grid getToadPattern() {
		Grid grid = new Grid();		
		grid.addToLivingCells(getLivingCellAtPosition(0, 1));
		grid.addToLivingCells(getLivingCellAtPosition(0, 2));
		grid.addToLivingCells(getLivingCellAtPosition(0, 3));
		grid.addToLivingCells(getLivingCellAtPosition(1, 0));
		grid.addToLivingCells(getLivingCellAtPosition(1, 1));
		grid.addToLivingCells(getLivingCellAtPosition(1, 2));
		return grid;
	}

	private Grid getBlinkerPattern() {
		Grid grid = new Grid();
		grid.addToLivingCells(getLivingCellAtPosition(0, 1));
		grid.addToLivingCells(getLivingCellAtPosition(1, 1));
		grid.addToLivingCells(getLivingCellAtPosition(2, 1));
		return grid;

	}

	private Grid getBoatPattern() {
		Grid grid = new Grid();
		grid.addToLivingCells(getLivingCellAtPosition(0, 0));
		grid.addToLivingCells(getLivingCellAtPosition(0, 1));
		grid.addToLivingCells(getLivingCellAtPosition(1, 0));
		grid.addToLivingCells(getLivingCellAtPosition(1, 2));
		grid.addToLivingCells(getLivingCellAtPosition(2, 1));
		return grid;
	}

	protected Cell getLivingCellAtPosition(int x, int y) {
		Cell cell = Cell.getInsatnce(x, y).markAlive();
		return cell;
	}
}

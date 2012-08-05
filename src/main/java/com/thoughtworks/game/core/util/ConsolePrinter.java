package com.thoughtworks.game.core.util;

import java.util.Arrays;
import java.util.Set;

import com.thoughtworks.game.core.model.Cell;
import com.thoughtworks.game.core.model.Grid;
import com.thoughtworks.game.core.model.State;

/**
 * Implementation which prints the grid to the console.
 * 
 * @author shajahan
 * 
 */
public class ConsolePrinter implements IPrinter {
	/**
	 * Displays the output in standard output format.
	 * 
	 * @param grid
	 *            The grid to be displayed.
	 */
	public void printThegrid(Grid grid) {
		Set<Cell> livingCells = grid.getLivingCells();
		Cell[] cells = new Cell[livingCells.size()];
		livingCells.toArray(cells);
		Arrays.sort(cells);
		int leastRow = cells[0].getPositon().getXCoordinate();
		int maxRow = cells[cells.length - 1].getPositon().getXCoordinate();
		int maxColumn = 0;
		int minColumn = 0;
		for (Cell cell : cells) {
			if (cell.getPositon().getYCoordinate() < minColumn) {
				minColumn = cell.getPositon().getYCoordinate();
			} else if (cell.getPositon().getYCoordinate() > maxColumn) {
				maxColumn = cell.getPositon().getYCoordinate();
			}
		}
		int currentPritingRow = leastRow;
		int currentprintingColumn = minColumn;
		int count = 0;
		System.out.println("\n");
		while (isMoreLeftToPrint(maxRow, maxColumn, currentPritingRow,
				currentprintingColumn)) {
			if (hasPrintedAllLiveCells(cells, count)) {
				currentprintingColumn = fillWithDeadCellsIfRequired(maxColumn,
						currentprintingColumn);
				break;
			}
			if (isStartOfAnotherLine(cells, currentPritingRow, count)) {
				currentprintingColumn = fillWithDeadCellsIfRequired(maxColumn,
						currentprintingColumn);
				System.out.println("\n");
				currentPritingRow = currentPritingRow + 1;
				currentprintingColumn = minColumn;
			}
			while (isFillColumnsWithDeadCellsTillNextLiveCellCome(cells,
					currentprintingColumn, count)) {
				System.out.print(State.DEAD.toString());
				currentprintingColumn += 1;
			}
			System.out.print(State.ALIVE.toString());
			currentprintingColumn++;
			count++;
		}
	}

	/**
	 * Answers whether the columns need to be filled till the next Live cell.
	 * 
	 * @param cells
	 * @param currentprintingColumn
	 * @param count
	 * @return TRUE/FALSE
	 */
	private boolean isFillColumnsWithDeadCellsTillNextLiveCellCome(
			Cell[] cells, int currentprintingColumn, int count) {
		return cells[count].getPositon().getYCoordinate() > currentprintingColumn;
	}

	/**
	 * Is the current cell to be printed on next row ?
	 * 
	 * @param cells
	 * @param currentPritingRow
	 * @param count
	 * @return
	 */
	private boolean isStartOfAnotherLine(Cell[] cells, int currentPritingRow,
			int count) {
		return cells[count].getPositon().getXCoordinate() > currentPritingRow;
	}

	/**
	 * Answers whether all the live cells has been printed already and only
	 * filling dead cell is left to do.
	 * 
	 * @param cells
	 * @param count
	 * @return TREU/FALSE
	 */
	private boolean hasPrintedAllLiveCells(Cell[] cells, int count) {
		return count > cells.length - 1;
	}

	/**
	 * Answers whether there's more cells to be printed.
	 * 
	 * @param maxRow
	 * @param maxColumn
	 * @param currentPritingRow
	 * @param currentprintingColumn
	 * @return TRUE/FALSE
	 */
	private boolean isMoreLeftToPrint(int maxRow, int maxColumn,
			int currentPritingRow, int currentprintingColumn) {
		return currentPritingRow <= maxRow
				|| currentprintingColumn <= maxColumn;
	}

	/**
	 * Fills the console with Dead cells.
	 * 
	 * @param maxColumn
	 * @param currentprintingColumn
	 * @return current printing column
	 */
	private int fillWithDeadCellsIfRequired(int maxColumn,
			int currentprintingColumn) {
		while ((maxColumn - currentprintingColumn) >= 0) {
			System.out.print(State.DEAD.toString());
			currentprintingColumn++;
		}
		return currentprintingColumn;
	}

	@Override
	public void print(String string) {
		System.out.println(string);		
	}

}

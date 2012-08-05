package com.thoughtworks.game.core.model;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.thoughtworks.game.core.util.ConsolePrinter;
import com.thoughtworks.game.core.util.IPrinter;

/**
 * Game of life Object.
 * 
 * @author shajahan
 * 
 */
public class GameOfLife {

	/**
	 * Instance to hold game of life.
	 */
	private static GameOfLife instance;

	/**
	 * Cache to store the patterns we have already calculated. Returns the next
	 * generation after one tick if already found in cache, else calculate using
	 * the algorithm.
	 */
	private final LoadingCache<Grid, Grid> cache = CacheBuilder.newBuilder()
			.maximumSize(10).build(new CacheLoader<Grid, Grid>() {
				public Grid load(Grid inputGrid) {
					return getTheNextGenerationAfterOneTick(inputGrid);
				}
			});

	/**
	 * Plays the game given an input Grid. If the client doesn't want the grid
	 * to be displayed, getNextGenerationAfterOneTick would be better option.
	 * 
	 * @param grid
	 * @return
	 * @throws ExecutionException
	 */
	public Grid playGame(Grid grid) throws ExecutionException {
		IPrinter printer = getPrinter();
		printer.print("\n----Starting state of the life ---");
		printer.printThegrid(grid);
		printer.print("\n--- Executing one tick of life -- ");
		Grid nextState = cache.get(grid);
		printer.print("\n----Current state of the life ---");
		printer.printThegrid(nextState);
		printer.print("\n");
		return grid;
	}

	/**
	 * Gets the current next state of grid given the current state.
	 * 
	 * @param grid
	 * @return
	 */
	public Grid getTheNextGenerationAfterOneTick(Grid grid) {
		grid.executeGenerationTick();
		return grid;
	}

	/**
	 * Gets the printer instance
	 * 
	 * @return
	 */
	protected IPrinter getPrinter() {
		return new ConsolePrinter();
	}

	/**
	 * Private constructor.
	 */
	private GameOfLife() {

	}

	/**
	 * Static method to get the instance.
	 * 
	 * @return
	 */
	public static GameOfLife getInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}

	/**
	 * Can be used to initialize states required to start Game of life.
	 */
	private static synchronized void init() {
		instance = new GameOfLife();
	}

}

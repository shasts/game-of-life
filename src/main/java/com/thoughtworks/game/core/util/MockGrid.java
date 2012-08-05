package com.thoughtworks.game.core.util;

import com.thoughtworks.game.core.model.Grid;
/**
 * Mock grid to test the caching 
 * @author shajahan
 *
 */
public class MockGrid extends Grid {

	protected static int numberOfTimesAlgorithmExecuted = 0;

	@Override
	public void executeGenerationTick() {
		super.executeGenerationTick();
		numberOfTimesAlgorithmExecuted++;
	}

	
	public int getNumberOfTimeAlgorithmExecuted(){
		return numberOfTimesAlgorithmExecuted;
	}
}

package com.training.indianstatecensusanalyser;
import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusData.csv";

	@Test
	public void givenCensusFileShouldReturnCorrectNumberOfEnteries() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int entries= stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
		assertEquals(36, entries);
	}
}
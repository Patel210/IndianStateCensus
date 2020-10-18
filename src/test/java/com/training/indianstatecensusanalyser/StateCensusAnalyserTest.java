package com.training.indianstatecensusanalyser;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;
 
import opencsvbuilder.CSVException;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusData.csv";
	private static final String INCORRECT_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnaylser\\src\\main\\resources\\IndianStateCensusData.csv";
	private static final String INCORRECT_TYPE_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectType.txt";
	private static final String INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectDelimiter.csv";
	private static final String INCORRECT_HEADER_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectHeader.csv";
	
	private StateCensusAnalyser stateCensusAnalyser;	
	
	@Before
	public void setup() {
		stateCensusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CSVException.class);
	}
	
	 
	@Test
	public void givenCensusFileShouldReturnCorrectNumberOfEnteries() {
		try {
			int entries = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			assertEquals(36, entries);
		} catch (CSVException e) {
		}
	}

	@Test
	public void givenWrongFilePathShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCensusData(INCORRECT_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenWrongFileTypeWhenProcessedShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCensusData(INCORRECT_TYPE_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	@Test
	public void givenFileWithWrongDelimiterWhenProcessedShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCensusData(INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenFileWithWrongHeaderWhenProcessedShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
	
	@Test
	public void givenCensusFileWhenSortedStateWiseShouldReturnTheSortedJSONString() {
		try {
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			String stateWiseSortedString = stateCensusAnalyser.getStateWiseSortedData();
			CSVStateCensus[] censusArray = new Gson().fromJson(stateWiseSortedString, CSVStateCensus[].class);
			int size = censusArray.length;
			assertEquals("Andaman and Nicobar Islands", censusArray[0].getState());
			assertEquals("West Bengal", censusArray[size-1].getState());
		} 
		catch(CSVException | CensusAnalyserException e) {} 
		
	}
	
	@Test
	public void givenCensusFileWhenSortedStateCodeWiseShouldReturnTheSortedJSONString() {
		try {
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			String stateWiseSortedString = stateCensusAnalyser.getStateCodeWiseSortedData();
			CSVStateCensus[] censusArray = new Gson().fromJson(stateWiseSortedString, CSVStateCensus[].class);
			int size = censusArray.length;
			assertEquals("AN", censusArray[0].getCode());
			assertEquals("WB", censusArray[size-1].getCode());
		} catch (CSVException | CensusAnalyserException e) {}
	}
	
	@Test
	public void givenCensusFileWhenSortedWithoutLoadingShouldThrowAnException() {
		try {
		String stateWiseSortedString = stateCensusAnalyser.getStateCodeWiseSortedData();
		CSVStateCensus[] censusArray = new Gson().fromJson(stateWiseSortedString, CSVStateCensus[].class);
		} catch(CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.NO_DATA_FOUND, e.type);
		}
	}
	
	@Test
	public void givenCensusFileWhenLoadedAndSortedBasedOnPopulationInDecendingOrderShouldReturnTheSortedJSONString() {
		try {
			stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			String stateWiseSortedString = stateCensusAnalyser.getPopulationWiseSortedDataInDecendingOrder();
			CSVStateCensus[] censusArray = new Gson().fromJson(stateWiseSortedString, CSVStateCensus[].class);
			int size = censusArray.length;
			assertEquals("Uttar Pradesh", censusArray[0].getState());
			assertEquals("Lakshadweep", censusArray[size-1].getState());
		} catch (CSVException | CensusAnalyserException e) {}
		
	}
}
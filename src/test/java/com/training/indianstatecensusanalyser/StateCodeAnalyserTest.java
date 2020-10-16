package com.training.indianstatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.training.csvbuilder.CSVException;

public class StateCodeAnalyserTest {
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
	public void givenStateCensusFileWhenProcessedForStateCodeShouldReturnCorrectNumberOfEnteries() {
		try {
			int entries = stateCensusAnalyser.loadStateCodeData(STATE_CENSUS_FILE_PATH);
			assertEquals(36, entries);
		} catch (CSVException e) {
		}
	}
	
	@Test
	public void givenWrongCensusFilePathWhenProcessedForStateCodeShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCodeData(INCORRECT_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenWrongCensusFileTypeWhenProcessedForStateCodeShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCodeData(INCORRECT_TYPE_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_TYPE, e.type);
		}
	}
	
	@Test
	public void givenCensusFileWithWrongDelimiterWhenProcessedForStateCodeShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCodeData(INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenCensusFileWithWrongHeaderWhenProcessedForStateCodeShouldThrowAnException() {
		try {
			stateCensusAnalyser.loadStateCodeData(INCORRECT_HEADER_STATE_CENSUS_FILE_PATH);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
}

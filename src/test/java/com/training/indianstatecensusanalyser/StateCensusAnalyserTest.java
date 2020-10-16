package com.training.indianstatecensusanalyser;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusData.csv";
	private static final String INCORRECT_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnaylser\\src\\main\\resources\\IndianStateCensusData.csv";
	private static final String INCORRECT_TYPE_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectType.txt";
	private static final String INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectDelimiter.csv";
	private static final String INCORRECT_HEADER_STATE_CENSUS_FILE_PATH = "C:\\Users\\Pranav\\eclipse-workspace\\IndianStateCensusAnalyser\\src\\test\\resources\\IndianStateCensusDataWithIncorrectHeader.csv";

	@Test
	public void givenCensusFileShouldReturnCorrectNumberOfEnteries() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int entries = stateCensusAnalyser.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			assertEquals(36, entries);
		} catch (CensusAnalyserException e) {

		}
	}

	@Test
	public void givenWrongFilePathShouldThrowAnException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_STATE_CENSUS_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenWrongFileTypeWhenProcessedShouldThrowAnException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_TYPE_STATE_CENSUS_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	@Test
	public void givenFileWithWrongDelimiterWhenProcessedShouldThrowAnException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenFileWithWrongHeaderWhenProcessedShouldThrowAnException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
}
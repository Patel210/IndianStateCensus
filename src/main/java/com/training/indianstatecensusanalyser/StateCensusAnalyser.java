package com.training.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.training.indianstatecensusanalyser.CensusAnalyserException.ExceptionType;

public class StateCensusAnalyser {

	public int loadStateCensusData(String filePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<CSVStateCensus> iterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("File problem encountered", ExceptionType.FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String filePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<CSVStates> iterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStates.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("File problem encountered", ExceptionType.FILE_PROBLEM);
		}
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}
}

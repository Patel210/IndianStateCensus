package com.training.indianstatecensusanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.training.indianstatecensusanalyser.CensusAnalyserException.ExceptionType;

public class StateCensusAnalyser {

	public int loadStateCensusData(String filePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<CSVStateCensus> iterator = getCSVFileIterator(reader, CSVStateCensus.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("File problem encountered", ExceptionType.FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String filePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<CSVStates> iterator = getCSVFileIterator(reader, CSVStates.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("File problem encountered", ExceptionType.FILE_PROBLEM);
		}
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
			throws CensusAnalyserException {
		try {
			if (!((filePath.split("\\.")[1]).equals("csv"))) {
				throw new CensusAnalyserException("Incorrect file type", ExceptionType.INCORRECT_TYPE);
			}
			if (!isCorrectDelimiter(filePath)) {
				throw new CensusAnalyserException("File contains Invalid Delimiter", ExceptionType.INCORRECT_DELIMITER);
			}
			if (!isCorrectHeader(filePath)) {
				throw new CensusAnalyserException("Incorrect Header", ExceptionType.INCORRECT_HEADER);
			}
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
					                                                .withIgnoreLeadingWhiteSpace(true)
					                                                .build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException("Unable to parse", ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public boolean isCorrectHeader(String filePath) throws CensusAnalyserException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (!headerColumns[1].equals("State Name") || !headerColumns[2].equals("TIN")
					|| !headerColumns[3].equals("Population") || !headerColumns[4].equals("State Code"))
				return false;
		} catch (IOException e) {
			throw new CensusAnalyserException("File Problem Occured", ExceptionType.FILE_PROBLEM);
		}
		return true;
	}

	public boolean isCorrectDelimiter(String filePath) throws CensusAnalyserException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				return false;
			}
		} catch (IOException e) {
			throw new CensusAnalyserException("File Problem Occured", ExceptionType.FILE_PROBLEM);
		}
		return true;
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}
}

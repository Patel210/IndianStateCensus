package com.training.indianstatecensusanalyser;

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
			if(!(filePath.split("\\.")[1].equals("csv"))) {
				throw new CensusAnalyserException("Incorrect File Type", ExceptionType.INCORRECT_TYPE);
			}
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					                                          .withType(CSVStateCensus.class).withIgnoreEmptyLine(true).build();
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_PROBLEM);
		}
	}
}

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
			if (!(filePath.split("\\.")[1].equals("csv"))) {
				throw new CensusAnalyserException("Incorrect File Type", ExceptionType.INCORRECT_TYPE);
			}
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				throw new CensusAnalyserException("File contains Invalid Delimiter", ExceptionType.INCORRECT_DELIMITER);
			}
			if (!headerColumns[1].equals("State Name") || !headerColumns[2].equals("TIN")
					|| !headerColumns[3].equals("Population") || !headerColumns[4].equals("State Code")) {
				throw new CensusAnalyserException("Incorrect Header", ExceptionType.INCORRECT_HEADER);
			}
			bufferedReader.close();
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					                                          .withType(CSVStateCensus.class).withIgnoreEmptyLine(true).build();
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException("Unable to parse", ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException e) {
			throw new CensusAnalyserException("File problem encountered", ExceptionType.FILE_PROBLEM);
		}
	}
}

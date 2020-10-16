package com.training.csvbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {
	
	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath) throws CSVException {
		try {
			if (!((filePath.split("\\.")[1]).equals("csv"))) {
				throw new CSVException("Incorrect file type", CSVException.ExceptionType.INCORRECT_TYPE);
			}
			if (!isCorrectDelimiter(filePath)) {
				throw new CSVException("File contains Invalid Delimiter", CSVException.ExceptionType.INCORRECT_DELIMITER);
			}
			if (!isCorrectHeader(filePath)) {
				throw new CSVException("Incorrect Header", CSVException.ExceptionType.INCORRECT_HEADER);
			}
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
					                                                .withIgnoreLeadingWhiteSpace(true)
					                                                .build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVException("Unable to parse", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public boolean isCorrectHeader(String filePath) throws CSVException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (!headerColumns[1].equals("State Name") || !headerColumns[2].equals("TIN")
					|| !headerColumns[3].equals("Population") || !headerColumns[4].equals("State Code"))
				return false;
		} catch (IOException e) {
			throw new CSVException("File Problem Occured", CSVException.ExceptionType.FILE_PROBLEM);
		}
		return true;
	}

	public boolean isCorrectDelimiter(String filePath) throws CSVException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				return false;
			}
		} catch (IOException e) {
			throw new CSVException("File Problem Occured", CSVException.ExceptionType.FILE_PROBLEM);
		}
		return true;
	}
}

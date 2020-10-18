package com.training.indianstatecensusanalyser;

import java.io.IOException; 
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.CSVException;
import opencsvbuilder.ICSVBuilder;

public class StateCensusAnalyser {

	public int loadStateCensusData(String filePath) throws CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class, filePath);
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CSVException("File Problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String filePath) throws CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<CSVStates> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStates> stateCodeList = csvBuilder.getCSVFileList(reader, CSVStates.class, filePath);
			return stateCodeList.size();
		} catch (IOException e) {
			throw new CSVException("File problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}
}

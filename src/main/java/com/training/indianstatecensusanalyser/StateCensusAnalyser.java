package com.training.indianstatecensusanalyser;

import java.io.IOException;  
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.CSVException;
import opencsvbuilder.ICSVBuilder;

public class StateCensusAnalyser {

	public int loadStateCensusData(String filePath) throws CSVException {
		List<CSVStateCensus> stateCensusList = getList(filePath, CSVStateCensus.class);
		return stateCensusList.size();
	}

	public int loadStateCodeData(String filePath) throws CSVException {
		List<CSVStates> stateCensusList = getList(filePath, CSVStates.class);
		return stateCensusList.size();
	}
	 
	private <E> List<E> getList(String filePath, Class csvClass) throws CSVException{
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return (List<E>) csvBuilder.getCSVFileList(reader, csvClass, filePath);
		}catch (IOException e) {
			throw new CSVException("File Problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}

	public String getSortedDataStateWise(String filePath) throws CSVException {
		List<CSVStateCensus> stateCensusList = getList(filePath, CSVStateCensus.class);
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getState());
		stateCensusList.sort(comparator);
		Gson gson = new Gson();
		String sortedCensusJsonString = gson.toJson(stateCensusList);
		return sortedCensusJsonString;
	}
}

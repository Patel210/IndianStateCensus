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
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getState());
		List<CSVStateCensus> sortedCensusList = getSortedCensusList(filePath, CSVStateCensus.class, comparator);
		return getListAsJsonString(sortedCensusList);
	}
	
	private String getListAsJsonString(List list) {
		Gson gson = new Gson();
		String sortedCensusJsonString = gson.toJson(list);
		return sortedCensusJsonString;
	}
	
	public String getSortedDataStateCodeWise(String filePath) throws CSVException {
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getCode());
		List<CSVStateCensus> sortedCensusList = getSortedCensusList(filePath, CSVStateCensus.class, comparator);
		return getListAsJsonString(sortedCensusList);
	}
	
	private <E> List<E> getSortedCensusList(String filePath, Class csvClass, Comparator<E> comparator) throws CSVException{
		List<E> list = getList(filePath, csvClass);
		list.sort(comparator);
		return list;
	}
}

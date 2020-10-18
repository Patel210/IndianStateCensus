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
	
	List<CSVStateCensus> censusDataList = null;
	List<CSVStates> stateDataList =  null;
	
	public int loadStateCensusData(String filePath) throws CSVException {
		censusDataList = getCSVList(filePath, CSVStateCensus.class);
		return censusDataList.size();
	}

	public int loadStateCodeData(String filePath) throws CSVException {
		stateDataList = getCSVList(filePath, CSVStates.class);
		return stateDataList.size();
	}
	 
	private <E> List<E> getCSVList(String filePath, Class csvClass) throws CSVException{
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return (List<E>) csvBuilder.getCSVFileList(reader, csvClass, filePath);
		}catch (IOException e) {
			throw new CSVException("File Problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}

	public String getStateWiseSortedData() throws CensusAnalyserException {
		if(censusDataList == null || censusDataList.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found", CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getState());
		censusDataList.sort(comparator);
		return getListAsJsonString(censusDataList);
	}
	
	public String getStateCodeWiseSortedData() throws CensusAnalyserException {
		if(censusDataList == null || censusDataList.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found", CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getCode());
		censusDataList.sort(comparator);
		return getListAsJsonString(censusDataList);
	}
	
	private String getListAsJsonString(List list) {
		Gson gson = new Gson();
		String sortedCensusJsonString = gson.toJson(list);
		return sortedCensusJsonString;
	}
}

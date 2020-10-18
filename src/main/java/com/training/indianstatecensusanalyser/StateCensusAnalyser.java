package com.training.indianstatecensusanalyser;

import java.io.IOException;  
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
		new CensusDataSortingUtility().sortDataStateWise(censusDataList);
		CensusAnalyserJsonIOService jsonIOService = new CensusAnalyserJsonIOService();
		return jsonIOService.createJsonString(censusDataList);
	}
	
	public String getStateCodeWiseSortedData() throws CensusAnalyserException {
		new CensusDataSortingUtility().sortDataStateCodeWise(censusDataList);
		CensusAnalyserJsonIOService jsonIOService = new CensusAnalyserJsonIOService();
		return jsonIOService.createJsonString(censusDataList);
	}
	
	public String getPopulationWiseSortedDataInDecendingOrder() throws CensusAnalyserException {
		new CensusDataSortingUtility().sortDataPopulationWiseInDecendingOrder(censusDataList);
		CensusAnalyserJsonIOService jsonIOService = new CensusAnalyserJsonIOService();
		jsonIOService.writeJSONFile("PopulationWiseSortedData", censusDataList);
		return jsonIOService.createJsonString(censusDataList);
	}
	
	public String getPopulationDensityWiseSortedDataInDecendingOrder() throws CensusAnalyserException {
		new CensusDataSortingUtility().sortDataPopulationDensityWiseInDecendingOrder(censusDataList);
		CensusAnalyserJsonIOService jsonIOService = new CensusAnalyserJsonIOService();
		jsonIOService.writeJSONFile("PopulationDensityWiseSortedData", censusDataList);
		return jsonIOService.createJsonString(censusDataList);
	}
}

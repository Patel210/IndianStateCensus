package com.training.indianstatecensusanalyser;

import java.util.Comparator;
import java.util.List;

public class CensusDataSortingUtility {

	public void sortDataStateWise(List<CSVStateCensus> list) throws CensusAnalyserException {
		if (list == null || list.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found",
					                          CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getState());
		list.sort(comparator);
	}

	public void  sortDataStateCodeWise(List<CSVStateCensus> list) throws CensusAnalyserException {
		if (list == null || list.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found",
					                          CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getCode());
		list.sort(comparator);
	}

	public void  sortDataPopulationWiseInDecendingOrder(List<CSVStateCensus> list) throws CensusAnalyserException {
		if (list == null || list.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found",
					                          CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStateCensus -> csvStateCensus.getPopulation());
		list.sort(comparator.reversed());
	}

	public void  sortDataPopulationDensityWiseInDecendingOrder(List<CSVStateCensus> list)
			throws CensusAnalyserException {
		if (list == null || list.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found",
											  CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator
				                                .comparing(csvStateCensus -> csvStateCensus.getPopulationDensity());
		list.sort(comparator.reversed());
	}

	public void sortDataAreaWiseInDecendingOrder(List<CSVStateCensus> list) throws CensusAnalyserException {
		if (list == null || list.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found",
											  CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<CSVStateCensus> comparator = Comparator
				                                .comparing(csvStateCensus -> csvStateCensus.getAreaInSqKms());
		list.sort(comparator.reversed());
	}
}

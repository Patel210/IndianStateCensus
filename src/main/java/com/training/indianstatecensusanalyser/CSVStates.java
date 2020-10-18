package com.training.indianstatecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName(column = "State Name", required = true)
	private String state;

	@CsvBindByName(column = "State Code", required = true)
	private String stateCode;

}

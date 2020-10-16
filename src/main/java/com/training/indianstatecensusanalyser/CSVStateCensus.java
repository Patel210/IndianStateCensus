package com.training.indianstatecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

	@CsvBindByName(column = "State Name", required = true)
	private String state;
	@CsvBindByName(column = "State Code", required = true)
	private String code;
	@CsvBindByName(column = "Population", required = true)
	private int population;
	@CsvBindByName(column = "TIN", required = true)
	private int tinNumber;

	@Override
	public String toString() {
		return "CSVStateCensus [state=" + state + ", code=" + code + ", population=" + population + ", tinNumber="
				+ tinNumber + "]";
	}
}

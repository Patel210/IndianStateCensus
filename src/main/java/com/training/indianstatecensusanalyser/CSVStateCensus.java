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
	@CsvBindByName(column = "Area (sq km)", required = true)
	private int areaInSqKms;
	@CsvBindByName(column = "Population Density", required = true)
	private int populationDensity;
	
	public int getPopulation() {
		return population;
	}

	public int getTinNumber() {
		return tinNumber;
	}

	public int getAreaInSqKms() {
		return areaInSqKms;
	}

	public int getPopulationDensity() {
		return populationDensity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

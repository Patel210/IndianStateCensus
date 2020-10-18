package com.training.indianstatecensusanalyser;

public class CensusAnalyserException extends Exception {
	
	ExceptionType type;

	enum ExceptionType {
		NO_DATA_FOUND;
	}

	public CensusAnalyserException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}

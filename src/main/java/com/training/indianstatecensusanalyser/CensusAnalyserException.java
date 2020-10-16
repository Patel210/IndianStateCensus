package com.training.indianstatecensusanalyser;

public class CensusAnalyserException extends Exception {
	ExceptionType type;

	enum ExceptionType {
		FILE_PROBLEM, UNABLE_TO_PARSE, INCORRECT_TYPE;
	}

	public CensusAnalyserException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}

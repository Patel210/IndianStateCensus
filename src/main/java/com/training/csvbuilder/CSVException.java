package com.training.csvbuilder;

public class CSVException extends Exception{
	public ExceptionType type;

	public enum ExceptionType {
		FILE_PROBLEM, UNABLE_TO_PARSE, INCORRECT_TYPE, INCORRECT_DELIMITER, INCORRECT_HEADER;
	}
	
	public CSVException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}

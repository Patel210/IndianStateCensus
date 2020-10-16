package com.training.indianstatecensusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
			throws CensusAnalyserException;
}

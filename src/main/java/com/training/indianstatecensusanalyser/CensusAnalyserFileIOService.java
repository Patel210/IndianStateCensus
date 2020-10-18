package com.training.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CensusAnalyserFileIOService {

	public void writeJSONFile(String fileName, String jsonString) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(fileName + ".json"));) {
			writer.write(jsonString);
		} catch (IOException e) {}
	}
}

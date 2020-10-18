package com.training.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

public class CensusAnalyserJsonIOService {

	public <E> String createJsonString(List<E> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public <E> void writeJSONFile(String fileName, List<E> list) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(fileName + ".json"));) {
			String jsonString = createJsonString(list);
			writer.write(jsonString);
		} catch (IOException e) {}
	}

}

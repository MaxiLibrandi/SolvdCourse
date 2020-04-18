package com.solvd.countWordsOcurrence;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RunnerCountWordsOcurrence {
	private static final Logger LOGGER = LogManager.getLogger(RunnerCountWordsOcurrence.class);

	public static void main (String[] args) {
		
		HashMap<String,Integer> ocurrences = new HashMap<String, Integer>();
		
		String text = null;
		
		try {
			text = FileUtils.readFileToString(new File("src/main/resources/text.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		
		for (String word : StringUtils.split(text,' ')) {			
			ocurrences.put(word = StringUtils.upperCase(word),(ocurrences.containsKey(word)) ? ocurrences.get(word) + 1 : 1);
		}
		
		try {
			FileUtils.writeStringToFile(new File("src/main/resources/countWordsOcurrence.txt"), ocurrences.toString(),StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
}

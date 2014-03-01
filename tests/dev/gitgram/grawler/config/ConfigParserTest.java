package dev.gitgram.grawler.config;


import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.json.simple.JSONObject;
import org.junit.Test;

public class ConfigParserTest {

	@Test
	public void testParse() throws Exception {
		ConfigParser parserTest = new ConfigParser();
		URL url = getClass().getResource("testJson.json");
		String fileName = url.getPath();
		JSONObject testResult = parserTest.parse(fileName);
		assertNotNull("Oops!", testResult);
	}

}

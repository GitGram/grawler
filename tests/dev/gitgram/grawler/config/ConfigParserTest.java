package dev.gitgram.grawler.config;


import static org.junit.Assert.assertEquals;

import java.net.URL;

import main.java.dev.gitgram.grawler.config.ConfigParser;

import org.json.simple.JSONObject;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ConfigParserTest {

	@Test
	public void testParse() throws Exception {
		ConfigParser parserTest = new ConfigParser();
		URL url = getClass().getResource("testJson.json");
		String fileName = url.getPath();
		JSONObject testResult = parserTest.parse(fileName);
		JSONObject expectedResult = createExpectedResult();
		assertEquals("Parsed result should be same as expected result", expectedResult, testResult);
	}

	private JSONObject createExpectedResult() {
		JSONObject saveObject = new JSONObject();
		JSONObject saveObject1 = new JSONObject();
		JSONObject userObject = new JSONObject();
		JSONObject allUserObject = new JSONObject();
		JSONObject resultObject = new JSONObject();
		
		saveObject.put("save", "true");
		userObject.put("user", saveObject);
		resultObject.put("allRepos", saveObject);
		
		saveObject1.put("save", "10");
		allUserObject.put("user", saveObject);
		allUserObject.put("collaborators", saveObject1);
		allUserObject.put("save", "true");
		
		resultObject.put("allUsers", allUserObject);
		
		return resultObject;
	}

}

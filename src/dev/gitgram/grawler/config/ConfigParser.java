package dev.gitgram.grawler.config;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Config.json Parser Class
 * @author r4chi7
 *
*/


public class ConfigParser {
	JSONParser jsonParser;
	JSONObject jsonObject;
	JSONArray jsonArray;
	
	public ConfigParser() {
		jsonParser = new JSONParser();
	}
	
	public JSONObject parse(String fileName) {
		JSONObject parseResult = new JSONObject();
		try {
			
			FileReader file = new FileReader(fileName);
			Object parserOutput = jsonParser.parse(file);
			parseResult = (JSONObject) parserOutput;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parseResult;
	}
	
}

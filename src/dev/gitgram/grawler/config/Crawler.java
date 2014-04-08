package dev.gitgram.grawler.config;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cascading.scheme.Scheme;
import cascading.scheme.hadoop.TextLine;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tuple.Fields;
public class Crawler {

	private final String USER_AGENT = "Mozilla/5.0";	

	
	public static void main(String[] args) throws Exception {

		Crawler http = new Crawler();
		System.out.println("Testing 1 - Send Http GET request");
		http.getUsers();
	}
	
	// HTTP GET request
	public StringBuffer sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		response.append("{\"values\":");
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		response.append("}");
		in.close();
		return response;
	}
	
	public void getUsers() throws Exception {
		int since = 0;
		String url;
		while(true) {
			url = "https://api.github.com/users?since=" + since + "&client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
			StringBuffer response = sendGet(url);
			JSONObject json = new JSONObject(response.toString());
			JSONArray values = json.getJSONArray("values");
			JSONObject value = null;
			if (values.length() == 0) {
				break;
			}
			for (int i = 0; i < values.length(); i++) {
				value = values.getJSONObject(i);
				System.out.println(value.toString());
			}
			since = value.getInt("id");
		}
//		FileWriter file = new FileWriter("test.json");
//		file.write(response.toString());
//		file.flush();
//		file.close();
	}
	
}

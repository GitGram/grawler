package main.java.dev.gitgram.grawler.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Crawler {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		Crawler http = new Crawler();
		http.getUsers();
		// Scheme sourceScheme = new TextLine(new Fields("line", "value"));
		// Tap input = new Hfs(sourceScheme, "test.json");
		http.saveUsersInfo();
	}

	// HTTP GET request
	public StringBuffer sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
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
		FileWriter file = new FileWriter("data/all_users.json");
		file.write("[");
		url = "https://api.github.com/users?since=0&client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
		StringBuffer response = sendGet(url);
		JSONObject json = new JSONObject(response.toString());
		JSONArray values = json.getJSONArray("values");
		JSONObject value = values.getJSONObject(0);
		file.append(value.toString());
		while (true) {
			url = "https://api.github.com/users?since="
					+ since
					+ "&client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
			response = sendGet(url);
			json = new JSONObject(response.toString());
			values = json.getJSONArray("values");
			value = null;
			if (values.length() == 0) {
				break;
			}
			for (int i = 0; i < values.length(); i++) {
				file.append(",");
				value = values.getJSONObject(i);
				System.out.println(value.toString());
				file.append(value.toString());
			}
			since = value.getInt("id");
		}

		file.append("]");
		file.flush();
		file.close();

	}

	public void saveUsersInfo() {
		try {
			FileReader file = new FileReader("data/all_users.json");
			BufferedReader reader = new BufferedReader(file);
			String data = reader.readLine();
			JSONArray jsonArray = new JSONArray(data);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String username = object.getString("login");
				FileWriter jsonFile = new FileWriter("data/" + username + ".json");

				getFollowers(jsonFile, object.getString("followers_url"));
				getRepos(jsonFile, object.getString("repos_url"));
				getOrganizations(jsonFile, object.getString("organizations_url"));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void getFollowers(FileWriter file, String url) {
		System.out.print("----- GET FOLLOWERS -----");
		StringBuffer response = null;
		try {
			file.append("\"followers\":");
			url += "?client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
			response = sendGet(url);
			if (!(response.toString() == "{\"values\":[]}")) {
				System.out.println(response.toString());
				file.append(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getRepos(FileWriter file, String url) {
		System.out.print("----- GET REPOS -----");
		StringBuffer response = null;
		try {
			file.append("\"repos\":");
			url += "?client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
			response = sendGet(url);
			if (!(response.toString() == "{\"values\":[]}")) {
				System.out.println(response.toString());
				file.append(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getOrganizations(FileWriter file, String url) {
		System.out.print("----- GET ORGANIZATIONS -----");
		StringBuffer response = null;
		try {
			file.append("\"organizations\":");
			url += "?client_id=558758e6355571fbb7d5&client_secret=5bc9e551b97ecdee77f93451d50e0f536eb09a8c";
			response = sendGet(url);
			if (!(response.toString() == "{\"values\":[]}")) {
				System.out.println(response.toString());
				file.append(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

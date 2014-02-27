package dev.gitgram.grawler.config;

public class Config {
	
	private static Config config;
	
	// Private constructor to enable Singleton behavior
	private Config() {
		
	}
	
	public Config configInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}
}

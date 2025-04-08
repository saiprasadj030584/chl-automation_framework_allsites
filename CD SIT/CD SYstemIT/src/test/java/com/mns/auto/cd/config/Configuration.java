package com.mns.auto.cd.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.Inject;
import com.mns.auto.cd.exception.ConfigException;

public class Configuration {

	private static Map<String, Object> config = new HashMap<String, Object>();

	public static String CONFIG_FILE;
	public static String CONFIG;
	

	@Inject
	public Configuration() {
		loadConfigurations();
		loadProperties();

	}
	
	
	@SuppressWarnings("unchecked")
	private void loadConfigurations() {
		
		final String TEST_ENVIRONMENT = System.getProperty("ENV");


		if (TEST_ENVIRONMENT == null || TEST_ENVIRONMENT.equals("0")) {
			CONFIG_FILE = "./config/config_ST.yml";
			CONFIG = System.getProperty("config_ST", "default");
		} else if (TEST_ENVIRONMENT.equals("ST")) {
			CONFIG_FILE = "./config/config_ST.yml";
			CONFIG = System.getProperty("config_ST", "default");
		} else if (TEST_ENVIRONMENT.equals("SIT")) {
			CONFIG_FILE = "./config/config_SIT.yml";
			CONFIG = System.getProperty("config_SIT", "default");
		}
		
		File configFile = new File(CONFIG_FILE);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(configFile);
			Yaml yaml = new Yaml();
			Map<?, ?> configData = (Map<?, ?>) yaml.load(inputStream);
			if (configData.get(CONFIG) == null) {
				throw new ConfigException("Config - '" + CONFIG + "' is not present in the config.yml file.");
			}
			for (Map.Entry<String, Object> entry : ((Map<String, Object>) configData.get(CONFIG))
					.entrySet()) {
				this.config.put(entry.getKey().toString(), entry.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void loadProperties() {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("target/project.properties");
			properties.load(inputStream);
			Set<Object> propertyKeys = properties.keySet();
			for (Object propertyKey : propertyKeys) {
				this.config.put((String) propertyKey, properties.getProperty((String) propertyKey));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean hasProperty(String key) {
		return config.containsKey(key);
	}

	public String getStringProperty(String key) {
		if (!(hasProperty(key))) {
			return null;
		}
		return ((String) config.get(key));
	}
	

}

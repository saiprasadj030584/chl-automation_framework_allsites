package com.jda.wms.config;

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
import com.jda.wms.exception.ConfigException;

public class Configuration1 {

	private Map<String, Object> config = new HashMap<String, Object>();

	@Inject
	public Configuration1() {
		loadConfigurations();
		loadProperties();
	}

	@SuppressWarnings("unchecked")
	private void loadConfigurations() {
		File configFile = new File(Constants.CONFIG_FILE1);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(configFile);
			Yaml yaml = new Yaml();
			Map<?, ?> configData = (Map<?, ?>) yaml.load(inputStream);
			if (configData.get(Constants.CONFIG1) == null) {
				throw new ConfigException("Config - '" + Constants.CONFIG1 + "' is not present in the config.yml file.");
			}
			for (Map.Entry<String, Object> entry : ((Map<String, Object>) configData.get(Constants.CONFIG1))
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

	public boolean hasProperty(String key) {
		return this.config.containsKey(key);
	}

	public String getStringProperty(String key) {
		if (!(hasProperty(key))) {
			return null;
		}
		return ((String) this.config.get(key));
	}
}

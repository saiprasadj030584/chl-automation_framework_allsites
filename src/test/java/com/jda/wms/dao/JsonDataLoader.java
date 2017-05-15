package com.jda.wms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jda.wms.exception.DataException;

public class JsonDataLoader {
	private final Gson gson = new GsonBuilder().create();

	private FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
		return new FileInputStream(new File(filePath).getAbsolutePath());
	}

	private InputStream getInputStream(String resource) {
		return JsonDataLoader.class.getResourceAsStream(resource);
	}

	/**
	 * Generic method to load data from static json in case of mock data.
	 * 
	 * @param dataPath
	 * @param genericType
	 * @return
	 * @throws DataException
	 */
	public <T> T getData(String dataPath, Class<T> genericType) throws DataException {
		Reader reader = null;
		try {
			URL url = JsonDataLoader.class.getResource(dataPath);
			InputStream inputStream = url == null ? getFileInputStream(dataPath) : getInputStream(dataPath);
			reader = new InputStreamReader(inputStream);
			return gson.fromJson(reader, genericType);
		} catch (IOException e) {
			throw new DataException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}
}

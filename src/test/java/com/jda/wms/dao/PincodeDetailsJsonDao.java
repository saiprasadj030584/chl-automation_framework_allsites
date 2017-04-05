package com.jda.wms.dao;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.exception.DataException;
import com.jda.wms.model.Pincode;

public class PincodeDetailsJsonDao {
	private static String PINCODE_PATH = "./src/test/resources/data/pincode.json";

	@Inject
	private JsonDataLoader jsonDataLoader;

	public List<Pincode> getPincodeDetails() throws DataException {
		return Arrays.asList(jsonDataLoader.getData(PINCODE_PATH, Pincode[].class));
	}

}

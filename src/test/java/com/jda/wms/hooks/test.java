package com.jda.wms.hooks;

import java.util.ArrayList;

import com.jda.wms.dao.GetDataFromJson;
import com.jda.wms.dataObject.CheckString;
import com.jda.wms.exception.DataException;

public class test {

	public static void main(String[] args) throws DataException {
		ArrayList<String> cs = new ArrayList<String>();
		CheckString checkString = new CheckString();
		
		GetDataFromJson getDataFromJson = new GetDataFromJson(null); 
		cs = getDataFromJson.getCheckString();
//		cs = checkString.getCheckString();
		System.out.println(cs.size());
		// System.out.println(cs.get(1));
	}

}

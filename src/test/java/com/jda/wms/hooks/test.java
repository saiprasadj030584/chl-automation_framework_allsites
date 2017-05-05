package com.jda.wms.hooks;

import java.util.ArrayList;
import java.util.List;

import com.jda.wms.dao.GetDataFromJson;
import com.jda.wms.dataObject.CheckString;

public class test {

	public static void main(String[] args) {
		List<String> cs = new ArrayList<String>();
		CheckString checkString = new CheckString();
		GetDataFromJson getDataFromJson = new GetDataFromJson(null); 
		
		cs = checkString.getCheckString();
		System.out.println(cs.size());
		// System.out.println(cs.get(1));
	}

}

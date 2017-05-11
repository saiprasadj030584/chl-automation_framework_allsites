package com.jda.wms.hooks;

import java.io.File;
//import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jda.wms.context.Context;
import com.jda.wms.context.Staff;
import com.jda.wms.dataObject.CheckString;

public class test {

	public static void main(String[] args) throws IOException  {

		/*CheckStringDetailsJsonDao checkStringDetailsJsonDao = new CheckStringDetailsJsonDao();
		List<String> cs = checkStringDetailsJsonDao.getCheckStringDetails().get(0).getCheckString();
			System.out.println(cs.size());*/ 
		
		ObjectMapper mapper = new ObjectMapper();
		CheckString cs = mapper.readValue(new File("./src/test/resources/data/check_string.json"), CheckString.class);
		System.out.println(cs);
		
		/*ObjectMapper mapper1 = new ObjectMapper();
		String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
		Staff staff1 = mapper1.readValue(jsonInString, Staff.class);
		System.out.println(staff1);*/

		
	}

}

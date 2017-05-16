package com.jda.wms.db;
import java.sql.*;  

public class Database3 {  
	public void connect(String connurl ){
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(connurl);
			
			Statement statement = connection.createStatement();
			
			  System.out.println("Connection successful");
			//step4 execute query  
			ResultSet rs=statement.executeQuery("select * from AB_ADDRESS");  
			while(rs.next())  
			System.out.println(rs.getInt(1));  
			  
			//step5 close the connection object  
			connection.close();  
			  
			}catch(Exception e){ System.out.println(e);}  
			  System.out.println("Exception");
			}  
	}



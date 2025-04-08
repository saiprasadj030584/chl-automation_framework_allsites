package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ClusteringDB {

	private Context context;
	private Database database;

	@Inject
	public ClusteringDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	} 

	public boolean isInClusterConfigurationDataTable(String clusterConfigID)
			throws SQLException, ClassNotFoundException {

		boolean isRecord = true;
		try {

			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println(
					"select * from cluster_configuration_data where cluster_config_id ='" + clusterConfigID + "'");
			ResultSet rs = stmt.executeQuery(
					"select * from cluster_configuration_data where cluster_config_id ='" + clusterConfigID + "'");
			isRecord = rs.next();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return isRecord;
	}
	public boolean isInClusterConfigTable(String clusterConfigID) throws SQLException, ClassNotFoundException {

		boolean isRecord = true;
		try{

			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("select * from cluster_configuration where cluster_config_id ='" + clusterConfigID + "'");
			ResultSet rs = stmt.executeQuery(
					"select * from cluster_configuration where cluster_config_id ='" + clusterConfigID + "'");
			isRecord = rs.next();

		}catch(Exception e){

			e.printStackTrace();
		}
		return isRecord;
	}
	public boolean isInClusterGroupTable(String clusterGroupID) throws SQLException, ClassNotFoundException {
		boolean isRecord = true;
		try{
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("select cluster_group_id from CLUSTER_GROUP");
			ResultSet rs = stmt.executeQuery(
					"select cluster_group_id from CLUSTER_GROUP");
			isRecord = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return isRecord;
	}
	public boolean isInMovetaskTable(String TagID) throws SQLException, ClassNotFoundException {
		boolean isRecord = true;
		try{
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("select tag_id from move_task");
			ResultSet rs = stmt.executeQuery(
					"select tag_id from move_task where tag_id ='" + TagID + "'");
			isRecord = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return isRecord;
	}
}
package com.jda.wms.context;

import java.util.ArrayList;

import cucumber.api.DataTable;

public class OrderHeaderContext {
	private String customerID;
	private String deliverByDate;
	private String orderDate;
	private String orderType;
	private String shipByDate;
	private String userDefDate1;
	private String orderNumber;
	private String[][] headerData;
	private int currentRecord = 0;
	private int numberOfLines;
	private ArrayList<String> failureList;
	private DataTable orderIDDataTable;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getDeliverByDate() {
		return deliverByDate;
	}

	public void setDeliverByDate(String deliverByDate) {
		this.deliverByDate = deliverByDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String OrderType) {
		this.orderType = OrderType;
	}

	public String getShipByDate() {
		return shipByDate;
	}

	public void setShipByDate(String shipByDate) {
		this.shipByDate = shipByDate;
	}

	public String getUserDefDate1() {
		return userDefDate1;
	}

	public void setUserDefDate1(String userDefDate1) {
		this.userDefDate1 = userDefDate1;
	}

	public String[][] getHeaderData() {
		return headerData;
	}

	public void setHeaderData(String[][] headerData) {
		this.headerData = headerData;
	}

	public int getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public ArrayList<String> getFailureList() {
		return failureList;
	}

	public void setFailureList(ArrayList<String> failureList) {
		this.failureList = failureList;
	}

	public DataTable getOrderIDDataTable() {
		return orderIDDataTable;
	}

	public void setOrderIDDataTable(DataTable orderIDDataTable) {
		this.orderIDDataTable = orderIDDataTable;
	}
}

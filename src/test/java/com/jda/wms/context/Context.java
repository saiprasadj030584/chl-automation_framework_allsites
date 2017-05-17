package com.jda.wms.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jda.wms.pages.foods.RDTTask;

public class Context {
	private RDTTask currentTask;
	private String preAdviceId;
	private String skuId;
	private String productGroup;
	private String cewarehousetype;
	private String allocationGroup;
	private String ean;
	private String newAbv;
	private String tagId;
	private String status;
	private int qtyOnHandBfrAdjustment;
	private int caseRatio;
	private String code;
	private String adjustmentType;
	private String supplierID;
	private String expiryDate;
	private int noOfLines;
	private Map<String, Map<String, String>> purchaseOrderMap;
	private Map<String, ArrayList<String>> tagIDMap;
	private int lineItem = 1;
	private int rcvQtyDue;
	private String location;
	private int tagIdIndex = 0;
	private String locationZone;
	private Map<String, String> locationPerTagMap;
	private List<String> checkStrings;
	private Map<String, Integer> qtyReceivedPerTagMap;
	private String name;
	private String country;
	private String address1;
	private int qtyReceivedPerTag;
	private String ceWarehouseTax;
	private String consignmentID;
	private String productCategory;
	private String lockCode;
	private String vintage;
	private String abv;
	private int qtyReceivedfromPutty;
	private String locationLockStatus;
	private String locationID;
	private String palletType;
	private Map<String, Map<String, String>> stockTransferOrderMap;
	private int qtyOnHand;
	private String faceType;
	private String siteId;
	private String orderId;
	private String shipDock;
	private String newShipDock;
	private String customer;
	private Connection connection = null;

	private int qtyReverse;

	public RDTTask getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(RDTTask currentTask) {
		this.currentTask = currentTask;
	}

	public String getABV() {
		return newAbv;
	}

	public void setABV(String newAbv) {
		this.newAbv = newAbv;
	}

	public String getlocationID() {
		return locationID;
	}

	public void setlocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getlocationLockStatus() {
		return locationLockStatus;
	}

	public void setlocationLockStatus(String locationLockStatus) {
		this.locationLockStatus = locationLockStatus;
	}

	public String getPreAdviceId() {
		return preAdviceId;
	}

	public void setPreAdviceId(String preAdviceId) {
		this.preAdviceId = preAdviceId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getEAN() {
		return ean;
	}

	public void setEAN(String ean) {
		this.ean = ean;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getAllocationGroup() {
		return allocationGroup;
	}

	public void setAllocationGroup(String allocationGroup) {
		this.allocationGroup = allocationGroup;
	}

	public void setCEWarehouseType(String cewarehousetype) {
		this.cewarehousetype = cewarehousetype;
	}

	public String getCEWarehouseType() {
		return cewarehousetype;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setqtyOnHandBeforeAdjustment(int qtyOnHandBfrAdjustment) {
		this.qtyOnHandBfrAdjustment = qtyOnHandBfrAdjustment;
	}

	public int getQtyOnHandBeforeAdjustment() {
		return qtyOnHandBfrAdjustment;
	}

	public void setCaseRatio(int caseRatio) {
		this.caseRatio = caseRatio;
	}

	public int getCaseRatio() {
		return caseRatio;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getFutureExpiryDate() {
		return expiryDate;
	}

	public void setFutureExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getNoOfLines() {
		return noOfLines;
	}

	public void setNoOfLines(int noOfLines) {
		this.noOfLines = noOfLines;
	}

	public int getLineItem() {
		return lineItem;
	}

	public void setLineItem(int lineItem) {
		this.lineItem = lineItem;
	}

	public int getRcvQtyDue() {
		return rcvQtyDue;
	}

	public void setRcvQtyDue(int rcvQtyDue) {
		this.rcvQtyDue = rcvQtyDue;
	}

	public Map<String, ArrayList<String>> getTagIDMap() {
		return tagIDMap;
	}

	public void setTagIDMap(Map<String, ArrayList<String>> tagIDMap) {
		this.tagIDMap = tagIDMap;
	}

	public int getTagIdIndex() {
		return tagIdIndex;
	}

	public void setTagIdIndex(int tagIdIndex) {
		this.tagIdIndex = tagIdIndex;
	}

	public String getLocationZone() {
		return locationZone;
	}

	public void setLocationZone(String locationZone) {
		this.locationZone = locationZone;
	}

	public Map<String, String> getLocationPerTagMap() {
		return locationPerTagMap;
	}

	public void setLocationPerTagMap(Map<String, String> locationPerTagMap) {
		this.locationPerTagMap = locationPerTagMap;
	}

	public Map<String, Integer> getQtyReceivedPerTagMap() {
		return qtyReceivedPerTagMap;
	}

	public void setQtyReceivedPerTagMap(Map<String, Integer> qtyReceivedPerTagMap) {
		this.qtyReceivedPerTagMap = qtyReceivedPerTagMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public int getQtyReceivedPerTag() {
		return qtyReceivedPerTag;
	}

	public void setQtyReceivedPerTag(int qtyReceivedPerTag) {
		this.qtyReceivedPerTag = qtyReceivedPerTag;
	}

	public String getCeWarehouseTax() {
		return ceWarehouseTax;
	}

	public void setCEWarehouseTax(String ceWarehouseTax) {
		this.ceWarehouseTax = ceWarehouseTax;
	}

	public String getConsignmentID() {
		return consignmentID;
	}

	public void setConsignmentID(String consignmentID) {
		this.consignmentID = consignmentID;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getPalletType() {
		return palletType;
	}

	public void setPalletType(String palletType) {
		this.palletType = palletType;

	}

	public String getLockCode() {
		return lockCode;
	}

	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}

	public Map<String, Map<String, String>> getPurchaseOrderMap() {
		return purchaseOrderMap;
	}

	public void setPurchaseOrderMap(Map<String, Map<String, String>> purchaseOrderMap) {
		this.purchaseOrderMap = purchaseOrderMap;
	}

	public String getVintage() {
		return vintage;
	}

	public void setVintage(String vintage) {
		this.vintage = vintage;
	}

	public Map<String, Map<String, String>> getstockTransferOrderMap() {
		return stockTransferOrderMap;
	}

	public void setstockTransferOrderMap(Map<String, Map<String, String>> stockTransferOrderMap) {
		this.stockTransferOrderMap = stockTransferOrderMap;

	}

	public void setQtyOnHand(int qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	public int getQtyOnHand() {
		return qtyOnHand;
	}

	public String getFaceType() {
		return faceType;
	}

	public void setFaceType(String faceType) {
		this.faceType = faceType;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getQtyReverse() {
		return qtyReverse;
	}

	public void setQtyReverse(int qtyReverse) {
		this.qtyReverse = qtyReverse;

	}


	public void setOrderNo(String orderId) {
		this.orderId = orderId;
	}
	public String getorderId() {
		return orderId;
	}

	public String getShipDock() {
		return shipDock;
	}

	public void setShipDock(String shipDock) {
		this.shipDock = shipDock;
	}

	public String getNewShipDock() {
		return newShipDock;
	}

	public void setNewShipDock(String newShipDock) {
		this.newShipDock = newShipDock;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

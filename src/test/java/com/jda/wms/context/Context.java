package com.jda.wms.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jda.wms.pages.Exit.RDTTask;

public class Context {
	private static String upiId = null;
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
	private List<String> caseRatiolist;
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
	private int qtyReceivedFromPutty;
	private String locationLockStatus;
	private String locationID;
	private String palletType;
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	private int qtyOnHand;
	private int qtyOnHand_zone;
	private String faceType;
	private String siteId;
	private ArrayList<String> failureList;
	private String orderId;
	private String customer = null;
	private String listId;
	private String toPallet;
	private String toLocation;
	private String finalLocation;
	private int qtyToMove;
	private String shipDock;
	private String newShipDock;
	private String trailerNo;
	private String dockSchedulerBookingID;
	private String orderStatus;
	private String consignment;
	private String errorMessage;
	private int pickedRecords;
	private String containerId;
	private static Connection connection = null;
	private String abvPercentage;
	private ArrayList<String> palletIDList;
	private Integer recordCountByTaskID;
	private String palletID;
	private int moveTaskRecordCount;
	private int qtyOrdered;
	private Map<Integer, Map<String, String>> listIDMap;
	private Map<String, String> pickFaceMap;
	private String taskId;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;
	private int qtyReverse;
	private boolean puttyLoginFlag = false;
	private boolean loginPage = false;
	private String addressID;
	private String packConfigID;
	private String dockSchedulerNotes;
	private String[] dockSchedulerBookingIDList;
	private String stoType;
	private Map<String, Map<Integer, Map<String, String>>> multipleOrderListIDMap;
	private Process puttyProcess;
	private String pickingType;
	private String triggerQty;
	private String workZone;
	private String task;
	private int QtyOnHandTag;
	private int QtyWithCaseRatio;
	private String LocationList;
	private String Quantity;
	private String RecordForPallet;
	private String qtyToMovePck;
	public static Connection connectionSQLDB = null;
	private static String parentRequestId;
	private static String childStartTime;

	private static String url;
	private static String puttyHost;
	private static String puttyPort;
	private static String appUsername;
	private static String appPassword;
	private static String dBHost;
	private static String dBUsername;
	private static String dBPassword;
	private String pickFaceTime;
	private String Key;
	
	private String qtyToMove2;
	private String tag;
	private String receiptId;
	public String poId;
	private String generateBelCode;
	private String skuid;
	private String belCode;
	private String SAPvalue;
	private String palletIDforUPI;
	private String POType;
	private String ASN;
	public String getSkuId2;
	private String packWeight;
	private String strokeCategory;
	private String commodityCode;
	private String ProductGroup1;

	public void setParentRequestId(String parentRequestId) {
		this.parentRequestId = parentRequestId;
	}

	public String getParentRequestId() {
		return parentRequestId;
	}

	public Connection getSQLDBConnection() {
		return connectionSQLDB;
	}

	public void setSQLDBConnection(Connection connectionSQLDB) {
		this.connectionSQLDB = connectionSQLDB;
	}
	

	public void setChildStartTime(String childStartTime) {

		this.childStartTime = childStartTime;
	}

	public String getChildStartTime() {
		return childStartTime;
	}

	public RDTTask getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(RDTTask currentTask) {
		this.currentTask = currentTask;
	}

	public String getPalletID() {
		return palletID;
	}

	public void setPalletID(String palletID) {
		this.palletID = palletID;
	}
	public String getpalletIDforUPI() {
		return palletIDforUPI;
	}

	public void setpalletIDforUPI(String palletIDforUPI) {
		this.palletIDforUPI = palletIDforUPI;
	}

	public String getABV() {
		return newAbv;
	}

	public void setABV(String newAbv) {
		this.newAbv = newAbv;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	public String getSkuId2() {
		return skuid;
	}

	public void setSkuId2(String skuid) {
		this.skuid = skuid;
	}
	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
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

	public ArrayList<String> getPalletIDList() {
		return palletIDList;
	}

	public void setPalletIDList(ArrayList<String> palletIDList) {
		this.palletIDList = palletIDList;
	}

	public int getRecordCountByTaskID() {
		return recordCountByTaskID;
	}

	public void setRecordCountByTaskID(int recordCountByTaskID) {
		this.recordCountByTaskID = recordCountByTaskID;
	}

	public int getMoveTaskRecordCount() {
		return moveTaskRecordCount;
	}

	public void setMoveTaskRecordCount(int moveTaskRecordCount) {
		this.moveTaskRecordCount = moveTaskRecordCount;
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

	public Map<String, String> getLocationForTagMap() {
		return locationPerTagMap;
	}

	public void setLocationForTagMap(Map<String, String> locationPerTagMap) {
		this.locationPerTagMap = locationPerTagMap;
	}

	public Map<String, Integer> getQtyReceivedPerTagMap() {
		return qtyReceivedPerTagMap;
	}

	public List<String> getCaseRatioList() {
		return caseRatiolist; // setCaseRatioList
	}

	public void setCaseRatioList(List<String> caseRatiolist) {
		this.caseRatiolist = caseRatiolist;
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

	public Map<Integer, Map<String, String>> getStockTransferOrderMap() {
		return stockTransferOrderMap;
	}

	public void setstockTransferOrderMap(Map<Integer, Map<String, String>> stockTransferOrderMap) {
		this.stockTransferOrderMap = stockTransferOrderMap;

	}

	public void setQtyOnHand(int qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	public int getQtyOnHand() {
		return qtyOnHand;
	}

	public void setQtyOnHand_zone(int qtyOnHand_zone) {
		this.qtyOnHand_zone = qtyOnHand_zone;
	}

	public int getQtyOnHand_zone() {
		return qtyOnHand_zone;
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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSAPvalue() {
		return SAPvalue;
	}

	public void setSAPvalue(String SAPvalue) {
		this.SAPvalue = SAPvalue;
	}

	public int getQtyReverse() {
		return qtyReverse;
	}

	public void setQtyReverse(int qtyReverse) {
		this.qtyReverse = qtyReverse;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
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

	public String getListID() {
		return listId;
	}

	public void setListID(String listId) {
		this.listId = listId;
	}

	public String getToPallet() {
		return toPallet;
	}

	public void setToPallet(String toPallet) {
		this.toPallet = toPallet;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(String finalLocation) {
		this.finalLocation = finalLocation;
	}

	public int getQtyToMove() {
		return qtyToMove;
	}

	public void setQtyToMove(int qtyToMove) {
		this.qtyToMove = qtyToMove;
	}
	
	public String getQtyToMovepck() {
		return qtyToMovePck;
	}

	public void setQtyToMovePck(String qtyToMovePck) {
		this.qtyToMovePck = qtyToMovePck;
	}

	public void setTrailerNo(String trailerNo) {
		this.trailerNo = trailerNo;
	}

	public ArrayList<String> getFailureList() {
		return failureList;
	}

	public void setFailureList(ArrayList<String> failureList) {
		this.failureList = failureList;
	}

	public void setPutawayLocationMap(Map<String, String> pickFaceMap) {
		this.pickFaceMap = pickFaceMap;
	}

	public Map<String, String> getPutawayLocationMap() {
		return pickFaceMap;
	}

	public String getTrailerNo() {
		return trailerNo;
	}

	public void setBookingID(String bookingID) {
		this.dockSchedulerBookingID = bookingID;
	}

	public String getBookingID() {
		return dockSchedulerBookingID;
	}

	public void setSTOConsignment(String consignment) {
		this.consignment = consignment;
	}

	public String getABVPercentage() {
		return abvPercentage;
	}

	public void setABVPercentage(String abvPercentage) {
		this.abvPercentage = abvPercentage;
	}

	public String getSTOConsignment() {
		return consignment;
	}

	public int getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public Map<Integer, Map<String, String>> getListIDMap() {
		return listIDMap;
	}

	public void setListIDMap(Map<Integer, Map<String, String>> listIDMap) {
		this.listIDMap = listIDMap;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public String getpoId() {
		return poId;
	}

	public void setpoId(String poId) {
		this.poId = poId;
	}
	public Map<Integer, Map<String, String>> getReplenishmentDetailsMap() {
		return replenishmentDetailsMap;
	}

	public void setReplenishmentDetailsMap(Map<Integer, Map<String, String>> replenishmentDetailsMap) {
		this.replenishmentDetailsMap = replenishmentDetailsMap;
	}

	public String getAbv() {
		return abv;
	}

	public void setAbv(String abv) {
		this.abv = abv;
	}

	public int getQtyReceivedFromPutty() {
		return qtyReceivedFromPutty;
	}

	public void setQtyReceivedFromPutty(int qtyReceivedFromPutty) {
		this.qtyReceivedFromPutty = qtyReceivedFromPutty;
	}

	public int getPickedRecords() {
		return pickedRecords;
	}

	public void setPickedRecords(int pickedRecords) {
		this.pickedRecords = pickedRecords;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public boolean isPuttyLoginFlag() {
		return puttyLoginFlag;
	}

	public void setPuttyLoginFlag(boolean puttyLoginFlag) {
		this.puttyLoginFlag = puttyLoginFlag;
	}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getPackConfigID() {
		return packConfigID;
	}

	public void setPackConfigID(String packConfigID) {
		this.packConfigID = packConfigID;
	}

	public void setDockSchedulerNotes(String dockSchedulerNotes) {
		this.dockSchedulerNotes = dockSchedulerNotes;
	}

	public String getDockSchedulerNotes() {
		return dockSchedulerNotes;
	}

	public String[] getDockSchedulerBookingID() {
		return dockSchedulerBookingIDList;
	}

	public void setDockSchedulerBookingID(String[] dockSchedulerBookingIDList) {
		this.dockSchedulerBookingIDList = dockSchedulerBookingIDList;
	}

	public String getStoType() {
		return stoType;
	}

	public void setStoType(String stoType) {
		this.stoType = stoType;
	}
	public String getPOType() {
		return POType;
	}
	public void setASN(String ASN) {
		this.ASN = ASN;
	}
	public String getASN() {
		return ASN;
	}

	public void setPOType(String POType) {
		this.POType = POType;
	}

	public Map<String, Map<Integer, Map<String, String>>> getMultipleOrderListIDMap() {
		return multipleOrderListIDMap;
	}

	public void setMultipleOrderListIDMap(Map<String, Map<Integer, Map<String, String>>> multipleOrderListIDMap) {
		this.multipleOrderListIDMap = multipleOrderListIDMap;
	}

	public Process getPuttyProcess() {
		return puttyProcess;
	}

	public void setPuttyProcess(Process puttyProcess) {
		this.puttyProcess = puttyProcess;
	}

	public String getPickingType() {
		return pickingType;
	}

	public void setPickingType(String pickingType) {
		this.pickingType = pickingType;
	}

	public void setJDALoginFlag(boolean loginPage) {
		this.loginPage = loginPage;

	}

	public boolean isJDALoginFlag() {
		return puttyLoginFlag;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}

	public void setPuttyHost(String puttyHost) {
		this.puttyHost = puttyHost;
	}

	public String getPuttyHost() {
		return puttyHost;
	}

	public void setPuttyPort(String puttyPort) {
		this.puttyPort = puttyPort;

	}

	public String getPuttyPort() {
		return puttyPort;
	}

	public void setAppUserName(String appUsername) {
		this.appUsername = appUsername;
	}

	public String getAppUserName() {
		return appUsername;
	}

	public void setAppPassord(String appPassword) {
		this.appPassword = appPassword;

	}

	public String getAppPassord() {
		return appPassword;
	}

	public void setDBHost(String dBHost) {
		this.dBHost = dBHost;
	}

	public String getDBHost() {
		return dBHost;
	}

	public void setDBUserName(String dBUsername) {
		this.dBUsername = dBUsername;

	}

	public String getDBUserName() {
		return dBUsername;
	}

	public void setDBPassword(String dBPassword) {
		this.dBPassword = dBPassword;

	}

	public String getDBPassword() {
		return dBPassword;
	}

	public void setErrorMessage(String string) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setTriggerQty(String triggerqty) {
		this.triggerQty = triggerQty;

	}

	public String getTriggerQty() {
		return triggerQty;
	}

	public void setworkZone(String workZone) {
		this.workZone = workZone;

	}
	public String getworkZone() {
		return workZone = workZone;
	}

	public void setQtyOnHandTag(int QtyOnHandTag) {
		this.QtyOnHandTag = QtyOnHandTag;
	}

	public void setQtyWithCaseRatio(int QtyWithCaseRatio) {
		this.QtyWithCaseRatio = QtyWithCaseRatio;

	}

	public void setLocationList(String thisLocation) {
		this.LocationList=thisLocation;
		
	}

	public String getLocationList() {
		return LocationList=LocationList;
	}

	public void setQuantity(String quantity) {
		this.Quantity=quantity;
		
	}

	public void setRecordForPallet(String record) {
		this.RecordForPallet=record;
		
	}

	public String getRecordForPallet(String record) {
		return RecordForPallet=record;
	}

	public String getTime() {
		
		return pickFaceTime=pickFaceTime ;
	}
	public void setTime() {
		this.pickFaceTime=pickFaceTime;
		
	}
	public void setTime(String pickFaceTime) {
		this.pickFaceTime = pickFaceTime;
	}

	public void setQtyToMove(String qtyToMove2) {
		this.qtyToMove2 = qtyToMove2;
		
	}

	public String getKey() {
		return Key;
	}


	public void setlockStatus(String status) {
		this.status = status;
		
	}

	public String getlockStatus() {
		
		return status;
	}
	public void setBelCode(String belCode) {
		this.belCode = belCode;
	}

	public String getBelCode() {
		return belCode;
	}

	public void setPackWeight(String packWeight)  {
		this.packWeight = packWeight;
	}
	
	public String getPackWeight() {
		return packWeight;
	}
	public void setProductGroup1(String ProductGroup1)  {
		this.ProductGroup1 = ProductGroup1;
	}
	
	public String getProductGroup1() {
		return ProductGroup1;
	}
	public void setStrokeCt(String strokeCategory) {
		this.strokeCategory=strokeCategory;
		
	}
	public String getStrokeCt() {
		return strokeCategory;
	}

	public void setCommodityCd(String commodityCode) {
		this.commodityCode=commodityCode;
		
	}
	public String getCommodityCd() {
		return commodityCode;
	}

	public void setSKUHang(String sku) {
		this.skuid=sku;
		
		// TODO Auto-generated method stub
		
	}
	public String getSKUHang(){
		return skuid;
	}


	
	}
	





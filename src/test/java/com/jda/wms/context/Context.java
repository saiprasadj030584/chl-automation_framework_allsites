package com.jda.wms.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.runtime.java.guice.ScenarioScope;

public class Context {
	private String preAdviceId;
	private String skuId;
	private String uploaded;
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
	private String dueDate;
	private ArrayList<String> skuFromUPI;
	private ArrayList<String> skuFromOrder;
	private String PutawayLocation1;
	private String PutawayLocation2;
	private String fromLocation;
	private int rcvQtyDue;
	private String location;
	private int tagIdIndex = 0;
	private String transactionTime;
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
	private String faceType;
	private String siteID;
	private ArrayList<String> failureList;
	private ArrayList<String> upiList;
	private ArrayList<String> orderList;
	private ArrayList<String> preAdviceList;
	private ArrayList<String> supplierIdList;
	private String orderId;
	private String orderId2;
	private String customer;
	private String listID;
	private String toPallet;
	private String toLocation;
	private String finalLocation;
	private int qtyToMove;
	private String shipDock;
	private String supplier;
	private ArrayList packConfigList;
	private String putawayLocation1;
	private String putawayLocation2;
	private String newShipDock;
	private String trailerNo;
	private String dockSchedulerBookingID;
	private String orderStatus;
	private String consignment;
	private int pickedRecords;
	private String containerId;
	private Connection connection = null;
	private static Connection dBConnection = null;
	private String abvPercentage;
	private ArrayList<String> palletIDList;
	private Integer recordCountByTaskID;
	private String receiptDate;
	private String palletID;
	private int moveTaskRecordCount;
	private int qtyOrdered;
	private Map<Integer, Map<String, String>> listIDMap;
	private Map<String, String> pickFaceMap;
	private String taskId;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;
	private int qtyReverse;
	private boolean puttyLoginFlag = false;
	private String addressID;
	private String packConfigID;
	private String dockSchedulerNotes;
	private String[] dockSchedulerBookingIDList;
	private String stoType;
	private Map<String, Map<Integer, Map<String, String>>> multipleOrderListIDMap;
	private Process puttyProcess;
	private String pickingType;
	private String upiId;
	private String asnId;
	private ArrayList skuFromPO;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> UPIMap;
	private Map<String, Map<String, Map<String, String>>> MultipleUPIMap;
	private Map<String, Map<Integer, Map<String, String>>> MultiplePOMap;
	private Map<String, String> poNumLinesMap;
	private Map<String, Integer> upiNumLinesMap;
	private String skuType;
	private String packConfig;
	private String UPC;
	private String projLoc;
	private String carrier;
	private String serviceLevel;
	private boolean poQtyMoreThanUPIQty = false;
	private String generateBelCode;
	private ArrayList<String> belCodeList;
	private ArrayList<String> enternewpallet;
	private String bookingTime;
	private String updatedBookingTime;
	private String dockId;
	private String updatedDockId;
	private String condition;
	private String reasonCode;
	private String owner;
	private String receiveType;
	private String Record;
	private String perfectCondition;
	private String supplierType;
	private String partset;
	private String toLocation2;
	private String relocateLoctn;
	private ArrayList<String> qtyTaskedList;
	private int noOfMoveTaskRecords;
	private int skuSize;
	private HashMap<Integer, String> qtyOnHandList;
	private String orderType;
	private static String parentRequestId;
	private String uniqueTag;
	private String adviceId;
	private boolean uniqueTagInRunStatus;
	private String totQtyOnHand;
	public static Connection connectionSQLDB = null;
	private static String childStartTime;
	private boolean jdaLoginFlag = false;
	private int qtyonhandafteradjustment;
	private String origin;
	private String assertString = null;
	private static String childRequestId;
	private static String testData;
	private boolean vehicleLoadRequired = false;
	private String secondPalletID;
	private String ejbError;
	private int updatedQty;
	private static String secondTestData;

	public Map<String, String> getPoNumLinesMap() {
		return poNumLinesMap;
	}

	public void setPoNumLinesMap(Map<String, String> poNumLinesMap) {
		this.poNumLinesMap = poNumLinesMap;
	}

	public ArrayList<String> getSupplierIdList() {
		return supplierIdList;
	}

	public void setSupplierIdList(ArrayList<String> supplierIdList) {
		this.supplierIdList = supplierIdList;
	}

	public ArrayList<String> getUpiList() {
		return upiList;
	}

	public void setUpiList(ArrayList<String> upiList) {
		this.upiList = upiList;
	}

	public ArrayList<String> getPreAdviceList() {
		return preAdviceList;
	}

	public void setPreAdviceList(ArrayList<String> preAdviceList) {
		this.preAdviceList = preAdviceList;
	}

	public String getPartset() {
		return partset;
	}

	public void setPartset(String partset) {
		this.partset = partset;
	}

	public String getPerfectCondition() {
		return perfectCondition;
	}

	public void setPerfectCondition(String perfectCondition) {
		this.perfectCondition = perfectCondition;
	}

	public String getUpdatedBookingTime() {
		return updatedBookingTime;
	}

	public void setUpdatedBookingTime(String updatedBookingTime) {
		this.updatedBookingTime = updatedBookingTime;
	}

	public String getDockId() {
		return dockId;
	}

	public void setDockId(String dockId) {
		this.dockId = dockId;
	}

	public String getUpdatedDockId() {
		return updatedDockId;
	}

	public void setUpdatedDockId(String updatedDockId) {
		this.updatedDockId = updatedDockId;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getPalletID() {
		return palletID;
	}

	public void setPalletID(String palletID) {
		System.out.println("Pallet Id SET HO GAYA");
		this.palletID = palletID;
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

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
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

	private ArrayList skuList;

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
		return caseRatiolist;
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

	public String getFaceType() {
		return faceType;
	}

	public void setFaceType(String faceType) {
		this.faceType = faceType;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
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
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
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

	public String getAsnId() {
		return asnId;
	}

	public void setAsnId(String asnId) {
		this.asnId = asnId;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public Map<Integer, Map<String, String>> getPOMap() {
		return poMap;
	}

	public void setPOMap(Map<Integer, Map<String, String>> pOMap) {
		poMap = pOMap;
	}

	public String getSKUType() {
		return skuType;
	}

	public void setSKUType(String skuType) {
		this.skuType = skuType;
	}

	public String getsupplierType() {
		return supplierType;
	}

	public void setsupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getPackConfig() {
		return packConfig;
	}

	public void setPackConfig(String packConfig) {
		this.packConfig = packConfig;
	}

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	public Map<String, Map<String, String>> getUPIMap() {
		return UPIMap;
	}

	public void setUPIMap(Map<String, Map<String, String>> uPIMap) {
		UPIMap = uPIMap;
	}

	public String getProjLoc() {
		return projLoc;
	}

	public void setProjLoc(String projLoc) {
		this.projLoc = projLoc;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public boolean isPoQtyMoreThanUPIQty() {
		return poQtyMoreThanUPIQty;
	}

	public void setPoQtyMoreThanUPIQty(boolean poQtyMoreThanUPIQty) {
		this.poQtyMoreThanUPIQty = poQtyMoreThanUPIQty;
	}

	public void setBelCode(String generateBelCode) {
		this.generateBelCode = generateBelCode;
	}

	public String getBelCode() {
		return generateBelCode;
	}

	public ArrayList<String> enterNewPallet() {
		return enternewpallet;
	}

	public void setNewPallet(ArrayList enternewpallet) {
		this.enternewpallet = enternewpallet;
	}

	public ArrayList<String> getBelCodeList() {
		return belCodeList;
	}

	public void setBelCodeList(ArrayList<String> belCodeList) {
		this.belCodeList = belCodeList;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public ArrayList getSkuList() {
		return skuList;
	}

	public void setSkuList(ArrayList skuList) {
		this.skuList = skuList;
	}

	public String getRecord() {
		return Record;
	}

	public void setRecord(String record) {
		Record = record;
	}

	public Map<String, Map<String, Map<String, String>>> getMultipleUPIMap() {
		return MultipleUPIMap;
	}

	public void setMultipleUPIMap(Map<String, Map<String, Map<String, String>>> multipleUPIMap) {
		MultipleUPIMap = multipleUPIMap;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public ArrayList getPackConfigList() {
		return packConfigList;
	}

	public void setPackConfigList(ArrayList packConfigList) {
		this.packConfigList = packConfigList;
	}

	public String getPutawayLocation1() {
		return putawayLocation1;
	}

	public void setPutawayLocation1(String putawayLocation1) {
		this.putawayLocation1 = putawayLocation1;
	}

	public String getPutawayLocation2() {
		return putawayLocation2;
	}

	public void setPutawayLocation2(String putawayLocation2) {
		this.putawayLocation2 = putawayLocation2;
	}

	public String getToLocation2() {
		return toLocation2;
	}

	public void setToLocation2(String toLocation2) {
		this.toLocation2 = toLocation2;
	}

	public String getRelocateLoctn() {
		return relocateLoctn;
	}

	public void setRelocateLoctn(String relocateLoctn) {
		this.relocateLoctn = relocateLoctn;
	}

	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	public Map<String, Integer> getUpiNumLinesMap() {
		return upiNumLinesMap;
	}

	public void setUpiNumLinesMap(Map<String, Integer> upiNumLinesMap) {
		this.upiNumLinesMap = upiNumLinesMap;
	}

	public Map<String, Map<Integer, Map<String, String>>> getMultiplePOMap() {
		return MultiplePOMap;
	}

	public void setMultiplePOMap(Map<String, Map<Integer, Map<String, String>>> multiplePOMap) {
		MultiplePOMap = multiplePOMap;
	}

	public ArrayList getSkuFromPO() {
		return skuFromPO;
	}

	public void setSkuFromPO(ArrayList skuFromPO) {
		this.skuFromPO = skuFromPO;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public ArrayList<String> getSkuFromOrder() {
		return skuFromOrder;
	}

	public void setSkuFromOrder(ArrayList<String> skuFromOrder) {
		this.skuFromOrder = skuFromOrder;
	}

	public ArrayList<String> getSkuFromUPI() {
		return skuFromUPI;
	}

	public void setSkuFromUPI(ArrayList<String> skuFromUPI) {
		this.skuFromUPI = skuFromUPI;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public ArrayList<String> getQtyTaskedList() {
		return qtyTaskedList;
	}

	public void setQtyTaskedList(ArrayList<String> qtyTaskedList) {
		this.qtyTaskedList = qtyTaskedList;
	}

	public int getNoOfMoveTaskRecords() {
		return noOfMoveTaskRecords;
	}

	public void setNoOfMoveTaskRecords(int noOfMoveTaskRecords) {
		this.noOfMoveTaskRecords = noOfMoveTaskRecords;
	}

	public int getSkuSize() {
		return skuSize;
	}

	public void setSkuSize(int skuSize) {
		this.skuSize = skuSize;
	}

	public HashMap<Integer, String> getQtyOnHandList() {
		return qtyOnHandList;
	}

	public void setQtyOnHandList(HashMap<Integer, String> qtyOnHandList) {
		this.qtyOnHandList = qtyOnHandList;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getParentRequestId() {
		return parentRequestId;
	}

	public void setParentRequestId(String parentRequestId) {
		this.parentRequestId = parentRequestId;
	}

	public String getUniqueTag() {
		return uniqueTag;
	}

	public void setUniqueTag(String uniqueTag) {
		this.uniqueTag = uniqueTag;
	}

	public String getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}

	public boolean getUniqueTagInRunStatus() {
		return uniqueTagInRunStatus;
	}

	public void setUniqueTagInRunStatus(boolean uniqueTagInRunStatus) {
		this.uniqueTagInRunStatus = uniqueTagInRunStatus;
	}

	public String getTotQtyOnHand() {
		return totQtyOnHand;
	}

	public void setTotQtyOnHand(String totQtyOnHand) {
		this.totQtyOnHand = totQtyOnHand;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public boolean isJdaLoginFlag() {
		return jdaLoginFlag;
	}

	public int getUpdatedQty() {
		return updatedQty;
	}

	public void setUpdatedQty(int updatedQty) {
		this.updatedQty = updatedQty;
	}

	public void setJdaLoginFlag(boolean jdaLoginFlag) {
		this.jdaLoginFlag = jdaLoginFlag;
	}

	public String getAssertString() {
		return assertString;
	}

	public void setAssertString(String assertString) {
		this.assertString = assertString;
	}

	public static String getChildRequestId() {
		return childRequestId;
	}

	public static void setChildRequestId(String childRequestId) {
		Context.childRequestId = childRequestId;
	}

	public static String getTestData() {
		return testData;
	}

	public static void setTestData(String testData) {
		Context.testData = testData;
	}
	public static String getSecondTestData() {
		return secondTestData;
	}

	public static void setSecondTestData(String secondTestData) {
		Context.secondTestData = secondTestData;
	}

	public boolean isVehicleLoadRequired() {
		return vehicleLoadRequired;
	}

	public void setVehicleLoadRequired(boolean vehicleLoadRequired) {
		this.vehicleLoadRequired = vehicleLoadRequired;
	}

	public int getQtyonhandafteradjustment() {
		return qtyonhandafteradjustment;
	}

	public void setQtyonhandafteradjustment(int qtyonhandafteradjustment) {
		this.qtyonhandafteradjustment = qtyonhandafteradjustment;
	}

	public String getSecondPalletID() {
		return secondPalletID;
	}

	public void setSecondPalletID(String secondPalletID) {
		this.secondPalletID = secondPalletID;
	}

	public ArrayList<String> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<String> orderList) {
		this.orderList = orderList;
	}

	public String getOrderId2() {
		return orderId2;
	}

	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}
	public void setDBConnection(Connection dBConnection) {
		this.dBConnection = dBConnection;

	}

	public Connection getDBConnection() {
		return dBConnection;
	}

	public void setEJBErrorMsg(String ejbError) {
		this.ejbError = ejbError;
	}

	public String getEJBErrorMsg() {
		return ejbError;
	}

}

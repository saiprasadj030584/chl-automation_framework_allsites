package com.jda.wms.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sikuli.script.Match;

import com.jda.wms.UI.pages.RDTTask;

import cucumber.api.Scenario;

public class Context {
	private static String upiId = null;
	private RDTTask currentTask;
	private int DataSize;
	private String BarCode;
	private String preAdviceId;
	private String skuId;
	private String skuId1;
	private String productGroup;
	private String cewarehousetype;
	private String allocationGroup;
	private String ean;
	private String newAbv;
	private String tagId;
	private String tagId1;
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
	private String consignmentID1;
	private String productCategory;
	private String lockCode;
	private String vintage;
	private String abv;
	private Scenario scenario;
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
	private ArrayList<String> orderIdlist;
	private ArrayList<String> orderIdlist1;
	private ArrayList<String> QtyList;
	private String orderId1;
	private String customer = null;
	private String listId;
	private String toPallet;
	private String toLocation;
	private String finalLocation;
	private int qtyToMove;
	private String shipDock;
	private String newShipDock;
	private String trailerID;
	private String trailerNo;
	private String trailerNumber;
	private String dockSchedulerBookingID;
	private String orderStatus;
	private String consignment;
	private String errorMessage;
	private int pickedRecords;
	private String containerId;
	private static Connection connection = null;
	private String abvPercentage;
	private ArrayList<String> palletIDList;
	private ArrayList<String> BelList;
	private ArrayList<String> preAdviceList;
	private Integer recordCountByTaskID;
	private String palletID;
	private String palletID1;
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
	private String ASN1;
	public String getSkuId2;
	private String packWeight;
	private String strokeCategory;
	private String commodityCode;
	private String ProductGroup1;
	private String allowedStock;
	private String SKUHang;
	private String sKUHang;
	private String PalletID2;
	private String BelCode2;
	private String upc;
	private String upc1;
	private String PalletId;
	private String poId2;
	private String belCode2;
	private String palletID2;
	private String orderId2;
	private String consignmentName;
	private String stoId2;
	private String country2;
	private String quantity;
	private String TagTrans;
	private String supportID;
	private String DockDoor;
	private String palletIDforUPI1;
	private String BelCode1;
	private String PalletId1;
	private String consignmentName2;
	private String MasterURN;
	private String MasterURN1;
	private int position;
	private Match mSlot;
	private ArrayList<String> palletPut;
	private String orderId;
	private ArrayList<String> OrderIDList;
	private ArrayList<String> MasterurnList;
	private ArrayList<String> PalletList;
	private ArrayList<String> ASNList;
	private ArrayList<String> skuIDList;
	private ArrayList<String> skuListForInventory;
	private ArrayList<String> UPCList;
	private String PreadviceId1;
	private String trailerNos;
	private String randomPal;
	private int tagId2;
	private String RMS_putaway_location;
	private String putaway_location;
	private String rackLocation;
	private ArrayList<String> LocList;
	private ArrayList<String> supList;
	private ArrayList<String> CustmList;
	private String qtyOrdered1;
	private String qtyDue;
	private String tote_container;
	private String cage_container;
	private ArrayList<String> skuIDListDirect;
	private ArrayList<String> skuIDList_Second_Direct;

	private ArrayList<String> skuIDListIN;
	private ArrayList<String> skuIDListNonRetail;
	private ArrayList<String> OrderIDList_Direct;
	private ArrayList<String> OrderIDList_Second_Direct;
	private ArrayList<String> OrderIDList_IN;
	private ArrayList<String> OrderIDList_NonRetail;
	private ArrayList<String> QtyList_Direct;
	private ArrayList<String> QtyList_Second_Direct;

	private ArrayList<String> QtyList_IN;
	private ArrayList<String> QtyList_NonRetail;
	private ArrayList<String> qtyListForInventory;
	private List<String> ContainerList;
	private ArrayList<String> ListId;
	private String NDCname;

	private ArrayList<String> SourceIdList;
	private ArrayList<String> HubList;
	private ArrayList<String> CustomerList;
	private ArrayList<String> upcList;
	private ArrayList<String> LineIdList;
	private ArrayList<String> SupplierList;
	private ArrayList<String> QtydueList;
	private ArrayList<String> OdnList;
	private ArrayList<String> UrnAdviceList;
	private ArrayList<String> StoList;
	private ArrayList<String> AsnIdList;
	private ArrayList<String> MasterUrnList;

	private String qnty;
	private String upcnotinurn;
	private String store;

	private String inputupc;
	private String qty;
	private String choice;
	
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

	public String getPalletID2() {
		return palletID2;
	}

	public void setPalletID2(String palletID) {
		this.palletID2 = palletID2;
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

	public String getConsignmentID_2() {
		return consignmentID;
	}

	public void setConsignmentID_2(String consignmentID2) {
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

	public String getTrailerNo() {
		return trailerNo;
	}

	public void setTrailerNo_2(String trailerNos) {
		this.trailerNos = trailerNos;
	}

	public String getTrailerNo_2() {
		return trailerNos;
	}

	public ArrayList<String> getFailureList() {
		return failureList;
	}

	public void setFailureList(ArrayList<String> failureList) {
		this.failureList = failureList;
	}

	public ArrayList<String> getListId() {
		return ListId;
	}

	public void setListId(ArrayList<String> ListId) {
		this.ListId = ListId;
	}

	public void setPutawayLocationMap(Map<String, String> pickFaceMap) {
		this.pickFaceMap = pickFaceMap;
	}

	public Map<String, String> getPutawayLocationMap() {
		return pickFaceMap;
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

	public void setQtyOredered1(String qtyOrdered1) {
		this.qtyOrdered1 = qtyOrdered1;
	}

	public String getQtyOredered1() {
		return qtyOrdered1;
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
		this.LocationList = thisLocation;

	}

	public String getLocationList() {
		return LocationList = LocationList;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;

	}

	public void setRecordForPallet(String record) {
		this.RecordForPallet = record;

	}

	public String getRecordForPallet(String record) {
		return RecordForPallet = record;
	}

	public String getTime() {

		return pickFaceTime = pickFaceTime;
	}

	public void setTime() {
		this.pickFaceTime = pickFaceTime;

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

	public void setBelCode2(String belCode2) {
		this.belCode2 = belCode2;
	}

	public String getBelCode2() {
		return belCode2;
	}

	public void setPackWeight(String packWeight) {
		this.packWeight = packWeight;
	}

	public String getPackWeight() {
		return packWeight;
	}

	public void setProductGroup1(String ProductGroup1) {
		this.ProductGroup1 = ProductGroup1;
	}

	public String getProductGroup1() {
		return ProductGroup1;
	}

	public void setStrokeCt(String strokeCategory) {
		this.strokeCategory = strokeCategory;

	}

	public String getStrokeCt() {
		return strokeCategory;
	}

	public void setCommodityCd(String commodityCode) {
		this.commodityCode = commodityCode;

	}

	public String getCommodityCd() {
		return commodityCode;
	}

	public void setAllowedStock(String allowedStock) {
		this.allowedStock = allowedStock;

	}

	public String getAllowedStock() {
		return allowedStock;
	}

	public void setSKUHang(String SKUHang) {
		this.SKUHang = SKUHang;
	}

	public String getSKUHang() {
		return SKUHang;
	}

	public void setTagTrans(String TagTrans) {
		this.TagTrans = TagTrans;
	}

	public String getTagTrans() {
		return TagTrans;
	}

	public void setupc(String upc) {
		this.upc = upc;
		// TODO Auto-generated method stub

	}

	public String getupc() {

		return upc;
	}

	public String getConsignmentName() {
		return consignmentName;
	}

	public void setConsignmentName(String consignmentName) {
		this.consignmentName = consignmentName;
	}

	public void setpoId2(String poId2) {
		this.poId2 = poId2;
	}

	public String getpoId2() {

		return poId2;
	}

	public void setOrderId2(String stoId2) {
		this.stoId2 = stoId2;

	}

	public String getOrderId2() {
		// TODO Auto-generated method stub
		return stoId2;
	}

	public void setOriginialCountry(String country2) {
		this.country2 = country2;
	}

	public String getOriginialCountry() {
		return country2;
	}

	public void setTrailerNumber(String trailerNumber) {
		this.trailerNumber = trailerNumber;
	}

	public String getTrailerNumber() {
		// TODO Auto-generated method stub
		return trailerNumber;
	}

	public String getQuantity() {
		return quantity;

	}

	public String getPalletId() {
		return PalletId;
	}

	public void setPalletId(String palletId) {
		PalletId = palletId;
	}

	public void setBasicUser(String supportID) {
		// TODO Auto-generated method stub
		this.supportID = supportID;

	}

	public String getBasicUser() {
		return supportID;
	}

	public String getDockDoor() {
		return DockDoor;
	}

	public void setDockDoor(String dockDoor) {
		DockDoor = dockDoor;
	}

	public String getPalletIDforUPI1() {
		return palletIDforUPI1;
	}

	public void setPalletIDforUPI1(String palletIDforUPI1) {
		this.palletIDforUPI1 = palletIDforUPI1;
	}

	public String getBelCode1() {
		return BelCode1;
	}

	public void setBelCode1(String belCode1) {
		BelCode1 = belCode1;
	}

	public String getPalletId1() {
		return PalletId1;
	}

	public void setPalletId1(String palletId1) {
		PalletId1 = palletId1;
	}

	public String getConsignmentName2() {
		return consignmentName2;
	}

	public void setConsignmentName2(String consignmentName2) {
		this.consignmentName2 = consignmentName2;
	}

	public void setKey(String key) {
		// TODO Auto-generated method stub
		this.Key = key;
	}

	public void setMasterURN(String MASTER_URN) {
		this.MasterURN = MASTER_URN;
	}

	public String getMasterURN() {
		return MasterURN;
	}

	public String getOrderId1() {
		return orderId1;
	}

	public void setOrderId1(String orderId1) {
		this.orderId1 = orderId1;
	}

	public String getMasterURN1() {
		return MasterURN1;
	}

	public void setMasterURN1(String masterURN1) {
		MasterURN1 = masterURN1;
	}

	public String getPalletID1() {
		return palletID1;
	}

	public void setPalletID1(String palletID1) {
		this.palletID1 = palletID1;
	}

	public String getASN1() {
		return ASN1;
	}

	public void setASN1(String aSN1) {
		ASN1 = aSN1;
	}

	public String getSkuId1() {
		return skuId1;
	}

	public void setSkuId1(String skuId1) {
		this.skuId1 = skuId1;
	}

	public String getUpc1() {
		return upc1;
	}

	public void setUpc1(String upc1) {
		this.upc1 = upc1;
	}

	public String getConsignmentID1() {
		return consignmentID1;
	}

	public void setConsignmentID1(String consignmentID1) {
		this.consignmentID1 = consignmentID1;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPosition(Match mSlot) {
		this.mSlot = mSlot;

	}

	public ArrayList<String> getBelList() {
		return BelList;
	}

	public void setBelList(ArrayList<String> belList) {
		BelList = belList;
	}

	public ArrayList<String> getPreAdviceList() {
		return preAdviceList;
	}

	public void setPreAdviceList(ArrayList<String> preAdviceList) {
		this.preAdviceList = preAdviceList;
	}

	public ArrayList<String> getPalletForPutaway() {
		return palletPut;
	}

	public void setPalletForPutaway(ArrayList<String> palletPut) {
		this.palletPut = palletPut;
	}

	public ArrayList<String> getOrderIdlist1() {
		return orderIdlist1;
	}

	public void setOrderIdlist1(ArrayList<String> orderIdlist1) {
		this.orderIdlist1 = orderIdlist1;
	}

	public ArrayList<String> getMasterurnList() {
		return MasterurnList;
	}

	public void setMasterurnList(ArrayList<String> masterurnList) {
		MasterurnList = masterurnList;
	}

	public ArrayList<String> getPalletList() {
		return PalletList;
	}

	public void setPalletList(ArrayList<String> palletList) {
		PalletList = palletList;
	}

	public ArrayList<String> getSkuIDList() {
		return skuIDList;
	}

	/*** methods for sku Data upload ***/
	public void setSkuDataSize(int recCount) {
		this.DataSize = recCount;
	}

	public int getSkuDataSize() {
		return DataSize;
	}

	public void setSkuIDList(ArrayList<String> sku_id_list) {
		this.skuIDList = sku_id_list;
	}

	public ArrayList<String> getSkuIDList(ArrayList<String> sku_id_list) {
		return skuIDList;
	}

	public void setSourceIDList(ArrayList<String> sourceIdList) {
		this.SourceIdList = sourceIdList;
	}

	public ArrayList<String> getSourceIDList() {
		return SourceIdList;
	}

	public void sethubList(ArrayList<String> hubIdList) {
		this.HubList = hubIdList;
	}

	public ArrayList<String> getHubList() {
		return HubList;
	}

	public void setCustomerList(ArrayList<String> customerList) {
		this.CustomerList = customerList;
	}

	public ArrayList<String> getCustomerList() {
		return CustomerList;
	}

	public void setUpcList(ArrayList<String> upcList) {
		this.upcList = upcList;
	}

	public ArrayList<String> getUpcList() {
		return upcList;
	}

	public void setSupplierList(ArrayList<String> supplierList) {
		this.SupplierList = supplierList;
	}

	public ArrayList<String> getSupplierList() {
		return SupplierList;
	}

	public void setLineIdList(ArrayList<String> lineIdList) {
		this.LineIdList = lineIdList;
	}

	public ArrayList<String> getLineIdList() {
		return LineIdList;
	}

	public void setQtyDue(ArrayList<String> qtyDueList) {
		this.QtydueList = qtyDueList;
	}

	public ArrayList<String> getQtyDue() {
		return QtydueList;
	}

	public void setOdnList(ArrayList<String> odnList) {
		this.OdnList = odnList;
	}

	public ArrayList<String> getOdnList() {
		return OdnList;
	}

	public void seturnAdviceList(ArrayList<String> urnAdviceList) {
		this.UrnAdviceList = urnAdviceList;
	}

	public ArrayList<String> getUrnAdviceList() {
		return UrnAdviceList;
	}

	public void setStoList(ArrayList<String> stoList) {
		this.StoList = stoList;
	}

	public ArrayList<String> getStoList() {
		return StoList;
	}

	public void setAsnIdList(ArrayList<String> asnList) {
		this.AsnIdList = asnList;
	}

	public ArrayList<String> getAsnIdList() {
		return AsnIdList;
	}

	public void setmasterUrnList(ArrayList<String> masterUrnList) {
		this.MasterUrnList = masterUrnList;
	}

	public ArrayList<String> getMasterUrnList() {
		return MasterUrnList;
	}

	public ArrayList<String> getSkuIDListDirect() {
		return skuIDListDirect;
	}

	public void setSkuIDListDirect(ArrayList<String> skuIDListDirect) {
		this.skuIDListDirect = skuIDListDirect;
	}

	public ArrayList<String> getSkuIDListIN() {
		return skuIDListIN;
	}

	public void setSkuIDListIN(ArrayList<String> skuIDListIN) {
		this.skuIDListIN = skuIDListIN;
	}

	public ArrayList<String> getSkuIDListNonRetail() {
		return skuIDListNonRetail;
	}

	public void setSkuIDListNonRetail(ArrayList<String> skuIDListNonRetail) {
		this.skuIDListNonRetail = skuIDListNonRetail;
	}

	public ArrayList<String> getUPCList() {
		return UPCList;
	}

	public void setUPCList(ArrayList<String> uPCList) {
		UPCList = uPCList;
	}

	public ArrayList<String> getASNList() {
		return ASNList;
	}

	public void setASNList(ArrayList<String> aSNList) {
		ASNList = aSNList;
	}

	public ArrayList<String> getOrderIDList() {
		return OrderIDList;
	}

	public void setOrderIDList(ArrayList<String> orderIDList) {
		this.OrderIDList = orderIDList;
	}

	public ArrayList<String> getOrderIDList_Direct() {
		return OrderIDList_Direct;
	}

	public void setOrderIDList_Direct(ArrayList<String> orderIDList_Direct) {
		this.OrderIDList_Direct = orderIDList_Direct;
	}

	public ArrayList<String> getOrderIDList_NonRetail() {
		return OrderIDList_NonRetail;
	}

	public void setOrderIDList_NonRetail(ArrayList<String> orderIDList_NonRetail) {
		this.OrderIDList_NonRetail = orderIDList_NonRetail;
	}

	public ArrayList<String> getOrderIDList_IN() {
		return OrderIDList_IN;
	}

	public void setOrderIDList_IN(ArrayList<String> orderIDList_IN) {
		this.OrderIDList_IN = orderIDList_IN;
	}

	public String getPreadviceId1() {
		return PreadviceId1;
	}

	public void setPreadviceId1(String preadviceId1) {
		PreadviceId1 = preadviceId1;
	}

	public void setOrderId_2(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId_2() {
		return orderId;
	}

	public void setTagId1(String tagId1) {
		this.tagId1 = tagId1;

	}

	public String getTagId1() {
		return tagId1;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;

	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setRandomPalletId(String randomPal) {
		this.randomPal = randomPal;

	}

	public String getRandomPalletId() {
		return randomPal;
	}

	public void setTagId2(int tagId2) {
		this.tagId2 = tagId2;

	}

	public int getTagId2() {
		return tagId2;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public String getRMS_putaway_location() {
		return RMS_putaway_location;
	}

	public void setRMS_putaway_location(String RMS_putaway_location) {
		this.RMS_putaway_location = RMS_putaway_location;
	}

	public String getPutaway_location() {
		return putaway_location;
	}

	public void setPutaway_location(String putaway_location) {
		this.putaway_location = putaway_location;
	}

	public ArrayList<String> getQtyList() {
		return QtyList;
	}

	public void setQtyList(ArrayList<String> qtyList) {
		QtyList = qtyList;
	}

	public ArrayList<String> getQtyList_Direct() {
		return QtyList_Direct;
	}

	public void setQtyList_Direct(ArrayList<String> qtyList_Direct) {
		this.QtyList_Direct = qtyList_Direct;
	}

	public ArrayList<String> getQtyList_IN() {
		return QtyList_IN;
	}

	public void setQtyList_IN(ArrayList<String> qtyList_IN) {
		this.QtyList_IN = qtyList_IN;
	}

	public ArrayList<String> getQtyList_NonRetail() {
		return QtyList_NonRetail;
	}

	public void setQtyList_NonRetail(ArrayList<String> qtyList_NonRetail) {
		this.QtyList_NonRetail = qtyList_NonRetail;
	}

	public ArrayList<String> getLocList() {
		return LocList;
	}

	public void setLocList(ArrayList<String> locList) {
		LocList = locList;
	}

	public ArrayList<String> getSupList() {
		return supList;
	}

	public void setSupList(ArrayList<String> supList) {
		this.supList = supList;
	}

	public void setQtyOredered(String qtyOrdered1) {
		this.qtyOrdered1 = qtyOrdered1;
	}

	public String getQtyOredered() {
		return qtyOrdered1;
	}

	public String getqtyDue() {
		return qtyDue;
	}

	public String setQtyDue(String QTY_DUE) {
		return qtyDue;
	}

	public ArrayList<String> getCustmList() {
		return CustmList;
	}

	public void setCustmList(ArrayList<String> custmList) {
		CustmList = custmList;
	}

	public String getTote_container() {
		return tote_container;
	}

	public void setTote_container(String tote_container) {
		this.tote_container = tote_container;
	}

	public String getCage_container() {
		return cage_container;
	}

	public void setCage_container(String cage_container) {
		this.cage_container = cage_container;
	}

	public List<String> getContainerList() {
		return ContainerList;
	}

	public void setContainerList(List<String> uniquecontainerList) {
		this.ContainerList = uniquecontainerList;
	}

	public void setContainerList1(List<String> uniquecontainerList) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getSkuIDList_Second_Direct() {
		return skuIDList_Second_Direct;
	}

	public void setSkuIDList_Second_Direct(ArrayList<String> skuIDList_Second_Direct) {
		this.skuIDList_Second_Direct = skuIDList_Second_Direct;
	}

	public ArrayList<String> getOrderIDList_Second_Direct() {
		return OrderIDList_Second_Direct;
	}

	public void setOrderIDList_Second_Direct(ArrayList<String> orderIDList_Second_Direct) {
		OrderIDList_Second_Direct = orderIDList_Second_Direct;
	}

	public ArrayList<String> getQtyList_Second_Direct() {
		return QtyList_Second_Direct;
	}

	public void setQtyList_Second_Direct(ArrayList<String> qtyList_Second_Direct) {
		QtyList_Second_Direct = qtyList_Second_Direct;
	}

	public ArrayList<String> getSkuListForInventory() {
		return skuListForInventory;
	}

	public void setSkuListForInventory(ArrayList<String> skuListForInventory) {
		this.skuListForInventory = skuListForInventory;
	}

	public void setQtyListForInventory(ArrayList<String> qtyListForInventory) {
		this.qtyListForInventory = qtyListForInventory;

	}

	public ArrayList<String> getQtyListForInventory() {
		return qtyListForInventory;
	}

	public void setNDC(String NDCname) {
		this.NDCname = NDCname;
		// TODO Auto-generated method stub

	}

	public String getNDC(String NDCname) {
		return NDCname;
		// TODO Auto-generated method stub

	}

	public String getTrailerID() {
		return trailerID;
	}

	public void setTrailerID(String trailerID) {
		this.trailerID = trailerID;
	}

	public void setqnty(String qnty) {
		this.qnty = qnty;
		// TODO Auto-generated method stub

	}

	public void setupcnotinurn(String upcnotinurn) {
		this.upcnotinurn = upcnotinurn;
	}

	public void setstore(String store) {
		this.store = store;
	}

	public String getupcnotinurn() {
		return upcnotinurn;
	}

	public String getqnty() {

		return qnty;
	}

	public String getstore() {
		return store;
	}

	public void setOption(String choice) {
		this.choice = choice;
	}

	public String getOption() {
		return choice;
	}
	public void setInputupc(String inputupc) {
		this.inputupc = inputupc;
	}

	public String getInputupc() {
		return inputupc;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getQty() {
		return qty;
	}
	
}

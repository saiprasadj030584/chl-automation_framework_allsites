package com.jda.wms.handlers;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.SikuliException;
import com.jda.wms.db.PreAdviceHeaderDB;
import com.jda.wms.db.UPIReceiptHeaderDB;
import com.google.inject.Inject;
import com.jda.wms.config.Constants;
import com.jda.wms.db.AddressDB;
import com.jda.wms.db.ClusteringDB;
import com.jda.wms.db.InventoryTransactionDB;
import com.jda.wms.db.LocationDB;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.utils.Utilities;

public class ValidationHandler {
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private OrderHeaderDB orderHeaderDB;
	private Constants constants;
	private RDTHandler rdtHandler;
	private AddressDB addressDB;
	private ClusteringDB clusteringDB;
	private LocationDB locationDB;
	private MoveTaskDB moveTaskDB;
	private Utilities utilities;
	private InventoryTransactionDB inventorytransactionDB;
	
	
	@Inject
	public ValidationHandler(UPIReceiptHeaderDB upiReceiptHeaderDB, Constants constants,
			PreAdviceHeaderDB preAdviceHeaderDB, RDTHandler rdtHandler, AddressDB addressDB,
			OrderHeaderDB orderHeaderDB, ClusteringDB clusteringDB, LocationDB locationDB, MoveTaskDB moveTaskDB,InventoryTransactionDB inventorytransactionDB) {
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.constants = constants;
		this.rdtHandler = rdtHandler;
		this.addressDB = addressDB;
		this.orderHeaderDB = orderHeaderDB;
		this.clusteringDB = clusteringDB;
		this.locationDB = locationDB;
		this.moveTaskDB = moveTaskDB;
		this.utilities = utilities;
		this.inventorytransactionDB = inventorytransactionDB;

	}

	public void validateUpiReleaseStatus(String urn) throws ClassNotFoundException, SQLException {
		try {
			Assert.assertEquals(constants.RELEASE_STATUS, upiReceiptHeaderDB.getStatus(urn));
			System.out.println(constants.UPI_STATUS_MATCH_MESSAGE);
		} catch (AssertionError e) {
			System.out.println(constants.UPI_STATUS_NOMATCH_MESSAGE);
		}
	}

	public void validateUpiCompleteStatus(String urn) throws ClassNotFoundException, SQLException {
		try {
			// Assert.assertEquals(constants.COMPLETE_STATUS,
			// upiReceiptHeaderDB.getStatus(urn));
			if (upiReceiptHeaderDB.getStatus(urn).equals(constants.COMPLETE_STATUS)) {
				// System.out.println(constants.UPI_STATUS_MATCH_MESSAGE);
				Assert.fail();
			}
		} catch (AssertionError e) {

			Assert.fail(constants.URN_RECEIVED);

		}
	}

	public void validatePOReleaseStatus(String po) throws ClassNotFoundException, SQLException {
		try {
			Assert.assertEquals(constants.RELEASE_STATUS, preAdviceHeaderDB.getStatus(po));
			System.out.println(constants.PO_STATUS_MATCH_MESSAGE);
		} catch (AssertionError e) {
			System.out.println(constants.PO_STATUS_NOMATCH_MESSAGE);
		}
	}

	public void validateRdtScreen(String screen) throws ClassNotFoundException, SQLException {
		try {
			Assert.assertEquals(screen, rdtHandler.getScreenName());
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError | SikuliException | InterruptedException e) {
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateSite(String site) throws ClassNotFoundException, SQLException {
		try {
			Assert.assertEquals(site, addressDB.getAddressId(site));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateOrderStatus(String orderID) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertEquals("Released", orderHeaderDB.getStatus(orderID));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateGroupId(String groupID) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(clusteringDB.isInClusterGroupTable(groupID));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateConfigId(String configID) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(clusteringDB.isInClusterConfigTable(configID));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateConfigIdInData(String configID) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(clusteringDB.isInClusterConfigurationDataTable(configID));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateLocation(String location) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(locationDB.checkLocation(location));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}
	// Repacking Validation of container id from move task
	// import and add the variable repackDB

	public void validateContainerId1(String ContainerId) throws Throwable {
		Thread.sleep(2000);
		try {
			System.out.println("Validating Container Id");
			Assert.assertEquals(true, moveTaskDB.isInMoveTaskTable(ContainerId));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			Assert.fail();
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public void validateTagId(String TagID) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(clusteringDB.isInMovetaskTable(TagID));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);
			System.out.println(constants.SCREEN_NOMATCH);
		}
	}

	public boolean validateUpiPreReceive(String urn) throws ClassNotFoundException, SQLException {
		try {
			if (upiReceiptHeaderDB.getStatus(urn).equals("PreReceived"))
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public boolean validateisHanging(String SKU) throws ClassNotFoundException, SQLException {
		try {
			if (upiReceiptHeaderDB.getU_D_8Status(SKU).equals("H"))
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public String randomPalletforRec() {
		String pal = Utilities.getNineDigitRandomNumber();
		return pal;

	}

	public boolean validateUpiPreReleaseStatus(String urn) throws ClassNotFoundException, SQLException {
		try {
			if (constants.PRE_RELEASE_STATUS.equalsIgnoreCase(upiReceiptHeaderDB.getStatus(urn)))
				return true;
			else
				return false;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return false;
	}


	public void validateContainerafterRepack(String pallet_id) throws Throwable {
		Thread.sleep(2000);
		try {
			Assert.assertTrue(inventorytransactionDB.isInInventoryTransactionTable(pallet_id));
			System.out.println(constants.SCREEN_MATCH);
		} catch (AssertionError e) {
			System.out.println(constants.SCREEN_NOMATCH);

		}
	}

}

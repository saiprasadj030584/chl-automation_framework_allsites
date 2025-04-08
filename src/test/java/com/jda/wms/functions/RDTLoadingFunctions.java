package com.jda.wms.functions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.sikuli.script.FindFailed;
import com.google.inject.Inject;
import com.jda.wms.UI.pages.StoreTrackingOrderPickingPage;
import com.jda.wms.config.Constants;
import com.jda.wms.context.Context;
import com.jda.wms.db.BookingInDiary;
import com.jda.wms.db.BookingInDiaryDetails;
import com.jda.wms.db.ConsignmentDB;
import com.jda.wms.db.LocationDB;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.db.PreAdviceHeaderDB;
import com.jda.wms.db.SkuDB;
import com.jda.wms.db.UPIReceiptHeaderDB;
import com.jda.wms.handlers.DataHandler;
import com.jda.wms.handlers.RDTHandler;
import com.jda.wms.handlers.ValidationHandler;
import com.jda.wms.handlers.WebHandler;
import com.jda.wms.utils.Utilities;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import com.jda.wms.config.SiteConstants;

/**
 * This class contains methods related to receiving functionality for all the
 * sites.
 * 
 * @author saiprasad.jakkula
 *
 */
public class RDTLoadingFunctions {
	private RDTHandler rdthandler;
	private DataHandler datahandler;
	private WebHandler uihandler;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private OrderLineDB orderlineDB;
	private Constants constants;
	private static Context context;
	private Utilities utilities;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private OrderHeaderDB orderheaderdb;
	private MoveTaskDB movetaskdb;
	private BookingInDiary bookingindiary;
	private BookingInDiaryDetails bookingindiarydetails;
	private ConsignmentDB consignmentDB;

	@Inject
	public RDTLoadingFunctions(RDTHandler rdthandler, UPIReceiptHeaderDB upiReceiptHeaderDB, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB, StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			DataHandler datahandler, OrderLineDB orderlineDB, OrderHeaderDB orderheaderdb, MoveTaskDB movetaskdb,
			BookingInDiary bookingindiary, BookingInDiaryDetails bookingindiarydetails, Constants constants,
			WebHandler uihandler, Utilities utilities, ConsignmentDB consignmentDB) {
		this.rdthandler = rdthandler;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.context = context;
		this.orderlineDB = orderlineDB;
		this.datahandler = datahandler;
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.orderheaderdb = orderheaderdb;
		this.movetaskdb = movetaskdb;
		this.bookingindiary = bookingindiary;
		this.bookingindiarydetails = bookingindiarydetails;
		this.constants = constants;
		this.uihandler = uihandler;
		this.utilities = utilities;
		this.consignmentDB = consignmentDB;
	}

	/**
	 * Author: Vedika The following is the code for Vehicle Loading
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	@Then("^I navigate to Vehicle Loading screen$")
	public void i_navigate_to_Vehicle_Loading_screen()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		rdthandler.Loading("Multi Pallet Load");
	}

	/**
	 * @author Vedika.Vinod The below code is to load orders for all DCs except
	 *         HEMEL
	 * @param orderId
	 * @param site
	 * @throws FindFailed
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Then("^I load the trailer \"([^\"]*)\" for order \"([^\"]*)\" for site \"([^\"]*)\"$")
	public void i_load_the_order_for_site(String trailerid, String orderId, String site)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {

			ArrayList<String> containerList = new ArrayList<String>();
			containerList = movetaskdb.getContainerIDList(orderId);

			String consignment = orderheaderdb.getConsignment(orderId);
			String bookingID = bookingindiarydetails.getBookingID(consignment, trailerid);

			if (site.equals("3641") || site.equals("3366")) {
				if (orderheaderdb.getStatus(orderId).equalsIgnoreCase("Picked") || orderheaderdb.getStatus(orderId).equalsIgnoreCase("Packed")
						|| orderheaderdb.getStatus(orderId).equalsIgnoreCase("Ready to Load")) {

					System.out.println("Loading Order : " + orderId + "............");
					for (String containerId : containerList) {

						rdthandler.typeStringAndTab(bookingindiary.getBay(bookingID));
						Thread.sleep(2000);
						rdthandler.typeStringAndTab(consignment);
						Thread.sleep(2000);
						rdthandler.typeStringAndEnter(bookingindiary.getTrailerID(bookingID));
						Thread.sleep(3000);
						rdthandler.typeStringAndEnter(movetaskdb.getContainer(orderId));
						Thread.sleep(2000);
						rdthandler.typeStringAndEnter("Y");
						Thread.sleep(3000);
						// rdthandler.typeKey("ENTER");
						// Thread.sleep(5000);
						// rdthandler.typeKey("ENTER");
						// Thread.sleep(3000);
					}
					validateAfterLoading(orderId);
				} else {
					Assert.fail(constants.ORDER_STATUS_MISMATCH);
				}

			} else {

				if (orderheaderdb.getStatus(orderId).equalsIgnoreCase("Picked") || orderheaderdb.getStatus(orderId).equalsIgnoreCase("Packed")
						|| orderheaderdb.getStatus(orderId).equalsIgnoreCase("Ready to Load")) {

					System.out.println("Loading Order : " + orderId + "............");
					for (String containerId : containerList) {

						if (rdthandler.getScreenName().equalsIgnoreCase("LodMent")) {

							Thread.sleep(2000);
							rdthandler.typeStringAndTab(movetaskdb.getContainer(orderId));
							Thread.sleep(2000);
							rdthandler.typeStringAndEnter(bookingindiary.getBay(bookingID));
							Thread.sleep(2000);
							Thread.sleep(4000);
							//rdthandler.typeKey("ENTER");
							//Thread.sleep(5000);
							// rdthandler.typeKey("ENTER");
//							bug identified while consolidation testing
							if (!rdthandler.getScreenName().equalsIgnoreCase("LodMent")) {
								if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgQry")) {
								rdthandler.typeStringAndEnter("Y");
								} else {
								rdthandler.typeKey("ENTER");
								}
								 Thread.sleep(5000);
							}
							Thread.sleep(3000);

						} else {
							Assert.fail(constants.SCREEN_NOMATCH);
						}

					}
					validateAfterLoading(orderId);
				} else {
					Assert.fail(constants.ORDER_STATUS_MISMATCH);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is for validating after loading is completed
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterLoading(String orderId) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Loading Process.....");

		try {
			if (orderheaderdb.getStatus(orderId).equalsIgnoreCase("Complete")) {
				System.out.println("Loaded Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for Consignment Close
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */

	@Then("^I close the consignment \"([^\"]*)\" for HEMEL$")
	public void i_close_the_consignment_for_HEMEL(String consignmentName)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {
			context.setConsignmentName(consignmentName);
			rdthandler.goToInventoryTransaction();
			rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(consignmentName);
			Thread.sleep(3000);
			rdthandler.typeKey("ENTER");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Vedika.Vinod This function is to perform loading for HEMEL
	 * @param palletId
	 * @param orderId
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	@Then("^I perform Loading for pallet \"([^\"]*)\" of order \"([^\"]*)\"$")
	public void i_perform_Loading_for_pallet_of_order(String palletId, String orderId)
			throws FindFailed, InterruptedException {
		try {

			rdthandler.Loading("Multi Pallet Load");

			Thread.sleep(2000);
			rdthandler.typeStringAndTab(palletId);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(context.getConsignmentName());
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(consignmentDB.getTrailerID(context.getConsignmentName()));
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			validateAfterLoading(orderId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^I navigate to Inventory Transaction screen$")
	public void i_navigate_to_Inventory_Transaction_screen() throws FindFailed, InterruptedException {
		rdthandler.goToInventoryTransaction();
	}

	/**
	 * Author: Vedika The following is the code for Pallet Congignment Linking
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I link the consignment \"([^\"]*)\" with the pallet \"([^\"]*)\"$")
	public void i_link_the_consignment_with_the_pallet(String consignmentName, String palletId)
			throws FindFailed, InterruptedException {

		rdthandler.typeStringAndTab(palletId);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(consignmentName);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");

	}

	/**
	 * @author SRIRAMASUBRAMANIAN.K Below is code to Navigate to shipment in
	 *         Hemel
	 * @throws Throwable
	 */

	@Given("^Go to Vehicle loading shipment$")
	public void go_to_Vehicle_loading_shipment() throws Throwable {
		rdthandler.typeKey("276");
		rdthandler.typeKey("ENTER");
	}

	/**
	 * @author SRIRAMASUBRAMANIAN.K Below is code to perform shipment in Hemel
	 * @param consignment
	 * @param trailer
	 * @throws Throwable
	 */
	@Given("^Perform shipment in hemel \"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_shipment_in_hemel(String consignment, String trailer) throws Throwable {

		Thread.sleep(2000);
		rdthandler.typeString("276");
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);

		rdthandler.typeKey("TAB");
		rdthandler.typeString(consignment);
		rdthandler.typeKey("TAB");
		rdthandler.typeString(trailer);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
		rdthandler.typeKey("F10");
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
		rdthandler.typeKey("F10");
		Thread.sleep(2000);
		rdthandler.typeChar('Y');
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTenDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(utilities.getTwoDigitRandomNumber());
	}
	/**
	 * @author Vedika.Vinod The below code is to load all orders in a
	 *         consignment
	 * @param orderId
	 * @param site
	 * @throws FindFailed
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Then("^I load the trailer \"([^\"]*)\" for consignment \"([^\"]*)\" for site \"([^\"]*)\"$")
	public void i_load_the_trailer_for_consignment_for_site(String trailerid, String consignmentName, String site)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {

			ArrayList<String> orderList = new ArrayList<String>();
			ArrayList<String> containerList = new ArrayList<String>();
			orderList = orderheaderdb.getOrderId(consignmentName);
			String bookingID = bookingindiarydetails.getBookingID(consignmentName, trailerid);

			if (site.equals("3641") || site.equals("3366")) {

				for (String orderId : orderList) {
					if (orderheaderdb.getStatus(orderId).equalsIgnoreCase("Picked")
							|| orderheaderdb.getStatus(orderId).equalsIgnoreCase("Ready to Load")) {
						containerList = movetaskdb.getContainerIDList(orderId);
						System.out.println("Loading Order : " + orderId + "............");
						for (String containerId : containerList) {
							if (rdthandler.getScreenName().equalsIgnoreCase("LodMent")) {
								rdthandler.typeStringAndTab(bookingindiary.getBay(bookingID));
								Thread.sleep(2000);
								rdthandler.typeStringAndTab(consignmentName);
								Thread.sleep(2000);
								rdthandler.typeStringAndEnter(bookingindiary.getTrailerID(bookingID));
								Thread.sleep(3000);
								rdthandler.typeStringAndEnter(containerId);
								Thread.sleep(2000);
								
								rdthandler.typeStringAndEnter("Y");
								Thread.sleep(3000);

							} else {
								Assert.fail(constants.SCREEN_NOMATCH);
							}
						}
						validateAfterLoading(orderId);
					} else {
						Assert.fail(constants.ORDER_STATUS_MISMATCH);
					}
				}

			} else {

				for (String orderId : orderList) {
					containerList = movetaskdb.getContainerIDList(orderId);

					if (orderheaderdb.getStatus(orderId).equalsIgnoreCase("Picked")
							|| orderheaderdb.getStatus(orderId).equalsIgnoreCase("Ready to Load")) {

						System.out.println("Loading Order : " + orderId + "............");

						for (String containerId : containerList) {

							if (rdthandler.getScreenName().equalsIgnoreCase("LodMent")) {

								Thread.sleep(2000);
								rdthandler.typeStringAndTab(containerId);
								Thread.sleep(2000);
								rdthandler.typeStringAndEnter(bookingindiary.getBay(bookingID));
								Thread.sleep(2000);
								Thread.sleep(4000);
						
								if (!rdthandler.getScreenName().equalsIgnoreCase("LodMent")) {
									if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgQry")) {
										rdthandler.typeStringAndEnter("Y");
									} else {
										rdthandler.typeKey("ENTER");
									}

									Thread.sleep(5000);
								}

							} else {
								Assert.fail(constants.SCREEN_NOMATCH);
							}

						}
						Thread.sleep(2000);
						validateAfterLoading(orderId);
					} else {
						Assert.fail(constants.ORDER_STATUS_MISMATCH);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
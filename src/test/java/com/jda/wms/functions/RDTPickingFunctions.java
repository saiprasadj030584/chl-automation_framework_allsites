package com.jda.wms.functions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.StoreTrackingOrderPickingPage;
import com.jda.wms.config.Constants;
import com.jda.wms.context.Context;
import com.jda.wms.db.LocationDB;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.db.PreAdviceHeaderDB;
import com.jda.wms.db.SkuDB;
import com.jda.wms.db.UPIReceiptHeaderDB;
import com.jda.wms.handlers.DataHandler;
import com.jda.wms.handlers.RDTHandler;
import com.jda.wms.handlers.ValidationHandler;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import com.jda.wms.config.SiteConstants;

/**
 * This class contains methods related to receiving functionality for all the
 * sites.
 * 
 * @author saiprasad.jakkula
 *
 */
public class RDTPickingFunctions {
	private RDTHandler rdthandler;
	private DataHandler dataHandler;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private OrderLineDB orderlineDB;
	private static Context context;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private ValidationHandler validationHandler;
	private SiteConstants siteConstants;
	private LocationDB locationDB;
	private MoveTaskDB movetaskDB;
	private SkuDB skuDB;

	private static Screen screen = new Screen();
	
	@Inject
	public RDTPickingFunctions(RDTHandler rdthandler, UPIReceiptHeaderDB upiReceiptHeaderDB, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB, StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			DataHandler datahandler, OrderLineDB orderlineDB, ValidationHandler validationHandler,
			SiteConstants siteConstants, LocationDB locationDB, MoveTaskDB movetaskDB, SkuDB skuDB) {
		this.rdthandler = rdthandler;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.context = context;
		this.orderlineDB = orderlineDB;
		this.dataHandler = datahandler;
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.validationHandler = validationHandler;
		this.siteConstants = siteConstants;
		this.locationDB = locationDB;
		this.movetaskDB = movetaskDB;
		this.skuDB = skuDB;
	}

	@Before
	public void beforeTest() {
		System.out.println("**Execution of " + name.getMethodName() + " in RDTFunctions started");
	}

	@After
	public void afterTest() {
		System.out.println("**Execution of " + name.getMethodName() + " in RDTFunctions completed");
	}

	@Rule
	public TestName name = new TestName();

	@Given("^Perform Stock Check for \"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_Stock_Check_for(String location, String qty) throws Throwable {
		rdthandler.InventMenu("New Stock Check");

		String str = locationDB.getCheckString(location);
		validationHandler.validateLocation(location);
		rdthandler.typeStringAndEnter(location);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(str);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(qty);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(qty);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeChar('N');
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
	}

	@Given("^Perform Stock Check for \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_Stock_Check_for(String listid, String location, String qty) throws Throwable {

		rdthandler.InventMenu("Existing Stock Check");
		rdthandler.typeStringAndEnter(listid);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(locationDB.getCheckString(location));
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(qty);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeStringAndEnter(qty);
		rdthandler.typeKey("ENTER");
	}

	// *****************************Following are StepDefs for
	// Picking*****************************//

	@Given("^Select Container Pick$")
	public void CompleteAllContainerPicking()
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.Picking("Container Pick");
	}

	/**
	 * Container Picking for Multiple ListIds
	 * 
	 * @author Kasi E
	 * @param ListS
	 * @throws Throwable
	 */
	@Given("^Perform picking for the container With Multiple ListID$")
	public void container_pick_Using_Multiple_List(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			TrolleyPickingusingList(list);
		}

	}

	/**
	 * Container Nonretail Picking process for Multiple ListIDS
	 * 
	 * @author Kasi E
	 * @param ListS
	 * @throws Throwable
	 */
	@Given("^Perform picking for NOn Retail container With Multiple ListID$")
	public void containerPickforNonRetailUsingMultipleList(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			containerPickforNonRetail(context.getListID());
		}

	}

	/**
	 * Container picking RDC Box Retail for Multiple ListIDS
	 * 
	 * @author Kasi E
	 * @param ListS
	 * @throws Throwable
	 */
	@Given("^Perform picking Rdc Box Retail With Multiple List$")
	public void RDCRetailBoxPickingUsingMultipleList(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			RetailBoxPickingUsingList(context.getListID());
		}

	}

	/**
	 * Container picking RDC Hang Retail for Multiple ListIDS
	 * 
	 * @author Kasi E
	 * @param ListS
	 * @throws Throwable
	 */
	@Given("^Perform picking Rdc Hang Retail With Multiple List$")
	public void RDCRetailHangPickingUsingMultipleList(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			RDCRetailHangPickingUsingList(context.getListID());
		}

	}

	/**
	 * RDC box Nonretail picking Methods.
	 * 
	 * @author kasi
	 * @param Site
	 * @param List_id
	 * @throws Throwable
	 */
	@Given("^Perform picking Rdc Box NonRetail With Multiple List$")
	public void RDCNonRetailBoxPickingUsingMultipleList(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			containerPickforNonRetail(context.getListID());
		}

	}

	/**
	 * RDCNonRetail Hanging
	 * 
	 * @author Kasi
	 * @param Site
	 * @param List_id
	 * @throws Throwable
	 */
	@Given("^Perform picking Rdc Hang NonRetail With Multiple List$")
	public void RDCNonRetailHangPickingUsingMultipleList(DataTable ListS) throws Throwable {
		for (Map<String, String> data : ListS.asMaps(String.class, String.class)) {

			String list = data.get("LIST_ID");
			System.out.println("List ID IS" + list);
			RDCRetailHangPickingUsingList(context.getListID());
		}

	}

	/**
	 * All Picking Method for Welham and Swindon for a list_id
	 * 
	 * @author Kasi E
	 * @param PickingType
	 * @param Site
	 * @param InputType
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Perform \"([^\"]*)\" Pick in \"([^\"]*)\" for \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void CompleteAllNDCPicking(String PickingType, String Site, String InputType, String value)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		System.out.println("site is " + Site);

		if (InputType.equals("ListID")) {
			context.setListID(value);
		} else if (InputType.equals("Order")) {
			context.setOrderId(value);
		} else if (InputType.equals("Consignment")) {
			context.setConsignmentID(value);
			// Consignment spelling was changed
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
		if (Site.equals("7401") && InputType.equals("ListID") && PickingType.equals("Trolley")) {
			// String list_id = value;
			TrolleyPickingusingList(context.getListID());
		} else if (Site.equals("5665") && InputType.equals("ListID") && PickingType.equals("Trolley")) {
			SWTrolleyPickingusingList(context.getListID());
		} else if (Site.equals("7401") && InputType.equals("Order") && PickingType.equals("Trolley")) {
			TrolleyPickingusingOrder(context.getOrderId());
		} else if (Site.equals("5665") && InputType.equals("Order") && PickingType.equals("Trolley")) {
			SWTrolleyPickingusingOrder(context.getOrderId());
		} else if (Site.equals("7401") && InputType.equals("Consignment") && PickingType.equals("Trolley")) {
			TrolleyPickingusingConsignment(context.getConsignmentID());
		} else if (Site.equals("5665") && InputType.equals("Consignment") && PickingType.equals("Trolley")) {
			SWTrolleyPickingusingConsignment(context.getConsignmentID());
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
	}

	/**
	 * All Picking for RDC
	 * 
	 * @author Kasi E
	 * @param Modularity
	 * @param OrderType
	 * @param Site
	 * @param InputType
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Perform RDC \"([^\"]*)\" \"([^\"]*)\" Pick in \"([^\"]*)\" for \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void CompleteAllRDCPicking(String Modularity, String OrderType, String Site, String InputType, String value)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		System.out.println("site is " + Site);

		if (InputType.equals("ListID")) {
			context.setListID(value);
		} else if (InputType.equals("Order")) {
			context.setOrderId(value);
		} else if (InputType.equals("Consignment")) {
			context.setConsignmentID(value);
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
		if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("ListID")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailBoxPickingUsingList(context.getListID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") || OrderType.equals("NonRetail")
				&& InputType.equals("ListID") && (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailHangPickingUsingList(context.getListID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("ListID")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			containerPickforNonRetail(context.getListID());
		} else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Order")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailBoxPickingUsingOrder(context.getOrderId());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") || OrderType.equals("NonRetail")
				&& InputType.equals("Order") && (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailHangPickingUsingOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Order")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			containerPickforNonRetailUsingOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Consignment")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailBoxPickingUsingConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") || OrderType.equals("NonRetail")
				&& InputType.equals("Consignment") && (Site.equals("6868") || Site.equals("7236"))) {
			RDCRetailHangPickingConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Consignment")
				&& (Site.equals("6868") || Site.equals("7236"))) {
			TrolleyPickingusingConsignment(context.getConsignmentID());
		}

		else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
	}

	/**
	 * All Picking Method for NDC for a list_id
	 * 
	 * @author Kasi E
	 * @param PickingType
	 * @param Site
	 * @param InputType
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Perform NDC \"([^\"]*)\" \"([^\"]*)\" Pick in \"([^\"]*)\" for \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void CompleteAllNDCPicking(String Modularity, String OrderType, String Site, String InputType, String value)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		System.out.println("site is " + Site);

		if (InputType.equals("ListID")) {
			context.setListID(value);
		} else if (InputType.equals("Order")) {
			context.setOrderId(value);
		} else if (InputType.equals("Consignment")) {
			context.setConsignmentID(value);
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
		if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("ListID")
				&& Site.equals("5885")) {
			System.out.println("Box Retail");
			StokeRetailBoxList(context.getListID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("ListID")
				&& Site.equals("5885")) {
			containerPickforNonRetail(context.getListID());
		}

		else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Order")
				&& Site.equals("5885")) {
			StokeRetailBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("Order")
				&& Site.equals("5885")) {
			containerPickforNonRetailUsingOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Consignment")
				&& Site.equals("5885")) {
			StokeRetailBoxConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("Consignment")
				&& Site.equals("5885")) {
			containerPickforNonRetailUsingConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("ListID")
				&& Site.equals("5885")) {
			System.out.println("Box NONRetail");
			StokeNonRetBoxList(context.getListID());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("ListID")
				&& Site.equals("5885")) {
			System.out.println("Hang NONRetail");
			RDCRetailHangPickingUsingList(context.getListID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Order")
				&& Site.equals("5885")) {
			StokeNonRetBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("Order")
				&& Site.equals("5885")) {
			containerPickforNonRetailUsingOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Consignment")
				&& Site.equals("5885")) {
			StokeNonRetBoxConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("Consignment")
				&& Site.equals("5885")) {
			containerPickforNonRetailUsingConsignment(context.getConsignmentID());
		}
		// west thurrock retail and non retail
		else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("ListID")
				&& Site.equals("5649")) {
			System.out.println("West TBox Retail");
			WTRetailBoxList(context.getListID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("ListID")
				&& Site.equals("5649")) {
			WTRetailBoxList(context.getListID());
		}

		else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Order")
				&& Site.equals("5649")) {
			WTRetailBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("Order")
				&& Site.equals("5649")) {
			WTRetailBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("Retail") && InputType.equals("Consignment")
				&& Site.equals("5649")) {
			WTRetailBoxConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Hang") && OrderType.equals("Retail") && InputType.equals("Consignment")
				&& Site.equals("5649")) {
			WTRetailBoxConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("ListID")
				&& Site.equals("5649")) {
			System.out.println("Box NONRetail");
			WTRetailBoxList(context.getListID());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("ListID")
				&& Site.equals("5649")) {
			System.out.println("Hang NONRetail");
			WTRetailBoxList(context.getListID());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Order")
				&& Site.equals("5649")) {
			WTRetailBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("Order")
				&& Site.equals("5649")) {
			WTRetailBoxOrder(context.getOrderId());
		} else if (Modularity.equals("Box") && OrderType.equals("NonRetail") && InputType.equals("Consignment")
				&& Site.equals("5649")) {
			WTRetailBoxConsignment(context.getConsignmentID());
		} else if (Modularity.equals("Hang") && OrderType.equals("NonRetail") && InputType.equals("Consignment")
				&& Site.equals("5649")) {
			WTRetailBoxConsignment(context.getConsignmentID());
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
	}

	/**
	 * @desc Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param List_id
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void SWTrolleyPickingusingList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeStringAndEnter("DUMMY");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.short_sleep);
			System.out.println("PCKContainerSCRN ENTRY");
			String Slot = rdthandler.getSlotIDforSW();
			Thread.sleep(Constants.medium_sleep);
			System.out.println(Slot);
			System.out.println("PCKContainerSCRN ");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			String container = movetaskDB.getContainerid(Slot, List_id);
			Thread.sleep(Constants.long_sleep);
			System.out.println("Container_id is" + container);
			if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.short_sleep);
				System.out.println("Check String");

				String location = rdthandler.getLocation();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(location);
				String chkstring = locationDB.getCheckString(location);
				rdthandler.typeString(chkstring);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.medium_sleep);
			}

			else {
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.short_sleep);
				System.out.println("Check String");

				String location = rdthandler.getLocation();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(location);
				String chkstring = locationDB.getCheckString(location);
				rdthandler.typeString(chkstring);
				rdthandler.typeKey("ENTER");
			}
		}

		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

	}

	// Picking process Using OrderID
	/**
	 * @desc Swindon Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void SWTrolleyPickingusingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		System.out.println("rderid is" + context.getOrderId());
		Thread.sleep(Constants.medium_sleep);

		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(context.getOrderId());
		Thread.sleep(10000);

		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			System.out.println("List is" + ListIDlist.get(z));
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			System.out.println("List_id Entered" + ListIDlist.get(z));

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeStringAndEnter("DUMMY");
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotIDforSW();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}

				else {
					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
				}
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// Picking process Using Consignment
	// Picking process Using OrderID
	/**
	 * @desc Swindon Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void SWTrolleyPickingusingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.medium_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeStringAndEnter("DUMMY");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.long_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotIDforSW();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}

				else {
					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
				}
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	/**
	 * @desc Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param List_id
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void TrolleyPickingusingList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeStringAndEnter("DUMMY");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.short_sleep);
			System.out.println("PCKContainerSCRN ENTRY");
			String Slot = rdthandler.WelhanmgetSlotID();
			Thread.sleep(Constants.short_sleep);
			System.out.println(Slot);
			System.out.println("PCKContainerSCRN ");
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			System.out.println("PCKContainerSCRN END ");
			Thread.sleep(Constants.long_sleep);

			String container = movetaskDB.getUNRMands(Slot, List_id);
			Thread.sleep(Constants.long_sleep);
			System.out.println("Container_id is" + container);
			rdthandler.typeString(container);
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);

	}

	// Picking process Using OrderID
	/**
	 * @desc Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void TrolleyPickingusingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		System.out.println("rderid is" + context.getOrderId());
		Thread.sleep(Constants.medium_sleep);

		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(context.getOrderId());
		Thread.sleep(10000);

		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			System.out.println("List is" + ListIDlist.get(z));
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			System.out.println("List_id Entered" + ListIDlist.get(z));

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter("DUMMY");
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				Thread.sleep(Constants.medium_sleep);
				String Slot = rdthandler.WelhanmgetSlotID();
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);

				String container = movetaskDB.getUNRMands(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				rdthandler.typeString(container);
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// Picking process Using Consignment
	// Picking process Using OrderID
	/**
	 * @desc Trolley_Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void TrolleyPickingusingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.medium_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeStringAndEnter("DUMMY");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.long_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.long_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.WelhanmgetSlotID();
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);

				String container = movetaskDB.getUNRMands(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// NonRetail COntainer Picking with ListID
	// Picking process Using OrderID
	/**
	 * @desc Non Retail Container Pick
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void containerPickforNonRetail(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		Thread.sleep(Constants.short_sleep);
		String topallet = rdthandler.randompalletforPick();
		System.out.println("topallet" + topallet);
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeString(topallet);
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		String totag = rdthandler.randomTagforPick();
		Thread.sleep(Constants.long_sleep);
		System.out.println("totag" + totag);
		rdthandler.typeString(totag);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(5000);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {

			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			System.out.println("SkuCon" + SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			System.out.println("PCKContainerSCRN END ");
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");
			Thread.sleep(5000);
		}
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);

	}

	// NonRetail COntainer Picking with Order
	// Picking process Using OrderID
	/**
	 * @desc Non retail container Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void containerPickforNonRetailUsingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		System.out.println("Order id is" + OrderID);
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.size(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.medium_sleep);
			String topallet = rdthandler.randompalletforPick();
			System.out.println("topallet" + topallet);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(topallet);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.long_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {

				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				System.out.println("SkuCon" + SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeString(totag);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// NonRetail COntainer Picking with Consignment
	/**
	 * @desc Non retail container Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void containerPickforNonRetailUsingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.short_sleep);
		for (int z = 0; z < ListIDlist.size(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.medium_sleep);
			String topallet = rdthandler.randompalletforPick();
			Thread.sleep(Constants.long_sleep);
			System.out.println("topallet" + topallet);
			rdthandler.typeString(topallet);
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.short_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {

				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				System.out.println("SkuCon" + SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeString(totag);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// RDC Picking functions
	/**
	 * @desc RDC BOX Pickning
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RetailBoxPickingUsingList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		Thread.sleep(Constants.long_sleep);

		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(3000);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);
			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.medium_sleep);
			System.out.println("PCKContainerSCRN ENTRY");
			String Slot = rdthandler.getSlotID();
			System.out.println(Slot);
			System.out.println("PCKContainerSCRN ");
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			System.out.println("PCKContainerSCRN END ");
			Thread.sleep(Constants.medium_sleep);

			String container = movetaskDB.getContainerid(Slot, List_id);
			Thread.sleep(Constants.long_sleep);
			System.out.println("Container_id is" + container);
			rdthandler.typeString(container);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);

	}

	// RDC Retail Box Picking with order
	/**
	 * @desc RDC BOX Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RetailBoxPickingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.medium_sleep);
		for (int z = 0; z < ListIDlist.size(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.short_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.medium_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);

				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// RDC Retail Box Picking with Consignment
	/**
	 * @desc RDC BOX Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RetailBoxPickingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.size(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			Thread.sleep(Constants.long_sleep);
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {

				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);
				Thread.sleep(Constants.long_sleep);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.long_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);

				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	// RDC Hanging Retail Picking
	/**
	 * @desc RDC Hang Pickning
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RDCRetailHangPickingUsingList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		String totag = rdthandler.randomTagforPick();
		Thread.sleep(Constants.short_sleep);
		System.out.println("totag" + totag);
		rdthandler.typeString(totag);
		rdthandler.typeKey("ENTER");

		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			rdthandler.typeStringAndEnter(upc);

			Thread.sleep(Constants.medium_sleep);

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);

	}

	/**
	 * @desc RDC Hang Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RDCRetailHangPickingUsingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.short_sleep);
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.short_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			Thread.sleep(Constants.long_sleep);
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);

				Thread.sleep(Constants.medium_sleep);

				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.medium_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
	}

	/**
	 * @desc RDC Hang Pickning
	 * @author Kasinathan.Elavarasa
	 * @param Consingment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */

	public void RDCRetailHangPickingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.short_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.short_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			// ArrayList<String> To_containers = new ArrayList<String>();
			Thread.sleep(Constants.long_sleep);
			for (int i = 0; i < SKUList.stream().count(); i++) {

				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);
				Thread.sleep(Constants.medium_sleep);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.medium_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
	}

	/**
	 * @desc RDC Retail Box
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RDCRetailBoxPickingUsingList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		System.out.println("List_id Entered");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		String totag = rdthandler.randomTagforPick();
		Thread.sleep(Constants.short_sleep);
		System.out.println("totag" + totag);
		rdthandler.typeString(totag);
		rdthandler.typeKey("ENTER");

		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(3000);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.medium_sleep);

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);

	}

	/**
	 * @desc RDC Retail Box
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RDCRetailBoxPickingUsingOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.short_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(3000);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.medium_sleep);

				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	/**
	 * @desc RDC Retail Box
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void RDCRetailBoxPickingUsingConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.medium_sleep);
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
			String totag = rdthandler.randomTagforPick();
			Thread.sleep(Constants.short_sleep);
			System.out.println("totag" + totag);
			rdthandler.typeString(totag);
			rdthandler.typeKey("ENTER");

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(3000);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.medium_sleep);

				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
		}
	}

	/**
	 * @desc Stoke Nonretail box
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeNonRetBoxList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		System.out.println("List_id Entered");
		Thread.sleep(Constants.short_sleep);
		String topallet = rdthandler.randompalletforPick();
		// String topalletid="1"+topallet;
		System.out.println("topallet" + topallet);
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeString(topallet);
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			if (rdthandler.getScreenName().equalsIgnoreCase("CntID")) {

				String totag = rdthandler.randomTagforPick();
				// String totagid="9"+totag;
				Thread.sleep(Constants.medium_sleep);
				System.out.println("totag" + totag);
				rdthandler.typeString(totag);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.short_sleep);

				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				System.out.println("SkuCon" + SKUList.get(i));
				Thread.sleep(Constants.short_sleep);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeString(totag);
				rdthandler.typeKey("ENTER");

			}

		}
		type_chkString();
	}

	/**
	 * @desc Stoke Nonretail box
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeNonRetBoxOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		Thread.sleep(Constants.long_sleep);
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.short_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.short_sleep);
			String topallet = rdthandler.randompalletforPick();
			// String topalletid="1"+topallet;
			System.out.println("topallet" + topallet);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeString(topallet);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				if (rdthandler.getScreenName().equalsIgnoreCase("CntID")) {

					String totag = rdthandler.randomTagforPick();
					// String totagid="9"+totag;
					Thread.sleep(Constants.medium_sleep);
					System.out.println("totag" + totag);
					rdthandler.typeString(totag);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);

					// To_containers =
					// movetaskDB.getToContainer_WitListID(List_id);

					System.out.println("SkuCon" + SKUList.get(i));
					Thread.sleep(Constants.short_sleep);
					String upc = skuDB.getSupplierSKU(SKUList.get(i));
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeString(totag);
					rdthandler.typeKey("ENTER");

				}

			}
			type_chkString();
		}
	}

	/**
	 * @desc Stoke Nonretail box
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeNonRetBoxConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {

		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			System.out.println("List_id Entered");
			Thread.sleep(Constants.short_sleep);
			String topallet = rdthandler.randompalletforPick();
			// String topalletid="1"+topallet;
			System.out.println("topallet" + topallet);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeString(topallet);
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				if (rdthandler.getScreenName().equalsIgnoreCase("CntID")) {

					String totag = rdthandler.randomTagforPick();
					// String totagid="9"+totag;
					Thread.sleep(Constants.medium_sleep);
					System.out.println("totag" + totag);
					rdthandler.typeString(totag);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);

					// To_containers =
					// movetaskDB.getToContainer_WitListID(List_id);

					System.out.println("SkuCon" + SKUList.get(i));
					Thread.sleep(Constants.short_sleep);
					String upc = skuDB.getSupplierSKU(SKUList.get(i));
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeString(totag);
					rdthandler.typeKey("ENTER");

				}

			}
			type_chkString();
			Thread.sleep(Constants.medium_sleep);

		}
	}

	public void type_chkString()
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			System.out.println("Check String");

			String location = rdthandler.getLocation();
			Thread.sleep(Constants.medium_sleep);
			System.out.println(location);
			String chkstring = locationDB.getCheckString(location);
			rdthandler.typeString(chkstring);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
	}

	// Stoke Retail Box
	/**
	 * @desc Stoke retail box
	 * @author Kasinathan.Elavarasa
	 * @param List
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeRetailBoxList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		rdthandler.typeKey("ENTER");
		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.short_sleep);
			System.out.println("PCKContainerSCRN ENTRY");
			String Slot = rdthandler.getSlotID();
			Thread.sleep(Constants.medium_sleep);
			System.out.println(Slot);
			System.out.println("PCKContainerSCRN ");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			String container = movetaskDB.getContainerid(Slot, List_id);
			Thread.sleep(Constants.long_sleep);
			System.out.println("Container_id is" + container);
			if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.short_sleep);
				System.out.println("Check String");

				String location = rdthandler.getLocation();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(location);
				String chkstring = locationDB.getCheckString(location);
				rdthandler.typeString(chkstring);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.medium_sleep);
			}

			else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
		}
		type_chkString();
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

	}

	/**
	 * @desc Stoke retail box
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeRetailBoxOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}

				else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
			}
			type_chkString();
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
		}
	}

	/**
	 * @desc Stoke retail box
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void StokeRetailBoxConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {

		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);

		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}

				else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
			}
			type_chkString();
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
		}
	}

	// west thurrock
	/**
	 * @author Kasinathan.Elavarasa
	 * @details West thurrock Picking
	 * @param List_id
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */

	public void WTRetailBoxList(String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		rdthandler.typeKey("ENTER");
		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);

			String upc = skuDB.getSupplierSKU(SKUList.get(i));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(Constants.short_sleep);
			System.out.println("PCKContainerSCRN ENTRY");
			String Slot = rdthandler.getSlotID();
			Thread.sleep(Constants.medium_sleep);
			System.out.println(Slot);
			System.out.println("PCKContainerSCRN ");
			Thread.sleep(Constants.medium_sleep);
			rdthandler.typeKey("ENTER");
			String container = movetaskDB.getContainerid(Slot, List_id);
			Thread.sleep(Constants.long_sleep);
			System.out.println("Container_id is" + container);

			if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

				System.out.println("PCKContainerSCRN END ");
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeString(container);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
			}
		}

		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

	}

	/**
	 * @desc West thurrock retail box
	 * @author Kasinathan.Elavarasa
	 * @param Order
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void WTRetailBoxOrder(String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
			}

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
		}
	}

	/**
	 * @desc West thurrock retail box
	 * @author Kasinathan.Elavarasa
	 * @param Consignment
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FindFailed
	 * @throws IOException
	 */
	public void WTRetailBoxConsignment(String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {

		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment(ConsignmentID);

		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);

				String upc = skuDB.getSupplierSKU(SKUList.get(i));
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + container);
				if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
				}
			}

			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
	}

	/**
	 * Container picking RDC Hang Retail for Multiple ListIDS
	 * 
	 * @author Komagal
	 * @param Site&Container
	 * @throws Throwable
	 */

	@Given("^Select Repacking$")
	public void CompleteRepacking()
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.Repacking();

	}

	@Given("^Perform Repacking using Site \"([^\"]*)\" and ContainerID \"([^\"]*)\"$")
	public void Repack_With_Site_Id(String Site, String ContainerID) throws Throwable {

		System.out.println("site is " + Site);
		if (Site.equals("7401")) {

			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);

				System.out.println("Site is welham");
				Thread.sleep(Constants.long_sleep);
				String toPallet = rdthandler.randompalletforRePack();
				Thread.sleep(Constants.long_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndEnter(toPallet);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + ContainerID);

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Site.equals("5665")) {
			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);

				System.out.println("Site is Swindon " + Site);
				Thread.sleep(Constants.short_sleep);
				String toPallet = rdthandler.randompalletforRePack();
				Thread.sleep(Constants.short_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndTab(toPallet);
				Thread.sleep(Constants.long_sleep);
				String clientID = movetaskDB.getclientID(ContainerID);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeStringAndEnter(clientID);
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("TAB");
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.short_sleep);
				System.out.println("Container_id is" + ContainerID);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (Site.equals("5885")) {
			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);

				System.out.println("Site is Stoke" + Site);
				Thread.sleep(Constants.short_sleep);
				String toPallet = rdthandler.randompalletforRePack();
				Thread.sleep(Constants.short_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndEnter(toPallet);
				Thread.sleep(Constants.short_sleep);

				rdthandler.typeKey("TAB");
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.short_sleep);
				System.out.println("Container_id is" + ContainerID);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (Site.equals("5649")) {
			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);

				System.out.println("Site is Westhrrock" + Site);
				Thread.sleep(Constants.long_sleep);
				String toPallet = rdthandler.randompalletforRePack();
				Thread.sleep(Constants.long_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndEnter(toPallet);
				Thread.sleep(Constants.long_sleep);

				rdthandler.typeKey("TAB");
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + ContainerID);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (Site.equals("6868")) {
			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);
				System.out.println("Site is Thorncliff");
				Thread.sleep(Constants.long_sleep);
				String clientID = movetaskDB.getclientID(ContainerID);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndTab(clientID);
				Thread.sleep(Constants.long_sleep);
				String toPallet = rdthandler.randompalletforRePack();
				Thread.sleep(Constants.long_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndEnter(toPallet);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + ContainerID);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else if (Site.equals("3641") || Site.equals("3366")) {
			try {

				validationHandler.validateContainerId1(ContainerID);
				Thread.sleep(2000);
				System.out.println("Site is Westfield");
				String toPallet = rdthandler.randompallet_975forRePack();
				Thread.sleep(Constants.long_sleep);
				System.out.println("toPallet" + toPallet);
				rdthandler.typeStringAndTab(toPallet);
				Thread.sleep(Constants.long_sleep);
				String clientID = movetaskDB.getclientID(ContainerID);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(clientID);
				System.out.println("ClientID" + clientID);
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(ContainerID);
				Thread.sleep(Constants.long_sleep);
				System.out.println("Container_id is" + ContainerID);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Assert.fail(Constants.INVALID_SITE);
		}

	}

	@Given("^Perform Repacking using URN \"([^\"]*)\"$")
	public void Repack_Hemel(String ContainerID) throws Throwable {
		System.out.println("site is Hemel");
		try {

			validationHandler.validateContainerId1(ContainerID);
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
			rdthandler.typeString(ContainerID);
			Thread.sleep(Constants.short_sleep);
			System.out.println("URN is" + ContainerID);
			Thread.sleep(Constants.short_sleep);
			String toPallet = rdthandler.randompallet_HemelforRePack();
			Thread.sleep(Constants.short_sleep);
			System.out.println("toPallet" + toPallet);
			rdthandler.typeStringAndTab(toPallet);
			rdthandler.typeKey("TAB");

			rdthandler.typeStringAndEnter("N");
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("^Select Repacking for tote consolidation$")
	public void CompleteRepackingTC()
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.RepackingTC();

	}

	// fULL container repack

	@Given("^Perform Repacking using Site \"([^\"]*)\",To_ContainerID \"([^\"]*)\", From_ContainerID \"([^\"]*)\"$")
	public void Repack_With_Site_Id_Pallet_Container(String Site, String To_ContainerID, String From_ContainerID)
			throws Throwable {

		if (Site.equals("5665") || Site.equals("5649") || Site.equals("7401") || Site.equals("5885")) {
			try {

				validationHandler.validateContainerId1(From_ContainerID);
				Thread.sleep(2000);
				System.out.println("Site is " + Site);
				Thread.sleep(Constants.short_sleep);
				context.setContainerId(From_ContainerID);
				System.out.println("to_containerid" + To_ContainerID);
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");
				Thread.sleep(1000);
				if (rdthandler.getScreenName().equals("QryCnfEnt")) {
					if (To_ContainerID.length() != 0) {

						rdthandler.typeString(To_ContainerID);
						Thread.sleep(1000);

					}

					context.setPalletID(To_ContainerID);
					Thread.sleep(Constants.long_sleep);
					rdthandler.changeKeyboardType();
					Thread.sleep(1000);
					rdthandler.typeKey("F4");
					Thread.sleep(1000);
					rdthandler.typeKey("TAB");
					rdthandler.typeKey("TAB");
					Thread.sleep(1000);
					if (From_ContainerID.length() != 0) {

						rdthandler.typeStringAndEnter(From_ContainerID);
						Thread.sleep(1000);

					} else {
						rdthandler.typeKey("ENTER");
					}
				}

				Thread.sleep(Constants.long_sleep);
				rdthandler.ValidateToteConsolidation();
				Thread.sleep(Constants.short_sleep);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Assert.fail(Constants.INVALID_SITE);
		}

	}

	// Partial Repack

	@Given("^Perform PartialRepacking using Site \"([^\"]*)\",To_ContainerID \"([^\"]*)\", From_ContainerID \"([^\"]*)\",UPC \"([^\"]*)\",Qty \"([^\"]*)\"$")
	public void Partial_Repack_With_Site_Id_Pallet_Container_upc_qty(String Site, String To_ContainerID,
			String From_ContainerID, String UPC, String QTY) throws Throwable {

		if (Site.equals("5665") || Site.equals("5649") || Site.equals("7401") || Site.equals("5885")) {
			try {

				validationHandler.validateContainerId1(From_ContainerID);
				Thread.sleep(2000);
				System.out.println("Site is " + Site);
				Thread.sleep(Constants.short_sleep);
				// context.setContainerId(ContainerID);
				context.setContainerId(From_ContainerID);
				System.out.println("to_containerid" + To_ContainerID);
				if (rdthandler.getScreenName().equals("QryCnfEnt")) {
					if (UPC.length() != 0) {
						rdthandler.typeString(UPC);
						Thread.sleep(1000);
						// rdthandler.typeKey("TAB");
					} else {
						rdthandler.typeKey("TAB");
					}
					Thread.sleep(1000);
					if (QTY.length() != 0) {
						rdthandler.typeString(QTY);
						Thread.sleep(1000);
						if (QTY.length() != 3)
							rdthandler.typeKey("TAB");
					} else {
						rdthandler.typeKey("TAB");
					}

					Thread.sleep(1000);
					if (To_ContainerID.length() != 0) {

						rdthandler.typeString(To_ContainerID);
						Thread.sleep(1000);

					}

					context.setPalletID(To_ContainerID);
					Thread.sleep(Constants.long_sleep);
					rdthandler.changeKeyboardType();
					Thread.sleep(1000);
					rdthandler.typeKey("F4");
					Thread.sleep(1000);

					rdthandler.typeKey("TAB");
					rdthandler.typeKey("TAB");
					Thread.sleep(1000);
					if (From_ContainerID.length() != 0) {

						rdthandler.typeStringAndEnter(From_ContainerID);
						Thread.sleep(1000);

					} else {
						rdthandler.typeKey("ENTER");
					}
					// rdthandler.typeStringAndEnter(From_ContainerID);
					Thread.sleep(Constants.long_sleep);
				}
				rdthandler.ValidateToteConsolidation();
				Thread.sleep(Constants.short_sleep);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Assert.fail(Constants.INVALID_SITE);
		}

	}

	@And("^Do Data Validation after Repack$")
	public void dataValidationAfterRepack() throws Throwable {
		Thread.sleep(2000);
		validationHandler.validateContainerafterRepack(context.getPalletID());

	}

	@And("^Perform PickException for \"([^\"]*)\", \"([^\"]*)\", Qty \"([^\"]*)\",Option \"([^\"]*)\" for \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void NDCPick_exception(String Site, String inputupc, String qty, String option, String InputType,
			String value) throws Throwable {
		System.out.println("site is " + Site);

		if (InputType.equals("ListID")) {
			context.setListID(value);
			context.setInputupc(inputupc);
			context.setQty(qty);
			context.setOption(option);

		} else if (InputType.equals("Order")) {
			context.setOrderId(value);
			context.setInputupc(inputupc);
			context.setQty(qty);
			context.setOption(option);
		} else if (InputType.equals("Consignment")) {
			context.setConsignmentID(value);
			context.setInputupc(inputupc);
			context.setQty(qty);
			context.setOption(option);
			// Consignment spelling was changed
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
		if (Site.equals("5885") && InputType.equals("ListID")) {
			// String list_id = value;
			TrolleyPickingusingListforPickException(context.getInputupc(), context.getQty(), context.getOption(),
					context.getListID());
		} else if (Site.equals("5885") && InputType.equals("Order")) {
			TrolleyPickingusingOrderforPickException(context.getInputupc(), context.getQty(), context.getOption(),
					context.getOrderId());
		} else if (Site.equals("5885") && InputType.equals("Consignment")) {
			TrolleyPickingusingConsignmentforPickException(context.getInputupc(), context.getQty(), context.getOption(),
					context.getConsignmentID());
		} else {
			System.out.println("Invalid Input Entered");
			rdthandler.logout();
			Assert.fail();
		}
	}

	public void TrolleyPickingusingListforPickException(String inputupc, String qty, String Option, String List_id)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		inputupc.trim();
		qty.trim();
		String upcarr[] = inputupc.split(" ");
		String qtyarr[] = qty.split(" ");
		ArrayList<String> upcList = new ArrayList<String>(Arrays.asList(upcarr));
		ArrayList<String> qtyList = new ArrayList<String>(Arrays.asList(qtyarr));
		rdthandler.typeString(List_id);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		System.out.println("List_id Entered");
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
			rdthandler.typeStringAndEnter(Constants.PRINTER);
			Thread.sleep(Constants.short_sleep);
			if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.short_sleep);
			}

		}

		// String orderID = movetaskDB.getTaskIDfromListID(List_id);
		ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(List_id);
		Thread.sleep(Constants.long_sleep);
		// ArrayList<String> To_containers = new ArrayList<String>();
		for (int i = 0; i < SKUList.stream().count(); i++) {
			Thread.sleep(Constants.medium_sleep);
			System.out.println("SkuCon");
			// To_containers = movetaskDB.getToContainer_WitListID(List_id);
			String upc = skuDB.getSupplierSKU(SKUList.get(i));

			if (upcList.contains(upc)) {
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("TAB");
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeString(qtyList.get(upcList.indexOf(upc)));
				Thread.sleep(Constants.short_sleep);
				rdthandler.typeKey("ENTER");
				Thread.sleep(Constants.long_sleep);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckQtyDwn")) {
					rdthandler.typeStringAndEnter(Option);
					Thread.sleep(Constants.short_sleep);
				}
				Thread.sleep(Constants.long_sleep);
				if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
					rdthandler.typeStringAndEnter(Constants.PRINTER);
					Thread.sleep(Constants.short_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.short_sleep);
					}
				}

				if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					Thread.sleep(Constants.short_sleep);
					String container = movetaskDB.getContainerid(Slot, List_id);
					Thread.sleep(Constants.long_sleep);
					System.out.println("Container_id is" + container);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
						rdthandler.typeStringAndEnter(Constants.PRINTER);
						Thread.sleep(Constants.short_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
							rdthandler.typeKey("ENTER");
							Thread.sleep(Constants.short_sleep);
						}
					}
				}
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}
			} else {
				Thread.sleep(Constants.long_sleep);
				rdthandler.typeStringAndEnter(upc);
				Thread.sleep(Constants.short_sleep);
				System.out.println("PCKContainerSCRN ENTRY");
				Thread.sleep(Constants.short_sleep);
				String Slot = rdthandler.getSlotID();
				Thread.sleep(Constants.medium_sleep);
				System.out.println(Slot);
				System.out.println("PCKContainerSCRN ");
				Thread.sleep(Constants.medium_sleep);
				rdthandler.typeKey("ENTER");
				// String container = movetaskDB.getContainerid(Slot, List_id);
				// Thread.sleep(Constants.long_sleep);
				// System.out.println("Container_id is"+container);
				if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
					System.out.println("Check String");

					String location = rdthandler.getLocation();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(location);
					String chkstring = locationDB.getCheckString(location);
					rdthandler.typeString(chkstring);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.medium_sleep);
				}

				else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

					System.out.println("PCKContainerSCRN END ");
					Thread.sleep(Constants.short_sleep);
					String container = movetaskDB.getContainerid(Slot, List_id);
					Thread.sleep(Constants.long_sleep);
					System.out.println("Container_id is" + container);
					rdthandler.typeString(container);
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
						rdthandler.typeStringAndEnter(Constants.PRINTER);
						Thread.sleep(Constants.short_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
							rdthandler.typeKey("ENTER");
							Thread.sleep(Constants.short_sleep);
						}
					}
				}

			}

		}
		type_chkString();
		// rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);

	}

	public void TrolleyPickingusingOrderforPickException(String inputupc, String qty, String Option, String OrderID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		inputupc.trim();
		qty.trim();
		String upcarr[] = inputupc.split(" ");
		String qtyarr[] = qty.split(" ");
		ArrayList<String> upcList = new ArrayList<String>(Arrays.asList(upcarr));
		ArrayList<String> qtyList = new ArrayList<String>(Arrays.asList(qtyarr));
		Thread.sleep(Constants.long_sleep);
		ArrayList<String> ListIDlist = movetaskDB.get_ListIDs(OrderID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
				rdthandler.typeStringAndEnter(Constants.PRINTER);
				Thread.sleep(Constants.short_sleep);
				if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
				}

			}

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));

				if (upcList.contains(upc)) {
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					System.out.println("PCKContainerSCRN ENTRY");
					String Slot = rdthandler.getSlotID();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(Slot);
					System.out.println("PCKContainerSCRN ");
					Thread.sleep(Constants.medium_sleep);
					rdthandler.typeKey("TAB");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(qtyList.get(upcList.indexOf(upc)));
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("PckQtyDwn")) {
						rdthandler.typeStringAndEnter(Option);
						Thread.sleep(Constants.short_sleep);
					}
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
						rdthandler.typeStringAndEnter(Constants.PRINTER);
						Thread.sleep(Constants.short_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
							rdthandler.typeKey("ENTER");
							Thread.sleep(Constants.short_sleep);
						}
					}

					if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

						Thread.sleep(Constants.short_sleep);
						String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
						Thread.sleep(Constants.long_sleep);
						System.out.println("Container_id is" + container);
						rdthandler.typeString(container);
						Thread.sleep(Constants.short_sleep);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.long_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
							rdthandler.typeStringAndEnter(Constants.PRINTER);
							Thread.sleep(Constants.short_sleep);
							if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
								rdthandler.typeKey("ENTER");
								Thread.sleep(Constants.short_sleep);
							}
						}
					} else if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.short_sleep);
						System.out.println("Check String");

						String location = rdthandler.getLocation();
						Thread.sleep(Constants.medium_sleep);
						System.out.println(location);
						String chkstring = locationDB.getCheckString(location);
						rdthandler.typeString(chkstring);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.medium_sleep);
					}

				} else {
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					System.out.println("PCKContainerSCRN ENTRY");
					Thread.sleep(Constants.short_sleep);
					String Slot = rdthandler.getSlotID();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(Slot);
					System.out.println("PCKContainerSCRN ");
					Thread.sleep(Constants.medium_sleep);
					rdthandler.typeKey("ENTER");
					// String container = movetaskDB.getContainerid(Slot,
					// ListIDlist.get(z));
					// Thread.sleep(Constants.long_sleep);
					// System.out.println("Container_id is"+container);
					if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.short_sleep);
						System.out.println("Check String");

						String location = rdthandler.getLocation();
						Thread.sleep(Constants.medium_sleep);
						System.out.println(location);
						String chkstring = locationDB.getCheckString(location);
						rdthandler.typeString(chkstring);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.medium_sleep);
					}

					else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

						System.out.println("PCKContainerSCRN END ");
						Thread.sleep(Constants.short_sleep);
						String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
						Thread.sleep(Constants.long_sleep);
						System.out.println("Container_id is" + container);
						rdthandler.typeString(container);
						Thread.sleep(Constants.short_sleep);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.long_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
							rdthandler.typeStringAndEnter(Constants.PRINTER);
							Thread.sleep(Constants.short_sleep);
							if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
								rdthandler.typeKey("ENTER");
								Thread.sleep(Constants.short_sleep);
							}
						}
					}

				}

			}
			type_chkString();
			// rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
		}
	}

	public void TrolleyPickingusingConsignmentforPickException(String inputupc, String qty, String Option,
			String ConsignmentID)
			throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException {
		inputupc.trim();
		qty.trim();
		String upcarr[] = inputupc.split(" ");
		String qtyarr[] = qty.split(" ");
		ArrayList<String> upcList = new ArrayList<String>(Arrays.asList(upcarr));
		ArrayList<String> qtyList = new ArrayList<String>(Arrays.asList(qtyarr));
		ArrayList<String> ListIDlist = movetaskDB.getListIDs_Consignment1(ConsignmentID);
		Thread.sleep(Constants.long_sleep);
		for (int z = 0; z < ListIDlist.stream().count(); z++) {
			System.out.println("List Size - " + ListIDlist.size());
			System.out.println("i confirming" + z);
			Thread.sleep(Constants.long_sleep);
			rdthandler.typeString(ListIDlist.get(z));
			Thread.sleep(Constants.short_sleep);
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			System.out.println("List_id Entered");
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.long_sleep);
			if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
				rdthandler.typeStringAndEnter(Constants.PRINTER);
				Thread.sleep(Constants.short_sleep);
				if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.short_sleep);
				}

			}

			ArrayList<String> SKUList = movetaskDB.getSkuIDList_ToLIstID(ListIDlist.get(z));
			Thread.sleep(Constants.long_sleep);
			// ArrayList<String> To_containers = new ArrayList<String>();
			for (int i = 0; i < SKUList.stream().count(); i++) {
				Thread.sleep(Constants.medium_sleep);
				System.out.println("SkuCon");
				// To_containers = movetaskDB.getToContainer_WitListID(List_id);
				String upc = skuDB.getSupplierSKU(SKUList.get(i));

				if (upcList.contains(upc)) {
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					System.out.println("PCKContainerSCRN ENTRY");
					String Slot = rdthandler.getSlotID();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(Slot);
					System.out.println("PCKContainerSCRN ");
					Thread.sleep(Constants.medium_sleep);
					rdthandler.typeKey("TAB");
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeString(qtyList.get(upcList.indexOf(upc)));
					Thread.sleep(Constants.short_sleep);
					rdthandler.typeKey("ENTER");
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("PckQtyDwn")) {
						rdthandler.typeStringAndEnter(Option);
						Thread.sleep(Constants.short_sleep);
					}
					Thread.sleep(Constants.long_sleep);
					if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
						rdthandler.typeStringAndEnter(Constants.PRINTER);
						Thread.sleep(Constants.short_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
							rdthandler.typeKey("ENTER");
							Thread.sleep(Constants.short_sleep);
						}
					}

					if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

						Thread.sleep(Constants.short_sleep);
						String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
						Thread.sleep(Constants.long_sleep);
						System.out.println("Container_id is" + container);
						rdthandler.typeString(container);
						Thread.sleep(Constants.short_sleep);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.long_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
							rdthandler.typeStringAndEnter(Constants.PRINTER);
							Thread.sleep(Constants.short_sleep);
							if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
								rdthandler.typeKey("ENTER");
								Thread.sleep(Constants.short_sleep);
							}
						}
					}
					if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.short_sleep);
						System.out.println("Check String");

						String location = rdthandler.getLocation();
						Thread.sleep(Constants.medium_sleep);
						System.out.println(location);
						String chkstring = locationDB.getCheckString(location);
						rdthandler.typeString(chkstring);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.medium_sleep);
					}
				} else {
					Thread.sleep(Constants.long_sleep);
					rdthandler.typeStringAndEnter(upc);
					Thread.sleep(Constants.short_sleep);
					System.out.println("PCKContainerSCRN ENTRY");
					Thread.sleep(Constants.short_sleep);
					String Slot = rdthandler.getSlotID();
					Thread.sleep(Constants.medium_sleep);
					System.out.println(Slot);
					System.out.println("PCKContainerSCRN ");
					Thread.sleep(Constants.medium_sleep);
					rdthandler.typeKey("ENTER");
					// String container = movetaskDB.getContainerid(Slot,
					// ListIDlist.get(z));
					// Thread.sleep(Constants.long_sleep);
					// System.out.println("Container_id is"+container);
					if (rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")) {
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.short_sleep);
						System.out.println("Check String");

						String location = rdthandler.getLocation();
						Thread.sleep(Constants.medium_sleep);
						System.out.println(location);
						String chkstring = locationDB.getCheckString(location);
						rdthandler.typeString(chkstring);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.medium_sleep);
					}

					else if (rdthandler.getScreenName().equalsIgnoreCase("TrlSltCnf")) {

						System.out.println("PCKContainerSCRN END ");
						Thread.sleep(Constants.short_sleep);
						String container = movetaskDB.getContainerid(Slot, ListIDlist.get(z));
						Thread.sleep(Constants.long_sleep);
						System.out.println("Container_id is" + container);
						rdthandler.typeString(container);
						Thread.sleep(Constants.short_sleep);
						rdthandler.typeKey("ENTER");
						Thread.sleep(Constants.long_sleep);
						if (rdthandler.getScreenName().equalsIgnoreCase("QuesEnt")) {
							rdthandler.typeStringAndEnter(Constants.PRINTER);
							Thread.sleep(Constants.short_sleep);
							if (rdthandler.getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
								rdthandler.typeKey("ENTER");
								Thread.sleep(Constants.short_sleep);
							}
						}
					}

				}

			}
			type_chkString();
			// rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
		}

	}

//	@Given("^Perform accuracy scanning \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	@Given("^Perform accuracy scanning with BEL \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_accuracy_scanning_with_BEL(String URN, String UPC, String Input, String qty, String confirm)
			throws Throwable {
		// if (itldb.accuracyRecodCheck(URN)) {

		/*
		 * rdthandler.typeStringAndEnter("2835"); Thread.sleep(2000);
		 * rdthandler.typeString(URN); Thread.sleep(2000);
		 * rdthandler.typeKey("TAB"); rdthandler.typeKey("TAB");
		 * Thread.sleep(2000); rdthandler.typeStringAndEnter(station);
		 * Thread.sleep(2000);
		 */
		String[] quantity = qty.split("\\s");
		String[] upc = UPC.split("\\s");
		int count = 0;
		rdthandler.typeStringAndEnter("2835");
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(URN);
		validateAccuracyScanning();
		// Thread.sleep(2000);
		// rdthandler.typeKey("TAB");
		// rdthandler.typeKey("TAB");
		Thread.sleep(1000);
		// rdthandler.typeStringAndEnter(station);
		Thread.sleep(1000);

		for (int i = 0; i < upc.length; i++) {

			for (int j = 1; j <= Integer.parseInt(quantity[i]); j++) {
				count++;
				Thread.sleep(500);
				String BEL = ""; //comment this once the function getBEL is added
//				String BEL = getBEL(URN, upc[i], qty); ---find the code
				rdthandler.typeStringAndEnter(BEL);
				validateAccuracyScanning();
				if (screen.exists("/images/Putty/Accuracyscan/presentationcode.png") != null) {
					System.out.println("inside presentation code");
					rdthandler.typeKey("ENTER");
					// rdthandler.typeKey("TAB");
					// rdthandler.typeKey("TAB");
					// rdthandler.typeKey("TAB");
					// rdthandler.typeKey("TAB");
					// Thread.sleep(1000);
					rdthandler.typeKey("TAB");
					Thread.sleep(1000);
					rdthandler.typeKey("TAB");
					Thread.sleep(1000);

					if (Input.equals("R") || Input.equals("A") || Input.equals("G")) {
						rdthandler.typeStringAndEnter(Input);
					} else {
						System.out.println("Invalid Presentation code");
						// Assert.fail("Invalid Presentation code");
					}
				}
				if (screen.exists("/images/Putty/Accuracyscan/accuracyscanmrgerror.png") != null) {
					System.out.println("inside merge error &&&&&&&&&&///////");
					Thread.sleep(1000);
					// rdthandler.typeStringAndEnter("Y");
					rdthandler.typeStringAndEnter(confirm);
					Thread.sleep(1000);
				}

				/*
				 * if (count % 10 == 0) { System.out.println(count +
				 * "&&&&&&&&&&///////"); //rdthandler.typeKey("ENTER");
				 * Thread.sleep(2000); System.out.println("Inside j loop");
				 * rdthandler.typeKey("ENTER"); Thread.sleep(2000);
				 * rdthandler.typeKey("TAB"); System.out.println(
				 * "divisible by 10$@#$@$@$@$@$@$@$"); Thread.sleep(2000);
				 * rdthandler.typeKey("TAB"); Thread.sleep(2000);
				 * rdthandler.typeKey("TAB");
				 * 
				 * Thread.sleep(2000);
				 * 
				 * rdthandler.typeStringAndEnter(Input); Thread.sleep(2000); }
				 */
			}

		}
		/*
		 * String[] quantity = qty.split("\\s"); String[] upc =
		 * UPC.split("\\s"); for(int i=0; i<UPC.length; i++) {
		 * rdthandler.typeStringAndEnter(upc[i]);
		 * //rdthandler.typeStringAndEnter(quantity[i]); } /*for (int i = 1; i
		 * <=(qty); i++) { rdthandler.typeStringAndEnter(UPC); }
		 */
		/*
		 * for (int i = 1;Integer.parseInt(quantity[i]);i++){
		 * 
		 * }
		 */

		// rdthandler.typeString(UPC);
		// System.out.println("Outside j and i #$@$%@$%#%##%#%#%");

		Thread.sleep(1000);
		rdthandler.typeKey("TAB");
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter("Y");
		// rdthandler.typeStringAndEnter(confirm);
		Thread.sleep(1000);
		// if
		// (screen.exists("/images/Putty/Accuracyscan/accuracyscanmrgerror.png")
		// != null) {
		// System.out.println("**MERGE ERROR**");
		// Thread.sleep(1000);
		// // rdthandler.typeStringAndEnter("Y");
		// rdthandler.typeStringAndEnter(confirm);
		// Thread.sleep(1000);
		// }
		// rdthandler.typeKey("ENTER");

		/*
		 * if (rdthandler.compareErrorScreen("QryCnfEnt")) {
		 * //rdthandler.typeKey("ENTER"); //}
		 */
		/*
		 * if (count < 10) { System.out.println("Inside some if #$%#%#%#%#%#%#%"
		 * ); rdthandler.typeKey("TAB"); rdthandler.typeKey("TAB");
		 * rdthandler.typeKey("TAB"); rdthandler.typeKey("TAB");
		 * Thread.sleep(2000); rdthandler.typeKey("TAB"); Thread.sleep(2000);
		 * rdthandler.typeStringAndEnter(Input); }
		 */
		/*
		 * if (rdthandler.compareErrorScreen("MrgPrgMsg")) {
		 * rdthandler.typeKey("ENTER"); rdthandler.typeKey("TAB");
		 * rdthandler.typeKey("TAB"); rdthandler.typeKey("TAB");
		 * rdthandler.typeKey("TAB"); Thread.sleep(2000);
		 * rdthandler.typeKey("TAB"); Thread.sleep(2000);
		 * 
		 * rdthandler.typeStringAndEnter(Input); }
		 */
		// }
		if (screen.exists("/images/Putty/Accuracyscan/presentationcode.png") != null) {
			System.out.println("**PRESENTATION CODE**");
			Thread.sleep(1000);
			// System.out.println("inside presentation code");
			rdthandler.typeKey("ENTER");
			Thread.sleep(3000);
			// rdthandler.typeKey("TAB");
			// Thread.sleep(2000);
			// rdthandler.typeKey("TAB");
			// Thread.sleep(2000);
			// rdthandler.typeKey("TAB");
			// Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			Thread.sleep(1000);

			if (Input.equals("R") || Input.equals("A") || Input.equals("G")) {
				rdthandler.typeStringAndEnter(Input);
			} else {
				System.out.println("Invalid Presentation code");
				Assert.fail("Invalid Presentation code");
			}
		}
		if (screen.exists("/images/Putty/Accuracyscan/accuracyscanmrgerror.png") != null) {
			System.out.println("++MERGE ERROR++");
			Thread.sleep(1000);
			// rdthandler.typeStringAndEnter("Y");
			rdthandler.typeStringAndEnter(confirm);
			Thread.sleep(1000);
		}
		/*
		 * if (rdthandler.compareErrorScreen("MrgPrgQry")) {
		 * rdthandler.typeStringAndEnter("Y");
		 * 
		 * }
		 */
		if (screen.exists("/images/Putty/Accuracyscan/Accuracyimage.png") != null) {
			System.out.println("inside accuracy image &&&&&&&&&&///////");
			Thread.sleep(1000);
			rdthandler.typeKey("ENTER");
			/*
			 * Thread.sleep(2000); rdthandler.typeStringAndEnter("Y");
			 * Thread.sleep(2000);
			 */
			if (screen.exists("/images/Putty/Accuracyscan/presentationcode.png") != null) {
				System.out.println("inside present image &&&&&&&&&&///////");
				Thread.sleep(1000);
				// System.out.println("inside presentation code");
				rdthandler.typeKey("ENTER");
				Thread.sleep(1000);
				// rdthandler.typeKey("TAB");
				// Thread.sleep(2000);
				// rdthandler.typeKey("TAB");
				// Thread.sleep(2000);
				// rdthandler.typeKey("TAB");
				// Thread.sleep(2000);
				// rdthandler.typeKey("TAB");
				// Thread.sleep(1000);
				// rdthandler.typeKey("TAB");
				// Thread.sleep(1000);
				rdthandler.typeKey("TAB");
				Thread.sleep(1000);
				rdthandler.typeKey("TAB");
				Thread.sleep(1000);
				if (Input.equals("R") || Input.equals("A") || Input.equals("G")) {
					rdthandler.typeStringAndEnter(Input);
				} else {
					System.out.println("Invalid Presentation code");
					Assert.fail("Invalid Presentation code");
				}
			}
			// rdthandler.typeStringAndEnter(confirm);
		}

		/*
		 * if (rdthandler.compareErrorScreen("AccuScan")){
		 * rdthandler.typeKey("ENTER"); Thread.sleep(2000);
		 * rdthandler.typeStringAndEnter("Y");
		 * 
		 * }
		 */
		/*
		 * if (rdthandler.compareErrorScreen("MrgPrgmMsg")) {
		 * rdthandler.typeStringAndEnter("Y"); }
		 */
		// } //else {
		// System.out.println("invalid container for accuracy scanning");

	}

	public void validateAccuracyScanning() throws ClassNotFoundException, SQLException {

		if (screen.exists("/images/Putty/Accuracyscan/AccuScanningExistingURN") != null) {
			System.out.println("URN Exists");
			// System.exit(0);
			Assert.fail("URN Exists");
		} else if (screen.exists("/images/Putty/Accuracyscan/AccuScanningInvalidUPC") != null) {
			System.out.println("Unexpected UPC");
			// System.exit(0);
			Assert.fail("Unexpected UPC");
		} else if (screen.exists("/images/Putty/Accuracyscan/AccuScanningNoUpc") != null) {
			System.out.println("No UPC Entered");
			Assert.fail("No UPC Entered");
		} else if (screen.exists("/images/Putty/Accuracyscan/AccuScanningUPCInvalid") != null) {
			System.out.println("Invalid UPC");
			Assert.fail("Invalid UPC");
		}
		// else {
		// System.out.println("Quantity not defaulted to 1");
		// }

	}

}

package com.jda.wms.functions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.db.PreAdviceHeaderDB;
import com.jda.wms.db.UPIReceiptHeaderDB;
import com.jda.wms.handlers.DataHandler;
import com.jda.wms.handlers.RDTHandler;
import com.jda.wms.handlers.ValidationHandler;
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
public class RDTReceivingFunctions {
	private RDTHandler rdthandler;
	private DataHandler dataHandler;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private OrderLineDB orderlineDB;
	private static Context context;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private ValidationHandler validationHandler;
	private SiteConstants siteConstants;

	@Inject
	public RDTReceivingFunctions(RDTHandler rdthandler, UPIReceiptHeaderDB upiReceiptHeaderDB, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB, StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			DataHandler datahandler, OrderLineDB orderlineDB, ValidationHandler validationHandler,
			SiteConstants siteConstants) {
		this.rdthandler = rdthandler;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.context = context;
		this.orderlineDB = orderlineDB;
		this.dataHandler = datahandler;
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.validationHandler = validationHandler;
		this.siteConstants = siteConstants;
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

	/**
	 * Blind Receiving with one URN and one UPC as input
	 * 
	 * @param Site
	 * @param URN
	 * @param UPC
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	@Given("^Do Blind Receiving with URN and UPC \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllBlindReceiving(String site, String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiCompleteStatus(URN);
		// validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		context.setupc(UPC);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeBlindReceivingStoke(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeBlindReceivingWestthurrock(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Swindon)) {
			completeBlindReceivingSwindon(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Hemel)) {
			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
			completeBlindReceivingWelham(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeBlindReceivingThorncliffe(context.getPalletID(), context.getupc());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	/**
	 * Blind Receiving for one pallet
	 * 
	 * @param Site
	 * @param URN
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Do Blind Receiving for pallet with URN \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllBlindReceivingForUrn(String site, String urn)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(urn);
		context.setPalletID(urn);
		// rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals("Hemel")) {
			completeAllBlindReceiving(site, context.getPalletID(), context.getupc());
		} else {
			urn = context.getPalletID();
			ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(urn);
			validationHandler.validateUpiReleaseStatus(urn);
			for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
				String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
				Thread.sleep(1000);
				completeAllBlindReceiving(site, urn, upc);
			}
			validationHandler.validateRdtScreen("RcvBli");
		}
	}

	/**
	 * Blind Receiving for ASN
	 * 
	 * @param Site
	 * @param ASN
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Do Blind Receiving for ASN \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllBlindReceivingForASN(String site, String asn)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		// rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		ArrayList<String> URNList = upiReceiptHeaderDB.getUrnList(asn);
		for (int i = 0; i < URNList.stream().distinct().count(); i++) {
			validationHandler.validateUpiReleaseStatus(URNList.get(i));
			if (site.equals("Hemel")) {
				completeAllBlindReceivingForUrn(site, URNList.get(i));
			} else {
				completeAllBlindReceivingForUrn(site, URNList.get(i));
			}
		}
	}

	/**
	 * UPI receiving for Pallet
	 * 
	 * @param Site
	 * @param URN
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Do UPI Receiving with Pallet \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeUpiReceiving(String site, String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		rdthandler.RecvMnu("UPI Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeUPIReceivingStoke(context.getPalletID());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeUPIReceivingWestthurrock(context.getPalletID());
		} else if (site.equals(siteConstants.Swindon)) {
			completeUPIReceivingSwindon(context.getPalletID());
		} else if (site.equals("5542")) {
			completeUPIReceivingHemel(context.getPalletID());
		} else if (site.equals(siteConstants.Welham)) {
			completeUPIReceivingWelham(context.getPalletID());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeUPIReceivingLongeaton(context.getPalletID());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeUPIReceivingThorncliffe(context.getPalletID());
		} else if (site.equals(siteConstants.Hydepark)) {
			completeUPIReceivingHydepark(context.getPalletID());
		} else if (site.equals(siteConstants.WestField)) {
			completeUPIReceivingWestfield(context.getPalletID());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	/**
	 * PreAdvice Receiving with pallet and BEL
	 * 
	 * @param Site
	 * @param URN
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Do PreAdvice Receiving with Pallet or BEL \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllPreAdviceReceiving(String site, String urn)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		Thread.sleep(2000);
		context.setPalletID(urn);
		rdthandler.RecvMnu("Pre-Adv Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			validationHandler.validateUpiReleaseStatus(urn);
			completePreAdviceReceivingStoke(context.getPalletID());
		} else if (site.equals(siteConstants.WestThurrock)) {
			validationHandler.validateUpiReleaseStatus(urn);
			completePreAdviceReceivingWestthurrock(context.getPalletID());
		} else if (site.equals(siteConstants.Swindon)) {
			validationHandler.validateUpiReleaseStatus(urn);
			completePreAdviceReceivingSwindon(context.getPalletID());
		} else if (site.equals(siteConstants.Welham)) {
			validationHandler.validateUpiReleaseStatus(urn);
			completePreAdviceReceivingWelham(context.getPalletID());
		} else if (site.equals(siteConstants.Longeaton)) {
			completePreAdviceReceivingLongeaton(context.getPalletID());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completePreAdviceReceivingThorncliffe(context.getPalletID());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	/**
	 * Pre advice receiving FSV with Pallet and BEL along with the UPC.
	 * 
	 * @param Site
	 * @param preAdviceId
	 * @param URN
	 * @param BEL
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Given("^Do PreAdvice Receiving with Pallet and BEL \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllPreAdviceReceiving(String site, String preAdviceId, String URN, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validatePOReleaseStatus(preAdviceId);
		Thread.sleep(2000);
		context.setPalletID(URN);
		rdthandler.RecvMnu("Pre-Adv Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completePreAdviceReceivingStokeFsv(context.getPalletID(), BEL);
		} else if (site.equals(siteConstants.WestThurrock)) {
			completePreAdviceReceivingWestthurrockFsv(context.getPalletID(), BEL);
		} else if (site.equals(siteConstants.Swindon)) {
			completePreAdviceReceivingSwindonFsv(context.getPalletID(), BEL);
		} else if (site.equals(siteConstants.Welham)) {
			completePreAdviceReceivingWelhamFsv(context.getPalletID(), BEL);
		} else if (site.equals(siteConstants.Longeaton)) {
			completePreAdviceReceivingLongeatonFsv(context.getPalletID(), BEL);
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completePreAdviceReceivingThorncliffeFsv(context.getPalletID(), BEL);
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	/**
	 * GS-128 Receiving for HEMEL alone.
	 * 
	 * @param pallet_ID
	 * @throws Throwable
	 */
	@And("^Do GS128 Receiving with Pallet \"([^\"]*)\"$")
	public void completeGS128Receiving(String pallet_ID) throws Throwable {
		context.setPalletID(pallet_ID);
		String urn = context.getPalletID();
		context.setOrderId(orderlineDB.getOrderWithPallet(urn));
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet_ID);
		validationHandler.validateUpiReleaseStatus(urn);
		rdthandler.RecvMnu("GSI-128 Receive");
		rdthandler.typeString(pallet_ID);
		String ToPallet = dataHandler.randomPallet();
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(ToPallet);
		Thread.sleep(2000);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getUpc(SKUList.get(i)));
			Thread.sleep(2000);
			rdthandler.typeqty(upiReceiptHeaderDB.getQty(urn, SKUList.get(i)));
			Thread.sleep(2000);
			// rdthandler.typeKey("TAB");
			// rdthandler.typeStringAndTab(upiReceiptHeaderDB.getUpc(SKUList.get(i)));
			rdthandler.typeStringAndTab(ToPallet);
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(urn, SKUList.get(i)));
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
		}
	}

	/**
	 * Validation post Receiving.
	 * 
	 * @throws Throwable
	 */
	@And("^Do Data Validation after Receive$")
	public void dataValidationAfterReceiving() throws Throwable {
		Thread.sleep(2000);
		validationHandler.validateUpiCompleteStatus(context.getPalletID());
	}

	@And("^Do Data Validation after Receive FSV$")
	public void dataValidationAfterReceivingFSV() throws Throwable {
		Thread.sleep(2000);
		validationHandler.validateUpiCompleteStatus(context.getPalletID());
	}

	/**
	 * The below methods are for Blind Receiving at individual Site level
	 * 
	 * @param URN
	 * @param UPC
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public void completeBlindReceivingHemel(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		rdthandler.typeString(URN);
		rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN, SKU));
	}

	public void completeBlindReceivingThorncliffe(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(URN);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.typeKey("ENTER");
		// rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN,SKU));
		// rdthandler.typeStringAndEnter(supplier_id);
	}

	public void completeBlindReceivingLongeaton(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeString(URN);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.typeKey("ENTER");
		// rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN,SKU));
		// rdthandler.typeStringAndEnter(supplier_id);
	}

	public void completeBlindReceivingStoke(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(URN);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN, SKU));
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(supplier_id);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeBlindReceivingWestthurrock(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(URN);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN, SKU));
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(supplier_id);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeBlindReceivingSwindon(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(URN);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getSupplier(URN, SKU));
		Thread.sleep(2000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeBlindReceivingWelham(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		rdthandler.typeStringAndTab(URN);
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
		Thread.sleep(1000);
		rdthandler.typeChar('Y');
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplier(URN, SKU));
		Thread.sleep(3000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	/**
	 * The below set of Methods are for UPI receiving at individual site level.
	 * 
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public void completeUPIReceivingStoke(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingWestthurrock(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingSwindon(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingWelham(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);// changed to NDC
																// from RDC
																// after
																// consolidation
																// testing
		Thread.sleep(2000);
	}

	public void completeUPIReceivingHemel(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		rdthandler.typeKey("ENTER");
	}

	public void completeUPIReceivingThrorncliffe(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_XDOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingLongeaton(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_XDOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingThorncliffe(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		Thread.sleep(2000);
		rdthandler.typeStringAndEnter(Constants.RDC_XDOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeUPIReceivingHydepark(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		rdthandler.typeKey("ENTER");
		rdthandler.typeKey("ENTER");
	}

	public void completeUPIReceivingWestfield(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeStringAndEnter(pallet);
		rdthandler.typeKey("ENTER");
		rdthandler.typeKey("ENTER");
	}

	/**
	 * The below set of methods are for Pre advice receiving at Individual site
	 * level
	 * 
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void completePreAdviceReceivingStoke(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			Thread.sleep(2000);
			String tag_id = dataHandler.getNineDigitRandomNumber();
			rdthandler.typeStringAndEnter(tag_id);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	public void completePreAdviceReceivingWestthurrock(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			Thread.sleep(2000);
			/*
			 * rdthandler.typeStringAndEnter(upc); Thread.sleep(2000);
			 * rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			 * Thread.sleep(2000); String
			 * tag_id=dataHandler.getNineDigitRandomNumber();
			 * rdthandler.typeStringAndEnter(tag_id); Thread.sleep(2000);
			 */
			/* Interchanged during COnsolidation Testing */
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			Thread.sleep(2000);
			String tag_id = dataHandler.getNineDigitRandomNumber();
			rdthandler.typeStringAndEnter(tag_id);
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	public void completePreAdviceReceivingSwindon(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			Thread.sleep(2000);
			String tag_id = dataHandler.getNineDigitRandomNumber();
			rdthandler.typeStringAndEnter(tag_id);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	public void completePreAdviceReceivingWelham(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			String tag_id = dataHandler.getNineDigitRandomNumber();
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(tag_id);
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	public void completePreAdviceReceivingLongeaton(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(Constants.RDC_DOCK_LOC);
			Thread.sleep(2000);
			String tag_id = dataHandler.getNineDigitRandomNumber();
			rdthandler.typeStringAndEnter(tag_id);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	public void completePreAdviceReceivingThorncliffe(String pallet)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet);
		rdthandler.typeStringAndEnter(pallet);
		for (int i = 0; i < SKUList.stream().distinct().count(); i++) {
			Thread.sleep(2000);
			String upc = upiReceiptHeaderDB.getUpc(SKUList.get(i));
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(upc);
			Thread.sleep(2000);
			rdthandler.typeStringAndTab(Constants.RDC_DOCK_LOC);
			Thread.sleep(2000);
			String tag_id = dataHandler.getNineDigitRandomNumber();
			rdthandler.typeStringAndEnter(tag_id);
			Thread.sleep(2000);
			if (i == 0) {
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			} else {
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
			}
		}
	}

	/**
	 * The below methods are for FSV pre advice receiving at individual site
	 * level.
	 * 
	 * @param pallet
	 * @param BEL
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void completePreAdviceReceivingSwindonFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(BEL);
		Thread.sleep(1000);
		rdthandler.typeTab();
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
	}

	public void completePreAdviceReceivingStokeFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(BEL);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
	}

	public void completePreAdviceReceivingWestthurrockFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(BEL);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
	}

	public void completePreAdviceReceivingWelhamFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(BEL);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
		Thread.sleep(2000);
	}

	public void completePreAdviceReceivingThorncliffeFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeString(BEL);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
	}

	public void completePreAdviceReceivingLongeatonFsv(String pallet, String BEL)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		Thread.sleep(1000);
		rdthandler.typeString(BEL);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
		rdthandler.typeKey("ENTER");
	}

	/**
	 * @author Muheshvar S The below code is to perform RMS Receive for Single
	 *         Line
	 * @param site
	 *            id
	 * @param URRN
	 * @param UPC
	 *            or TRL
	 */

	@Given("^Do RMS with URN and UPC \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllRMSReceiving(String site, String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		context.setupc(UPC);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeRMSReceivingStoke(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeRMSReceivingWestthurrock(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Swindon)) {
			completeRMSReceivingSwindon(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Hemel)) {
			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
			// completeRMSReceivingWelham(context.getPalletID(),context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeRMSReceivingThorncliffe(context.getPalletID(), context.getupc());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	/**
	 * @author Muheshvar S The below code is to perform RMS Receive for Multi
	 *         Line
	 * @param site
	 *            id
	 * @param URRN
	 */

	@Given("^Do RMS with URN \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeMultiRMSReceiving(String site, String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeMultiRMSReceivingStoke(context.getPalletID());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeMultiRMSReceivingWestthurrock(context.getPalletID());
		} else if (site.equals(siteConstants.Swindon)) {
			completeMultiRMSReceivingSwindon(context.getPalletID());
		} else if (site.equals(siteConstants.Hemel)) {
			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
			// completeRMSReceivingWelham(context.getPalletID(),context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeMultiRMSReceivingThorncliffe(context.getPalletID());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	public void completeRMSReceivingSwindon(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
		String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
		String Random = rdthandler.randomPalletforRec();
		if (validationHandler.validateUpiPreReceive(URN)) {
			rdthandler.typeStringAndTab(URN);
			Thread.sleep(1000);
		} else {
			rdthandler.typeStringAndEnter(URN);
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
		}
		if (validationHandler.validateisHanging(SKU)) {
			rdthandler.typeString(TRL);
		} else {
			rdthandler.typeStringAndTab(UPC);
		}
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Qty);
		Thread.sleep(1000);
		rdthandler.typeString(Constants.perf_indicator);
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(supplier_id);
		Thread.sleep(2000);
		if (validationHandler.validateisHanging(SKU)) {
			rdthandler.typeStringAndTab(Random);
			Thread.sleep(500);
			rdthandler.typeChar('Y');
		} else {
			rdthandler.typeKey("TAB");
			Thread.sleep(500);
			rdthandler.typeKey("TAB");
		}
		Thread.sleep(500);
		rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
		Thread.sleep(5000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeMultiRMSReceivingSwindon(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKU_List = upiReceiptHeaderDB.getSkuList(URN);
		String Random = rdthandler.randomPalletforRec();
		for (int i = 0; i < SKU_List.size(); i++) {
			String SKU = SKU_List.get(i);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
			String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
			String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
			Thread.sleep(2000);
			if (validationHandler.validateUpiPreReceive(URN)) {
				rdthandler.typeStringAndTab(URN);
				Thread.sleep(1000);
			} else {
				rdthandler.typeStringAndEnter(URN);
				Thread.sleep(2000);
				rdthandler.typeKey("TAB");
			}

			Thread.sleep(2000);
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeString(TRL);
			} else {
				rdthandler.typeStringAndTab(UPC);
			}
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
			Thread.sleep(1000);
			rdthandler.typeString(Constants.perf_indicator);
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getSupplier(URN, SKU));
			Thread.sleep(2000);
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeStringAndTab(Random);
				Thread.sleep(500);
				rdthandler.typeChar('Y');
			} else {
				rdthandler.typeKey("TAB");
				Thread.sleep(500);
				rdthandler.typeKey("TAB");
			}
			Thread.sleep(500);
			rdthandler.typeStringAndEnter(Constants.NDC_DOCK_LOC);
			Thread.sleep(3000);
			rdthandler.typeKey("ENTER");

		}
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeRMSReceivingThorncliffe(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReceive(URN)) {
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeString(TRL);
			} else {
				rdthandler.typeStringAndTab(UPC);
			}
			Thread.sleep(1000);
			Thread.sleep(1000);
			rdthandler.typeString(URN);
			Thread.sleep(1000);

		} else {
			rdthandler.typeKey("TAB");
			rdthandler.typeString(URN);
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(2000);
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeString(TRL);
			} else {
				rdthandler.typeStringAndTab(UPC);
			}
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
		}
		rdthandler.typeStringAndTab(Qty);
		Thread.sleep(1000);
		rdthandler.typeString(Constants.perf_indicator);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		Thread.sleep(2000);
	}

	public void completeMultiRMSReceivingThorncliffe(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKU_List = upiReceiptHeaderDB.getSkuList(URN);
		for (int i = 0; i < SKU_List.size(); i++) {
			String SKU = SKU_List.get(i);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
			String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
			String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
			Thread.sleep(2000);
			if (validationHandler.validateUpiPreReceive(URN)) {
				if (validationHandler.validateisHanging(SKU)) {
					rdthandler.typeString(TRL);
				} else {
					rdthandler.typeStringAndTab(UPC);
				}
				Thread.sleep(1000);
				rdthandler.typeString(URN);
				Thread.sleep(1000);

			} else {
				rdthandler.typeKey("TAB");
				rdthandler.typeString(URN);
				Thread.sleep(1000);
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");
				rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
				Thread.sleep(2000);
				if (validationHandler.validateisHanging(SKU)) {
					rdthandler.typeString(TRL);
				} else {
					rdthandler.typeStringAndTab(UPC);
				}
				Thread.sleep(1000);
				rdthandler.typeKey("TAB");
			}
			rdthandler.typeStringAndTab(Qty);
			Thread.sleep(1000);
			rdthandler.typeString(Constants.perf_indicator);
			Thread.sleep(1000);
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
		}
	}

	public void completeRMSReceivingStoke(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
		if (validationHandler.validateUpiPreReceive(URN)) {
			rdthandler.typeStringAndTab(URN);
			Thread.sleep(1000);
		} else {
			rdthandler.typeStringAndEnter(URN);
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
		}
		if (validationHandler.validateisHanging(SKU)) {
			rdthandler.typeString(TRL);
		} else {
			rdthandler.typeStringAndTab(UPC);
		}
		Thread.sleep(500);
		rdthandler.typeStringAndTab(Qty);
		// rdthandler.typeStringAndTab(Constants.RMS_QTY);
		Thread.sleep(1000);
		rdthandler.typeString(Constants.perf_indicator);
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.typeStringAndEnter(supplier_id);
		Thread.sleep(2000);
		// rdthandler.typeStringAndEnter(supplier_id);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeMultiRMSReceivingStoke(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		ArrayList<String> SKU_List = upiReceiptHeaderDB.getSkuList(URN);
		for (int i = 0; i < SKU_List.size(); i++) {
			String SKU = SKU_List.get(i);
			String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
			String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
			Thread.sleep(2000);
			if (validationHandler.validateUpiPreReceive(URN)) {
				rdthandler.typeStringAndTab(URN);
				Thread.sleep(1000);
			} else {
				rdthandler.typeStringAndEnter(URN);
				Thread.sleep(2000);
				rdthandler.typeKey("TAB");
			}
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeString(TRL);
			} else {
				rdthandler.typeStringAndTab(UPC);
			}
			Thread.sleep(500);
			rdthandler.typeStringAndTab(Qty);
			// rdthandler.typeStringAndTab(Constants.RMS_QTY);
			Thread.sleep(1000);
			rdthandler.typeString(Constants.perf_indicator);
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			Thread.sleep(1000);
			rdthandler.typeStringAndEnter(supplier_id);
			Thread.sleep(2000);
			// rdthandler.typeStringAndEnter(supplier_id);
			validationHandler.validateRdtScreen("RcvBli");
			rdthandler.typeKey("ENTER");
		}
	}

	public void completeRMSReceivingWestthurrock(String URN, String UPC)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String SKU = upiReceiptHeaderDB.getSku(URN);
		String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
		String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
		String Random = rdthandler.randomPalletforRec();
		String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReceive(URN)) {
			rdthandler.typeStringAndTab(URN);
			Thread.sleep(1000);
		} else {
			rdthandler.typeStringAndEnter(URN);
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
		}
		if (validationHandler.validateisHanging(SKU)) {
			rdthandler.typeString(TRL);
		} else {
			rdthandler.typeStringAndTab(UPC);
		}
		rdthandler.typeStringAndTab(Qty);
		Thread.sleep(1000);
		rdthandler.typeString(Constants.perf_indicator);
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		rdthandler.typeString(supplier_id);
		if (validationHandler.validateisHanging(SKU)) {
			rdthandler.typeStringAndEnter(Random);
		} else {
			rdthandler.typeKey("ENTER");
		}
		Thread.sleep(5000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeMultiRMSReceivingWestthurrock(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> SKU_List = upiReceiptHeaderDB.getSkuList(URN);
		for (int i = 0; i < SKU_List.size(); i++) {
			String SKU = SKU_List.get(i);
			String Qty = upiReceiptHeaderDB.getQty(URN, SKU);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			String supplier_id = upiReceiptHeaderDB.getSupplier1(URN, SKU);
			String Random = rdthandler.randomPalletforRec();
			String TRL = "02" + supplier_id.substring(1) + UPC + String.format("%03d", Integer.parseInt(Qty)) + "10";
			Thread.sleep(2000);
			if (validationHandler.validateUpiPreReceive(URN)) {
				rdthandler.typeStringAndTab(URN);
				Thread.sleep(1000);
			} else {
				rdthandler.typeStringAndEnter(URN);
				Thread.sleep(2000);
				rdthandler.typeKey("TAB");
			}
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeString(TRL);
			} else {
				rdthandler.typeStringAndTab(UPC);
			}
			rdthandler.typeStringAndTab(Qty);
			Thread.sleep(1000);
			rdthandler.typeString(Constants.perf_indicator);
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
			rdthandler.typeString(supplier_id);
			if (validationHandler.validateisHanging(SKU)) {
				rdthandler.typeStringAndEnter(Random);
			} else {
				rdthandler.typeKey("ENTER");
			}
			Thread.sleep(5000);
			validationHandler.validateRdtScreen("RcvBli");
			rdthandler.typeKey("ENTER");
		}
	}

	/**
	 * @author Ramsundar The below code is to perform RMS Receive for Multiline
	 * @param site
	 *            id
	 * @param URRN
	 */

	@Given("^Do Blind Receiving with URN \"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllRMSReceiving(String site, String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeRMSReceivingStoke(context.getPalletID());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeRMSReceivingWestthurrock(context.getPalletID());
		} else if (site.equals(siteConstants.Swindon)) {
			completeRMSReceivingSwindon(context.getPalletID());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeRMSReceivingThorncliffe(context.getPalletID());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	public void completeRMSReceivingStoke(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		int i = 0;
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(URN);
		rdthandler.typeStringAndTab(URN);
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
		} else
			rdthandler.typeKey("TAB");
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
		} else
			rdthandler.typeKey("TAB");
		Thread.sleep(500);
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(500);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndEnter(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			Thread.sleep(500);
			rdthandler.typeKey("ENTER");
			i++;
		} else {
			rdthandler.typeKey("ENTER");
		}
		Thread.sleep(1000);
		while (rdthandler.getScreenName().equals("RcvBli") && i < SKUList.size()) {
			Thread.sleep(500);
			rdthandler.typeKey("TAB");
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
			Thread.sleep(1000);
			rdthandler.typeChar('Y');
			rdthandler.typeKey("TAB");
			rdthandler.typeString(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			rdthandler.typeKey("ENTER");
			Thread.sleep(1000);
			rdthandler.typeKey("ENTER");
			i++;
		}
		Thread.sleep(1000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeRMSReceivingWestthurrock(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		int i = 0;
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(URN);
		rdthandler.typeStringAndTab(URN);
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
		} else
			rdthandler.typeKey("TAB");
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
		} else
			rdthandler.typeKey("TAB");
		Thread.sleep(500);
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(500);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeString(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			Thread.sleep(500);
		} else {
			rdthandler.typeKey("TAB");
		}
		if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equals("H")) {
			rdthandler.typeStringAndEnter(dataHandler.getNineDigitRandomNumber());
		} else {
			rdthandler.typeKey("ENTER");
		}
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeKey("ENTER");
			i++;
		}
		Thread.sleep(1000);
		while (rdthandler.getScreenName().equals("RcvBli") && i < SKUList.size()) {
			Thread.sleep(500);
			rdthandler.typeKey("TAB");
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
			Thread.sleep(1000);
			rdthandler.typeChar('Y');
			rdthandler.typeKey("TAB");
			rdthandler.typeString(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			Thread.sleep(1000);
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equals("H")) {
				rdthandler.typeString(dataHandler.getNineDigitRandomNumber());
				Thread.sleep(1000);
			}
			rdthandler.typeKey("ENTER");
			Thread.sleep(1000);
			rdthandler.typeKey("ENTER");
			i++;
		}
		Thread.sleep(1000);
		validationHandler.validateRdtScreen("RcvBli");

	}

	public void completeRMSReceivingThorncliffe(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		int i = 0;
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(URN);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
		} else
			rdthandler.typeKey("TAB");
		Thread.sleep(1000);
		rdthandler.typeString(URN);
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
			i++;
		} else
			rdthandler.typeKey("TAB");
		Thread.sleep(500);
		rdthandler.typeKey("TAB");
		Thread.sleep(500);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(500);
			rdthandler.typeKey("ENTER");
		} else {
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
		}
		Thread.sleep(1000);
		while (rdthandler.getScreenName().equals("RcvBli") && i < SKUList.size()) {
			Thread.sleep(1000);
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
			Thread.sleep(1000);
			rdthandler.typeChar('Y');
			rdthandler.typeKey("ENTER");
			Thread.sleep(1000);
			rdthandler.typeKey("ENTER");
			i++;
		}
		Thread.sleep(1000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public void completeRMSReceivingSwindon(String URN)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		int i = 0;
		ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(URN);
		rdthandler.typeStringAndTab(URN);
		Thread.sleep(2000);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
		} else
			rdthandler.typeKey("TAB");
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
		} else
			rdthandler.typeKey("TAB");
		Thread.sleep(500);
		rdthandler.typeKey("TAB");
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			Thread.sleep(500);
		} else {
			rdthandler.typeKey("TAB");
		}
		if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equals("H")) {
			rdthandler.typeStringAndTab(dataHandler.getNineDigitRandomNumber());
		} else {
			rdthandler.typeKey("Tab");
		}
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(500);
		if (validationHandler.validateUpiPreReleaseStatus(URN)) {
			rdthandler.typeKey("ENTER");
			i++;
		}
		rdthandler.typeKey("ENTER");
		while (rdthandler.getScreenName().equals("RcvBli") && i < SKUList.size()) {
			Thread.sleep(500);
			rdthandler.typeKey("TAB");
			rdthandler.typeString(getUPCOrTRL(URN, SKUList.get(i)));
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equalsIgnoreCase("B"))
				rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKUList.get(i)));
			Thread.sleep(1000);
			rdthandler.typeChar('Y');
			rdthandler.typeString(upiReceiptHeaderDB.getSupplierId(SKUList.get(i)));
			rdthandler.typeKey("TAB");
			if (upiReceiptHeaderDB.getBoxedOrHanging(SKUList.get(i)).equals("H"))
				rdthandler.typeString(dataHandler.getNineDigitRandomNumber());
			rdthandler.typeKey("ENTER");
			Thread.sleep(1000);
			rdthandler.typeKey("ENTER");
			i++;
		}
		Thread.sleep(1000);
		validationHandler.validateRdtScreen("RcvBli");
	}

	public String getUPCOrTRL(String URN, String sku) throws ClassNotFoundException, SQLException {
		if (upiReceiptHeaderDB.getBoxedOrHanging(sku).equalsIgnoreCase("H")) {
			String TRL = null;
			System.out.println(URN);
			System.out.println(sku);
			String upc = upiReceiptHeaderDB.getUpc(sku);
			String qty = upiReceiptHeaderDB.getQty(URN, sku);
			System.out.println(qty);
			String supplier_Id = upiReceiptHeaderDB.getSupplierId(sku);
			qty = String.format("%03d", Integer.parseInt(qty));
			TRL = "02" + supplier_Id.substring(1) + upc + qty + "11";
			return TRL;
		} else {
			String upc = upiReceiptHeaderDB.getUpc(sku);
			return upc;

		}

	}

//	public void completeStockAdjustmentThorncliffe(String UPC, String qnty, String store)
//			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
//
//		rdthandler.typeStringAndTab(UPC);
//		rdthandler.typeKey("TAB");
//		rdthandler.typeStringAndTab(qnty);
//		Thread.sleep(1000);
//		rdthandler.typeString("Y");
//		Thread.sleep(1000);
//
//		rdthandler.typeStringAndTab(Constants.RMS_REASON);
//		Thread.sleep(2000);
//		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
//		Thread.sleep(1000);
//		rdthandler.changeKeyboardType();
//		Thread.sleep(2000);
//		rdthandler.typeKey("F4");
//		Thread.sleep(2000);
//
//		rdthandler.typeKey("TAB");
//		rdthandler.typeKey("TAB");
//		rdthandler.typeKey("TAB");
//		rdthandler.typeStringAndEnter(store);
//	}

	public void completeStockAdjustmentSwindon(String UPC, String qnty, String store)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(qnty);
		Thread.sleep(1000);
		rdthandler.typeString("Y");
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.RMS_REASON);
		Thread.sleep(2000);
		String Supplier = upiReceiptHeaderDB.getSupplierfromupc(UPC);
		Thread.sleep(1000);
		rdthandler.typeString(Supplier);
		Thread.sleep(2000);
		rdthandler.changeKeyboardType();
		Thread.sleep(2000);
		rdthandler.typeKey("F4");
		Thread.sleep(3000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(Constants.RDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(store);
		Thread.sleep(5000);
		rdthandler.typeStringAndTab(dataHandler.getNineDigitRandomNumber());
		rdthandler.typeKey("ENTER"); // to be removed once getboxorhangforupc is added
//		if (upiReceiptHeaderDB.getboxorhangforupc(UPC).equalsIgnoreCase("H")) //add the function getboxorhangforupc{
//			Thread.sleep(2000);
//			rdthandler.typeKey("ENTER");
//		} else {
//			Thread.sleep(1000);
//			rdthandler.typeKey("TAB"); 
//			Thread.sleep(1000);
//			rdthandler.typeStringAndEnter("N");
//		}
		Thread.sleep(2000);

	}

	public void completeStockAdjustmentStoke(String UPC, String qnty, String store)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(qnty);
		Thread.sleep(1000);
		rdthandler.typeString("Y");
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.RDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.typeString(Constants.RMS_REASON);
		Thread.sleep(2000);
		rdthandler.changeKeyboardType();
		Thread.sleep(2000);
		rdthandler.typeKey("F4");
		Thread.sleep(2000);

		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(store);
	}

	public void completeStockAdjustmentWestthurrock(String UPC, String qnty, String store)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeStringAndTab(qnty);
		Thread.sleep(1000);
		rdthandler.typeString("Y");
		Thread.sleep(1000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(1000);

		rdthandler.typeString(Constants.RMS_REASON);
		Thread.sleep(2000);
		rdthandler.changeKeyboardType();
		Thread.sleep(2000);
		rdthandler.typeKey("F4");
		Thread.sleep(2000);
		 String sku = upiReceiptHeaderDB.getskufromUpc(UPC);
		 if (upiReceiptHeaderDB.getboxorhang(sku).equalsIgnoreCase("H")) {
		 rdthandler.typeString(dataHandler.getEightDigitRandomNumber());
		 } else {
		 rdthandler.typeKey("TAB");
		 }
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");

		rdthandler.typeStringAndEnter(store);
	}

	@Given("^Do Stock Adjustment with UPC , Quantity and Store \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeStockAdjustment(String UPC, String qty, String Store, String site)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		validationHandler.validateSite(site);

		context.setupc(UPC);
		context.setqnty(qty);
		context.setstore(Store);

		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
			completeStockAdjustmentStoke(context.getupc(), context.getqnty(), context.getstore());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeStockAdjustmentWestthurrock(context.getupc(), context.getqnty(), context.getstore());
		} else if (site.equals(siteConstants.Swindon)) {
			completeStockAdjustmentSwindon(context.getupc(), context.getqnty(), context.getstore());
		} else if (site.equals(siteConstants.Hemel)) {
//			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
//			completeBlindReceivingWelham(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
//			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeStockAdjustmentThorncliffe(context.getupc(), context.getqnty(), context.getstore());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	public void completeStockAdjustmentThorncliffe(String UPC, String qnty, String store)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		rdthandler.typeStringAndTab(UPC);
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndTab(qnty);
		Thread.sleep(1000);
		rdthandler.typeString("Y");
		Thread.sleep(1000);

		rdthandler.typeStringAndTab(Constants.RMS_REASON);
		Thread.sleep(2000);
		rdthandler.typeStringAndTab(Constants.NDC_DOCK_LOC);
		Thread.sleep(1000);
		rdthandler.changeKeyboardType();
		Thread.sleep(2000);
		rdthandler.typeKey("F4");
		Thread.sleep(2000);

		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeKey("TAB");
		rdthandler.typeStringAndEnter(store);
	}

	@Given("^Do Blind Receiving with over receipt URN and Qnty \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAlloverreceiptBlindReceiving(String site, String URN, String qnty)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		context.setqnty(qnty);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
//			completeBlindReceivingovereceiptStoke(context.getPalletID(), context.getqnty());
		} else if (site.equals(siteConstants.WestThurrock)) {
//			completeBlindReceivingovereceiptWT(context.getPalletID(), context.getqnty());
		} else if (site.equals(siteConstants.Swindon)) {
//			completeBlindReceivingovereceiptSwindon(context.getPalletID(), context.getqnty());
		} else if (site.equals(siteConstants.Hemel)) {
			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
			completeBlindReceivingWelham(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeBlindReceivingoverreceiptThorncliffe(context.getPalletID(), context.getqnty());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	public void completeBlindReceivingoverreceiptThorncliffe(String URN, String qnty)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		ArrayList<String> skulist = upiReceiptHeaderDB.getSkuList(URN);
		int count = 0;
		if (upiReceiptHeaderDB.getStatus(URN).equals("Released")) {

			count = count + 1;
			rdthandler.typeKey("TAB");
			Thread.sleep(3000);
			rdthandler.typeString(URN);
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			rdthandler.typeKey("TAB");
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(3000);
		}
		for (int i = 0; i < skulist.size(); i++) {
			String SKU = skulist.get(i);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
			if ((upiReceiptHeaderDB.getStatus(URN).equals("PreReceived")) && (count == 0)) {

				count = count + 1;
				rdthandler.typeStringAndTab(UPC);
				Thread.sleep(3000);
				rdthandler.typeString(URN);
				Thread.sleep(1000);
			} else {
				rdthandler.typeStringAndTab(UPC);
				Thread.sleep(3000);
				rdthandler.typeKey("TAB");
			}

			rdthandler.typeStringAndTab(qnty);
			Thread.sleep(1000);
			rdthandler.typeString("Y");
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
		}
	}

	@Given("^Do Blind Receiving with upc not in urn URN and UPCnotinURN \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void completeAllupc_not_in_urnBlindReceiving(String site, String URN, String upcnotinurn)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		validationHandler.validateUpiReleaseStatus(URN);
		validationHandler.validateSite(site);
		context.setPalletID(URN);
		context.setupcnotinurn(upcnotinurn);
		rdthandler.RecvMnu("Blind Receive");
		System.out.println("site is " + site);
		if (site.equals(siteConstants.Stoke)) {
//			completeBlindReceivingupcnotinurnStoke(context.getPalletID(), context.getupcnotinurn());
		} else if (site.equals(siteConstants.WestThurrock)) {
			completeBlindReceivingWestthurrock(context.getPalletID(), context.getupcnotinurn());
		} else if (site.equals(siteConstants.Swindon)) {
//			completeBlindReceivingupcnotinurnSwindon(context.getPalletID(), context.getupcnotinurn());
		} else if (site.equals(siteConstants.Hemel)) {
			completeBlindReceivingHemel(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Welham)) {
			completeBlindReceivingWelham(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Longeaton)) {
			completeBlindReceivingLongeaton(context.getPalletID(), context.getupc());
		} else if (site.equals(siteConstants.Thorncliffe)) {
			completeBlindReceivingupcnotinurnThorncliffe(context.getPalletID(), context.getupcnotinurn());
		} else {
			System.out.println("Invalid Site Entered");
		}
	}

	public void completeBlindReceivingupcnotinurnThorncliffe(String URN, String upcnotinurn)
			throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {

		ArrayList<String> skulist = upiReceiptHeaderDB.getSkuList(URN);
		int count = 0;
		rdthandler.typeString(URN);
		Thread.sleep(1000);
		for (int i = 0; i < skulist.size(); i++) {
			if (upiReceiptHeaderDB.getStatus(URN).equals("Released")) {

				rdthandler.typeKey("ENTER");
				Thread.sleep(3000);
				rdthandler.typeKey("TAB");
				Thread.sleep(1000);
			} else {
				System.out.println(upiReceiptHeaderDB.getStatus(URN));
				Thread.sleep(1000);
				rdthandler.typeKey("TAB");
			}

			String SKU = skulist.get(i);
			String supplier_id = upiReceiptHeaderDB.getSupplier(URN, SKU);
			String UPC = upiReceiptHeaderDB.getUpc(SKU);
			System.out.println(upiReceiptHeaderDB.getboxorhang(SKU));
			rdthandler.typeStringAndTab(upcnotinurn);
			Thread.sleep(1000);
			rdthandler.typeStringAndTab(upiReceiptHeaderDB.getQty(URN, SKU));
			Thread.sleep(1000);
			rdthandler.typeChar('Y');
			Thread.sleep(1000);
			rdthandler.typeKey("TAB");
			Thread.sleep(1000);
			rdthandler.typeString(supplier_id);
			Thread.sleep(1000);
			if (count == 0) {
				rdthandler.changeKeyboardType();
				Thread.sleep(2000);
				count = count + 1;
			}
			rdthandler.typeKey("F4");
			Thread.sleep(2000);
			if (upiReceiptHeaderDB.getboxorhang(SKU).equalsIgnoreCase("H")) {
				rdthandler.typeStringAndTab(dataHandler.getNineDigitRandomNumber());
			} else {
				rdthandler.typeKey("TAB");
				Thread.sleep(3000);
			}
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
			rdthandler.typeStringAndEnter(Constants.RDC_DOCK_LOC);
			Thread.sleep(3000);
			validationHandler.validateRdtScreen("RcvBli");
			rdthandler.typeKey("ENTER");
			Thread.sleep(3000);
		}
	}

	 @And("^Do GS128 Receiving with pallet_ID \"([^\"]*)\" and To_pallet \"([^\"]*)\"$")
		public void completeGS128ReceivingwithEAN(String pallet_ID,String To_pallet) 
				throws Throwable {	
			context.setPalletID(pallet_ID);
			String urn = context.getPalletID();
			//context.setOrderId(orderlineDB.getOrderWithPallet(urn));
			ArrayList<String> SKUList = upiReceiptHeaderDB.getSkuList(pallet_ID);
			validationHandler.validateUpiReleaseStatus(urn);
			rdthandler.RecvMnu("GSI-128 Receive");
			rdthandler.typeStringAndEnter(pallet_ID);		
			Thread.sleep(2000);
			for (int i=0 ; i< SKUList.stream().distinct().count(); i++) {
				Thread.sleep(2000);
				rdthandler.typeStringAndTab(upiReceiptHeaderDB.getEAN(SKUList.get(i)));
				Thread.sleep(2000);
				rdthandler.typeqty(upiReceiptHeaderDB.getQty(urn,SKUList.get(i)));
				Thread.sleep(2000);
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");
				Thread.sleep(2000);
				rdthandler.typeStringAndTab(To_pallet);
				Thread.sleep(2000);
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");
				rdthandler.typeKey("TAB");			
				Thread.sleep(2000);
				rdthandler.typeStringAndEnter(Constants.LOCATION_ID);
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				
			}		
		}

	
}

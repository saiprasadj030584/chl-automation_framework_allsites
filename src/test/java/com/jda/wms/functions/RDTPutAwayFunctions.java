package com.jda.wms.functions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.StoreTrackingOrderPickingPage;
import com.jda.wms.config.Constants;
import com.jda.wms.config.SiteConstants;
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
import cucumber.api.java.en.Given;

public class RDTPutAwayFunctions {
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


	@Inject
	public RDTPutAwayFunctions(RDTHandler rdthandler, UPIReceiptHeaderDB upiReceiptHeaderDB, Context context,PreAdviceHeaderDB preAdviceHeaderDB, 
			StoreTrackingOrderPickingPage storeTrackingOrderPickingPage, DataHandler datahandler, OrderLineDB orderlineDB,
			ValidationHandler validationHandler, SiteConstants siteConstants,
			LocationDB locationDB, MoveTaskDB movetaskDB, SkuDB skuDB){
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
	public void beforeTest(){
		System.out.println("**Execution of "+name.getMethodName()+" in RDTFunctions started");
	}
	@After
	public void afterTest(){
		System.out.println("**Execution of "+name.getMethodName()+" in RDTFunctions completed");
	}


	@Rule
	public TestName name= new TestName(); 



	//Puaway Steping Defns//		
	/**
	 * @description Select Putaway for West THurrock
	 * @author Kasinathan.Elavarasa
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	@Given("^Select Normal Putaway$")
	public void SelectPutawayScreen() throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.PutAwayMnu("Normal");
	}	

	/**
	 * @description  Putaway for West THurrock
	 * @author Kasinathan.Elavarasa
	 * @throws InterruptedException
	 * @parm   pallet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	@Given("^Perform Putaway for WestThurrock \"([^\"]*)\"$")
	public void PutawayforWT(String pallet ) throws Throwable {
		context.setPalletID(pallet);
		PutawayforWTwithPallet(context.getPalletID());

	}	
	@Given("^Perform Putaway for WestThurrock to Multiple Pallet$") 
	public void PutawayforWTMultiplePallet(DataTable pallet) throws Throwable {
		for (Map<String, String> data : pallet.asMaps(String.class, String.class)) { 

			String palletid= data.get("Pallet_ID");
			System.out.println("pallet ID IS"+palletid);
			PutawayforWTwithPallet(palletid);
		}
	}
	@Given("^Perform Putaway for WestThurrock \"([^\"]*)\" to \"([^\"]*)\"$")
	public void PutawayforWTusingloc(String pallet,String location ) throws Throwable {
		context.setPalletID(pallet);
		context.setLocation(location);
		PutawayforWTwithPalletLoc(context.getPalletID(),context.getLocation());

	}
	//Putaway for Stoke
	@Given("^Perform Putaway for Stoke \"([^\"]*)\"$")
	public void Putawayforstoke(String pallet ) throws Throwable {
		context.setPalletID(pallet);
		PutawayforStokewithPallet(context.getPalletID());

	}	
	@Given("^Perform Putaway for Stoke to Multiple Pallet$") 
	public void PutawayforStokeMultiplePallet(DataTable pallet) throws Throwable {
		for (Map<String, String> data : pallet.asMaps(String.class, String.class)) { 

			String palletid= data.get("Pallet_ID");
			System.out.println("pallet ID IS"+palletid);
			PutawayforStokewithPallet(palletid);
		}
	}
	@Given("^Perform Putaway for Stoke \"([^\"]*)\" to \"([^\"]*)\"$")
	public void PutawayforStokeusingloc(String pallet,String location ) throws Throwable {
		context.setPalletID(pallet);
		context.setLocation(location);
		PutawayforStokewithPalletLoc(context.getPalletID(),context.getLocation());

	}
	//Putaway for Swindon NDC
	@Given("^Perform Putaway for Swindon \"([^\"]*)\"$")
	public void PutawayforSwindon(String pallet ) throws Throwable {
		context.setPalletID(pallet);
		PutawayforSwindonPallet(context.getPalletID());

	}	
	@Given("^Perform Putaway for Swindon to Multiple Pallet$") 
	public void PutawayforSwindonMultiplePallet(DataTable pallet) throws Throwable {
		for (Map<String, String> data : pallet.asMaps(String.class, String.class)) { 
			Thread.sleep(Constants.medium_sleep);
			String palletid= data.get("Pallet_ID");
			System.out.println("pallet ID IS"+palletid);
			PutawayforSwindonPallet(palletid);
		}
	}
	//Putaway for RDC's
	@Given("^Perform Putaway for RDC \"([^\"]*)\"$")
	public void PutawayforRDC(String pallet ) throws Throwable {
		context.setPalletID(pallet);
		PutawayforRDCPallet(context.getPalletID());

	}	
	@Given("^Perform Putaway for RDC \"([^\"]*)\" to \"([^\"]*)\"$")
	public void PutawayforRDCWithlocation(String pallet,String location ) throws Throwable {
		context.setPalletID(pallet);
		context.setLocation(location);
		PutawayforRDCPalletWithLoc(context.getPalletID(),context.getLocation());

	}
	@Given("^Perform Putaway for RDC to Multiple Pallet$") 
	public void PutawayforRDCMultiplePallet(DataTable pallet) throws Throwable {
		for (Map<String, String> data : pallet.asMaps(String.class, String.class)) { 
			Thread.sleep(Constants.medium_sleep);
			String palletid= data.get("Pallet_ID");
			System.out.println("pallet ID IS"+palletid);
			PutawayforRDCPallet(palletid);
		}
	}
	//Putaway for Welham NDC
	@Given("^Perform Putaway for Welham \"([^\"]*)\"$")
	public void PutawayforWelham(String pallet ) throws Throwable {
		context.setPalletID(pallet);
		PutawayforWelhamPallet(context.getPalletID());

	}	
	@Given("^Perform Putaway for Welham to Multiple Pallet$") 
	public void PutawayforWelhamMultiplePallet(DataTable pallet) throws Throwable {
		for (Map<String, String> data : pallet.asMaps(String.class, String.class)) { 
			Thread.sleep(Constants.medium_sleep);
			String palletid= data.get("Pallet_ID");
			System.out.println("pallet ID IS"+palletid);
			PutawayforWelhamPallet(palletid);
		}
	}
	@Given("^Perform Putaway for \"([^\"]*)\" \"([^\"]*)\"$")
	public void PutawayPackage(String site,String pallet ) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		context.setPalletID(pallet);
		if(site.equals("7401")){
			PutawayforWelhamPallet(context.getPalletID());
		}
		else if(site.equals("5665")){
			PutawayforSwindonPallet(context.getPalletID());
		}
		else if(site.equals("5649")){
			PutawayforWTwithPallet(context.getPalletID());
		}
		else if(site.equals("5885")){
			PutawayforStokewithPallet(context.getPalletID());
		}
		else if((site.equals("7278")||site.equals("6868"))){
			PutawayforRDCPallet(context.getPalletID());
		}

	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @desc Check String 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void type_chkString() throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		if(rdthandler.getScreenName().equalsIgnoreCase("PckPalTo")){
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.short_sleep);
			System.out.println("Check String");

			String location= rdthandler.getLocation();
			Thread.sleep(Constants.medium_sleep);
			System.out.println(location);
			String chkstring=locationDB.getCheckString(location);
			rdthandler.typeString(chkstring);	
			rdthandler.typeKey("ENTER");
			Thread.sleep(Constants.medium_sleep);
		}
	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @desc putaway for pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforWTwithPallet(String pallet) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		String location=movetaskDB.getToLocUsingPall(pallet);
		Thread.sleep(Constants.medium_sleep);
		String chkstring=locationDB.getCheckString(location);
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeString(chkstring);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);


	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet and location
	 * @desc putaway for pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforWTwithPalletLoc(String pallet,String location) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("TAB");
		rdthandler.typeString(location);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		String chkstring=locationDB.getCheckString(location);
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeString(chkstring);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);


	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforStokewithPallet(String pallet) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		String location=movetaskDB.getToLocUsingPall(pallet);
		Thread.sleep(Constants.medium_sleep);
		String chkstring=locationDB.getCheckString(location);
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeString(chkstring);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @param location
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforStokewithPalletLoc(String pallet,String location) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("TAB");
		rdthandler.typeString(location);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		String chkstring=locationDB.getCheckString(location);
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeString(chkstring);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);


	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforSwindonPallet(String pallet) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("TAB");
		Thread.sleep(Constants.short_sleep);
		String aisle=locationDB.getAisle(pallet);
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeString(aisle+"S");
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.long_sleep);
		rdthandler.typeKey("ENTER");
		String location= rdthandler.getLocation();
		Thread.sleep(Constants.medium_sleep);
		System.out.println(location);
		String chkstring=locationDB.getCheckString(location);
		rdthandler.typeString(chkstring);	
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);


	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @Details- RDC Putaway
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforRDCPallet(String pallet) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("F10");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		String location= rdthandler.getLocation();
		Thread.sleep(Constants.medium_sleep);
		System.out.println(location);
		String chkstring=locationDB.getCheckString(location);
		rdthandler.typeString(chkstring);	
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @param loc
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforRDCPalletWithLoc(String pallet,String loc) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("TAB");
		rdthandler.typeString(loc);
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("F10");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.short_sleep);
		rdthandler.typeKey("ENTER");
		String location= rdthandler.getLocation();
		Thread.sleep(Constants.medium_sleep);
		System.out.println(location);
		String chkstring=locationDB.getCheckString(location);
		rdthandler.typeString(chkstring);	
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
	}
	/**
	 * @author Kasinathan.Elavarasa
	 * @param pallet
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void PutawayforWelhamPallet(String pallet) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		rdthandler.typeString(pallet);
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);
		rdthandler.typeKey("ENTER");
		String location= rdthandler.getLocation();
		Thread.sleep(Constants.medium_sleep);
		System.out.println(location);
		String chkstring=locationDB.getCheckString(location);
		rdthandler.typeString(chkstring);	
		rdthandler.typeKey("ENTER");
		Thread.sleep(Constants.medium_sleep);


	}
    /**
	 * @author SRIRAMASUBRAMANIAN.K
	 * @param siteid
	 * @param palletid
	 * @param fromlocationid
	 * @throws Throwable
	 */

@Given("^Perform perform Marshalling \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_perform_Marshalling(String siteid, String palletid, String fromlocationid) throws Throwable {
		if (siteid.equals("5665") || siteid.equals("5885")) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(4000);
			rdthandler.typeStringAndEnter(locationDB.getCheckString(fromlocationid));
			Thread.sleep(3000);
			rdthandler.typeKey("ENTER");
			
		}
		// ***CODE FOR THOURNCLIFF***//

		else if (siteid.equals("6868")) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("F12");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
		}
		// ****Code for XDOCK**//

		else if ((siteid.equals("3641") || siteid.equals("3366"))) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			rdthandler.typeKey("F12");
			rdthandler.typeKey("ENTER");
			rdthandler.typeStringAndEnter(palletid);
			rdthandler.typeKey("ENTER");
		}
		// ***code for hemel***///

		else if (siteid.equals("5542")) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			if (fromlocationid.equals("QRLANE")) {
				rdthandler.typeKey("TAB");
				Thread.sleep(2000);
				rdthandler.typeStringAndEnter(palletid);
				Thread.sleep(2000);
				rdthandler.typeKey("F10");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeStringAndEnter(fromlocationid);
				Thread.sleep(2000);
			} 
                             else if (fromlocationid.equals("RUS1IN")) {
				rdthandler.typeKey("TAB");
				Thread.sleep(2000);
				rdthandler.typeStringAndEnter(palletid);
				Thread.sleep(2000);
				rdthandler.typeKey("F10");
				Thread.sleep(2000);
				rdthandler.typeKey("ENTER");
				Thread.sleep(2000);
				rdthandler.typeStringAndEnter(locationDB.getCheckString(fromlocationid));
			}
		}
		// ***code for Welham***///

		else if (siteid.equals("7401")) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			
		}
		// ***code for westhurock***///

		else if (siteid.equals("5649")) {
			Thread.sleep(2000);
			rdthandler.typeString("2");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeString("6");
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("TAB");
			Thread.sleep(2000);
			rdthandler.typeStringAndEnter(palletid);
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
			Thread.sleep(2000);
			rdthandler.typeKey("ENTER");
		}

	}




}

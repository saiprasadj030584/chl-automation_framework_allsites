package com.jda.wms.handlers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Assert;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.handlers.ValidationHandler;
import com.jda.wms.config.Constants;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class RDTHandler {

	private Configuration configuration;
	private static Screen screen = new Screen();
	private static Process putty;
	public static String host = null;
	public static String port = null;
	public ValidationHandler validationHandler;
	private Utilities utilities;
	public String connStr = null;
	// private static String connStr = "files/putty.exe -telnet
	// hlxc0dc048.unix.marksandspencercate.com 41911";
	// public static String connStr = "files/putty.exe -telnet '"+host+"'
	// '"+port+"'";
	private static String user = Constants.rdt_user_id;
	private static String pwd = Constants.rdt_password;

	@Inject
	public RDTHandler(Configuration configuration) {
		this.configuration = configuration;
		this.validationHandler = validationHandler;
		this.utilities = utilities;
	}

	/**
	 * Used for Logging in to RDT terminal
	 * 
	 * @throws Throwable
	 */
	@Given("^Login to terminal$")
	public void LoginToTerminal() throws Throwable {
		invokePutty();
		Thread.sleep(1000);
		login();
		Thread.sleep(1000);
	}

	/**
	 * Used for invoking RDT to navigating till main menu.
	 * 
	 * @throws Throwable
	 */
	@Given("^Login to Main menu$")
	public void LoginToMainMenu() throws Throwable {
		invokePutty();
		Thread.sleep(1000);
		login();
		Thread.sleep(1000);
		gotoMainMenu();
	}

	/**
	 * Navigate to main menu on RDT.
	 * 
	 * @throws Throwable
	 */
	@Given("^Go to Main menu$")
	public void goToMainMenu() throws Throwable {
		Thread.sleep(1000);
		gotoMainMenu();
	}

	/**
	 * open putty based be .exe
	 * 
	 * @throws Throwable
	 */
	public void invokePutty() throws Throwable {
		// host = configuration.getStringProperty("sit-putty-host");
		// port = configuration.getStringProperty("sit-putty-port");
		host = Constants.putty_host;
		port = Constants.putty_port;
		// port = configuration.getStringProperty("sit-putty-port");
		connStr = "files/putty.exe -telnet " + host + " " + port + "";
		System.out.println(connStr);
		System.out.println("Kill existing process");
		Runtime.getRuntime().exec("taskkill /F /IM putty.exe");
		Thread.sleep(1000);
		System.out.println("Open Putty with Connection String passed in");
		putty = Runtime.getRuntime().exec(connStr);
	}

	/**
	 * Login to Putty
	 * 
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public int login() throws FindFailed, InterruptedException {
		if (getScreenName().equalsIgnoreCase("LogIn")) {
			System.out.println("entered Login");
			typeString(user);
			typeKey("DOWN");
			typeString(pwd);
			typeKey("ENTER");
			if (getScreenName().equalsIgnoreCase("login")) {
				return 1; // login success
			} else {
				return 0; // login failure
			}
		} else {
			System.out.println("Currently in screen:" + getScreenName());
			gotoMainMenu();
			return 2; // already logged in
		}
	}

	/**
	 * Logout
	 * 
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void logout() throws FindFailed, InterruptedException {
		gotoMainMenu();
		typeStringAndEnter("999");
		if (getScreenName().equalsIgnoreCase("lstlog")) {
			typeStringAndEnter("N");
		}
	}

	/**
	 * Navigate to Main Menu on RDT
	 * 
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void gotoMainMenu() throws FindFailed, InterruptedException {
		int count = 1;
		if (getScreenName().equalsIgnoreCase("LogIn")) {
			System.out.println("Not logged in");
			return;
		} else if ((getScreenName().equalsIgnoreCase("MaiMnu")) || (getScreenName().equalsIgnoreCase("mainmenu"))) {
			System.out.println("Already in Main Menu");
			return;
		} else {
			while (count++ < 5) {
				screen.type(Key.F10);
				Thread.sleep(500);
			}
			while (!((getScreenName().equalsIgnoreCase("MaiMnu")) || (getScreenName().equalsIgnoreCase("mainmenu")))) {
				screen.type(Key.F10);
				Thread.sleep(500);
			}
			System.out.println("Reached main menu");
		}
	}

	/**
	 * Get current Screen name
	 * 
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public String getScreenName() throws FindFailed, InterruptedException {
		Match mLocation = screen.find("images/PuttyIcon.png");
		Location loc = mLocation.getCenter().offset(0, 30);
		tripleClick(loc);
		String str = copy_to_clipboard();
		screen.click(loc);
		str = str.split("\\s+")[1];
		System.out.println("The screen name is " + str);
		return str;
	}

	/**
	 * triple click to copy the content
	 * 
	 * @param loc
	 * @throws FindFailed
	 */
	public static void tripleClick(Location loc) throws FindFailed {
		screen.click(loc);
		screen.mouseDown(Button.LEFT);
		screen.mouseUp(Button.LEFT);
		screen.wait(0.01);
		screen.mouseDown(Button.LEFT);
		screen.mouseUp(Button.LEFT);

	}

	/**
	 * copy to clipboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String copy_to_clipboard() throws InterruptedException {
		String clpbrd_text = null;
		// typeKey("CONTROL-C");
		Thread.sleep(1000);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		DataFlavor flavor = DataFlavor.stringFlavor;
		if (clipboard.isDataFlavorAvailable(flavor)) {
			try {
				clpbrd_text = (String) clipboard.getData(flavor);
				System.out.println("#############################Copied this line to clipboard");
				System.out.println(clpbrd_text);
				return clpbrd_text;
			} catch (UnsupportedFlavorException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return "clipboard copy FAIL";
	}

	/**
	 * Handle String Entries
	 * 
	 * @param str
	 */
	public void typeString(String str) {
		screen.type(str);
	}

	public void typeChar(char Char) {
		String s = Character.toString(Char);
		screen.type(s);
	}

	public void typeStringAndEnter(String str) {
		screen.type(str);
		typeKey("ENTER");
	}

	public void typeStringAndTab(String str) {
		screen.type(str);
		typeKey("TAB");
	}

	public void typeCharAndTab(char Char) {
		String s = Character.toString(Char);
		screen.type(s);
		typeKey("TAB");
	}

	public void typeTab() {
		typeKey("TAB");
	}

	public void typeqty(String qty) throws InterruptedException {
		if (Integer.parseInt(qty) > 999) {
			screen.type(qty);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		} else {
			screen.type(qty);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		}

	}

	/**
	 * Handle Key entries
	 * 
	 * @param key
	 */
	public void typeKey(String key) {
		switch (key) {
		case "ENTER":
			screen.type(Key.ENTER);
			break;
		case "UP":
			screen.type(Key.UP);
			break;
		case "DOWN":
			screen.type(Key.DOWN);
			break;
		case "TAB":
			screen.type(Key.TAB);
			break;
		case "BACKSPACE":
			screen.type(Key.BACKSPACE);
			break;
		case "CONTROL-C":
			screen.type("c", Key.CTRL);
			break;
		case "F3":
			screen.type(Key.F3);
			break;
		case "F4":
			screen.type(Key.F4);
			break;
		case "F10":
			screen.type(Key.F10);
			break;
		case "F12":
			screen.type(Key.F12);
			break;
		default:
			System.out.println("Unknown key passed");
			break;
		}
	}

	/**
	 * Navigate to different Receiving Menus.
	 * 
	 * @param RecMenuType
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void RecvMnu(String RecMenuType) throws FindFailed, InterruptedException {
		switch (RecMenuType) {
		case "Basic Receiving":
			screen.type("211" + Key.ENTER);
			if (getScreenName().equals("BasRcvMnu")) {
				System.out.println("In Basic Receive Menu");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Blind Receive":
			screen.type("2111" + Key.ENTER);
			if (getScreenName().equals("RcvBli")) {
				System.out.println("In Blind Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Pre-Adv Receive":
			screen.type("2112" + Key.ENTER);
			if (getScreenName().equals("RcvPreEnt")) {
				System.out.println("In Preadv Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "GSI-128 Receive":
			screen.type("2113" + Key.ENTER);
			if (getScreenName().equals("RcvScnEANCt")) {
				System.out.println("In GSI-128 Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Tag Receive":
			screen.type("2114" + Key.ENTER);
			if (getScreenName().equals("RcvRecTag")) {
				System.out.println("In Tag Receive Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Group Receiving":
			screen.type("212" + Key.ENTER);
			if (getScreenName().equals("GrpRcvMnu")) {
				System.out.println("In Group Receiving Menu");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "UPI Receive":
			screen.type("2121" + Key.ENTER);
			if (getScreenName().equals("RcvRecUPI")) {
				System.out.println("In UPI Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Delivery Receive":
			screen.type("2122" + Key.ENTER);
			if (getScreenName().equals("RcvDel")) {
				System.out.println("In Delivery Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Trailer Receive":
			screen.type("2123" + Key.ENTER);
			if (getScreenName().equals("RcvTrl")) {
				System.out.println("In Trailer Receiving Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Receipt Reconcile":
			screen.type("213" + Key.ENTER);
			if (getScreenName().equals("Reconcile")) {
				System.out.println("In Receipt Reconcile Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "C&E Colli Update":
			screen.type("214" + Key.ENTER);
			if (getScreenName().equals("CERcvMnu")) {
				System.out.println("In C&E Colli Update Menu");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Status Change":
			screen.type("2141" + Key.ENTER);
			if (getScreenName().equals("MRNEnt")) {
				System.out.println("Status Change");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Colli Count":
			screen.type("2142" + Key.ENTER);
			if (getScreenName().equals("MRNEnt")) {
				System.out.println("Status Change");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		default:
			System.out.println("Unknown key passed");
			break;
		}
	}

	/**
	 * navigate to different Putaway Screens
	 * 
	 * @param PutAwayType
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void PutAwayMnu(String PutAwayType) throws FindFailed, InterruptedException {
		switch (PutAwayType) {
		case "Normal":
			screen.type("221" + Key.ENTER);
			if (getScreenName().equals("PutEnt")) {
				System.out.println("In Normal PutAway Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Sortation":
			screen.type("222" + Key.ENTER);
			if (getScreenName().equals("SrtPutEn")) {
				System.out.println("In Sortation PutAway Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		}
	}

	/**
	 * Navigate to Various Picking Screen
	 * 
	 * @param PickType
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void Picking(String PickType) throws FindFailed, InterruptedException {
		switch (PickType) {
		case "Picking":
			screen.type("231" + Key.ENTER);
			if (getScreenName().equals("PckTskMnu")) {
				System.out.println("In Picking Menu");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Normal Pick":
			screen.type("2311" + Key.ENTER);
			if (getScreenName().equals("PckEnt")) {
				System.out.println("In Normal Picking Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Replenish Pick":
			screen.type("2312" + Key.ENTER);
			if (getScreenName().equals("PckEnt")) {
				System.out.println("In Replenish Pick Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Container Pick":
			screen.type("2313" + Key.ENTER);
			if (getScreenName().equals("PckEnt")) {
				System.out.println("In Container Pick Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Pallet Repacking":
			screen.type("232" + Key.ENTER);
			if (getScreenName().equals("PalRpkEnt")) {
				System.out.println("In Pallet Repacking Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Demand Allocate":
			screen.type("233" + Key.ENTER);
			if (getScreenName().equals("DemAll")) {
				System.out.println("In Demand Allocate Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Replen Release":
			screen.type("234" + Key.ENTER);
			if (getScreenName().equals("RelRepEnt")) {
				System.out.println("In Replen Release Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		}
	}

	/**
	 * navigate to Relocate Screen
	 * 
	 * @param RelocateType
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void Relocate(String RelocateType) throws FindFailed, InterruptedException {
		switch (RelocateType) {
		case "Existing Relocate":
			screen.type("241" + Key.ENTER);
			if (getScreenName().equals("RelEnt")) {
				System.out.println("In Existing Relocate Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Normal":
			screen.type("242" + Key.ENTER);
			if (getScreenName().equals("RelNewEnt")) {
				System.out.println("In New Relocate Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Zero Demand":
			screen.type("243" + Key.ENTER);
			if (getScreenName().equals("RelZerEnt")) {
				System.out.println("In Zero Demand Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		case "Multi Tag":
			screen.type("244" + Key.ENTER);
			if (getScreenName().equals("RelMultiTagC")) {
				System.out.println("In Multi Tag Screen");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		}
	}

	public void InventMenu(String inventMenuType) throws FindFailed, InterruptedException {
		switch (inventMenuType) {
		case "New Stock Check":
			screen.type("252" + Key.ENTER);
			if (getScreenName().equals("StkNewEnt")) {
				System.out.println("In stock check Menu");
				break;
			}
		case "Existing Stock Check":
			screen.type("251" + Key.ENTER);
			if (getScreenName().equals("StkNewEnt")) {
				System.out.println("In stock check Menu");
				break;
			} else {
				System.out.println("Invalid Screen Entered");
				break;
			}
		}
	}

	/**
	 * Author: Vedika The following is the code for navigating to loading screen
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void Loading(String loadType) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		gotoMainMenu();
		if (loadType.equals("Multi Pallet Load")) {
			screen.type("276" + Key.ENTER);
			if (getScreenName().equalsIgnoreCase("LodMEnt")) {
				System.out.println("In Loading Menu");
			} else {
				validationHandler.validateRdtScreen("LodMEnt");
				System.out.println("Invalid Screen Entered");
			}
		}
	}

	/**
	 * Repacking navigation
	 */
	public void Repacking() throws FindFailed, InterruptedException {

		screen.type("232" + Key.ENTER);
		if (getScreenName().equals("PalRpkEnt")) {
			System.out.println("In Repacking Menu");

		} else {
			System.out.println("Invalid Screen Entered");

		}
	}

	/**
	 * Picking handler methods
	 * 
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public String getSlotID() throws FindFailed, InterruptedException {
		System.out.println("Enter Get SLot");
		Match mLocation = screen.find("images/PuttyIcon.png");
		System.out.println(mLocation);
		Location loc = mLocation.getCenter().offset(0, 120);
		System.out.println("Location is-" + loc);
		tripleClick(loc);
		String str = copy_to_clipboard();
		String SltVal = str.replaceAll("[^0-9]", "");
		// str = str.split("\\s+")[1];
		System.out.println("The Slot is " + SltVal);
		return SltVal;
	}

	/**
	 * Picking handler methods
	 * 
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public String WelhanmgetSlotID() throws FindFailed, InterruptedException {
		System.out.println("Enter Get SLot");
		Match mLocation = screen.find("images/PuttyIcon.png");
		System.out.println(mLocation);
		Location loc = mLocation.getCenter().offset(50, 120);
		System.out.println("Location is-" + loc);
		DoubleClick(loc);
		String str = copy_to_clipboard();
		String SltVal = str.replaceAll("_", "");
		// str = str.split("\\s+")[1];
		System.out.println("The Slot is " + SltVal);
		return SltVal;
	}

	/**
	 * @author Kasinathan.Elavarasa
	 * @details Getslot for swindon picking
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public String getSlotIDforSW() throws FindFailed, InterruptedException {
		System.out.println("Enter Get SLot");
		Match mLocation = screen.find("images/PuttyIcon.png");
		System.out.println(mLocation);
		Location loc = mLocation.getCenter().offset(0, 110);
		System.out.println("Location is-" + loc);
		tripleClick(loc);
		String str = copy_to_clipboard();
		String SltVal = str.replaceAll("[^0-9]", "");
		// str = str.split("\\s+")[1];
		System.out.println("The Slot is " + SltVal);
		return SltVal;
	}

	/**
	 * @author Kasinathan.Elavarasa
	 * @desc genrate 11 digit pallet for Pick
	 * @return
	 */
	public String randompalletforPick() {
		String Firstpalletdigit = Utilities.getTenDigitRandomNumber();

		String toPallet = "1" + Firstpalletdigit;
		System.out.println(toPallet);
		return toPallet;

	}

	/**
	 * @author Kasinathan.Elavarasa
	 * @desc genrate 10 digit pallet for Pick
	 * @return
	 */
	public String randomTagforPick() {
		String Tag = Utilities.getNineDigitRandomNumber();
		String toTag = "9" + Tag;
		return toTag;

	}

	/**
	 * @author Kasinathan.Elavarasa
	 * @desc Double click
	 * @param loc
	 * @throws FindFailed
	 */
	private static void DoubleClick(Location loc) throws FindFailed {
		screen.click(loc);
		screen.mouseDown(Button.LEFT);
		screen.mouseUp(Button.LEFT);
		screen.wait(0.01);

	}

	/**
	 * @author Kasinathan.Elavarasa
	 * @details Get the location from Check string Screen
	 * @return
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public String getLocation() throws FindFailed, InterruptedException {
		System.out.println("Enter Get SLot");
		Match mLocation = screen.find("images/PuttyIcon.png");
		System.out.println(mLocation);
		Location loc = mLocation.getCenter().offset(60, 55);
		System.out.println("Location is-" + loc);
		DoubleClick(loc);
		String str = copy_to_clipboard();

		return str;
	}

	/**
	 * Author: Vedika The following is the code for navigating to Inventory
	 * Transaction screen on RDT
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void goToInventoryTransaction() throws FindFailed, InterruptedException {

		gotoMainMenu();
		changeKeyboardType();
		Thread.sleep(3000);
		typeKey("F3");
		Thread.sleep(2000);
		typeString("7");
		typeKey("ENTER");
		Thread.sleep(3000);
	}

	/**
	 * Author: Vedika The following is the code for changing the Keyboard type
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void changeKeyboardType() throws FindFailed, InterruptedException {

		Thread.sleep(2000);
		screen.click("/images/Putty/PuttyIcon.png");
		Thread.sleep(2000);
		screen.click("images/Putty/Changesettings.png");
		Thread.sleep(2000);
		screen.click("images/Putty/Keyboard.png");
		Thread.sleep(2000);
		screen.click("/images/Putty/XtermR6.png");
		Thread.sleep(2000);
		screen.click("/images/Putty/PuttyApply.png");

	}

	// Repack -- random values
	public String randompalletforRePack() {
		String toPallet = utilities.getFourteenDigitRandomNumber();
		System.out.println(toPallet);
		return toPallet;

	}

	public String randompallet_975forRePack() {
		String toPallet = utilities.getFourteenDigit_975Number();
		System.out.println(toPallet);
		return toPallet;

	}

	public String randompallet_HemelforRePack() {
		String toPallet = utilities.getP_EightNumber();
		System.out.println(toPallet);
		return toPallet;
	}

	public String randomPalletforRec() {
		String pal = Utilities.getNineDigitRandomNumber();
		return pal;

	}

	/**
	 * @author Pavani.Itte
	 * Below code is to navigate to Repacking screen for tote consolidation
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public void RepackingTC() throws FindFailed, InterruptedException {

		screen.type("0" + Key.ENTER);
		Thread.sleep(1000);
		screen.type("4" + Key.ENTER);

		if (getScreenName().equals("QryCnfEnt")) {
			System.out.println("In Repacking Menu");

		} else {
			System.out.println("Invalid Screen Entered");

		}
	}

	public void ValidateToteConsolidation() throws FindFailed, InterruptedException {

		if (getScreenName().equalsIgnoreCase("MrgPrgMsg")) {
			System.out.println("MrgPrgMsg");
			if (getErrorname().equalsIgnoreCase("Repack")) {
				System.out.println("Repack is  completed");
				typeKey("ENTER");

			} else if (getErrorname().equalsIgnoreCase("From and To pallets")) {
				System.out.println("From and To pallets are from Different BU");
				Assert.fail("Repack same BU articles.");
			}

			else if (getErrorname().equalsIgnoreCase("consignment for From")) {
				System.out.println("From and To pallets have different consignment");
				Assert.fail("Must have same consignment for from and to pallets.");
			} else if (getErrorname().equalsIgnoreCase("Tdept for From and")) {
				System.out.println("Tdept for From and To pallets didn't match");
				Assert.fail("Tdept is different for From and To pallets.");
			}

			else if (getErrorname().equalsIgnoreCase("Destination for From")) {
				System.out.println("Destination is different for from and to pallets");
				Assert.fail("Do Repack for same destination pallets.");
			} else if (getErrorname().equalsIgnoreCase("From and To Pallet")) {
				System.out.println("Locations are different for From and To pallets");
				Assert.fail("Do Repack for pallets which are in same location.");
			} else if (getErrorname().equalsIgnoreCase("To pallet and To")) {
				System.out.println("To container and To pallets didn't match");
				Assert.fail("To container and To pallets didn't match.");
			} else if (getErrorname().equalsIgnoreCase("Quantity being")) {
				System.out.println("From pallet exceeds the tote weight.");
				Assert.fail("pallet being repacked exceeds tote weight.");
			} else if (getErrorname().equalsIgnoreCase("UPC/Quantity is not")) {
				System.out.println("Upc/Qty value not null.");
				Assert.fail("Enter UPC/Qty");
			} else if (getErrorname().equalsIgnoreCase("Repack error! Please")) {
				System.out.println("pallet being repacked exceeds the original qty.");
				Assert.fail("Enter correct qty for Repack.");
			} else {
				System.out.println("Unknown Error");
			}

		} else {
			Assert.fail("Invalid screen");
		}
	}

	public String getErrorname() throws FindFailed, InterruptedException {
		Match mLocation = screen.find("images/PuttyIcon.png");
		Location loc = mLocation.getCenter().offset(0, 40);
		tripleClick(loc);
		String str = copy_to_clipboard();
		str = str.trim();
		return str;
	}

}

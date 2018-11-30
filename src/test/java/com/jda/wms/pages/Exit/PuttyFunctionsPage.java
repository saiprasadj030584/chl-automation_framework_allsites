package com.jda.wms.pages.Exit;

import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.SupplierSkuDB;
import com.jda.wms.utils.Utilities;

public class PuttyFunctionsPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private SupplierSkuDB supplierSkuDB;
	private PreAdviceLineDB preAdviceLineDB;

	@Inject
	public PuttyFunctionsPage(Context context,SupplierSkuDB supplierSkuDB,PreAdviceHeaderDB preAdviceHeaderDB,PreAdviceLineDB preAdviceLineDB) {
		
		this.context = context;
		this.preAdviceHeaderDB=preAdviceHeaderDB;
		this.supplierSkuDB=supplierSkuDB;
		this.preAdviceLineDB=preAdviceLineDB;
		
		
	}

	public void invokePutty() throws IOException, InterruptedException {
		Process putty = Runtime.getRuntime().exec("bin/putty/putty.exe");
		context.setPuttyProcess(putty);
		Thread.sleep(2000);
	}

	public void loginPutty(String host, String port) throws FindFailed, InterruptedException {

		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		System.out.println("host"+host);
		screen.type(host);
		screen.type(Key.TAB);
		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		System.out.println("port"+port);
		screen.type(port);
		screen.wait("images/Putty/Telnet.png", timeoutInSec);
		screen.click("images/Putty/Telnet.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void Tab() throws FindFailed, InterruptedException{
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}
	public void singleTab() throws FindFailed, InterruptedException{
		screen.type(Key.TAB);
	}

	public void enterJdaLogin(String username, String pwd) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type(username);
		screen.type(Key.TAB);
		screen.type(pwd);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void mimimizePuty() throws FindFailed, InterruptedException {
		screen.wait("/images/Putty/MinimizePutty.png", timeoutInSec);
		screen.click("/images/Putty/MinimizePutty.png");
		screen.rightClick();
		Thread.sleep(2000);
		screen.wait("/images/Putty/Minimize.png", timeoutInSec);
		screen.click("/images/Putty/Minimize.png");
	}

	public boolean isLoginScreenDisplayed() {
		if (screen.exists("images/Putty/Username.png") != null)
			return true;
		else
			return false;
	}
	public boolean Overreceipterror() {
		if (screen.exists("images/Putty/Receiving/OverReceiptError1.png") != null)
			return true;
		else
			return false;
	}
	public boolean UnknownStock() {
		if (screen.exists("images/Putty/Receiving/UnknownstockError.png") != null)
			return true;
		else
			return false;
	}

	public void minimisePutty() throws FindFailed, InterruptedException {
		screen.wait("images/Putty/PuttyMinimise.png", timeoutInSec);
		screen.click("images/Putty/PuttyMinimise.png");
		Match mStatus = screen.find("images/Putty/PuttyMinimise.png");
		screen.mouseMove(63, 0);
		screen.click(mStatus.containsMouse());
		Thread.sleep(2000);
	}

	public boolean isMainMenuDisplayed() {
		if (screen.exists("images/Putty/MainMenu.png") != null) {
			context.setPuttyLoginFlag(true);
			return true;
		} else
			return false;
	}

	public void pressTab() throws InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(2000);
	}

	public void pressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void nextScreen() throws InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterPrinter() throws InterruptedException {

		screen.type("PRINTER1");
		screen.type(Key.ENTER);

	}
public void enterPrinter1()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Answer1.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("PRINTER1");

	}

	public void enterF11() throws InterruptedException {

		screen.type(Key.F11);

	}
//	@Given("^I generate pallet id$")
	public void i_generate_pallet_id_for_UPI(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
		
		System.out.println("preadv "+preAdviceId);
		String palletIDforUPI = null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "222";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate();
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
		String dept = deptmanipulate();
		System.out.println("dept "+dept);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
		String advice2 = Utilities.getSixDigitRandomNumber();
		System.out.println("advice "+advice2);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletIDforUPI = siteid+ barcode + URN + supplier + '0' + dept + advice2 + skuqtymanipulate + checkbit;
		context.setpalletIDforUPI(palletIDforUPI);
		System.out.println("check palletid "+palletIDforUPI);
	}
	
	public void i_generate_pallet_id_for_UPI_for_red_stock(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
		
		System.out.println("preadv "+preAdviceId);
		String palletIDforUPI = null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "222";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate();
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
		String dept = deptmanipulate();
		System.out.println("dept "+dept);
//		String dept = "000";
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
		String advice2 = Utilities.getSixDigitRandomNumber();
		System.out.println("advice "+advice2);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletIDforUPI = siteid+ barcode + URN + supplier + '0' + dept + advice2 + skuqtymanipulate + checkbit;
		context.setpalletIDforUPI(palletIDforUPI);
		System.out.println("check palletid "+palletIDforUPI);
	}
//	@Given("^I generate pallet id$")
	public void i_generate_pallet_id(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
//		context.setSkuId2(skuid);
		System.out.println("preadv "+preAdviceId);
		String palletID= null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "145";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate();
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
//		String dept = "000";
		String dept = deptmanipulate();
		System.out.println("dept "+dept);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
		System.out.println("advice "+advice);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletID = siteid+ barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
		context.setPalletID(palletID);
		System.out.println("check palletid "+palletID);
	}
	public void i_generate_pallet_id_forUnknown(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
		context.setSkuId2(skuid);
		System.out.println("preadv "+preAdviceId);
		String palletID= null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "145";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate();
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
		String dept = "00";
//		String dept = deptmanipulate();
		System.out.println("dept "+dept);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
		System.out.println("advice "+advice);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletID = siteid+ barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
		context.setPalletID(palletID);
		System.out.println("check palletid "+palletID);
	}
	// Get supplierid - 4 digit and manipulated to get only integer
		public String suppliermanipulate() throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId2();
			System.out.println("skuid "+context.getSkuId2());
//			context.getSkuId2();
//			context.getSkuId2();
			String supplier = supplierSkuDB.getSupplierId(skuid);
			String[] supplierSplit = supplier.split("M");
			return supplierSplit[1];
		}
//		public String suppliermanipulateForRedStock() throws ClassNotFoundException, SQLException {
//			String skuid=context.getSkuId();
//			System.out.println("skuid "+context.getSkuId());
////			context.getSkuId2();
////			context.getSkuId2();
//			String supplier = supplierSkuDB.getSupplierId(skuid);
//			String[] supplierSplit = supplier.split("M");
//			return supplierSplit[1];
//		}
		// Get dept - 3 digit
		public String deptmanipulate() throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId2();
			String dept = SkuDB.getProductGroup(skuid);
			System.out.println("Dept" + dept);
				String[] deptSplit = dept.split("T");
				return deptSplit[1];
				
					
			}
	
			


			
			
		
		public String deptmanipulateForRedStock() throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId();
			String dept = SkuDB.getProductGroup(skuid);
			System.out.println("Dept" + dept);
			String[] deptSplit = dept.split("T");
			return deptSplit[1];
			
			
		}
		public String skuQtyManipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
			
			String skuid=context.getSkuId2();
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "0" + qtyDue;
			} 
			return qtyDue;
		}
		
public String skuQtyManipulateForRedStock(String preAdviceId) throws ClassNotFoundException, SQLException {
			
			String skuid=context.getSkuId();
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "0" + qtyDue;
			} 
			return qtyDue;
		}
		public String skuQtyManipulate2(String preAdviceId) throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId2();
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "000" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 3) {
				qtyDue = "0" + qtyDue;
			}
			return qtyDue;
		}
		public String skuQtyManipulate2OverReceipt(String preAdviceId) throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId2();
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = qtyDue+"000"  ;
			} else if (sumLength == 2) {
				qtyDue = qtyDue + "00"  ;
			} else if (sumLength == 3) {
				qtyDue = qtyDue + "0"  ;
			}
			return qtyDue;
		}
		public String skuQtyManipulate2ForRedStock(String preAdviceId) throws ClassNotFoundException, SQLException {
			String skuid=context.getSkuId();
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "000" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 3) {
				qtyDue = "0" + qtyDue;
			}
			return qtyDue;
		}
//		@Given("^I generate belcode$")
		public void I_generate_belcode_for_UPI(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			context.setSkuId2(skuid);
			// Checkdigit : 2 any random number
//			String checkdigit = Utilities.getTwoDigitRandomNumber();
			String checkdigit = "02";
			System.out.println("checkdigit "+checkdigit);
			// Supplier code : 5 digit
			String supplier = suppliermanipulate();
			System.out.println("supplier "+supplier);
			// UPC : 8 digit
			String upc = preAdviceLineDB.getUpc(skuid);
			System.out.println("upc "+upc );
			// Quantity : 4 digit
			String skuqtymanipulate = skuQtyManipulate2(preAdviceId);
			System.out.println("skuqtymanipulate "+skuqtymanipulate);
			// Checkbit hardcoded : 1 digit
			String checkbit = "1";
			System.out.println("checkbit "+checkbit);
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			context.setBelCode(belCode);
			System.out.println(belCode);;
		}
		public void I_generate_belcode(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			context.setSkuId2(skuid);
			// Checkdigit : 2 any random number
			String checkdigit = Utilities.getTwoDigitRandomNumber();
//			String checkdigit = "02";
			System.out.println("checkdigit "+checkdigit);
			// Supplier code : 5 digit
			String supplier = suppliermanipulate();
			System.out.println("supplier "+supplier);
			// UPC : 8 digit
//			String upc = "00000000";
			String upc = preAdviceLineDB.getUpc(skuid);
			System.out.println("upc "+upc );
			// Quantity : 4 digit
			String skuqtymanipulate = skuQtyManipulate2(preAdviceId);
			System.out.println("skuqtymanipulate "+skuqtymanipulate);
			// Checkbit hardcoded : 1 digit
			String checkbit = "1";
			System.out.println("checkbit "+checkbit);
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			context.setBelCode(belCode);
			System.out.println(belCode);;
		}
		public void I_generate_belcode_for_overreceipt(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			context.setSkuId2(skuid);
			// Checkdigit : 2 any random number
			String checkdigit = Utilities.getTwoDigitRandomNumber();
//			String checkdigit = "02";
			System.out.println("checkdigit "+checkdigit);
			// Supplier code : 5 digit
			String supplier = suppliermanipulate();
			System.out.println("supplier "+supplier);
			// UPC : 8 digit
			String upc = preAdviceLineDB.getUpc(skuid);
			System.out.println("upc "+upc );
			// Quantity : 4 digit
			String skuqtymanipulate = skuQtyManipulate2OverReceipt(preAdviceId);
			System.out.println("skuqtymanipulate "+skuqtymanipulate);
			// Checkbit hardcoded : 1 digit
			String checkbit = "1";
			System.out.println("checkbit "+checkbit);
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			context.setBelCode(belCode);
			System.out.println(belCode);;
		}
		
		public void I_generate_belcode_for_unknown(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			context.setSkuId2(skuid);
			// Checkdigit : 2 any random number
			String checkdigit = Utilities.getTwoDigitRandomNumber();
//			String checkdigit = "02";
			System.out.println("checkdigit "+checkdigit);
			// Supplier code : 5 digit
			String supplier = suppliermanipulate();
			System.out.println("supplier "+supplier);
			// UPC : 8 digit
			String upc = "00000000";
//			String upc = preAdviceLineDB.getUpc(skuid);
			System.out.println("upc "+upc );
			// Quantity : 4 digit
			String skuqtymanipulate = skuQtyManipulate2(preAdviceId);
			System.out.println("skuqtymanipulate "+skuqtymanipulate);
			// Checkbit hardcoded : 1 digit
			String checkbit = "1";
			System.out.println("checkbit "+checkbit);
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			context.setBelCode(belCode);
			System.out.println(belCode);;
		}

}

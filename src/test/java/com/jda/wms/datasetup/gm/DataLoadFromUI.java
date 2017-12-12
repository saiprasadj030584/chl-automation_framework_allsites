package com.jda.wms.datasetup.gm;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.pages.gm.DeliveryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;

public class DataLoadFromUI {
	private JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;
	private DeliveryDB deliveryDB;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private UPIReceiptHeaderDB uPIReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private OrderHeaderPage orderHeaderPage;
	private Context context;
	private OrderHeaderDB orderHeaderDB;
	private DeliveryPage deliveryPage;
	public String invalidTesDataerrMsg = "Invalid test data provided as reference in the iARM portal.Please update valid test data and try again.";
	public String ejbErrMsg ="EJB Error Found - Application issue . please check with Non Prod Team and Try again";

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public DataLoadFromUI(JdaHomePage jdaHomePage,JDAFooter jdaFooter,DeliveryPage deliveryPage,
			DeliveryDB deliveryDB,Context context,UpiReceiptHeaderPage upiReceiptHeaderPage,
			PreAdviceHeaderDB preAdviceHeaderDB,PreAdviceHeaderPage  preAdviceHeaderPage,UPIReceiptHeaderDB  uPIReceiptHeaderDB,OrderHeaderPage orderHeaderPage,OrderHeaderDB orderHeaderDB) {

		this.jdaHomePage=jdaHomePage;
		this.jdaFooter=jdaFooter;
		this.deliveryPage=deliveryPage;
		this.context=context;
		this.deliveryDB=deliveryDB;
		this.upiReceiptHeaderPage =upiReceiptHeaderPage;
		this.preAdviceHeaderPage =  preAdviceHeaderPage;
		this.preAdviceHeaderDB=preAdviceHeaderDB;
		this.uPIReceiptHeaderDB =uPIReceiptHeaderDB;
		this.orderHeaderPage=orderHeaderPage;
		this.orderHeaderDB=orderHeaderDB;
	}

	public void duplicateASN(String asnReference, String asn)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {
			jdaHomePage.navigateToDeliveryPage();
			System.out.println("inside after query click asn");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click asn");
			if (screen.exists("images/AfterQueryClick.png") != null) {
				deliveryPage.enterAsnId(asnReference);
				jdaFooter.clickExecuteButton();
				if (deliveryPage.isNoRecordFound()) {
				System.out.println(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + asnReference);
				context.setErrorMessage(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + asnReference);
				Assert.fail(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + asnReference);
				}
			} else {
				// Clicking query for the second time to ensure test case
				jdaFooter.clickQueryButton();
				System.out.println("inside after query click asn");
				if (screen.exists("images/AfterQueryClick.png") != null) {
					deliveryPage.enterAsnId(asnReference);
					jdaFooter.clickExecuteButton();
					if (deliveryPage.isNoRecordFound()) {
						System.out.println(
								"No asn data present in UI due to invalid test data. Please check with project team to get valid data:-"
										+ asnReference);
						Assert.fail("No asn data present in UI");
					}
				} else {
					Assert.fail("Application Issue - Query button not clicked");
				}
			}

			if (deliveryPage.isEJBerrorfound()) {
				System.out.println(ejbErrMsg);
				context.setErrorMessage(ejbErrMsg);
				Assert.fail(ejbErrMsg);
			}

			screen.rightClick();
			Thread.sleep(2000);
			screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
			screen.click("images/DuplicateOption/duplicate.png");
			Thread.sleep(2000);
			screen.type("a", Key.CTRL);
			jdaFooter.pressBackSpace();
			deliveryPage.enterAsnId(asn);
			jdaFooter.clickExecuteButton();
			Thread.sleep(2000);
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			if (deliveryPage.warningPopUpDuplicateMsg()) {
				System.out.println("Warning popup  found");
				jdaFooter.PressEnter();
			} else {
				System.out.println("Warning popup not found");
			}
			Thread.sleep(2000);
			if (deliveryPage.isEJBerrorfound()) {
				System.out.println("EJB error Found");
				screen.wait("images/IgnoreMergeRulesUnchecked.png", timeoutInSec);
				screen.click("images/IgnoreMergeRulesUnchecked.png");
				Thread.sleep(2000);
				jdaFooter.clickExecuteButton();
				jdaFooter.PressEnter();
				context.setErrorMessage("EJB Error---> Ignore merge rules checked");
			} else {
				context.setErrorMessage("NA");
				System.out.println("1 EJB err not found");
			}
			// jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			if (deliveryPage.isEJBerrorfound()) {
				System.out.println(ejbErrMsg);
				context.setErrorMessage(ejbErrMsg);
				Assert.fail(ejbErrMsg+" Failed even after enabling the ignore merge rules checked..");
			} else {
				System.out.println("2 EJB err not found");
			}
			context.setAsnId(asn);
			Assert.assertEquals("No ASN ID in Oracle DB", asn, deliveryDB.getAsnIdForASN(context.getAsnId()));
		} catch (

		Exception e) {
			context.setErrorMessage(e.getMessage());
			System.out.println("Duplication of delivery - Exception - " + e.getMessage());
			Assert.fail("Duplication of delivery - Exception - " + e.getMessage());
		}
	}

	public void duplicateUPI(String upiReference, String upi)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {
			jdaHomePage.navigateToUpiReceiptHeaderPage();
			System.out.println("inside after query click ui");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click ui");
			upiReceiptHeaderPage.enterPalletWithReference(upiReference);
			jdaFooter.clickExecuteButton();
			if (upiReceiptHeaderPage.isNoRecordFound()) {
				System.out.println(invalidTesDataerrMsg + " Invalid upi reference-->" + upiReference);
				context.setErrorMessage(invalidTesDataerrMsg + " Invalid upi reference-->" + upiReference);
				Assert.fail(invalidTesDataerrMsg + " Invalid upi-->" + upiReference);
				}
		

			 else {
				// Clicking query for the second time to ensure test case
				jdaFooter.clickQueryButton();
				System.out.println("inside after query click ui");
				if (screen.exists("images/AfterQueryClick.png") != null) {
					upiReceiptHeaderPage.enterPalletWithReference(upiReference);
					jdaFooter.clickExecuteButton();
					if (deliveryPage.isNoRecordFound()) {
						Assert.assertTrue("No upi data present in UI ", false);
					}
				} else {
					Assert.fail("Application Issue - Query button not clicked");
				}
			}
			if (upiReceiptHeaderPage.isEJBerrorfound()) {
				System.out.println(ejbErrMsg);
				context.setErrorMessage(ejbErrMsg);
				Assert.fail(ejbErrMsg);
				
			}

			screen.rightClick();
			Thread.sleep(2000);
			screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
			screen.click("images/DuplicateOption/duplicate.png");
			Thread.sleep(2000);

			screen.type("a", Key.CTRL);
			jdaFooter.pressBackSpace();
			upiReceiptHeaderPage.enterPalletWithReference(upi);
			jdaFooter.clickExecuteButton();

			if (deliveryPage.isEJBerrorfound()) {
				Thread.sleep(2000);
				screen.wait("images/UpiReceiptHeader/MiscellaneousTab.png", timeoutInSec);
				screen.click("images/UpiReceiptHeader/MiscellaneousTab.png");
				Thread.sleep(2000);
				screen.wait("images/IgnoreMergeRulesUnchecked.png", timeoutInSec);
				screen.click("images/IgnoreMergeRulesUnchecked.png");
				Thread.sleep(2000);
				jdaFooter.clickExecuteButton();
				context.setErrorMessage("EJB Error---> Ignore merge rules checked");
			} else {
				context.setErrorMessage("NA");
				System.out.println("1 EJB err not found");
			}
			if (screen.exists("images/SaveModifications.png") != null) {
				System.out.println("Save Modifications");
				jdaFooter.PressEnter();
			}
			if (screen.exists("images/DefaultToReleased.png") != null) {
				System.out.println("Default to Released");
				jdaFooter.PressEnter();
			}
			if (screen.exists("images/DuplicateLines.png") != null) {
				System.out.println("Duplicating Lines..");
				jdaFooter.PressEnter();
			}

			if (deliveryPage.isEJBerrorfound()) {
				System.out.println("EJB Error even after merge rules - Fail");
				context.setErrorMessage(ejbErrMsg);
				Assert.fail(ejbErrMsg+" Failed even after enabling the ignore merge rules checked..");
			} else {
				System.out.println("2 EJB err not found");
			}
			context.setUpiId(upi);
			System.out.println("UPI Id" + context.getUpiId());
			Assert.assertEquals("No UPI ID in Oracle DB", upi, uPIReceiptHeaderDB.getUpiIdForUPI(context.getUpiId()));
		} catch (Exception e) {
			context.setErrorMessage(e.getMessage());
			System.out.println("Duplication of UPI - Exception - " + e.getMessage());
			Assert.fail("Duplication of UPI - Exception - " + e.getMessage());
		}
	}

	public void duplicatePO(String poReference, String po)
			throws ClassNotFoundException, FindFailed, InterruptedException, SQLException {
		try {

			jdaHomePage.navigateToPreAdviceHeaderMaintenance();
			System.out.println("inside after query click po");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click po");
			preAdviceHeaderPage.enterPreAdviceID(poReference);
			jdaFooter.clickExecuteButton();

			if (preAdviceHeaderPage.isNoRecordFound()) {
				System.out.println(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + poReference);
				context.setErrorMessage(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + poReference);
				Assert.fail(invalidTesDataerrMsg + " Invalid Purchase Order reference-->" + poReference);
			}

			else {
				// Clicking query for the second time to ensure test case
				jdaFooter.clickQueryButton();
				System.out.println("inside after query click po");
				if (screen.exists("images/AfterQueryClick.png") != null) {
					preAdviceHeaderPage.enterPreAdviceID(poReference);
					jdaFooter.clickExecuteButton();
					if (deliveryPage.isNoRecordFound()) {
						System.out.println(
								"No po data present in UI due to invalid test data. Please check with project team to get valid data:-"
										+ poReference);
						Assert.fail("No po data present in UI");
					}
				} else {
					Assert.fail("Application Issue - Query button not clicked");
				}
				if (preAdviceHeaderPage.isEJBerrorfound()) {
					System.out.println(ejbErrMsg);
					context.setErrorMessage(ejbErrMsg);
					Assert.fail(ejbErrMsg);
				}
				if (deliveryPage.isNoRecordFound()) {
					System.out.println(
							"No po data present in UI due to invalid test data. Please check with project team to get valid data:-"
									+ poReference);
					Assert.fail("No po data present in UI");
				}

				screen.rightClick();
				Thread.sleep(2000);
				screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
				screen.click("images/DuplicateOption/duplicate.png");
				Thread.sleep(2000);
				screen.type("a", Key.CTRL);
				jdaFooter.pressBackSpace();

				preAdviceHeaderPage.enterPreAdviceID(po);

				jdaFooter.clickExecuteButton();
				if (deliveryPage.isEJBerrorfound()) {
					Thread.sleep(2000);
					screen.wait("images/UpiReceiptHeader/MiscellaneousTab.png", timeoutInSec);
					screen.click("images/UpiReceiptHeader/MiscellaneousTab.png");
					Thread.sleep(2000);
					screen.wait("images/IgnoreMergeRulesUnchecked.png", timeoutInSec);
					screen.click("images/IgnoreMergeRulesUnchecked.png");
					Thread.sleep(2000);
					jdaFooter.clickExecuteButton();
					context.setErrorMessage("EJB Error---> Ignore merge rules checked");
				} else {
					context.setErrorMessage("NA");
					System.out.println("1 EJB err not found");
				}
				if (screen.exists("images/SaveModifications.png") != null) {
					System.out.println("Save Modifications");
					jdaFooter.PressEnter();
					Thread.sleep(4000);
				}
				if (screen.exists("images/DefaultToReleased.png") != null) {
					System.out.println("Default to Released");
					jdaFooter.PressEnter();
					Thread.sleep(2000);
				}
				if (screen.exists("images/DuplicateLines.png") != null) {
					System.out.println("Duplicating Lines..");
					jdaFooter.PressEnter();
					Thread.sleep(2000);
				}
				Thread.sleep(3000);
				if (deliveryPage.isEJBerrorfound()) {
					System.out.println(ejbErrMsg);
					context.setErrorMessage(ejbErrMsg);
					Assert.fail(ejbErrMsg+"Failed even after enabling the ignore merge rules checked..");
				} else {
					System.out.println("2 EJB err not found");
				}
				context.setPreAdviceId(po);
				Assert.assertEquals("No PO ID in Oracle DB", po, preAdviceHeaderDB.getPreAdviceIdForPO(po));
			}
		} catch (Exception e) {
			context.setErrorMessage("Duplication of PO - Exception - " + e.getMessage());
			System.out.println("Duplication of PO - Exception - " + e.getMessage());
			Assert.fail("Duplication of PO - Exception - " + e.getMessage());
		}
	}

	public void duplicateOdn(String orderReference, String order)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		Thread.sleep(2000);
		System.out.println("Order Reference" + orderReference);
		orderHeaderPage.enterOrderID(orderReference);

		jdaFooter.clickExecuteButton();
		if (orderHeaderPage.isNoRecordFound()) {
			System.out.println(invalidTesDataerrMsg + " Invalid upi reference-->" + orderReference);
			context.setErrorMessage(invalidTesDataerrMsg + " Invalid upi reference-->" + orderReference);
			Assert.fail(invalidTesDataerrMsg + " Invalid upi-->" + orderReference);
			
		}
		if (orderHeaderPage.isEJBerrorfound()) {
			System.out.println(ejbErrMsg);
			context.setErrorMessage(ejbErrMsg);
			Assert.fail(ejbErrMsg);
		}
		screen.rightClick();
		Thread.sleep(2000);
		screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
		screen.click("images/DuplicateOption/duplicate.png");
		Thread.sleep(2000);
		screen.type("a", Key.CTRL);
		jdaFooter.pressBackSpace();

		orderHeaderPage.enterOrderID(order);

		orderHeaderPage.clickDeliveryAddressTab();
		orderHeaderPage.clickGLN();
		Thread.sleep(1000);
		screen.type("a", Key.CTRL);
		jdaFooter.pressBackSpace();

		jdaFooter.clickExecuteButton();

		if (deliveryPage.isEJBerrorfound()) {
			Thread.sleep(2000);
			screen.wait("images/UpiReceiptHeader/MiscellaneousTab.png", timeoutInSec);
			screen.click("images/UpiReceiptHeader/MiscellaneousTab.png");
			Thread.sleep(2000);
			screen.wait("images/IgnoreMergeRulesUnchecked.png", timeoutInSec);
			screen.click("images/IgnoreMergeRulesUnchecked.png");
			Thread.sleep(2000);
			jdaFooter.clickExecuteButton();
			context.setErrorMessage("EJB Error---> Ignore merge rules checked");
		} else {
			context.setErrorMessage("NA");
			System.out.println("1 EJB err not found");
		}
		if (screen.exists("images/SaveModifications.png") != null) {
			System.out.println("Save Modifications");
			jdaFooter.PressEnter();
		}
		if (screen.exists("images/DefaultToReleased.png") != null) {
			System.out.println("Default to Released");
			jdaFooter.PressEnter();
			Thread.sleep(2000);
		}
		if (screen.exists("images/DuplicateLines.png") != null) {
			System.out.println("Duplicating Lines..");
			jdaFooter.PressEnter();
			Thread.sleep(2000);

		}
		if (deliveryPage.isEJBerrorfound()) {
			System.out.println(ejbErrMsg);
			context.setErrorMessage(ejbErrMsg);
			Assert.fail(ejbErrMsg+" Failed even after enabling the ignore merge rules checked..");
		} else {
			System.out.println("2 EJB err not found");
		}
		context.setOrderId(order);
		Assert.assertEquals("No PO ID in Oracle DB", order, orderHeaderDB.getOrderIdForOdn(order));
	}

	/*
	 * public void duplicateOdn(String orderReference, String order) throws
	 * FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	 * System.out.println("CHECK333433");
	 * jdaHomePage.navigateToOrderHeaderMaintenance();
	 * jdaFooter.clickQueryButton();
	 * orderHeaderPage.enterOrderID(orderReference);
	 * 
	 * jdaFooter.clickExecuteButton(); if(orderHeaderPage.isNoRecordFound()){
	 * Assert.assertTrue("No Order data present in UI ", false); }
	 * if(orderHeaderPage.isEJBerrorfound()){ Assert.assertTrue(
	 * "EJB error found", false); } screen.rightClick(); Thread.sleep(2000);
	 * screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
	 * screen.click("images/DuplicateOption/duplicate.png"); Thread.sleep(2000);
	 * screen.type("a", Key.CTRL); jdaFooter.pressBackSpace();
	 * 
	 * 
	 * orderHeaderPage.enterOrderID(order);
	 * 
	 * jdaFooter.clickExecuteButton(); jdaFooter.PressEnter();
	 * jdaFooter.PressEnter(); jdaFooter.PressEnter();
	 * 
	 * context.setOrderId(order); Assert.assertEquals("No PO ID in Oracle DB"
	 * ,order, orderHeaderDB.getOrderIdForOdn(order)); }
	 */
	public void killBrowser() throws IOException {

		// Process killIE = Runtime.getRuntime()
		// .exec("cmd /c taskkill /F /IM iexplore.exe /FI \"USERNAME eq
		// %username%\"");
		Process killChrome = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM chrome.exe /FI \"USERNAME eq %username%\"");
		Process killFirefox = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM firefox.exe /FI \"USERNAME eq %username%\"");

		Process killGeckoDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM geckodriver.exe /FI \"USERNAME eq %username%\"");
		Process killChromeDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM chromedriver.exe /FI \"USERNAME eq %username%\"");

		Process killIeDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM IEDriverServer.exe /FI \"USERNAME eq %username%\"");
	}
}

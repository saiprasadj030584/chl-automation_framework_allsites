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
	private Context context;
	private OrderHeaderPage orderHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private DeliveryPage deliveryPage;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public DataLoadFromUI(JdaHomePage jdaHomePage, JDAFooter jdaFooter, DeliveryDB deliveryDB,
			OrderHeaderPage orderHeaderPage, Context context, UpiReceiptHeaderPage upiReceiptHeaderPage,
			PreAdviceHeaderDB preAdviceHeaderDB, PreAdviceHeaderPage preAdviceHeaderPage, OrderHeaderDB orderHeaderDB,
			UPIReceiptHeaderDB uPIReceiptHeaderDB, DeliveryPage deliveryPage) {

		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.deliveryPage = deliveryPage;
		this.context = context;
		this.deliveryDB = deliveryDB;
		this.upiReceiptHeaderPage = upiReceiptHeaderPage;
		this.preAdviceHeaderPage = preAdviceHeaderPage;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.uPIReceiptHeaderDB = uPIReceiptHeaderDB;
		this.orderHeaderPage = orderHeaderPage;
		this.orderHeaderDB = orderHeaderDB;
		this.deliveryPage = deliveryPage;
	}

	public void duplicateASN(String asnReference, String asn)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {
			jdaHomePage.navigateToDeliveryPage();
			boolean screenFound = deliveryPage.deliveryHomePage();
			System.out.println("Delivery Page Check" + screenFound);
			Assert.assertEquals(true, screenFound);

			System.out.println("inside after query click asn");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click asn");
			if (screen.exists("images/AfterQueryClick.png") != null) {
				deliveryPage.enterAsnId(asnReference);
				jdaFooter.clickExecuteButton();
				if (deliveryPage.isNoRecordFound()) {
					Assert.assertTrue("No ASN data present in UI ", false);
				}
			} else {
				// Clicking query for the second time to ensure test case
				jdaFooter.clickQueryButton();
				System.out.println("inside after query click asn");
				if (screen.exists("images/AfterQueryClick.png") != null) {
					deliveryPage.enterAsnId(asnReference);
					jdaFooter.clickExecuteButton();
					if (deliveryPage.isNoRecordFound()) {
						Assert.assertTrue("No ASN data present in UI ", false);
					}
				} else {
					Assert.fail("Application Issue - Query button not clicked");
				}
			}

			if (deliveryPage.isEJBerrorfound()) {
				Assert.assertTrue("EJB error found", false);
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
				context.setEJBErrorMsg("EJB Error---> Ignore merge rules checked");
			} else {
				context.setEJBErrorMsg("NA");
				System.out.println("1 EJB err not found");
			}
			// jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			if (deliveryPage.isEJBerrorfound()) {
				Assert.fail("Failed even after enabling the ignore merge rules checked..");
			} else {
				System.out.println("2 EJB err not found");
			}
			context.setAsnId(asn);
			Assert.assertEquals("No ASN ID in Oracle DB", asn, deliveryDB.getAsnIdForASN(context.getAsnId()));
		} catch (

		Exception e) {
			context.setEJBErrorMsg(e.getMessage());
			System.out.println("Duplication of delivery - Exception - " + e.getMessage());
			Assert.fail("Duplication of delivery - Exception - " + e.getMessage());
		}
	}

	public void duplicateUPI(String upiReference, String upi)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		try {
			jdaHomePage.navigateToUpiReceiptHeaderPage();
			boolean screenFound =upiReceiptHeaderPage.upiHomePage();
			System.out.println("upi Page Check" + screenFound);
			Assert.assertEquals(true, screenFound);
			System.out.println("inside after query click ui");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click ui");
			System.out.println("CHECKKKKK1");
			upiReceiptHeaderPage.enterPalletWithReference(upiReference);
			jdaFooter.clickExecuteButton();
			if (upiReceiptHeaderPage.isNoRecordFound()) {
				Assert.assertTrue("No upi data present in UI ", false);

			} else {
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
				Assert.assertTrue("EJB error found", false);
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
				context.setEJBErrorMsg("EJB Error---> Ignore merge rules checked");
			} else {
				context.setEJBErrorMsg("NA");
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
				Assert.fail("Failed even after enabling the ignore merge rules checked..");
			} else {
				System.out.println("2 EJB err not found");
			}
			context.setUpiId(upi);
			System.out.println("UPI Id" + context.getUpiId());
			Assert.assertEquals("No UPI ID in Oracle DB", upi, uPIReceiptHeaderDB.getUpiIdForUPI(context.getUpiId()));
		} catch (Exception e) {
			context.setEJBErrorMsg(e.getMessage());
			System.out.println("Duplication of UPI - Exception - " + e.getMessage());
			Assert.fail("Duplication of UPI - Exception - " + e.getMessage());
		}
	}

	public void duplicatePO(String poReference, String po)
			throws ClassNotFoundException, FindFailed, InterruptedException, SQLException {
		try {

			jdaHomePage.navigateToPreAdviceHeaderMaintenance();
			boolean screenFound = preAdviceHeaderPage.preadviceHomePage();
			System.out.println("Pre advice found" + screenFound);
			Assert.assertEquals(true, screenFound);
			System.out.println("inside after query click po");
			jdaFooter.clickQueryButton();
			System.out.println("inside after query click po");
			preAdviceHeaderPage.enterPreAdviceID(poReference);
			jdaFooter.clickExecuteButton();

			if (preAdviceHeaderPage.isNoRecordFound()) {
				Assert.assertTrue("No po data present in UI ", false);
			}

			else {
				// Clicking query for the second time to ensure test case
				jdaFooter.clickQueryButton();
				System.out.println("inside after query click po");
				if (screen.exists("images/AfterQueryClick.png") != null) {
					preAdviceHeaderPage.enterPreAdviceID(poReference);
					jdaFooter.clickExecuteButton();
					if (deliveryPage.isNoRecordFound()) {
						Assert.assertTrue("No po data present in UI ", false);
					}
				} else {
					Assert.fail("Application Issue - Query button not clicked");
				}
				if (preAdviceHeaderPage.isEJBerrorfound()) {
					Assert.assertTrue("EJB error found", false);
				}

				if (deliveryPage.isNoRecordFound()) {
					Assert.assertTrue("No po data present in po ", false);
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
					context.setEJBErrorMsg("EJB Error---> Ignore merge rules checked");
				} else {
					context.setEJBErrorMsg("NA");
					System.out.println("1 EJB err not found");
				}
				if (screen.exists("images/SaveModifications.png") != null) {
					System.out.println("Save Modifications");
					jdaFooter.PressEnter();
					Thread.sleep(2000);
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
					Assert.fail("Failed even after enabling the ignore merge rules checked..");
				} else {
					System.out.println("2 EJB err not found");
				}
				context.setPreAdviceId(po);
				Assert.assertEquals("No PO ID in Oracle DB", po, preAdviceHeaderDB.getPreAdviceIdForPO(po));
			}
		} catch (Exception e) {
			context.setEJBErrorMsg(e.getMessage());
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
			Assert.assertTrue("No Order data present in UI ", false);
		}
		if (orderHeaderPage.isEJBerrorfound()) {
			Assert.assertTrue("EJB error found", false);
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
			context.setEJBErrorMsg("EJB Error---> Ignore merge rules checked");
		} else {
			context.setEJBErrorMsg("NA");
			System.out.println("1 EJB err not found");
		}

		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		if (deliveryPage.isEJBerrorfound()) {
			Assert.fail("Failed even after enabling the ignore merge rules checked..");
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

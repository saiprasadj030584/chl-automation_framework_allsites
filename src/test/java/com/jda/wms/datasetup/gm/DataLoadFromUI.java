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
	private DeliveryPage deliveryPage;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;
	private DeliveryDB deliveryDB;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private UPIReceiptHeaderDB uPIReceiptHeaderDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private Context context;
	private OrderHeaderPage orderHeaderPage;
	private OrderHeaderDB orderHeaderDB;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public DataLoadFromUI(JdaHomePage jdaHomePage, JDAFooter jdaFooter, DeliveryPage deliveryPage,
			DeliveryDB deliveryDB, OrderHeaderPage orderHeaderPage, Context context,
			UpiReceiptHeaderPage upiReceiptHeaderPage, PreAdviceHeaderDB preAdviceHeaderDB,
			PreAdviceHeaderPage preAdviceHeaderPage, OrderHeaderDB orderHeaderDB,
			UPIReceiptHeaderDB uPIReceiptHeaderDB) {

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
	}

	public void duplicateASN(String asnReference, String asn)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		jdaHomePage.navigateToDeliveryPage();
		jdaFooter.clickQueryButton();
		deliveryPage.enterAsnId(asnReference);
		jdaFooter.clickExecuteButton();
		if (deliveryPage.isNoRecordFound()) {
			Assert.assertTrue("No ASN data present in UI ", false);
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
		} else {
			System.out.println("EJB err not found");
		}
		// jdaFooter.PressEnter();
		jdaFooter.PressEnter();

		context.setAsnId(asn);
		Assert.assertEquals("No ASN ID in Oracle DB", asn, deliveryDB.getAsnIdForASN(context.getAsnId()));
	}

	public void duplicateUPI(String upiReference, String upi)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		jdaHomePage.navigateToUpiReceiptHeaderPage();
		jdaFooter.clickQueryButton();
		System.out.println("CHECKKKKK1");
		upiReceiptHeaderPage.enterPalletWithReference(upiReference);
		jdaFooter.clickExecuteButton();
		if (upiReceiptHeaderPage.isNoRecordFound()) {
			Assert.assertTrue("No upi data present in UI ", false);
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
		}
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		context.setUpiId(upi);
		System.out.println("UPI Id" + context.getUpiId());
		Assert.assertEquals("No UPI ID in Oracle DB", upi, uPIReceiptHeaderDB.getUpiIdForUPI(context.getUpiId()));

	}

	public void duplicatePO(String poReference, String po)
			throws ClassNotFoundException, FindFailed, InterruptedException, SQLException {

		jdaHomePage.navigateToPreAdviceHeaderMaintenance();
		jdaFooter.clickQueryButton();
		preAdviceHeaderPage.enterPreAdviceID(poReference);
		jdaFooter.clickExecuteButton();

		if (preAdviceHeaderPage.isNoRecordFound()) {
			Assert.assertTrue("No po data present in UI ", false);
		}
		if (preAdviceHeaderPage.isEJBerrorfound()) {
			Assert.assertTrue("EJB error found", false);
		}

		if (deliveryPage.isNoRecordFound()) {
			Assert.assertTrue("No po data present in UI ", false);
		}

		screen.rightClick();
		Thread.sleep(2000);
		screen.wait("images/DuplicateOption/duplicate.png", timeoutInSec);
		screen.click("images/DuplicateOption/duplicate.png");
		Thread.sleep(2000);
		screen.type("a", Key.CTRL);
		jdaFooter.pressBackSpace();

		preAdviceHeaderPage.enterPreAdviceID(po);
		;
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
		}
		jdaFooter.PressEnter();
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(3000);

		context.setPreAdviceId(po);
		Assert.assertEquals("No PO ID in Oracle DB", po, preAdviceHeaderDB.getPreAdviceIdForPO(po));
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
		}
		
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();

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

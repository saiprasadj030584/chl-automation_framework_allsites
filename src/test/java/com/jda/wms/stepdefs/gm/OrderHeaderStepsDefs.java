package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.ConsignmentDB;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.OrderPreparationPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class OrderHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private InventoryDB inventoryDB;
	private OrderLineDB orderLineDB;
	private OrderHeaderPage orderHeaderPage;
	private SkuDB skuDB;
	private LocationDB locationDb;
	private OrderPreparationPage orderPreparationPage;
	private ConsignmentDB consignmentDB;
	private JdaHomePage jDAHomePage;
	private InventoryQueryPage inventoryQueryPage;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private SkuSkuConfigDB skuSkuConfigDB;

	@Inject
	public OrderHeaderStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, PreAdviceLineStepDefs preAdviceLineStepDefs,
			PreAdviceLineDB preAdviceLineDB, UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage,
			OrderHeaderDB orderHeaderDB, InventoryDB inventoryDB, OrderLineDB orderLineDB,
			OrderHeaderPage orderHeaderPage,SkuDB skuDB,LocationDB locationDb,OrderPreparationPage orderPreparationPage,ConsignmentDB consignmentDB,JdaHomePage jDAHomePage,InventoryQueryPage inventoryQueryPage,StockAdjustmentsPage stockAdjustmentsPage,SkuSkuConfigDB skuSkuConfigDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.inventoryDB = inventoryDB;
		this.orderLineDB = orderLineDB;
		this.orderHeaderPage = orderHeaderPage;
		this.skuDB=skuDB;
		this.jdaLoginStepDefs=jdaLoginStepDefs;
		this.locationDb=locationDb;
		this.orderPreparationPage=orderPreparationPage;
		this.consignmentDB=consignmentDB;
		this.jdaHomePage=jDAHomePage;
		this.inventoryQueryPage=inventoryQueryPage;
		this.stockAdjustmentsPage=stockAdjustmentsPage;
		this.skuSkuConfigDB=skuSkuConfigDB;

	}

	@Given("^the order id \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_id_should_be_in_status(String orderNumber, String status) throws Throwable {
		context.setOrderId(orderNumber);
		context.setStatus(status);
		Assert.assertEquals("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber));
		// jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}

	@Given("^order header should be updated for picked stock$")
	public void order_header_should_be_updated_for_picked_stock() throws Throwable {
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();
		Assert.assertEquals("Order header not displayed as expected", "Picked",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^order header should be updated for unpicked stock$")
	public void order_header_should_be_updated_for_unpicked_stock() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();

		Assert.assertEquals("Order header not displayed as expected", "Released",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	
	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status and \"([^\"]*)\" skus should be in \"([^\"]*)\" location$")
	public void the_order_id_of_type_should_be_in_status_and_skus_should_be_in_location(
			String orderType, String status,String skuType,String locationId) throws Throwable {
		
		String orderNumber=context.getOrderId();
		
		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setStatus(status);
		context.setLocationID(locationId);

		//calculate qty available for allocation
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation=false;
		// suspense
				
				for (int i = 0; i < skuFromOrder.size(); i++)
				{
					ArrayList<String>  locationList=inventoryDB.getLocationsForSku((String)skuFromOrder.get(i));
					System.out.println(locationList);
					ArrayList<String> validLocations=new ArrayList<String>();
					int totalQtyOnHand=0;
					for (int j = 0; j < locationList.size(); j++)
					{
						if(locationDb.getLocationZone(locationList.get(j))!=null)
								{
							if(context.getSKUType().equalsIgnoreCase("Hanging") || context.getSKUType().equalsIgnoreCase("GOH"))
							{
						if(locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("HANG"))
				{
							System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
							if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
							{
							System.out.println("entered"+locationList.get(j));
					validLocations.add(locationList.get(j));
					totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
							}
				}
							}
							
							else if(context.getSKUType().equalsIgnoreCase("Boxed") )
							{
						if(locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("BOX"))
				{
							System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
							if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
							{
							System.out.println("entered"+locationList.get(j));
					validLocations.add(locationList.get(j));
					totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
							}
				}
							}
							
							else if(context.getSKUType().equalsIgnoreCase("Flatpack"))
							{
						if((locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")) || (locationDb.getLocationZone(locationList.get(j)).contains("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")))
				{
							System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
							if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
							{
							System.out.println("entered"+locationList.get(j));
					validLocations.add(locationList.get(j));
					totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
							}
				}
							}
						
								}
					}
					System.out.println(validLocations);
					System.out.println("totalQtyOnHand"+totalQtyOnHand);
					if(totalQtyOnHand>=Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),(String)skuFromOrder.get(i))))
					{
						allocation=true;
					}
					
					if(context.getLocationID()!=null)
					{
					if(context.getLocationID().equalsIgnoreCase("suspense"))
							{
							Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
									inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
							Assert.assertFalse("Stock is present in other locations" ,
									allocation);
							}
					}
					else
					{

//							Assert.assertTrue("Stock is not present in other locations" ,
//									allocation);
						
						System.out.println("Stock is not present in other locations");
						
						if(!(allocation))
						{
							//default:stock adjustment to qty 500
							jDAHomePage.navigateToStockAdjustment();
							Thread.sleep(2000);
							stockAdjustmentsPage.selectNewStock();
							jdaFooter.clickNextButton();
							Thread.sleep(2000);
							stockAdjustmentsPage.enterSkuId(context.getSkuId());
							jdaFooter.pressTab();
//							if(context.getSKUType().equalsIgnoreCase("Boxed")|| context.getSKUType().equalsIgnoreCase("Flatpack"))
//							{
//							stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("BOX").get(0));
//							}
//							else if(context.getSKUType().equalsIgnoreCase("GOH")|| context.getSKUType().equalsIgnoreCase("Hanging"))
//							{
					//		stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("HANG").get(0));
							
							stockAdjustmentsPage.enterLocation(validLocations.get(0));
							
							//}
							stockAdjustmentsPage.enterSiteId(context.getSiteId());
							stockAdjustmentsPage.enterQuantityOnHand("500");
							stockAdjustmentsPage.enterOrigin("NONE");
							
							stockAdjustmentsPage.enterPackConfig(skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
							jdaFooter.clickNextButton();
							stockAdjustmentsPage.enterPalletType("PALLET");
							jdaFooter.clickNextButton();
							stockAdjustmentsPage.enterReasonCode();
							jdaFooter.clickDoneButton();
							stockAdjustmentsPage.handlePopUp();
				
						}

						

							
					}
				

		// To Validate Modularity,New Product Check for SKU

			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			// TODO Check for multiple skus
			context.setSKUType(type);

			verification.verifyData("SKU Type", type, skuDB.getSKUType((String)skuFromOrder.get(i)), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String)skuFromOrder.get(i)),
					failureList);
		}
	
		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// //Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		// jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	@Given("^the order id of type \"([^\"]*)\" with \"([^\"]*)\" skus should be in \"([^\"]*)\" status$")
	public void the_order_id_of_type_with_skus_should_be_in_status(String orderType,String skuType,String status)
			throws Throwable {
		
		String orderNumber=context.getOrderId();
		
		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		// suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation=false;
		for (int i = 0; i < skuFromOrder.size(); i++)
		{
			ArrayList<String>  locationList=inventoryDB.getLocationsForSku((String)skuFromOrder.get(i));
			System.out.println(locationList);
			ArrayList<String> validLocations=new ArrayList<String>();
			int totalQtyOnHand=0;
			for (int j = 0; j < locationList.size(); j++)
			{
				if(locationDb.getLocationZone(locationList.get(j))!=null)
						{
					if(context.getSKUType().equalsIgnoreCase("Hanging") || context.getSKUType().equalsIgnoreCase("GOH"))
					{
				if(locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("HANG"))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
					
					else if(context.getSKUType().equalsIgnoreCase("Boxed") )
					{
				if(locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("BOX"))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
					
					else if(context.getSKUType().equalsIgnoreCase("Flatpack"))
					{
				if((locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")) || (locationDb.getLocationZone(locationList.get(j)).contains("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
				
						}
			}
			System.out.println(validLocations);
			System.out.println("totalQtyOnHand"+totalQtyOnHand);
			if(totalQtyOnHand>=Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),(String)skuFromOrder.get(i))))
			{
				allocation=true;
			}
			
			if(context.getLocationID()!=null)
			{
			if(context.getLocationID().equalsIgnoreCase("suspense"))
					{
					Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
							inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
					Assert.assertFalse("Stock is present in other locations" ,
							allocation);
					}
			}
			else
			{

//					Assert.assertTrue("Stock is not present in other locations" ,
//							allocation);
				
				System.out.println("Stock is not present in other locations");
				
				if(!(allocation))
				{
					//default:stock adjustment to qty 500
					jDAHomePage.navigateToStockAdjustment();
					Thread.sleep(2000);
					stockAdjustmentsPage.selectNewStock();
					jdaFooter.clickNextButton();
					Thread.sleep(2000);
					stockAdjustmentsPage.enterSkuId(context.getSkuId());
					jdaFooter.pressTab();
//					if(context.getSKUType().equalsIgnoreCase("Boxed")|| context.getSKUType().equalsIgnoreCase("Flatpack"))
//					{
//					stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("BOX").get(0));
//					}
//					else if(context.getSKUType().equalsIgnoreCase("GOH")|| context.getSKUType().equalsIgnoreCase("Hanging"))
//					{
			//		stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("HANG").get(0));
					
					stockAdjustmentsPage.enterLocation(validLocations.get(0));
					
					//}
					stockAdjustmentsPage.enterSiteId(context.getSiteId());
					stockAdjustmentsPage.enterQuantityOnHand("500");
					stockAdjustmentsPage.enterOrigin("NONE");
					
					stockAdjustmentsPage.enterPackConfig(skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
					jdaFooter.clickNextButton();
					stockAdjustmentsPage.enterPalletType("PALLET");
					jdaFooter.clickNextButton();
					stockAdjustmentsPage.enterReasonCode();
					jdaFooter.clickDoneButton();
					stockAdjustmentsPage.handlePopUp();
		
				}

				

					
			}
		
		
			
		// To Validate Modularity,New Product Check for SKU

					String type = null;
					switch (context.getSKUType()) {
					case "Boxed":
						type = "B";
						break;
					case "Hanging":
						type = "H";
						break;
					case "Flatpack":
						type = "P";
						break;
					case "GOH":
						type = "C";
						break;
					}
					// TODO Check for multiple skus

					verification.verifyData("SKU Type", type, skuDB.getSKUType((String)skuFromOrder.get(i)), failureList);
					verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String)skuFromOrder.get(i)),
							failureList);
				}

		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// Order status
//		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
//				failureList);

		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^the multiple order id of type \"([^\"]*)\" with \"([^\"]*)\" skus should be in \"([^\"]*)\" status$")
	public void the_multiple_order_id_of_type_with_skus_should_be_in_status(String orderType,String skuType,String status)
			throws Throwable {
		
		String orderNumber1=context.getOrderId();
		String orderNumber2=context.getOrderId1();
		
		String orderNumber=orderNumber1+","+orderNumber2;
		
		String[] orderArray = orderNumber.split(",");
		ArrayList<String> orderList=new ArrayList<String>();
		for (int i = 0; i < orderArray.length; i++) {
			orderList.add(orderArray[i]);
		}
		context.setOrderList(orderList);
		
		context.setSKUType(skuType);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		// suspense
		for(int k=0;k<context.getOrderList().size();k++)
		{
			context.setOrderId(context.getOrderList().get(k));
		
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation=false;
		for (int i = 0; i < skuFromOrder.size(); i++)
		{
			ArrayList<String>  locationList=inventoryDB.getLocationsForSku((String)skuFromOrder.get(i));
			System.out.println(locationList);
			ArrayList<String> validLocations=new ArrayList<String>();
			int totalQtyOnHand=0;
			for (int j = 0; j < locationList.size(); j++)
			{
				if(locationDb.getLocationZone(locationList.get(j))!=null)
						{
					if(context.getSKUType().equalsIgnoreCase("Hanging") || context.getSKUType().equalsIgnoreCase("GOH"))
					{
				if(locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("HANG"))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
					
					else if(context.getSKUType().equalsIgnoreCase("Boxed") )
					{
				if(locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("BOX"))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
					
					else if(context.getSKUType().equalsIgnoreCase("Flatpack"))
					{
				if((locationDb.getLocationZone(locationList.get(j)).contains("BOX") && locationDb.getUserDefType2(locationList.get(j)).contains("BOX") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")) || (locationDb.getLocationZone(locationList.get(j)).contains("HANG") && locationDb.getUserDefType2(locationList.get(j)).contains("HANG") && locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")))
		{
					System.out.println(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)));
					if(inventoryDB.getLockStatus(locationList.get(j),(String)skuFromOrder.get(i)).equalsIgnoreCase("UnLocked"))
					{
					System.out.println("entered"+locationList.get(j));
			validLocations.add(locationList.get(j));
			totalQtyOnHand+=Integer.parseInt(inventoryDB.getQtyForSkuInLocation((String)skuFromOrder.get(i),locationList.get(j)));
					}
		}
					}
				
						}
			}
			System.out.println(validLocations);
			System.out.println("totalQtyOnHand"+totalQtyOnHand);
			if(totalQtyOnHand>=Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),(String)skuFromOrder.get(i))))
			{
				allocation=true;
			}
			
			if(context.getLocationID()!=null)
			{
			if(context.getLocationID().equalsIgnoreCase("suspense"))
					{
					Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
							inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
					Assert.assertFalse("Stock is present in other locations" ,
							allocation);
					}
			}
			else
			{

//					Assert.assertTrue("Stock is not present in other locations" ,
//							allocation);
				
				System.out.println("Stock is not present in other locations");
				
				if(!(allocation))
				{
					//default:stock adjustment to qty 500
					jDAHomePage.navigateToStockAdjustment();
					Thread.sleep(2000);
					stockAdjustmentsPage.selectNewStock();
					jdaFooter.clickNextButton();
					Thread.sleep(2000);
					stockAdjustmentsPage.enterSkuId(context.getSkuId());
					jdaFooter.pressTab();
//					if(context.getSKUType().equalsIgnoreCase("Boxed")|| context.getSKUType().equalsIgnoreCase("Flatpack"))
//					{
//					stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("BOX").get(0));
//					}
//					else if(context.getSKUType().equalsIgnoreCase("GOH")|| context.getSKUType().equalsIgnoreCase("Hanging"))
//					{
			//		stockAdjustmentsPage.enterLocation(locationDb.getLocationForZone("HANG").get(0));
					
					stockAdjustmentsPage.enterLocation(validLocations.get(0));
					
					//}
					stockAdjustmentsPage.enterSiteId(context.getSiteId());
					stockAdjustmentsPage.enterQuantityOnHand("500");
					stockAdjustmentsPage.enterOrigin("NONE");
					
					stockAdjustmentsPage.enterPackConfig(skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
					jdaFooter.clickNextButton();
					stockAdjustmentsPage.enterPalletType("PALLET");
					jdaFooter.clickNextButton();
					stockAdjustmentsPage.enterReasonCode();
					jdaFooter.clickDoneButton();
					stockAdjustmentsPage.handlePopUp();
		
				}

				

					
			}
		
			
		// To Validate Modularity,New Product Check for SKU

					String type = null;
					switch (context.getSKUType()) {
					case "Boxed":
						type = "B";
						break;
					case "Hanging":
						type = "H";
						break;
					case "Flatpack":
						type = "P";
						break;
					case "GOH":
						type = "C";
						break;
					
					}
					// TODO Check for multiple skus

					verification.verifyData("SKU Type", type, skuDB.getSKUType((String)skuFromOrder.get(i)), failureList);
					verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String)skuFromOrder.get(i)),
							failureList);
				}

		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(context.getOrderId()),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(context.getOrderId()),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(context.getOrderId()), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(context.getOrderId()), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(context.getOrderId()),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(context.getOrderId()), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(context.getOrderId()),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(context.getOrderId()),
					failureList);
		}
		

		// Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		}
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		i_create_a_consignment_for_multiple_order();

		
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^I create a consignment for order$")
	public void i_create_a_consignment_for_order() throws Throwable {
		// jdaLoginPage.login();
		jdaHomePage.navigateToOrderPreparationPage();
		jdaFooter.clickNextButton();
		orderPreparationPage.enterOrderId(context.getOrderId());
		jdaFooter.clickNextButton();
		String consignment = "CON" + Utilities.getTwoDigitRandomNumber();
		orderPreparationPage.createNewConsignment(consignment);
		orderPreparationPage.selectRecord();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
	}
	
	@Given("^I create a consignment for multiple order$")
	public void i_create_a_consignment_for_multiple_order() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String consignment = "CON" + Utilities.getThreeDigitRandomNumber();
		while(consignmentDB.isConsignmentExists(consignment))
		{
			consignment = "CON" + Utilities.getThreeDigitRandomNumber();
		}
		
		context.setConsignmentID(consignment);
		for(int k=0;k<context.getOrderList().size();k++)
		{
			context.setOrderId(context.getOrderList().get(k));
			orderHeaderDB.removeConsignment(context.getOrderId());
			if(k==0)
			{
		jdaHomePage.navigateToOrderPreparationPage();
		jdaFooter.clickNextButton();
		orderPreparationPage.enterOrderId(context.getOrderId());
		jdaFooter.clickNextButton();
		orderPreparationPage.createNewConsignment(context.getConsignmentID());
		orderPreparationPage.selectRecord();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		}
		else
		{
			jdaHomePage.navigateToOrderPreparationPage();
			orderPreparationPage.selectExisting();
			jdaFooter.clickNextButton();
			orderPreparationPage.enterConsignment(context.getConsignmentID());
			orderPreparationPage.enterOrderId(context.getOrderId());
			jdaFooter.clickNextButton();
			orderPreparationPage.selectRecord();
			jdaFooter.clickNextButton();
			orderPreparationPage.enterTrailerType("Trailer");
			jdaFooter.clickNextButton();
			jdaFooter.clickNextButton();
			jdaFooter.clickDoneButton();
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
		}
			verification.verifyData("consignment not updated"+context.getOrderId(), context.getConsignmentID(), orderHeaderDB.getConsignment(context.getOrderId()), failureList);
	}
		Assert.assertTrue("Consignment Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^the OrderID of type \"([^\"]*)\" for sku \"([^\"]*)\" should be in \"([^\"]*)\" status at site \"([^\"]*)\"$")
	public void the_OrderID_of_type_for_sku_should_be_in_status(String orderType, String skuType,String status, String siteId) throws Throwable {
		//jdaLoginPage.login();
		
		String orderId=context.getOrderId();
				
		context.setOrderId(orderId);
		context.setOrderType(orderType);
		context.setSiteId(siteId);
		String Type=null;
		switch(skuType){
		case "GOH":
			Type="C";
			break;
		case "flatpack":
			Type="P";
			break;
		case "Boxed":
			Type="B";
			break;
		case "Hanging":
			Type="H";
			break;			
		}		
		context.setSKUType(Type);
		context.setSkuId(orderHeaderDB.getSkuId(orderId));
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderID(orderId);
		jdaFooter.clickExecuteButton();
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer,ArrayList<String>>();
		verification.verifyData("Order Status", status, orderHeaderDB.getStatus(orderId), failureList);
		verification.verifyData("Order Type", orderType, orderHeaderDB.getOrderType(orderId), failureList);
		verification.verifyData("SKU Type", context.getSKUType(), skuDB.getSKUType(context.getSkuId()), failureList);
		verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
	    context.setSkuId(orderLineDB.getSkuId(orderId));
	  	Assert.assertTrue("Order Status details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the order stock modularity should be visible$")
	public void the_Order_stock_modularity_should_be_visible() throws Throwable {
		inventoryQueryPage.clickUserDefinedTab();
		
		if(context.getSKUType().equalsIgnoreCase("Boxed"))
		{
		Assert.assertEquals("No of Lines in PO and UPI Header do not match","BOX",
				orderHeaderDB.getStockModularity(context.getOrderId()));
		}
		else if(context.getSKUType().equalsIgnoreCase("Hanging"))
		{
		Assert.assertEquals("No of Lines in PO and UPI Header do not match","HANG",
				orderHeaderDB.getStockModularity(context.getOrderId()));
	 }
		
		
	}
	
	@Then("^I query with Order Id$")
	public void i_query_with_order_id() throws Throwable {
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();
	}
	

}

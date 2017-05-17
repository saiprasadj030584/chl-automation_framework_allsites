package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderMaintenancePage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final JDAFooter jdaFooter;
	private final OrderLineMaintenancePage orderLineMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderHeaderMaintenancePage(WebDriver webDriver, JDAFooter jdaFooter,
			OrderLineMaintenancePage orderLineMaintenancePage) {
		super(webDriver);
		this.webDriver = webDriver;
		this.jdaFooter = jdaFooter;
		this.orderLineMaintenancePage = orderLineMaintenancePage;
	}

	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderNumber.png", timeoutInSec);
		screen.click("images/OrderNumber.png");
		screen.type(OrderNo);
	}

	public void clickLinesButton() throws FindFailed {
		screen.wait("images/OrderHeaderLines.png", timeoutInSec);
		screen.click("images/OrderHeaderLines.png");
	}

	public String getOrderStatus() throws FindFailed, InterruptedException {
		String orderStatus = null;
		jdaFooter.clickQueryButton();
		Match mStatus = screen.find("images/OrderHeaderStatus.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		orderStatus = App.getClipboard();
		logger.debug("Order status is: " + orderStatus);
		return orderStatus;
	}

	public void clickOrderHeaderStatus() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderStatus.png", timeoutInSec);
		screen.click("images/OrderHeaderStatus.png");
		Thread.sleep(3000L);
	}

	public String getOrderDate() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/OrderDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCreatedBy() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/CreatedBy.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getOrderTime() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/OrderTime.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCreationDate() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/CreationDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCreationTime() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/CreationTime.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getMoveTaskStatus() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/MoveTaskStatus.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getFromSiteId() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/FromSiteId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getNumberOfLines() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/NumberOfLines.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickDeliveryAddressTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderMaintenance/DeliveryAddress.png", timeoutInSec);
		screen.click("images/OrderHeaderMaintenance/DeliveryAddress.png");
		Thread.sleep(2000);
	}

	public String getCustomer() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryAddress/Customer.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getName() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryAddress/Name.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getAddress1() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryAddress/Address1.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCountry() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryAddress/Country.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickDeliveryDetailsTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderMaintenance/DeliveryDetails.png", timeoutInSec);
		screen.click("images/OrderHeaderMaintenance/DeliveryDetails.png");
		Thread.sleep(2000);
	}

	public String getDeliveryByTimeFromDeliveryDetailsTab() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryDetails/DeliveryByTime.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getShipByTime() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryDetails/ShipByTime.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getShipByDate() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryDetails/ShipByDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getDeliveryByDateFromDeliveryDetailsTab() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/DeliveryDetails/DeliveryByDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickUserDefinedTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderMaintenance/UserDefined.png", timeoutInSec);
		screen.click("images/OrderHeaderMaintenance/UserDefined.png");
		Thread.sleep(2000);
	}

	public String getDeliveryType() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/UserDefined/DeliveryType.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getDeliveryByTimeFromUserDefinedTab() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/UserDefined/DeliveryByTime.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getDeliveryByDateFromUserDefinedtab() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/UserDefined/DeliveryByDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/Status.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getType() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/Type.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickHubAddressTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderMaintenance/HubAddress.png", timeoutInSec);
		screen.click("images/OrderHeaderMaintenance/HubAddress.png");
		Thread.sleep(2000);
	}

	public String getHub() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/HubAddress/Hub.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getHubName() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/HubAddress/HubName.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getHubCountry() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/HubAddress/HubCountry.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getIfosOrderNum() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/UserDefined/IfosOrderNum.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getShipDock() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/ShipDock.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();

	}
}

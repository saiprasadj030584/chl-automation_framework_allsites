package com.jda.wms.pages.foods;

import java.awt.AWTException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.BackOfficeContext;
import com.jda.wms.pages.PageObject;

public class BackOfficeLoginPage extends PageObject {

	private final static String USERNAME_KEY = "back_office.login_page.user-name-element";
	private final static String PASSWORD_KEY = "back_office.login_page.password";
	private final static String LOGIN_KEY = "back_office.login_page.login-button-element";
	private final static String POP_UP_WINDOW_KEY = "back_office.login_page.pop-up-window";
	private final static String POP_UP_WINDOW_CLOSE_BUTTON_KEY = "back_office.login_page.pop-up-window-close-button";

	private final Configuration configuration;
	private final WebDriver webDriver;
	private final BackOfficeContext backOfficeContext;

	@Inject
	public BackOfficeLoginPage(WebDriver webDriver, Configuration configuration, BackOfficeContext backOfficeContext) {
		super(webDriver);
		this.webDriver = webDriver;
		this.configuration = configuration;
		this.backOfficeContext = backOfficeContext;
	}

	private WebElement getUserNameElement() {
		return actions().getElement(USERNAME_KEY);
	}

	private WebElement getPasswordElement() {
		return actions().getElement(PASSWORD_KEY);
	}

	private WebElement getLoginElement() {
		return actions().getElement(LOGIN_KEY);
	}

	private WebElement getNewWindowPopUpElement() {
		return actions().getElement(POP_UP_WINDOW_KEY);
	}

	private WebElement getNewWindowPopUpCloseButtonElement() {
		return actions().getElement(POP_UP_WINDOW_CLOSE_BUTTON_KEY);
	}

	private WebElement getClosePopUpButtonElement() {
		return actions().getElement(POP_UP_WINDOW_CLOSE_BUTTON_KEY);
	}

	public void enterUserName(String userName) {
		getUserNameElement().clear();
		getUserNameElement().sendKeys(userName);
	}

	public void enterPassword(String password) {
		getPasswordElement().clear();
		getPasswordElement().sendKeys(password);
	}

	public void clickLoginButton() {
		getLoginElement().click();
	}

	public void clickClosePopUpButton() {
		getClosePopUpButtonElement().click();
	}

	public void openBackOfficeLoginPage() throws InterruptedException {	
		webDriver.get(configuration.getStringProperty("back-office-base-url"));
		synchronizer().waitUntilPageLoaded();
	}

	public void loginAsUser() {
		for (int i = 0; i < 10; i++) {
			if (getLoginElement().isDisplayed()) {
				webDriver.findElement(By.name("j_username")).clear();
				webDriver.findElement(By.name("j_username")).sendKeys("admin");
				webDriver.findElement(By.name("j_password")).clear();
				webDriver.findElement(By.name("j_password")).sendKeys("12345");
				webDriver.findElement(By.className("login_btn")).click();
				synchronizer().waitUntilPageLoaded();
			}
			i++;
		}
	}

	public void openBackOfficeLogin_1() throws InterruptedException {

		webDriver.get(configuration.getStringProperty("back-office-base-url"));
		Thread.sleep(5000L);
		synchronizer().waitUntilPageLoaded();
		for (int i = 0; i <= 20; i++) {
			if (webDriver.getTitle().equalsIgnoreCase("Login")) {
				enterUserName(configuration.getStringProperty("back-office-user-name"));
				Thread.sleep(5000L);
				enterPassword(configuration.getStringProperty("back-office-password"));
				Thread.sleep(5000L);
				clickLoginButton();
				Thread.sleep(5000L);
				synchronizer().waitUntilPageLoaded();
			} else {
				break;
			}

		}

	}

	public void switchToWindow() {
		Set<String> windowNames = webDriver.getWindowHandles();
		String windowName = "";
		for (String string : windowNames) {
			windowName = string;
		}
		webDriver.switchTo().window(windowName);
		System.out.println(webDriver.getTitle());
		synchronizer().waitUntilPageLoaded();
		WebElement okButtonElement = getNewWindowPopUpElement();
		okButtonElement.click();
	}

	public void switchToWindow_1() throws InterruptedException {

		Set<String> windowNames = webDriver.getWindowHandles();
		String windowName = "";

		for (String string : windowNames) {
			windowName = string;
			System.out.println("windowName");
			webDriver.switchTo().window(windowName);
			synchronizer().waitUntilPageLoaded();
			if (webDriver.getTitle().equalsIgnoreCase("hybris Backoffice")) {

				System.out.println("Entered");
				break;
			}
		}
		// System.out.println("The window title_1" + webDriver.getTitle());
		// for (int i = 0; i <= 4; i++) {

		// if (webDriver.findElement(By.cssSelector(".z-hbox table tbody tr
		// td button")).isDisplayed()) {
		Thread.sleep(2000L);
		webDriver.findElement(By.cssSelector(".z-hbox table tbody tr td button")).sendKeys(Keys.ENTER);
		Thread.sleep(2000L);
		webDriver.findElement(By.cssSelector(".z-hbox table tbody tr td button")).sendKeys(Keys.ENTER);

		Thread.sleep(2000L);
		webDriver.findElement(By.cssSelector(".z-hbox table tbody tr td button")).sendKeys(Keys.ENTER);

		Thread.sleep(2000L);
		webDriver.findElement(By.cssSelector(".z-hbox table tbody tr td button")).sendKeys(Keys.ENTER);

		// } else {
		// break;
		// }

	}

	public void clickNewWindowPopUpCloseButton() {
		for (int i = 0; i <= 4; i++) {
			if (getNewWindowPopUpCloseButtonElement().isEnabled()) {
				getNewWindowPopUpElement().click();
			}
		}
	}

	public void backofficeLogin() throws InterruptedException {

		for (int i = 0; i <= 20; i++) {
			if (webDriver.getTitle().equalsIgnoreCase("Login")) {
				enterUserName(configuration.getStringProperty("back-office-user-name"));
				Thread.sleep(1000L);
				enterPassword(configuration.getStringProperty("back-office-password"));
				Thread.sleep(1000L);
				clickLoginButton();
				synchronizer().waitUntilPageLoaded();
			} else {
				break;
			}

		}
	}

	public void atsInventryReservecheck() throws InterruptedException, AWTException {
		WebElement f = webDriver.findElement(By.xpath(
				"//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]"));
		List rows = f.findElements(By.tagName("tr"));
		int row_size = rows.size();
		System.out.println("the size of the row is : " + row_size);
		for (int i = 1; i <= row_size; i++) {
			String reservedValue = webDriver.findElement(By
					.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
							+ i + "]/td[4]"))
					.getText();
			int y = Integer.parseInt(reservedValue);
			System.out.println("the converted reserved value is going to check is: " + y);
			if (y != 0) {
				backOfficeContext.setReservedvalue(y);
				String reservedSlavenumber = webDriver.findElement(By
						.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
								+ i + "]/td[1]"))
						.getText();
				System.out.println("The slave number is which we get before setting is:" + reservedSlavenumber);
				backOfficeContext.setSlaveNumber(reservedSlavenumber);
				System.out.println("The slave number which is setted is: " + reservedSlavenumber);
				String alocatedcount = webDriver.findElement(By
						.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
								+ i + "]/td[5]"))
						.getText();
				int actualCount = Integer.parseInt(alocatedcount);
				System.out
						.println("The alocated value of the reserved slave before placing product : " + alocatedcount);
				backOfficeContext.setAlocatednumber(actualCount);
				System.out.println("the setted alocated value of the reserved slave before placing product :"
						+ backOfficeContext.getAlocatednumber());
			}
		}
	}

	public void atsInventryAllocationcheck() throws InterruptedException, AWTException {
		String slaveNumber = backOfficeContext.getSlaveNumber().trim();
		System.out.println("The received slave number is: " + slaveNumber);
		int actualAlocCount = backOfficeContext.getAlocatednumber();
		System.out.println("The received alocated value is: " + backOfficeContext.getAlocatednumber());
		WebElement f = webDriver.findElement(By.xpath(
				"//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]"));
		List rows = f.findElements(By.tagName("tr"));
		int row_size = rows.size();
		for (int i = 1; i <= row_size; i++) {
			String reservedSlavenumber = webDriver.findElement(By
					.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
							+ i + "]/td[1]"))
					.getText().trim();
			System.out.println("the slave number comparing is:" + reservedSlavenumber);
			System.out.println("The two numbers going to compare is " + slaveNumber + "and" + reservedSlavenumber);
			if (reservedSlavenumber.equals(slaveNumber)) {
				System.out.println("the slave number finaly matched is:" + reservedSlavenumber);
				String alocatedcount = webDriver.findElement(By
						.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
								+ i + "]/td[5]"))
						.getText();
				System.out.println("the alocated value is : " + alocatedcount);
				int finallAlocatedval = Integer.parseInt(alocatedcount);
				int reservedval = backOfficeContext.getReservedvalue();
				System.out.println("The received reserved value is: " + reservedval);
				System.out.println("The final Alocated vAlue is: " + finallAlocatedval);
				if (finallAlocatedval == (actualAlocCount + reservedval)) {
					System.out.println("the alocated value is : " + alocatedcount);
					System.out.println(
							"SUCCESS!! SUCCESS!!!!, the alocated value is finaly validated and found as increased");
				}
			}
		}
	}

	public void atsInventryStoreSlaveId() throws InterruptedException, AWTException {

		String folder = System.getProperty("user.home") + "\\Desktop";
		String fileName = folder + "\\PendingSellerAssignmentSlaveList" + ".csv";
		FileWriter writer;
		{
			try {
				writer = new FileWriter(fileName, false);
				String nextLine = "";
				writer.write(nextLine);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		WebElement f = webDriver.findElement(By.xpath(
				"//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]"));
		List rows = f.findElements(By.tagName("tr"));
		int row_size = rows.size();
		backOfficeContext.setSlaveCount(row_size);
		for (int i = 1; i <= row_size; i++) {
			String[] slaveid = new String[row_size];
			String reservedSlavenumber = webDriver.findElement(By
					.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
							+ i + "]/td[1]"))
					.getText().trim();
			String[] slaveIdvalueFromReservedSlavenumber = reservedSlavenumber.split("-");
			String finalSlaveId = slaveIdvalueFromReservedSlavenumber[1];
			String comma = ",";
			slaveid[i - 1] = finalSlaveId;
			String finalData = slaveid[i - 1];
			System.out.println("The slave ids are: " + finalData);

			{
				try {
					writer = new FileWriter(fileName, true);
					String nextLine = "," + finalData;
					writer.write(nextLine);
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		{
			try {
				writer = new FileWriter(fileName, true);
				String nextLine = ",";
				writer.write(nextLine);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void atsInventryAllocationcheckAfterCancelation() throws InterruptedException, AWTException {
		String slaveNumber = backOfficeContext.getSlaveNumber();
		int rowCount = backOfficeContext.getReservedvalue();
		int actualAlocCount = backOfficeContext.getAlocatednumber();
		System.out.println("The received alocated value is: " + backOfficeContext.getAlocatednumber());
		WebElement f = webDriver.findElement(By.xpath(
				"//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]"));
		List rows = f.findElements(By.tagName("tr"));
		int row_size = rows.size();
		for (int i = 1; i <= row_size; i++) {
			String reservedSlavenumber = webDriver.findElement(By
					.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
							+ i + "]/td[1]"))
					.getText();
			System.out.println("the slave number comparing is:" + reservedSlavenumber);
			if (reservedSlavenumber == slaveNumber) {
				System.out.println("the slave number finaly matched is:" + reservedSlavenumber);
				String alocatedcount = webDriver.findElement(By
						.xpath("//*[@class='yw-collapsibleContainer-container z-div']/div[1]/div[2]/div[2]/div[4]/table[1]/tbody[1]/tr["
								+ i + "]/td[5]"))
						.getText();
				System.out.println("the alocated value is : " + alocatedcount);
				int finallAlocatedval = Integer.parseInt(alocatedcount);
				int reservedval = backOfficeContext.getReservedvalue();
				if (finallAlocatedval == (actualAlocCount - reservedval)) {
					System.out.println("the alocated value after cancellation is: " + alocatedcount);
					System.out.println(
							"SUCCESS!! SUCCESS!!!!, the alocated value is finaly validated and found as decreased");
				}
			}
		}
	}
}

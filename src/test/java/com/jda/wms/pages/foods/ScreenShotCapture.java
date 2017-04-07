package com.jda.wms.pages.foods;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

import cucumber.api.Scenario;

public class ScreenShotCapture extends PageObject {

	public Scenario myScenario;
	private WebDriver webDriver;

	@Inject
	public ScreenShotCapture(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void screenshotcapture() throws AWTException {
		try {

			Robot robot1 = new Robot();
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage img1 = robot1.createScreenCapture(new Rectangle(size));
			// BufferedImage img2 = robot1.createScreenCapture(new
			// Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			File save_path = new File(
					"C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img1.png");
			ImageIO.write(img1, "png", save_path);
			File fi = new File(
					"C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img1.png");
			byte[] fileContent = Files.readAllBytes(fi.toPath());
			String filePath = "C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img1.png";
			FileOutputStream fos;
			fos = new FileOutputStream(filePath);
			fos.write(fileContent);
			BufferedImage img = null;
			BufferedImage tempPNG = null;
			BufferedImage tempJPG = null;
			File newFilePNG = null;
			File newFileJPG = null;
			img = ImageIO.read(new File(
					"C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img1.png"));
			tempJPG = resizeImage(img, img.getWidth(), img.getHeight());
			newFileJPG = new File(
					"C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img_New.png");
			ImageIO.write(tempJPG, "png", newFileJPG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage resizeImage(final Image image, int width, int height) {
		width = 900;
		height = 500;
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();

		try {
			graphics2D.setComposite(AlphaComposite.Src);
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2D.drawImage(image, 0, 0, width, height, null);
			graphics2D.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

}
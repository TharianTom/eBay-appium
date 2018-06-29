package com.eBay.wrappers;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import com.eBay.runners.SetupScript;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("rawtypes")
public class Screen {
	private static Dimension screenDimensions;
	private static AppiumDriver driver;
	private static int loadTimeout;
	
	protected Screen(){

	}
	
	public static void swipeRight(){
		driver = SetupScript.getDriver();
		screenDimensions = driver.manage().window().getSize();
		System.out.println("Screen dimensions "+ screenDimensions);
		int startx = (int) (screenDimensions.width * 0.01);
		int starty = screenDimensions.height / 2;
		int endy = screenDimensions.height / 2;
		int endx = (int) (screenDimensions.width * 0.9);
		PointOption startPoint = PointOption.point(startx, starty);
		PointOption endPoint = PointOption.point(endx, endy);
		WaitOptions startTime = WaitOptions.waitOptions(Duration.ofMillis(1000));
		WaitOptions endTime = WaitOptions.waitOptions(Duration.ofMillis(10));
		new TouchAction(driver).press(startPoint).waitAction(startTime).moveTo(endPoint).waitAction(endTime).release().perform();
	}
	
	@SuppressWarnings("rawtypes")
	public static void scrollDown(){
		driver = SetupScript.getDriver();
		screenDimensions = driver.manage().window().getSize();
		System.out.println("Screen dimensions "+ screenDimensions);
		int startx = screenDimensions.width /2;
		int starty =(int) ( screenDimensions.height  * 0.9);
		int endy = (int) (screenDimensions.height  * 0.1);
		int endx = screenDimensions.width /2;
		PointOption startPoint = PointOption.point(startx, starty);
		PointOption endPoint = PointOption.point(endx, endy);
		WaitOptions startTime = WaitOptions.waitOptions(Duration.ofMillis(1000));
		WaitOptions endTime = WaitOptions.waitOptions(Duration.ofMillis(10));
		new TouchAction(driver).press(startPoint).waitAction(startTime).moveTo(endPoint).waitAction(endTime).release().perform();
	}
	
	
	public static void scrollUp(){
		driver = SetupScript.getDriver();
		screenDimensions = driver.manage().window().getSize();
		System.out.println("Screen dimensions "+ screenDimensions);
		int startx = screenDimensions.width /2;
		int starty =(int) ( screenDimensions.height  * 0.1);
		int endy = (int) (screenDimensions.height  * 0.9);
		int endx = screenDimensions.width /2;
		PointOption startPoint = PointOption.point(startx, starty);
		PointOption endPoint = PointOption.point(endx, endy);
		WaitOptions startTime = WaitOptions.waitOptions(Duration.ofMillis(1000));
		WaitOptions endTime = WaitOptions.waitOptions(Duration.ofMillis(10));
		new TouchAction(driver).press(startPoint).waitAction(startTime).moveTo(endPoint).waitAction(endTime).release().perform();
	}
	
	public static void getPageSource(){
		driver = SetupScript.getDriver();
		System.out.println("Printing Source "+ driver.getPageSource());
	}

}

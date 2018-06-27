package com.eBay.wrappers;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import com.eBay.runners.SetupScript;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Screen {
	private static Dimension screenDimensions;
	private static AppiumDriver<AndroidElement> driver;
	
	protected Screen(){

	}
	
	@SuppressWarnings("rawtypes")
	public static void swipeRight(){
		driver = SetupScript.getDriver();
		screenDimensions = driver.manage().window().getSize();
		System.out.println("Screen dimensions "+ screenDimensions);
		int startx = (int) (screenDimensions.width * 0.001);
		int starty = screenDimensions.height / 2;
		int endy = screenDimensions.height / 2;
		int endx = (int) (screenDimensions.width * 0.9);
//		TouchAction action = new TouchAction(driver);
//		action.press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy));
//		action.release();
		PointOption startPoint = PointOption.point(startx, starty);
		PointOption endPoint = PointOption.point(endx, endy);
		WaitOptions startTime = WaitOptions.waitOptions(Duration.ofMillis(1000));
		WaitOptions endTime = WaitOptions.waitOptions(Duration.ofMillis(10));
		new TouchAction(driver).press(startPoint).waitAction(startTime).moveTo(endPoint).waitAction(endTime).release().perform();
	}
	

}

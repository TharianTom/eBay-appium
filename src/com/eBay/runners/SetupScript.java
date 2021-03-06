package com.eBay.runners;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.eBay.helpers.PropertiesHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;

@SuppressWarnings("rawtypes")
public final class SetupScript {
	private static AppiumDriver driver;
	private static String DEVICE_NAME = PropertiesHelper.getProperty("DesiredCapabilities", "deviceName");
	private static String APP = System.getProperty("user.dir")
			+ PropertiesHelper.getProperty("DesiredCapabilities", "app");
	private static String APPIUM_DRIVER = PropertiesHelper.getProperty("DesiredCapabilities", "appiumDriver");
	private static String APP_PACKAGE = PropertiesHelper.getProperty("DesiredCapabilities", "appPackage");
	private static String APP_ACTIVITY = PropertiesHelper.getProperty("DesiredCapabilities", "appActivity");

	public static void setUp() throws IOException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", DEVICE_NAME);
		capabilities.setCapability("app", APP);
		try {
			setDriver(new AndroidDriver(new URL(APPIUM_DRIVER), capabilities));
			System.out.println("App Launched successfully");
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void setupTest() {
		((StartsActivity) driver).startActivity(new Activity(APP_PACKAGE, APP_ACTIVITY));
	}

	public static void stopActivity() {
		driver.closeApp();
	}

	public static void tearDown() {
		getDriver().quit();
	}

	public static void resetApp() {
		driver.resetApp();
	}

	public static AppiumDriver getDriver() {
		return driver;
	}

	private static void setDriver(AppiumDriver driver) {
		SetupScript.driver = driver;
	}

}

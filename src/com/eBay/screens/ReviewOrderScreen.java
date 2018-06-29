package com.eBay.screens;

import org.openqa.selenium.NoSuchElementException;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ReviewOrderScreen extends Screen {
	static Element deliveryTitle = new Element("//android.view.View[@text='Delivery address']", "xpath");
	static Element alternativeDeliveryTitle = new Element("//android.view.View[@text='Delivery Address']", "xpath");
	static Element progressLayout = new Element("com.ebay.mobile:id/progress_layout", "id");
	static Element webView = new Element("android.view.View", "class", "scrollDown", 2);
	static int loadTimeout = 20;
	/*
	 * Always use the method while navigating to review screen
	 */
	public static void amIHere() {
		progressLayout.waitForElementToBeAbsent(loadTimeout);
		try {
			deliveryTitle.elementPresent();
		} catch (NoSuchElementException e) {
			System.out.println("Exception" + e);
			alternativeDeliveryTitle.elementPresent();
		}
	}

	public static void validateProductDetails() {
		String product = ExcelUtil.getTestData("productName");
		webView.hasText(product);
	}

}

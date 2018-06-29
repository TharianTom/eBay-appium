package com.eBay.screens;

import org.openqa.selenium.NoSuchElementException;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ReviewOrderScreen extends Screen {
	static Element deliveryTitle = new Element("//android.view.View[@text='Delivery address']", "xpath");
	static Element alternativeDeliveryTitle = new Element("//android.view.View[@text='Delivery Address']", "xpath");
	static Element progressLayout = new Element("com.ebay.mobile:id/progress_layout", "id");
	static Element itemName = new Element("android.view.View", "class", "scrollDown", 2);
	
	 

	public static void amIHere(int timeout) {
		progressLayout.waitForElementToBeAbsent(timeout);
		try {
			deliveryTitle.elementPresent();
		} catch (NoSuchElementException e) {
			System.out.println("Exception" + e);
			alternativeDeliveryTitle.elementPresent();
		}
	}

	public static void validateProductDetails() {
		String product = ExcelUtil.getTestData("productName");
		itemName.hasText(product);
		product = product.replaceAll("'", "&apos;");
		//Element productElement = new Element("//android.view.View[@text='SONY 65 INCHES 65X7000E 4k UHD HDR SMART LED TV + ONE YEAR DEALER\'S WARRANTY']", "xpath", "scrollDown", 2);
		//productElement.elementPresent();
	}

}

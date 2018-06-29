package com.eBay.screens;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ProductDetailsScreen extends Screen {

	static Element productName = new Element("com.ebay.mobile:id/textview_item_name", "id");
	static Element productPrice = new Element("com.ebay.mobile:id/textview_item_price", "id");
	static Element butNowButton = new Element("com.ebay.mobile:id/button_bin", "id");
	static int loadTimeout = 10;
	/*
	 * Always use the method while navigating to product details screen
	 */
	public static void amIHere() {
		productName.waitForElementToBePresent(loadTimeout);
		productName.elementPresent();
	}

	public static void isPriceExpected() {
		String price = ExcelUtil.getTestData("productPrice");
		productPrice.hasText(price);
	}

	public static void getProductName() {
		String product = productName.getText();
		ExcelUtil.setTestData("productName", product);
	}

	public static void buyItem() {
		butNowButton.clickElement();
	}

}

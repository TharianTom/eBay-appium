package com.eBay.screens;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class QuantityUpdateScreen extends Screen {

	static Element productName = new Element("com.ebay.mobile:id/item_title", "id");
	static Element productPrice = new Element("com.ebay.mobile:id/textview_item_price", "id");
	static Element currentQuantiy = new Element("android:id/numberpicker_input", "id");
	static Element quantityPicker = new Element("android.widget.NumberPicker", "class");
	static Element reviewButton = new Element("com.ebay.mobile:id/take_action", "id");
	static int loadTimeout = 5;
	
	/*
	 * Always use the method while navigating to quantity screen
	 */
	public static void amIHere() {
		productName.waitForElementToBePresent(loadTimeout);
		productName.elementPresent();
	}

	public static void hasExpectedPrice() {
		String price = ExcelUtil.getTestData("productPrice");
		productPrice.hasText(price);
	}

	public static void hasExpectedProductName() {
		String product = ExcelUtil.getTestData("productName");
		productName.hasText(product);
	}

	public static void isQuantityUpdated() {
		String quantity = ExcelUtil.getTestData("quantity");
		currentQuantiy.hasText(quantity);
	}

	public static void goToReview() {
		reviewButton.clickElement();
	}

}

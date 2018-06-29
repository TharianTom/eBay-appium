package com.eBay.screens;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ResultsScreen extends Screen {

	static Element itemPrice = new Element("com.ebay.mobile:id/textview_item_price", "id");
	static Element filter = new Element("com.ebay.mobile:id/button_filter", "id");
	static int loadTimeout = 10;
	
	/*
	 * Always use the method while navigating to results screen
	 */
	public static void amIHere() {
		filter.waitForElementToBePresent(loadTimeout);
		filter.elementPresent();
	}

	/*
	 * This method touches the most expensive item in current screen
	 */
	public static void touchMostExpensive() {
		List<String> pricesInString = itemPrice.getTexts();
		List<Integer> prices = new ArrayList<Integer>();
		String currency = new String();
		for (String priceInString : pricesInString) {
			priceInString = priceInString.replace(",", "");
			currency = priceInString.replaceAll("[0-9]", "");
			priceInString = priceInString.replaceAll(currency, "");
			priceInString = priceInString.replace(",", "");
			prices.add(Integer.valueOf(priceInString));
		}
		Integer expensiveItemPrice = Collections.max(prices);
		String expensiveItemPriceInString = NumberFormat.getNumberInstance(Locale.US).format(expensiveItemPrice);
		expensiveItemPriceInString = currency + expensiveItemPriceInString;
		Element expensiveItem = new Element("//android.widget.TextView[@text='" + expensiveItemPriceInString + "']",
				"xpath", "scrollDown", 3);
		expensiveItem.clickElement();
		ExcelUtil.setTestData("productPrice", expensiveItemPriceInString.toString());
	}
}

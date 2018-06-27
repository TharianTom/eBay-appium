package com.eBay.screens;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ResultsScreen extends Screen{

	public ResultsScreen() {
		super();
	}
	static Element itemPrice = new Element("com.ebay.mobile:id/textview_item_price", "id");
	static Element filter = new Element("com.ebay.mobile:id/button_filter", "id");
	
	public static void amIHere(int timeout){
		filter.waitForElementToBePresent(timeout);
		filter.elementPresent();
	}
	
	public static void touchMostExpensive(){
		List<String> pricesInString = itemPrice.getTexts();
		List<Integer> prices = new ArrayList<Integer>();
		for(String priceInString: pricesInString){
			priceInString = priceInString.replace("₹","");
			priceInString = priceInString.replace(",","");
			prices.add(Integer.valueOf(priceInString));
		}	
		Integer expensiveItemPrice = Collections.max(prices);
		String expensiveItemPriceInString = NumberFormat.getNumberInstance(Locale.US).format(expensiveItemPrice);
		expensiveItemPriceInString = "₹" + expensiveItemPriceInString; 
		Element expensiveItem = new Element("//android.widget.TextView[@text='"+expensiveItemPriceInString+"']", "xpath");
		expensiveItem.clickElement();
		ExcelUtil.setTestData("productPrice", expensiveItemPriceInString);
	}
}

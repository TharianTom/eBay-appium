package com.eBay.screens;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class HomeScreen extends Screen {

	static Element searchText = new Element("com.ebay.mobile:id/search_box", "id");
	static Element homeButton = new Element("com.ebay.mobile:id/home", "id");
	static Element eBayLogo = new Element("com.ebay.mobile:id/logo", "id");
	static Element sellingCapsule = new Element("com.ebay.mobile:id/capsule_selling", "id");
	static int loadTimeout = 10;
	
	/*
	 * Always use the method while navigating to home screen
	 */
	public static void amIHere() {
		eBayLogo.waitForElementToBePresent(loadTimeout);
		eBayLogo.elementPresent();
	}

	public static void touchSearch() {
		searchText.clickElement();
	}

	public static void touchHome() {
		homeButton.clickElement();
	}

	public static void searchText() {
		searchText.clickElement();
		SearchScreen.amIHere();
		SearchScreen.searchText(ExcelUtil.getTestData("searchKeyword"));
	}

}

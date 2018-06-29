package com.eBay.screens;

import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class SearchScreen extends Screen {

	static Element searchText = new Element("com.ebay.mobile:id/search_src_text", "id");
	static Element predictedSearch = new Element("com.ebay.mobile:id/text", "id");

	/*
	 * Always use the method while navigating to search screen
	 */
	public static void amIHere() {
		searchText.elementPresent();
	}

	public static void searchText(String searchValue) {
		searchText.sendKeys(searchValue);
		searchText.enterKeyEvent();
	}

}

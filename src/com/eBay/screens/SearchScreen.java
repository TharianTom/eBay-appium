package com.eBay.screens;

import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class SearchScreen extends Screen{
	SearchScreen(){
		super();
	}
	static Element searchText = new Element("com.ebay.mobile:id/search_src_text", "id", "noScroll");
	static Element predictedSearch = new Element("com.ebay.mobile:id/text", "id", "scrollDown");

	
	public static void amIHere(){
		searchText.elementPresent();
	}
	
	public static void searchText(String searchValue){
		searchText.sendKeys(searchValue);
		searchText.enterKeyEvent();
	}
	
}

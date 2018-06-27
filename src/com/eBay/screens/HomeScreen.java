package com.eBay.screens;

import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class HomeScreen extends Screen {
	HomeScreen() {
		super();
	}

	static Element searchText = new Element("com.ebay.mobile:id/search_box", "id", "swipeDown");
	static Element homeButton = new Element("com.ebay.mobile:id/home", "id", "noScroll");
	static Element eBayLogo = new Element("com.ebay.mobile:id/logo", "id", "noScroll");
	static Element sellingCapsule = new Element("com.ebay.mobile:id/capsule_selling", "id", "noScroll");
	
	public static void amIHere(int timeout){
		eBayLogo.waitForElementToBePresent(timeout);
		eBayLogo.elementPresent();
	}
	
	public static void touchSearch(){
		searchText.clickElement();
	}
	
	public static void touchHome(){
		homeButton.clickElement();
	}
	
	public static void searchText(String searchValue){
		searchText.clickElement();
		SearchScreen.amIHere();
		SearchScreen.searchText(searchValue);
	}
	
}

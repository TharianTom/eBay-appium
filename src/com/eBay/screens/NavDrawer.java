package com.eBay.screens;

import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class NavDrawer extends Screen {
	NavDrawer() {
		super();
	}

	static Element signInImage = new Element("com.ebay.mobile:id/logo", "id");
	
	public static void touchSignIn(){
		signInImage.clickElement();
	}
		
}

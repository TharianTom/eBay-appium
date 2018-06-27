package com.eBay.screens;

import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class SignInScreen extends Screen {
	SignInScreen() {
		super();
	}

	static Element userNameField = new Element("com.ebay.mobile:id/edit_text_username", "id", "scrollUp");
	static Element passwordfield = new Element("com.ebay.mobile:id/edit_text_password", "id", "scrollUp");
	static Element signInButton = new Element("com.ebay.mobile:id/button_sign_in", "id", "scrollDown");
	static Element loginGreeting = new Element("com.ebay.mobile:id/text_google_greeting_enroll", "id", "noScroll");
	static Element noThanks = new Element("com.ebay.mobile:id/button_google_deny", "id", "noScroll");
	
	public static void amIHere(){
		userNameField.elementPresent();
	}
	
	public static void amINotHere(){
		userNameField.waitForElementToBeAbsent(20);
	}
	
	public static void signIn(String userName, String password){
		userNameField.sendKeys(userName);
		passwordfield.sendKeys(password);
		signInButton.clickElement();
	}
	
	public static void dismissGreeting(){
		if(loginGreeting.ifElementPresent()){
			noThanks.clickElement();
		}
	}		
}
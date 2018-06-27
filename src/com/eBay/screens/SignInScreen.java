package com.eBay.screens;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class SignInScreen extends Screen {
	SignInScreen() {
		super();
	}

	static Element userNameField = new Element("com.ebay.mobile:id/edit_text_username", "id");
	static Element passwordfield = new Element("com.ebay.mobile:id/edit_text_password", "id");
	static Element signInButton = new Element("com.ebay.mobile:id/button_sign_in", "id");
	static Element loginGreeting = new Element("com.ebay.mobile:id/text_google_greeting_enroll", "id");
	static Element noThanks = new Element("com.ebay.mobile:id/button_google_deny", "id");
	static Element errorMessage = new Element("//android.widget.TextView[@text='Oops, that doesn't match.']", "xpath");
	
	public static void amIHere(){
		userNameField.elementPresent();
	}
	
	public static void amINotHere(int timeout){
		userNameField.waitForElementToBeAbsent(timeout);
	}
	
	public static void signIn(){
		userNameField.sendKeys(ExcelUtil.getTestData("userName"));
		passwordfield.sendKeys(ExcelUtil.getTestData("password"));
		signInButton.clickElement();
	}
	
	public static void dismissGreeting(){
		if(loginGreeting.ifElementPresent()){
			noThanks.clickElement();
		}
	}		
	
}
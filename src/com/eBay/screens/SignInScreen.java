package com.eBay.screens;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class SignInScreen extends Screen {

	static Element userNameField = new Element("com.ebay.mobile:id/edit_text_username", "id");
	static Element userNameFieldAfterTap = new Element(
			"//android.widget.EditText[contains(@resource-id,'com.ebay.mobile:id/edit_text_username')]", "xpath");
	static Element passwordfield = new Element("com.ebay.mobile:id/edit_text_password", "id");
	static Element progressLayout = new Element("com.ebay.mobile:id/progress_layout", "id");
	static Element signInButton = new Element("com.ebay.mobile:id/button_sign_in", "id");
	static Element loginGreeting = new Element("com.ebay.mobile:id/text_google_greeting_enroll", "id");
	static Element noThanks = new Element("com.ebay.mobile:id/button_google_deny", "id");
	static Element errorMessage = new Element("//android.widget.TextView[@text='Oops, that's not a match.']", "xpath");
	static int loadTimeout = 30;

	/*
	 * Always use the method while navigating to home screen
	 */
	public static void amIHere() {
		userNameField.elementPresent();
	}

	/*
	 * Wait for application to leave the screen. wait for progress bar to complete
	 */
	public static void amINotHere() {
		progressLayout.waitForElementToBeAbsent(loadTimeout);
		userNameField.elementNotPresent();
	}

	public static void signIn() {
		userNameField.sendKeys(ExcelUtil.getTestData("userName"));
		passwordfield.sendKeys(ExcelUtil.getTestData("password"));
		signInButton.clickElement();
	}

	public static void dismissGreeting() {
		if (loginGreeting.ifElementPresent()) {
			noThanks.clickElement();
		}
	}
}
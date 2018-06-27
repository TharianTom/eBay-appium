package com.eBay.screens;

import org.openqa.selenium.NoSuchElementException;

import com.eBay.helpers.ExcelUtil;
import com.eBay.wrappers.Element;
import com.eBay.wrappers.Screen;

public final class ReviewOrderScreen extends Screen {
	static Element deliveryTitle = new Element("//android.view.View[@text='Delivery Address']", "xpath");
	static Element alternativeDeliveryTitle = new Element("//android.view.View[@text='Delivery address']", "xpath");
	
	public static void amIHere(int timeout){
		deliveryTitle.waitForElementToBePresent(timeout);
		try{
		deliveryTitle.elementPresent();
		}catch(NoSuchElementException e){
			System.out.println("Exception" + e);
			alternativeDeliveryTitle.elementPresent();
		}
	}
	
	public static void validateProductDetails(){
		String price = ExcelUtil.getTestData("productPrice");
		String product = ExcelUtil.getTestData("productName");
		Element priceElement = new Element("//android.widget.TextView[@text='"+price+"']", "xpath", "scrollDown", 2);
		Element productElement = new Element("//android.widget.TextView[@text='"+product+"']", "xpath", "scrollDown", 2);
		priceElement.elementPresent();
		productElement.elementPresent();
	}
	
}

package com.eBay.wrappers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eBay.runners.SetupScript;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.offset.ElementOption;

@SuppressWarnings("rawtypes")
public class Element {
	private String using;
	private String by;
	private AppiumDriver driver;
	private String scrollDirection;
	private int scrollTimeout;

	
	public Element(String using, String by){
		this.using = using;
		this.by = by;
		this.scrollDirection = null;
		this.scrollTimeout = 0;
		driver = SetupScript.getDriver();
	}
	
	public Element(String using, String by, String scrollDirection, int scrollTimeout){
		this.using = using;
		this.by = by;
		this.scrollDirection = scrollDirection;
		this.scrollTimeout = scrollTimeout;
		driver = SetupScript.getDriver();
	}
	
	public By getElementUsing() {
		if(by.matches("id")){
			return By.id(using);
		}else if(by.matches("xpath")){
			return By.xpath(using);
		}else if(by.matches("class")){
			return By.className(using);
		}
		throw new RuntimeException("Unidentified Element Property "+ by + ". Please add or correct the 'by' value");
	}
	
	public Boolean ifElementPresent(){
		By by = getElementUsing();
		System.out.println("If element present " + by);
		try{
			driver.findElement(by);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public void elementNotPresent(){
		if(ifElementPresent()){
			throw new RuntimeException("Element "+ using + " is present in screen");
		}
	}
	
	public Boolean ifElementDisplayed(){
		By by = getElementUsing();
		System.out.println("If element displayed " + by);
		try{
			System.out.println("If element displayed " + driver.findElement(by).isDisplayed());
			return driver.findElement(by).isDisplayed();
		}catch(Exception e){
			return false;
		}
	}

	
	public void elementPresent(){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Checking Element Present "+ by);
		if(scrollTimeout>0){
			for(int i = 0; i< scrollTimeout; i++){
				try{
					driver.findElement(by);
					break;
				}catch(NoSuchElementException e){
					if(scrollDirection.equals("scrollDown")){
						Screen.scrollDown();
					} else{
						Screen.scrollUp();
					}
				}
			}
			driver.findElement(by);
		}else{
			driver.findElement(by);
		}
	}
	
	public String getText(){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Getting text "+ by);
		return driver.findElement(by).getText();
	}
	
	public void hasText(String string){
		System.out.println("Checking if text "+ string + " exists");
		if(scrollTimeout>0){
			for(int i = 0; i< scrollTimeout; i++){
				if(!ifTextPresent(string)){
					if(scrollDirection.equals("scrollDown")){
						Screen.scrollDown();
					} else{
						Screen.scrollUp();
					}
				}
			}
			if(!ifTextPresent(string)){
				throw new RuntimeException("No text found with " + string + " for " + by + ", but got text " + gotText); 
			}
		}else{
			if(!ifTextPresent(string)){
				throw new RuntimeException("No text found with " + string + " for " + by + ", but got text " + gotText); 
			} 
		}
	}
	
	public boolean ifTextPresent(String string){
		List<String> gotText = getTexts();
		System.out.println("Checking if text "+ string + " matches " + gotText);
		if(!gotText.contains(string)){
			return false; 
		}
		return true;
	}
	
	public List<String> getTexts(){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Getting texts "+ by);
		List<AndroidElement> elements = driver.findElements(by);
		List<String> strings = new ArrayList<String>();
		for(AndroidElement element: elements) {
			strings.add(element.getText());
		}
		System.out.println("Got text "+ strings);
		return strings;	
	}
	
	public void clickElement(){
		elementPresent();
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Clicking "+ by);
		driver.findElement(by).click();
	}
	
	public void sendKeys(String text){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Sending values "+ text +" to "+ by);
		driver.findElement(by).sendKeys(text);
	}
	
	public void waitForElementToBePresent(int timeout){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		if(!ifElementPresent()){
			System.out.println("Waiting for " + by + " to be present");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}catch(Exception e){
				
			}
		}
	}
	
	public void scrollTo(Element endElement){
		driver = SetupScript.getDriver();
		By startBy = getElementUsing();
		By endBy = endElement.getElementUsing();
		ElementOption startElementOption = ElementOption.element(driver.findElement(startBy));
		ElementOption endElementOption = ElementOption.element(driver.findElement(endBy));
		new TouchAction(driver).press(startElementOption).moveTo(endElementOption).release().perform();
	}
	
	public void waitForElementToBeAbsent(int timeout){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Waiting for " + by + " to be absent");
//		WebDriverWait wait = new WebDriverWait(driver, timeout);
//		try{
//			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(by)));
//		}catch(Exception e){
//		}
		long currentTime = System.currentTimeMillis();
		while(ifElementPresent()  && notTimedOut(currentTime,timeout)){
		}
	}
	
    public static boolean notTimedOut(long startTime, long waitTime) {
    	long expectedTime = (waitTime * 1000) + startTime;
        if (System.currentTimeMillis() < expectedTime) {
            return true;
        }
        return false;
    }
    
	@SuppressWarnings("deprecation")
	public void enterKeyEvent(){	
		((PressesKey) driver).pressKeyCode(66);
//		By by = getElementUsing();
//		driver.findElement(by).sendKeys("\n");
	}
}

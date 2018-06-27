package com.eBay.wrappers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eBay.runners.SetupScript;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.PressesKey;


public class Element {
	private String using;
	private String scrollDirection;
	private String by;
	private AppiumDriver<AndroidElement> driver;

	
	public Element(String using, String by, String scrollDirection){
		this.using = using;
		this.by = by;
		//TODO Specify scroll direction to drag to an element
		this.scrollDirection = scrollDirection;
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
		System.out.println("If element present" + by);
		try{
			driver.findElement(by);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public void elementPresent(){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Checking Element Present "+ by);
		driver.findElement(by);
	}
	
	public String getText(){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Getting text "+ by);
		return driver.findElement(by).getText();
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
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Clicking "+ by);
		driver.findElement(by).click();;
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
	
	public void waitForElementToBeAbsent(int timeout){
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Waiting for " + by + " to be absent");
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try{
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(by)));
		}catch(Exception e){
		}
//		long currentTime = System.currentTimeMillis();
//		while(ifElementPresent() && notTimedOut(currentTime,timeout)){
//			System.out.println("Waiting for " + by + " to be absent");
//		}
	}
	
    public static boolean notTimedOut(long startTime, long waitTime) {
        if (System.currentTimeMillis() < (waitTime * 1000) + startTime) {
            return true;
        }
        return false;
    }
    
	public void enterKeyEvent(){	
		((PressesKey) driver).pressKeyCode(66);
//		By by = getElementUsing();
//		driver.findElement(by).sendKeys("\n");
	}
}

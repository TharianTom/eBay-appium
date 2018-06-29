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

/**
 * All element specific actions and assertions needs to defined here
 *
 */
@SuppressWarnings("rawtypes")
public class Element {
	private String using;
	private String by;
	private AppiumDriver driver;
	private String scrollDirection;
	private int scrollTimeout;

	/**
	 * Specify element properties here
	 * 
	 * @param using:
	 *            attribute value
	 * @param by:
	 *            attribute used to identify the element
	 */
	public Element(String using, String by) {
		this.using = using;
		this.by = by;
		this.scrollDirection = null;
		this.scrollTimeout = 0;
		driver = SetupScript.getDriver();
	}

	/**
	 * Specify element properties here
	 * 
	 * @param using:
	 *            attribute value
	 * @param by:
	 *            attribute used to identify the element
	 * @param scrollDirection:
	 *            specify the scroll direction based on relative position of
	 *            element on screen
	 * @param scrollTimeout:
	 *            number of scroll attempts that should be done before element
	 *            is identified
	 */
	public Element(String using, String by, String scrollDirection, int scrollTimeout) {
		this.using = using;
		this.by = by;
		this.scrollDirection = scrollDirection;
		this.scrollTimeout = scrollTimeout;
		driver = SetupScript.getDriver();
	}

	/**
	 * Form and return the 'by' query
	 * 
	 * @return the assembled by query
	 */
	public By getElementUsing() {
		if (by.matches("id")) {
			return By.id(using);
		} else if (by.matches("xpath")) {
			return By.xpath(using);
		} else if (by.matches("class")) {
			return By.className(using);
		}
		throw new RuntimeException("Unidentified Element Property " + by + ". Please add or correct the 'by' value");
	}

	/**
	 * Check if element is present
	 * 
	 * @return true if element is present
	 */
	public Boolean ifElementPresent() {
		By by = getElementUsing();
		System.out.println("If element present " + by);
		try {
			driver.findElement(by);
		} catch (Exception e) {
			System.out.println("If element present " + false);
			return false;
		}
		System.out.println("If element present " + true);
		return true;
	}

	/**
	 * Fails test if element is present
	 */
	public void elementNotPresent() {
		if (ifElementPresent()) {
			throw new RuntimeException("Element " + using + " is present in screen");
		}
	}

	/**
	 * Check if element is displayed
	 * 
	 * @return true if element is displayed
	 */
	public Boolean ifElementDisplayed() {
		By by = getElementUsing();
		System.out.println("If element displayed " + by);
		try {
			System.out.println("If element displayed " + driver.findElement(by).isDisplayed());
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Fails if element is not present
	 */
	public void elementPresent() {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Checking Element Present " + by);
		if (scrollTimeout > 0) {
			for (int i = 0; i < scrollTimeout; i++) {
				try {
					driver.findElement(by);
					break;
				} catch (NoSuchElementException e) {
					if (scrollDirection.equals("scrollDown")) {
						Screen.scrollDown();
					} else {
						Screen.scrollUp();
					}
				}
			}
			driver.findElement(by);
		} else {
			driver.findElement(by);
		}
	}

	/**
	 * Returns String of a specific element
	 */
	public String getText() {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Getting text " + by);
		return driver.findElement(by).getText();
	}

	/**
	 * Validates if the specified element/s has the required texts
	 * 
	 * @param string:
	 *            The string to be validated
	 */
	public void hasText(String string) {
		System.out.println("Checking if text " + string + " exists");
		Boolean textFound = false;
		if (scrollTimeout > 0) {
			for (int i = 0; i < scrollTimeout; i++) {
				textFound = ifTextPresent(string);
				if (textFound) {
					return;
				} else {
					if (scrollDirection.equals("scrollDown")) {
						Screen.scrollDown();
					} else {
						Screen.scrollUp();
					}
				}
			}
			throw new RuntimeException("No text found with " + string + " for " + by);
		} else {
			if (!ifTextPresent(string)) {
				throw new RuntimeException("No text found with " + string + " for " + by);
			}
		}
	}

	/**
	 * Check if the string is present
	 * 
	 * @param string:
	 *            The string to be validated
	 * @return true if text is present
	 */
	public boolean ifTextPresent(String string) {
		List<String> gotText = getTexts();
		System.out.println("Checking if text " + string + " matches " + gotText);
		if (gotText.contains(string)) {
			return true;
		}
		return false;
	}

	/**
	 * Get the list of strings for the element group
	 * 
	 * @return string list
	 */
	public List<String> getTexts() {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Getting texts " + by);
		@SuppressWarnings("unchecked")
		List<AndroidElement> elements = driver.findElements(by);
		List<String> strings = new ArrayList<String>();
		for (AndroidElement element : elements) {
			strings.add(element.getText());
		}
		System.out.println("Got text " + strings);
		return strings;
	}

	/**
	 * Tap on the screen element
	 */
	public void clickElement() {
		elementPresent();
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Clicking " + by);
		driver.findElement(by).click();
	}

	/**
	 * Send keys to element. (For entering text)
	 * 
	 * @param text:
	 *            The text to be entered
	 */
	public void sendKeys(String text) {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Sending values " + text + " to " + by);
		driver.findElement(by).sendKeys(text);
	}

	/**
	 * Wait for an element to appear on screen
	 * 
	 * @param timeout:
	 *            The time required for element to appear
	 */
	public void waitForElementToBePresent(int timeout) {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		if (!ifElementPresent()) {
			System.out.println("Waiting for " + by + " to be present");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Scroll from one element to another
	 * 
	 * @param endElement:
	 *            Scroll end point
	 */
	public void scrollTo(Element endElement) {
		driver = SetupScript.getDriver();
		By startBy = getElementUsing();
		By endBy = endElement.getElementUsing();
		ElementOption startElementOption = ElementOption.element(driver.findElement(startBy));
		ElementOption endElementOption = ElementOption.element(driver.findElement(endBy));
		new TouchAction(driver).press(startElementOption).moveTo(endElementOption).release().perform();
	}

	/**
	 * Wait for element to disappear
	 * 
	 * @param timeout:
	 *            The time required for element to disappear
	 */
	public void waitForElementToBeAbsent(int timeout) {
		driver = SetupScript.getDriver();
		By by = getElementUsing();
		System.out.println("Waiting for " + by + " to be absent");
		long currentTime = System.currentTimeMillis();
		while (ifElementPresent()) {
			if (!notTimedOut(currentTime, timeout)) {
				break;
			}
		}
	}

	/**
	 * A time out counter
	 * 
	 * @param startTime:
	 *            Start Time of counter
	 * @param waitTime:
	 *            expected wait time of counter in seconds
	 * @return
	 */
	public static boolean notTimedOut(long startTime, long waitTime) {
		long expectedTime = (waitTime * 1000) + startTime;
		if (System.currentTimeMillis() < expectedTime) {
			return true;
		}
		return false;
	}

	/**
	 * To press enter/search key on keyboard
	 */
	@SuppressWarnings("deprecation")
	public void enterKeyEvent() {
		((PressesKey) driver).pressKeyCode(66);
	}
}

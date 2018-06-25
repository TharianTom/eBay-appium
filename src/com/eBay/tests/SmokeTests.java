package com.eBay.tests;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

import com.eBay.runners.SetupScript;

public class SmokeTests {
    private AndroidDriver<WebElement> driver = SetupScript.getDriver();


	@BeforeSuite
    public void beforeClassTasks() throws IOException{
    	SetupScript.setUp();
    }

    @AfterSuite
    public void afterClassTasks() {
    	SetupScript.tearDown();
    }

	@BeforeMethod
	public void beforeTest() throws Exception {
		SetupScript.setupTest();	
	}

    @Test()
    public void test1() {
        
  
    }
}

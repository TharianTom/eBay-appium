package com.eBay.tests;


import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

import com.eBay.runners.SetupScript;
import com.eBay.screens.HomeScreen;
import com.eBay.screens.NavDrawer;
import com.eBay.screens.ResultsScreen;
import com.eBay.screens.SignInScreen;

public class SmokeTests {
    private AppiumDriver driver;

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
    public void smoketest() {
    	HomeScreen.amIHere(10);
    	//HomeScreen.swipeRight();
    	HomeScreen.touchHome();
    	NavDrawer.touchSignIn();
    	SignInScreen.signIn("test21@gmail.com", "test@123");
    	SignInScreen.amINotHere();
    	SignInScreen.dismissGreeting();
    	HomeScreen.amIHere(20);
    	HomeScreen.searchText("65 inch tv");
    	ResultsScreen.amIHere(2);
    	ResultsScreen.touchMostExpensive();  	
    }
}

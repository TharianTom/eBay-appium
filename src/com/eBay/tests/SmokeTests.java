package com.eBay.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.eBay.helpers.ExcelUtil;
import com.eBay.runners.SetupScript;
import com.eBay.screens.HomeScreen;
import com.eBay.screens.NavDrawer;
import com.eBay.screens.ProductDetailsScreen;
import com.eBay.screens.QuantityUpdateScreen;
import com.eBay.screens.ResultsScreen;
import com.eBay.screens.ReviewOrderScreen;
import com.eBay.screens.SignInScreen;
import com.eBay.wrappers.BaseTest;
import com.eBay.wrappers.TestMethodCapture;

@Listeners(TestMethodCapture.class)
public final class SmokeTests extends BaseTest {
	
	@BeforeMethod
	public void beforeTest() throws Exception {
		SetupScript.setupTest();	
		ExcelUtil.setExcelFileSheet("SmokeTests");
		SetupScript.resetApp();
	}
	
	@Test(priority = 1, description = "Checkout With Expensive TV in current results screen")
    public void checkoutWithExpensiveItem() {
    	HomeScreen.amIHere(10);
    	//HomeScreen.swipeRight();
    	HomeScreen.touchHome();
    	NavDrawer.touchSignIn();
    	SignInScreen.signIn();
    	SignInScreen.amINotHere(20);
    	SignInScreen.dismissGreeting();
    	HomeScreen.amIHere(20);
    	HomeScreen.searchText();
    	ResultsScreen.amIHere(2);
    	ResultsScreen.touchMostExpensive();  
    	ProductDetailsScreen.amIHere(10);
    	ProductDetailsScreen.isPriceExpected();
    	ProductDetailsScreen.getProductName();
    	ProductDetailsScreen.buyItem();
    	QuantityUpdateScreen.amIHere(30);
    	QuantityUpdateScreen.hasExpectedPrice();
    	QuantityUpdateScreen.hasExpectedProductName();
    	QuantityUpdateScreen.goToReview();
    	ReviewOrderScreen.amIHere(40);
    	ReviewOrderScreen.validateProductDetails();
    }
	
	@Test(priority = 2, description = "Faling Test Case")
    public void failingTestCase() {
    	HomeScreen.amIHere(10);
    	//HomeScreen.swipeRight();
    	HomeScreen.touchHome();
    	NavDrawer.touchSignIn();
    	SignInScreen.signIn();
    	SignInScreen.amINotHere(20);
    	SignInScreen.dismissGreeting();
    	HomeScreen.amIHere(20);
    	
	}
}

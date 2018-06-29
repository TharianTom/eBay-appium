package com.eBay.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.eBay.helpers.ExcelUtil;
import com.eBay.helpers.TestListener;
import com.eBay.runners.SetupScript;
import com.eBay.screens.HomeScreen;
import com.eBay.screens.NavDrawer;
import com.eBay.screens.ProductDetailsScreen;
import com.eBay.screens.QuantityUpdateScreen;
import com.eBay.screens.ResultsScreen;
import com.eBay.screens.ReviewOrderScreen;
import com.eBay.screens.SignInScreen;
import com.eBay.wrappers.BaseTest;

@Listeners(TestListener.class)
public final class SmokeTests extends BaseTest {

	@BeforeMethod
	public void beforeTest() throws Exception {
		SetupScript.setupTest();
		ExcelUtil.setExcelFileSheet("SmokeTests");
		SetupScript.resetApp();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		SetupScript.stopActivity();
		;
	}

	@Test(priority = 1, description = "Checkout With Expensive TV in current results screen")
	public void checkoutWithExpensiveItem() {
		HomeScreen.amIHere();
		HomeScreen.touchHome();
		NavDrawer.touchSignIn();
		SignInScreen.signIn();
		SignInScreen.amINotHere();
		SignInScreen.dismissGreeting();
		HomeScreen.amIHere();
		HomeScreen.searchText();
		ResultsScreen.amIHere();
		ResultsScreen.touchMostExpensive();
		ProductDetailsScreen.amIHere();
		ProductDetailsScreen.isPriceExpected();
		ProductDetailsScreen.getProductName();
		ProductDetailsScreen.buyItem();
		QuantityUpdateScreen.amIHere();
		QuantityUpdateScreen.hasExpectedPrice();
		QuantityUpdateScreen.hasExpectedProductName();
		QuantityUpdateScreen.goToReview();
		ReviewOrderScreen.amIHere();
		ReviewOrderScreen.validateProductDetails();
	}

	@Test(priority = 2, description = "Failng Test Case")
	public void failingTestCase() {
		HomeScreen.amIHere();
		HomeScreen.touchHome();
		NavDrawer.touchSignIn();
		SignInScreen.signIn();
		SignInScreen.amINotHere();
		SignInScreen.dismissGreeting();
		HomeScreen.amIHere();
	}
}

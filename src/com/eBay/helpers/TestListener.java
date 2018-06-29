package com.eBay.helpers;

import org.openqa.selenium.WebDriverException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.eBay.wrappers.Screen;

public class TestListener implements ITestListener,IInvokedMethodListener {
	private static ThreadLocal<ITestNGMethod> currentMethods = new ThreadLocal<>();
	private static ThreadLocal<ITestResult> currentResults = new ThreadLocal<>();

 // This method will execute only when the test is pass.
 public void onTestSuccess(ITestResult tr) {
	 System.out.println("TEST CASE PASSED");
	 try{
		 ScreenshotUtility.captureScreenShot(tr, "pass");
	 }catch(WebDriverException e){
		 System.out.println("Failed To take ScreenShot");
	 }
 }

 // This method will execute only on the event of fail test.
 public void onTestFailure(ITestResult tr) {
	 System.out.println("TEST CASE FAILED");
	 Screen.getPageSource();
	 try{
		 ScreenshotUtility.captureScreenShot(tr, "fail");
	 }catch(WebDriverException e){
		 System.out.println("Failed To take ScreenShot");
	 }
 }

 @Override
 public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
   currentMethods.set(method.getTestMethod());
   currentResults.set(testResult);
 }

 @Override
 public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
   currentMethods.remove();
   currentResults.remove();
 }

 public static ITestNGMethod getTestMethod() {
   return checkNotNull(currentMethods.get(),
     "Did you forget to register the %s listener?", TestListener.class.getName());
 }

/**
  * Parameters passed from a data provider are accessible in the test result.
  */
 public static ITestResult getTestResult() {
   return checkNotNull(currentResults.get(),
     "Did you forget to register the %s listener?", TestListener.class.getName());
 }
 
 private static <T> T checkNotNull(T o, String msg, Object param) {
	  if (o == null) {
	    throw new NullPointerException(String.format(msg, param));
	  }
	  return o;
 }

@Override
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	
}

@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	
}
}


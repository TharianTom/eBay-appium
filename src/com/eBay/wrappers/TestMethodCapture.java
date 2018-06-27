package com.eBay.wrappers;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestMethodCapture implements IInvokedMethodListener {
	  private static ThreadLocal<ITestNGMethod> currentMethods = new ThreadLocal<>();
	  private static ThreadLocal<ITestResult> currentResults = new ThreadLocal<>();

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
	      "Did you forget to register the %s listener?", TestMethodCapture.class.getName());
	  }

	/**
	   * Parameters passed from a data provider are accessible in the test result.
	   */
	  public static ITestResult getTestResult() {
	    return checkNotNull(currentResults.get(),
	      "Did you forget to register the %s listener?", TestMethodCapture.class.getName());
	  }
	  
	  private static <T> T checkNotNull(T o, String msg, Object param) {
		  if (o == null) {
		    throw new NullPointerException(String.format(msg, param));
		  }
		  return o;
	  }
	}

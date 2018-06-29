package com.eBay.wrappers;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.eBay.runners.SetupScript;

public class BaseTest {

	@BeforeSuite
	public void beforeClassTasks() throws IOException {
		SetupScript.setUp();
	}

	@AfterSuite
	public void afterClassTasks() {
		SetupScript.tearDown();
	}

}

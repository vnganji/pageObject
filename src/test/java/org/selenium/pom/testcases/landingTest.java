package org.selenium.pom.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.baseTest;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObj.Landingpage;
import resources.DataUtil;
import resources.DriverManager;
import resources.DataUtil;

public class landingTest extends baseTest {

	public WebDriver driver = null;
	public Logger log = null;
	

	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		 intializeDriver();
		 driver=DriverManager.getDriver();
		 log = initializeLogger(new landingTest().getClass().getName());
	}
	
	

	@Test(priority = 1)
	public void Validateloginbtn() throws IOException, InterruptedException {
		
		if (!DataUtil.getRunnerMode("Validateloginbtn"))
			throw new SkipException("Validateloginbtn test skipped as runmode set to No");
	
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		System.out.println(prop.getProperty("URL"));
		driver.get(prop.getProperty("URL"));
		Landingpage lp = new Landingpage();
		if (!lp.getloginbtn().isDisplayed()) {

			sAssert.assertTrue(false);
			log.error("login button not displayed");

		} else {

			sAssert.assertTrue(true);
			log.info("login button displayed");
		}

		sAssert.assertAll();
		lp = null;
	}

	@Test(priority = 2)
	public void chkEmailid() throws IOException, InterruptedException {

		if (!DataUtil.getRunnerMode("chkEmailid"))
			throw new SkipException("chkEmailid test skipped as runmode set to No");

		Landingpage lp = new Landingpage();
		if (!lp.getLnk_email().isDisplayed()) {
			sAssert.assertTrue(false);
			log.error("email id not displayed on the login page");
		} else {
			sAssert.assertTrue(true);
			log.info("email id displayed on the login page");
		}

		sAssert.assertAll();
	}

	@AfterTest
	public void teardown() {
		
		if (driver != null) {
			driver.close();
			
		}
	}

}

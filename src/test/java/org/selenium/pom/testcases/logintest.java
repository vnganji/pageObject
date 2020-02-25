package org.selenium.pom.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.jsoup.helper.DataUtil;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.baseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObj.Landingpage;
import pageObj.Loginpage;
import resources.DriverManager;

public class logintest extends baseTest {

	public WebDriver driver = null;
	public Logger log = null;

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {

		intializeDriver();
		driver = DriverManager.getDriver();
		log = initializeLogger(new landingTest().getClass().getName());
	}

	@Test(dataProvider = "datagen", dataProviderClass = resources.DataUtil.class)
	public void validateLogin(Hashtable<String, String> data) throws IOException, InterruptedException {

		log.info("Driver Initialized Successfully");
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));

		Landingpage l1 = new Landingpage();
		l1.btn_login.click();
		Loginpage lp = new Loginpage();
		setText(lp.getUsername(), data.get("email"),"login:Username");
		setText(lp.getPwd(), data.get("pwd"),"login:Password");
		
	}

	@AfterTest
	public void teardown() {
		
		if (driver != null) {
			driver.quit();
			
		}

}}

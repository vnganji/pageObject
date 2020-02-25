package org.selenium.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.selenium.pom.testcases.landingTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObj.basePage;
import resources.DataUtil;
import resources.DriverManager;
import resources.WebCustElements;

public class baseTest extends WebCustElements {

	
	
	
	public String userdir = System.getProperty("user.dir");
	public Properties prop = null;
	public SoftAssert sAssert = new SoftAssert();
	public Logger log = null;
	
	
	public void intializeDriver() throws IOException, InterruptedException {
		WebDriver driver = null;
		prop = new Properties();
		FileInputStream config = new FileInputStream(userdir +"//src//main//java//resources//config.properties");
		prop.load(config);
		
		System.out.println(prop.getProperty("browser"));
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {

			/*System.setProperty("webdriver.chrome.driver", userdir +"//src//main//java//executables//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();*/
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName("chrome");
			dc.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.56.1:4445/wd/hub"),dc);
					
			
		}else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {}
		
		DriverManager.setDriver(driver);
		
		

	}
	
	
	public void teardown(WebDriver driver){
		driver.close();
	}
	
	
	public boolean runchkmode(String methodname){
		
		return DataUtil.getRunnerMode(methodname);
		
		
	}

}

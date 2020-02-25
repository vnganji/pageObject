package org.selenium.pom.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class spiceJet {
	public WebDriver driver;

	@Test
	public void spice() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver.get("https://www.spicejet.com");
		// selecting round trip
		driver.findElement(By.xpath("//input[@value='RoundTrip']")).click();
		// selecting departure city
		driver.findElement(By.xpath("//input[@name='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='HYD']"))
				.click();
		// selecting destination city
		driver.findElement(By.xpath("//input[@name='ctl00_mainContent_ddl_destinationStation1_CTXT']")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='GWL']"))
				.click();
		String[] departDate = "24-April-2020".split("-");
		String[] returnDate = "21-Apr-2020".split("-");
		WebElement month = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]"));
		WebElement year = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]"));
		boolean found = true;

		while (found) {

			System.out.println(checkStale(month));
			System.out.println(checkStale(year));

			if (month.getText().contains(departDate[1]) && (year.getText().contains(departDate[2]))) {
				driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//a[text()='"
						+ departDate[0] + "']")).click();
				found = false;
			} else {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}
		}

	}

	public boolean checkStale(WebElement obj){
		int count=0;
		while (count < 4) {
			   try {
			    //If exception generated that means It Is not able to find element then catch block will handle It.
			  if (obj.isDisplayed())
			    //If exception not generated that means element found and element text get cleared.
			    obj.clear();    
			   } catch (StaleElementReferenceException e) {
			    e.toString();
			    System.out.println("Trying to recover from a stale element :" + e.getMessage());
			    count = count + 1;
			   }
			   count = count + 4;
				return true;
			  }
	
		return false;
	}
}

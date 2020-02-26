package org.selenium.pom.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.baseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class roughTest {

	public static WebDriver driver = null;
@Test
	public void node() throws MalformedURLException{
		//adding new desired capabilities
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setBrowserName("chrome");
	dc.setPlatform(Platform.WINDOWS);
	
	driver = new RemoteWebDriver(new URL("http://192.168.56.1:4445/wd/hub"),dc);
			
	driver.get("https://www.google.com");
	
		
	}

	
	@Test(enabled=false)
	public void order() {
		List<String> names = new ArrayList<String>();
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://datatables.net/examples/basic_init/table_sorting.html");
		List<WebElement> table = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		for (WebElement tab : table) {
			names.add(tab.getText());
		}

		List<String> ordername = names;
		names.clear();
		// sort in ascending order tetett
		Collections.sort(ordername);
		// click the name column to get the ascending order
		driver.findElement(By.xpath("//table[@id='example']//tr[@role='row']/th[1]")).click();

		List<WebElement> table1 = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		for (WebElement tab : table1) {
			names.add(tab.getText());
		}
		boolean ascorder = true;
		for (int i = 0; i < 10; i++) {
			System.out.println(names.get(i));
			System.out.println(ordername.get(i));
			if (!names.get(i).equals(ordername.get(i))) {
				ascorder = false;
			}
		}

		if (ascorder){
			System.out.println("ascending order working fine");}
		else{
			System.out.println("ascending order not working fine");}
		
		//check for descending order
		Collections.sort(ordername,Collections.reverseOrder());
		names.clear();
		driver.findElement(By.xpath("//table[@id='example']//tr[@role='row']/th[1]")).click();

		List<WebElement> table2 = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		for (WebElement tab : table2) {
			names.add(tab.getText());
		}
		boolean dscorder = true;
		for (int i = 0; i < 10; i++) {
			System.out.println(names.get(i));
			System.out.println(ordername.get(i));
			if (!names.get(i).equals(ordername.get(i))) {
				dscorder = false;
			}
		}

		if (ascorder){
			System.out.println("descending order working fine");}
		else{
			System.out.println("descending order not working fine");}
		

	}

	@Test(enabled = false)
	public void makemyTrip() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/flights/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']//span[@class='tabsCircle appendRight5']")).click();
		driver.findElement(By.cssSelector("#fromCity")).click();
		String fromCity = "Hyderabad";
		String toCity = "Delhi";
		String depDate = "24,February 2020";
		String retDate = "24,April 2020";
		String[] dDate = depDate.split(",");
		String[] rDate = retDate.split(",");

		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys(fromCity);
		driver.findElement(By.xpath("//p[contains(text(),'" + fromCity + "')]")).click();
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#toCity")).click();
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement tofield = driver.findElement(By.xpath("//input[@placeholder='To']"));
		// js.executeScript("arguments[0].sendKeys(toCity);",tofield);
		// js.executeScript("document.getElementByxpath("")"
		tofield.sendKeys(toCity);

		/*
		 * //a.moveToElement(driver.findElement(By.cssSelector(
		 * "input[placeholder='To']"))).click().sendKeys(toCity).build().perform
		 * ();
		 * driver.findElement(By.xpath("//p[contains(text(),'"+toCity+"')]")).
		 * click();
		 * driver.findElement(By.xpath("//p[@data-cy='departureDate']")).click()
		 * ; while (!driver.findElement(By.xpath(
		 * "(//div[@class='DayPicker-Caption']/div)[1]")).getText().contains(
		 * dDate[1])){
		 * driver.findElement(By.xpath("//span[@aria-label='Next Month']")).
		 * click(); } driver.findElement(By.xpath(
		 * "(//div[@class='dateInnerCell']//p[text()='"+dDate[0]+"'])[1])")).
		 * click();
		 * 
		 */
	}

	@Test(enabled = false)
	public void autoSugg() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/flights/");
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//label[@for='fromCity']"))).click().keyDown(Keys.CONTROL)
				.sendKeys("Hyderabad").build().perform();
		driver.findElement(By.xpath("//p[contains(text(),'Hyderabad')]")).click();
	}

	@Test(enabled = false)
	public void dynamictn() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.spicejet.com/");

		driver.findElement(By.xpath("//input[@value='Departure City']")).click();
		driver.findElement(By.xpath("//a[@value='GOI']")).click();
		driver.findElement(By.xpath("//input[@value='Arrival City']")).click();
		driver.findElement(By.xpath("(//a[@value='KNU'])[2]")).click();

	}

	@Test(enabled = false)
	public void tables() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(
				"https://www.cricbuzz.com/live-cricket-scorecard/22773/nz-vs-ind-1st-t20i-india-tour-of-new-zealand-2020");
		driver.manage().window().maximize();
		WebElement table = driver.findElement(
				By.xpath("/div[@id='innings_1']/div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]/child::div[3]"));
		List<WebElement> players = table.findElements(By.xpath("//div[@class='cb-col cb-col-100 cb-scrd-itms'][1]"));
		int count = players.size();
		System.out.println(count);

		for (int i = 0; i < count; i++) {
			System.out.println(players.get(i).getText());
		}

	}

	@Test(enabled = false)
	public void lmWebDrivers() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		System.out.println(driver.findElements(By.tagName("a")).size());
		WebElement headerdiv = driver.findElement(By.xpath("//div[@id='nav-xshop']"));
		List<WebElement> welem = headerdiv.findElements(By.tagName("a"));

		for (WebElement elem : welem) {
			System.out.println(elem.getText());
		}

	}

	@Test(enabled = false)
	public void frames() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		Actions a = new Actions(driver);
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droppable']"));
		a.dragAndDrop(source, target).build().perform();

	}

	@Test(enabled = false)
	public void multipleWindows() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[text()='Gmail']"))).keyDown(Keys.CONTROL).click().build()
				.perform();
		a.moveToElement(driver.findElement(By.xpath("//a[text()='Images']"))).keyDown(Keys.CONTROL).click().build()
				.perform();
		Set<String> hwnds = driver.getWindowHandles();
		Thread.sleep(7000);
		Iterator<String> it = hwnds.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		/*
		 * for (String wnd:hwnds){ driver.switchTo().window(wnd);
		 * System.out.println(driver.getTitle()); }
		 */

	}

	@Test(enabled = false)
	public void actionMethod() {
		System.out.println("action methods");

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.gap.com/");
		Actions a = new Actions(driver);
		By denim = By.xpath("//a[text()='Denim']");
		a.moveToElement(driver.findElement(denim)).build().perform();
		By searchtext = By.xpath("//input[@name='searchText']");
		a.moveToElement(driver.findElement(searchtext)).click().sendKeys("shoes").contextClick().build().perform();
	}
}

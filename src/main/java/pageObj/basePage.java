package pageObj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public abstract class basePage {

	public WebDriverWait wait = null;
	public SoftAssert sAssert = new SoftAssert();
	public static int explicitWaittime = 15;
	public Logger log = null;

	public <T> void validatePage(WebDriver driver, T page) {
		
		initializeLogger("basePage");

		String pagename = ((basePage) page).getClass().toString();
		ExpectedCondition pageloadcondition = ((basePage) page).validatePageConditon();

		wait = new WebDriverWait(driver, explicitWaittime);
		try {
			wait.until(pageloadcondition);
		} catch (TimeoutException E) {
			E.getStackTrace();
			sAssert.fail(pagename + " page not displayed");
			log.error(pagename +" not dialayed, run stopped");
			System.exit(0);
		}

		sAssert.assertAll();

	}



	public void initializeLogger(String clsname) {
		// cls.getClass().getName()
		log = LogManager.getLogger(clsname);
	}

	public abstract ExpectedCondition validatePageConditon();

}

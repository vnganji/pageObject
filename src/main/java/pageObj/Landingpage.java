package pageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import resources.DriverManager;

public class Landingpage extends basePage {
	private WebDriver driver = null;

	@FindBy(xpath = "//a[contains(@href,'sign_in')]")
	public WebElement btn_login;
	@FindBy(xpath = "//h3[text()='An Academy to learn Everything about Testing']")
	public WebElement txt_landingpage;
	@FindBy(xpath = "//span[text()='info@qaclickacademy.com']")
	public WebElement lnk_email;

	public Landingpage() {
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 12), this);
		super.validatePage(driver, this);
	}

	public WebElement getloginbtn() {
		return btn_login;

	}

	public WebElement getLnk_email() {
		return lnk_email;
	}

	@Override
	public ExpectedCondition validatePageConditon() {
		return ExpectedConditions.visibilityOf(txt_landingpage);

	}

}

package pageObj;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.Test;

import resources.DriverManager;

public class Loginpage extends basePage {

	private WebDriver driver = null;
	// create page object for login
	@FindBy(xpath = "//input[@type='email']")
	public WebElement edt_username;
	@FindBy(xpath = "//input[@type='password']")
	public WebElement edt_password;
	@FindBy(xpath = "//input[@name='commit']")
	public WebElement btn_login;
	@FindBy(xpath = "//a[contains(text(),'Create an Account')]")
	public WebElement txt_content;
	
	
	public Loginpage(){
		this.driver=DriverManager.getDriver();
		PageFactory.initElements(driver,this);
		super.validatePage(driver, this);
	}

	public WebElement getUsername() {
		return edt_username;
	}

	public WebElement getPwd() {
		return edt_password;
	}



	public WebElement getlogin() {
		return btn_login;
	}

	@Override
	public ExpectedCondition validatePageConditon() {
		return ExpectedConditions.visibilityOf(txt_content);
		
	}

}

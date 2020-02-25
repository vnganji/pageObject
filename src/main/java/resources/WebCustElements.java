package resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Loader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebCustElements {

	public Logger log = null;
	
	public Logger initializeLogger(String clsname) {
	
		return LogManager.getLogger(clsname);
	}
	
	
	public void initilizeLog(){
		log = initializeLogger(new WebCustElements().getClass().getName());
	}

	
	public void setText(WebElement obj, String value, String Desc) {
		initilizeLog();
		System.out.println(obj.getText());
		try {
			obj.sendKeys(value);
			log.info(Desc + " set with value " + value);
		} catch (RuntimeException e) {
			log.error(Desc + " doesnt exist ");
		}

	}
}

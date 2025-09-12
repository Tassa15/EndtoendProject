package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePages {
	public final static Logger logger = LogManager.getLogger(LoginPage.class);
		
By btnAuthentification = By.xpath("//a[@aria-label='Login']//*[name()='svg']");

public void sAuthentifier () {
	Interact (btnAuthentification, "", "click");
	logger.info("Bouton clickable");
		
	}
	


}

package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class AuthentificationPage extends BasePages {
	public static final Logger logger = LogManager.getLogger(AuthentificationPage.class);
	
	By txtUserName = By.id("username");
	By txtPwd = By.id("password");
	By btnlogin = By.xpath("//button[normalize-space()='Log in']");
	
	public void insertUserName (String username) {
		Interact (txtUserName, username, "type");
		logger.info("username saisi avec succès");
	}
	
	public void insertPassword (String password) {
		Interact (txtPwd,password,"type");
		logger.info("password saisi avec succès");
		
	}
	public void clickBtn () {
	Interact (btnlogin, "", "click");
	logger.info("click sur bouton effectué");
			
	}
	
	
	
	}


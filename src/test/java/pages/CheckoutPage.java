package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePages {
	public static final Logger logger = LogManager.getLogger(CheckoutPage.class);
	 By btnPayement = By.xpath("//a[normalize-space()='Proceed to checkout']");
	 
	 By firstName = By.id("billing_first_name");
	 By lastName = By.id("billing_last_name");
	 By streetAdress = By.id("billing_address_1");
	 
			

}

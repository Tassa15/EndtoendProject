package pages;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePages {
	public static final Logger logger = LogManager.getLogger(CheckoutPage.class);
	By btnPayement = By.xpath("//a[normalize-space()='Proceed to checkout']");

	By firstName = By.id("billing_first_name");
	By lastName = By.id("billing_last_name");
	By streetAdress = By.id("billing_address_1");
	By postCode = By.id("billing_postcode");
	By town = By.id("billing_city");
	By phone = By.id("billing_phone");
	By emailAddress = By.id("billing_email");
	By terms = By.id("terms");
	By btcPlacrorder = By.xpath("//button[@id='place_order']");

	public void proceededtoCheckOut() {
		try {
			WebElement element = waitForElementToBeClickableAndScroll(btnPayement, ScrollPosition.BOTTOM);
			if (element == null) {
				logger.error("le bouton 'acceder au payment'est introuvable");
				throw new RuntimeException("le bouton 'acceder au payment'est introuvable");
			}
			try {
				element.click();
				logger.info("click sur le bouton 'accceder au payment' réussi");
			} catch (Exception e) {
				logger.warn("click sur le bouton'payment' avec selenium échoué, tentative avec js");
				((JavascriptExecutor) driver).executeScript("arguements [0].click(),", element);
				logger.info("click js sur le bouton 'payement' réussi");
			}

		} catch (Exception e) {
			logger.info("le boutton 'payment' est introuvable");

		}
	}

	public void saisiDesChampsRequis(Map<By, String> champs) {

		for (Map.Entry<By, String> entry : champs.entrySet()) {

			By locator = entry.getKey();
			String value = entry.getValue();

			ScrollPosition position = ScrollPosition.TOP;
			if (locator.equals(town) || locator.equals(phone) || locator.equals(emailAddress) || locator.equals(terms)
					|| locator.equals(btcPlacrorder)) {
				position = ScrollPosition.BOTTOM;
			}

		}
	}

}

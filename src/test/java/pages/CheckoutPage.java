package pages;

import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePages.ScrollPosition;
import utilities.ExtentReportListner;

public class CheckoutPage extends BasePages {
	public static final Logger logger = LogManager.getLogger(CheckoutPage.class);

	public By btnCheckout = By.xpath("//a[normalize-space()='Proceed to checkout']");
	public By inputFirstName = By.id("billing_first_name");
	public By inputLastName = By.id("billing_last_name");
	public By inputStreerAddress = By.id("billing_address_1");
	public By inputPostcode = By.id("billing_postcode");
	public By inputTown = By.id("billing_city");
	public By inputPhone = By.id("billing_phone");
	public By inputEmailAddress = By.id("billing_email");
	public By acceptTerms = By.id("terms");
	public By placeOrder = By.id("place_order");

	// =======================
	// Accéder à la page paiement
	// =======================
	public void ProceedtoCheckout() {
		try {
			WebElement element = waitForElementToBeClickableAndScroll(btnCheckout, ScrollPosition.BOTTOM);
			if (element == null) {
				logger.error("le bouton 'btnCheckout' est introuvable");
				throw new RuntimeException("le bouton 'btnCheckout' est introuvable");
			}
			try {
				// essayer avec selenium
				element.click();
				logger.info("click selenium 'proceed to checkout' réussi");
			} catch (Exception e) {
				logger.warn("click selenium échoué, tentative avec js");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				logger.info("click Js sur le bouton 'proceed to checkout' réussi");
			}
		} catch (Exception e) {
			logger.error("erreur lors de la tentative de clic sur le boutton 'proceed to checkout': {}",
					e.getMessage());
		}
	}

	// on déclare la methode de saisideschamps requis qui va prendre comme paramètre
	// :

	public void saisiDesChamprsRequis(Map<By, String> champs) {
		// la boucle va contenir un localisateur et une valeur.
		for (Map.Entry<By, String> entry : champs.entrySet()) {

			By locator = entry.getKey();// recupère la clé (le champ à remplir)

			String value = entry.getValue();// recupère la valeur à saisir

			// on initialise par défaut le scroll en haut,

			ScrollPosition position = ScrollPosition.TOP;
			// on vérifie si les elemnts suivant sont en bas, on scroll en bas

			if (locator.equals(inputTown) || locator.equals(inputPhone) || locator.equals(inputEmailAddress)
					|| locator.equals(acceptTerms) || locator.equals(placeOrder)) {

				position = ScrollPosition.BOTTOM;
			}

			WebElement element = waitForElementToBeClickableAndScroll(locator, position);

			if (element != null) { // verifier si l'element n'est pas nul
				try {// pour les champs de texte
					if (value != null && !value.isEmpty()) {// on vérifie que la valeur qu'on doit saisir dans le champ
															// n'est pas nulle et pas vide
						// si c'est vrai on va faire clear et sendkeys
						element.clear();
						element.sendKeys(value);
						logger.info("valeur saisie dans : \" + locator + \" -> \" + value");// exemple de log :
																							// By.id("billing_first_name")
																							// -> Tassadit

					} else { // autre
						// BOUTON OU CHECKBOX - CLICK NORMAL
						try {
							element.click();
							logger.info("Clic effectué sur : " + locator);

						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							logger.info("Clic JS effectué sur : " + locator);
						}
					}
				} catch (Exception e) {
					logger.error("Erreur lors de la saisie ou du clic : " + locator + " - " + e.getMessage());
				}
			} else {// renvoie au if d'en haut (if(element!=null)
				logger.error("Impossible de trouver l'élément : " + locator);
			}
		}
	}
}

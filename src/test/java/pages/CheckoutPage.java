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
    public By inputStreetAddress = By.id("billing_address_1");
    public By inputPostcode = By.id("billing_postcode");
    public By inputTown = By.id("billing_city");
    public By inputPhone = By.id("billing_phone");
    public By inputEmailAddress = By.id("billing_email");
    public By acceptTerms = By.id("terms");
    public By placeOrder = By.id("place_order");

    // Accéder à la page paiement
    public void proceedToCheckout() {
        try {
            WebElement element = waitForElementToBeClickableAndScroll(btnCheckout, ScrollPosition.BOTTOM);
            if (element == null) {
                logger.error("Le bouton 'btnCheckout' est introuvable");
                throw new RuntimeException("Le bouton 'btnCheckout' est introuvable");
            }
            try {
                element.click();
                logger.info("Click Selenium 'proceed to checkout' réussi");
            } catch (Exception e) {
                logger.warn("Click Selenium échoué, tentative avec JS");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                logger.info("Click JS sur le bouton 'proceed to checkout' réussi");
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la tentative de clic sur le bouton 'proceed to checkout': " + e.getMessage());
        }
    }

    public void saisiDesChampsRequis(Map<By, String> champs) {
        for (Map.Entry<By, String> entry : champs.entrySet()) {
            By locator = entry.getKey();
            String value = entry.getValue();

            ScrollPosition position = ScrollPosition.TOP;
            if (locator.equals(inputTown) || locator.equals(inputPhone) || locator.equals(inputEmailAddress)
                    || locator.equals(acceptTerms) || locator.equals(placeOrder)) {
                position = ScrollPosition.BOTTOM;
            }

            WebElement element = waitForElementToBeClickableAndScroll(locator, position);

            if (element != null) {
                try {
                    if (value != null && !value.isEmpty()) {
                        element.clear();
                        element.sendKeys(value);
                        logger.info("Valeur saisie dans : " + locator + " -> " + value);
                    } else {
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
            } else {
                logger.error("Impossible de trouver l'élément : " + locator);
            }
    	
            }
        
        }
    //
   
    public boolean isProductInCart(String produceName) {
    	String productText = driver.findElement(By.xpath("//td[@class='product-name']")).getText();
    	return productText.contains(produceName);
    }
    	public boolean isPlaceOrderButtonDiplayed () {
    		return driver.findElement(placeOrder).isDisplayed();
    		
    	}
    	public boolean isAcceptTermsChecked() {
    		return driver.findElement(acceptTerms).isSelected();
        }
    	
    	}
    


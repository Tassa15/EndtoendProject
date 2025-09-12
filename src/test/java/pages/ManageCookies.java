package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.ExtentReportListner;

public class ManageCookies extends BasePages {

	public final static Logger logger = LogManager.getLogger(ManageCookies.class);

	By cookiesBtn = By.xpath("//button[normalize-space(text())='Accepter']");

	public void gererCookies() {

		try {
			WebElement element = WaitToElementToBeVisible(cookiesBtn);
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				logger.info("cookies accceptees");
				ExtentReportListner.test.info("cookies acceptees");
			} else {
				logger.warn("bouton cookies non clickable");

			}

		} catch (Exception e) {
			logger.error("erreur lors de la gestion des cookies", e);
		}
	}
}

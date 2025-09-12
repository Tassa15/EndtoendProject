package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MenuBoutique extends BasePages {

    public final static Logger logger = LogManager.getLogger(MenuBoutique.class);

    By menuBoutique = By.xpath("//li[@id='menu-item-689']//a[@role='menuitem'][normalize-space()='Boutique']");
    By selectProduct = By.xpath("//li[@id='menu-item-1552']//a[@role='menuitem'][normalize-space()='Chaussure']");
    String produit;
    By btnAjouterauPanier = By.xpath("//a[@aria-label='Add to cart: “Air Jordan 1 Low”']");
    By btnCheckCard = By.xpath(
            "//div[@data-device='desktop']//div[@data-row='middle']//span[@class='ct-icon-container']//*[name()='svg']");

    public void listProduit() {
        try {
            selectOptionsListeLi(menuBoutique, selectProduct, produit);
            logger.info("option {} selectionnée dans la liste avec succès");
        } catch (Exception e) {
            logger.error("option introvable : {}", e.getMessage());
        }
    }

    public void addArticle() {
        try {
        	produit = "Chaussure";
        	listProduit();
            WebElement btn = waitForElementToBeClickableAndScroll(btnAjouterauPanier, ScrollPosition.CENTER);
            if (btn == null) {
                logger.error("bouton ajouter au panier introuvable");
                throw new RuntimeException("bouton ajouter au panier introuvable");
            }
            try {
                btn.click();
                logger.info("click sur le bouton réussi");
            } catch (Exception e) {
                logger.warn("boutton introuvable, tentative avec JS");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                logger.info("clic JS ajouté au panier réussi");
            }
        } catch (Exception e) {
            logger.error("boutton ajouté au panier non clickable", e);
        }
    }

    public void CheckCard() {
        try {
            WebElement btnCheck = waitForElementToBeClickableAndScroll(btnCheckCard, ScrollPosition.TOP);
            if (btnCheck == null) {
                logger.error("impossible de trouver le bouton panier: element nul");
                return;
            }

            int retries = 5;
            boolean clicked = false;

            while (retries > 0 && !clicked) {
                boolean isInViewport = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].getBoundingClientRect().top >= 0", btnCheck);

                if (!isInViewport) {
                    logger.warn(
                            "⚠ Le bouton panier n'est pas complètement visible, scroll fallback en cours... Tentatives restantes: {}",
                            retries);
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", btnCheck);
                    Thread.sleep(500); // Pause pour laisser le scroll se faire
                }

                try {
                    btnCheck.click(); // tentative de clic Selenium
                    clicked = true;
                    logger.info("click sur le bouton 'panier' réussi");
                } catch (Exception e) {
                    logger.error("clic Selenium échoué, tentative avec JS", e);
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCheck);
                        clicked = true;
                        logger.info("Clic JS sur le bouton panier réussi");
                    } catch (Exception jsEx) {
                        logger.warn("Clic JS échoué également : {}", jsEx.getMessage());
                    }
                }

                retries--; // on réduit le compteur des tentatives
            }

            if (!clicked) {
                logger.error("Impossible de cliquer sur le bouton panier après plusieurs tentatives");
            }

        } catch (Exception e) {
            logger.error("Erreur critique lors du clic sur le bouton panier: {}", e.getMessage());
        }
    } // <-- fin méthode CheckCard

} // <-- fin classe MenuBoutique

package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Utility;

public abstract class BasePages {

	private final static Logger logger = LogManager.getLogger(BasePages.class);
//je déclare le driver commun pour que les classes qui vont hériter de cette classe l'utiliseront
	protected WebDriver driver;

	//enum  ici pour qu'il soit accessible partout dans la classe(on le met au niveau de la classe.
	public enum ScrollPosition {
	    TOP,
	    CENTER,
	    BOTTOM

	}

//je déclare la variable de la classe actions qui sert à faire des actions avce la souris et le clavier
	protected Actions action;

	/*
	 * Elle a des méthodes prêtes à l’emploi : interact() pour cliquer, taper…o
	 * hover() pour passer la souris ,waitForElementToBeVisible() pour attendre
	 * qu’un élément apparaisse
	 */
//on déclare le constructeur de la basepages: toutes les classses qui vont hériter de basepage
	public BasePages() {//un constructeur doit avoir le mmeme nom de la classe
//
		this.driver = Utility.getDriver();// je recupère le driver initialisé.

		this.action = new Actions(driver);// créé l'objet action basé sur ce driver pour faire des manipulations
		/*
		 * Actions est une classe utilitaire de selenium qui permet d'executer des
		 * actions avancées de l'utilisateur (mouvement de souris (movetoelement),
		 * clique maintenu (clickAndHold glisser deposer (dragAndDrop)
		 *
		 *
		 *
		 */

	}
/////////////////DECLARATION DES METHODES////////////
//je commence par la methode waitToElementToBeVisible mais avant je dois lui configurer sa variable

	public final static int TIME_OUT = 15;

	// je declare la methode

	public WebElement WaitToElementToBeVisible(By locator) {
		try {
			// on créé un bojet webdriver wait qui va attenndre un certain temps

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
			//webdriverwait  est un objet de selenium qui permet de faire attendre le navigateur jusqu'à ce qu'une condition soit vraie
			// on attend jusqu'à ce que l'element soit visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info("lelement est visible ");
			return element ;
		} catch (Exception e) {
			logger.error("element non visible", e);
			throw new RuntimeException("Element non visible:" + locator, e);
			// la methode WaitToElementToBeVisibledoit toujours retourner un webelement même
			// en cas d'erreur

		}
	}

	public WebElement WaitToElementToBeClickable(By locator) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			logger.error("element non clickable", e);
			throw new RuntimeException("Element non cliquable:" + locator, e);
		}
	}

	// ATTENDRE QU'UNE LISTE DELEMENT SOIT VISIBLE

	public List<WebElement> waitForAllElementsVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			logger.error("Impossible de trouver les éléments visibles : {}", locator, e);
			throw new RuntimeException("Impossible de trouver les éléments visibles : " + locator, e);
		}
	}

	public boolean waitToElementToDisappear(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		} catch (Exception e) {
			logger.error("Élément toujours présent : {}", locator);
			throw new RuntimeException("Élément toujours présent : " + locator, e);
		}

	}



		public void Interact(By locator, String value, String action) {
			// avant quoi que ce soit, lelement dit etre dabord visible et puis stocké dans
			// element de type webelement
			
		    WebElement element = WaitToElementToBeVisible(locator);

		    if (element.isDisplayed() && element.isEnabled()) {
		        switch (action.toLowerCase()) {

		            case "click":
		                element.click();
		                logger.info("Cliquer sur l’élément: {}", element);
		                break;

		            case "type":
		                element.click();   // assure le focus sur le champ
		                element.clear();   // vide le champ avant saisie

		                try {
		                    for (char c : value.toCharArray()) {
		                        element.sendKeys(Character.toString(c));
		                        Thread.sleep(50); // petite pause entre chaque caractère
		                    }
		                } catch (InterruptedException e) {
		                    logger.error("Erreur pendant la saisie caractère par caractère", e);
		                    Thread.currentThread().interrupt();
		                }

		                logger.info("Valeur saisie (longueur={})", value.length());
		                break;

		            case "submit":
		                element.submit();
		                logger.info("Élément soumis: {}", element);
		                break;

		            default:
		                logger.warn("Action inconnue: {}", action);
		                break;
		        }
		    } else {
		        logger.error("Élément non interactif : {}", locator);
		    }
		}


	public void selectOptionsListeLi(By menuHoverLocator, By listItemsLocator, String valueToSelect) {
			/*les arguments :
			 * menuHoverLocator : le selecteur (by) pour trouver l'element qui déclenche le menu li/di sur lequel on fait le hover
			 * listItemsLocator : le sélecteur pour trouver toutes les options visibles dans la liste.
			 * valueToSelect le texte exacte de l'option à selectionner
			 */
			// récupérer l'élement MenuHover et le stocker avec la méthode wait
			//menuHover est l'element qui contient le menu à survoler
			WebElement menuHover = WaitToElementToBeVisible(menuHoverLocator);
			logger.info("Element  trouvé :{}", menuHover);


			// survoller le menuHover une fois visible grace à
			action.moveToElement(menuHover).perform();
			/*action est une classe de selenium,
			 *moveToElement est une methode de la classe 'action'
			 *
			 *
			 */
			logger.info("survol de menu réussi : {}",menuHoverLocator);
			// récupérer tous les élements de la liste avec la méthode wait

			List<WebElement> listOptions = waitForAllElementsVisible(listItemsLocator);

			// Flag pour vérifier si l'option a été trouvée
			boolean found = false;

			for (WebElement option : listOptions) {//pour chaue element, tu lui attribues une option de la liste
	            String optionText = option.getText().trim();//recupere un texte de cette option specifique et stock le dans une variable optionText de type string
	            logger.info("Option trouvée : {}", optionText);

	            if (optionText.equalsIgnoreCase(valueToSelect.trim())) {//si le texte trouvé correspond à la valeur souhaitee
	                option.click();//clicque dessus
	                logger.info("Option '{}' sélectionnée avec succès", valueToSelect);
	                found = true;
	                break;
	            }
			}
	}

	public WebElement waitForElementToBeClickableAndScroll(By locator, ScrollPosition position) {
	    try {
	        WebElement element = WaitToElementToBeVisible(locator);

	        // Scroll avec scrollIntoView selon position demandée
	        String jsPosition = "";
	        switch (position) {
	            case CENTER:
	                jsPosition = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});";
	                break;
	            case TOP:
	                jsPosition = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});";
	                break;
	            case BOTTOM:
	                jsPosition = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'end'});";
	                break;
	        }

	        ((JavascriptExecutor) driver).executeScript(jsPosition, element);
	       // offset pour compenser un header sticky (optionnel)
	     //   int offset = 80; // hauteur approximative du header
	       // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", -offset);

	        return new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(ExpectedConditions.elementToBeClickable(locator));

	    } catch (Exception e) {
	        logger.error("Échec pour rendre l'élément {} cliquable et visible. Erreur : {}", locator, e.getMessage());
	        return null;
	    }
	}
}
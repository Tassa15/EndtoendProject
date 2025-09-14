package tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AuthentificationPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ManageCookies;
import pages.MenuBoutique;
import pages.PageDaccueil;
import utilities.ConfigReader;
import utilities.ExtentReportListner;
import utilities.Utility;

public class PurchaseFlowTests {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        Utility.initDriver("normal", ConfigReader.getProperty("browser"));
        driver = Utility.getDriver();
        ExtentReportListner.initReport("parcours d'achat - smoke test");
    }

    @Test
    public void saisirLeschampsRequis() throws InterruptedException{
        System.out.println("Démarrage du test de parcours d'achat");

        try {
            PageDaccueil homePage = new PageDaccueil();
            ManageCookies cookiesMng = new ManageCookies();
            LoginPage lp = new LoginPage();
            AuthentificationPage ap = new AuthentificationPage();
            MenuBoutique mb = new MenuBoutique();
            CheckoutPage cp = new CheckoutPage();
            

            // Récupération des valeurs depuis le fichier de config
            String username = ConfigReader.getProperty("username");
            String password = ConfigReader.getProperty("password");
            String firstName = ConfigReader.getProperty("firstName");
            String lastName = ConfigReader.getProperty("lastName");
            String streetAddress = ConfigReader.getProperty("streetAddress");
            String postcode = ConfigReader.getProperty("postcode");
            String town = ConfigReader.getProperty("town");
            String phone = ConfigReader.getProperty("phone");
            String email = ConfigReader.getProperty("email");

            // Déclarer une MAP pour les champs à remplir
            Map<org.openqa.selenium.By, String> champs = new LinkedHashMap<>();
            champs.put(cp.inputFirstName, firstName);
            champs.put(cp.inputLastName, lastName);
            champs.put(cp.inputStreerAddress, streetAddress);
            champs.put(cp.inputPostcode, postcode);
            champs.put(cp.inputTown, town);
            champs.put(cp.inputPhone, phone);
            champs.put(cp.inputEmailAddress, email);
            champs.put(cp.acceptTerms, "");
            champs.put(cp.placeOrder, "");

            // Navigation et actions
            homePage.accessUrl();
            System.out.println("Accès URL réussi");
            ExtentReportListner.test.pass("Accès URL réussi");

            cookiesMng.gererCookies();
            System.out.println("Cookies gérées avec succès");
            ExtentReportListner.test.pass("Cookies gérées avec succès");

            lp.sAuthentifier();
            System.out.println("Click sur le bouton 's’authentifier' réussi");
            ExtentReportListner.test.pass("Click sur le bouton 's’authentifier' réussi");

            ap.insertUserName(username);
            System.out.println("Username saisi avec succès");
            ExtentReportListner.test.pass("Username saisi avec succès");

            ap.insertPassword(password);
            System.out.println("Mot de passe saisi");
            ExtentReportListner.test.pass("Mot de passe saisi");

            ap.clickBtn();
            System.out.println("Authentification réussie");
            ExtentReportListner.test.pass("Authentification réussie");

            mb.listProduit();
            System.out.println("Affichage de la liste produit réussi");
            ExtentReportListner.test.pass("Affichage de la liste produit réussi");

            mb.addArticle();
            System.out.println("Article ajouté avec succès");
            ExtentReportListner.test.pass("Article ajouté au panier");

            mb.CheckCard();
            System.out.println("Panier vérifié");
            ExtentReportListner.test.info("Panier vérifié");

            // Cliquez sur le bouton Proceed to Checkout
            cp.ProceedtoCheckout();
            System.out.println("Click sur 'proceed to checkout' réussi");
            ExtentReportListner.test.info("Click sur 'proceed to checkout' réussi");

            // Wait explicite pour que la page de paiement soit chargée
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(cp.inputFirstName));
            System.out.println("Page de paiement chargée");

            // Remplissage des champs
            cp.saisiDesChamprsRequis(champs);
            System.out.println("Champs de paiement remplis");
            ExtentReportListner.test.pass("Champs de paiement remplis");
            
            
            
            Thread.sleep(5000);
            

        } catch (Exception e) {
            System.out.println("Erreur lors du parcours : " + e.getMessage());
            ExtentReportListner.test.fail("Erreur lors du parcours : " + e.getMessage());
            e.printStackTrace();
            
           
        }
    }

    @AfterAll
    public static void tearDown() {
        Utility.quitDriver();
        ExtentReportListner.flushReport();
    }
}

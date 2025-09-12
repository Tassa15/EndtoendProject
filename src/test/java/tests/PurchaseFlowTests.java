package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import pages.AuthentificationPage;
import pages.LoginPage;
import pages.ManageCookies;
import pages.MenuBoutique;
import pages.PageDaccueil;
import utilities.ConfigReader;
import utilities.ExtentReportListner; 
import utilities.Utility;

public class PurchaseFlowTests {
	public static WebDriver driver ;
	
	@BeforeAll

	public static void setUp() {//preparer l'environnement avant d'executer les tests

		Utility.initDriver("normal", ConfigReader.getProperty("browser"));
		driver = Utility.getDriver();
	//initialisation du rapport avec nom de test
		ExtentReportListner.initReport("parcours d'achat - smoke test");
	}
	@Test
	public void purchaseFlow() {
	    System.out.println("Démarrage du test de parcours d'achat");
	    try {
	        PageDaccueil homePage = new PageDaccueil();
	        ManageCookies cookiesMng = new ManageCookies();
	        LoginPage lp = new LoginPage();
	        AuthentificationPage ap = new AuthentificationPage ();
	        MenuBoutique mb = new  MenuBoutique();
	        //recuperation ds valeurs depuis le fichier de config
	        String username = ConfigReader.getProperty("username");
	        String password = ConfigReader.getProperty("password");
	        
	        
	        homePage.accessUrl();
	        System.out.println("Accès URL réussi");
	        ExtentReportListner.test.pass("Accès URL réussi");
	        
	        cookiesMng.gererCookies();
	        System.out.println("Cookies gérées avec succès");
	        ExtentReportListner.test.pass("Cookies gérées avec succès");
	        
	        lp.sAuthentifier();
	        System.out.println("click sur le bouton 'sauthentifier' réussi");
	        ExtentReportListner.test.pass("click sur le bouton 'sauthentifier' réussi");
	        ap.insertUserName(username);
	        System.out.println("username saisi avec succès");
	        ExtentReportListner.test.pass("username saisi avec succès");
	        ap.insertPassword(password);
	        System.out.println("mot de passe saisi");
	        ExtentReportListner.test.pass("mot de passe saisi");
	        ap.clickBtn();
	        System.out.println("Authentification réussie");
	        ExtentReportListner.test.pass("Authentification réussie");
	        mb.listProduit();
	        System.out.println("affichage de la liste produit réussi");
	        ExtentReportListner.test.pass("affichage de la liste produit réussi");
	        mb.addArticle();
	        System.out.println("article ajouté avec succès");
	        ExtentReportListner.test.pass("article ajouté au panier");
	        mb.CheckCard();
	        System.out.println("panier vérifié");
	        ExtentReportListner.test.info("panier vérifié");
	       	    
	    } catch(Exception e) {
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


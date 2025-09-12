package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import utilities.ConfigReader;
import utilities.ExtentReportListner;

public class PageDaccueil extends BasePages {

	public final static Logger logger = LogManager.getLogger(PageDaccueil.class);

	
	public void accessUrl()	{
		//je recupere la valeur base url depuis le fichier de config
		String url =ConfigReader.getProperty("base.url");
	//j'ouvre l'url
		driver.get(url);
		//logs et reporting
		logger.info("L'ur saisie est :{}", url);
		//ExtentReportListner.test.info("URL saisie:"+ url);
	}

}


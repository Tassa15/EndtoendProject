package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {

	private static WebDriver driver ;


	public static void initDriver(String mode, String browser) {
		if (driver == null) {

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions ();

	options.addArguments("__incognito");
	driver = new ChromeDriver(options);
	driver.manage().window().maximize();
		}
	}

	public static WebDriver getDriver () {
		return driver;
	}

public static void quitDriver () {
if (driver != null) {
	driver.quit();
	driver = null;
}

}


}




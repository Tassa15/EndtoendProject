package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListner {

	/*Création de l'objet qui gère le rapport html*/
	public static ExtentReports extent;

	/*l’objet qui représente le scénario en cours : il sert à écrire (pass, fail, info, etc.)*/
	public static  ExtentTest test;

	public static void initReport(String testName) {
		
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		ExtentSparkReporter report = new ExtentSparkReporter("target/extent/rapport-test-shopping.html");
		
		report.config().setDocumentTitle("Rapport Tests Automation");
	    report.config().setReportName("Parcours d'achat - Smoke Test");
	    report.config().setTheme(Theme.STANDARD);
		
		
		
		extent = new ExtentReports();
		extent.attachReporter(report);
		test = extent.createTest(testName);

	}

	public static void flushReport() {

		extent.flush();
	}

}

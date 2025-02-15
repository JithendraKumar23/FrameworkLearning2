package test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DummyTest {
	
	public static WebDriver driver ;

	public static void main(String[] args) throws Exception {
		
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./Report/ExtentReport.html");
		extentReports.attachReporter(extentSparkReporter);
		
		/*
		 * extentSparkReporter.config().setTheme(Theme.DARK);
		 * extentSparkReporter.config().setDocumentTitle("Doc Title");
		 * extentSparkReporter.config().setReportName("Report Name");
		 * extentSparkReporter.config().setTimeStampFormat("dd/MM/YYYY HH:mm:ss");
		 */
		
		extentSparkReporter.loadJSONConfig(new File("./ExtentReportConfig/Extent-Report-Config.json"));
		
		driver = new ChromeDriver();
		driver.get("https://polarislogin-so.replicon.com/");
		
		ExtentTest extentTest1 = extentReports.createTest("TestCase1");
		extentTest1.log(Status.PASS, "TestCase1 is Passed");
		
		ExtentTest extentTest2 = extentReports.createTest("TestCase2");
		extentTest2.fail("TestCase2 is Failed");
		
		extentReports.createTest("TestCase3").skip("<i>TestCase3 is Skipped</i>");
		
		extentReports.createTest("TestCase4").info("<b>TestCase4 is Info</b>");
		
		extentReports.createTest("TestCase5")
		.fail("TestCase5 is Failed")
		.info(MarkupHelper.createLabel("Highlight the Test Case in the COLOR", ExtentColor.RED));
		
		// Log the Exception info in the Report , should use the ".info("Throwable t")" Method
		extentReports.createTest("TestCase5")
		.info("Throwable t");	
		
		extentReports.createTest("TestCase6" , "CaptureScreenshot")
		.addScreenCaptureFromPath(getScreenshot("TestCase6") , "Screeshot Captured @ TestLevel");
		
		extentReports.createTest("TestCase7")
		.info("TestCase 7")
		.fail("TestCase Failed" , MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("TestCase7")).build());
		
		extentReports.createTest("TestCase8")
		.pass("TestCase Passed")
		.assignAuthor("Jithendra")
		.assignCategory("Smoke")
		.assignDevice("Chrome1.1");
		
		extentReports.createTest("TestCase9")
		.fail("TestCase Failed")
		.assignAuthor("Kumar")
		.assignCategory("Regression")
		.assignDevice("Edge1.1");
		
		extentReports.createTest("TestCase10")
		.pass("TestCase Passed")
		.assignAuthor("Jithendra")
		.assignCategory("Smoke")
		.assignCategory("Regression")
		.assignDevice("Chrome1.1")
		.assignDevice("Edge1.1")
		.assignDevice("Firefox1.1");
		
		extentReports.createTest("TestCase10")
		.pass("TestCase Passed")
		.assignAuthor("Jithendra")
		.assignCategory("Smoke" , "Regression")
		.assignDevice("Chrome1.1" , "Edge1.1" , "Firefox1.1");
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File("C:\\Users\\jeeth\\OneDrive\\Documents\\Workspace\\FrameworkLearning2\\Report\\ExtentReport.html").toURI());
	}
	
	public static String getScreenshot(String fileName) throws IOException 
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver ;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/"+fileName+".png");
		FileUtils.copyFile(srcFile, destFile );
		return destFile.getAbsolutePath();
	}

}

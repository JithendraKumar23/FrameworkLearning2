package generic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class BaseTest implements IAutoConst{
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	@Parameters({"Grid", "GridURL", "Browser", "ENV"})
	
	@BeforeTest
	public void launchApplication(@Optional(GRID) String grid, @Optional(GRIDURL) String GridURL, @Optional(BROWSER) String br, @Optional(ENV) String env) throws MalformedURLException
	{
		if(grid.equalsIgnoreCase("Yes")){
			
			URL url = new URL(GridURL);
			if(br.equalsIgnoreCase("Chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Chrome Browser Lanched",true);
			}
			else if (br.equalsIgnoreCase("Firefox")) 
			{
				FirefoxOptions options = new FirefoxOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Firefox Browser Lanched",true);
			}
			else
			{
				EdgeOptions options = new EdgeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Edge Browser Lanched",true);
			}
		}
		
		else{
			
		if(br.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			System.out.println("Chrome launch");
		}
		else if (br.equalsIgnoreCase("Firefox")) 
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new EdgeDriver();
		}
	}
		String urlOfTheApplication = Utilities.getProperties(env , "URL");
		Reporter.log("App URL : " + urlOfTheApplication,true);
		driver.get(urlOfTheApplication);
		
		driver.manage().window().maximize();
		
		String ITO = Utilities.getProperties(env, "ITO");
		int implicitTimeOutInInt = Integer.parseInt(ITO);
		Reporter.log("ITO : " + implicitTimeOutInInt, true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeOutInInt));

		String ETO = Utilities.getProperties(env, "ETO");
		int explictTimeOutInInt = Integer.parseInt(ETO);
		Reporter.log("ETO : " + explictTimeOutInInt, true);
		wait = new WebDriverWait(driver, Duration.ofSeconds(explictTimeOutInInt));	 
		
		//extentTest = extentReports.createTest(context.getName());
}
	
	@AfterMethod
	public void takeScreenshot(ITestResult testresult) throws Exception
	{
		// Printing the test case name is moved to listerns class
		String testCaseName = testresult.getName();
		//Reporter.log("TestCase Name : " + testCaseName,true);
		
		int testCaseStatus = testresult.getStatus();
		String resultInString = String.valueOf(testCaseStatus);
		Reporter.log("TesCase Status[ 1-SUCCESS , 2-FAILURE , 3-SKIP ] : " + resultInString,true);
				
		if (testCaseStatus == 2) 
		{
			TakesScreenshot tscreen = (TakesScreenshot)driver;
			File srcFile = tscreen.getScreenshotAs(OutputType.FILE);
			
			String path = SCREENSHOT+testCaseName+".png";
			File dstFile = new File(path);
			
			FileUtils.copyFile(srcFile, dstFile);
			Reporter.log(testCaseName+" TestCase is failed , Screenshot is taken", true);
		}
		else
		{
			Reporter.log(testCaseName+" TestCase is passed , Screenshot is NOT taken", true);
		}

	}
	
	@AfterTest
	public void closeBrowser() 
	{
		//driver.quit();
	}
	
	@BeforeSuite
	public void initializeExtentReport() throws IOException 
	{
		extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./Report/ExtentReport.html");
		extentReports.attachReporter(extentSparkReporter);
		
		// System releted info to the report
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		
		extentSparkReporter.loadJSONConfig(new File("./ExtentReportConfig/Extent-Report-Config.json"));
	}
	
	@AfterSuite
	public void generateExtentReport() 
	{
		extentReports.flush();
	}

}

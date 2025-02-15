package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.ExtentTest;

public class LoginPage{
	
	private WebDriverWait wait;
	
	// Declaration
	@FindBy(id = "CompanyNameTextBox")
	private WebElement companyKey;
	
	@FindBy(id = "NextButton")
	private WebElement clickOnNEXTBtn;
	
	@FindBy(id = "LoginNameTextBox")
	private WebElement enterUN;
	
	@FindBy(id = "PasswordTextBox")
	private WebElement enterPW;
	
	@FindBy(id = "LoginButton")
	private WebElement clickOnLOGINBtn;

	
	// Initialization
	public LoginPage(WebDriver driver , WebDriverWait wait)
	{
		PageFactory.initElements(driver, this);
		this.wait = wait;
	}

	
	// Utilization
	public void login(String compKey, String un, String pw)
	{
		companyKey.sendKeys(compKey);
		//extentTest.info("Enter the " + compKey);
		clickOnNEXTBtn.click();
		//extentTest.info("Click on NEXT button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginNameTextBox")));
		enterUN.sendKeys(un);
		enterPW.sendKeys(pw);
		clickOnLOGINBtn.click();
	}
}

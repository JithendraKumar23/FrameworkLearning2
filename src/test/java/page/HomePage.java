package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HomePage {

	private WebDriverWait wait;
	
	// Declaration
	@FindBy(xpath = "//h1[text()='Resource Management']")
	private WebElement verifyLogin;
	
	@FindBy(xpath = "//span[text()='Projects']")
	private WebElement clickOnProjectsMenu;
	
	// Initialization
	public HomePage(WebDriver driver , WebDriverWait wait) 
	{
		PageFactory.initElements(driver, this);
		this.wait = wait;
	}
	
	// Utilization
	public boolean verifyLogin() 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(verifyLogin));
			Reporter.log("Login is pass", true);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Reporter.log("Login is fail", true);
			return false;
		}
	}
}

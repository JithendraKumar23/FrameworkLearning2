package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Declaration
	@FindBy(xpath = "//span[text()='Projects']")
	private WebElement clickOnProjectsMenu;

	
	// Initialization
	public MenuPage(WebDriver driver , WebDriverWait wait)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}
	
 	//Utilization
	public void clickOnProjectOption() throws InterruptedException
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(clickOnProjectsMenu).click().perform();
	}
}

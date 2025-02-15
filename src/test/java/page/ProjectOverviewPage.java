package page;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ProjectOverviewPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Declaration
	@FindBy(xpath = "//span[text()='Summary']")
	private WebElement summaryTab;
	
	@FindBy(xpath = "//button[@data-qe-id='ProjectEditButton_EditButton']")
	private WebElement clickOnEDITBtn;
	
	@FindBy(xpath = "//button[@data-qe-id='ProjectEditButton_DoneEditButton']")
	private WebElement clickOnDONEEDITingBtn;
	
	@FindBy(xpath = "//span[text()='Revenue Recognition']")
	private WebElement documents;
	
	@FindBy(xpath = "//h6[text()='Expenses']/../../../following-sibling::div/button[@aria-label='Edit']")
	private WebElement inspectTheEDITiconINExpenseTab;
	
	@FindBy(xpath = "//div[@class='MuiCardContent-root']//button[contains(@class , 'MuiButtonBase-root MuiButton-root')]")
	private WebElement clickOnFileWebAddressBtn;
	
	@FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-contained')])[2]")
	private WebElement clickOnChooseFileToUploadBtn;
	
	@FindBy(xpath = "//span[text()='Ok']")
	private WebElement clickOKBtn;
	
	// Initialization
	public ProjectOverviewPage(WebDriver driver, WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	public boolean veryfyUserLandedOnProjectSummarypage() 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(summaryTab));
			Reporter.log("User successfully landed on the Summary Page",true);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Reporter.log("User navigation on Summary Page is FAILED..!!!",true);
			return false;
		}
	}
	
	public void clickOnEditBtn() 
	{
		wait.until(ExpectedConditions.visibilityOf(clickOnEDITBtn));
		clickOnEDITBtn.click();
	}
	
	public void scrollPageTillDocumentisVisible() 
	{
		wait.until(ExpectedConditions.visibilityOf(clickOnDONEEDITingBtn));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", documents);
	}
	
	public void uploadFileFromLocal() throws InterruptedException, IOException 
	{
		wait.until(ExpectedConditions.visibilityOf(inspectTheEDITiconINExpenseTab));
		wait.until(ExpectedConditions.visibilityOf(clickOnFileWebAddressBtn));
		clickOnFileWebAddressBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(clickOnChooseFileToUploadBtn));
		clickOnChooseFileToUploadBtn.click();
		
		File file = new File("./uploadFileUsingAutoIT/fileupload.exe");
		String pathOftheFile = file.getAbsolutePath();
		Reporter.log(pathOftheFile,true);
		
		Runtime.getRuntime().exec(pathOftheFile);
		

	}
}

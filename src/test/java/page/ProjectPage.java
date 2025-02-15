package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import generic.Utilities;

public class ProjectPage{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Declaration
	@FindBy(xpath = "//span[text()='List']")
	private WebElement clickOnListTab;
	
	@FindBy(xpath = "//p[text()='124']")
	private WebElement selectProjectFromList;
	
	@FindBy(xpath = "//button[@aria-label='Add Project']")
	private WebElement clickOnADDPROJECTbutton;
	
	@FindBy(id = "name")
	private WebElement enterProjectName;
	
	@FindBy(id = "code")
	private WebElement enterProjectCode;
	
	@FindBy(xpath = "//input[@aria-label='Planned Start Date']")
	private WebElement clickOnProjectStartDateFiled;
	
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton' and @aria-label='Previous']")
	private WebElement clickOnLeftArrowiconToChangeTheMonthInTheCallender;
	
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton' and @aria-label='Next']")
	private WebElement clickOnRightArrowiconToChangeTheMonthInTheCallender;
	
	@FindBy(xpath = "(//p[text()='July 2024']/../../../..//p[text()='1'])[1]")
	private WebElement selectProjectStartDateOnCallender;
	
	@FindBy(xpath = "//input[@aria-label='Planned End Date']")
	private WebElement clickOnProjectEndDateFiled;
	
	@FindBy(xpath = "//p[text()='December 2024']/../../../..//p[text()='31']")
	private WebElement selectProjectEndDateOnCallender;
	
	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-alignCenter']")
	private WebElement getNonSelectedMonthTextFromCallender;
	
	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiAutocomplete-popupIndicator'])[1]")
	private WebElement openClientDropdownList;
	
	@FindBy(xpath = "//span[text()='AnalytiQ']")
	private WebElement selectAnalytiQClient;
	
	@FindBy(xpath = "//input[@aria-label='Program']")
	private WebElement clickOnProgramField;
	
	@FindBy(xpath = "//ul[@class='MuiAutocomplete-listbox']//button[@type='button']")
	private WebElement CreateNewProgramFromDropDownList;
	
	@FindBy(xpath = "//span[text()='Add Program']")
	private WebElement clickOnAddProgram;
	
	@FindBy(xpath = "//label[text()='Project Manager']/..//button[@class='MuiButtonBase-root MuiIconButton-root MuiAutocomplete-popupIndicator']")
	private WebElement openProjectManagerDropdownList;
	
	@FindBy(xpath = "//span[text()='Benson, Tammy (Project Manager)']")
	private WebElement selectTammyBensonAsAnPM;
	
	@FindBy(xpath = "//div[@class='MuiFormControl-root MuiFormControl-fullWidth']")
	private WebElement openAllowEntryWithOutTaskDropDown;
	
	@FindBy(xpath = "//li[text()='Billable']")
	private WebElement selectBillableOption;
	
	//@FindBy(xpath = "//button[@data-qe-id='AddProjectAdd']")
	//private WebElement clickOnCreateProjectBtn;
	
	@FindBy(xpath = "//span[text()='Add Project']")
	private WebElement ADDBUTTON;
	
	@FindBy(xpath = "//button//div[contains(@class , 'MuiCircularProgress-root')]")
	private WebElement spinner;
	
	
	// Initialization
	public ProjectPage(WebDriver driver, WebDriverWait wait)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}
	
	// Utilization
	public void clickOnListTab() 
	{
		clickOnListTab.click();
	}
	
	public void clickOnProjectNameInListPage() 
	{
		wait.until(ExpectedConditions.visibilityOf(selectProjectFromList));
		selectProjectFromList.click();
	}
	
	public void clickOnCreateProjectButton() 
	{
		clickOnADDPROJECTbutton.click();
	}
	
	public void selectProjectEndDateByClickingNextButton() throws InterruptedException 
	{
		for(int i = 1 ; i<=12 ; i++)
		{
			try 
			{
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOf(selectProjectEndDateOnCallender));
				selectProjectEndDateOnCallender.click();
				Reporter.log("Given Date is selected",true);
				break;
			}
			catch (Exception e)
			{
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOf(clickOnRightArrowiconToChangeTheMonthInTheCallender));
				Reporter.log("Given Date is NOT found in the displayed " + getNonSelectedMonthTextFromCallender.getText() +" Month, Clicking NEXT..",true);
				clickOnRightArrowiconToChangeTheMonthInTheCallender.click();
			}
		}
	}
	
	public void selectClientFromDropdown() 
	{
		wait.until(ExpectedConditions.invisibilityOf(selectProjectEndDateOnCallender));
		wait.until(ExpectedConditions.visibilityOf(openClientDropdownList));
		openClientDropdownList.click();
		selectAnalytiQClient.click();
	}
	
	public void createProgramFromDropdown(String env) throws InterruptedException 
	{
		clickOnProgramField.click();
		String pgmName = Utilities.getProperties(env, "PGMNAME");
		clickOnProgramField.sendKeys(pgmName);
		
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='MuiAutocomplete-listbox']//button[@type='button']")));
			CreateNewProgramFromDropDownList.click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		clickOnAddProgram.click();
	}
	
	public void selectProjectManager() 
	{
		wait.until(ExpectedConditions.invisibilityOf(spinner));
		wait.until(ExpectedConditions.visibilityOf(openProjectManagerDropdownList));
		openProjectManagerDropdownList.click();
		selectTammyBensonAsAnPM.click();
	}
	
	public void selectAllowEntryWithoutTask() 
	{
		wait.until(ExpectedConditions.visibilityOf(openAllowEntryWithOutTaskDropDown));
		openAllowEntryWithOutTaskDropDown.click();
		selectBillableOption.click();
	}
	
	
	public void clickOnADDPROJECTButton() throws InterruptedException
	{
//		WebElement test = driver.findElement(By.xpath("//button[@data-qe-id='AddProjectAdd']"));
//		WebElement pgm = driver.findElement(By.xpath("//label[text()='Program']"));
//		wait.until(ExpectedConditions.visibilityOf(spinner));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", test);
		
//		wait.until(ExpectedConditions.invisibilityOf(spinner));
		
		wait.until(ExpectedConditions.visibilityOf(ADDBUTTON));
		ADDBUTTON.click();
	}
	
	public void createProject(String env) throws InterruptedException
	{
		String projectName = Utilities.getProperties(env, "PROJECTNAME");
		enterProjectName.sendKeys(projectName);
		enterProjectCode.sendKeys("Code C1");
		
		clickOnProjectStartDateFiled.click();
		selectProjectStartDateOnCallender.click();
		
		clickOnProjectEndDateFiled.click();
		selectProjectEndDateByClickingNextButton();
		
		selectClientFromDropdown();
		createProgramFromDropdown(env);
		selectProjectManager();
		selectAllowEntryWithoutTask();
		clickOnADDPROJECTButton();
	}
	
	public void scroll() throws InterruptedException 
	{
		Thread.sleep(7000);
		JavascriptExecutor j=(JavascriptExecutor) driver;
		for(int i=1;i<=5;i++)
		{
			j.executeScript("window.scrollBy(0,500)");
			Thread.sleep(1000);
			Reporter.log("Scroll the page",true);
		}
	}
	
}

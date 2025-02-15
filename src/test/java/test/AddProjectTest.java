package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseTest;
import page.MenuPage;
import page.ProjectOverviewPage;
import page.ProjectPage;

public class AddProjectTest extends BaseTest{
	
	
	@Test(priority = 2 , testName = "CreateProject" , groups = {"Regression" , "Smoke"})
	public void testAddProject() throws InterruptedException
	{
		 MenuPage menuPage = new MenuPage(driver, wait);
		 menuPage.clickOnProjectOption();
		 
		 ProjectPage projectPage = new ProjectPage(driver, wait);
		 projectPage.clickOnListTab();
		 //projectPage.clickOnProjectNameInListPage();
		// projectPage.scroll();
		 
		 
		 projectPage.clickOnCreateProjectButton();
		 projectPage.createProject(ENV);

		ProjectOverviewPage overviewPage = new ProjectOverviewPage(driver, wait);
		boolean result = overviewPage.veryfyUserLandedOnProjectSummarypage();
		Assert.assertEquals(result, true);
		 
	}

}

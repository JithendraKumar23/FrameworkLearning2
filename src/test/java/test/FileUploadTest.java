package test;

import java.io.IOException;

import org.testng.annotations.Test;

import generic.BaseTest;
import page.ProjectOverviewPage;

public class FileUploadTest extends BaseTest {
	
	@Test(priority = 3 , enabled = true , testName = "FileUpload" , groups = {"Regression"})
	public void fileuploadTest() throws InterruptedException, IOException 
	{
		
		ProjectOverviewPage projectoverviewpage = new ProjectOverviewPage(driver, wait);
		projectoverviewpage.clickOnEditBtn();
		projectoverviewpage.scrollPageTillDocumentisVisible();
		projectoverviewpage.uploadFileFromLocal();
		
	}

}

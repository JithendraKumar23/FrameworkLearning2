package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseTest;
import page.HomePage;
import page.LoginPage;


public class ValidLoginTest extends BaseTest{

	//@Listeners(generic.Listener.class)

	@Test
	public void testValidLogin()
	{
//		String enterCompanyKeyFromExcel = Utilities.getExcelData(EXCELDATA, "validLogin", 1, 0);
//		String enterUnFromExcel = Utilities.getExcelData(EXCELDATA, "validLogin", 1, 1);
//		String enterPwFromExcel = Utilities.getExcelData(EXCELDATA, "validLogin", 1, 2);
		
		LoginPage loginPage = new LoginPage(driver, wait);
		//loginPage.login(enterCompanyKeyFromExcel, enterUnFromExcel, enterPwFromExcel);
		loginPage.login("ppmso2", "admin", "Replicon@123");
		
		HomePage homePage = new HomePage(driver, wait);
		boolean validateLogin = homePage.verifyLogin();
		Assert.assertEquals(validateLogin, true);
	}
}

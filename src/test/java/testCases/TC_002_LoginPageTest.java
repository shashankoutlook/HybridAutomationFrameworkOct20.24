package testCases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import com.opencart.listener.Utilities;

public class TC_002_LoginPageTest extends BaseClass {
	LoginPage loginpage;
	HomePage homepage;

	public TC_002_LoginPageTest(){
		super();
	}
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		loginpage=homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="supplyTestData")
	public void verifyLoginWithValidCredentials(String email,String password) {
		AccountPage accountPage = loginpage.login(email, password);
		accountPage.getDisplayStatusOfEditYourAccountInformationOption();
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
	}
	@DataProvider
	public Object[][] supplyTestData() {
		/*Object[][]data= {
				{"kalyanshaan@gmail.com","12345"},
				{"kalyanadmin@gmail.com","12345"},
				{"shashankadmin@gmail.com","12345"}
				};
		*/
		Object[][] data= Utilities.getTestDataFromExcel("Login");
				
		
				
		return data;
	}
	@Test(priority=2)
	public void veriifyLoginWithInValidCredentials() {
		AccountPage accountPage = loginpage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage), "Expected warning message not displayed");
				
	}
	
	
	@Test(priority=3)
	public void verifyInvalidEmailAddressValidPasswordCredentials() {
		AccountPage accountPage = loginpage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("ValidPassword"));
		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage), "Expected warning message not displayed");
				
		
	}
	@Test(priority=4)
	public void VerifyValidEmailAddressInvalidPassword() {
		AccountPage accountPage = loginpage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage), "Expected warning message not displayed");
				
		
	}
	
	@Test(priority=5)
	public void VerifyLoginWithoutProvidingCredentials() {
		loginpage.clickonLoginButton();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,\"alert-dismissible\")]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(actualWarningMessage), "Expected warning message not displayed");
				
		
	}
	
	
		
}

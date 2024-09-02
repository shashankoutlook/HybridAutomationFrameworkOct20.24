package testCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencart.listener.Utilities;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass  {
	
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	public TC_001_AccountRegistrationTest() {
		super();
	}
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		logger.info("*****Starting TC_001_AccountRegistrationTest***** ");
		HomePage homepage=new HomePage(driver);
		registerpage=homepage.navigateToRegisterPage();
	
		
	}
	@AfterMethod
	
	public void teardown() {
		driver.quit();
	}

		@Test(priority=1)
	public void verifyRegisterAnAccountWithMandatoryFields() {
			logger.info("*****Starting verifyRegisterAnAccountWithMandatoryFields except subscribe agree button ***** ");

			accountsuccesspage=registerpage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
			String actualsuccessheading=accountsuccesspage.retrieveAccountSuccessPageHeading();
			Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page not displayed");
		}	
		@Test(priority=2)
		public void verifyRegisteringAccountWithAllTheFields() {
			logger.info("*****Starting verifyRegisteringAccountWithAllTheFields and clicking subscribe button also***** ");

			accountsuccesspage=registerpage.registeWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
			String actualsuccessheading=accountsuccesspage.retrieveAccountSuccessPageHeading();
			Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page not displayed");
		}
		
		@Test(priority=3)
		public void verifyRegisteringAccountWithExistingEmailAddress() {
			logger.info("*****Starting verifyRegisteringAccountWithExistingEmailAddress and testing existing email address***** ");

			accountsuccesspage=registerpage.registeWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
			String actualWaringMessage=registerpage.retrieveduplicateEmailAddressWarning();
			Assert.assertTrue(actualWaringMessage.contains(dataProp.getProperty("duplicateEmailAddressWarning")),"warning message regarding duplicate email address not displayed");
		}
		
		@Test(priority=4)
		public void verifyRegisteringAccountWithoutFillingAnyDetails() {
			logger.info("*****Starting verifyRegisteringAccountWithoutFillingAnyDetails and Validating Expected Message***** ");

			registerpage.clickContinuefield();
			//Assert.assertTrue(registerpage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("EmailWarning"), dataProp.getProperty("TelephoneWarning"), dataProp.getProperty("PasswordWarning")));
			
			
			registerpage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("EmailWarning"), dataProp.getProperty("TelephoneWarning"), dataProp.getProperty("PasswordWarning"));
			logger.info("***** Finished TC_001_AccountRegistrationTest *****");

/*
			//String actualPolicyPrivacyWaring=registerpage.retrievePrivacyPolicyWarning();
			Assert.assertTrue(registerpage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Warning Message Not Displayed");
			
			//String actualfirstnameWarning=registerpage.retrieveFirstNameWarning();
			Assert.assertEquals(registerpage.retrieveFirstNameWarning(),dataProp.getProperty("firstNameWarning"),"First Name must be between 1 and 32 characters!");
			
			//String actualLastnameWarning=registerpage.retrieveLastNameWarning();
			Assert.assertEquals(registerpage.retrieveLastNameWarning(),dataProp.getProperty("lastNameWarning"),"Last Name must be between 1 and 32 characters!");
			
			//String actualEmalWarning=registerpage.retrieveEmailWarning();
			Assert.assertEquals(registerpage.retrieveEmailWarning(),dataProp.getProperty("EmailWarning"),"E-Mail Address does not appear to be valid!");
			
			//String actualTelePhoneWarning=registerpage.retrieveTelephoneWarning();
			Assert.assertEquals(registerpage.retrieveTelephoneWarning(),dataProp.getProperty("TelephoneWarning"),"Telephone must be between 3 and 32 characters!");
			
			//String actualPasswordWaring=registerpage.retrievePasswordWarning();
			Assert.assertEquals(registerpage.retrievePasswordWarning(),dataProp.getProperty("PasswordWarning"),"Password must be between 4 and 20 characters!");
	*/
		}
}

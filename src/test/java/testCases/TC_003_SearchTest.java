package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_003_SearchTest extends BaseClass {
	SearchPage searchpage;
	HomePage homepage;
	public TC_003_SearchTest(){
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);


	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		homepage=new HomePage(driver);
		homepage.enterProductIntoSearch(dataProp.getProperty("ValidProduct"));
		Assert.assertTrue(homepage.ClickOnSearchBoxOption().displayStatusOfHpProduct(),"Valid Not Product is displayed");
	}
	@Test(priority=2)
		public void VerifySearchWithInValidProduct() {
		homepage=new HomePage(driver);
		homepage.enterProductIntoSearch(dataProp.getProperty("invalidProduct"));
		homepage.ClickOnSearchBoxOption();
		searchpage=homepage.ClickOnSearchBoxOption();
		//Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No product is search results is not displayed");
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No product is search results is not displayed");

		}
		@Test(priority=3,dependsOnMethods={"VerifySearchWithInValidProduct","verifySearchWithValidProduct"})
		public void verifySearchWithoutAnyProduct() {
			HomePage homepage=new HomePage(driver);
			homepage.ClickOnSearchBoxOption();
			searchpage=homepage.ClickOnSearchBoxOption();
			//String actualSearchMessage=searchpage.retrieveNoProductMessageText();
			Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No product is search results is not displayed");
			
		}
}

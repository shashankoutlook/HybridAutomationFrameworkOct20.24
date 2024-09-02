package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchWarning;
	
	//Creating Constructor
		public LoginPage(WebDriver driver) {
			
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
	//Action method
		public void enterEmailAddress(String emailText) {
			emailAddressField.sendKeys(emailText);
		}
		
		public void enterPassword(String passwordText) {
			passwordField.sendKeys(passwordText);
		}
		
		public AccountPage clickonLoginButton() {
			loginButton.click();
			return new AccountPage(driver);
		}
		public AccountPage login(String emailText,String passwordText) {
			emailAddressField.sendKeys(emailText);
			passwordField.sendKeys(passwordText);
			loginButton.click();
			return new AccountPage(driver);
		}
		public String retrieveEmailPasswordNotMatchingWarningMessageText() {
			String warningText=emailPasswordNotMatchWarning.getText();
			return warningText;
		}
}

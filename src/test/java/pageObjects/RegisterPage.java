package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailaddressfield;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephonefield;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordfield;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement passwordConfirmfield;
	
	@FindBy(xpath="//input[@name='newsletter']")
	private WebElement clickSubscribefield;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyfield;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement clickOnContinueBtn;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')")
	private WebElement duplicateEmailAddressWarningn;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacypolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']//following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']//following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']//following-sibling::div")
	private WebElement EmailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']//following-sibling::div")
	private WebElement TelephoneWarningmsg;
	
	@FindBy(xpath="//input[@id='input-password']//following-sibling::div")
	private WebElement PasswordWarningmsg;
	
	
	//Creating Constructor
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Actions
	public String retrievePasswordWarning() {
		String passwordwarningText=PasswordWarningmsg.getText();
		return passwordwarningText;
	}
	public String retrieveTelephoneWarning() {
		String telephonewarningText=TelephoneWarningmsg.getText();
		return telephonewarningText;
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailWarning() {
		String EmailWarningText=EmailWarning.getText();
		return EmailWarningText;
	}
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacypolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveduplicateEmailAddressWarning() {
	String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
	return duplicateEmailWarningText;
	}
	
	public void enterFirstname(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	
	public void enterLastname(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailAddress) {
		emailaddressfield.sendKeys(emailAddress);
	}
	
	public void enterTelephonedetails(String telephoneText) {
		telephonefield.sendKeys(telephoneText);
	}
	
	public void enterPassworddetails(String passwordText) {
		passwordfield.sendKeys(passwordText);
	}
	public void enterPasswordconfirmdetails(String passwordconfirmText) {
		passwordConfirmfield.sendKeys(passwordconfirmText);
	}
	
	public void clicksubcribebutton() {
		clickSubscribefield.click();
	}
	
	public void clickprivacypolicyfield() {
		privacyPolicyfield.click();
	}
	
	public AccountSuccessPage clickContinuefield() {
		clickOnContinueBtn.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailAddress,String telephoneText,String passwordText,String passwordconfirmText  ) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailaddressfield.sendKeys(emailAddress);
		telephonefield.sendKeys(telephoneText);
		passwordfield.sendKeys(passwordText);
		passwordConfirmfield.sendKeys(passwordconfirmText);
		privacyPolicyfield.click();
		clickOnContinueBtn.click();
		return new AccountSuccessPage(driver);

	}
	public AccountSuccessPage registeWithAllFields(String firstNameText,String lastNameText,String emailAddress,String telephoneText,String passwordText,String passwordconfirmText  ) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailaddressfield.sendKeys(emailAddress);
		telephonefield.sendKeys(telephoneText);
		passwordfield.sendKeys(passwordText);
		passwordConfirmfield.sendKeys(passwordconfirmText);
		clickSubscribefield.click();
		privacyPolicyfield.click();
		clickOnContinueBtn.click();
		return new AccountSuccessPage(driver);

	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
		String actualprivacyPolicyWarningText=privacypolicyWarning.getText();
		boolean privacypolicyWarningStatus=actualprivacyPolicyWarningText.contains(expectedPasswordWarning);
		
		String actualfirstNameWarningText=firstNameWarning.getText();
		boolean firstNameWarningstatus=actualfirstNameWarningText.contains(expectedFirstNameWarning);
		
		String actuallastNameWarningText=lastNameWarning.getText();
		boolean lastNameWarningStatus=actuallastNameWarningText.contains(actuallastNameWarningText);
		
		String actualEmailWarningText=EmailWarning.getText();
		boolean EmailWarningStatus=actualEmailWarningText.contains(actualEmailWarningText);
		
		String actualTelephoneWarningText=TelephoneWarningmsg.getText();
		boolean TelephoneWarningmsg=actualTelephoneWarningText.contains(actuallastNameWarningText);
		
		String actualPasswordWarning=PasswordWarningmsg.getText();
		boolean PasswordWarningmsg=actualPasswordWarning.contains(actualPasswordWarning);
		
		return privacypolicyWarningStatus && firstNameWarningstatus && lastNameWarningStatus && EmailWarningStatus && TelephoneWarningmsg && PasswordWarningmsg;

	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText="iPhone")
	private WebElement validProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	
	//Creating Constructor
		public SearchPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
			}
		
		//Actions
		public boolean displayStatusOfHpProduct() {
			boolean displayStatus=validProduct.isDisplayed();
			return displayStatus;
		}
		
	public String retrieveNoProductMessageText() {
		String noProductMessageText=noProductMessage.getText();
		return noProductMessageText;
	}
}

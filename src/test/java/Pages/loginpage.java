package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverFactory.DriverFactory;

public class loginpage {
	homePage hp;
	private WebDriver driver;
	@FindBy(id="id_username")
	WebElement username;
	@FindBy(id="id_password")
	WebElement password;
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	public loginpage()
	{
		driver=DriverFactory.getDriver();
		PageFactory.initElements(driver,this);
		
	}
	
	public void login()
	{
		username.sendKeys("Test-229");
		password.sendKeys("Shivagami229.");
		loginBtn.click();
	}

}
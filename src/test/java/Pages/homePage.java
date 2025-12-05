package Pages;

import java.time.Duration;
//import java.lang.System.Logger;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;
import Utils.ConfigReader;

public class homePage {

	// private static final Logger logger =
	// LogManager.getLogger(homePage.class);
	private WebDriver driver;
	WebDriverWait wait;
	// @FindBy(xpath="//div[2][@class='navbar-nav']/ul/a[1]")
	@FindBy(xpath = "//a[contains(text(),' Register ')]") // register link
	WebElement registerLink;
	@FindBy(xpath = "//input[@value='Register']") // verify registerpage
	WebElement register;
	@FindBy(xpath = "//a[contains(text(),'Sign in')]") // signin link
	WebElement signinLink;
	@FindBy(xpath = "//input[@value='Login']") // verify signin page with login
												// button text
	WebElement signin;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	@FindBy(xpath = "//a[contains(@class,'nav-link dropdown-toggle')]")
	WebElement dropdownBtn;
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu show')]/a")
	List<WebElement> dropdownTopics;
	// @FindBy(xpath="//div[contains(@class,'alert alert-primary')]")
	@FindBy(xpath = "//div[contains(@class,'alert')]")
	WebElement errorMsg;
	// @FindBy(xpath="//div[contains(@class,'card-body d-flex
	// flex-column')]/h5")
	@FindBy(xpath = "//div[@class='col']/div/div/h5")
	List<WebElement> getstartedTopic;
	// @FindBy(xpath="//div[contains(@class,'card-body d-flex flex-column')]/a")
	@FindBy(xpath = "//div[@class='col']/div/div/a")
	List<WebElement> getstartedBtn;

	@FindBy(xpath = "//h4[contains(@class,'bg-secondary text-white')]")
	WebElement intropageHeading;

	// @FindBy(xpath="//h4[contains(@class,'bg-secondary text-white')]")
	public homePage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	// select register link or signin link
	public void clickLink(String linkName) {
		if (linkName.equalsIgnoreCase("Registers")) {
			registerLink.click();
		} else if (linkName.equalsIgnoreCase("Sign in")) {
			signinLink.click();
		}
	}

	public String fetchregisterTitle() {

		String registertitle = register.getAttribute("value");
		System.out.print("Register button text= " + registertitle);
		return registertitle;

	}

	public String fetchsigninpageTitle() {
		String signintitle = signin.getAttribute("value");
		System.out.print("Login button text: " + signintitle);
		return signintitle;
	}

	public void selectTopicFromDropdown(String topic) {

		dropdownBtn.click();
		for (WebElement options : dropdownTopics) {
			String optionsTxt = options.getText();

			if (optionsTxt.equalsIgnoreCase(topic)) {
				options.click();
				System.out.print("options are: " + optionsTxt);
				break;
			}
		}

	}

	public String fetchErrorMsg() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		System.out.print("Error Message: " + errorMsg.getText());
		return errorMsg.getText();
	}

	public void getStartedclick(String topic) {

		for (int i = 0; i < getstartedTopic.size(); i++) { // looping since it
															// has multiple
															// getstarted button

			String topicsTxt = getstartedTopic.get(i).getText();// getting exact
																// location text
			System.out.println("Index: " + i + " | Topic: " + topicsTxt);
			if (topicsTxt.equalsIgnoreCase(topic)) {
				WebElement btn = getstartedBtn.get(i);

				btn.click();

				System.out.println("topics clicked are: " + topicsTxt);
				break;
			}
		}

	}

	public String fetchIntroductionPageTitle() {
		System.out.print("Page Title: " + intropageHeading.getText());
		return intropageHeading.getText();
	}

	public void homeApplication() {
		String homepageurl = ConfigReader.getProperty("homeurl");
		driver.get(homepageurl);
	}

	public void gotosignin() {
		signinLink.click();
	}
	/*
	 * public void login() { username.sendKeys("Test-229");
	 * password.sendKeys("Shivagami229."); loginBtn.click(); }
	 */
}
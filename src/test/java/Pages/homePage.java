package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;
import Utils.ConfigReader;

public class homePage {

	private WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//button[@class='btn']")
	private WebElement getStartedButton;

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
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/a")
	List<WebElement> dropdownTopics;

	@FindBy(xpath = "//a[contains(@class,'nav-link dropdown-toggle')]")
	WebElement dropdownBtn;

	@FindBy(xpath = "//div[contains(@class,'alert')]")
	WebElement errorMsg;

	@FindBy(xpath = "//div[@class='col']/div/div/h5")
	List<WebElement> getstartedTopic;

	@FindBy(xpath = "//div[@class='col']/div/div/a")
	List<WebElement> getstartedBtn;

	@FindBy(xpath = "//h4[contains(@class,'bg-secondary text-white')]")
	WebElement intropageHeading;

	public homePage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void clickGetStarted() {

		getStartedButton.click();
	}

	public String fetchTitle() {
		return driver.getTitle();

	}

	public void launchApplication() {
		String url = ConfigReader.getProperty("url");
		driver.get(url);
	}

	// select register link or signin link
	public void clickLink(String linkName) {
		if (linkName.equalsIgnoreCase("Register")) {
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

	public void selectTopicFromDropdown(String topicName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		int retries = 3;
		while (retries > 0) {
			try {

				WebElement freshDropdownBtn = wait.until(
						ExpectedConditions.elementToBeClickable(dropdownBtn));
				freshDropdownBtn.click();

				// Re-fetch dropdown options each time to avoid
				// StaleElementReference Exception
				List<WebElement> freshOptions = wait.until(ExpectedConditions
						.visibilityOfAllElements(dropdownTopics));

				for (WebElement option : freshOptions) {
					if (option.getText().trim().equalsIgnoreCase(topicName)) {
						wait.until(
								ExpectedConditions.elementToBeClickable(option))
								.click();
						return;
					}
				}

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale detected → retrying...");
				// ReInitialize page factory to refresh all elements
				//
				PageFactory.initElements(driver, this);// recreates ALL @FindBy
														// elements fresh from
														// the current DOM.
				retries--;
			}
		}

		throw new RuntimeException("Dropdown option NOT found: " + topicName);
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

}
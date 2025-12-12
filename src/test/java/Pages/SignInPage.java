
package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;
import Utils.ExcelUtil;

public class SignInPage {

	private static final Logger logger = LogManager.getLogger(SignInPage.class);
	private WebDriver driver;
	@FindBy(xpath = "//a[text()='Sign in']")
	WebElement signInLink;

	@FindBy(xpath = "//a[@href='/register']")
	WebElement register1Link;

	@FindBy(xpath = "//a[text()='Register!']")
	WebElement register2Link;

	@FindBy(id = ("id_username"))
	WebElement UsernameInputbox;

	@FindBy(id = ("id_password"))
	WebElement PasswordInputbox;

	@FindBy(xpath = ("//input[@value='Login']"))
	WebElement LoginButton;

	@FindBy(xpath = ("//div[@role='alert']"))
	WebElement LoginAlert;

	@FindBy(xpath = ("//div[@class='col-sm']"))
	WebElement AlertWindow;
	@FindBy(linkText = ("Sign out"))
	WebElement Signoutlink;

	Map<String, WebElement> registerLink = new HashMap<>();

	public SignInPage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void clickOnSignIn() {

		logger.info("Click On SignIn Link");
		signInLink.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("login"));

	}

	public String getPageTitle() {
		logger.info("Verify Sign-IN Page Title");
		return driver.getTitle();
	}

	public void clickLink(String linkName) {
		logger.info("Click on " + linkName);

		if (linkName.equalsIgnoreCase("register")) {
			register1Link.click();

		} else if (linkName.equalsIgnoreCase("register!"))
			register2Link.click();
	}

	public void Login(String username, String password) {
		logger.info("Login for user: " + username);
		UsernameInputbox.sendKeys(username);
		PasswordInputbox.sendKeys(password);
		LoginButton.click();

	}

	public void userLogin() throws IOException {

		logger.info("Login");
		List<Map<String, String>> userdata = ExcelUtil.getTestData("Account");
		UsernameInputbox.sendKeys(userdata.get(0).get("Username"));
		PasswordInputbox.sendKeys(userdata.get(0).get("Password"));
		LoginButton.click();

	}

	public String verifySuccesfulLogin() {

		logger.info("Verify Login ");
		return LoginAlert.getText();
	}

	public String verifySuccesfulLogintc(String testcase) {

		logger.info("Verify Login ");
		String validationMessage = null;

		if (testcase.contains("empty")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			validationMessage = (String) js.executeScript(
					"return arguments[0].validationMessage;", UsernameInputbox);
			if (validationMessage.isBlank()) {
				validationMessage = (String) js.executeScript(
						"return arguments[0].validationMessage;",
						PasswordInputbox);
			}
		} else {
			validationMessage = LoginAlert.getText();
		}

		return validationMessage;

	}

	public void Signout() {
		Signoutlink.click();
	}

}

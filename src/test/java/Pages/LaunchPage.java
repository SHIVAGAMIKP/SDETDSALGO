package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverFactory.DriverFactory;
import Utils.ConfigReader;

public class LaunchPage {
	private static final Logger logger = LogManager.getLogger(LaunchPage.class);

	private WebDriver driver;
	@FindBy(xpath = "//button[@class='btn']")
	private WebElement getStartedButton;

	public LaunchPage() {
		logger.info("Loading the DSAlgo portal....");
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}

	public void clickGetStarted() {
		logger.info("clicking on GetStarted button now");
		getStartedButton.click();
	}

	public String fetchTitle() {
		return driver.getTitle();

	}

	public void launchApplication() {
		String url = ConfigReader.getProperty("url");
		driver.get(url);
	}
}

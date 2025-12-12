package Pages;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;

public class QueuePage {

	private static final Logger logger = LogManager.getLogger(QueuePage.class);
	private WebDriver driver;

	@FindBy(xpath = "//ul//a[@class='list-group-item']")
	List<WebElement> qlinkCount;
	@FindBy(xpath = "//a[@href='queue']")
	WebElement qStartButton;
	@FindBy(xpath = "//div/strong/p")
	WebElement qPageLinkTitle;
	@FindBy(xpath = "//div[@class='CodeMirror-scroll']")
	WebElement codeSpace1;
	@FindBy(xpath = "//button[text()='Run']")
	WebElement runButton;
	@FindBy(id = "output")
	WebElement Output;
	@FindBy(linkText = "Try here>>>")
	WebElement tryHereLink;

	public QueuePage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}

	public void goToQueuePAge() {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block: 'center'});",
				qStartButton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(qStartButton));
		wait.until(ExpectedConditions.elementToBeClickable(qStartButton));

		qStartButton.click();

	}

	public boolean GetLinkCount(List<String> linknames) {

		logger.info("Verifying count and names of links on Queue page");
		List<String> qLinkName = new LinkedList<String>();
		if (qlinkCount.size() == 4) {

			for (WebElement qlink : qlinkCount) {
				qLinkName.add(qlink.getText());
			}

		} else

		{
			logger.info("Verifying number links on Queue page: Fail ");
			return false;
		}
		logger.info("Verifying Names of links on Queue page: "
				+ qLinkName.equals(linknames));
		return (qLinkName.equals(linknames));

	}

	public void clickonQLinks(String QueuePageLinks) {

		logger.info("Click on " + QueuePageLinks);
		for (WebElement qlink : qlinkCount) {
			System.out.println(qlink.getText());
			if (qlink.getText().trim()
					.equalsIgnoreCase(QueuePageLinks.strip())) {
				qlink.click();

				break;

			}
		}

	}

	public boolean verifyQlinkPage(String QueuePageLinks) {

		logger.info("Verify title of Queue Page Links");
		qPageLinkTitle.getText();

		if (qPageLinkTitle.getText().equalsIgnoreCase(QueuePageLinks.trim())) {
			return true;
		}
		logger.info("Verify title of Queue Page Links: Fail");
		return false;

	}

	public void TryHere(String QueuePageLinks) {

		logger.info("Click on try here :" + QueuePageLinks);
		tryHereLink.click();

	}

	public boolean VerifyAssementPage() {

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			logger.info("Tryhere Link - Assement page displayed");
			return true;
		}
		logger.error("Tryhere Link - Assement page Fail");
		return false;

	}

	public void EnterCode(String code) throws InterruptedException {

		logger.info("Enter code:" + code);
		Actions actions = new Actions(driver);
		actions.click(codeSpace1).build().perform();
		actions.sendKeys(codeSpace1, code).perform();
		runButton.click();
		logger.info("Click on Run to execute code");

	}

	public boolean getOutput(String output) {

		logger.info("Validate code  output");

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			return (alertMsg.equalsIgnoreCase(output.trim()));
		} catch (NoAlertPresentException e) {
			return (Output.getText().equalsIgnoreCase(output.trim()));
		}

	}

}

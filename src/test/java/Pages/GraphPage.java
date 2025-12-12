package Pages;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

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

public class GraphPage {

	@FindBy(xpath = "//a[@href='graph']")
	WebElement graphStartButton;

	@FindBy(xpath = "//ul//a[@class='list-group-item']")
	List<WebElement> graphPageLinkCount;

	@FindBy(xpath = "//div/strong/p")
	WebElement graphPageLinkTitle;

	@FindBy(xpath = "//div[@class='CodeMirror-scroll']")
	WebElement codeSpace1;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement runButton;

	@FindBy(id = "output")
	WebElement Output;

	@FindBy(linkText = "Try here>>>")
	WebElement tryHereLink;

	private static final Logger logger = LogManager.getLogger(GraphPage.class);
	private WebDriver driver;

	public GraphPage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}

	public void goToGraphPAge() {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block: 'center'});",
				graphStartButton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(graphStartButton));
		wait.until(ExpectedConditions.elementToBeClickable(graphStartButton));
		graphStartButton.click();

	}

	public boolean GetgraphLinkCount(List<String> linknames) {

		logger.info("Verifying count and names of links on Graph page");
		List<String> graphLinkName = new LinkedList<String>();
		if (graphPageLinkCount.size() == 2) {

			for (WebElement glink : graphPageLinkCount) {
				graphLinkName.add(glink.getText());
			}

		} else

		{
			logger.error("Verifying number of links on Graph page: Fail ");
			return false;
		}
		logger.info("Verifying names of links on Graph page"
				+ graphLinkName.equals(linknames));
		return (graphLinkName.equals(linknames));

	}

	public void clickonGraphLinks(String GraphPageLinks) {

		logger.info("Click on links in Graph Page: " + GraphPageLinks);

		for (WebElement glink : graphPageLinkCount) {
			if (glink.getText().strip()
					.equalsIgnoreCase(GraphPageLinks.trim())) {
				glink.click();
				break;

			}
		}

	}

	public boolean verifyGraphlinkPage(String GraphPageLinks) {

		logger.info("Verify page title of links on Graph Page");
		graphPageLinkTitle.getText();

		if (graphPageLinkTitle.getText().equalsIgnoreCase(GraphPageLinks)) {
			return true;
		}
		logger.error("Verify page title of links on Graph Page: Fail");
		return false;

	}

	public void TryHere(String GraphPageLinks) {

		logger.info("Click on try here:" + GraphPageLinks);
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

}

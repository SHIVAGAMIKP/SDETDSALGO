package Pages;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	public String goToGraphPAge() {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block: 'center'});",
				graphStartButton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(graphStartButton));
		wait.until(ExpectedConditions.elementToBeClickable(graphStartButton));
		graphStartButton.click();
		return driver.getTitle();

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
			logger.info("Verifying number of links on Graph page: Fail ");
			return false;
		}
		logger.info("Verifying names of links on Graph page"
				+ graphLinkName.equals(linknames));
		return (graphLinkName.equals(linknames));

	}

	public boolean clickonGraphLinks(String GraphPageLinks) {

		logger.info("Click on links in Graph Page");

		for (WebElement glink : graphPageLinkCount) {

			System.out.println(glink.getText());

			if (glink.getText().strip()
					.equalsIgnoreCase(GraphPageLinks.trim())) {

				glink.click();
				return true;
			}
		}
		logger.info("Click " + GraphPageLinks + " Fail");
		return false;

	}

	public boolean verifyGraphlinkPage(String GraphPageLinks) {

		logger.info("Verify page title of links on Graph Page");
		graphPageLinkTitle.getText();

		if (graphPageLinkTitle.getText().equalsIgnoreCase(GraphPageLinks)) {
			return true;
		}
		logger.info("Verify page title of links on Graph Page: Fail");
		return false;

	}

	public boolean TryHere(String GraphPageLinks) {

		logger.info("Click on try here:" + GraphPageLinks);
		tryHereLink.click();

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			return true;
		}
		logger.info("Click on try here :" + GraphPageLinks + "Fail");
		return false;

	}

	public void EnterCode(String code) {

		logger.info("Enter the code : " + code);
		Actions actions = new Actions(driver);
		actions.click(codeSpace1).build().perform();
		actions.sendKeys(codeSpace1, code).perform();
		logger.info("Click on Run to execute code");
		runButton.click();

	}

	public boolean getOutput(String output) {

		logger.info("Expected code output is :" + output);
		logger.info("Actual code output is :" + Output.getText());
		return (Output.getText().equalsIgnoreCase(output));

	}

}

package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;

public class ArraysPage {

	private WebDriver driver;
	@FindBy(xpath = "//div[2][@class='col']/div[1]/div/a")
	WebElement arraysGetStartedBtn;
	@FindBy(xpath = "//h4[contains(@class,'bg-secondary text-white')]")
	WebElement fetchpageTitle;
	@FindBy(xpath = "//a[contains(@class,'list-group-item')]")
	List<WebElement> arraysubpageLinks;
	@FindBy(xpath = "//div[@class='col-sm']/strong//p[contains(@class,'bg-secondary text-white')]")
	WebElement arraysubpageTitle;
	@FindBy(xpath = "//a[contains(@class,'btn btn-info')]")
	WebElement tryhereBtn;
	@FindBy(xpath = "//div[contains(@class,'CodeMirror cm-s-default')]")
	WebElement texteditor;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(id = "output")
	WebElement consoleOutput;
	String expectedMessage;
	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	WebElement practiceQueLink;
	@FindBy(xpath = "//a[contains(@class,'list-group-item')]")
	List<WebElement> practiceSubTopics;
	@FindBy(xpath = "//div[@class='question']/h2")
	WebElement practicesubTopicsTitle;

	public ArraysPage() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}
	public void arraysgetstartedClick() {
		arraysGetStartedBtn.click();
	}
	public String fetchArrayspageTitle() {
		System.out.println(fetchpageTitle.getText());
		return fetchpageTitle.getText();
	}
	public void topicsCovered(String topiccovered) {
		for (int i = 0; i < arraysubpageLinks.size(); i++) { // looping since it
																// has multiple
																// getstarted
																// button

			String topicsTxt = arraysubpageLinks.get(i).getText();// getting
																	// exact
																	// location
																	// text
			System.out.println("Index: " + i + " | Topic: " + topicsTxt);
			if (topicsTxt.equalsIgnoreCase(topiccovered)) {
				WebElement topiclink = arraysubpageLinks.get(i);

				topiclink.click();

				System.out.println("topics clicked are: " + topicsTxt);
				break;
			}
		}
	}
	public String fetchtitlepage() {
		String pagetitle = arraysubpageTitle.getText();
		// System.out.println(arraysubpageTitle.getText());
		return pagetitle;
	}
	public void tryhere() {
		tryhereBtn.click();
	}
	public boolean textEditorIsDisplayed() {
		return texteditor.isDisplayed();
	}

	public void runPythonCode(String code) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Clear CodeMirror completely
		js.executeScript("arguments[0].CodeMirror.setValue('')", texteditor);

		// Set new cleaned code
		js.executeScript("arguments[0].CodeMirror.setValue(arguments[1])",
				texteditor, code);

		runBtn.click();

	}

	public String getResult() {

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			System.out.println(alertMsg);
			return (alertMsg);
		} catch (NoAlertPresentException e) {
			return consoleOutput.getText().trim();
		}

	}

	public void practicequeLink() {
		practiceQueLink.click();
	}

	public String getPageTitle() {
		System.out.println("Tile: " + driver.getTitle());
		return driver.getTitle();
	}

	public void practicesubTopiclink(String practicequeList) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		for (WebElement topic : practiceSubTopics) {
			String topicText = topic.getText().trim();
			System.out.println("Found Topic: " + topicText);

			if (topicText.equalsIgnoreCase(practicequeList)) {
				wait.until(ExpectedConditions.elementToBeClickable(topic));
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView(true);",
				// topic);
				topic.click();
				System.out.println("Clicked Topic: " + topicText);
				return;

			}
		}

	}

	public String subtopicTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement titleElement = wait
				.until(ExpectedConditions.visibilityOf(practicesubTopicsTitle));
		String titleText = titleElement.getText();
		System.out.print("Title " + titleText);
		return titleText;
	}

}

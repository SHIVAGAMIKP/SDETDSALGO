package Pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverFactory.DriverFactory;
import Utils.ConfigReader;

public class TreePage {
	private static final Logger logger = LogManager.getLogger(TreePage.class);
	private WebDriver driver;
	@FindBy(linkText = "Overview of Trees")
	private WebElement OverviewOfTreesLink;
	@FindBy(linkText = "Terminologies")
	private WebElement TerminologiesLink;
	@FindBy(linkText = "Types of Trees")
	private WebElement TypesOfTreesLink;
	@FindBy(linkText = "Tree Traversals")
	private WebElement TreeTraversalsLink;
	@FindBy(linkText = "Binary Trees")
	private WebElement BinaryTreesLink;
	@FindBy(linkText = "Types of Binary Trees")
	private WebElement TypesOfBinaryTreesLink;
	@FindBy(linkText = "Implementation in Python")
	private WebElement ImplementationInPythonLink;
	@FindBy(linkText = "Binary Tree Traversals")
	private WebElement BinaryTreeTraversalsLink;
	@FindBy(linkText = "Implementation of Binary Trees")
	private WebElement ImplementationOfBinaryTreesLink;
	@FindBy(linkText = "Applications of Binary trees")
	private WebElement ApplicationsOfBinaryTreesLink;
	@FindBy(linkText = "Binary Search Trees")
	private WebElement BinarySearchTreesLink;
	@FindBy(linkText = "Implementation Of BST")
	private WebElement ImplementationOfBSTLink;
	@FindBy(xpath = "//a[normalize-space()='Try here>>>']")
	private WebElement tryHereButton;
	@FindBy(xpath = "//button[normalize-space()='Run']")
	private WebElement runButton;
	@FindBy(xpath = "//pre[@id='output']")
	private WebElement outputMessage;

	public TreePage() {
		logger.info("TreePage constructor....");
		driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void clickOnLink(String linkText) {
		switch (linkText) {
		case "Overview of Trees":
			OverviewOfTreesLink.click();
			break;
		case "Terminologies":
			TerminologiesLink.click();
			break;
		case "Types of Trees":
			TypesOfTreesLink.click();
			break;
		case "Tree Traversals":
			TreeTraversalsLink.click();
			break;
		case "Binary Trees":
			BinaryTreesLink.click();
			break;
		case "Types of Binary Trees":
			TypesOfBinaryTreesLink.click();
			break;
		case "Implementation in Python":
			ImplementationInPythonLink.click();
			break;
		case "Binary Tree Traversals":
			BinaryTreeTraversalsLink.click();
			break;
		case "Implementation of Binary Trees":
			ImplementationOfBinaryTreesLink.click();
			break;
		case "Applications of Binary trees":
			ApplicationsOfBinaryTreesLink.click();
			break;
		case "Binary Search Trees":
			BinarySearchTreesLink.click();
			break;
		case "Implementation Of BST":
			ImplementationOfBSTLink.click();
			break;
		}
		logger.info(driver.getTitle());

	}

	public void clickOnTryHere() {
		logger.info("clicking on try here button");
		tryHereButton.click();
	}

	public String fetchTitle() {
		return driver.getTitle();
	}

	public void successfulLoginAndNavigationToTree() {
		String url = ConfigReader.getProperty("url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[@class='btn']")).click();
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		driver.findElement(By.xpath("//input[@id='id_username']")).sendKeys("Test-229");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Shivagami229.");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.findElement(By.xpath("//a[@href='tree']")).click();
	}

	public String enterPythonCodeAndClickRun(String valid) {
		logger.info("entering text in editor ");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(valid.equalsIgnoreCase("y")) {
		js.executeScript("editor.setValue('print(\"Valid python code\")');");
		logger.info("Entered valid code");
		}else if(valid.equalsIgnoreCase("n")) {
			js.executeScript("editor.setValue('some non python text');");
			logger.info("Entered invalid code");
		}
		logger.info("entered code in editor");
		runButton.click();
		logger.info("clicked on run");
		String result=runCodeAndGetResult(driver);
		logger.info(outputMessage.getText());
		return result;
	}

	public String runCodeAndGetResult(WebDriver driver) {

		String result = "";
		try {
			Alert alert = driver.switchTo().alert();
			result = alert.getText();
			alert.accept(); 
		} catch (NoAlertPresentException e) {
			result = outputMessage.getText();
		}

		return result;
	}

}

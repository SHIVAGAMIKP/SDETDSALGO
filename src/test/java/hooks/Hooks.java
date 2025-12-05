package hooks;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {

	@Before(order = 1)
	public void launchBrowser() {
		DriverFactory.initDriver();
	}

	@After(order = 1)
	public void quitBrowser(Scenario scenario) {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

	@After(order = 2)
	public void takeScreenhot(Scenario scenario) {

		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
					.getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Failed " + scenario.getName(),
					new ByteArrayInputStream(screenshot));// allure

			scenario.attach(screenshot, "image/png",
					"Failed " + scenario.getName());// extent report
		}
	}
}
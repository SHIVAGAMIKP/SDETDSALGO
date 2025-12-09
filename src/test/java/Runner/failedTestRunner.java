package Runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import DriverFactory.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"@target/rerun.txt"}, glue = {"StepDefinitions",
		"hooks"}, plugin = {"pretty", "html:target/cucumber-report.html",
				"json:target/cucumber.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/rerun.txt"}, tags = "not @QQueueExcelDPDD and not @SiginDPDataDriven and not @SiginExcel-ScenarioDataDriven")

public class failedTestRunner extends AbstractTestNGCucumberTests {

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void beforeClass(@Optional("chrome") String browser) {
		if (browser != null && !browser.equals("param-val-not-found")) {
			System.out.println(browser);
			DriverFactory.setupBrowser(browser);
		}
	}

}

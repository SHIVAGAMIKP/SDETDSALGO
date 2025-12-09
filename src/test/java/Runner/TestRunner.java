package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"}, glue = {
		"StepDefinitions", "hooks"}, plugin = {"pretty",
				"html:target/cucumber-report.html", "json:target/cucumber.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/rerun.txt"}, tags = "not @QQueueExcelDPDD and not @SiginDPDataDriven")

public class TestRunner extends AbstractTestNGCucumberTests {

}

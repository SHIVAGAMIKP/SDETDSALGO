package Runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import DriverFactory.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features/home.feature","src/test/resources/features/arrays.feature"}, glue = {
		"StepDefinitions","hooks" }, plugin = { "pretty", "html:target/cucumber-report.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
//,tags="@DD"

public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void beforeClass(@Optional("chrome") String browser) {
		if (browser != null && !browser.equals("param-val-not-found")) {
			System.out.println(browser);
			DriverFactory.setupBrowser(browser);
		}
	}

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}

//"src/test/resources/features/.feature"



// "not @SiginDPExcel-DPDataDrive and not @QQueueExcelDPDD "
// + "and not @SiginDPDataDriven and not @Queue50:50DD and not @Graph50:50DD")
// tags = "not @SiginDPExcel-DPDataDrive and not @QQueueExcelDPDD"

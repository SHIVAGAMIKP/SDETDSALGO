
package Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import DriverFactory.DriverFactory;
import Utils.ExcelUtil;
import Utils.Savedata;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/features/Sign-In.feature", glue = {
		"StepDefinitions", "hooks"}, plugin = {"pretty",
				"html:target/cucumber-report.html", "json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, tags = "@SiginDataProvider")

public class SignInDatadrivenTestRunner {

	private TestNGCucumberRunner runner;

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUpClass(@Optional("chrome") String browser) {

		System.out.println("Running on browser = " + browser);

		DriverFactory.setupBrowser(browser);

		runner = new TestNGCucumberRunner(this.getClass());
	}

	@DataProvider(name = "RunnerData", parallel = false)
	public Object[][] mergedData() throws Exception {

		Object[][] LogintestData = new ExcelUtil().getData("Login");

		Object[][] testscenarios = runner.provideScenarios();

		List<Object[]> scenariodataList = new ArrayList<>();

		for (Object[] dataRow : LogintestData) {
			for (Object[] scenarioRow : testscenarios) {

				scenariodataList.add(new Object[]{dataRow[0], scenarioRow[0],
						scenarioRow[1], DriverFactory.getBrowser()});

			}
		}

		return scenariodataList.toArray(new Object[0][]);
	}

	@Test(dataProvider = "RunnerData")
	public void runScenario(Map<String, String> data, PickleWrapper scenario,
			FeatureWrapper feature, String Browser) {

		Savedata.setData(data);
		DriverFactory.setupBrowser(Browser);
		runner.runScenario(scenario.getPickle());

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		runner.finish();
	}
}

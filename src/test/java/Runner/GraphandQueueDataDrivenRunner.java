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

@CucumberOptions(features = {"src/test/resources/features/Graph.feature",
		"src/test/resources/features/Queue.feature"}, glue = {"StepDefinitions",
				"hooks"}, plugin = {"pretty",
						"html:target/cucumber-report.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, tags = "@Graph50:50DD or @Queue50:50DD")

public class GraphandQueueDataDrivenRunner {

	private TestNGCucumberRunner runner;

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUpClass(@Optional("chrome") String browser) {

		System.out.println("Running on browser = " + browser);

		DriverFactory.setupBrowser(browser);

		runner = new TestNGCucumberRunner(this.getClass());
	}

	@DataProvider(name = "MergedDP", parallel = false)
	public Object[][] mergedData() throws Exception {

		// Load excel data
		Object[][] excelData = new ExcelUtil().getData("PythonCode");

		// Load all cucumber scenarios
		Object[][] scenarios = runner.provideScenarios();

		List<Object[]> mergedList = new ArrayList<>();

		for (Object[] excelRow : excelData) {
			for (Object[] scenarioRow : scenarios) {

				mergedList.add(new Object[]{excelRow[0], // Map<String,String>
															// test data
						scenarioRow[0], // PickleWrapper
						scenarioRow[1], // FeatureWrapper
						DriverFactory.getBrowser()});
				System.out.println("merged list is" + mergedList.getLast());
				System.out.println(
						"Driver factory is" + DriverFactory.getBrowser());
			}
		}

		return mergedList.toArray(new Object[0][]);
	}

	@Test(dataProvider = "MergedDP")
	public void runScenario(Map<String, String> data, PickleWrapper pickle,
			FeatureWrapper feature, String Browser) {

		Savedata.setexecutionType("DD");
		Savedata.setData(data);
		DriverFactory.setupBrowser(Browser);

		runner.runScenario(pickle.getPickle());

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		runner.finish();
	}
}

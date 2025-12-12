package StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import Pages.HomePage;
import Pages.QueuePage;
import Pages.SignInPage;
import Utils.ExcelUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QueueStepDef {

	private String output;

	QueuePage queue = new QueuePage();
	SignInPage signIn = new SignInPage();
	HomePage homePage = new HomePage();

	private String scenario;

	@Before(order = 2)
	public void GetScenario(Scenario sc) {

		scenario = sc.getName();

	}

	@Given("User is in Queue Page.")
	public void user_is_in_queue_page()
			throws InterruptedException, IOException {

		homePage.launchApplication();
		homePage.clickGetStarted();
		signIn.clickOnSignIn();
		signIn.userLogin();
		queue.goToQueuePAge();

	}

	@Then("Verify the count and names of the links.")
	public void verify_the_count_and_names_of_the_links(DataTable dataTable) {

		List<String> linknameslist = dataTable.asList();
		queue.GetLinkCount(linknameslist);
		Assert.assertTrue(queue.GetLinkCount(linknameslist));

	}

	@When("User clicks on {string} in Queue Page.")
	public void user_clicks_on_in_queue_page(String QueuePageLinks) {
		queue.clickonQLinks(QueuePageLinks);
	}

	@Then("Verify the {string} is displayed.")
	public void verify_the_expected_page_is_displayed(String QueuePageLinks) {
		Assert.assertTrue(queue.verifyQlinkPage(QueuePageLinks));
	}

	@Given("User is in {string} page of Queue module.")
	public void user_is_in_page_of_Queue_module(String QueuePageLinks) {
		queue.clickonQLinks(QueuePageLinks);

	}

	@When("User clicks on TryHere link in {string} page of Queue module")
	public void user_clicks_on_try_here_link_in_page_of_Queue_module(
			String QueuePageLinks) {
		queue.TryHere(QueuePageLinks);
	}

	@Then("Verfiy the Editor Page is displayed.")
	public void verfiy_the_editor_page_is_displayed() {
		Assert.assertTrue(queue.VerifyAssementPage());

	}

	@Given("User is in {string} code execution Page.")
	public void user_is_in_code_execution_page(String QueuePageLinks) {
		queue.clickonQLinks(QueuePageLinks);
		queue.TryHere(QueuePageLinks);
	}

	@When("User enters the code in editor space and clicks on run button to execute the code.")
	public void user_enters_the_code_in_editor_space_and_clicks_on_run_button_to_execute_the_code()
			throws InterruptedException {

		String sheetName = null;
		Map<String, String> Queuecode = null;

		if (scenario.trim()
				.equals("Verify code execution for valid Queue code.")
				|| scenario.trim().equals(
						"Verify code execution for invalid Queue code."))
			sheetName = "Queue-SODD";
		else if (scenario.trim()
				.equals("Verify code execution for valid Graph code.")
				|| scenario.trim().equals(
						"Verify code execution for invalid Graph code."))
			sheetName = "Graph-SODD";

		try {
			Queuecode = ExcelUtil.getTestRow(sheetName, scenario);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (Queuecode != null) {

			queue.EnterCode(Queuecode.get("Code").trim());
			output = Queuecode.get("Output").trim();

		}

	}

	@Then("Verfiy the expected and actual ouput displayed.")
	public void verfiy_the_expected_and_actual_ouput_displayed() {
		Assert.assertTrue(queue.getOutput(output));
	}

}
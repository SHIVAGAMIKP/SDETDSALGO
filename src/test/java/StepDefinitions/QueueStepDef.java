package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import Pages.LaunchPage;
import Pages.Queue;
import Pages.SignInPage;
import Utils.ExcelUtil;
import Utils.Savedata;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QueueStepDef {

	// private String code, output , QueuePageLinks;
	// private Map<String, String> Queuedata;
	// private String code;
	// private String QueuePageLinks;
	private String output;

	Queue queue = new Queue();

	SignInPage signIn = new SignInPage();
	LaunchPage lp = new LaunchPage();

	private String scenario;

	@Before(order = 2)
	public void GetScenario(Scenario sc) {

		scenario = sc.getName();

	}

	@Given("User is in Queue Page.")
	public void user_is_in_queue_page() throws InterruptedException {

		lp.launchApplication();
		lp.clickGetStarted();
		signIn.clickOnSignIn();
		signIn.Login("Test-229", "Shivagami229.");

		Assert.assertEquals(queue.goToQueuePAge(), "Queue");

	}

	@Then("Verify the count and names of the links.")
	public void verify_the_count_and_names_of_the_links(DataTable dataTable) {

		List<String> linknameslist = dataTable.asList();
		queue.GetLinkCount(linknameslist);
		Assert.assertTrue(queue.GetLinkCount(linknameslist));

	}

	@When("User clicks on {string} in Queue Page.")
	public void user_clicks_on_in_queue_page(String QueuePageLinks) {
		Assert.assertTrue(queue.clickonQLinks(QueuePageLinks));
	}

	@Then("Verify the {string} is displayed.")
	public void verify_the_expected_page_is_displayed(String QueuePageLinks) {
		Assert.assertTrue(queue.verifyQlinkPage(QueuePageLinks));
	}

	@Given("User is in {string} Page.")
	public void user_is_in_page(String QueuePageLinks) {
		queue.clickonQLinks(QueuePageLinks);
		Assert.assertTrue(queue.verifyQlinkPage(QueuePageLinks));

	}

	// @When("User clicks on TryHere link on {string}")
	// public void user_clicks_on_try_here_link_on(String QueuePageLinks) {
	// Assert.assertTrue(queue.TryHere(QueuePageLinks));
	// }

	@When("User clicks on TryHere link on  {string} and clicks on run button to execute the {string} entered in Editor space.")
	public void user_clicks_on_try_here_link_on_and_clicks_on_run_button_to_execute_the_entered_in_editor_space(
			String QueuePageLinks, String code) throws InterruptedException {

		Assert.assertTrue(queue.TryHere(QueuePageLinks));
		if (Savedata.getexecutionType() != null
				&& Savedata.getexecutionType().equalsIgnoreCase("DD")) {
			// queue.EnterCode(Savedata.getData().get("Code"));
			code = Savedata.getData().get("Code");
		}

		queue.EnterCode(code);
	}

	@Then("{string} for the executed code should be displayed.")
	public void expected_output_for_the_executed_code_should_be_displayed(
			String output) {
		if (Savedata.getexecutionType() != null
				&& Savedata.getexecutionType().equalsIgnoreCase("DD")) {

			// queue.getOutput(Savedata.getData().get("output"));
			output = Savedata.getData().get("Output");
		}

		Assert.assertTrue(queue.getOutput(output));
	}

	// @And("User clicks on run button to execute the {string} entered in Editor
	// space.")
	// public void
	// user_clicks_on_run_button_to_execute_the_sample_entered_in_editor_space(
	// String code) throws InterruptedException, IOException {
	// /*
	// * This if statement will execute when its data driven through excel
	// * only with code and output not end to end. Links will be selected from
	// * scenario outline but the code and output data will be from excel
	// */
	//
	// System.out.println("code is" + code);
	// if (Savedata.getexecutionType() != null
	// && Savedata.getexecutionType().equalsIgnoreCase("DD")) {
	// // Queuedata = Savedata.getData();
	// // code = Queuedata.get("Code");
	// queue.EnterCode(Savedata.getData().get("Code"));
	//
	// }
	// /*
	// * The below else if block is for data driven execution through scenario
	// * name in excel data sheet
	// */
	// else if ("<Code>".equalsIgnoreCase(code.trim())) {
	// System.out.println(scenario);
	//
	// /* Get Data from excel */
	// List<Map<String, String>> Queuecode = ExcelUtil
	// .getTestData("Queue-SODD");
	// System.out.println(Queuecode.get(0));
	// Map<String, String> getRow = Queuecode.stream()
	// .filter(row -> scenario.contains(row.get("ScenarioName")))
	// .findFirst().orElse(null);
	//
	// if (getRow != null) {
	//
	// code = getRow.get("Code").trim();
	// System.out.println(code);
	// output = getRow.get("Output").trim();
	// System.out.println(output);
	// queue.EnterCode(code);
	// } else {
	// System.out.println("mismatch");
	// }
	//
	// } else {
	// queue.EnterCode(code);
	// }
	//
	// }

	@When("User clicks on TryHere link on {string} and executes the code by clicking on run button.")
	public void user_clicks_on_try_here_link_on_and_executes_the_code_by_clicking_on_run_button(
			String QueuePageLinks) throws InterruptedException {

		Map<String, String> Queuecode = null;

		Assert.assertTrue(queue.TryHere(QueuePageLinks));
		try {
			Queuecode = ExcelUtil.getTestRow("Queue-SODD", scenario);
			System.out.println("Quesecode is " + Queuecode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Queuecode != null) {
			System.out.println(Queuecode);
			queue.EnterCode(Queuecode.get("Code").trim());

			output = Queuecode.get("Output").trim();

		} else {
			Assert.assertFalse(false);
		}

	}

	@Then("Verfiy the expected and actual ouput displayed.")
	public void verfiy_the_expected_and_actual_ouput_displayed() {
		Assert.assertTrue(queue.getOutput(output));
	}

	/*
	 * Data Driven through Excel -end to end with out scenario outline
	 */
	@Given("User is in Queue Page Links.")
	public void user_is_in_queue_page_links() {
		// Queuedata = Savedata.getData();
		// QueuePageLinks = Queuedata.get("QueuePageLinks").trim();
		// queue.clickonQLinks(QueuePageLinks);
		// Assert.assertTrue(queue.verifyQlinkPage(QueuePageLinks));

		queue.clickonQLinks(Savedata.getData().get("QueuePageLinks"));
		Assert.assertTrue(queue
				.verifyQlinkPage(Savedata.getData().get("QueuePageLinks")));

	}

	@When("User clicks on TryHere link on  QueuePage Links")
	public void user_clicks_on_try_here_link_on_queue_page_links() {
		// QueuePageLinks = Queuedata.get("QueuePageLinks").trim();
		Assert.assertTrue(
				queue.TryHere(Savedata.getData().get("QueuePageLinks")));
	}

	@When("User clicks on run button to execute the sample code entered in Queue Editor.")
	public void user_clicks_on_run_button_to_execute_the_sample_code_entered_in_queue_editor()
			throws InterruptedException {
		// code = Queuedata.get("Code").trim();
		queue.EnterCode(Savedata.getData().get("Code"));
	}

	@Then("Output for the executed code should be displayed.")
	public void output_for_the_executed_code_should_be_displayed() {
		// output = Queuedata.get("Output").trim();
		Assert.assertTrue(queue.getOutput(Savedata.getData().get("Output")));
	}

}
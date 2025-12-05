package StepDefinitions;

import org.testng.Assert;

import Pages.TreePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TreeStepDef {
	TreePage tp;
	String result;

	@Given("user successfully signed in to DSAlgo portal and user is on the Tree page")
	public void the_user_signed_in_and_is_on_the_tree_page_of_ds_algo_portal() {
		tp = new TreePage();
		tp.successfulLoginAndNavigationToTree();
	}

	@When("user clicks on {string}")
	public void user_clicks_on(String linkText) {

		tp.clickOnLink(linkText);
	}

	@Then("user should navigate to {string}")
	public void user_should_navigate_to(String expected) {
		Assert.assertEquals(tp.fetchTitle(), expected);
	}

	@Given("user is on {string} page")
	public void user_is_on_page(String topicLink) {
		tp.clickOnLink(topicLink);
	}

	@When("user clicks on Try Here button")
	public void user_clicks_on_button() {
		tp.clickOnTryHere();
	}

	@Then("user should navigate to Try Editor page")
	public void user_should_navigate_to_try_editor_page() {
		Assert.assertEquals(tp.fetchTitle(), "Assessment");
	}

	@When("user enters null or invalid code in try editor box")
	public void user_enter_null_or_invalid_code_in_try_editor_box() {
		result = tp.enterPythonCodeAndClickRun("n");
	}

	@Given("user is on try editor page of corresponding {string}")
	public void user_is_on_try_editor_page_of_corresponding(String topicLink) {
		tp.clickOnLink(topicLink);
		tp.clickOnTryHere();
	}

	@When("user enters valid code in try editor box")
	public void user_enter_in_try_editor_box() {
		result = tp.enterPythonCodeAndClickRun("y");
	}

	@Then("user should see output in the same window")
	public void user_should_see_output_in_the_same_window() {
		Assert.assertEquals(result.trim(), "Valid python code");
	}

	@Then("user should see alert in the same window")
	public void user_should_see_alert_in_the_same_window() {
		Assert.assertTrue(result.contains("SyntaxError"));
	}
}

package StepDefinitions;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import Pages.ArraysPage;
import Pages.HomePage;
import Pages.SignInPage;
import Utils.ExcelUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArraysStepDefinition {
	private static final Logger logger = LogManager
			.getLogger(ArraysStepDefinition.class);
	SignInPage signIn = new SignInPage();
	HomePage homepage = new HomePage();

	ArraysPage arrayspage = new ArraysPage();;

	private String output;
	private String scenario;

	@Before(order = 2)
	public void GetScenario(Scenario sc) {

		scenario = sc.getName();

	}

	@Given("user is signed into Arrays Introduction page")
	public void user_is_signed_into_arrays_introduction_page()
			throws IOException {
		homepage.launchApplication();
		homepage.clickGetStarted();
		homepage.gotosignin();

		signIn.userLogin();

		arrayspage.arraysgetstartedClick();
		arrayspage.fetchArrayspageTitle();

	}

	@When("user clicks on {string} link")
	public void user_clicks_on_link(String topiccoveredlink) {
		arrayspage.topicsCovered(topiccoveredlink);

	}

	@Then("user should be redirected to {string} page")
	public void user_should_be_redirected_to_page(String expected_pagetitle) {
		logger.info("verifying title of array sub page");
		String actual_pagetitle = arrayspage.fetchtitlepage();
		Assert.assertEquals(actual_pagetitle, expected_pagetitle,
				"does not match. Expected: " + expected_pagetitle
						+ " but found: " + actual_pagetitle);
	}

	@Given("user is on {string} page")
	public void user_is_on_page(String topiccoveredlink) {
		arrayspage.topicsCovered(topiccoveredlink);
	}

	@When("user clicks on Try Here button")
	public void user_clicks_on_try_here_button() {
		arrayspage.tryhere();
	}

	@Then("text editor should be displayed")
	public void text_editor_should_be_displayed() {

		logger.info("Verify if text editor is displayed");
		Assert.assertTrue(arrayspage.textEditorIsDisplayed());

	}

	@Given("user is on text editor page for corresponding {string} page")
	public void user_is_on_text_editor_page_for_corresponding_page(
			String topiccoveredlink) {
		arrayspage.topicsCovered(topiccoveredlink);
		arrayspage.tryhere();
		arrayspage.textEditorIsDisplayed();

	}

	@When("user enters python code and clicks Run button")
	public void user_enters_python_code_and_clicks_run_button() {

		Map<String, String> Arraycode = null;
		try {
			Arraycode = ExcelUtil.getTestRow("Arrays_sheet", scenario);

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (Arraycode != null) {
			arrayspage.runPythonCode(Arraycode.get("Code"));
			output = Arraycode.get("Output").trim();

		} else {
			Assert.assertFalse(false);
		}
	}

	@Then("output should be displayed in console")
	public void output_should_be_displayed_in_console() {
		String result = arrayspage.getResult();
		Assert.assertEquals(output, result);

	}

	@Then("error message should be displayed in popup window")
	public void error_message_should_be_displayed_in_popup_window() {
		String result = arrayspage.getResult();
		Assert.assertEquals(output, result);
	}

	@Given("user is on Applications of Array page")
	public void user_is_on_applications_of_array_page() {
		arrayspage.topicsCovered("Applications of Array");

	}

	@When("user clicks on Practice Questions link")
	public void user_clicks_on_practice_questions_link() {

		arrayspage.practicequeLink();

	}

	@Then("user should be redirected to Practice Questions page")
	public void user_should_be_redirected_to_practice_questions_page() {

		String actualTitle = arrayspage.getPageTitle();
		Assert.assertEquals("Practice Questions", actualTitle);
	}

	@Given("user is on Practice Questions page")
	public void user_is_on_practice_questions_page() {
		arrayspage.topicsCovered("Applications of Array");

		arrayspage.practicequeLink();
		arrayspage.getPageTitle();
	}

	@When("user clicks on the {string} link")
	public void user_clicks_on_the_link(String practiceQuestiontopic) {
		arrayspage.practicesubTopiclink(practiceQuestiontopic);
	}

	@Then("user should be navigated to the {string} page")
	public void user_should_be_navigated_to_the_page(String expectedTitle) {
		String actualTitle = arrayspage.subtopicTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Given("user is on {string} page with text editor")
	public void user_is_on_page_with_text_editor(String practiceQuestiontopic) {
		arrayspage.topicsCovered("Applications of Array");
		arrayspage.practicequeLink();
		arrayspage.getPageTitle();
		arrayspage.practicesubTopiclink(practiceQuestiontopic);
		arrayspage.subtopicTitle();
		arrayspage.textEditorIsDisplayed();

	}

}

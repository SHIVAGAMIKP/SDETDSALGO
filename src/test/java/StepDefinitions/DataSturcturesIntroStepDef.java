//package StepDefinitions;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.testng.Assert;
//
//import Pages.DataStructuresIntro;
//
//public class DataSturcturesIntroStepDef {
//
//	DataStructuresIntro dsi;
//	String result;
//
//	@Given("user is on Data structures introduction page of DSAlgo portal")
//	public void user_is_on_data_structures_introduction_page_of_ds_algo_portal() {
//		dsi = new DataStructuresIntro();
//		dsi.successfulLoginAndNavigationToDataStructuresIntro();
//	}
//
//	@When("user clicks on time complexity link")
//	public void user_clicks_on_time_complexity_link() {
//		dsi.goToTimeComplexPage();
//	}
//
//	@Then("user should navigate to time complexity page")
//	public void user_should_navigate_to_time_complexity_page() {
//		Assert.assertEquals(dsi.fetchTitle(), "Time Complexity");
//	}
//
//	@Given("user is on time complexity page")
//	public void user_is_on_time_complexity_page() {
//		dsi.goToTimeComplexPage();
//	}
//
//	@When("user clicks on practice questions link")
//	public void user_clicks_on_practice_questions_link() {
//		dsi.goToPracticeQuestionsPage();
//	}
//
//	@Then("user should navigate to Practice Questions page")
//	public void user_should_navigate_to_practice_questions_page() {
//		Assert.assertEquals(dsi.fetchTitle().trim(), "Practice Questions");
//	}
//
//	@Given("user navigates from Time Complex page to Try Editor page")
//	public void user_navigates_from_time_complex_page_to_try_editor_page() {
//		dsi.goToTimeComplexPage();
//		dsi.goToPracticeQuestionsPage();
//
//	}
//
//	@When("user clicks on Try Here button from time complexity page")
//	public void user_clicks_on_try_here_button_from_time_complexity_page() {
//		dsi.clickOnTryHere();
//	}
//
//	@Then("user should land on Try Editor page")
//	public void user_should_land_on_try_editor_page() {
//		Assert.assertEquals(dsi.fetchTitle(), "Assessment");
//	}
//
//	@Given("user landed on Try Editor page")
//	public void user_landed_on_try_editor_page() {
//		dsi.goToTimeComplexPage();
//		dsi.clickOnTryHere();
//	}
//
//	@When("user enters null or invalid code in text box")
//	public void user_enters_null_or_invalid_code_in_text_box() {
//		result = dsi.enterPythonCodeAndClickRun("n");
//	}
//
//	@Then("user should see alert message in the same window")
//	public void user_should_see_alert_message_in_the_same_window() {
//		Assert.assertTrue(result.contains("SyntaxError"));
//	}
//
//	@When("user enters valid code in text box")
//	public void user_enters_valid_code_in_text_box() {
//		result = dsi.enterPythonCodeAndClickRun("y");
//	}
//
//	@Then("user should see output below run button in the same window")
//	public void user_should_see_output_below_run_button_in_the_same_window() {
//		Assert.assertEquals(result.trim(), "Valid python code");
//	}
//
//}

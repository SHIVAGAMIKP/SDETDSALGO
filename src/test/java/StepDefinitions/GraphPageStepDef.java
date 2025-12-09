package StepDefinitions;

import java.util.List;

import org.testng.Assert;

import Pages.Graph;
import Pages.SignInPage;
import Pages.homePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GraphPageStepDef {

	Graph gr = new Graph();
	SignInPage signIn = new SignInPage();
	homePage lp = new homePage();
	// private Map<String, String> pythonTestCode;

	@Given("User is in Graph Page.")
	public void user_is_in_graph_page() {
		lp.launchApplication();
		// lp.homeApplication();
		lp.clickGetStarted();
		// lp.clickGetStarted();
		signIn.clickOnSignIn();
		signIn.Login("Test-229", "Shivagami229.");
		Assert.assertEquals(gr.goToGraphPAge(), "Graph");
	}

	@Then("Verify the count and names of the links on Graph Page.")
	public void verify_the_count_and_names_of_the_links_on_graph_page(
			DataTable dataTable) {
		List<String> linknameslist = dataTable.asList();
		Assert.assertTrue(gr.GetgraphLinkCount(linknameslist));

	}

	@When("User clicks on {string} in Graph Page.")
	public void user_clicks_on_in_graph_page(String grPageLinks) {
		Assert.assertTrue(gr.clickonGraphLinks(grPageLinks));
	}

	@Then("Verify {string} is displayed.")
	public void verify_is_displayed(String grPageLinks) {
		Assert.assertTrue(gr.verifyGraphlinkPage(grPageLinks));
	}

	@Given("User is in {string} topic of Graph page.")
	public void user_is_in_topic_of_graph_page(String grPageLinks) {
		gr.clickonGraphLinks(grPageLinks);
		Assert.assertTrue(gr.verifyGraphlinkPage(grPageLinks));

	}

	@When("User clicks on TryHere link on  {string}.")
	public void user_clicks_on_try_here_link_on(String grPageLinks) {
		Assert.assertTrue(gr.TryHere(grPageLinks));
	}

}
package StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import Pages.GraphPage;
import Pages.HomePage;
import Pages.SignInPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GraphPageStepDef {

	GraphPage graphPage = new GraphPage();
	SignInPage signIn = new SignInPage();
	HomePage homePage = new HomePage();

	@Given("User is in Graph Page.")
	public void user_is_in_graph_page() throws IOException {
		homePage.launchApplication();
		homePage.clickGetStarted();
		signIn.clickOnSignIn();
		signIn.userLogin();
		graphPage.goToGraphPAge();

	}

	@Then("Verify the count and names of the links on Graph Page.")
	public void verify_the_count_and_names_of_the_links_on_graph_page(
			DataTable dataTable) {
		List<String> linknameslist = dataTable.asList();
		Assert.assertTrue(graphPage.GetgraphLinkCount(linknameslist));

	}

	@When("User clicks on {string} in Graph Page.")
	public void user_clicks_on_in_graph_page(String grPageLinks) {
		graphPage.clickonGraphLinks(grPageLinks);
	}

	@Then("Verify {string} is displayed.")
	public void verify_is_displayed(String GraphPageLinks) {
		Assert.assertTrue(graphPage.verifyGraphlinkPage(GraphPageLinks));
	}

	@Given("User is in {string} page of Graph module.")
	public void user_is_in_page_of_Graph_module(String GraphPageLinks) {
		graphPage.clickonGraphLinks(GraphPageLinks);

	}

	@When("User clicks on TryHere link in {string} page of Graph module")
	public void user_clicks_on_try_here_link_in_page_of_Graph_module(
			String GraphPageLinks) {
		graphPage.TryHere(GraphPageLinks);
	}

}
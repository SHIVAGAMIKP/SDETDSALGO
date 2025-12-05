package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import Pages.LaunchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class launchStepDef {
	private static final Logger logger = LogManager.getLogger(launchStepDef.class);
	LaunchPage lp;

	@Given("The user opens DSAlgo portal link")
	public void user_is_in_launch_page() {
		lp = new LaunchPage();
		lp.launchApplication();

	}

	@When("The User clicks on GetStarted")
	public void user_user_clicks_on_get_started() {
		lp.clickGetStarted();
	}

	@Then("User should navigate to Home page")
	public void user_should_navigate_to_home_page() {
		logger.info("verifying title of home page");
		Assert.assertEquals(lp.fetchTitle(), "NumpyNinja");
	}

}

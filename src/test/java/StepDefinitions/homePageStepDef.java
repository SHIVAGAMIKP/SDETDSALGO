package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import DriverFactory.DriverFactory;
//import Pages.LaunchPage;
import Pages.homePage;
import Pages.loginpage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class homePageStepDef {
	private static final Logger logger = LogManager.getLogger(homePageStepDef.class);
		
	homePage hp;
	loginpage lp;

	

	@Given("The user opens DSAlgo portal link")
	public void user_is_in_launch_page() {
		hp = new homePage();
		hp.launchApplication();

	}

	@When("The User clicks on GetStarted")
	public void user_user_clicks_on_get_started() {
		hp.clickGetStarted();
	}

	@Then("User should navigate to Home page")
	public void user_should_navigate_to_home_page() {
		logger.info("verifying title of home page");
		Assert.assertEquals(hp.fetchTitle(), "NumpyNinja");
	}

	

	@Given("User is on home page")
	public void user_is_on_home_page() {
		
		hp = new homePage();
		hp.homeApplication();
	   
	}

	@When("User clicks the {string} link")
	public void user_clicks_the_link(String link) {
			hp.clickLink(link);
	    }

	@Then("User should be navigated to the registration page")
	public void user_should_be_navigated_to_the_registration_page() {
	    
		logger.info("verifying title of register page");
	
		Assert.assertEquals(hp.fetchregisterTitle(), "Register");
	}
	
	
	@Then("User should be navigated to the SignIn page")
	public void user_should_be_navigated_to_the_sign_in_page() {
		
		logger.info("verifying title of signin page");
		Assert.assertEquals(hp.fetchsigninpageTitle(), "Login");
		
	  
	}
	
	
	@When("The User select {string} from dropdown menu")
	public void the_user_select_from_dropdown_menu(String topiclink) {
	  hp.selectTopicFromDropdown(topiclink);
	}

	@Then("The user get warning message {string}")
	public void the_user_get_warning_message(String expectedMsg) {
	//	logger.info("verifying error message when cliking on dropdown without signin");
//		Assert.assertEquals(hp.fetchErrorMsg(), "You are not logged in");
		 logger.info("Verifying error message when clicking on dropdown options without signin");
		    String actualMsg = hp.fetchErrorMsg();
		    Assert.assertEquals(actualMsg, expectedMsg, 
		        "Warning message does not match. Expected: " + expectedMsg + " but found: " + actualMsg);
	}
	
	@Given("User is signed in and on Home Page")
	public void user_is_signed_in_and_on_home_page() {
		hp=new homePage();
		hp.homeApplication();
		hp.gotosignin();
		lp=new loginpage();
		lp.login();
	    
	}

	@Then("User should be navigating to the corresponding {string} introduction page")
	public void user_should_be_navigating_to_the_corresponding_introduction_page(String expectedTitle) {
	   String actualTitle=hp.fetchIntroductionPageTitle();
	//	logger.info("verifying corresponding introduction cliking on dropdown after signin");
		logger.info("verifying page navigating to corresponding page after signin");
		Assert.assertEquals(actualTitle,expectedTitle, "page title does not match");
		
	}

	
	@When("The user clicks on Get Started button for corresponding {string}")
	public void the_user_clicks_on_get_started_button_for_corresponding(String topic) {
		 hp.getStartedclick(topic);
	}
	

/*	@Then("User should see page navigated to the corresponding {string} introduction page")
	public void user_should_see_page_navigated_to_the_corresponding_introduction_page(String expectedTitle) {
	 
	//	String actualTitle=hp.fetchIntroductionPageTitle();
		
		Assert.assertEquals(actualTitle,expectedTitle, "page title does not match");
	}*/
}
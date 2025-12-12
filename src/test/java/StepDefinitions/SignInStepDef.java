package StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import Pages.HomePage;
import Pages.SignInPage;
import Utils.ExcelUtil;
import Utils.Savedata;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInStepDef {

	SignInPage signIn = new SignInPage();
	HomePage homePage = new HomePage();
	private String expectedMessage;

	@Given("User is in Sign-Page.")
	public void user_is_in_sign_page() {

		homePage.launchApplication();
		homePage.clickGetStarted();
		signIn.clickOnSignIn();

	}

	@When("User clicks on Sign-in link on Sign-In Page.")
	public void user_clicks_on_sign_in_link_on_sign_in_page() {

		signIn.clickOnSignIn();
	}

	@Then("User Sign-In page should be dispalyed.")
	public void user_sign_in_page_should_be_dispalyed() {

		Assert.assertEquals(signIn.getPageTitle(), "Login");
	}

	@When("User clicks on {string} link in Sign-In Page.")
	public void user_clicks_on_link_in_sign_in_page(String registerLink) {

		signIn.clickLink(registerLink);

	}

	@Then("User should be redirected to register Page.")
	public void user_should_be_redirected_to_register_page() {
		Assert.assertEquals(signIn.getPageTitle(), "Registration");

	}

	@When("User enters Login Credentials.")
	public void user_enters_and_clicks_on_login() throws IOException {
		signIn.userLogin();
	}

	@Then("{string} message should be displayed on home Page.")
	public void message_should_be_displayed_on_home_page(
			String expectedMessage) {
		String actualMessage = signIn.verifySuccesfulLogin();
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@When("User enters username,password and clicks on Login to {string}.")
	public void user_enters_username_password_and_clicks_on_login_to(
			String testcase) throws IOException {

		List<Map<String, String>> Logindata = ExcelUtil.getTestData("Sign-IN");
		Map<String, String> getRow = Logindata.stream().filter(
				row -> row.get("TestCase").trim().equals(testcase.trim()))
				.findFirst().orElse(null);

		if (getRow != null) {

			String username = getRow.get("Username").trim();
			String password = getRow.get("Password").trim();
			expectedMessage = getRow.get("Expected Result").trim();
			signIn.Login(username, password);
		}
	}

	@Then("Expected message should be displayed for {string}.")
	public void expected_message_should_be_displayed_for(String testCase) {
		Assert.assertEquals(signIn.verifySuccesfulLogintc(testCase),
				expectedMessage);
	}

	// Data Provider Data Driven Step def //
	@When("User enters username,password and clicks on Login.")
	public void user_enters_username_password_and_clicks_on_login()
			throws InterruptedException, IOException {

		Map<String, String> Logindata = Savedata.getData();

		String username = Logindata.get("Username").trim();
		String password = Logindata.get("Password").trim();
		signIn.Login(username, password);

	}

	@Then("Verfiy expected message is displayed.")
	public void Verfiy_expected_message_is_displayed() throws IOException {
		Map<String, String> Logindata = Savedata.getData();
		String actualMessage = signIn
				.verifySuccesfulLogintc(Logindata.get("SignInScenario"));
		Assert.assertEquals(actualMessage,
				Logindata.get("Expected Result").trim());
	}
}
// Data Provider Data Driven Step def //

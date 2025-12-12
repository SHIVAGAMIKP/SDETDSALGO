Feature: Sign-In

  Background: 
  	Given User is in Sign-Page.

  Scenario: Verify Sign-in link on Sign-In Page
    When User clicks on Sign-in link on Sign-In Page.
    Then User Sign-In page should be dispalyed.

  Scenario: Verify register link in the top right of the Sign-In Page.
    When User clicks on "register" link in Sign-In Page.
    Then User should be redirected to register Page.

  Scenario: Verify register! link below username and password field of Sign-In Page.
    When User clicks on "register!" link in Sign-In Page.
    Then User should be redirected to register Page.
    
   Scenario: Verify Login Credentials.
    When User enters Login Credentials.
    Then "You are logged in" message should be displayed on home Page.  

  Scenario Outline: Verify Login with valid and invalid credentials.
    When User enters username,password and clicks on Login to "<TestCase>". 
    Then Expected message should be displayed for "<TestCase>".
    Examples:
	
|TestCase									  		    |
|Verify login with empty username and password          |
|Verify login with empty username 			            |
|Verify login with empty password			            |
|Verify valid Login Credentials		                    |
|Verify valid with single character username            |
|Verify invalid Login Credentials with numeric password |
|Verify invalid Login Credentials					    |
|Verify invalid Login Credentials with special character|


    

@SiginDataProvider
  Scenario: Verify Login with valid and invalid credentials from Excel.
    When User enters username,password and clicks on Login.
    Then Verfiy expected message is displayed.   

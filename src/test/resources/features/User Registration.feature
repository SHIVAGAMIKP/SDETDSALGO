Feature: User Registration on DS Algo Portal

  Scenario: Successful registration with valid credentials
  Given user is on the Register page
  When user enters valid username and password
  When user confirms the same password and clicks Register
  Then user sees the Login page

  Scenario: Register with mismatched passwords
    Given user is on the Register page
When user enters valid username and password
When user confirms a different password and clicks Register
Then user sees an error message about password mismatch


  Given user is on the Register page
When user clicks Register without entering details
Then user sees validation messages for required fields

  Scenario Outline: Registration with invalid input data
  Given user is on the Register page
  When user enters <username> and <password>
  When user confirms <confirmPassword> and clicks Register
  Then <message> should be displayed


    Examples:
      | username    | password    | confirmPassword | message                      |
      | testuser1   | pass123     | pass321         | Passwords do not match       |
      |             | pass123     | pass123         | Username is required         |
      | testuser2   |             |                 | Password is required         |
      | testuser3   | short       | short           | Password too short           |
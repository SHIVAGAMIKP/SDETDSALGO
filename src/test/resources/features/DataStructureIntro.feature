Feature: Data Structures Introduction - Functional testing

  Background:
    Given user is on Data structures introduction page of DSAlgo portal

  Scenario: user is able to navigate to Time Complexity page
    When user clicks on time complexity link
    Then user should navigate to time complexity page

  Scenario: user is able to navigate to Practice Questions page
    Given user is on time complexity page
    When user clicks on practice questions link
    Then user should navigate to Practice Questions page

  Scenario: Verify the functionality of Try Here button on Time Complexity page
    Given user is on time complexity page
    When user clicks on Try Here button from time complexity page
    Then user should land on Try Editor page

  Scenario: Verify the functionality of Data Structures try editor with null or invalid python code
    Given user landed on Try Editor page
    When user enters null or invalid code in text box
    Then user should see alert message in the same window

  Scenario: Verify the functionality of  Data Structures try editor with valid python code
    Given user landed on Try Editor page
    When user enters valid code in text box
    Then user should see output below run button in the same window

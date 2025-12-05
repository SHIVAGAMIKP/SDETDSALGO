Feature: Launching the application



  Scenario: Navigation from launch page to home page
    Given The user opens DSAlgo portal link
    When The User clicks on GetStarted
    Then User should navigate to Home page

  Scenario: verify register link is visible and clicking link redirects to registration page
    Given User is on home page
    When User clicks the "Register" link
    Then User should be navigated to the registration page

  Scenario: verify SignIn link is visible and clicking link redirects to SignIn page
    Given User is on home page
    When User clicks the "Sign In" link
    Then User should be navigated to the SignIn page

  Scenario Outline: User is on Home Page and selects "<topic>" from dropdown menu without SignIn
    Given User is on home page
    When The User select "<topic>" from dropdown menu
    Then The user get warning message "You are not logged in"

    Examples:
      | topic       |
      | Arrays      |
      | Linked List |
      | Stack       |
      | Queue       |
      | Tree        |
      | Graph       |

  Scenario Outline: User is on Home Page and selects "<topic>" from dropdown menu after SignIn
    Given User is signed in and on Home Page
    When The User select "<topic>" from dropdown menu
    Then User should be navigating to the corresponding "<expectedHeading>" introduction page

    Examples:
      | topic       | expectedHeading |
      | Arrays      | Array           |
      | Linked List | Linked List     |
      | Stack       | Stack           |
      | Queue       | Queue           |
      | Tree        | Tree            |
      | Graph       | Graph           |

  Scenario Outline: User is on Home page and clicks GetStarted button for "<topic>" without sign in
    Given User is on home page
    When The user clicks on Get Started button for corresponding "<topic>"
    Then The user get warning message "You are not logged in"

    Examples:
      | topic                        |
      | Data Structures-Introduction |
      | Array                        |
      | Linked List                  |
      | Stack                        |
      | Queue                        |
      | Tree                         |
      | Graph                        |

  Scenario Outline: User is on Home page and clicks GetStarted button for "<topic>" after SignIn
    Given User is signed in and on Home Page 
    When The user clicks on Get Started button for corresponding "<topic>"
    Then User should be navigating to the corresponding "<expectedHeading>" introduction page

     Examples: 
      | topic                        | expectedHeading              |
      | Data Structures-Introduction | Data Structures-Introduction |
      | Array                        | Array                        | 
      | Linked List                  | Linked List                  |
      | Stack                        | Stack                        |
      | Queue                        | Queue                        |  
      | Tree                         | Tree                         | 
      | Graph                        | Graph                        |
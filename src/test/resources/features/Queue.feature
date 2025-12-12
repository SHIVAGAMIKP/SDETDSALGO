Feature: Queue Module

  Background:
    Given User is in Queue Page.

  Scenario: Verify count and names of links on Queue Page.
    Then Verify the count and names of the links.
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Verify the Links on Queue Page:
    When User clicks on "<QueuePageLinks>" in Queue Page.
    Then Verify the "<QueuePageLinks>" is displayed.

    Examples:
      | QueuePageLinks                         |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |
      
      
   Scenario Outline: Verify TryHere Link for all the links on Queue Page.
    Given User is in "<QueuePageLinks>" page of Queue module.
    When User clicks on TryHere link in "<QueuePageLinks>" page of Queue module
    Then Verfiy the Editor Page is displayed.

   Examples:
      | QueuePageLinks                         | 
      | Implementation of Queue in Python      | 
      | Implementation using collections.deque |
      | Implementation using array             | 
      | Queue Operations                       |   


  Scenario Outline: Verify code execution for valid Queue code. 
    Given User is in "<QueuePageLinks>" code execution Page.
    When User enters the code in editor space and clicks on run button to execute the code.
    Then Verfiy the expected and actual ouput displayed.

   Examples:
      | QueuePageLinks                         | 
      | Implementation of Queue in Python      | 
      | Implementation using collections.deque |
      | Implementation using array             | 
      | Queue Operations                       | 
      
      
  Scenario Outline: Verify code execution for invalid Queue code. 
    Given User is in "<QueuePageLinks>" code execution Page.
    When  User enters the code in editor space and clicks on run button to execute the code.
    Then  Verfiy the expected and actual ouput displayed.

   Examples:
      | QueuePageLinks                         | 
      | Implementation of Queue in Python      | 
      | Implementation using collections.deque | 
      | Implementation using array             | 
      | Queue Operations                       | 
       
       
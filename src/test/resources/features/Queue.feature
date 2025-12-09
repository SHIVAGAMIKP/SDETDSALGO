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
      
      
@Queue50:50DD
  Scenario Outline: : Verify TryHere Link and code execution for Queue Page
    Given User is in "<QueuePageLinks>" Page.
    When  User clicks on TryHere link on  "<QueuePageLinks>" and clicks on run button to execute the "<Code>" entered in Editor space.
    Then "<Output>" for the executed code should be displayed.

   Examples:
      | QueuePageLinks                         | Code                                            | Output                                 |
      | Implementation of Queue in Python      | print('Implementation of Queue in Python')      | Implementation of Queue in Python      |
      | Implementation using collections.deque | print('Implementation using collections.deque') | Implementation using collections.deque |
      | Implementation using array             | print('Implementation using array')             | Implementation using array             |
      | Queue Operations                       | print('Queue Operations')                       | Queue Operations                       |
      
      
#This Scenario is to validate valid and invalid  code through excel and data provider.
 
 @QQueueExcelDPDD    
  Scenario: Verify code execution for Queue Page - ExcelDD
   Given User is in Queue Page Links.
   When User clicks on TryHere link on  QueuePage Links
   And  User clicks on run button to execute the sample code entered in Queue Editor.
   Then Output for the executed code should be displayed.
   
   
#Below two Scenarios is to validate valid and invalid  code through excel and Scenario Outline.
@QSODD
  Scenario Outline: Verify TryHere Link and code execution for valid Queue code. 
    Given User is in "<QueuePageLinks>" Page.
    When User clicks on TryHere link on "<QueuePageLinks>" and executes the code by clicking on run button.
    Then Verfiy the expected and actual ouput displayed.

   Examples:
      | QueuePageLinks                         | 
      | Implementation of Queue in Python      | 
      | Implementation using collections.deque |
      | Implementation using array             | 
      | Queue Operations                       | 
      
      
@QSODD
  Scenario Outline: Verify TryHere Link and code execution for invalid Queue code. 
    Given User is in "<QueuePageLinks>" Page.
    When User clicks on TryHere link on "<QueuePageLinks>" and executes the code by clicking on run button.
    Then Verfiy the expected and actual ouput displayed.

   Examples:
      | QueuePageLinks                         | 
      | Implementation of Queue in Python      | 
      | Implementation using collections.deque | 
      | Implementation using array             | 
      | Queue Operations                       | 
       
       
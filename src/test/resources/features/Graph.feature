Feature: Graph Module

  Background:
    Given User is in Graph Page.

  Scenario: Verify count and names of links on Graph Page.
 
    Then Verify the count and names of the links on Graph Page.
      | Graph                 |
      | Graph Representations |

  Scenario Outline: Verify the Links on Graph Page:
    When User clicks on "<GraphPageLinks>" in Graph Page.
    Then Verify "<GraphPageLinks>" is displayed.

    Examples:
      | GraphPageLinks        |
      | Graph                 |
      | Graph Representations |


   Scenario Outline: Verify TryHere Link for all the links on Graph Page.
    Given User is in "<GraphPageLinks>" page of Graph module.
    When User clicks on TryHere link in "<GraphPageLinks>" page of Graph module
    Then Verfiy the Editor Page is displayed.

    Examples:
      | GraphPageLinks        | 
      | Graph                 | 
      | Graph Representations |    

 Scenario Outline: Verify code execution for valid Graph code. 
    Given User is in "<GraphPageLinks>" code execution Page.
    When User enters the code in editor space and clicks on run button to execute the code.
    Then Verfiy the expected and actual ouput displayed.

    Examples:
      | GraphPageLinks        | 
      | Graph                 | 
      | Graph Representations | 
      

Scenario Outline: Verify code execution for invalid Graph code.
    Given User is in "<GraphPageLinks>" code execution Page.
    When User enters the code in editor space and clicks on run button to execute the code.
    Then Verfiy the expected and actual ouput displayed.

    Examples:
      | GraphPageLinks        | 
      | Graph                 | 
      | Graph Representations | 

      
 
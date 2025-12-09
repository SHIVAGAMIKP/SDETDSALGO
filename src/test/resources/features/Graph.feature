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

@Graph50:50DD
  Scenario Outline: : Verify TryHere Link and code execution for links on  Graph Page
    Given User is in "<GraphPageLinks>" Page.
    When  User clicks on TryHere link on  "<GraphPageLinks>" and clicks on run button to execute the "<Code>" entered in Editor space.
    #And User clicks on run button to execute the "<Code>" entered in Editor space.
    Then "<Output>" for the executed code should be displayed.

    Examples:
      | GraphPageLinks        | Code                           | Output				   |
      | Graph                 | print('Graph')                 | Graph                 |
      | Graph Representations | print('Graph Representations') | Graph Representations |
      
@GraphSODD
Scenario Outline: Verify TryHere Link and code execution for valid Graph code.
    Given User is in "<GraphPageLinks>" Page.
    When User clicks on TryHere link on "<GraphPageLinks>" and executes the code by clicking on run button.
    Then Verfiy the expected and actual ouput displayed.

    Examples:
      | GraphPageLinks        | 
      | Graph                 | 
      | Graph Representations | 
      
@GraphSODD
Scenario Outline: Verify TryHere Link and code execution for invalid Graph code.
    Given User is in "<GraphPageLinks>" Page.
   	When User clicks on TryHere link on "<GraphPageLinks>" and executes the code by clicking on run button.
    Then Verfiy the expected and actual ouput displayed.

    Examples:
      | GraphPageLinks        | 
      | Graph                 | 
      | Graph Representations | 

      
 
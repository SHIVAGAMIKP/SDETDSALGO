Feature: Arrays Introduction Page

  Background:
    Given user is signed into Arrays Introduction page

  Scenario Outline: Verify Navigation from Arrays Introduction to subtopic pages
    When user clicks on "<subTopic>" link
    Then user should be redirected to "<expectedpagetitle>" page

    Examples:
      | subTopic                  | expectedpagetitle         |
      | Arrays in Python          | Arrays in Python          |
      | Arrays Using List         | Arrays Using List         |
      | Basic Operations in Lists | Basic Operations in Lists |
      | Applications of Array     | Applications of Array     |

  Scenario Outline: Access text editor from subtopic pages
    Given user is on "<subTopic>" page
    When user clicks on Try Here button
    Then text editor should be displayed

    Examples:
      | subTopic                  |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

  @DD
  Scenario Outline: Execute valid Python code in editor

    Given user is on text editor page for corresponding "<subTopic>" page
    When user enters python code and clicks Run button
    Then output should be displayed in console

    Examples:
      | subTopic                  |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

  @DD
  Scenario Outline: Execute invalid Python code in editor
    Given user is on text editor page for corresponding "<subTopic>" page
    When user enters python code and clicks Run button
    Then error message should be displayed in popup window

    Examples:
      | subTopic                  |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |


  Scenario: Redirect from Applications of Array page to Practice Question link
    Given user is on Applications of Array page
    When user clicks on Practice Questions link
    Then user should be redirected to Practice Questions page

  Scenario Outline: Navigate to practice question subpages
    Given user is on Practice Questions page
    When user clicks on "<practiceQuestiontopic>" link
    Then user should be navigated to the "<PraQuestiontopic>" page

    Examples:
      | practiceQuestiontopic                   | PraQuestiontopic |
      | Search the array                        | QUESTION         |
      | Max Consecutive Ones                    | QUESTION         |
      | Find Numbers with Even Number of Digits | QUESTION         |
      | Squares of a Sorted Array               | QUESTION         |

  Scenario Outline: Run valid Python code in practice question editor
    Given user is on "<practiceQuestiontopic>" page with text editor
    When user enters python code and clicks Run button
    Then output should be displayed in console
    
   Examples:
      | practiceQuestiontopic                   | 
      | Search the array                        | 
      | Max Consecutive Ones                    | 
      | Find Numbers with Even Number of Digits | 
      | Squares of a Sorted Array               | 
  Scenario Outline: Run invalid syntax in practice question editor
    Given user is on "<practiceQuestiontopic>" page with text editor
    When user enters python code and clicks Run button
    Then user should see alertMessage displayed in popup window
    
    Examples:
      | practiceQuestiontopic                   | 
      | Search the array                        | 
      | Max Consecutive Ones                    | 
      | Find Numbers with Even Number of Digits | 
      | Squares of a Sorted Array               | 

 
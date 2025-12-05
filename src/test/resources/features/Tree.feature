Feature: Tree Functional testing

  Background:
    Given user successfully signed in to DSAlgo portal and user is on the Tree page

  Scenario Outline: Navigation from Tree page to respective Tree topic pages
    When user clicks on '<topicLinks>'
    Then user should navigate to '<ExpectedTitle>'

    Examples:
      | topicLinks                     | ExpectedTitle                  |
      | Overview of Trees              | Overview of Trees              |
      | Terminologies                  | Terminologies                  |
      | Types of Trees                 | Types of Trees                 |
      | Tree Traversals                | Tree Traversals                |
      | Binary Trees                   | Binary Trees                   |
      | Types of Binary Trees          | Types of Binary Trees          |
      | Implementation in Python       | Implementation in Python       |
      | Binary Tree Traversals         | Binary Tree Traversals         |
      | Implementation of Binary Trees | Implementation of Binary Trees |
      | Applications of Binary trees   | Applications of Binary trees   |
      | Binary Search Trees            | Binary Search Trees            |
      | Implementation Of BST          | Implementation Of BST          |

  Scenario Outline: Verify the functionality of Try Here button on tree topic pages
    Given user is on '<tree topic>' page
    When user clicks on Try Here button
    Then user should navigate to Try Editor page

    Examples:
      | tree topic                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

  Scenario Outline: Verify the functionality of try editor with null or invalid python code
    Given user is on try editor page of corresponding '<tree topic>'
    When user enters null or invalid code in try editor box
    Then user should see alert in the same window

    Examples:
      | tree topic                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

  Scenario Outline: Verify the functionality of try editor with valid python code
    Given user is on try editor page of corresponding '<tree topic>'
    When user enters valid code in try editor box
    Then user should see output in the same window

    Examples:
      | tree topic                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

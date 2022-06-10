@SignUp
Feature: Sign Up Page

  @Positive @Register
  Scenario: [Positive] As a user, I want to sign up
    Given I go to Brick page
    When  I fill all valid data
    And   I click sign up button
    Then  I see 'success' notification
    And   Delete current registered user

  @Positive @LoginPage
  Scenario: [Positive] As a user, I want go to login page
    Given I go to Brick page
    When  I click login page
    Then  I see login page

  @Positive @ContactSales
  Scenario: [Positive] As a user, I want to contact sales
    Given I go to Brick page
    When  I click contact sales button
    Then  I will be directed to mail sales

  @Negative @ValidateFields
  Scenario Outline: [Negative] Validate all inputted fields error message
    Given I go to Brick page
    When  I input invalid data '<firstName>' '<lastName>' '<email>' '<phone>' '<password>' '<confirmPassword>'
    And   I click sign up button
    Then  I see error label
    Examples:
      | firstName | lastName | email             | phone | password | confirmPassword |
      | empty     | empty    | empty             | empty | empty    | empty           |
      | empty     | empty    | empty             | empty | 12345    | empty           |
      | empty     | empty    | empty             | empty | 12345    | 1234            |
      | empty     | empty    | empty             | empty | 123456   | 1234            |
      | empty     | empty    | asd               | empty | empty    | empty           |
      | empty     | empty    | asd@asd           | empty | empty    | empty           |
      | empty     | empty    | asd@asd.asd.      | empty | empty    | empty           |
      | empty     | empty    | asd@asd.asd.asdfg | empty | empty    | empty           |
      | space     | space    | empty             | empty | empty    | empty           |

  @Negative @SameEmail
  Scenario: [Negative] As a user, I want to sign up with same email
    Given I go to Brick page
    When  I fill all valid data
    And   I click sign up button
    Then  I see 'success' notification

    Given I go to Brick page
    When  I fill all valid data
    And   I click sign up button
    Then  I see 'error' notification
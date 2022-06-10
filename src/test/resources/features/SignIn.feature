@SignIn
Feature: Sign In Page

  @Positive @Login
  Scenario: [Positive] As a user, I want to sign in with registered account
    Given I go to Brick page
    When  I fill all valid data
    And   I click sign up button
    Then  I see 'success' notification
    When  I click login page
    Then  I see login page
    When  I input username and password
    And   I click sign in button
    Then  I see login notification
    And   Delete current registered user

  @Positive @LoginPage
  Scenario: [Positive] As a user, I want go to login page
    Given I go to Brick page
    When  I click login page
    Then  I see login page
    When  I click register page
    Then  I see register page

  @Positive @ForgotPassword
  Scenario: [Positive] As a user, I forgot my password
    Given I go to Brick page
    When  I click login page
    Then  I see login page
    When  I click forgot password button
    Then  I see forgot password page

  @Negative @InvalidAccount
  Scenario: [Negative] As a user, I want to sign in with invalid account
    Given I go to Brick page
    When  I click login page
    Then  I see login page
    When  I input username and password
    And   I click sign in button
    Then  I see invalid login notification
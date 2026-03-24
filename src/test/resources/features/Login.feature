Feature: Login to CRM Application

  Background:
    Given User launch the CRM URL

  Scenario: Successfully login with valid credentials
    #Given User launch the CRM URL
    When User login to CRM application
    Then User verify the homepage

    @LoginMultipleUsers
    Scenario: Login with multiple users from JSON
      When user logs in with multiple credentials from JSON





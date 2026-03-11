Feature: Login to CRM Application

  Scenario: Succesfully login with Valid credentials
    Given User launch the CRM URL
    When User enter the valid username and password
    And User click on login button
    Then User verify the homepage

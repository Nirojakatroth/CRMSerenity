Feature: CRM Contacts Module

  Background:
    Given User launches the CRM application URL
    When User logs into the CRM application
    And User clicks on Contacts


  @Smoke
  Scenario: Successfully create a contact
    When User creates a contact using excel "ContactsData"
    Then User verifies the created contact name


  @Master
  Scenario: Successfully delete the first contact
    When User verifies contact before deletion
    And User delete the first contact
    Then User verifies the contact after deletion


  @Sanity
  Scenario: Successfully search a contact
    When User searches for contact "dfh1772614881597 hwd1772614881597"
    Then User should see the searched contact


  @Regression
  Scenario: Successfully edit the existing contact
    When User updates contact details using "EditContact"
    Then User should see the contact updated
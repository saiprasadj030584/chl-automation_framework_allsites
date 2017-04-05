@login
Feature: Login with JDA application
  As a user
  I want to login 
  So that I am able to receive and desptach orers

  @complete
  Scenario: To check the order allocation
    Given user is in JDA Dispatcher Food application
    When User is in Order header and executes the order no
    And User select the SKU line
    And User Allocates the product
    Then Validate the status in Order Header screen

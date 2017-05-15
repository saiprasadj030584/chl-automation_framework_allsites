@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation

  @shipdock_reassignment
  Scenario: To assign the the shipdock
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to order header maintenance table
    And I search the order Id "3000000011"
    Then the ship dock should be displayed as "DEFAULT SD"
    When I navigate to ship dock reassignment table
    Then I enter the site id "9771" and order id "3000000011"
    Then I should find one record
    Then I enter the "FAVESD" in new shipdock reassignment
    Then the shipdock should be displayed as "FAVESD"

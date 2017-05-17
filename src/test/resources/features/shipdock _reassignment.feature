@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that it can be used for allocation

 
@complete
  Scenario: Shipdock reassignment
    #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to ship dock reassignment
    And I enter the from site id "9771" and order id "3000000001"
    And I proceed to next
    Then the order list should be displayed
    When I proceed to next
    And I enter the new ship dock "TESTSD"
    Then the ship dock should be updated for an order

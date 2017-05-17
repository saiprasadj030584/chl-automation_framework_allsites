@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that it can be used for allocation

  @allocation
  Scenario: STO allocation
    Given I have logged in as warehouse user in JDA dispatcher food application
    When i navigate to order header maintenance
    And i enter the order id "6666164250"
    Then i should see the status as "Allocated"
    When i enter the order id "6666164250" in order line maintenance page
    Then the tracking level, Qty Ordered, should be displayed
    Then i navigate user defined tab
    Then the case ratio should be dislayed
    Then i navigate to general tab
    Then the Qty tasked should be displayed if not back ordered should be flag

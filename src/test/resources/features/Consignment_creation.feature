@Consignment_Creation
Feature: Consignment Creation
  As a warehouse user
  I want to create the consignment
  So that the consignments can be used for picking and dispatching to stores

  @wip8
  Scenario: Create consignment for STO 
    #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to order preparation page
    And I select the group type as "Consignment"
    And I proceed to next
    And I enter the order id "5000011419"
    And I proceed to next
    Then I select the record
    And I proceed to next
    And the total orders should be displayed as "1"
    When I select the trailer type as "TRAILER"
    And I proceed to next
    And I proceed to next
    Then the record should be displayed for consignment preparation process
    When I proceed to complete the process
    Then the consignment should be generated in the order header maintenance
    
    
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to order preparation page
    And I select the group type as "Consignment"
    And I proceed to next
    And I enter the order id "5000011419"
    And I proceed to next
    And I select the record from the available list
    And I proceed to next
    Then the total orders should be displayed as 1
    When I select the trailer type as "TRAILER"
    And I proceed to next
    And I proceed to next
    Then the record should be displayed for consignment preparation process 
    When I proceed to complete the process
    And "step to click yes button"
    Then the consignment should be generated in the order header maintenance

    
    
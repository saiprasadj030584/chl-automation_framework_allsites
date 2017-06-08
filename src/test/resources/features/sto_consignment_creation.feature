@sto_consignment_creation
Feature: Consignment Creation
  As a warehouse user
  I want to create the consignment
  So that the consignments can be used for picking and dispatching to stores

  @complete @sto @consignment_creation
  Scenario Outline: Create consignment for STO
    #Pre-condition - STO should not have consignment earlier
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to order preparation page
    And I select the group type as "Consignment"
    And I proceed to next
    And I enter the order id "<OrderID>"
    And I proceed to next
    Then I select the record
    And I proceed to next
    And the total orders should be displayed as "1"
    When I select the trailer type as "TRAILER"
    And I proceed to next
    And I proceed to next
    Then the record should be displayed for consignment preparation process
    When I proceed to create the consignment
    Then the consignment should be generated in the order header maintenance

    Examples: 
      | OrderID     |
      | 89000051013 |

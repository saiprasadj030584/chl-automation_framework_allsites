@Consignment_Creation
Feature: Consignment Creation
  As a warehouse user
  I want to create the consignment
  So that the consignments be used for allocation

  @wip04
  Scenario: create consignment using Order Preparation screen
   # Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to order Header Maintenance page
    And I enter the Oreder id "3000000010"
    When I navigate to order preparation screen
    And I enter the Group type as "consignment"
    And I proceed to next
    And I have the order id "3000000010"
    And I proceed to next
    Then I select the record
    And I proceed to next
    When I select the Trailer type as"Trailer"
    And I proceed to next
    And I proceed to next
    Then the record should be displayed 
    When I proceed to complete the processs
    Then the consignment should be updated in the order header maintenance page

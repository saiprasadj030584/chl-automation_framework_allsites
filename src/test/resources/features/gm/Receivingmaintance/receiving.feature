@Receiving_Maintainance
Feature: Receiving Maintainance
  As a warehouse user
  I want to update the delivery status in Delivery_Maintainance

  @Receiving_Maintain
  Scenario Outline: Receiving Maintainance process
    Given I have an ASN_ID with Delivery status as "In Progress"
    When I navigate to Delivery Management page
    And I enter an ASN_ID as "<ASNID>"
    And I choose the Delivery status as "Complete"
    When I navigate to Delivery page 
    And I enter an ASN_ID
    Then the Delivery status should be update

    Examples: 
      | asn_id   |
      | 21101115 | 

  @purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order
  
  Scenario Outline: Inventory Lock for NV in  JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    Then I should see the receiving completion
    And I logout the putty
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table 
    And the "Inv Lock" itl should be updated in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7150061190 | REC002   |
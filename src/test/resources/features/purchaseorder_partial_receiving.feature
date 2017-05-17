@partial_receive_purchase_order
Feature: Partial receive purchase order
  As a warehouse user
  I want to receive the PO partially 
  So that I can receive the remaining later

  @receive_po_partial_ambient
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the "Ambient" category  PO "<PreAdviceID>" in "Released" status with more than one line items and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for selected pre-advice line item
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive the first sku of the purchase order at location "<Location>"
    And I navigate to inventory query page
    Then the inventory details should be displayed for all the tag id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "In Progress"
    Then the goods receipt should be generated for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  8000000003 | REC002   |

  @receive_po_partial_bws_bonded
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the "BWS-Bonded" category  PO "<PreAdviceID>" in "Released" status with more than one line items and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM and Vintage details for selected pre-advice line item
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive the first sku of the purchase order at location "<Location>"
    When I navigate to inventory query page
    Then the inventory details should be displayed for all the tag id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "In Progress"
    Then the goods receipt should be generated for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7000000010 | REC002   |

  @receive_po_partial_bws_non_bonded
  Scenario Outline: Receiving process in JDA WMS for BWS-Non-Bonded product category
    Given the "BWS-Non-Bonded" category  PO "<PreAdviceID>" in "Released" status with more than one line items and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for selected pre-advice line item
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive the first sku of the purchase order at location "<Location>"
    When I navigate to inventory query page
    Then the inventory details should be displayed for all the tag id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "In Progress"
    Then the goods receipt should be generated for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7000004500 | REC002   |

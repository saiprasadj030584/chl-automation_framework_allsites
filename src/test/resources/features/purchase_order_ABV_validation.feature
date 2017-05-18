@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip3
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-New Vintage" category should be "Released" status and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    And the ABV should be calculated for "<ABV>" percentage of its actual value
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus at location "<Location>"
    Then the error message should be displayed in receiving
    Then I proceed to complete the receiving
    And I navigate to inventory query page
    Then the ABV should be updated for all the Tag Id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"
    Then the goods receipt should be generated for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location | ABV |
      |  7150010210 | REC002   |  11 |
      |  7150010210 | REC002   |   5 |

  @wip4
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-New Vintage" category should be "Released" status and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>" with no ABV
    Then the error message should be displayed as enter the ABV for the UPC

    Examples: 
      | PreAdviceID | Location |
      |  0030001869 | REC002   |

  @wip8
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-New Vintage" category should be "Released" status and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    And I have the vintage for each line item
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus at location "<Location>"
    Then the error message should be displayed as vintage is not as expected

    Examples: 
      | PreAdviceID | Location |
      |  0030001869 | REC002   |

  @wip6
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-New Vintage" category should be "Released" status and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive  the skus for each line item at location "<Location>" with no vintage
    Then the error message should be displayed as enter vintage for the UPC

    Examples: 
      | PreAdviceID | Location |
      |  0030001869 | REC002   |

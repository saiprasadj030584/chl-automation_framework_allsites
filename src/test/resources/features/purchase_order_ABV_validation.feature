@po_abv_negative
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @complete @po @abv_more_than10
  Scenario Outline: Receiving purcahse order for BWS bonded product category with ABV more than 10 percentage
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    And the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus at location "<Location>" with additional "<ABVPercentage>" % of its actual value
    Then the error message should be displayed
    Then I proceed to complete the receiving
    Then the receiving should be completed
    Then the ABV should be updated for all the Tag Id
    Then the status should be diaplayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location | ABVPercentage |
      |  2058206818 | REC002   |            14 |

  @complete @po @abv_less_than10
  Scenario Outline: Receiving purchase order for BWS bonded product category with ABV less than 10 percentage
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    And the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus at location "<Location>" with additional "<ABVPercentage>" % of its actual value
    Then the receiving should be completed
    Then the ABV should be updated for all the Tag Id
    Then the status should be diaplayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location | ABVPercentage |
      |  2058206819 | REC002   |             5 |

  @complete @po @no_abv
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category with no ABV
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    And the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>" with no ABV
    Then the error message should be displayed

    Examples: 
      | PreAdviceID | Location |
      |  2058206820 | REC002   |

  @complete @po @incorrect_vintage
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category with incorrect vintage
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    And the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    And I have the vintage for each line item
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus at location "<Location>" with incorrect vintage
    Then the error message should be displayed

    Examples: 
      | PreAdviceID | Location |
      |  2058206821 | REC002   |

  @complete @po @no_vintage
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category with no vintage
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    And the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive  the skus for each line item at location "<Location>" with no vintage
    Then the error message should be displayed

    Examples: 
      | PreAdviceID | Location |
      |  2058206822 | REC002   |

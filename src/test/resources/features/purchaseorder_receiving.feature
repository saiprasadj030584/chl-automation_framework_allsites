@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @po_receive_ambient
  Scenario Outline: Receiving process in JDA WMS for Ambient1 product category
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    And I should see the receiving completion
    And I logout the putty
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  8050014845 | REC002   |

  @po_receive_bws_bonded
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    Then I should see the receiving completion
    And I logout the putty
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7150020201 | REC002   |

  @po_receive_bws_non_bonded
  Scenario Outline: Receiving process in JDA WMS for BWS-Non-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    Then I should see the receiving completion
    And I logout the putty
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7450012189 | REC002   |

  @po_receive_bws_new_vintage
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    Then I should see the receiving completion
    And I logout the putty
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  7150011190 | REC002   |

  @po_receive_in_progress_status
  Scenario Outline: In progress Status for ambient product
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive single sku for the purchase order at location "<Location>"
    Then the status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID | Location |
      |  8050005009 | REC002   |

  @po_receive_negative
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have pre-advice line items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order
    Then I should see error in receiving

    Examples: 
      | PreAdviceID | Status   |
      |  7150010189 | Complete |

@po_receiving
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @po_receive_ambient @po @complete
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in putty
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
      |  2058206801 | REC002   |

  @po_receive_bws_bonded @po @complete
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
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

    Examples: 
      | PreAdviceID | Location |
      |  2058206802 | REC002   |

  @po_receive_bws_non_bonded @po @complete
  Scenario Outline: Receiving process in JDA WMS for BWS-Non-Bonded product category
    Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
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

    Examples: 
      | PreAdviceID | Location |
      |  2058206803 | REC002   |

  @po_receive_bws_new_vintage @po @complete
  Scenario Outline: Receiving process in JDA WMS for BWS-New-Vintage product category
    Given the PO "<PreAdviceID>" with "BWS-New Vintage" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
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

    Examples: 
      | PreAdviceID | Location |
      |  2058206804 | REC002   |

  @po_receive_in_progress_status @po @complete
  Scenario Outline: In progress Status for ambient product
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive single sku for the purchase order at location "<Location>"
    Then the status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID | Location |
      |  2058206827 | REC002   |

  @po_receive_bws_F23_bonded @po @complete
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded F23 product category
    Given the PO "<PreAdviceID>" with "BWS-F23 Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
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

    Examples: 
      | PreAdviceID | Location |
      |  2058206805 | REC002   |

  @po_receive_nv_lock @po @complete
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

  @po_negative_receive_completepo @po @complete @po_demo
  Scenario: Receive a Complete / On hold Purchase order
    Given a PO should be "Complete" status
    And the PO should have pre-advice line items
    And I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order
    Then I should see that no valid preadvices found message

  @po_negative_receive_holdpo @po @complete
  Scenario Outline: Receive a Complete / On hold Purchase order
    Given the PO "<PreAdviceID>" should be "<Status>" status
    And the PO should have pre-advice line items
    And I have logged in as warehouse user in putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order
    Then I should see that no valid preadvices found message

    Examples: 
      | PreAdviceID | Status |
      |  2058206826 | Hold   |

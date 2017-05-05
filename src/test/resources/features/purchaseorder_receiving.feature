@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @po_receive_wip_bws
  Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    #Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, number of lines
    #And the PO should have address details in the pre-advice header maintenance table
    #Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    #And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items

    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice line items should be linked with the consignment
    #And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    #When I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    #When I select user directed option in main menu
    #And I receive the PO with basic and pre-advice receiving
    #Then I should be directed to pre-advice entry page
    #When I receive all the skus for pre-advice id "<PreAdviceID>" and at location "<Location>"
    #Then I should see the receiving completion
    #When I navigate to inventory query page
    #Then the inventory details should be displayed for all the tag id
    #When I navigate to pre-advice header maintenance page
    #Then the status should be displayed as "Complete"
    Examples: 
      | PreAdviceID | Host                                    | Port  | NoOfLines | Supplier | Location |
      |  7150010189 | hlxc0dc024.unix.marksandspencercate.com | 20140 |         1 | F01946   | REC002   |

  @po_receive_wip_ambient
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, number of lines
    And the PO should have address details in the pre-advice header maintenance table
    Then the supplier should have supplier pallet and customs excise details in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    When I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for pre-advice id "<PreAdviceID>" and at location "<Location>"
    Then I should see the receiving completion
    When I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    Then the inventory details should be displayed for all the tag id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"
    Then the goods receipt should be generated for the received stock in inventory transaction table

    Examples: 
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004580 |         1 | F01946   | REC002   |

  @po_receive_wip_demo
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for pre-advice id "<PreAdviceID>" and at location "<Location>"
    Then I should see the receiving completion
    When I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    Then the inventory details should be displayed for all the tag id
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"

    Examples: 
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004580 |         1 | F01946   | REC002   |

@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @po_receive_wip_bws
  Scenario Outline: Receiving process in JDA WMS
    #Given the PO "<PreAdviceID>" should be "Released" status and have future due date, site id, number of lines
    #And the PO should have address details in the pre-advice header maintenance table
    #Then the supplier should have supplier pallet details in the address maintenanace table
    When I create consignment for the supplier
    And I link the consignment to the pre-advice ID
    Then the pre-advice id should be displayed with the linked consignment
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
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004573 |         1 | F01946   | REC002   |

  @po_receive_wip_ambient
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
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004578 |         1 | F01946   | REC002   |

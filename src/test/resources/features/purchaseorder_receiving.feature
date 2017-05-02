@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip_receive
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for pre-advice id "<PreAdviceID>" and at location "<Location>"
    Then I should see the receiving completion

    Examples: 
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050005815 |         2 | F01946   | REC002   |

  @complete
  Scenario: Inventory is updated after the transaction
    Given I have logged in as warehouse user in JDA dispatcher food application
    #When I navigate to inventory query page and search the tag id "8050035478"
    Then the quantity on hand, location, site id and status should be displayed in the general tab
    And the expiry date, pallet id, receipt id and supplier details should be displayed in the miscellaneous tab
    And the storage location, base UOM and product groud should be displayed
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"

  @po_receive_wip
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
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004573 |         1 | F01946   | REC002   |

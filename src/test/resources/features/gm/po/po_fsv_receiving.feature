@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @po_receive_hanging @po @wiprg
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the FSV PO "<PreAdviceID>" of type "Hanging" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | SiteID | Location |
      | 25300100369 |   5885 | REC001   |

  @po_receive_boxed @po @inprogress
  Scenario Outline: Receiving process in JDA WMS for Boxed type
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      # | 25300100302 |   5649 | REC001   |
      | 25300100363 |   5649 | REC001   |

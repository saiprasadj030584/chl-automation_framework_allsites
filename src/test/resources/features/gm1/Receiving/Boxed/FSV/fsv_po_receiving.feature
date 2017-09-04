@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @boxed @fsv_po @receiving @boxed_receiving_fsv_receiving @complete
  Scenario Outline: Validate the FSV Receipt process
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300110363 |   5649 | REC001   |

  @boxed @fsv_po @receiving @boxed_receiving_fsv_receiving_multiple_lines @complete
  Scenario Outline: Validate the FSV Receipt process-multiple line items
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300523369 |   5649 | REC001   |

  @boxed @fsv_po @receiving @boxed_receiving_fsv_automatic_document_closure @complete
  Scenario Outline: Validate automatic document closure PO
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300110363 |   5649 | REC001   |

  @boxed @fsv_po @receiving @boxed_receiving_fsv_automatic_document_closure_multiple_line_items @complete
  Scenario Outline: Validate automatic document closure PO_multiple_line items
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300523369 |   5649 | REC001   |

  @boxed @fsv_po @receiving @complete @boxed_fsv_po_receiving_articles_with_different_putaway_group
  Scenario Outline: Validate receiving process for boxed when two putaway group involved
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus having different putaway group for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | PO199127833 |   5885 | REC001   |

  @FSV_po_over_receive_boxed @boxed @receiving @fsv_po @complete
  Scenario Outline: Over receiving process in JDA WMS for Hanging type with Lock Codes
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I perform "Over Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the error message should be displayed as cannot over receipt failed

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300110368 |   5649 | REC001   |

  @FSV_po_under_receive_boxed @boxed @receiving @fsv_po @complete
  Scenario Outline: Over receiving process in JDA WMS for Hanging type with Lock Codes
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I perform "Under Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the FSV po status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID | SiteID | Location |
      | 25300120368 |   5649 | REC001   |

  @FSV_po_over_receive_boxed_with_lock_code @boxed @receiving @fsv_po @complete
  Scenario Outline: Over receiving process in JDA WMS for Hanging type with Lock Codes
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    And I lock the product with lock code "<LockCode>"
    When I perform "Over Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the error message should be displayed as cannot over receipt failed

    Examples: 
      | PreAdviceID | SiteID | Location | LockCode |
      | 25300130368 |   5649 | REC001   | QAFTS    |

  @FSV_po_under_receive_boxed_with_lock_code @boxed @receiving @fsv_po @complete
  Scenario Outline: Over receiving process in JDA WMS for Hanging type with Lock Codes
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    And I lock the product with lock code "<LockCode>"
    When I perform "Under Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the FSV po status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID | SiteID | Location | LockCode |
      | 25300140368 |   5649 | REC001   | QAFTS    |

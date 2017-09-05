@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @boxed @fsv_po @receiving @boxed_receiving_fsv_receiving @complete @ds
  Scenario: Validate the FSV Receipt process
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @boxed @fsv_po @receiving @boxed_receiving_fsv_receiving_multiple_lines @complete @ds
  Scenario: Validate the FSV Receipt process-multiple line items
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @boxed @fsv_po @receiving @boxed_receiving_fsv_automatic_document_closure @complete @ds
  Scenario: Validate automatic document closure PO
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @boxed @fsv_po @receiving @boxed_receiving_fsv_automatic_document_closure_multiple_line_items @complete @ds
  Scenario: Validate automatic document closure PO_multiple_line items
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @boxed @fsv_po @receiving @complete @boxed_fsv_po_receiving_articles_with_different_putaway_group @ds
  Scenario: Validate receiving process for boxed when two putaway group involved
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus having different putaway group for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

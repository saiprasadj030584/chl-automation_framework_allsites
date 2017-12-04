@boxed_fsv_po_receiving
Feature: Boxed - FSV PO - Receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @boxed @fsv_po @receiving @unique_boxed_receiving_fsv_po_validate_the_fsv_receipt_process @complete @ds @jenkinsA
  Scenario: Validate the FSV Receipt process
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And I update the advice id for all line items
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    
  @boxed @fsv_po @receiving @unique_boxed_receiving_fsv_receiving_multiple_lines @complete @ds @jenkinsA
  Scenario: Validate the FSV Receipt process-multiple line items
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

 @jenkinsS @boxed @fsv_po @receiving @unique_boxed_receiving_fsv_po_validate_automatic_document_closure_po @complete @ds @jenkinsA
  Scenario: Validate automatic document closure PO
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @boxed @fsv_po @receiving @unique_boxed_receiving_fsv_automatic_document_closure_po_multiple_line_items @complete @ds @jenkinsA
  Scenario: Validate automatic document closure PO_multiple_line items
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed @fsv_po @receiving @complete @unique_boxed_receiving_fsv_po_validate_receiving_process_for_boxed_when_two_putaway_group_involved @ds
  Scenario: Validate receiving process for boxed when two putaway group involved
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus having different putaway group for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"

    @jenkinsA @boxed @fsv_po @receiving @unique_boxed_receiving_fsv_po_validate_the_urgent_delivery_po @complete @ds
  Scenario: Validate the urgent delivery PO
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And I update the advice id for all line items
    And the PO should not be linked with UPI line
    When I am on to pre-advice line maintenance page
    And I mark it as urgent PO
    Then the PO should be updated for urgent delivery
    
     @jenkinsA @unique_boxed_receiving_fsv_po_validate_manual_receipt @goh @receiving @direct_po @complete @ds
  Scenario: Validate manual receipt
   Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And I update the advice id for all line items
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
   
    

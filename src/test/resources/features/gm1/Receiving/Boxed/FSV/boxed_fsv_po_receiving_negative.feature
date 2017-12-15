@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

 @jenkinsA @unique_boxed_receiving_fsv_po_validate_damaged_on_receipt_from_supplier @boxed @fsv_po @receiving @complete @ds @jenkinsA
  Scenario: Validate damaged on receipt (From supplier) for FSV PO - Boxed
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "DMGD" should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    When I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged for fsv po
    
     @jenkinsA @unique_boxed_receiving_fsv_po_validate_not_received_po @fsv_po @boxed @receiving @complete @ds
  Scenario: Validate not received PO
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "REC001"
    Then Error message should be displayed on the page
    
    
     

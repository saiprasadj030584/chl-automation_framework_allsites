@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

  @boxed_receiving_fsv_po_validate_damaged_on_receipt_from_supplier @boxed @fsv_po @receiving @complete @ds  @jenkinsfsv @boxed_jenkins
  Scenario: Validate damaged on receipt (From supplier) for FSV PO - Boxed
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "DMGD" should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    When I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged
    
    
     

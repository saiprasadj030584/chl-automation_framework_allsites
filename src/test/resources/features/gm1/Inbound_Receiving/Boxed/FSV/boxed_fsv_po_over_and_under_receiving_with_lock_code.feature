@boxed_fsv_po_inbound_receiving_with_lock_code
Feature: Boxed - FSV PO - Inbound Receiving with lock code
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @jenkinsA @boxed_inbound_receiving_fsv_po_over_receiving_with_lock_code @boxed @inbound_receiving @fsv_po @complete @ds @boxed_jenkins
  Scenario: Validate Over receiving with lock code
   Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "QAFTS" should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I lock the product with lock code "QAFTS"
    When I perform "Over Receiving" for all skus for the FSV purchase order at location "REC001"
    Then the error message should be displayed as cannot over receipt failed

  @jenkinsA @boxed_inbound_receiving_fsv_po_under_receiving_with_lock_code @boxed @inbound_receiving @fsv_po @complete @ds @boxed_jenkins
  Scenario: Validate Under receiving with lock code
     Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "QAFTS" should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I lock the product with lock code "QAFTS"
    When I perform "Under Receiving" for all skus for the FSV purchase order at location "REC001"
    Then the FSV po status should be displayed as "In Progress"

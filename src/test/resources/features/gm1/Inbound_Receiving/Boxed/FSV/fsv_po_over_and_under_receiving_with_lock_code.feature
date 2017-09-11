@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @boxed_inbound_receiving_fsv_po_over_receiving_with_lock_code @boxed @inbound_receiving @fsv_po @complete @ds @jenkins
  Scenario Outline: Validate Over receiving with lock code
   Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I lock the product with lock code "QAFTS"
    When I perform "Over Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the error message should be displayed as cannot over receipt failed

  @boxed_inbound_receiving_fsv_po_over_receiving_with_lock_code @boxed @inbound_receiving @fsv_po @complete @ds @jenkins
  Scenario Outline: Validate Under receiving with lock code
     Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO with "<Lockcode>" should have sku, quantity due details
    And the PO should not be linked with UPI line
    And I lock the product with lock code "QAFTS"
    When I perform "Under Receiving" for all skus for the FSV purchase order at location "<Location>"
    Then the FSV po status should be displayed as "In Progress"

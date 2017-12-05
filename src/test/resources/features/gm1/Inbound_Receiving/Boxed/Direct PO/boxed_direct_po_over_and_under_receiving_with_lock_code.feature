@purchase_order_receiving
Feature: Inbound Receiving - Over & Under Receiving with Lock code
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @jenkinsS @jenkinsA @boxed_inbound_receiving_direct_po_over_receiving_with_lock_code @complete @ds @boxed @inbound_receiving @complete @ds @group_0
  Scenario: Validate Over receiving with lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I perform "Over Receiving" for all skus at location "REC001"
    Then the error message should be displayed as cannot over receipt

   @jenkinsS @jenkinsA @boxed_inbound_receiving_direct_po_under_receiving_with_lock_code @complete @ds @boxed @inbound_receiving @complete @ds 
  Scenario: Validate Under receiving with lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I perform "Under Receiving" for all skus at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"

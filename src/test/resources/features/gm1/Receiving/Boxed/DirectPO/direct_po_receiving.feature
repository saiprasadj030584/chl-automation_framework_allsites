@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_receiving_direct_po_receiving_without_lock_code @receiving @direct_po @boxed @complete @ds
  Scenario: Validate receipting process without lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @boxed_receiving_direct_po_receiving_automatic_document_closure @receiving @direct_po @boxed @complete @ds
  Scenario: Validate automatic document closure ASN
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @boxed_direct_po_receiving_preadvice_qty_greater_than_upi_qty @po @complete @boxed @receiving @direct_po @ds @jenkins1
  Scenario: Receiving when Pre advice line quantity is greater than the UPI line quantity
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"

  @boxed_receiving_direct_po_preadvice_qty_less_than_upi_qty @boxed @receiving @direct_po @complete @ds @jenkins1
  Scenario: Receiving when Pre advice line quantity is less than the UPI line quantity
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"

  @boxed @direct_po @receiving @complete @boxed_direct_po_receiving_articles_with_different_putaway_group @ds @jenkins2
  Scenario: Receiving process in JDA WMS for Boxed type having different putaway group
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus having different putaway group for the purchase order at location "REC001"
    Then the inventory should be displayed for all received tags for two putaway group
    And the goods receipt should be generated for received stock in inventory transaction for two putaway group
    Then the po status should be displayed as "Complete"

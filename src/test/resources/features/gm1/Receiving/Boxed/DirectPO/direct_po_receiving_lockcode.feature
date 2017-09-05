@direct_po_receiving_lock_code
Feature: Purchase order receiving with Lock code
  As a warehouse user
  I want to receive the locked articles
  But i cannot putaway the purchase order

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTS @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAFTS lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMP @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QACOMP lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMP"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPC @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAPC lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPC"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_FWL @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with FWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "FWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_REWORK @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with REWORK lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "REWORK"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSFWL @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAFTSFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QACNIFWL @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QACNIFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACNIFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCFWL @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAPCFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>QAPCFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAFTSRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMPRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QACOMPRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMPRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAPCRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPCRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_FWLRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with FWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "FWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAFTSFWLRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAFTSFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QACOMFWLRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QACOMFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

  @boxed_receiving_direct_po_receiving_with_lock_code_QAPCFWLRW @boxed @receiving @direct_po @complete @ds
  Scenario: Validate receiving process with QAPCFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPCFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

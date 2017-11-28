@direct_po_receiving_lock_code
Feature: Boxed - Direct PO - Receiving with Lock code
  As a warehouse user
  I want to receive the locked articles
  But i cannot putaway the purchase order

  
  @jenkinsA @jenkinsfo @jenkinsS @boxed_receiving_direct_po_receiving_with_lock_code_qafts @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAFTS lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsfo @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qacomp @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QACOMP lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMP"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qapc @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAPC lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPC"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_fwl @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with FWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "FWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

   @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_rework @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with REWORK lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "REWORK"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qaftsfwl @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAFTSFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @boxed_receiving_direct_po_receiving_with_lock_code_qacnifwl @boxed @receiving @direct_po @not_applciable @ds
  Scenario: Validate receiving process with QACNIFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACNIFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qapcfwl @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAPCFWL lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPCFWL"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qaftsrw @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAFTSRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsS @jenkinsA @boxed_receiving_direct_po_receiving_with_lock_code_qacomprw @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QACOMPRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMPRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsS @jenkinsA @boxed_receiving_direct_po_validate_receiving_process_with_qapcrw_lock_code @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAPCRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPCRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_validate_receiving_process_with_fwlrw_lock_code @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with FWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "FWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsS @jenkinsA @boxed_receiving_direct_po_validate_receiving_process_with_qapcfwlrw_lock_code @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAFTSFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTSFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsS @jenkinsA @boxed_receiving_direct_po_validate_receiving_process_with_qacomfwlrw_lock_code @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QACOMFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QACOMFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsS @jenkinsA @boxed_receiving_direct_po_validate_receiving_process_with_qapcfwlrw_lock_code @boxed @receiving @direct_po @complete @ds @jenkinsbrl
  Scenario: Validate receiving process with QAPCFWLRW lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAPCFWLRW"
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

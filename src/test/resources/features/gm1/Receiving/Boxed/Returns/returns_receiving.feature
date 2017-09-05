@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_verification_movement_label_field_blind_receiving @receiving @returns @boxed @complete @ds
  Scenario: Verification of movement label field in the blind receiving screen
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @boxed_receiving_returns_receiving_returns_single_ASN_multiple_URRN @receiving @returns @boxed @complete @ds
  Scenario: Verify receiving for single ASN holds many URRN
    Given the multiple UPI of type "Boxed" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi

  @boxed_receiving_returns_verify_due_receipt_date_upi_management @receiving @returns @boxed @complete @ds
  Scenario: Verify the ASN in the UPI management and check the due and receipt date along with ASN
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y"
    And I check the inventory for transaction update
    When I navigate to UPI Management screen
    And I search with ASN in UPI Management screen
    Then the due date and receipt date should be displayed for the ASN

  @boxed_receiving_returns_receiving_with_single_supplier @returns @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and unique UPC with one supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y'
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "Y" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @boxed_receiving_returns_receiving_unique_upc_n_condition @returns @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and unique UPC, quantity defaulted as '1' and perfect condition as 'N'
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @boxed_receiving_returns_receiving_upi_status_update @returns @receiving @returns @boxed @complete @ds
  Scenario: Verify able to see the progress of URRN using status update in the UPI header screen
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @boxed_receiving_returns_receiving_with_multi_supplier_y_condition @returns @receiving @returns @boxed @review
  Scenario: Do detail receiving process by providing input as URRN and unique UPC with multi supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' / 'N'
    Given the UPI and ASN should be in "Released" status for multi sourced SKU
    When I perform receiving for all skus at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multi sourced SKU receipt

  @boxed_receiving_returns_receiving_with_multi_supplier_n_condition @returns @receiving @returns @boxed @complete @ds
  Scenario Outline: Do detail receiving process by providing input as URRN and unique UPC with multi supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' / 'N'
    Given the UPI and ASN should be in "Released" status for multi sourced SKU
    When I perform receiving for all skus at "REC003" with perfect condition "N"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multi sourced SKU receipt

@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_rms_verify_receiving_with_urrn_holds_different_dept_upc_and_mixed_stock @boxed @receiving @returns @review @check18
  Scenario: Verify receiving with URRN holds different dept UPC and mixed stock
    Given the UPI with "Boxed" skus and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order with mixed stock at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @boxed_receiving_returns_rms_validate_detail_receiving_urrn_and_verify_the_no_of_singles_per_upc_to_be_received_quantity_defaulted_as_1_and_verify_itl_screen_after_receiving @boxed @receiving @returns @review @check18
  Scenario: Returns receiving verification for number of singles per UPC
    Given the UPI with "Boxed" skus and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order with mixed stock at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

@boxed_receiving_returns
Feature: Boxed - Returns - Receiving
  As a warehouse user
  I want to receive the returned articles

  @yes @unique_boxed_receiving_returns_rms_verify_receiving_with_urrn_holds_different_dept_upc_and_mixed_stock @boxed @receiving @returns @review @check18 @complete1 
  Scenario: Verify receiving with URRN holds different dept UPC and mixed stock
    Given the UPI with "Boxed" skus and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order with mixed stock at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @yes @unique_boxed_receiving_returns_rms_validate_detail_receiving_urrn_and_verify_the_no_of_singles_per_upc_to_be_received_quantity_defaulted_as_1_and_verify_itl_screen_after_receiving @boxed @receiving @returns @review @complete1 
  Scenario: Returns receiving verification for number of singles per UPC
    Given the UPI with "Boxed" skus and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order with mixed stock at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @unique_boxed_receiving_returns_verify_the_asn_in_the_upi_management_and_check_the_due_and_receipt_ date_ along_with_asn @receiving @returns @boxed @complete1 @ds
  Scenario: Verification of movement label field in the blind receiving screen
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @unique_boxed_receiving_returns_verify_receiving_for_single_asn_holds_many_urrn @receiving @returns @boxed @complete1
  Scenario: Verify receiving for single ASN holds many URRN
    Given the multiple UPI of type "Boxed" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi

  @unique_boxed_receiving_returns_verify_due_receipt_date_upi_management @receiving @returns @boxed @complete1 @ds
  Scenario: Verify the ASN in the UPI management and check the due and receipt date along with ASN
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y"
    And I check the inventory for transaction update
    When I navigate to UPI Management screen
    And I search with ASN in UPI Management screen
    Then the due date and receipt date should be displayed for the ASN

  @unique_boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_unique_upc_with_one_supplier_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_y_ @returns @receiving @returns @boxed @complete1 @ds
  Scenario: Do detail receiving process by providing input as URRN and unique UPC with one supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y'
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "Y" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @unique_boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_unique_upc_quantity_defaulted_as_1_and_perfect_condition_as_n_ @returns @receiving @returns @boxed @complete1 @ds
  Scenario: Do detail receiving process by providing input as URRN and unique UPC, quantity defaulted as '1' and perfect condition as 'N'
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @unique_boxed_receiving_returns_verify_able_to_see_the_progress_of_urrn_using_status_update_in_the_upi_header_screen @returns @receiving @returns @boxed @complete @ds
  Scenario: Verify able to see the progress of URRN using status update in the UPI header screen
    Given the UPI and ASN should be received at "REC003" for normal upc with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

  @jenkinsA @unique_boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_unique_upc_with_multi_supplier_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_n_  @returns @receiving @returns @boxed @complete1 @ds
  Scenario: Do detail receiving process by providing input as URRN and unique UPC with multi supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' / 'N'
    Given the UPI and ASN should be in "Released" status for multi sourced SKU
    When I perform receiving for all skus at "REC003" with perfect condition "N"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multi sourced SKU receipt

  @jenkinsA @unique_boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_unique_upc_with_multi_supplier_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_y @returns @receiving @returns @boxed @ds @complete1 
  Scenario: Do detail receiving process by providing input as URRN and unique UPC with multi supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' / 'N'
    Given the UPI and ASN should be in "Released" status for multi sourced SKU
    When I perform receiving for all skus at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multi sourced SKU receipt

@unique_boxed_receiving_returns_rms_validate_detail_receiving_urrn_and_verify_the_no_of_singles_per_upc_to_be_received_quantity_defaulted_as_1_and_verify_itl_screen_after_receiving @returns @receiving @returns @boxed @ds @complete1
  Scenario: Validate detail receiving URRN and verify the no. of singles per UPC to be received,quantity defaulted as '1' and verify ITL screen after receiving
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

@boxed_receiving_returns_footwear
Feature: Boxed - Returns - Receiving - Footwear
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_n_dmg_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'N' – DMG lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "DMGD"

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit_both_have_same_suffix]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_y_imperfect_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – IMPERFECT lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and lockcode "IMPERFECT"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPERFECT"

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit_both_have_same_suffix]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_n_imperfect_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'N' - IMPERFECT lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "IMPERFECT"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPERFECT"

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit_only_one_value_provided]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_y_singleshoe_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' –  SINGLESHOE lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and lockcode "SINGLESHOE"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "SINGLESHOE" 
    
     @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit_only_one_value_provided]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_n_singleshoe_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'N' -  SINGLESHOE lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "SINGLESHOE"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "SINGLESHOE"

    @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_footwear_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_perfect_condition_as_y_should_prompt_to_provide_correct_upc_as_10_digit @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – Should prompt to provide correct UPC as 10 digit
    Given the UPI and ASN should be in "Released" status
    When I provide eight digit UPC while receiving all skus at "REC003" with perfect condition "Y"
    Then footer UPC validation error message should be displayed

   
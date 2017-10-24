@boxed_receiving_returns_partset
Feature: Boxed - Returns - Receiving - PartSet
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_part_in_set_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_part_in_set_is_2_perfect_condition_as_y_ @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'Y'
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and partset "2"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_part_in_set_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_part_in_set_is_2_perfect_condition_as_n_–_dmg_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'N' – DMG lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and partset "2" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "DMGD"

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_part_in_set_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_part_in_set_is_1_perfect_condition_as_y_impset_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'Y' -IMPSET lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and partset "1" and lockcode "IMPSET"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPSET"

  @boxed_receiving_returns_do_detail_receiving_process_by_providing_input_as_urrn_and_part_in_set_upc_[10_digit]_followed_by_quantity_should_be_defaulted_as_1_and_part_in_set_is_1_perfect_condition_as_n_impset_lock_code_in_itl @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'N' -IMPSET lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and partset "1" and lockcode "IMPSET"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPSET"

@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_footwear_damaged_n_perfect_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'N' – DMG lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "DMGD"

  @boxed_receiving_returns_footwear_imperfect_y_perfect_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – IMPERFECT lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and lockcode "IMPERFECT"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPERFECT"

  @boxed_receiving_returns_footwear_imperfect_n_perfect_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'N' - IMPERFECT lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "IMPERFECT"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPERFECT"

  @boxed_receiving_returns_footwear_singleshoe_y_perfect_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' –  SINGLESHOE lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and lockcode "SINGLESHOE"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "SINGLESHOE"

  @boxed_receiving_returns_footwear_singleshoe_n_perfect_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'N' -  SINGLESHOE lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and lockcode "SINGLESHOE"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "SINGLESHOE"

  @boxed_receiving_returns_footwear_digit_validation @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – Should prompt to provide correct UPC as 10 digit
    Given the UPI and ASN should be in "Released" status
    When I provide eight digit UPC while receiving all skus at "REC003" with perfect condition "Y"
    Then footer UPC validation error message should be displayed

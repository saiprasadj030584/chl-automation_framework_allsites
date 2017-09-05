@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_receiving_returns_with_partset2 @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'Y'
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and partset "2"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

  @boxed_receiving_returns_receiving_returns_with_partset2_and_lockcode_DMGD_n_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'N' – DMG lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and partset "2" and lockcode "DMGD"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "DMGD"

  @boxed_receiving_returns_receiving_returns_with_partset1_and_lockcode_IMPSET_y_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'Y' -IMPSET lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "Y" and partset "1" and lockcode "IMPSET"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPSET"

  @boxed_receiving_returns_receiving_returns_with_partset1_and_lockcode_IMPSET_n_condition @receiving @returns @boxed @complete @ds
  Scenario: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'N' -IMPSET lock code in ITL
    Given the UPI and ASN should be in "Released" status
    And I receive all skus for the returns order at "REC003" with perfect condition "N" and partset "1" and lockcode "IMPSET"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "IMPSET"

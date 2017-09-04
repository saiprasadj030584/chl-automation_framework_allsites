@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_receiving_returns_with_partset2 @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'Y'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PreAdviceID | PalletId             | ASN        | Location | Condition | Partset |
      |  2010002111 | 00050456000511235601 | 0000100508 | REC001   | Y         |       2 |

  @boxed_receiving_returns_receiving_returns_with_partset2_and_lockcode_DMGD_n_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'N' – DMG lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset | LockCode |
      | 58850006476470077010064764700400 | 0000647647 | REC003   | N         |       2 | DMGD     |

  @boxed_receiving_returns_receiving_returns_with_partset1_and_lockcode_IMPSET_y_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'Y' -IMPSET lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset | LockCode |
      | 58850006376310077010063763100400 | 0000637631 | REC003   | Y         |       1 | IMPSET   |

  @boxed_receiving_returns_receiving_returns_with_partset1_and_lockcode_IMPSET_n_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 1, perfect condition as 'N' -IMPSET lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset | LockCode |
      | 58850006576270077010065762700400 | 0000657627 | REC003   | N         |       1 | IMPSET   |

@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_footwear_damaged_n_perfect_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'N' – DMG lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode |
      | 58850008192683077010081926830400 | 00008192683 | REC003   | N         | DMGD     |

  @boxed_receiving_returns_footwear_imperfect_y_perfect_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – IMPERFECT lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | 58850005786180077010057861800100 | 00005786181 | REC003 | Y | IMPERFECT |

  @boxed_receiving_returns_footwear_imperfect_n_perfect_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -both have same suffix] , followed by quantity should be defaulted as '1' and perfect condition as 'N' - IMPERFECT lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode  |
      | 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT |

  @boxed_receiving_returns_footwear_singleshoe_y_perfect_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' –  SINGLESHOE lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode   |
      | 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |

  @boxed_receiving_returns_footwear_singleshoe_n_perfect_condition @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit -only one value provided] , followed by quantity should be defaulted as '1' and perfect condition as 'N' -  SINGLESHOE lock code in ITL
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode   |
      | 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |

  @boxed_receiving_returns_footwear_digit_validation @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and footwear UPC [10 digit] , followed by quantity should be defaulted as '1' and perfect condition as 'Y' – Should prompt to provide correct UPC as 10 digit
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    When I provide eight digit UPC while receiving all skus at "<Location>" with perfect condition "<Condition>"
    Then footer UPC validation error message should be displayed

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850003166960077010031669600300 | 0000316158 | REC003   | Y         |

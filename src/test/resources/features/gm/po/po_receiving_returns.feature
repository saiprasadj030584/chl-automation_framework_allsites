@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @po_receiving_returns_footwear @po @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode   |
      | 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      | 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      | 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      | 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |

  @returns_receiving_footwear_digit_validation
  Scenario Outline: Returns receiving for Footwear - UPC Digit validation
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    When I provide eight digit UPC while receiving all skus at "<Location>" with perfect condition "<Condition>"
    Then footer UPC validation error message should be displayed
   
    Examples: 
      | PalletId                         | ASN        | Location |
      #| 58850003166280077010031662800300 | 0000316218 | REC003   |
      #| 58850003166380077010031663800300 | 0000316318 | REC003   |
      | 58850003166480077010031664800300 | 0000316418 | REC003   |

  @returns_receiving_multisupplier_perfect_Y
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition as Y
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      #| 58850003166280077010031662800300 | 0000316218 | REC003   |
      #| 58850003166380077010031663800300 | 0000316318 | REC003   |
      | 58850003166480077010031664800300 | 0000316418 | REC003   |

  @returns_receiving_multisupplier_perfect_n
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition as N
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      #| 58850003166280077010031662800300 | 0000316218 | REC003   |
      #| 58850003166380077010031663800300 | 0000316318 | REC003   |
      | 58850003166480077010031664800300 | 0000316418 | REC003   |

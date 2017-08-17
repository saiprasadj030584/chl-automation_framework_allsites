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
      | PalletId                         | ASN        | Location | Condition |
      #| 58850003166280077010031662800300 | 0000316218 | REC003   | Y         |
      #| 58850003166380077010031663800300 | 0000316318 | REC003   | N         |
      #| 58850003166480077010031664800300 | 0000316418 | REC003   | N        |
      | 58850003166960077010031669600300 | 0000316158 | REC003   | Y         |

  @returns_receiving_perfect_condition 
  Scenario Outline: Do detail receiving process by providing input as URRN and unique UPC with multi supplier , followed by quantity should be defaulted as '1' and perfect condition as 'y' / 'N'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for multi sourced SKU
      When I perform receiving for all skus at "<Location>" with perfect condition "<Condition>"
     When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multi sourced SKU receipt
    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      #| 58850003166380077010031663800300 | 0000316318 | REC003   | Y         |
      #| 58850003166990077010031669900300 | 0000316998 | REC003   | N         |
      #| 58850003566970077010035669700300 | 0000346978 | REC003   | Y         |
      #| 58850001166990077010011669900300 | 0000116998 | REC003   | N         |
      | 58850001366990077010013669900300 | 0000136998 | REC003   | N         |
      

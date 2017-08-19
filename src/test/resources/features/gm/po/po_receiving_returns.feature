@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @receiving_returns_footwear @po @complete
  Scenario Outline: Returns receiving for Footwear with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode  |
      | 58850006486180077010064861800200 | 00006486181 | REC003   | Y         | IMPERFECT |
  #| 58850001231330077010012313300600 | 0000123133 | REC003   | Y         | IMPERFECT |
  #| 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
  #| 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
  #| 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
  #| 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
  
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

  @returns_receiving @returns @wipr
  Scenario Outline: Returns receiving process in JDA WMS for Boxed type upc with single supplier without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for normal upc with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850004395640077010027343300600 | 0000478916 | REC003   | N         |
      | 58850004220650077010027343300600 | 0000133216 | REC003   | Y         |

  @receiving_returns_with_partset
  Scenario Outline: Returns receiving for Part set without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
      #| PO2010002002 | PO050456000511235611 | PO00100501 | REC001   |
      #| PO2010003001 | PO050456000511235710 | PO00100600 | REC001   |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |

  @receiving_returns_with_partset_and_lockcode
  Scenario Outline: Returns receiving for Part set with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId | ASN | Location | Condition | Partset | LockCode |

  #| 58850006476470077010064764700400 | 0000647647 | REC003   | N         |       2 | DMGD     |
  #| 58850006376310077010063763100400 | 0000637631 | REC003   | Y         |       1 | IMPSET   |
  #| 58850006576270077010065762700400 | 0000657627 | REC003   | N         |       1 | IMPSET   |
  @receiving_returns_qty_singles_verfication
  Scenario Outline: Returns receiving verification for number of singles per UPC
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008386250077010083862500300 | 0000838625 | REC003   | Y         |

  @verification_movement_label_field_blind_receiving
  Scenario Outline: Returns receiving verification of movement label screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      | 95580085370650011050230212341238 | 0000838629 | REC003   |

@purchase_order_receiving_returns
Feature: Purchase order receiving
  
  As a warehouse user
  I want to receive the returned articles

  @returns_receiving @returns @wipr
  Scenario Outline: Returns receiving process in JDA WMS for Boxed type upc with single supplier without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for normal upc with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for SKU with single supplier

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850004395640077010027343300600 | 0000478916 | REC003   | N         |
      | 58850004220650077010027343300600 | 0000133216 | REC003   | Y         |

  @receiving_returns_qty_singles_verfication @onhold
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

      
      @boxed_receiving_returns_receiving_returns_with_partset @receiving @returns @boxed @complete
  Scenario Outline: Do detail receiving process by providing input as URRN and Part in set UPC [10 digit] , followed by quantity should be defaulted as '1' and Part in set is 2, perfect condition as 'Y'
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

  
  
  
  @receiving_returns_qty_singles_verfication @onhold
  Scenario Outline: Returns receiving verification for number of singles per UPC
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008386250077010083862500300 | 0000838625 | REC003   | Y         |

  @boxed_receiving_returns_verification_movement_label_field_blind_receiving @receiving @returns @boxed @complete
  Scenario Outline: Verification of movement label field in the blind receiving screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      | 95580085370650011050230212341238 | 0000838629 | REC003   |
      
      @boxed_receiving_returns_receiving_returns_single_ASN_multiple_URRN @receiving @returns @boxed @complete
  Scenario Outline: Verify receiving for single ASN holds many URRN
    Given the multiple UPI "<PalletId>" of type "Boxed" and ASN "<ASN>" should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi

    Examples: 
      | PalletId                                                          | ASN        | Location | Condition |
     # | 56490000359590536160009081800200,56490000369590536190009081900600 | 0000006298 | REC003   | Y         |
     | 58850008387380077010083865900300,58850008387380077010083856100300 | 0000838748 | REC003   | Y         |
     #| 58850008387380077010083872800300,58850001251140077010012512400300 | 0000828738 | REC003   | Y         | second upi 2 nd line item sku partset
     
     @boxed_receiving_returns_verify_due_receipt_date_upi_management @receiving @returns @boxed @complete
  Scenario Outline: Verify the ASN in the UPI management and check the due and receipt  date  along with ASN
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    And I check the inventory for transaction update
    When I navigate to UPI Management screen
    And I search with ASN in UPI Management screen
    Then the due date and receipt date should be displayed for the ASN

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008387380077010083873800300 | 0000838738 | REC003   | Y         |
      

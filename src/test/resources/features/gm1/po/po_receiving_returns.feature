@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @receiving_returns_footwear @po @complete
  Scenario Outline: Returns receiving for Footwear with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode |
      #| 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      #| 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      #| 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      #| 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
      | 58850008192683077010081926830400 | 00008192683 | REC003   | N         | DMGD     |

  @receiving_returns_with_partset
  Scenario Outline: Returns receiving for Part set without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset |
      | 58850006276460077010062764600400 | 0000627646 | REC003   | Y         |       2 |

  @receiving_returns_with_partset_and_lockcode
  Scenario Outline: Returns receiving for Part set with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
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
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      
      | 58850001251140077010012511400300 | 0000125114 | REC003   | Y         |

  @verification_movement_label_field_blind_receiving
  Scenario Outline: Returns receiving verification of movement label screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      | 95580085370650011050230212341238 | 0000838629 | REC003   |

  @receiving_returns_verify_due_receipt_date_upi_management
  Scenario Outline: Returns receiving verification of ASN in the UPI management
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    And I check the inventory for transaction update
    When I navigate to UPI Management screen
    And I search with ASN in UPI Management screen
    Then the due date and receipt date should be displayed for the ASN

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008387380077010083873800300 | 0000838738 | REC003   | Y         |

  @receiving_returns_mixed_stock
  Scenario Outline: Verify receiving with URRN holds different dept UPC and mixed stock
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order with mixed stock at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850006546430077010065464301200 | 0000654643 | REC003   | Y         |

  @receiving_returns_single_ASN_multiple_URRN
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

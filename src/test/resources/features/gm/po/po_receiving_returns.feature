@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @receiving_returns_footwear @po @complete
  Scenario Outline: Returns receiving for Footwear with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode |
      #| 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      #| 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      #| 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      #| 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
      | 58850008191683077010081916830400 | 00008191683 | REC003   | N         | DMGD     |

  @receiving_returns_with_partset
  Scenario Outline: Returns receiving for Part set without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset |
      | 58850006276460077010062764600400 | 0000627646 | REC003   | Y         |       2 |

  @receiving_returns_with_partset_and_lockcode
  Scenario Outline: Returns receiving for Part set with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset | LockCode |
      | 58850006476470077010064764700400 | 0000647647 | REC003   | N         |       2 | DMGD     |

  #| 58850006376310077010063763100400 | 0000637631 | REC003   | Y         |       1 | IMPSET   |
  #| 58850006576270077010065762700400 | 0000657627 | REC003   | N         |       1 | IMPSET     |
  @receiving_returns_qty_singles_verfication
  Scenario Outline: Returns receiving verification for number of singles per UPC
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008386250077010083862500300 | 0000838625 | REC003   | Y         |

  @verification_movement_label_field_blind_receiving
  Scenario Outline: Returns receiving verification of movement label screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with movement label enabled
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location |
      | 95580085370650011050230212341236 | 0000838627 | REC003   |

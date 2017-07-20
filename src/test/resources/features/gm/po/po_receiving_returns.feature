@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @receiving_returns_footwear @po @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type with lock code
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
      | 58850008091680077010080916800400 | 00008091681 | REC003   | N         | DMGD     |

  @receiving_returns_with_partset
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset |
      | 58850006276450077010062764500400 | 0000627645 | REC003   | Y         |       2 |

  @receiving_returns_with_partset_and_lockcode
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type with lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>" and partset "<Partset>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | Partset | LockCode |
      | 58850006276450077010062764500400 | 0000627645 | REC003   | Y         |       2 | DMGD     |
      | 58850006276450077010062764500400 | 0000627645 | REC003   | N         |       2 | IMPSET   |
      | 58850006276450077010062764500400 | 0000627645 | REC003   | Y         |       1 | DMGD     |
      | 58850006276450077010062764500400 | 0000627645 | REC003   | N         |       1 | DMGD     |

  @receiving_returns_qty_singles_verfication
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I receive all skus for the purchase order at "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008386240077010083862400300 | 0000838624 | REC003   | Y         |

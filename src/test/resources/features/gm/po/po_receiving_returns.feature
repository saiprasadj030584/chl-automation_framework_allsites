@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @po_receiving_returns_footwear @po @complete
  Scenario Outline: Receiving for Returns type for footwear
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode   |
      | 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      | 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      | 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      | 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
      
      
      @po_receiving_returns_partset @po @wip
  Scenario Outline: Receiving for Returns type for Part set
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN         | Location | Condition | LockCode   |
      | 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      | 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      | 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      | 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
      

@purchase_order_receiving_returns
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @po_receiving_returns_footwear @po @inpr
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId | ASN | Location | Condition | LockCode |

  # | 58850006486180077010064861800200 | 00006486181 | REC003   | Y         | IMPERFECT |
  #| 58850001231330077010012313300600 | 0000123133 | REC003   | Y         | IMPERFECT |
  #| 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
  #| 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
  #| 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
  #| 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |
  @returns_receiving @returns @wipr
  Scenario Outline: Returns receiving process in JDA WMS for Boxed type upc with single supplier without lock code
    Given the UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for normal upc with perfect condition "<Condition>" and lockcode "<LockCode>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lock code "<LockCode>"

    Examples: 
      | PalletId                         | ASN        | Location | Condition | LockCode |
      | 58850004385640077010027343300600 | 0000468916 | REC003   | N         | DMGD     |
      #| 58850002754330077010027343300600 | 0000254336 | REC003   | N         | DMG      |
      #| 58850002734330077010027343300600 | 0000214336 | REC003   | N         | DMG      |
      #| 58850002634330077010026343300600 | 0000234336 | REC003   | N         | DMG      |
      #| 58850002232330077010022323300600 | 0000223233 | REC003   | Y         |          |
      #| 58850001231330077010012313300600 | 0000123133 | REC003   | Y         |          |
      #| 58850006486180077010064861800200 | 00006486181 | REC003   | Y         | IMPERFECT |
      #| 58850005786180077010057861800100 | 00005786181 | REC003   | Y         | IMPERFECT  |
      #| 58850007286180077010072861800100 | 00007286181 | REC003   | N         | IMPERFECT  |
      #| 58850006086180077010060861800100 | 00006086181 | REC003   | Y         | SINGLESHOE |
      #| 58850007186180077010071861800100 | 00007186181 | REC003   | N         | SINGLESHOE |

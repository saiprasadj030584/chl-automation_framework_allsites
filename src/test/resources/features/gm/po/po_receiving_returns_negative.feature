@receiving_returns_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @validate_quantity_field
  Scenario Outline: Returns receiving with invalid quantity
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    When I receive all skus for the purchase order at "<Location>" with incorrect quantity "<Quantity>"
    Then The invalid quantity error message should be displayed on the screen

    Examples: 
      | PalletId                         | ASN         | Location | Quantity |
      | 58850008191684077010081916840400 | 00008191684 | REC001   |        2 |

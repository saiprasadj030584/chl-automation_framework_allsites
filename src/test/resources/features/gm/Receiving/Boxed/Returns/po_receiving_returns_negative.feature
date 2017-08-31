@receiving_returns_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @validate_quantity_field
  Scenario Outline: Returns receiving with invalid quantity
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    When I receive all skus for the returns order at "<Location>" with incorrect quantity "<Quantity>"
    Then the invalid quantity error message should be displayed on the screen

    Examples: 
      | PalletId                         | ASN         | Location | Quantity |
      | 58850008191685077010081916850400 | 00008191685 | REC001   |        2 |
      
      @boxed_receiving_returns_validate_quantity_field @returns @boxed @receiving @complete
  Scenario Outline: Verify quantity field by providing different input in the blind receiving screen
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    When I receive all skus for the returns order at "<Location>" with incorrect quantity "<Quantity>"
    Then the invalid quantity error message should be displayed on the screen

    Examples: 
      | PalletId                         | ASN         | Location | Quantity |
      | 58850008191685077010081916850400 | 00008191685 | REC001   |        2 |

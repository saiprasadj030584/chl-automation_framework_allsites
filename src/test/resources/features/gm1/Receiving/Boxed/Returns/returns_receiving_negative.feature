@receiving_returns_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_validate_quantity_field @returns @boxed @receiving @complete @ds
  Scenario: Verify quantity field by providing different input in the blind receiving screen
    Given the UPI and ASN should be in "Released" status
    When I receive all skus for the returns order at "REC001" with incorrect quantity "2"
    Then the invalid quantity error message should be displayed on the screen

  @boxed_receiving_returns_invalid_URRN @returns @boxed @receiving @complete @ds
  Scenario Outline: Return receiving validation with invalid URRN
    Given I have an invalid UPI
    When I blind receive the invalid upi
    Then the URN status should be displayed as URRN does not exist

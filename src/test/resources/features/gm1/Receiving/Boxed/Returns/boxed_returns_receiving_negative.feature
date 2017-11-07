@boxed_receiving_returns_negative
Feature: Boxed - Returns - Receiving - Negative validations
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_returns_verify_quantity_field_by_providing_different_input_in_the_blind_receiving_screen @returns @boxed @receiving @complete @ds
  Scenario: Verify quantity field by providing different input in the blind receiving screen
    Given the UPI and ASN should be in "Released" status
    When I receive all skus for the returns order at "REC001" with incorrect quantity "2"
    Then the invalid quantity error message should be displayed on the screen

  @boxed_receiving_returns_urrn_entered_in_the_receiving_screen_which_is_not_in_the_system_negative_test @returns @boxed @receiving @complete @ds @no_ds
  Scenario: URRN entered in the receiving screen which is not in the system_negative test
    Given I have an invalid UPI
    When I blind receive the invalid upi
    Then the URN status should be displayed as URRN does not exist

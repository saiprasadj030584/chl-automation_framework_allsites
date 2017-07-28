@purchase_order
Feature: Purchase order receiving with invalid URRN
  As a warehouse user
  I can receive articles with invalid URRN

  @return_receiving_invalid_URRN
  Scenario Outline: Return receiving validation with invalid URRN
    Given I have an invalid UPI "<PalletId>"
    When I blind receive the invalid upi "<PalletId>"
    Then the URN status should be displayed as URRN does not exist

    Examples: 
      | PalletId                         |
      | 58850001245610954910013456100610 |

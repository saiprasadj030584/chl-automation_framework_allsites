@purchase_order
Feature: Purchase order receiving with invalid URRN
  As a warehouse user
  I can receive articles with invalid URRN

  @return_receiving
  Scenario Outline: return receiving with invalid URRN
    Given i have UPI "<PalletId>"
    When i blind receive all upi "<PalletId>" 
    Then the URN status should be displayed as URRN does not exist

    Examples: 
      | PalletId                         | 
      | 58850001245610954910013456100610 | 

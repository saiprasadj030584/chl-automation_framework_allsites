@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @po_putaway_fsv_field_validation
  Scenario Outline: Putaway process - Field Validation in JDA WMS for FSV PO - Boxed
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be received at location  "<Location>" and site id "<SiteID>"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location

    #And I proceed without entering quantity
    #Then the error message should be displayed as invalid quantity exception
    Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300100914 | REC001   |   5649 |

  @po_putaway_returns_field_validation
  Scenario Outline: Putaway process - Field Validation in JDA WMS for Returns PO - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

    Examples: 
         | PalletId                         | ASN        | Location | Condition|
         | 58850008386250077010083862500300 | 0000838625 | REC003   | Y        |

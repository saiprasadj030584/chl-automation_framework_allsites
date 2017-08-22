@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @po_putaway_fsv_field_validation
  Scenario Outline: Putaway process - Field Validation in JDA WMS for FSV PO - Boxed
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be received at location "<Location>" and site id "<SiteID>"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
     Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300100914 | REC001   |   5649 |

  @po_putaway_returns_quantity_validation
  Scenario Outline: Putaway process - Quantity Field Validation in JDA WMS for Returns PO - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering quantity for returns
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850008286250077010083862500366 | 0000838662 | REC003   | Y         |   5885 |

  @po_putaway_returns_location_validation
  Scenario Outline: Putaway process - Location Field Validation in JDA WMS for Returns PO - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering location for returns
    Then the warning message should be displayed for returns
    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004134450077210063862500366 | 0000765627 | REC003   | Y         |   5885 |
      
       @boxed_fsv_putaway_location_full @boxed @fsv @putaway
  Scenario Outline: Validate Putaway Logic for receiving singles when locations full
     Given the FSV PO "<PreAdviceID>" of type "Boxed" should be received at location "<Location>" and site id "<SiteID>"
     When I choose normal putaway
     And I proceed by entering less quantity for FSV
     Then the ITL should be generated for putaway stock in inventory transaction
     Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300100918| REC001   |   5649 |
     
    

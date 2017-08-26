@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @returns_boxed_putaway_to_preferred_aisle @returns @boxed @putaway
  Scenario Outline: Validate Returns Putaway to preferred aisle
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway for returns
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004164350077210083862500366 |0000765624| REC003   | Y         |   5885 |
      
         @boxed_returns_putaway_location_override @boxed @idt @putaway
    Scenario Outline: Validate Override Putaway Location
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
     When I choose normal putaway
     And I proceed by overriding the location  "<Location>"
     And the goods receipt should be generated for putaway stock in inventory transaction for override
     
     Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004143340077210083862500366 |0000765569| REC003   | Y         |   5885 |
      
  
      @returns_boxed_putaway_location_full @boxed @returns @boxed @putaway
  Scenario Outline:Validate Putaway Logic for receiving singles when locations full
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
   # When I choose existing relocate
   # And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by enterin less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction
    
      Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      |58850004254250077210083862500362 | 0000765567 | REC003   | Y         |   5885 |
      
      @po_putaway_returns_location_validation
  Scenario Outline: Putaway process - Location Field Validation in JDA WMS for Returns PO - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering location for returns
    Then the warning message should be displayed for returns
    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004134450077210063862500366 | 0000765627 | REC003   | Y         |   5885 |
      
       @po_putaway_returns_quantity_validation
  Scenario Outline: Putaway process - Quantity Field Validation in JDA WMS for Returns PO - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering quantity for returns
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850008286250077010083862500366 | 0000838662 | REC003   | Y         |   5885 |

  
      
      
    

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
      
      @returns_boxed_putaway_location_full @boxed @returns @boxed @putaway
  Scenario Outline:Validate Putaway Logic for receiving singles when locations full
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity
    Then the ITL should be generated for putaway relocated stock in inventory transaction
    
      Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004175450077210083862500366 | 0000765655 | REC003   | Y         |   5885 |
      
    

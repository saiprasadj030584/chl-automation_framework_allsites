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

 
       @boxed_fsv_putaway_location_full @boxed @fsv @putaway
  Scenario Outline: Validate Putaway Logic for receiving singles when locations full
     Given the FSV PO "<PreAdviceID>" of type "Boxed" should be received at location "<Location>" and site id "<SiteID>"
     When I choose normal putaway
     And I proceed by entering less quantity for FSV
     Then the ITL should be generated for putaway stock in inventory transaction
     Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300100918| REC001   |   5649 |
      
      
         @boxed_fsv_putaway_location_override @boxed @idt @putaway
    Scenario Outline: Validate Override Putaway Location
     Given the FSV PO "<PreAdviceID>" of type "Boxed" should be received at location "<Location>" and site id "<SiteID>"
     When I choose normal putaway
     And I proceed by overriding the location  "<Location>"
     And the goods receipt should be generated for putaway stock in inventory transaction for override
     
      Examples: 
      | PreAdviceID | Location | SiteID |
      |25300100108| REC001   |   5649 |
      
     
    

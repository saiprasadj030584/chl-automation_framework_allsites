@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @date
  Scenario Outline: Putaway process in JDA WMS for Boxed type
    Given the FSV PO "<PreAdviceID>" of type "Hanging" should be in "Released" status at site id "<SiteID>"
    Given the FSV PO "<PreAdviceID>" of type "Hanging" should be received at location "<Location"> and site id "<SiteID>"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PreAdviceID  | Location | SiteID |
      | PO2010002240 | REC001   |   5649 |

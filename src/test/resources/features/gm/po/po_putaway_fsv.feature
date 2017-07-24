@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

@date
  Scenario Outline: Putaway process in JDA WMS for Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" details should be displayed
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

     Examples: 
      | PreAdviceID  | SiteId |
      | PO2010002240 |   5649 |
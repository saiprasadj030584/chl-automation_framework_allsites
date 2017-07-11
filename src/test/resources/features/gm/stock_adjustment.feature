@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to do stock adjustments 
  So that I can allocate

  @stock_adjustment
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | ReasonCode | SiteId|Location   |
      | Dirty      |5649|1AA103 |
      #| DMIT       | 1AA103 |
      #| EXPD       | 1AA103 |
      #| FOUND      | 1AA103 |
      #| IE         | 1AA103 |
      #| INCOMPLETE | 1AA103 |
      #| LOST       | 1AA103 |
      #| SAMPLES    | 1AA103 |

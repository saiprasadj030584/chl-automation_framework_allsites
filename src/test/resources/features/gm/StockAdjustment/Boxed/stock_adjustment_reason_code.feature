@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to adjust the stock in inventory

  @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_dirty @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | DIRTY      |
      
      @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_dmit @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | DMIT       |
      
     @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_expd @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | EXPD       |
      
     @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_found @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | FOUND      |
      
      @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_incomplete @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | INCOMPLETE |
      
       @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_lost @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | LOST       |
      
       @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_samples @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | SAMPLES    |

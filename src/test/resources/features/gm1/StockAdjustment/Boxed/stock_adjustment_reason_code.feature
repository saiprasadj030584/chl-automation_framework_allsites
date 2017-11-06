@stock_adjustments
Feature: Boxed - Stock adjustments with Reason Code
  As a warehouse user
  I want to adjust the stock in inventory

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_dirty @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | Dirty      |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_dmit @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | DMIT       |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_expd @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | EXPD       |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_found @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | FOUND      |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_incomplete @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | INCOMPLETE |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_lost @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | LOST       |   5649 |

  @jenkins_analysis @stock_adjustment @boxed @boxed_stock_adjustment_stock_adjustment_reason_code_samples @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | SiteId |
      | 1AA103   | SAMPLES    |   5649 |

  @boxed @jenkins_analysis @stock_adjustment @boxed_stock_adjustment_stock_adjustment_reason_code_stock_count @complete @ds @jenkins1 @no_ds
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode  | SiteId |
      | 1AA103   | Stock Count |   5649 |

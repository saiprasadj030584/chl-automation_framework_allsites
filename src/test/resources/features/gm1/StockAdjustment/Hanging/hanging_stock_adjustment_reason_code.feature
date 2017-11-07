@hanging_stock_adjustments_reason_code
Feature: Hanging - Stock adjustments with Reason Code
  As a warehouse user
  I want to adjust the stock in inventory

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_dirty_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as Dirty
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | Dirty      |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_dmit_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as DMIT
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | DMIT       |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_expd_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as EXPD
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | EXPD       |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_found_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as FOUND
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | FOUND      |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_incomplete_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as INCOMPLETE
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | INCOMPLETE |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_lost_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as LOST
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | LOST       |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_samples_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as SAMPLES
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | SAMPLES    |

  @hanging @stock_adjustment @stock_adjustment_reason_code @hanging_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_stock_count_ @complete @ds @no_ds @group_3
  Scenario Outline: Do stock adjustment by selecting reason code as SC
    Given I have a sku of type "Hanging" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | SC         |

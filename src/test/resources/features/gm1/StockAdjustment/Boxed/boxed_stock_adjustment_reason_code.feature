@boxed_stock_adjustments_reason_code
Feature: Boxed - Stock adjustments with Reason Code
  As a warehouse user
  I want to adjust the stock in inventory

  @jenkinsA @jenkinsrun  @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_dirty @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as Dirty
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | 
      | 1AA103   | Dirty      |   

  @jenkinsA @jenkinsgm @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_dmit @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as DMIT
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | 
      | 1AA103   | DMIT       |  

  @jenkinsA @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_expd @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as EXPD
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | EXPD       | 

  @jenkinsA @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_found @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as FOUND
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | 
      | 1AA103   | FOUND      |  

  @jenkinsA @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_incomplete @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as INCOMPLETE
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | 
      | 1AA103   | INCOMPLETE |

  @jenkinsA @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_lost @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as LOST
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode | 
      | 1AA103   | LOST       |  

  @jenkinsA @stock_adjustment @boxed @unique_boxed_stock_adjustment_stock_adjustment_reason_code_samples @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as SAMPLES
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode |
      | 1AA103   | SAMPLES    |  

  @boxed @jenkinsA @stock_adjustment @unique_boxed_stock_adjustment_stock_adjustment_reason_code_stock_count @complete @ds @jenkins1 @no_ds
  Scenario Outline: Do stock adjustment by selecting reason code as SC
    Given I have a sku of type "Boxed" to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id at specific transaction time
    Then the reason code should be updated for specific transaction time

    Examples: 
      | Location | ReasonCode  | 
      | 1AA103   | SC |  

@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to do stock adjustments 
  So that I can allocate

  @stock_adjustment
  Scenario Outline: stock adjustment process
    Given I have a Skuid and pack config
    When I navigate to stock adjustments page
    When I create a new stock with siteid "5649" and location "1AA103"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query page
    When I choose the code as "<Code>" and search the sku id
    Then the reason code should be updated

    Examples: 
      | ReasonCode | Code       |
      | Dirty      | Adjustment |

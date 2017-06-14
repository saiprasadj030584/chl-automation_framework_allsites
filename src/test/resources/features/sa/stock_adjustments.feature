@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to do stock adjustments 
  So that I can

  @complete @sa
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have a tag id with the "<Status>" status
    When I navigate to stock adjustments page
    When I search the inventory details
    Then the record should be displayed in the results
    When I navigate to create or modify tab
    Then the product details should be displayed from inventory
    When I "<AdjustmentType>" the quantity on hand
    And I choose the reason code as "<ReasonCode>"
    Then the stock adjustments home page should be displayed
    Then inventory transaction detail should have the updated quantity and uploaded filename for the "<ReasonCode>"

    Examples: 
      | Status   | AdjustmentType | ReasonCode           |
      | Locked   | Decrement      | Damaged by Warehouse |
      | Unlocked | Decrement      | Damaged by Warehouse |
      | Locked   | Decrement      | Expired Stock        |
      | Unlocked | Decrement      | Expired Stock        |
      | Locked   | Decrement      | Head Office          |
      | Unlocked | Decrement      | Head Office          |
      | Locked   | Decrement      | Hampers Stock        |
      | Unlocked | Decrement      | Hampers Stock        |
      | Locked   | Decrement      | Receiving Correction |
      | Unlocked | Decrement      | Receiving Correction |
      | Locked   | Increment      | Receiving Correction |
      | Unlocked | Increment      | Receiving Correction |
      | Locked   | Decrement      | Infestation          |
      | Unlocked | Decrement      | Infestation          |
      | Locked   | Decrement      | Outlets Stock        |
      | Unlocked | Decrement      | Outlets Stock        |
      | Locked   | Increment      | Returns from RDC     |
      | Unlocked | Increment      | Returns from RDC     |
      | Locked   | Decrement      | Stock Count          |
      | Unlocked | Decrement      | Stock Count          |
      | Locked   | Increment      | Stock Count          |
      | Unlocked | Increment      | Stock Count          |
      | Locked   | Decrement      | Returns to Supplier  |
      | Unlocked | Decrement      | Returns to Supplier  |

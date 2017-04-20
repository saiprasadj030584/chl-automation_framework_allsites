@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to do stock adjustments 
  So that I can

  @wip
  Scenario Outline: Stock adjustments of a locked/unlocked product with different reason codes
    # Given I have logged in as warehouse user in JDA dispatcher food application
   # And I am on inventory query page
    And I have the tag id "<TagID>" with "<Status>" status
    When I navigate to stock adjustments page
    When I search the inventory details
    Then the record should be displayed in the results
    When I navigate to create or modify tab
    Then the product details should be displayed as expected from inventory
    When I "<AdjustmentType>" the quantity on hand
    And I choose the reason code as "<ReasonCode>"
    Then the stock adjustments home page should be displayed
    When I navigate to inventory transaction query
    And I search tag id "<TagID>" and code as "Adjustment"
    Then I should see the original quantity and updated quantity in the general tab
    When I navigate to miscellaneous tab
    Then I should see the readon code as "<ReasonCode>"
    When I navigate to miscellaneous2 tab
    Then I should see the uploaded filename

    Examples: 
      | TagID      | Status   | AdjustmentType | ReasonCode           |
      | 6327637212 | Locked   | Decrement      | Damaged by Warehouse |
      #| 2000160302 | Unlocked | Decrement      | Damaged by Warehouse |
      #| 6327637212 | Locked   | Decrement      | Expired Stock        |
      #| 2000160302 | Unlocked | Decrement      | Expired Stock        |
      #| 6327637212 | Locked   | Decrement      | Head Office          |
      #| 2000160302 | Unlocked | Decrement      | Head Office          |
      #| 6327637212 | Locked   | Decrement      | Hampers Stock        |
      #| 2000160302 | Unlocked | Decrement      | Hampers Stock        |
      #| 6327637212 | Locked   | Decrement      | Receiving Correction |
      #| 2000160302 | Unlocked | Decrement      | Receiving Correction |
      #| 6327637212 | Locked   | Increment      | Receiving Correction |
      #| 2000160302 | Unlocked | Increment      | Receiving Correction |
      #| 6327637212 | Locked   | Decrement      | Infestation          |
      #| 2000160302 | Unlocked | Decrement      | Infestation          |
      #| 6327637212 | Locked   | Decrement      | Outlets Stock        |
      #| 2000160302 | Unlocked | Decrement      | Outlets Stock        |
      #| 6327637212 | Locked   | Increment      | Returns from RDC     |
      #| 2000160302 | Unlocked | Increment      | Returns from RDC     |
      #| 6327637212 | Locked   | Decrement      | Stock Count          |
      #| 2000160302 | Unlocked | Decrement      | Stock Count          |
      #| 6327637212 | Locked   | Increment      | Stock Count          |
      #| 2000160302 | Unlocked | Increment      | Stock Count          |
      #| 6327637212 | Locked   | Decrement      | Returns to Supplier  |
      #| 2000160302 | Unlocked | Decrement      | Returns to Supplier  |

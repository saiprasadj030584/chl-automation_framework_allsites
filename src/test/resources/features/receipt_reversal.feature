@receipt_reversal
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @wip01
  Scenario: Lock the inventory from unlocked status
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I have received tag Id "7080001006"
    When I navigate to receipt reversal page
    And select Receipt Type as "Pre-Advice" and enter the tag id as "7080001006"
    And I navigate to next screen
    Then corresponding inventory information should be displayed in reversals screen
    When I enter the Qty to reverse as "16" and navigate to finish screen
    And I enter the reason code as "Damaged Stock"
    And proceed to reverse the quantity
    #Then Inventory should be updated with the new updated quantity
    #And "Receipt Reversal" ITL should be generated

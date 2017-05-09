@receipt_reversal
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @wip5
  Scenario: Lock the inventory from unlocked status
    
    Then Inventory should be updated with the new updated quantity
    And the ITL should be generated for the code "receipt reversal"
    When I navigate to miscellaneous tab
    Then I should see the readon code as "DMGD"
    When I navigate to miscellaneous2 tab
    Then the uploaded filename should be displayed
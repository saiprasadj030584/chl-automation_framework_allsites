@receipt_reversal
Feature: Receipt reversal
  As a warehouse user
  I want to return a received stock to the supplier
  So that damaged stocks are not used for allocation

  @complete
  Scenario: Return the damaged stocks to the supplier
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have received tag Id "7080001006"
    When I navigate to receipt reversal page
    And I select receipt type as "Pre-Advice" and enter the tag id as "7080001006"
    And I proceed to next
    Then the inventory details should be displayed in reversals tab
    When I enter the quantity 16 to reverse
    And I proceed to next
    And I select the reason code as "Damaged Stock"
    And I proceed to reverse the quantity
    Then the quantity should be updated for the tag id
    And the "Receipt Reversal" ITL should be generated

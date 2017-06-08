@receipt_reversal
Feature: Receipt reversal
  As a warehouse user
  I want to return a received stock to the supplier
  So that damaged stocks are not used for allocation

  @complete @po @receipt_reversal
  Scenario: Return the damaged stocks to the supplier
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have a tag id received at "INBOUND" zone
    When I navigate to receipt reversal page
    And I select receipt type as "Pre-Advice" and enter the tag id
    And I proceed to next
    Then the inventory details should be displayed in reversals tab
    When I enter the quantity 16 to reverse
    And I proceed to next
    And I select the reason code as "Damaged Stock"
    And I proceed to reverse the quantity
    Then the inventory should be updated with new quantity
    And the ITL should be generated for the code "receipt reversal"
    When I navigate to miscellaneous tab
    Then I should see the readon code as "DMGD"
    When I navigate to miscellaneous2 tab
    Then the uploaded filename should be displayed

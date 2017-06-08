@sto_deallocation
Feature: Deallocate the order
  As a warehouse user
  I want to deallocate the order 
  So that the order can be used for future allocation

  @deallocation @complete @sto
  Scenario Outline: De-allocate the stock transfer
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the order "<OrderID>" should be "Allocated" status
    When I navigate to order management page
    And the summary records page should be displayed
    When I proceed to next
    And I enter the site id as "9771" and order id
    And I proceed to next
    And I select the record in order management page
    And I update the status as "Released"
    And I proceed to complete the deallocation
    Then the order should be in "Released" status

    Examples: 
      | OrderID    |
      | 6600033112 |

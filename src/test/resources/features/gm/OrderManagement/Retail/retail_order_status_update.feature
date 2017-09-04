@Order_Management_Retail_order_Boxed_verify_order_status_update
Feature: verify order status update
  As a warehouse user
  I want to allocate the order 
  So that the order status can be updated in the order management screen

  @boxed_order_management_retail_order_status_update @boxed @order_management @retail @complete
  Scenario Outline: Verify order status updated for each transaction in order management screen and order header
    Given the "<orderId>" of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I allocate the stocks
    When I navigate to order management page
    And I search the "<orderId>"
    Then the record should be displayed
    When I proceed to check the status
    Then the status should be displayed as "Allocated" in order header

    Examples: 
      | orderId    |
      | 4170001469 |

@goh_order_management_idt
Feature: Boxed - IDT - Order Management
  As a warehouse user
  I want to allocate the order 
  So that the order status can be updated in the order management screen

   @jenkinsg @unique_goh_order_management_idt_verify_order_status_updated_for_each_transaction_in_order_management_screen_and_order_header @goh @order_management @idt @complete @ds
  Scenario: Verify order status updated for each transaction in order management screen and order header
   # Given the order of "IDT" should be in "Released" status in order header maintenance
   Given the order id of type "IDT" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    When I navigate to order management page
    And I search the order
    Then the record should be displayed
    When I proceed to check the status
    Then the status should be displayed as "Allocated" in order header

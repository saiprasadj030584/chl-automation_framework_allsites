@flatpack_order_management_idt
Feature: Flatpack - IDT - Order Management
  As a warehouse user
  I want to allocate the order 
  So that the order status can be updated in the order management screen

           
      @jenkinsg @unique_flatpack_order_management_idt_verify_order_status_updated_for_each_transaction_in_order_management_screen_and_order_header @flatpack @order_management @idt @complete
  Scenario Outline: Verify order status updated for each transaction in order management screen and order header
   # Given the "Flatpack" type "<orderId>" of "IDT" should be in "Released" status in order header maintenance
     Given the order id of type "IDT" with "Flatpack" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    When I navigate to order management page
    And I search the order
    Then the record should be displayed
    When I proceed to check the status
    Then the status should be displayed as "Allocated" in order header

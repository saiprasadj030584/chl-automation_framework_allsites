@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation

  @order_header_Bulk_pick_order
  Scenario: Load the STO order details
    Given the bulk pick order "5000011419" should be "Realeased" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs

 @order_header_table_Store_order
 Scenario: Load the STO order details
    Given the STO "6600033369" should be "Realeased" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the customer should have CSSM flag updated in address maintenance table
    
    @order_header_table_International_order
 Scenario: Load the international order details
    Given the INT "6646215738" should be "Realeased" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the order should have hub details in hub address tab
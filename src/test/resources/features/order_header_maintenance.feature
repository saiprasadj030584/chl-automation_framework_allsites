@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation

  @order_header_table
  Scenario: Load the STO order details
    Given the bulk pick order "5000011419" should be "Realeased" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs

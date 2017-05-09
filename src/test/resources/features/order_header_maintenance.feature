@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation

@order_header_table
   Scenario: Load the STO order details
    Given the STO "0030229923" should be "In Realeased" status with type "RDC" and have order date, site id, number of lines,created by,more task status,order time,creation date and creation time in the order header maintenance table
    And the STO should have delivery details
    And the STO should have delivery address details
    And the STO should have user defined details
    

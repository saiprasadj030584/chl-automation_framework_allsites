@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation
@wip
  Scenario: Load the STO order details
    Given the bulk pick order "5000011419" should be "Realeased" status, "RDC" type, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs

 @jz
 Scenario: Load the STO order details
    Given the bulk pick order "5000011419" should be "Realeased" status, "STR" type, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the customer should have CSSM flag updated in address maintenance table
    
   @complete
 Scenario: Load the international order details
    Given the bulk pick order "5000011419" should be "Realeased" status, "INT SEA" type, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the order should have hub details in hub address tab
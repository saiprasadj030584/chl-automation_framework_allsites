@receive_STO_order
Feature: Receive STO order
  As a warehouse user
  I want to receive the STO 
  So that I can be used for allocation
@complete
  Scenario: Load the STO order details
    Given the bulk pick order "5000011419" should be "Released" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs

 @complete
 Scenario: Load the STO order details
    Given the STO "6600033370" should be "Released" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the customer should have CSSM flag updated in address maintenance table
    
   @complete
 Scenario: Load the international order details
    Given the INT "6646215741" should be "Released" status, order details in the order header maintenance table
    And the order should have delivery address details in delivery address tab
    And the order should have delivery details in delivery details and user defined tabs
    And the order should have hub details in hub address tab
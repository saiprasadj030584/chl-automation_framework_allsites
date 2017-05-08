 @STO_Data_Validation
Feature: check the oredr details
  As a warehouse user
  I want to receive the purchase order 
  So that I can be used for allocation
 
 @wip4
 Scenario: check the order details in order line screen
 Given I navigate to order Line Maintenance Page
 And the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each  line items

@Exit_Picking
Feature: Orders_Picking
As a user in Exit DC
Order should be autoalocated
so that I can pick and dispatch

@SN1_Picking_Order_Manual_Franchise_Boxed
  Scenario: SN1_Picking Order Manual Franchise Boxed
  Given Logging in as warehouse user in Exit application
  Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
  And Navigate to Move Task management Screen to verify Order Allocated status
  #And Validation of List Id generated with prefix as MANB*



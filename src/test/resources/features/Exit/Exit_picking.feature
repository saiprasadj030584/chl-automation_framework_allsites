@Exit_Picking
Feature: Orders_Picking
As a user in Exit DC
Order should be autoalocated
so that I can pick and dispatch

@SN1_Picking_Order_Manual_Franchise_Boxed 
  Scenario: SN1_Picking Order Manual Franchise Boxed
  #Given Logging in as warehouse user in Exit application
  Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
  And Navigate to Move Task management Screen to verify Order Allocated status
  And Validation of List Id generated with prefix as MANB
  
  
@SN2_Picking_Order_Manual_IDT 
  Scenario: SN1_Picking Order Manual IDT
  #Given Logging in as warehouse user in Exit application
  Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
  And Navigate to Move Task management Screen to verify Order Allocated status for IDT
  And Validation of List Id generated with prefix as IDT
  
  
 @SN3_Picking_FSV_Cross_Dock_and_Receiving
 Scenario: SN3_Picking FSV Cross Dock
 Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542"
 And Navigate to Move Task management Screen to verify Order Allocated status for FSV Crossdock
 And Validation of List Id generated with prefix as FSVB
 Then I login as warehouse user in putty
 And I select user directed option in main menu 
 And I select Receiving menu
 And I enter URN and Bel 
 
 
  @SN4_Picking_ASN_Cross_Dock
 Scenario: SN3_Picking FSV Cross Dock
 Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542"
 And Navigate to Move Task management Screen to verify Order Allocated status for ASN Crossdock
 And Validation of List Id generated with prefix as ASNB
 
 @SN05_Picking_in_Rdt_MANB
 Scenario: SN1_Picking Order Manual Franchise Boxed
  #Given Logging in as warehouse user in Exit application
  Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
  And Navigate to Move Task management Screen to verify Order Allocated status
  And Validation of List Id generated with prefix as MANB
  Then I login as warehouse user in putty
  And I select user directed option in main menu
  And I select picking with container pick
  Then I should be directed to pick entry page
  And I enter ListId and TagId
  
  @SN06_Picking_in_Rdt_IDT
  Scenario: SN1_Picking Order Manual IDT
  #Given Logging in as warehouse user in Exit application
  Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
  And Navigate to Move Task management Screen to verify Order Allocated status for IDT
  And Validation of List Id generated with prefix as IDT
  Then I login as warehouse user in putty
  And I select user directed option in main menu
  And I select picking with container pick
  Then I should be directed to pick entry page
  And I enter ListId and TagId
 
  
  
  
  


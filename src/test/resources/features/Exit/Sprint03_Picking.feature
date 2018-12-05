@Exit_Picking @Boxed_Outbound @Picking_Boxed_Outbound
Feature: Orders_Picking
  As a user in Exit DC
  Order should be Auto Allocated
  so that I can Pick and Dispatch

  @TC01_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Boxed 
  Scenario: Validate Pick list id generated for an order-Manual Franchise Boxed
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB

  @TC02_Validate_Pick_list_id_generated_for_an_order_Manual_IDT  
  Scenario: Validate Pick list id generated for an order-Manual IDT
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT

  @TC03_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order  
  Scenario: Validate Pick list id generated for a FSV Cross dock order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier

  @TC04_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order 
  Scenario: Validate Pick list id generated for a cross dock ASN order
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN
    And I navigate to Order header screen to verify the status in Ready to Load

  @TC05_Validate_Picking_process_for_Manual_Franchise_order 
  Scenario: Validate Picking process for Manual Franchise order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId
    And I navigate to Order header screen to verify the status in Ready to Load

  @TC06_Validate_Picking_process_for_Manual_IDT_order @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario: Validate Picking process for Manual IDT order(Transfer order)
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId for IDT
    And I navigate to Order header screen to verify the status in Ready to Load

  @TC07_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order 
  Scenario: Validate the auto picking process for the Cross dock FSV order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And I navigate to Order header screen to verify the status in Ready to Load
    
 @Picking @TC09_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order
  Scenario Outline: Negative_Path_Validate scanning incorrect pallet id for a Manual Order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter Invalid List Id "<List_Id>"
    And I validate Error message is displayed
	Examples:
	|List_Id|
	|MANB00001234|    
	
	@Picking @TC010_Validate_32_digit_URN_generation_after_picking
  Scenario: Validate 32 digit URN generation after picking
  	Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    And I should be directed to pick entry page
    And I enter ListId and TagId for IDT
    And I navigate to Order header screen to verify the status in Ready to Load
    And Navigate to Order Container screen
    And Click on Query
    And Enter Order Id
    And click execute
    And Validate the 32 digit URN is generated
    
   @Picking @TC011_Create_a_consignment_or_Load_label
   Scenario: Create a consignment or Load label
    And Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    
    @Picking @TC012_Create_Consignment_Drop
   Scenario: Create a consignment or Load label
    And Login to JDA Dispatcher web screen
    And Go to consignment drop maintainance screen
    
    
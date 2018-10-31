@Exit_Picking @Boxed–Outbound @Picking-Boxed–Outbound
Feature: Orders_Picking
  As a user in Exit DC
  Order should be autoalocated
  so that I can pick and dispatch

  @TC01_Validate_Pick_list_id_generated_for_an_order-Manual_Franchise_Boxed @Boxed–Outbound
  Scenario: Validate Pick list id generated for an order-Manual Franchise Boxed
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB

  @TC02_Validate_Pick_list_id_generated_for_an_order-Manual_IDT @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario: Validate Pick list id generated for an order-Manual IDT
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT

  @TC03_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario: Validate Pick list id generated for a FSV Cross dock order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier

  @TC04_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario: Validate Pick list id generated for a cross dock ASN order
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN
    And I navigate to Order header screen to verify the status in Ready to Load

  @TC05_Validate_Picking_process_for_Manual_Franchise_order @Boxed–Outbound @Picking-Boxed–Outbound
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

  @TC07_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order @Boxed–Outbound @Picking-Boxed–Outbound @Picking-Boxed–Outbound
  Scenario: Validate the auto picking process for the Cross dock FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And I navigate to Order header screen to verify the status in Ready to Load
    
 

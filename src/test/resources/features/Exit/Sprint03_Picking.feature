@Exit_Picking @Boxed_Outbound @Picking_Boxed_Outbound
Feature: Orders_Picking
  As a user in Exit DC
  Order should be Auto Allocated
  so that I can Pick and Dispatch

  @TC01_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Boxed
  Scenario Outline: Validate Pick list id generated for an order-Manual Franchise Boxed
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC02_Validate_Pick_list_id_generated_for_an_order_Manual_IDT
  Scenario Outline: Validate Pick list id generated for an order-Manual IDT
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC03_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order
  Scenario Outline: Validate Pick list id generated for a FSV Cross dock order
     Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC04_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order
  Scenario Outline: Validate Pick list id generated for a cross dock ASN order
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC05_Validate_Picking_process_for_Manual_Franchise_order
  Scenario Outline: Validate Picking process for Manual Franchise order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC06_Validate_Picking_process_for_Manual_IDT_order @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario Outline: Validate Picking process for Manual IDT order(Transfer order)
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId for IDT
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @TC07_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order
  Scenario Outline: Validate the auto picking process for the Cross dock FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Picking @TC09_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order
  Scenario Outline: Negative_Path_Validate scanning incorrect pallet id for a Manual Order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter Invalid List Id "<List_Id>"
    And I validate Error message is displayed

    Examples: 
      | List_Id      | SKU                |
      | MANB00001234 | 000000000021071852 |

  @Picking @TC010_Validate_32_digit_URN_generation_after_picking
  Scenario Outline: Validate 32 digit URN generation after picking
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
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

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Picking @TC011_Create_a_consignment_or_Load_label
  Scenario Outline: Create a consignment or Load label
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And Select trailer type to reflect Hazardous and Repack status
    And click execute
    And validate the record is saved

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Picking @TC012_Create_Consignment_Drop
  Scenario Outline: Create a consignment or Load label
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    And Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And Select trailer type to reflect Hazardous and Repack status
    And click execute
    And Go to consignment drop maintainance screen
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter consignment
    And Enter chamber and Address Id
    Then click execute
    And validate the record is saved

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Sorting @TC013_Validate_adding_URN_to_pallet_id
  Scenario Outline: Validate adding urn to pallet id
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for sortation in Direct Receiving

    #And I navigate to Order header screen to verify the status in Ready to Load
    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Picking @TC16_Negative_Path_Scan_incorrect_UPC_during_picking_Manual_Order
  Scenario Outline: Negative_Path_Validate scanning incorrect pallet id for a Manual Order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter Invalid List Id "<List_Id>"
    And I validate Error message is displayed

    Examples: 
      | List_Id      | SKU                |
      | IDTB00001234 | 000000000021071852 |


  @Picking @TC017_Negative_Path_Scan_incorrect_To_Location_during_Manual_order_Picking
  Scenario Outline: Negative_Path_Scan incorrect To Location during Manual order Picking
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    And I should be directed to pick entry page
    And I type ListId and TagId
    And I enter Invalid To Location "<ToLocation>"
    And I validate incorrect location message is displayed

    Examples: 
      | SKU                | ToLocation |
      | 000000000021071852 | TURKEY     |

  @Sortation @TC18_Happy_path_sortation_group_validation
  Scenario: Happy path sortation group validation
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click
    And Verify whether the access

  @Sortation @TC19_Happy_path_validate_sortation_functional_access
  Scenario Outline: Happy path sortation group functional access
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click
    And Click on Query
    And Specify the sortation group "<sortationgroup>"
    And Click search
    And Validate Blind receipt, pre-advice receipt, repack

    Examples: 
      | sortationgroup |
      | Sortation      |

  #@Sortation @TC20_Negative_path_validate_sortation_functional_not_found
  #Invalid in frontend application
  @Ordering @TC035_Happy_Path_Validate_FSV_order
  Scenario Outline: Happy_Path_Validate FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZF24"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Ordering @TC036_Happy_Path_Validate_Manual_order
  Scenario Outline: Happy_Path_Validate_Manual_order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZNL1"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Ordering @TC037_Happy_Path_Validate_Cross_dock_order
  Scenario Outline: Happy_Path_Validate Cross dock order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZF24"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Ordering @TC038_Happy_Path_Validate_Manual_STO
  Scenario Outline: Happy_Path_Validate Manual STO
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZNL1"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Trailer @TC039_Validate_trailer_creation
  Scenario: Validate trailer creation
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved

  @Trailer @TC040_Validate_consignment_Trailer_linking
  Scenario: Validate consignment Trailer linking
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    And Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And validate the record is saved 
    And Go to consignment drop maintainance screen
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter consignment
    And Enter chamber and Address Id
    Then click execute
    And validate the record is saved
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved 
    And Go to Consignment Trailer Linking
    And Select Trailer
    And Select Consignment
    And Click next
    And I click on trailer Add button
    And validate Consignment Trailer is linked
    
  @Trailer @TC041_Negative_path_Incorrect_Pallet_for_consignment_trailer_linking
  Scenario: Negative_path_Incorrect Pallet for consignment trailer linking
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And Select trailer type to reflect Hazardous and Repack status
    And click execute
    And validate the record is saved 
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved 
    And Go to Consignment Trailer Linking
    And Select Trailer
    And Select Consignment
    And Click next
    And validate error message is displayed
     
  @Shipdock @TC042_Validate_shipdock_assignment
  Scenario Outline: Validate_shipdock_assignment
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    Then Verify the shipdock field is set

    Examples: 
      | SKU                |
      | 000000000021071852 |
      
   @Ordering @TC043_Validate_Franchise_order_allocation_configuration_Function_Access
   Scenario: Validate_Franchise_order_allocation   
    Given Login to JDA Dispatcher web screen
    And Go to User Group Function Access 
		And Type in Function Access search for text box
		And validate the access is Enabled
		And Go to Site Global Function Access 
		And Type in and validate Function Access search for text box
		
  @Ordering @TC044_Validate_Franchise_Allocation_creation
  Scenario: Validate_Franchise_Allocation_creation  
    Given Login to JDA Dispatcher web screen
    And Go to Allocation algorithm Setup
    And Click next 
    And I select Allocation creation date by zone option
    And I click on Add button
    And type "INBOUND" in location zone
    Then save the Allocation created
    
  @TC45_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Hanging
  Scenario Outline: Validate Pick list id generated for an order-Manual Franchise hanging
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANH

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @TC46_Validate_Pick_list_id_generated_for_an_order_Manual_IDT_hanging
  Scenario Outline: Validate Pick list id generated for an order-Manual IDT_hanging
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @TC47_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order_hanging
  Scenario Outline: Validate Pick list id generated for a FSV Cross dock order_hanging
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @TC48_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order_hanging
  Scenario Outline: Validate Pick list id generated for a cross dock ASN order_hanging
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @complete @TC049_Validate_Picking_process_for_Manual_Franchise_order_for_hanging
  Scenario Outline: Validate Picking process for Manual Franchise order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANH
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Complete @TC50_Validate_Picking_process_for_Manual_IDT_order @Hanging–Outbound @Picking-Hanging–Outbound_for_hanging
  Scenario Outline: Validate Picking process for Manual IDT order(Transfer order)
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter ListId and TagId for IDT
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @complete @TC51_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order_hanging
  Scenario Outline: Validate the auto picking process for the Cross dock FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Picking @TC52_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order_for_hanging
  Scenario Outline: Negative_Path_Validate scanning incorrect pallet id for a Manual Order for hanging
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter Invalid List Id "<List_Id>"
    And I validate Error message is displayed

    Examples: 
      | List_Id      | SKU                |
      | MANB00001234 | 000000000021071851 |

  @Picking @TC53_Validate_32_digit_URN_generation_after_picking_for_hanging
  Scenario Outline: Validate 32 digit URN generation after picking for hanging
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging
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

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Picking @TC60_Negative_Path_Scan_incorrect_UPC_during_picking_Manual_Order
  Scenario Outline: Negative_Path_Validate scanning incorrect pallet id for a Manual Order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    And I enter Invalid List Id "<List_Id>"
    And I validate Error message is displayed
    Examples: 
      | List_Id      | SKU                |
      | IDTH00001234 | 000000000021071851 |

 
 
  @Picking @TC061_Negative_Path_Scan_incorrect_To_Location_during_Manual_order_Picking
  Scenario Outline: Negative_Path_Scan incorrect To Location during Manual order Picking
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with container pick
    And I should be directed to pick entry page
    And I type ListId and TagId
    And I enter Invalid To Location "<ToLocation>"
    And I validate incorrect location message is displayed

    Examples: 

      | SKU                | ToLocation |
      | 000000000021071851 | ISRAIL     |
    

  @Ordering @TC62_Happy_Path_Validate_FSV_order_hanging
  Scenario Outline: Happy_Path_Validate FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZF24"

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Ordering @TC63_Happy_Path_Validate_Manual_order_hanging
  Scenario Outline: Happy_Path_Validate_Manual_order
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZNL1"

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Ordering @TC64_Happy_Path_Validate_Cross_dock_order_hanging
  Scenario Outline: Happy_Path_Validate Cross dock order
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZF24"

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Ordering @TC65_Happy_Path_Validate_Manual_STO_hanging
  Scenario Outline: Happy_Path_Validate Manual STO
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Login to JDA Dispatcher web screen
    And I navigate to order header
    And Click on Query
    And Specify the Order in orderline
    And click execute
    And Navigate to user Defined tab
    Then Verify the delivery type field is set "ZNL1"

    Examples: 
      | SKU                |
      | 000000000021071851 |

      
  
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
    And I navigate to Order header screen to verify the status in Ready to Load

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

  @TC06_Validate_Picking_process_for_Manual_IDT_order @Boxed�Outbound @Picking-Boxed�Outbound
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
  Scenario: Create a consignment or Load label
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
    
  @Picking @TC012_Create_Consignment_Drop
  Scenario: Create a consignment or Load label
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
      
  @Sortation @TC26_Happy_path_Pallet_Consignment_Linking_process
   Scenario Outline: Happy_path_Pallet_Consignment_Linking_process
   Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And configure putty settings
    And I select Inventory transaction option
    And Enter Pallet Id
    And Enter Consignment "CONS040119"
    And Validate the pallet and consignment is linked
    Examples: 
      | SKU                |
      | 000000000021071852 |
   
  @Sortation @TC27_Negative_Path_Incorrect_pallet_consignment_linking
  Scenario Outline: Negative_Path_Incorrect pallet consignment linking
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And configure putty settings
    And I select Inventory transaction option
    And Enter Pallet Id "P1286952"
    And Enter Consignment "CONS7993271218"
    And Validate the pallet and consignment is linked
 		
      
 @Sortation @TC28_Happy_Path_Validate_consignment_id_format
  Scenario: Happy_Path_Validate consignment id format
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
    And validate the consignment id is created
   
  @Sortation @TC29_Happy_Path_Validate_consignment_add
  Scenario: Happy_Path_Validate consignment add
    Given Login to JDA Dispatcher web screen
  	And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And validate the record is saved
    
  @Sortation @TC31_Negative_Path_Validate_consignment_rules
  Scenario: Negative_Path_Validate_consignment_rules
    Given Login to JDA Dispatcher web screen
  	And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And validate the record is saved
    And I click on Add button
    And Enter same consignment name
    And Select consignment Status
    And Select Mode of transport
    And click execute
    And validate Error message is displayed
    
  @Sortation @TC32_Happy_Path_Validate_Load_build_process
    Scenario: Happy_Path_Validate_Load_build_process
    Given Login to JDA Dispatcher web screen
  	And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And Go to consignment drop maintainance screen
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter consignment
    And Enter chamber and Address Id
    Then click execute
    And Go to close consignment
    And Enter same consignment name
    And Click next
    And Select consignment to close
    And Click done
    
    @Sortation @TC033_Happy_path_Validate_GREEN_stock_in_Load_build
  Scenario: Happy_path_Validate GREEN stock in Load build
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Load Label
    And Click next
    And Enter same consignment name
    And Click next
    And Click done
    
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
  Scenario: Validate_Franchise_Allocation_creation
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

  @Complete @TC50_Validate_Picking_process_for_Manual_IDT_order @Hanging�Outbound @Picking-Hanging�Outbound_for_hanging
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

  @Picking @TC53_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order_for_hanging
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

  @Picking @TC54_Validate_32_digit_URN_generation_after_picking_for_hanging
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
      
      @completed @TC69_access_for_Consignment_amendment_restriction
 		Scenario: Access for Consignment Amendment Restriction
 		Given Login to JDA Dispatcher web screen
		And Go to User Group Function Access through Administration
		Then Search for "Consignment" report
		And Validate that records should be loaded for consignment
		And Access should be enabled for "ADMIN" Group for consignment
		Then Search for other group "PICK CLERK" for consignment
		Then Access should be enabled for "PICK CLERK" Group for consignment
      
      
      @completed @Reports @TC70_Validate_the_M_n_S_custom_inspection_report
  Scenario: Validate the Custom Inspection Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Custom Inspection Report"
    And Verify that the record is displayed for M&S - Customs Inspection Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Inspection Report
    Then Validate the report selection page for M&S - Customs Inspection Report completed
          

@completed  @TC71_access_for_CIR_Report_generation
 		Scenario: Access for CIR Report generation
 		Given Login to JDA Dispatcher web screen
		And Go to User Group Function Access through Administration
		Then Search for "Custom Inspection Report" report
		And Validate that records should be loaded
		And Access should be enabled for "ADMIN" Group
				Then Search for other group "OPERATIVE"
	And Access should be enabled for "OPERATIVE" Group

      
    @Reports @TC074_Load_systemic_reports_revised_on_amended_in_Consignment
    Scenario: Load systemic reports revised on amended in Consignment
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for " M&S - Load Label"
    And Verify that the record is displayed for Load Label
    And Enter consignment name
    Then Validate the confirmation page for Load Label
    Then Validate the report selection page for Load Label
    
   
        @complete @TC090_To_verify_red_check_failure_SKU_compliant_flag_check
     Scenario Outline: To Verify Red Check failure - SKU Compliant flag Check
     Given The Sku "<SKU>" validate the t-dept to be null 
     And Validate User defined check three as the sku moves to compliance
     Then Update the Product group with a valid T-Dept
     #And Validate User defined check three as the sku moves to compliance
     #Then Update the Product group with a valid T-Dept
     Then Navigate to Administration > Setup > Scheduler > Scheduler Job History 
     And Search for the Job "SKUVALIDATIONCHECKJ"
     Then Validate the status as "SUCCEEDED"
     Then Navigate to Administration > Setup > Scheduler > Scheduler Schedules
      And Search for the Job "SKUVALIDATIONCHECKJ"
     Then go to calender Preview
     
    
    Examples: 
      | SKU                |
      | 000000000021180074 |

  @complete @Report @TC091_RED_Report_creation
  Scenario: To Verify RED Report creation
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Red Report
    And Verify that the record is displayed for Red Report
    Then Validate the confirmation page for Red Report
    And Proceed next to Output tab for the report
    Then Validate the report selection page for Red Report creation

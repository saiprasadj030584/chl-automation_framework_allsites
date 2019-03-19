@Exit_Picking @Boxed_Outbound @Picking_Boxed_Outbound
Feature: Orders_Picking
  As a user in Exit DC
  Order should be Auto Allocated
  so that I can Pick and Dispatch

#-----------------------------------------------------------------------------------------------------------------------------
   #TestCases included in @Sprint01
   
   #TC01_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Boxed
   #TC02_Validate_Pick_list_id_generated_for_an_order_Manual_IDT
   #TC03_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order
   
   @Sprint03 @Allocation @TC01_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Boxed
  Scenario Outline: SN01_Validate Pick list id generated for an order-Manual Franchise Boxed
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB

    Examples: 
      | SKU                |
      | 000000000021071852 |

#----------------------------------------------------------------------------------------------------------------------------
   @Sprint03 @Allocation @TC02_Validate_Pick_list_id_generated_for_an_order_Manual_IDT
  Scenario Outline: SN02_Validate Pick list id generated for an order-Manual IDT
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT

    Examples: 
      | SKU                |
      | 000000000021071852 |

#----------------------------------------------------------------------------------------------------------------------------

   @Sprint03 @Allocation @TC03_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order
  Scenario Outline: SN03_Validate Pick list id generated for a FSV Cross dock order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Allocation @TC04_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order
  Scenario Outline: SN04_Validate Pick list id generated for a cross dock ASN order
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Picking @TC05_Validate_Picking_process_for_Manual_Franchise_order
  Scenario Outline: SN05_Validate Picking process for Manual Franchise order
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

   @Sprint03 @Picking @TC06_Validate_Picking_process_for_Manual_IDT_order @Boxed–Outbound @Picking-Boxed–Outbound
  Scenario Outline: SN06_Validate Picking process for Manual IDT order(Transfer order)
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

   @Sprint03 @Picking @TC07_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order
  Scenario Outline: SN07_Validate the auto picking process for the Cross dock FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Picking @Picking @TC09_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order
  Scenario Outline: SN09_Negative_Path_Validate scanning incorrect pallet id for a Manual Order
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

   @Sprint03 @Picking @TC10_Validate_32_digit_URN_generation_after_picking
  Scenario Outline: SN10_Validate 32 digit URN generation after picking
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

   @Sprint03 @Picking @TC11_Create_a_consignment_or_Load_label
  Scenario: SN11_Create a consignment or Load label
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

   @Sprint03 @Picking @TC12_Create_Consignment_Drop
  Scenario: SN12_Create a consignment or Load label
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

   @Sprint03 @Sorting @TC13_Validate_adding_URN_to_pallet_id
  Scenario Outline: SN13_Validate adding urn to pallet id
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for sortation in Direct Receiving
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate,Pick and Repack record

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Sorting @TC14_Negative_path_Adding_wrong_destination_URN_to_the_Pallet
  Scenario Outline: SN14_Negative path_Adding wrong destination URN to the Pallet
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for different destination for sortation in Direct Receiving
    And I Validate the Error message for different source and destination

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Sorting @TC15_Negative_path_Adding_wrong_T_Dept_URN_to_the_Pallet
  Scenario Outline: SN15_Negative_path_Adding_wrong_T_Dept_URN_to_the_Pallet
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for different destination for sortation in Direct Receiving
    And I Validate the Error message for different source and destination

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Picking @TC16_Negative_Path_Scan_incorrect_UPC_during_picking_Manual_Order
  Scenario Outline: SN16_Negative_Path_Validate scanning incorrect pallet id for a Manual Order
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

   @Sprint03 @Picking @TC17_Negative_Path_Scan_incorrect_To_Location_during_Manual_order_Picking
  Scenario Outline: SN17_Negative_Path_Scan incorrect To Location during Manual order Picking
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

   @Sprint03 @Sortation @TC18_Happy_path_sortation_group_validation
  Scenario: SN18_Happy path sortation group validation
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click
    And Verify whether the access

   @Sprint03 @Sortation @TC19_Happy_path_validate_sortation_functional_access
  Scenario Outline: SN19_Happy path sortation group functional access
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click
    And Click on Query
    And Specify the sortation group "<sortationgroup>"
    And Click search
    And Validate Blind receipt, pre-advice receipt, repack

    Examples: 
      | sortationgroup |
      | Sortation      |

   @Sprint03 @Sortation @TC26_Happy_path_Pallet_Consignment_Linking_process
  Scenario Outline: SN26_Happy_path_Pallet_Consignment_Linking_process
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    Then I login as warehouse user in putty
    And I link the pallet and consignment

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Sortation @TC27_Negative_Path_Incorrect_pallet_consignment_linking
  Scenario: SN27_Negative_Path_Incorrect pallet consignment linking
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And configure putty settings
    And I select Inventory transaction option
    And Enter Pallet Id "P1286952"
    And Enter Consignment "7993271218"
    And Validate the error is displayed

   @Sprint03 @Sortation @TC28_Happy_Path_Validate_consignment_id_format
  Scenario: SN28_Happy_Path_Validate consignment id format
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

   @Sprint03 @Sortation @TC29_Happy_Path_Validate_consignment_add
  Scenario: SN29_Happy_Path_Validate consignment add
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

   @Sprint03 @Reports @TC30_Happy_path_review_load_report_validation
  Scenario Outline: SN30_Happy_Path_Review Load report validation
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "pallet report"
    And Verify that the record is displayed for M&S - URN pallet Report
    And Enter URN as parameter
    And Validate the confirmation page for M&S - pallet Report
    Then Validate the report selection page for M&S - URN pallet report completed

    Examples: 
      | SkuId              |
      | 000000000021071852 |

   @Sprint03 @Sortation @TC31_Negative_Path_Validate_consignment_rules
  Scenario: SN31_Negative_Path_Validate_consignment_rules
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

   @Sprint03 @Sortation @TC32_Happy_Path_Validate_Load_build_process
  Scenario: SN32_Happy_Path_Validate_Load_build_process
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

   @Sprint03 @Sortation @TC33_Happy_path_Validate_GREEN_stock_in_Load_build
  Scenario: SN33_Happy_path_Validate GREEN stock in Load build
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

   @Sprint03 @Franchise @TC34_Negative_Path_Validate_multi_line_pickin_for_Manual_SO
  Scenario Outline: SN34_Validate Picking process for Manual IDT order(Transfer order)
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

   @Sprint03 @Ordering @TC35_Happy_Path_Validate_FSV_order
  Scenario Outline: SN35_Happy_Path_Validate FSV order
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

   @Sprint03 @Ordering @TC36_Happy_Path_Validate_Manual_order
  Scenario Outline: SN36_Happy_Path_Validate_Manual_order
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

   @Sprint03 @Ordering @TC37_Happy_Path_Validate_Cross_dock_order
  Scenario Outline: SN37_Happy_Path_Validate Cross dock order
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

   @Sprint03 @Ordering @TC38_Happy_Path_Validate_Manual_STO
  Scenario Outline: SN38_Happy_Path_Validate Manual STO
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

   @Sprint03 @Trailer @TC39_Validate_trailer_creation
  Scenario: SN39_Validate trailer creation
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved

   @Sprint03 @Trailer @TC40_Validate_consignment_Trailer_linking
  Scenario Outline: SN40_Validate consignment Trailer linking
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And drop the same consignment
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    And I link consignment with trailer
Examples: 
      | SKU                |
      | 000000000021071852 |
      
   @Sprint03 @Trailer @TC41_Negative_path_Incorrect_Pallet_for_consignment_trailer_linking
  Scenario: SN41_Negative_path_Incorrect Pallet for consignment trailer linking
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
    And Click next
    And validate error message is displayed

   @Sprint03 @Shipdock @TC42_Validate_shipdock_assignment
  Scenario Outline: SN42_Validate_shipdock_assignment
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

   @Sprint03 @Ordering @TC43_Validate_Franchise_order_allocation_configuration_Function_Access
  Scenario: SN43_Validate_Franchise_Allocation_creation
    Given Login to JDA Dispatcher web screen
    And Go to User Group Function Access
    And Type in Function Access search for text box
    And validate the access is Enabled
    And Go to Site Global Function Access
    And Type in and validate Function Access search for text box

   @Sprint03 @Ordering @TC44_Validate_Franchise_Allocation_creation
  Scenario: SN44_Validate_Franchise_Allocation_creation
    Given Login to JDA Dispatcher web screen
    And Go to Allocation algorithm Setup
    And Click next
    And I select Allocation creation date by zone option
    And click add
    And type "INBOUND" in location zone
    Then save the Allocation created

   @Sprint03 @Picking @TC45_Validate_Pick_list_id_generated_for_an_order_Manual_Franchise_Hanging
  Scenario Outline: SN45_Validate Pick list id generated for an order-Manual Franchise hanging
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANH

    Examples: 
      | SKU                |
      | 000000000021071851 |

   @Sprint03 @Picking @TC46_Validate_Pick_list_id_generated_for_an_order_Manual_IDT_hanging
  Scenario Outline: SN46_Validate Pick list id generated for an order-Manual IDT_hanging
    Given Order Status should be "Released", Type should be "NONRETAIL", Customer should be "5542" for IDT "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status for IDT
    And Validation of List Id generated with prefix as IDT for hanging

    Examples: 
      | SKU                |
      | 000000000021071851 |

   @Sprint03 @Picking @TC47_Validate_Pick_list_id_generated_for_a_FSV_Cross_dock_order_hanging
  Scenario Outline: SN47_Validate Pick list id generated for a FSV Cross dock order_hanging
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier

    Examples: 
      | SKU                |
      | 000000000021071851 |

   @Sprint03 @Picking @TC48_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order_hanging
  Scenario Outline: SN48_Validate Pick list id generated for a cross dock ASN order_hanging
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

   @Sprint03 @Picking @TC49_Validate_Picking_process_for_Manual_Franchise_order_for_hanging
  Scenario Outline: SN49_Validate Picking process for Manual Franchise order
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

   @Sprint03 @Picking @TC50_Validate_Picking_process_for_Manual_IDT_order @Hanging–Outbound @Picking-Hanging–Outbound_for_hanging
  Scenario Outline: SN50_Validate Picking process for Manual IDT order(Transfer order)
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

   @Sprint03 @Picking @TC51_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order_hanging
  Scenario Outline: SN51_Validate the auto picking process for the Cross dock FSV order
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I navigate to Order header screen to verify the status in Ready to Load

    Examples: 
      | SKU                |
      | 000000000021071851 |

   @Sprint03 @Picking @TC53_Negative_Path_Validate_scanning_incorrect_pallet_id_for_a_Manual_Order_for_hanging
  Scenario Outline: SN53_Negative_Path_Validate scanning incorrect pallet id for a Manual Order for hanging
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

   @Sprint03 @Picking @TC54_Validate_32_digit_URN_generation_after_picking_for_hanging
  Scenario Outline: SN54_Validate 32 digit URN generation after picking for hanging
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

   @Sprint03 @Sorting @TC057_Validate_adding_URN_to_pallet_id
  Scenario Outline: SN57_Validate adding urn to pallet id
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for sortation in Direct Receiving
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate,Pick and Repack record

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Sorting @TC58_Negative_path_Adding_wrong_destination_URN_to_the_Pallet
  Scenario Outline: SN58_Negative path_Adding wrong destination URN to the Pallet
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for different destination for sortation in Direct Receiving
    And I Validate the Error message for different source and destination

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Sorting @TC59_Negative_path_Adding_wrong_T_Dept_URN_to_the_Pallet
  Scenario Outline: SN59_Negative_path_Adding_wrong_T_Dept_URN_to_the_Pallet
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And I select sorting menu
    And I enter URN for different destination for sortation in Direct Receiving
    And I Validate the Error message for different source and destination

    Examples: 
      | SKU                |
      | 000000000021071852 |

   @Sprint03 @Picking @TC60_Negative_Path_Scan_incorrect_UPC_during_picking_Manual_Order
  Scenario Outline: SN60_Negative_Path_Validate scanning incorrect pallet id for a Manual Order
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

   @Sprint03 @Picking @TC61_Negative_Path_Scan_incorrect_To_Location_during_Manual_order_Picking
  Scenario Outline: SN61_Negative_Path_Scan incorrect To Location during Manual order Picking
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

   @Sprint03 @Ordering @TC62_Happy_Path_Validate_FSV_order_hanging
  Scenario Outline: SN62_Happy_Path_Validate FSV order
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

   @Sprint03 @Ordering @TC63_Happy_Path_Validate_Manual_order_hanging
  Scenario Outline: SN63_Happy_Path_Validate_Manual_order
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

   @Sprint03 @Ordering @TC64_Happy_Path_Validate_Cross_dock_order_hanging
  Scenario Outline: SN64_Happy_Path_Validate Cross dock order
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

   @Sprint03 @Ordering @TC65_Happy_Path_Validate_Manual_STO_hanging
  Scenario Outline: SN65_Happy_Path_Validate Manual STO
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

   @Sprint03 @USER_ACCESS @TC66_prohibition_rules_new_rules_admin
  Scenario Outline: SN66_Prohibition Rules - Happy Path - Add new rules Access (Admin Group)
    Given Login to JDA Dispatcher web screen
    And Navigate to Admin-->user-->user
    And Provide the Support Team user ID "<supportId>" and Click Execute
    Then User group is validated as "ADMIN"
    And Go to System Profile
    Then Navigate to --ROOT- > USER > RECEIVING > INT
    When Tried to Add New Rules for prohibition
    Then New pohibition logic should be allowed to include
    Then Enter the New Prohibition for Israel
    Then The new rule should be included

    Examples: 
      | supportId |
      | P9138780E |

   @Sprint03 @USER_ACCESS @TC67_prohibition_rules_new_rules_basic_user
  Scenario Outline: SN67_Prohibition Rules - Add new rules Access - BASICUSER group
    Given Login to JDA Dispatcher web screen
    And Navigate to Admin-->user-->user
    And Provide the Support Team user ID "<supportId>" and Click Execute
    Then User group is validated as "BASICUSER"
    Given Login to JDA Dispatcher web screen as Basic User "<supportId>"
    Then Validate the page for basic user

    Examples: 
      | supportId |
      | P9142516E |

   @Sprint03 @COMPLIANCE @TC68_red_stock_moved_to_green
  Scenario Outline: SN68_RED Stock moved to GREEN post SKU compliance status changed
    Given The green stock SKU "<SKU>"
    Given Alter the check weight to make the stock as RED Stock
    And Validate User defined check three as the sku moves to compliance
    Then check weight is validated as null as stock becomes red stock
    Then Update the Packed weight
    Then Validate User defined check three as the red sku becomes green

    Examples: 
      | SKU                |
      | 000000000022120199 |

   @Sprint03 @USER_ACCESS @TC69_access_for_Consignment_amendment_restriction
  Scenario: SN69_Access for Consignment Amendment Restriction
    Given Login to JDA Dispatcher web screen
    And Go to User Group Function Access through Administration
    Then Search for "Consignment" report
    And Validate that records should be loaded for consignment
    And Access should be enabled for "ADMIN" Group for consignment
    Then Search for other group "PICK CLERK" for consignment
    Then Access should be enabled for "PICK CLERK" Group for consignment

   @Sprint03 @Reports @TC70_Validate_the_M_n_S_custom_inspection_report
  Scenario: SN70_Validate the Custom Inspection Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Custom Inspection Report"
    And Verify that the record is displayed for M&S - Customs Inspection Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Inspection Report
    Then Validate the report selection page for M&S - Customs Inspection Report completed

   @Sprint03 @USER_ACCESS @TC71_access_for_CIR_Report_generation
  Scenario: SN71_Access for CIR Report generation
    Given Login to JDA Dispatcher web screen
    And Go to User Group Function Access through Administration
    Then Search for "Custom Inspection Report" report
    And Validate that records should be loaded
    And Access should be enabled for "ADMIN" Group
    Then Search for other group "OPERATIVE"
    And Access should be enabled for "OPERATIVE" Group

   @Sprint03 @USER_ACCESS @TC73_admin_team_access_to_create_amend_or_delete_sortation_rules
  Scenario Outline: SN73_Admin Team access to create, amend or delete Sortation rules
    Given The sku "<SkuId>" details and corresponding product group
    Given Access the table for trusted group given the customerID "7977"

    Examples: 
      | SkuId              |
      | 000000000021071852 |

   @Sprint03 @Reports @TC74_Load_systemic_reports_revised_on_amended_in_Consignment
  Scenario: SN74_Load systemic reports revised on amended in Consignment
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for " M&S - Load Label"
    And Verify that the record is displayed for Load Label
    And Enter consignment name
    Then Validate the confirmation page for Load Label
    Then Validate the report selection page for Load Label

   @Sprint03 @USER_ACCESS @TC76_set_up_trusted_rules
  Scenario Outline: SN76_Set up Trusted Rules
    Given The sku "<SkuId>" details and corresponding product group
    Given Access the table for trusted group given the customerID "7977"

    Examples: 
      | SkuId              |
      | 000000000021071852 |

   @Sprint03 @USER_ACCESS @TC77_Set_up_prohibition_rules
  Scenario: SN77_Set up Prohibition Rules
    Given Login to JDA Dispatcher web screen
    And Go to System Profile
    Then Navigate to --ROOT- > USER > RECEIVING > INT
    When Tried to Add New Rules for prohibition
    Then New pohibition logic should be allowed to include
    
    @Sprint03 @Receiving @TC78_Direct_receiving_Happy_path_Non_Trusted_Boxed_NonProhibited_inventory
  Scenario Outline: SN78_To validate direct receiving happy path non-trusted-non-prohibited inventory
    Given Checking the conditions "Non-Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071852 |
    
    @Sprint03 @Receiving @TC79_Direct_receiving_Happy_path_Trusted_Boxed_NonProhibited_inventory
  Scenario Outline: SN79_To validate Direct receiving – Happy path – Trusted – Boxed -  NonProhibited inventory
    Given Checking the conditions "Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "4611"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |
      
      @Sprint03 @Receiving @TC83_Direct_receiving_Happy_path_Trusted_Hanging_NonProhibited_inventory
  Scenario Outline: SN83_To validate Direct receiving – Happy path – Trusted – Hanging -  NonProhibited inventory
    Given Checking the conditions "Trusted", "Hanging" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |
      
      @Sprint03 @Receiving @TC84_Direct_receiving_Happy_path_Non_Trusted_Hanging_Prohibited_inventory
  Scenario Outline: SN84_To validate Direct receiving – Happy path – Non Trusted – Hanging – Prohibited inventory
    Given Checking the country of origination for sku "<SkuId>"
    Given Checking the conditions "Non-Trusted", "Hanging" and "Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition
    Then Update country for non-prohibition

    Examples: 
      | SkuId              |
      | 000000000021071851 |
      
      @Sprint03 @Receiving @TC85_Direct_receiving_Happy_path_Trusted_Hanging_Prohibited_inventory
  Scenario Outline: SN85_To validate Direct receiving – Happy path – Trusted – Hanging -  Prohibited inventory
    Given Checking the country of origination for sku "<SkuId>"
    Given Checking the conditions "Trusted", "Hanging" and "Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition
    Then Update country for non-prohibition

    Examples: 
      | SkuId              |
      | 000000000021071851 |
    
    @Sprint03 @Receiving @TC88_Direct_receiving_Happy_path_Trusted_Hanging_NonProhibited_inventory_URN_Scan
  Scenario Outline: SN83_To validate Direct receiving – Happy path – Trusted – Hanging -  NonProhibited inventory URN Scan
    Given Checking the conditions "Trusted", "Hanging" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |
    

   @Sprint03 @COMPLIANCE @TC090_To_verify_red_check_failure_SKU_compliant_flag_check
  Scenario Outline: SN90_To Verify Red Check failure - SKU Compliant flag Check
    Given The Sku "<SKU>" validate the t-dept to be null
    And Validate User defined check three as the sku moves to compliance
    Then Update the Product group with a valid T-Dept
    And Validate User defined check three as the sku moves to compliance
    Then Update the Product group with a valid T-Dept
    Then Navigate to Administration > Setup > Scheduler > Scheduler Job History
    And Search for the Job "SKUVALIDATIONCHECKJ"
    Then Validate the status as "SUCCEEDED"
    Then Navigate to Administration > Setup > Scheduler > Scheduler Schedules
    And Search for the Job "SKUVALIDATIONCHECKJ"
    Then go to calender Preview

    Examples: 
      | SKU                |
      | 000000000021180074 |

   @Sprint03 @Report @TC91_RED_Report_creation
  Scenario: SN91_To Verify RED Report creation
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Red Report
    And Verify that the record is displayed for Red Report
    Then Validate the confirmation page for Red Report
    And Proceed next to Output tab for the report
    Then Validate the report selection page for Red Report creation

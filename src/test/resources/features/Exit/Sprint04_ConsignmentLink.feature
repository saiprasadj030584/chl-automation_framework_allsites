@Sprint04_consignmentLinking
Feature: ConsignmentLinking
  As a Exit DC user should be able to login
   so that I validate repacking
   with ConsignmentLinking

  @ConsignmentLinking @TC01_Validate_Pick_list_id_generated_for_an_order-Manual_Franchise_Boxed
  Scenario Outline: Validate Pick list id generated for an order-Manual Franchise Boxed
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    And Navigate to Move Task management Screen to verify Order Allocated status
    And Validation of List Id generated with prefix as MANB

    Examples: 
      | SKU                |
      | 000000000021071852 |
      
  @ConsignmentLinking @TC02_Validate_Picking_process_for_Manual_Franchise_order
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

  @ConsignmentLinking @TC03_Validate_Picking_process_for_Manual_Franchise_order_for_hanging
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

  @ConsignmentLinking @TC04_Happy_Path_Validate_Manual_order
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

  @ConsignmentLinking @TC05_Validate_Pick_list_id_generated_for_an_order_Manual_STO
  Scenario Outline: Validate Pick list id generated for an order-Manual_STO
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

  @Completed @Unpick @TC07_Validate_unpicking_the_order
  Scenario Outline: To Validate unpicking the order in EXIT dispatcher
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
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
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Unpick/Unship
    And I enter container_id
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick and UnPick

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @Unpick @TC08_Validate_unpick_and_relocate_tasks
  Scenario Outline: To Validate unpicking and relocate order in EXIT dispatcher
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
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
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Unpick/Unship
    And I enter container_id
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick and UnPick
    And I navigate to move task query page
    And Query with TaskId as RELOCATE and "<SkuId>"

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @In-Progress @Unpick @TC09_Validate_relocate_task_completion
  Scenario Outline: To Validate unpicking and relocate task completion in EXIT dispatcher
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute and get TagID
    And check the Inventory Transaction for Receipt, Allocate and Pick
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Unpick/Unship
    And I enter container_id
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Relocate and Existing relocate
    And I enter UPC and TagId "<SkuId>"
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick and UnPick

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @completed @TC10_validate_the_putaway_group_for_black_zone
  Scenario: Validate Putaway group for Black zone
    Given Login to JDA Dispatcher web screen
    When I navigate to SKU maintenance page
    Then Query for checking Putaway group
    Then Validate the putaway group is not null

  @completed @Putaway @TC11_Validate_black_stock_adjustment
  Scenario Outline: To Validate Black stock adjustment
    Given Login to JDA Dispatcher web screen
    And Take a sku having stock in "BLACKB"
    Then Navigate to Stock Adjustment Screen
    And Click next
    And Query with sku id and tag id in BLACK area
    And Click next
    And Click next
    Then Adjust the stock form the sku - quantity in hand
    When Verified in Inventory and ITL
    Then Stock is validated successfully

    Examples: 
      | Location |
      | AA001    |

  @completed @Putaway @TC12_negative_path_stock_associated_urn_must_allow_neagtive_adjustment
  Scenario Outline: Negative Path Stock associated URN must allow only negative adjustment
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    Then Get the tag ID
    And check the Inventory Transaction for Receipt, Allocate and Pick
    Then Navigate to Stock Adjustment Screen
    And Click next
    And Enter Container_ID for stock adjustment
    And Click next
    And Click next
    Then Decrease the quantity in hand

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @completed @ConsignmentLinking @TC13_validate_stock_take_checks
  Scenario Outline: Validate stock take checks
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    Then Get the tag ID
    And check the Inventory Transaction for Receipt, Allocate and Pick
    When I navigate to stock check list Generation page
    And I select mode of list generation as 'Generate by inventory'
    And I enter the Tag ID as on inventory tab
    And I proceed to next
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    When I proceed to next
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
    When I navigate to stock check query page
    And I search the list by tag id as and task date as current date
    Then I should see the record with list ID

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @completed @Reports @TC14_Validate_URN's_in_pallet_report
  Scenario Outline: Validate the M&S - URN in pallet report
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

  @completed @Reports @TC17_Validate_stock_check_report
  Scenario Outline: Validate the M&S - stock check report
    Given Order Status should be "Released", Type should be "RETAIL", Customer should be "5542" for SKU "<SKU>"
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Stock check tasks - by list ID"
    And Verify that the record is displayed for M&S - stock check tasks Report
    And Enter list ID as parameter
    And Validate the confirmation page for M&S - stock check Report
    Then Validate the report selection page for M&S - stock check completed

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @Reversion @TC018_Reversion_of_stock_from_a_trailer_Wanted_stock
  Scenario Outline: Reversion of stock from a trailer_Wanted stock
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    And I complete Vechile loading
    And I revert stock from trailer

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @Reversion @TC019_Reversion_of_stock_from_a_consignment_Wanted_stock
  Scenario Outline: Reversion_of_stock_from_a_consignment_Wanted_stock
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
    Then I login as warehouse user in putty
    And I unlink consignment with trailer

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @Repacking @TC020_Validate_repacking_the_pallet_Reversion
  Scenario Outline: Validate repack after consignment closure
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
    Then I login as warehouse user in putty
    And I unlink consignment with trailer

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @Repacking @TC021_Validate_Reversion_relocate_storage_location
  Scenario Outline: Validate repack after consignment closure
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And I create Trailer
    And I create DockDoor
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    Then I login as warehouse user in putty
    And I repack the consignment
    And validate the message is displayed
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

  @ConsignmentLinking @Repacking @TC022_Validate_repack_after_consignment_closure
  Scenario Outline: Validate repack after consignment closure
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    Then I login as warehouse user in putty
    And I repack the consignment
    And validate the message is displayed

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @TC24_User_Access_to_Reversion
  Scenario: User Access to Reversion
    Given Login to JDA Dispatcher web screen
		And Go to Admin>ACCESS CNT>Workstation access control & Click
		And Select a RDT Group from the Group dropdown box
    And Type in Function Access search "Enable Consignment Closure change for Consignment Management"
    Then validate the access is Enabled
    
  @ConsignmentLinking @TC25_Validate_consignment_closure
  Scenario: Validate consignment closure
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

  @ConsignmentLinking @TC26_Validate_vehicle_loading_Single_pallet
  Scenario Outline: Validate_vehicle_loading_Single_pallet
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
    And I link consignment with trailer
    And I close the consignment
    And I complete Vechile loading

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @TC27_Negative_Path_Enter_incorrect_pallet_id
  Scenario: Negative Path_Enter incorrect pallet id
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select vehicle loading option in main menu
    And select multi pallet load
    And I enter invalid pallet "1015"
    And I enter consignment "CONS030119"
    And validate the error message is displayed

  @ConsignmentLinking @TC29_Validate_confirm_shipment
  Scenario Outline: Validate_confirm_shipment
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
    And I link consignment with trailer
    And I close the consignment
    And I complete Vechile loading
    And Login to JDA Dispatcher web screen
    And I navigate to Trailer Shipping page
    And select trailer text tab
    Then Enter Trailer number
    And Click next

    #And Click done
    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @TC30_Negative_Path_Validate_trailer_shipping
  Scenario: Negative_Path_Validate_trailer_shipping
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer Shipping page
    And select trailer text tab
    Then Enter Trailer number
    And Click next
    And validate the error popup is displayed

  @completed @ConsignmentLinking @TC32_Validate_the_container_report
  Scenario: Validate the Container Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Container Report"
    And Verify that the record is displayed for Container Report or M&S - Short Invoice for Container Report
    Then Enter Trailer number
    Then Validate the confirmation page for Container Report or M&S - Short Invoice for Container Report
    Then Validate the report selection page for Container Report or M&S - Short Invoice for Container Report completed

  @ConsignmentLinking @TC33_Validate_pallet_count_or_container_confirmation_logic_for_a_consignment_id
  Scenario Outline: Validate pallet count or container confirmation logic for a consignment id
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    And I complete Vechile loading

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @ConsignmentLinking @TC34_Negative_Path_container_and_consignment
  Scenario: Negative Path_container and consignment
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select vehicle loading option in main menu
    And I select vehicle load option
    And I enter dock door "1015"
    And validate the error message is displayed

  @completed @ConsignmentLinking @TC35_Validate_Load_Hazardous_report
  Scenario: Validate Load Hazardous Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S DGN- Report"
    And Verify that the record is displayed for Dangerous Goods
    Then Enter Trailer number
    And Validate the confirmation page for Dangerous Goods
    Then Validate the report selection page for Dangerous Goods

  @completed @Trailer_Maintenance @TC36_Create_Trailer_id
  Scenario: Create_Trailer_id
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved

  @completed @Trailer_Maintenance @TC037_Validate_consignment_Trailer_linking
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

  @completed @ConsignmentLinking @TC38_Validate_Proforma_Invoice_report
  Scenario: Validate Proforma_Invoice_report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Proforma Invoice - by Order ID"
    And Verify that the record is displayed for Proforma Invoice
    Then Enter order number "1015176558"
    And Validate the confirmation page for Proforma Invoice
    Then Validate the report selection page for Proforma Invoice

  @ConsignmentLinking @TC39_Check_that_the_User_Groups_have_been_set_up_with_the_required_access_for_the_RDTs
  Scenario: Check that the User Groups have been set up with the required acceses for the RDTs
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Select a User Group from the Group dropdown box
    And Verify whether the access is valid

  @ConsignmentLinking @TC40_Validate_site_related_Function_Access_are_enabled
  Scenario: Validate_site_related_Function_Access_are_enabled
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Search for Picking and Relocate access
    And Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click
    And Search for Picking and Relocate access
    And Go to Admin>ACCESS CNT>Workstation access control & Click
    And Search for Picking and Relocate access
    And Go to Site Global Function Access
    And Search for Picking and Relocate access

  @completed @TC44_validate_load_closure_user_profile
  Scenario: Validate Load closure user profile
    Given Login to JDA Dispatcher web screen
    And Go to User Group Function Access through Administration
    Then Search for "consignment closure" report
    And Validate that records should be loaded for consignment closure
    And Access should be enabled for "ADMIN" Group for consignment closure

  @completed @Reports @TC46_Load_systematic_reports_revised
  Scenario: Load systemic reports revised on amended in Consignment
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Custom Inspection Report"
    And Verify that the record is displayed for M&S - Customs Inspection Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Inspection Report
    Then Validate the report selection page for M&S - Customs Inspection Report completed

  @ConsignmentLinking @TC48_Negative_Path_Validate_adding_palle_to_closed_consignment
  Scenario Outline: Negative_Path_Validate_adding_palle_to_closed_consignment
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    Then I login as warehouse user in putty
    And I repack the consignment
    And validate the message is displayed

    Examples: 
      | SKU                |
      | 000000000021071852 |

@ConsignmentLinking @Reversion @TC51_Identify_missing_pallet_id_remove_pallet_id
  Scenario Outline: Identify missing pallet id remove pallet id
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    And I complete Vechile loading
    And I revert stock from trailer

    Examples: 
      | SKU                |
      | 000000000021071852 |
      
      @ConsignmentLinking @Reversion @TC52_Validate_container_report
  Scenario Outline: Validate container report

    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving
    And Login to JDA Dispatcher web screen
    And I create a consignment
    And Login to JDA Dispatcher web screen
    And drop the same consignment
    And Login to JDA Dispatcher web screen
    And I create Trailer
    And I create DockDoor
    Then I login as warehouse user in putty
    And I link the pallet and consignment
    And Login to JDA Dispatcher web screen
    And I link consignment with trailer
    And Login to JDA Dispatcher web screen
    And I close the consignment
    And I complete Vechile loading
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Container Report"
    And Verify that the record is displayed for M&S - Short Invoice for Container Report
    Then Enter Trailer number for the consignment
    Then Validate the confirmation page for M&S - Short Invoice for Container Report
    Then Validate the report selection page for M&S - Short Invoice for Container Report completed
    
    
    Examples: 
      | SKU                |
      | 000000000021071852 |
      
 @completed @Trailer_Maintenance @TC54_Validate_Trailer_id
  Scenario: Validate_Trailer_id
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved

  @completed @ConsignmentLinking @TC60_RED_Report_creation
  Scenario: To Verify RED Report creation
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Red Report
    And Verify that the record is displayed for Red Report
    Then Validate the confirmation page for Red Report
    And Proceed next to Output tab for the report
    Then Validate the report selection page for Red Report creation

  @completed @ConsignmentLinking @TC61_Validate_black_stock_adjustment
  Scenario Outline: To Validate Black stock adjustment
    Given The location "<Location>" verify the workzone as "BLACKB"
    Given Login to JDA Dispatcher web screen
    And Take a sku having stock in "BLACKB"
    Then Navigate to Stock Adjustment Screen
    And Query with sku id and tag id in BLACK area
    Then Adjust the stock form the sku - quantity in hand
    When Verified in Inventory and ITL
    Then Stock is validated successfully

    Examples: 
      | Location |
      | AA001    |

  @completed @ConsignmentLinking @TC63_Validate_stock_check_details_for_outbound
  Scenario Outline: Validate stock check details for outbound
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
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
    When I navigate to stock check list Generation page
    And I select mode of list generation as 'Generate by inventory'
    And I enter the sku as on inventory tab
    And I proceed to next
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    When I proceed to next
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
    When I navigate to stock check query page
    And I search the list by tag id as and task date as current date
    Then I should see the record with list ID

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @completed @Trailer_Maintenance @TC64_Validate_Trailer_id
  Scenario: Validate_Trailer_id
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved

  @completed @ConsignmentLinking @TC65_validate_stock_take_checks
  Scenario Outline: Validate stock take checks
    #Given The Sku "<SKU>" is already picked
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SKU>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    Then Get the tag ID
    And check the Inventory Transaction for Receipt, Allocate and Pick
    When I navigate to stock check list Generation page
    And I select mode of list generation as 'Generate by inventory'
    And I enter the Tag ID as on inventory tab
    And I proceed to next
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    When I proceed to next
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
    When I navigate to stock check query page
    And I search the list by tag id as and task date as current date
    Then I should see the record with list ID

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @inProgress @Repacking @TC66_Missing_URN_Repacking
  Scenario Outline: Missing URN_Repacking
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And I Navigate to Order Container Maintenance page
    Then Site finds the stock physically
    Then I login as warehouse user in putty
    And I select user directed option in main menu

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @completed @Putaway @TC67_Adjustment_to_URN_in_pallet
  Scenario Outline: Adjustment to URN in Pallet
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    Then Get the tag ID
    And check the Inventory Transaction for Receipt, Allocate and Pick
    Then Navigate to Stock Adjustment Screen
    And Click next
    And Enter Container_ID for stock adjustment
    And Click next
    And Click next
    Then Decrease the quantity in hand
    When Verified in Inventory and ITL
    Then Stock is validated successfully

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @completed @ConsignmentLinking @TC70_auto_complete_red_urn_putaway_post_receipt
  Scenario Outline: Auto complete Red URN putaway post receipt
    Given The details for the sku "<SkuId>"
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

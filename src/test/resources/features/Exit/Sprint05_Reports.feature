@Reports
Feature: Report Generation
  As I search for different reports
  and put different parameters
  so that I view the reports

   @Sprint05 @Reports @TC01_Validate_the_M_n_S_Identify_URN_Report
  Scenario: SN01_Validate the M&S - Identify URN Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Identify URN Report"
    And Verify that the record is displayed for Identify Urn Report
    Then Enter the required parameters UPC, supplier id and quantity
    Then Validate the confirmation page for M&S - Identify URN Report
    Then Validate the report selection page for M&S - Identify URN completed

   @Sprint05 @Reports @TC02_Validate_the_MNS_Dangerous_Goods_DGN_Report
  Scenario: SN02_Validate_the_MNS_Dangerous_Goods_DGN_Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S DGN- Report"
    And Verify that the record is displayed for Dangerous Goods
    Then Enter Trailer number
    And Validate the confirmation page for Dangerous Goods
    Then Validate the report selection page for Dangerous Goods

   @Sprint05 @Reports @TC03_Validate_the_MnS_Shipment_Manifest_Report
  Scenario: SN03_Validate the M&S - Shipment Manifest Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Shipment Manifest Report"
    And Verify that the record is displayed for M&S - Shipment Manifest Report
    Then Enter Trailer number
    Then Validate the confirmation page for M&S - Shipment Manifest Report
    Then Validate the report selection page for M&S - Shipment Manifest Report completed

   @Sprint05 @Reports @TC04_Validate_the_M_n_S_NonShipped_greater_than_4_weeks_report
  Scenario Outline: SN04_Validate the M&S - Non-Shipped greater than 4 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Non Shipped greater than 4weeks Report"
    And Verify that the record is displayed M&S - Non-Shipped greater than 4 weeks Report
    Then Enter the status "<status>" as parameter
    Then Validate the confirmation page for M&S - Non-Shipped greater than 4 weeks Report
    Then Validate the report selection page for M&S - Non-Shipped greater than 4 weeks completed

    Examples: 
      | status |
      | GREEN  |

   @Sprint05 @Reports @TC05_Validate_the_M_n_S_Gains_or_loss_report
  Scenario: SN05_Validate the M&S - Gains and Loss Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Gains and Loss Report"
    And Verify that the record is displayed for M&S Gains Or Loss Report
    Then Validate the confirmation page for Gains and Loss Report
    Then Validate the report selection page for Gains and Loss Report completed

   @Sprint05 @Reports @TC06_Validate_the_M_n_S_allocation_vs_receipts_across_last_3_weeks_report
  Scenario: SN06_Validate the M&S - Allocation vs Receipts across last 3 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Allocation vs Receipts across last 3 weeks Report"
    And Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed

   @Sprint05 @Reports @TC07_Validate_the_MnS_Stock_Allocation_Reportt
  Scenario: SN07_Validate the M&S - Allocation vs Receipts across last 3 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Allocation vs Receipts across last 3 weeks Report"
    And Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed

   @Sprint05 @Reports @TC08_Validate_the_M_n_S_stock_status_report
  Scenario: SN08Validate M&S - Stock Status Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Stock Status"
    And Verify that the record is displayed for M&S - Stock Status Report
    Then Validate the confirmation page for M&S - Stock Status Report
    Then Validate the report selection page for M&S - Stock Status completed

   @Sprint05 @Reports @TC09_Validate_the_M_n_S_weekly_receipts_and_despatches_report
  Scenario: SN09_Validate the  M&S - Weekly Receipts and Despatches Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Weekly Receipts and Despatches by Customer"
    And Verify that the record is displayed for M&S - Weekly Receipts and Despatches by Customer Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Weekly Receipts and Despatches by Customer Report
    Then Validate the report selection page for M&S - Weekly Receipts and Despatches by Customer completed

   @Sprint05 @Reports @TC10_Validate_the_MnS_Sortation_Report
  Scenario: SN10_Validate the M&S - Sortation Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Sortation Report"
    And Verify that the record is displayed for  M&S - Sortation Report
    Then Enter the sortation
    Then Validate the confirmation page for M&S - Sortation Report
    Then Validate the report selection page for M&S - Sortation Report completed

   @Sprint05 @Reports @TC11_Validate_the_M_n_S_prohibition_report
  Scenario: SN11_Validate the M&S - Prohibition Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Prohibition"
    And Verify that the record is displayed for M&S - Prohibition Report
    Then Validate the confirmation page for M&S - Prohibition Report
    Then Validate the report selection page for M&S - Prohibition completed

   @Sprint05 @Reports @TC12_Validate_the_M_n_S_trusted_report
  Scenario: SN12_Validate the M&S - Trusted Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Trusted Report"
    And Verify that the record is displayed for Trusted Report
    Then Validate the confirmation page for Trusted Report
    Then Validate the report selection page for Trusted Report completion

   @Sprint05 @Reports @TC13_Validate_the_M_n_S_batch_id_and_BBE_report
  Scenario: SN13_Validate the M&S - Batch ID & BBE Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Batch ID & BBE Report"
    And Verify that the record is displayed for BatchId and  BBE Report
    Then Validate the confirmation page for Batch ID Report
    Then Validate the report selection page for BatchId completion

   @Sprint05 @Reports @TC14_Validate_the_M_n_S_Outstanding_Pallets_to_load_report
  Scenario: SN14_Validate the M&S - Outstanding Pallets to Load Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Outstanding Pallets to Load Report"
    And Verify that the record is displayed for M&S - Outstanding Pallets to Load Report
    Then Enter Trailer number for report
    Then Validate the confirmation page for M&S - Outstanding Pallets to Load Report
    Then Validate the report selection page for M&S - Outstanding Pallets to Load Report completed

   @Sprint05 @TC15_Validate_the_M_n_S_Red_Location_Report
  Scenario: SN15_Validate the M&S - Red Location Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Red Location Report"
    And Verify that the record is displayed for Red Location Report
    Then Validate the confirmation page for RedLocation Report
    Then Validate the report selection page for Red Location completion

   @Sprint05 @Reports @TC16_Validate_the_M_n_S_Black_Stock_Status_Report
  Scenario: SN16_Validate the M&S - Black Stock Status Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Black Stock Status Report"
    And Verify that the record is displayed for Black Stock Status Report
    Then Validate the confirmation page for Black Stock Status Report
    Then Validate the report selection page for Black Stock Status completion

   @Sprint05 @Reports @TC17_Validate_the_MnS_Empty_Red_Locations_Report
  Scenario: SN17_Validate the M&S - Empty Red Locations Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Empty Red Locations Report"
    And Verify that the record is displayed for M&S - Empty Red Locations Report
    Then Enter the red location as parameter
    Then Validate the confirmation page for M&S - Empty Red Locations Report
    Then Validate the report selection page for M&S - Empty Red Locations Report

   @Sprint05 @Reports @TC18_Validate_the_M_N_S_URN_Audit_Trail_Report
  Scenario Outline: SN18_Validate the M&S - URN Audit Trail Report
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - URN Audit Trail Report"
    And Verify that the record is displayed for M&S - URN Audit Trail Report
    And Enter URN as parameter
    And Validate the confirmation page for M&S - URN Audit Trail Report
    Then Validate the report selection page for M&S - URN Audit Trail completed

    Examples: 
      | SkuId              |
      | 000000000021071852 |
      
 @Sprint05 @Reports @TC19_Validate_the_MNS_Operative_Performance_Report
  Scenario Outline: SN19_Validate the M&S - Operative Performance Report
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Operative Performance Trusted Report"
    And Verify that the record is displayed for Operative Performance Trusted Report
    And Enter start and end date
    And Validate the confirmation page for Operative Performance Trusted Report
    And Validate the report selection page for Operative Performance Trusted Report
    
    Examples: 
      | SkuId              |
      | 000000000021071852 |
      
   @Sprint05 @Reports @TC20_Validate_the_MNS_Green_Stock_Available_to_Pick_Flow_Report
  Scenario: SN20_Validate the M&S -Green Stock Available to Pick-FLOW Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

   @Sprint05 @Reports @TC22_Validate_the_MNS_Soiled_Damaged_Report
  Scenario: SN22_Validate the M&S - Soiled & Damaged Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Soiled and Damaged"
    And Verify that the record is displayed for Soiled and Damaged
    And Validate the confirmation page for Soiled and Damaged
    Then Validate the report selection page for Soiled and Damaged

   @Sprint05 @Reports @TC23_Validate_the_MNS_Pallet_not_Consigned_Report
  Scenario: SN23_Validate the M&S - Pallet not Consigned Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Pallet not Consigned Report"
    And Verify that the record is displayed for Pallet not Consigned Report
    And Enter customer id "4624"
    And Validate the confirmation page for Pallet not Consigned Report
    Then Validate the report selection page for Pallet not Consigned Report

   @Sprint05 @Reports @TC24_Validate_the_MNS_Unpicked_not_relocated_Report
  Scenario: SN24_Validate the M&S - Unpicked, not relocated Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Unpicked Not Relocated Stock"
    And Verify that the record is displayed for Unpicked Not Relocated Stock
    And Validate the confirmation page for Unpicked Not Relocated Stock
    Then Validate the report selection page for Unpicked Not Relocated Stock

   @Sprint05 @Reports @TC25_Validate_the_MnS_Stock_in_Putaway_Report
  Scenario: SN25_Validate the M&S - Stock in Putaway Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Stock In RED Putaway Locations"
    And Verify that the record is displayed for M&S - Stock In RED Putaway Locations
    Then Enter the customer as parameter
    Then Validate the confirmation page for M&S - Stock In RED Putaway Locations
    Then Validate the report selection page for M&S - Stock In RED Putaway Locations

   @Sprint05 @Reports @TC26_Validate_the_M_n_S_Customs_valuation_for_consignment_report
  Scenario: SN26_Validate the M&S - Customs Valuation for Consignment Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed

   @Sprint05 @Reports @TC27_Validate_the_M_n_S_weekly_summary_report
  Scenario: SN27_Validate the M&S - Weekly Summary Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Weekly Summary Report"
    And Verify that the record is displayed for M&S - Weekly Summary Report
    And Enter the date for end of week as parameter
    Then Validate the confirmation page for M&S - Weekly Summary Report
    Then Validate the report selection page for M&S - Weekly Summary Report completed

   @Sprint05 @Reports @TC28_Validate_the_M_n_S_pallet_build_report
  Scenario: SN28_Validate the M&S - Pallet Build Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Pallet Built Report"
    And Verify that the record is displayed for M&S - Pallet Built Report
    Then Validate the confirmation page for M&S - Pallet Built Report
    Then Validate the report selection page for M&S - Pallet Built Report completed

   @Sprint05 @Reports @TC30_Validate_the_M_n_S_short_invoice_for_container_report
  Scenario: SN30_Validate the M&S - Short Invoice for Container Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Short Invoice for Container Report"
    And Verify that the record is displayed for M&S - Short Invoice for Container Report
    Then Enter Trailer number
    Then Validate the confirmation page for M&S - Short Invoice for Container Report
    Then Validate the report selection page for M&S - Short Invoice for Container Report completed

   @Sprint05 @Reports @TC29_Validate_the_MnS_Audit_Check_Report
  Scenario: SN29_Validate_the_MnS_Audit_Check_Report
    Given That I have stock check list id
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for " M&S - Audit Check Report"
    And Verify that the record is displayed for M&S - Audit Check Report
    Then Enter the sortation
    Then Validate the confirmation page for M&S - Audit Check Report 
    Then Validate the report selection page for M&S - Audit Check Report completed

   @Sprint05 @Reports @TC31_Validate_the_M_n_S_URNs_on_Pallet_Report
  Scenario Outline: SN31_Validate the M&S - URNs on Pallet Report
    Given Data to be inserted and received with PalletID with "Released","NONRETAIL","5542" for "<SkuId>"
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - 'URNs on Pallet Report"
    And Verify that the record is displayed for M&S - URNs on Pallet Report
    And Enter PalletId
    Then Validate the confirmation page for M&S - URNs on Pallet Report
    Then Validate the report selection page for M&S - URNs on Pallet Report

    Examples: 
      | SkuId              |
      | 000000000021071852 |

   @Sprint05 @Reports @TC32_Validate_the_MnS_VAS_Report
  Scenario: SN32_Validate the M&S VAS Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed

   @Sprint05 @Reports @TC39_Validate_container_report
  Scenario: SN39_Validate the M&S - M&S - Customs Valuation for Consignment Report and Customs Valuation for Consignment Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed
    Then Select Print to screen and proceed next
    And Clear the previous search
    And Search for "Inspection"
    And Verify that the record is displayed for M&S - Customs Inspection Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Inspection Report
    Then Validate the report selection page for M&S - Customs Inspection Report completed

   @Sprint05 @Reports @TC41_Validate_the_MNS_Report_stock_moved_from_RED_to_GREEN_status
  Scenario: SN41_Validate the M&S -stock_moved_from_RED_to_GREEN_status
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

   @Sprint05 @Reports @TC42_Validate_RED_to_GREEN_report
  Scenario: SN42_Validate RED to GREEN report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

   @Sprint05 @Reports @TC44_Validate_the_Review_Red_Data_report
  Scenario: SN44_Validate the Review Red Data report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Red Stock Report"
    And Verify that the record is displayed for M&S - Red Stock Report
    Then Validate the confirmation page for M&S - Red Stock Report
    Then Validate the report selection page for M&S - Red Stock Report completed

   @Sprint05 @Reports @TC46_Validate_consumables_daily_report
  Scenario: SN46_Validate consumables daily report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed

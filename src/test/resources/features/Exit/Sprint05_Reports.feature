@Reports
Feature: Report Generation
  As I search for different reports
  and put different parameters
  so that I view the reports

  @inProgres @Reports @TC01_Validate_the_M_n_S_Identify_URN_Report
  Scenario: Validate the M&S - Identify URN Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Identify URN Report
    And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    Then Validate the report selection page for Identify URN completion

  @Reports @TC04_Validate_the_M_n_S_NonShipped_greater_than_4_weeks_report
  Scenario Outline: Validate the M&S - Non-Shipped greater than 4 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Non-Shipped greater than 4 weeks Report"
    And Verify that the record is displayed M&S - Non-Shipped greater than 4 weeks Report
    Then Enter the status "<status>" as parameter
    Then Validate the confirmation page for M&S - Non-Shipped greater than 4 weeks Report
    Then Validate the report selection page for M&S - Non-Shipped greater than 4 weeks completed

    Examples: 
      | status |
      | GREEN  |

  @completed @Reports @TC05_Validate_the_M_n_S_Gains_or_loss_report
  Scenario: Validate the M&S - Gains and Loss Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S -  Gains and Loss Report"
    And Verify that the record is displayed for M&S Gains Or Loss Report
    Then Validate the confirmation page for Gains and Loss Report
    Then Validate the report selection page for Gains and Loss Report completed

  @completed @Reports @TC06_Validate_the_M_n_S_allocation_vs_receipts_across_last_3_weeks_report
  Scenario: Validate the M&S - Allocation vs Receipts across last 3 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Allocation vs Receipts across last 3 weeks Report"
    And Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed

  @completed @Reports @TC08_Validate_the_M_n_S_stock_status_report
  Scenario: Validate M&S - Stock Status Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Stock Status"
    And Verify that the record is displayed for M&S - Stock Status Report
    Then Validate the confirmation page for M&S - Stock Status Report
    Then Validate the report selection page for M&S - Stock Status completed

  @completed @Reports @TC09_Validate_the_M_n_S_weekly_receipts_and_despatches_report
  Scenario: Validate the  M&S - Weekly Receipts and Despatches Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Weekly Receipts and Despatches by Customer"
    And Verify that the record is displayed for M&S - Weekly Receipts and Despatches by Customer Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Weekly Receipts and Despatches by Customer Report
    Then Validate the report selection page for M&S - Weekly Receipts and Despatches by Customer completed

  @completed @Reports @TC11_Validate_the_M_n_S_prohibition_report
  Scenario: Validate the M&S - Prohibition Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Prohibition"
    And Verify that the record is displayed for M&S - Prohibition Report
    Then Validate the confirmation page for M&S - Prohibition Report
    Then Validate the report selection page for M&S - Prohibition completed

  @completed @Reports @TC12_Validate_the_M_n_S_trusted_report
  Scenario: Validate the M&S - Trusted Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Trusted Report"
    And Verify that the record is displayed for Trusted Report
    Then Validate the confirmation page for Trusted Report
    Then Validate the report selection page for Trusted Report completion

  @completed @Reports @TC13_Validate_the_M_n_S_batch_id_and_BBE_report
  Scenario: Validate the M&S - Batch ID & BBE Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Batch ID & BBE Report"
    And Verify that the record is displayed for BatchId and  BBE Report
    Then Validate the confirmation page for Batch ID Report
    Then Validate the report selection page for BatchId completion

  @completed @Reports @TC14_Validate_the_M_n_S_Outstanding_Pallets_to_load_report
  Scenario: Validate the M&S - Outstanding Pallets to Load Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Outstanding Pallets to Load Report"
    And Verify that the record is displayed for M&S - Outstanding Pallets to Load Report
    Then Enter Trailer number
    Then Validate the confirmation page for M&S - Outstanding Pallets to Load Report
    Then Validate the report selection page for M&S - Outstanding Pallets to Load Report completed

  @completed @TC15_Validate_the_M_n_S_Red_Location_Report
  Scenario: Validate the M&S - Red Location Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Red Location Report"
    And Verify that the record is displayed for Red Location Report
    Then Validate the confirmation page for RedLocation Report
    Then Validate the report selection page for Red Location completion

  @completed @Reports @TC16_Validate_the_M_n_S_Black_Stock_Status_Report
  Scenario: Validate the M&S - Black Stock Status Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Black Stock Status Report"
    And Verify that the record is displayed for Black Stock Status Report
    Then Validate the confirmation page for Black Stock Status Report
    Then Validate the report selection page for Black Stock Status completion

  @Reports @TC19_Validate_the_M_N_S_Operative_Performance_Report
  Scenario: Validate_the_M&S_Operative_Performance_Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Operative Performance Trusted Report"
    And Verify that the record is displayed for Operative Performance Trusted Report
    And Enter start and end date
    And Validate the confirmation page for Operative Performance Trusted Report
    Then Validate the report selection page for Operative Performance Trusted Report

  @Reports @TC20_Validate_the_MNS_Green_Stock_Available_to_Pick_Flow_Report
  Scenario: Validate the M&S -Green Stock Available to Pick-FLOW Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

  @Reports @TC22_Validate_the_MNS_Soiled_Damaged_Report
  Scenario: Validate the M&S - Soiled & Damaged Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Soiled and Damaged"
    And Verify that the record is displayed for Soiled and Damaged
    And Validate the confirmation page for Soiled and Damaged
    Then Validate the report selection page for Soiled and Damaged

  @Reports @TC23_Validate_the_MNS_Pallet_not_Consigned_Report
  Scenario: Validate the M&S - Pallet not Consigned Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Pallet not Consigned Report"
    And Verify that the record is displayed for Pallet not Consigned Report
    And Enter customer id "4624"
    And Validate the confirmation page for Pallet not Consigned Report
    Then Validate the report selection page for Pallet not Consigned Report

  @Reports @TC24_Validate_the_MNS_Unpicked_not_relocated_Report
  Scenario: Validate the M&S - Unpicked, not relocated Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Unpicked Not Relocated Stock"
    And Verify that the record is displayed for Unpicked Not Relocated Stock
    And Validate the confirmation page for Unpicked Not Relocated Stock
    Then Validate the report selection page for Unpicked Not Relocated Stock

  @completed @Reports @TC26_Validate_the_M_n_S_Customs_valuation_for_consignment_report
  Scenario: Validate the M&S - Customs Valuation for Consignment Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed

    @completed @Reports @TC31_Validate_the_M_n_S_URNs_on_Pallet_Report
  Scenario Outline: Validate the M&S - URNs on Pallet Report
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

  @completed @Reports @TC39_Validate_container_report
  Scenario: Validate the M&S - M&S - Customs Valuation for Consignment Report and Customs Valuation for Consignment Report
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
    
    
    @completed @Reports @TC27_Validate_the_M_n_S_weekly_summary_report
  Scenario: Validate the M&S - Weekly Summary Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Weekly Summary Report"
    And Verify that the record is displayed for M&S - Weekly Summary Report
    And Enter the date for end of week as parameter
    Then Validate the confirmation page for M&S - Weekly Summary Report
    Then Validate the report selection page for M&S - Weekly Summary Report completed

  @completed @Reports @TC28_Validate_the_M_n_S_pallet_build_report
  Scenario: Validate the M&S - Pallet Build Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Pallet Built Report"
    And Verify that the record is displayed for M&S - Pallet Built Report
    Then Validate the confirmation page for M&S - Pallet Built Report
    Then Validate the report selection page for M&S - Pallet Built Report completed
    
    @completed @Reports @TC29_Validate_the_M_n_S_short_invoice_for_container_report
  Scenario: Validate the M&S - Short Invoice for Container Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Short Invoice for Container Report"
    And Verify that the record is displayed for M&S - Short Invoice for Container Report
    Then Enter Trailer number
    Then Validate the confirmation page for M&S - Short Invoice for Container Report
    Then Validate the report selection page for M&S - Short Invoice for Container Report completed
    
    
    @completed @Reports @TC17_Validate_the_MnS_Empty_Red_Locations_Report
  Scenario: Validate the M&S - Empty Red Locations Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Empty Red Locations Report"
    And Verify that the record is displayed for M&S - Empty Red Locations Report
    Then Enter the red location as parameter
    Then Validate the confirmation page for M&S - Empty Red Locations Report
    Then Validate the report selection page for M&S - Empty Red Locations Report
    
    @completed @Reports @TC07_Validate_the_MnS_Stock_Allocation_Reportt
  Scenario: Validate the M&S - Allocation vs Receipts across last 3 weeks Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Allocation vs Receipts across last 3 weeks Report"
    And Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed
    
    @completed @Reports @TC25_Validate_the_MnS_Stock_in_Putaway_Report
  Scenario: Validate the M&S - Stock in Putaway Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Stock In RED Putaway Locations"
    And Verify that the record is displayed for M&S - Stock In RED Putaway Locations
    Then Enter the customer as parameter
    Then Validate the confirmation page for M&S - Stock In RED Putaway Locations
    Then Validate the report selection page for M&S - Stock In RED Putaway Locations
    
    
    
    
    

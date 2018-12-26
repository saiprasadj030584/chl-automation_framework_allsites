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



 @Reports  @TC04_Validate_the_M_n_S_NonShipped_greater_than_4_weeks_report
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
    |status|
    |GREEN|
   
    @completed @Reports  @TC05_Validate_the_M_n_S_Gains_or_loss_report
Scenario: Validate the M&S - Gains and Loss Report 
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S -  Gains and Loss Report"
    And Verify that the record is displayed for M&S Gains Or Loss Report
    Then Validate the confirmation page for Gains and Loss Report
    Then Validate the report selection page for Gains and Loss Report completed
    
    @completed @Reports  @TC06_Validate_the_M_n_S_allocation_vs_receipts_across_last_3_weeks_report
Scenario: Validate the M&S - Allocation vs Receipts across last 3 weeks Report
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Allocation vs Receipts across last 3 weeks Report"
    And Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report
    Then Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed
   
       
   @completed @Reports  @TC08_Validate_the_M_n_S_stock_status_report
Scenario: Validate M&S - Stock Status Report 
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Stock Status"
    And Verify that the record is displayed for M&S - Stock Status Report
    Then Validate the confirmation page for M&S - Stock Status Report
    Then Validate the report selection page for M&S - Stock Status completed
    
   @completed @Reports  @TC09_Validate_the_M_n_S_weekly_receipts_and_despatches_report
Scenario: Validate the  M&S - Weekly Receipts and Despatches Report
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Weekly Receipts and Despatches by Customer"
    And Verify that the record is displayed for M&S - Weekly Receipts and Despatches by Customer Report
    Then Enter the date for commencing week as parameter
    Then Validate the confirmation page for M&S - Weekly Receipts and Despatches by Customer Report
    Then Validate the report selection page for M&S - Weekly Receipts and Despatches by Customer completed
    #
    @completed @Reports  @TC11_Validate_the_M_n_S_prohibition_report
    Scenario: Validate the M&S - Prohibition Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Prohibition"
    And Verify that the record is displayed for M&S - Prohibition Report
    Then Validate the confirmation page for M&S - Prohibition Report
    Then Validate the report selection page for M&S - Prohibition completed
    
    @completed @Reports  @TC12_Validate_the_M_n_S_trusted_report
    Scenario: Validate the M&S - Trusted Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S - Trusted Report
    And Verify that the record is displayed for Trusted Report
    Then Validate the confirmation page for Trusted Report
    Then Validate the report selection page for Trusted Report completion
    
 		@completed @Reports @TC13_Validate_the_M_n_S_batch_id_and_BBE_report
    Scenario: Validate the M&S - Batch ID & BBE Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S - Batch ID & BBE Report
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
    
    
     @completed @Reports  @TC26_Validate_the_M_n_S_Customs_valuation_for_consignment_report
Scenario: Validate the M&S - Customs Valuation for Consignment Report
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Customs Valuation for Consignment Report"
    And Verify that the record is displayed for M&S - Customs Valuation for Consignment Report
    And Enter consignment name
    Then Validate the confirmation page for M&S - Customs Valuation for Consignment Report
    Then Validate the report selection page for M&S - Customs Valuation for Consignment Report completed
    
    #M&S - Pallet Built Report
    #M&S - Weekly Summary Report
    
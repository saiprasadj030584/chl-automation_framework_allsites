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
    Then Validate the confirmation page for Identify Urn Report
    Then Validate the report selection page for Identify URN completion


@inProgres @Reports  @TC04_Validate_the_M_n_S_NonShipped_greater_than_4_weeks_report
Scenario: Validate the M&S - Non-Shipped greater than 4 weeks Report
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S - Non-Shipped greater than 4 weeks Report
    And Verify that the record is displayed for Identify Urn Report
    Then Validate the confirmation page for Identify Urn Report
    Then Validate the report selection page for Identify URN completion
    
        
    @inProgres @Reports  @TC05_Validate_the_M_n_S_Gains_or_loss_report
Scenario: Validate the M&S - Gains and Loss Report 
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S -  Gains and Loss Report
    And Verify that the record is displayed for Identify Urn Report
    Then Validate the confirmation page for Identify Urn Report
    Then Validate the report selection page for Identify URN completion
    
    @inProgres @Reports  @TC06_Validate_the_M_n_S_allocation_vs_receipts_across_last_3_weeks_report
Scenario: Validate the M&S - Allocation vs Receipts across last 3 weeks Report
 Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S - Allocation vs Receipts across last 3 weeks Report
    And Verify that the record is displayed for Identify Urn Report
    Then Validate the confirmation page for Identify Urn Report
    Then Validate the report selection page for Identify URN completion
    
   #@inProgres @Reports  @TC08_Validate_the_M_n_S_stock_status_report
#Scenario: Validate M&S - Stock Status Report 
 #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Stock Status Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
   #@inProgres @Reports  @TC09_Validate_the_M_n_S_weekly_receipts_and_despatches_report
#Scenario: Validate the  M&S - Weekly Receipts and Despatches Report
 #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Weekly Receipts and Despatches Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
    #@inProgres @Reports  @TC11_Validate_the_M_n_S_prohibition_report
    #Scenario: Validate the M&S - Prohibition Report
    #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Prohibition Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
    #@inProgres @Reports  @TC12_Validate_the_M_n_S_trusted_report
    #Scenario: Validate the M&S - Trusted Report
    #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Trusted Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
 #		@inProgres @Reports @TC13_Validate_the_M_n_S_batch_id_and_BBE_report
    #Scenario: Validate the M&S - Batch ID & BBE Report
    #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Batch ID & BBE Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
    #@inProgres @Reports @TC14_Validate_the_M_n_S_Outstanding_Pallets_to_load_report
    #Scenario: Validate the M&S - Outstanding Pallets to Load Report
    #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Outstanding Pallets to Load Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
    #@inProgres @Reports @TC15_Validate_the_M_n_S_Red_Location_Report
    #Scenario: Validate the M&S - Red Location Report
    #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Red Location Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #
    #@inProgres @Reports @TC16_Validate_the_M_n_S_Black_Stock_Status_Report
    #Validate the M&S - Black Stock Status Report
     #Given Login to JDA Dispatcher web screen
    #And Go to Reports Selection and click
    #Then Select Print to screen and proceed next
    #And Search for the M&S - Black Stock Status Report
    #And Verify that the record is displayed for Identify Urn Report
    #Then Validate the confirmation page for Identify Urn Report
    #Then Validate the report selection page for Identify URN completion
    #

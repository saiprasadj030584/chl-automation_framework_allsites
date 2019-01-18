@Reports
Feature: Report Generation
  As I search for different reports
  and put different parameters
  so that I view the reports

  @completed @Reports @TC03_Validate_container_report
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

  @Reports @TC04_Validate_the_MNS_Report_stock_moved_from_RED_to_GREEN_status
  Scenario: Validate the M&S -stock_moved_from_RED_to_GREEN_status
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

  @Reports @TC05_Validate_RED_to_GREEN_report
  Scenario: Validate RED to GREEN report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S - Green Stock Available to Pick Flow"
    And Verify that the record is displayed for Green Stock Available to Pick Flow
    And Enter customer id "4624"
    And Validate the confirmation page for Green Stock Available to Pick Flow
    Then Validate the report selection page for Green Stock Available to Pick Flow

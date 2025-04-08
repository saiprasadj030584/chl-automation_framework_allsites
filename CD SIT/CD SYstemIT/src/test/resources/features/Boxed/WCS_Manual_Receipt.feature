@WCS_Manual_Receipt
Feature: As a users to verify the wcs manual receipt functionality

  @SIT_BGI_Direct_WCS_Manual_Receipt_processing @BoxedGoods
  Scenario: The objective of the test is to confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing
    Given To confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part

  @SIT_BGI_FSV_WCS_Manual_Receipt_processing @BoxedGoods
  Scenario: The objective of the test is to confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing
    Given To confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part

  @SIT_BGI_UKHC_WCS_Manual_Receipt_processing @BoxedGoods
  Scenario: The objective of the test is to confirm UKHC delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing
    Given To confirm UKHC delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part

  @SIT_BGI_PerUna_WCS_Manual_Receipt_processing @BoxedGoods
  Scenario: The objective of the test is to confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing
    Given To confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part

  @SIT_BGI_Direct_Advanced_error_handling_WCS_Manual_Receipt_processing @BoxedGoods
  Scenario: The objective of the test is to confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received processing
    Given To confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received processing part

  @SIT_BGI_Direct_WCS_Manual_Receipt_validation @BoxedGoods
  Scenario: The objective of the test is to confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation
    Given To confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part

  @SIT_BGI_FSV_WCS_Manual_Receipt_validation @BoxedGoods
  Scenario: The objective of the test is to confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation
    Given To confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part

  @SIT_BGI_UKHC_WCS_Manual_Receipt_validation @BoxedGoods
  Scenario: The objective of the test is to confirm UKHC delivery load units that cannot be automatically received  by the omniscanner are sent to a repack workstation for manual receiving
    Given To confirm UKHC delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part

  @SIT_BGI_PerUna_WCS_Manual_Receipt_validation @BoxedGoods
  Scenario: The objective of the test is to confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving
    Given To confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part

  @SIT_BGI_Direct_Advanced_error_handling_WCS_Manual_Receipt_validation @BoxedGoods
  Scenario: The objective of the test is to confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received
    Given To confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received validation part

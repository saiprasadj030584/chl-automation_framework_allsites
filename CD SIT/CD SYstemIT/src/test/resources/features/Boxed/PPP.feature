@PPP
Feature: As a users to verify the pre pack prepared functionality

  @SIT_BGI_Direct_PPP_Repack_BCS @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS
    Given To be confirmed Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS

  @SIT_BGI_FSV_PPP_Repack_BCS @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS
    Given To be confirmed fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS

  @SIT_BGI_UKHC_PPP_Repack_BCS @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS
    Given To be confirmed ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS

  @SIT_BGI_Peruna_PPP_Repack_BCS @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS
    Given To be confirmed peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS

  @SIT_BGI_Direct_PPP_Repack_BCS_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation
    Given To be confirmed Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation

  @SIT_BGI_FSV_PPP_Repack_BCS_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation
    Given To be confirmed fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation

  @SIT_BGI_UKHC_PPP_Repack_BCS_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation
    Given To be confirmed ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation

  @SIT_BGI_Peruna_PPP_Repack_BCS_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation
    Given To be confirmed peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation

  @SIT_BGI_PPP_Normal_SKU @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP.
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP

  @SIT_BGI_PPP_Fragile_SKU @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and cover the processing of a load unit containing an ecom only, fragile SKU at PPP
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing an ecom only, fragile SKU at PPP

  @SIT_BGI_PPP_Max @Processing_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over 13.2kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over 13.2kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs

  @SIT_BGI_PPP_Normal_SKU_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP validation
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP validation part

  @SIT_BGI_PPP_Fragile_SKU_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and cover the processing of a load unit containing an ecom only, fragile SKU at PPP validation
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing an ecom only, fragile SKU at PPP validation part

  @SIT_BGI_PPP_Max_Val @Validation_PPP @Boxedgoods
  Scenario: The objective of the test is to confirm the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over 13.2kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs validation
    Given To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over 13.2kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs validation part

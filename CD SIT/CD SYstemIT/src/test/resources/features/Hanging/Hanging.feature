@Hanging
Feature: As a user to verify the HangingGoods

  @LCK_WMS_DBH_001_Processing @HangingProcessing1
  Scenario: To check where inventory in the DBH is locked in the WMS that the same inventory is locked in WCS.
    Given To check where inventory in the DBH is locked in the WMS and WCS as processing part

  @LCK_WMS_MWH_Processing @HangingProcessing2
  Scenario: To check where inventory in the MWH is locked in the WMS that the same inventory is locked in WCS
    Given To check where inventory in the MWH is locked in the WMS and WCS as processing part

  @UNLCK_WMS_MWH_003_Processing @HangingProcessing3
  Scenario: To check where inventory in the MWH is unlocked in the WMS that the same inventory is unlocked in WCS
    Given To check where inventory in the MWH is unlocked in the WMS and WCS as processing part

  @RCPT_DIR_CNI_001_Processing @HangingProcessing4
  Scenario: To be verify the FW FSV delivery as processing part
    Given To check that we can receive a RCPT_DIR_CNI_Processing

  @RCPT_DIR_CNI_001_Validating @HangingValidation4
  Scenario: To be verify the FW FSV delivery as processing part
    Given To check that we can receive a RCPT_DIR_CNI_Validating

  @RCPT_DIR_FW_001_Processing @HangingProcessing5
  Scenario: To be verify the FW FSV delivery as processing part
    Given To check that we can receive a RCPT_DIR_FW_Processing

  @RCPT_DIR_FW_001_Validating @HangingValidation5
  Scenario: To be verify the FW FSV delivery as processing part
    Given To check that we can receive a RCPT_DIR_FW_Validating

  @PUT_DIR_HBH_001__Processing @HangingProcessing6
  Scenario: To check that inventory receipted from Goods In Hanging for a Direct delivery can be putaway to DBH
    Given To check that inventory receipted from Goods In Hanging PUT_DIR_HBH as processing part

  @PUT_DIR_HBH_001_Validating @HangingValidation6
  Scenario: To check that inventory receipted from Goods In Hanging for a Direct delivery can be putaway to DBH
    Given To check that inventory receipted from Goods In Hanging PUT_DIR_HBH as Validation part

  @RCPT_DIR_STNRD_001_Processing @HangingProcessing7
  Scenario: To be verify the Direct delivery as processing part
    Given To check that we can receive a RCPT_DIR_STNRD_Processing

  @RCPT_DIR_STNRD_001_validating @HangingProcessing7
  Scenario: To be verify the Direct delivery as validation part
    Given To check that we can receive a RCPT_DIR_STNRD_validating

  @STK_MWH_ADJ_NEG_001_Processing @HangingProcessing8
  Scenario: To be verify the Negative adjustment as processing part
    Given To check that we can do Negative adjustment

  @STK_MWH_ADJ_POS_002 @HangingProcessing9
  Scenario: To be verify the Positive adjustment as processing part
    Given To check that we can do Positive adjustment

  @PUT_DIR_HBH_001_Processing @HangingProcessing10
  Scenario: To be verify the Putaway in HBH location as processing part
    Given To check that we can do Putaway in HBH location as processing part

  @PUT_DIR_HBH_001_validating @HangingValidation10
  Scenario: To be verify the Putaway in HBH location  as validation part
    Given To check that we can do Putaway in HBH location as validation part

  @PUT_DIR_DBH_001_Processing @HangingProcessing11
  Scenario: To be verify the Putaway in DBH location as processing part
    Given To check that we can do Putaway in DBH location as processing part

  @PUT_DIR_DBH_001_validating @HangingValidation11
  Scenario: To be verify the Putaway in DBH location  as validation part
    Given To check that we can do Putaway in DBH location as validation part

  @PPUT_DIR_MWH_001_Processing @HangingProcessing12
  Scenario: To be verify the Putaway in MWH location as processing part
    Given To check that we can do Putaway in MWH location as processing part

  @PUT_DIR_MWH_001_validating @HangingValidation12
  Scenario: To be verify the Putaway in MWH location  as validation part
    Given To check that we can do Putaway in MWH location as validation part

  @RCPT_FSV_STNRD_001_Processing @HangingProcessing13
  Scenario: To be verify the FSV delivery as processing part
    Given To check that we can receive a RCPT_FSV_STNRD_Processing

  @RCPT_FSV_STNRD_001_Validation @HangingProcessing13
  Scenario: To be verify the FSV delivery as validation part
    Given To check that we can receive a RCPT_FSV_STNRD_validating

  @RCPT_UKHC_001_processing @HangingProcessing14
  Scenario: To be verify the UKHC delivery as processing part
    Given To check that we can receive a RCPT_UKHC_processing

  @RCPT_UKHC_001_validation @HangingProcessing14
  Scenario: To be verify the UKHC delivery as validation part
    Given To check that we can receive a RCPT_UKHC_validation

  @RCPT_DIR_EXCPTN_001_Processing @HangingProcessing15
  Scenario: To be verify the DIR EXCPTN as processing part
    Given To check that we can receive a DIR EXCPTN Processing

  @RCPT_DIR_EXCPTN_001_Validation @HangingProcessing15
  Scenario: To be verify the DIR EXCPTN as validation part
    Given To check that we can receive a DIR EXCPTN validation

  @RCPT_CR814_RL_DIR_001_Processing @HangingProcessing16
  Scenario: To be verify the CR814RLDIR as processing part
    Given To check that we can receive a CR RLDIR processing

  @RCPT_CR814_RL_DIR_001_validation @HangingProcessing16
  Scenario: To be verify the CR814RLDIR as validation part
    Given To check that we can receive a CR RLDIR validation

  @PUT_BCH_SAME_1000_Processing @HangingProcessing17
  Scenario: To be verify the PUT_BCH_SAME as processing part
    Given To check that we can receive a PUT_BCH_SAME processing

  @PUT_BCH_SAME_1000_Validation @HangingProcessing17
  Scenario: To be verify the PUT_BCH_SAME as validation part
    Given To check that we can receive a PUT_BCH_SAME validation

  @MWH_892_VAL_001 @HangingProcessing18
  Scenario: To be verify the MWH VAL as processing part
    Given To check that we can receive a MWH VAL processing

  @RCPT_FSV_CNI_Processing @HangingProcessing19
  Scenario: To be verify the RCPT FSV CNI as processing part
    Given To check that we can receive a RCPT FSV CNI processing

  @RCPT_FSV_CNI_Validation @HangingProcessing19
  Scenario: To be verify the RCPT FSV CNI as validation part
    Given To check that we can receive a RCPT FSV CNI validation

  @RCPT_DIR_FWL_001_Processing @HangingProcessing19
  Scenario: To be verify the RCPT FSV FWL as processing part
    Given To check that we can receive a RCPT FSV FWL processing

  @RCPT_DIR_FWL_001_Validation @HangingProcessing19
  Scenario: To be verify the RCPT FSV FWL as validation part
    Given To check that we can receive a RCPT FSV FWL validation

@Palletisation
Feature: As a users to verify the palletisation functionality

  @SIT_BGI_Direct_Palletisation @ProcessingPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm direct deliveries load units can be received sent to palletisation and stored in the HBW
    Given To be confirmed direct deliveries load units can be received sent to palletisation and stored in the HBW

  @SIT_BGI_UKHC_Palletisation @ProcessingPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm ukhc deliveries load units can be received sent to palletisation and stored in the HBW
    Given To be confirmed ukhc deliveries load units can be received sent to palletisation and stored in the HBW

  @SIT_BGI_Direct_Palletisation_Val @ValidationPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm direct deliveries load units can be received sent to palletisation and stored in the HBW
    Given To be confirmed direct deliveries load units can be received sent to palletisation and stored in the HBW validation part

  @SIT_BGI_UKHC_Palletisation_Val @ValidationPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm ukhc deliveries load units can be received sent to palletisation and stored in the HBW
    Given To be confirmed ukhc deliveries load units can be received sent to palletisation and stored in the HBW validation part

  @SIT_BGI_Direct_GOH_SKU_HBW @ProcessingPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm Direct delivery load units containing GOH SKUs are sent to palletisation
    Given To be confirmed Direct delivery load units containing GOH SKUs are sent to palletisation

  @SIT_BGI_Direct_Large_SKU_HBW @ProcessingPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm Direct delivery load units containing Large SKUs are sent to palletisation
    Given To be confirmed Direct delivery load units containing Large SKUs are sent to palletisation

  @SIT_BGI_Direct_GOH_SKU_HBW_Val @ValidationPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm Direct delivery load units containing GOH SKUs are sent to palletisation
    Given To be confirmed Direct delivery load units containing GOH SKUs are sent to palletisation validation part

  @SIT_BGI_Direct_Large_SKU_HBW_Val @ValidationPalletisation @Boxedgoods
  Scenario: The objective of the test is to confirm Direct delivery load units containing Large SKUs are sent to palletisation
    Given To be confirmed Direct delivery load units containing Large SKUs are sent to palletisation validation part

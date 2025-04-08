#Author Dhivya N
@Ollerton_ShoeReceiving
Feature: As a user to verify the GB_Ollerton shoecases

  @Singleshoe_LeftUPCReceiving @complete
  Scenario: To Process receiving for single Leftshoe sku
    #Given User done the data setup for the given sku
    #When user perform receiving
    Then Sku should reach the IMPERFHOLD location after putaway

  @Singleshoe_RightUPCReceiving @complete
  Scenario: To Process receiving for single Rightshoe sku
    Given User done the data setup for the given sku
    When user perform receiving
    Then Sku should reach the IMPERFHOLD location after putaway

  @Differentshoe_DifferentUPC_LeftRight_Receiving @complete
  Scenario: To Process receiving for left UPC & Different sku right UPC sku
    Given User done the data setup for the given sku
    When user perform receiving
    Then Receiving should be failed

  @Differentshoe_DifferentUPC_LeftLeft_Receiving @complete
  Scenario: To Process receiving for shoe sku left UPC & Different sku left UPC
    Given User done the data setup for the given sku
    When user perform receiving
    Then Receiving should be failed

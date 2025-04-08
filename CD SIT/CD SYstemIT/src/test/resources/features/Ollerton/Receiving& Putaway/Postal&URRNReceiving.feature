#Author Dhivya N
@Ollerton_Receiving
Feature: As a user to verify the GB_Ollerton cases

  @PostalReceving @complete
  Scenario: To Process postal receiving for Box skus
    Given As User to launch RP WMS and User done the data setup for the given sku
    And As user login to HHT
    When User perform postal receiving
    Then Messageid should be generated once postal receiving completed

  @URRNReceving @complete
  Scenario: To Process URRN receiving for Box skus
    Given User done the data setup for box sku
    When User perform URRN receiving
    Then Messageid should be generated once URRN receiving completed

  @Postal&URRNReceving @complete
  Scenario: To Process postal & URRN receiving for Box skus
    Given User done the data setup for postal & URRN receiving
    When User perform Postal and URRN receiving
    Then Messageid should be generated for postal and URRN once receiving completed

  @RecevingLargekus @complete
  Scenario: To Process receiving for Oversize skus
    Given User done the data setup for oversize
    When User perform receiving for oversize sku
    Then Sku should reach the oversize location after putaway

@pallet_type_update
Feature: pallet type update
  As a warehouse user
  I want to update the pallet type for the item 
  So that the correct pallet can be updtaed

  @complete @po @pallet_type_update
  Scenario Outline: Pallet type update
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the sku "<sku>" of pre-advice id "<preAdviceId>" have the pallet type as "<existingPalletType>"
    When I update the pallet type as "<palletType>"
    Then the pallet type should be updated

    Examples: 
      | preAdviceId | sku      | existingPalletType | palletType |
      |  2058206817 | 21036013 | CAGE               | CHEP       |
      |  2058206817 | 21036013 | CHEP               | BROWN      |
      |  2058206817 | 21036013 | CHEP               | RED        |
      |  2058206817 | 21036013 | EURO               | CHEP       |
      |  2058206817 | 21036013 | RED                | BROWN      |
      |  2058206817 | 21036013 | RED                | CHEP       |

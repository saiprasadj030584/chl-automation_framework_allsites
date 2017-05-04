@pallet_type
Feature: pallet type update
  As a warehouse user
  I want to update the pallet type for the item 
  So that the correct pallet can be updtaed

  @wip1
  Scenario Outline: Pallet type update
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the sku "<sku>" of pre-advice id "<preAdviceId>" have the pallet type as "<existingPalletType>"
    When I update the pallet type as "<palletType>"
    Then the pallet type should be updated

    Examples: 
      | preAdviceId | sku      | existingPalletType | palletType |
      |  2050004483 | 21033574 | CAGE               | CHEP       |
      |  0040000098 | 20001670 | CHEP               | BROWN      |
      |  0071000091 | 21107028 | CHEP               | RED        |
      |  8050004505 | 21036013 | EURO               | CHEP       |
      |  7165010017 | 21101988 | RED                | BROWN      |
      |	 716501091	| 20001717 | RED 								| CHEP			 |
